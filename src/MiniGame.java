import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class MiniGame {
    private JFrame parentFrame; 
    private JFrame gameFrame;
    private String petType;
    private int score = 0;
    private int ballX, ballY;
    private int platformX = 300; 
    private int platformWidth = 200;
    private int platformHeight = 40;
    private int ballSize = 60;
    private boolean gameRunning = true;
    private boolean gamePaused = false;
    private Timer gameTimer;
    private Random random = new Random();
    private final int PLATFORM_SPEED = 20;
    private final int BALL_SPEED = 7;
    private JLabel scoreLabel;
    private JLabel messageLabel;
    private JPanel gamePanel; 
    
    private Color backgroundColor = Main.LB;
    private Color platformBallColor = new Color(101, 67, 33); 
    
    public MiniGame(JFrame parentFrame, String petType) { 
        this.parentFrame = parentFrame;
        this.petType = petType;
    }
    
    public void startGame() {
        gameFrame = new JFrame("Catch the Ball!");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(800, 600);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setResizable(false);
        
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(backgroundColor);
                g.fillRect(0, 0,800, 600);
                g.setColor(platformBallColor);
                g.fillRect(platformX, 600 - 50, platformWidth, platformHeight);
                if (gameRunning && !gamePaused) {
                    g.setColor(platformBallColor);
                    g.fillOval(ballX, ballY, ballSize, ballSize);
                }
                if (gamePaused) {
                    g.setColor(new Color(0, 0, 0, 180));
                    g.fillRect(0, 0, 800, 600);
                    
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, 60));
                    String pauseText = "ПАУЗА";
                    int textWidth = g.getFontMetrics().stringWidth(pauseText);
                    g.drawString(pauseText, (800 - textWidth) / 2, 600 / 2);
                    
                    g.setFont(new Font("Arial", Font.PLAIN, 30));
                    String continueText = "Нажмите P для продолжения";
                    textWidth = g.getFontMetrics().stringWidth(continueText);
                    g.drawString(continueText, (800 - textWidth) / 2, 600 / 2 + 70);
                }
            }
        };
        gamePanel.setLayout(null);
        gamePanel.setPreferredSize(new Dimension(800, 600));
        scoreLabel = new JLabel("Счет: 0");
        scoreLabel.setBounds(20, 20, 300, 40);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 36));
        scoreLabel.setForeground(Color.BLACK);
        
        messageLabel = new JLabel("Поймайте 10 мячей! Управление: A и D, Пауза: P");
        messageLabel.setBounds(150, 45, 600, 40);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        messageLabel.setForeground(Color.BLACK);
        
        JLabel instructionLabel = new JLabel("← A / D → для движения | P - пауза");
        instructionLabel.setBounds(200, 600 - 100, 400, 30);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        instructionLabel.setForeground(Color.DARK_GRAY);
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        gamePanel.add(scoreLabel);
        gamePanel.add(messageLabel);
        gamePanel.add(instructionLabel);
        
        createNewBall(); 
        
        gameTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameRunning && !gamePaused) { 
                    ballY += BALL_SPEED;
                    
                    if (ballY + ballSize >= 600 - 50 && ballY <= 600 - 50 + platformHeight && ballX + ballSize >= platformX && ballX <= platformX + platformWidth) {
                        score += 10;
                        scoreLabel.setText("Счет: " + score);
                        
                        if (score >= 100) {
                            endGame(true);
                        } else {
                            createNewBall();
                        }
                    }
                    
                    if (ballY > 600) {
                        createNewBall();
                    }
                    
                    gamePanel.repaint(); 
                }
            }
        });
        
        gamePanel.setFocusable(true); 
        gamePanel.requestFocusInWindow(); 
        
        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!gamePaused) {
                    switch(e.getKeyCode()) {
                        case KeyEvent.VK_A: 
                        case KeyEvent.VK_LEFT:
                            if (platformX > 0) {
                                platformX = Math.max(0, platformX - PLATFORM_SPEED);
                            }
                            break;
                        case KeyEvent.VK_D:
                        case KeyEvent.VK_RIGHT:
                            if (platformX < 800 - platformWidth) {
                                platformX = Math.min(800 - platformWidth, platformX + PLATFORM_SPEED);
                            }
                            break;
                        case KeyEvent.VK_P:
                            togglePause(); 
                            break;
                    }
                } else {
                    if (e.getKeyCode() == KeyEvent.VK_P) {
                        togglePause();
                    }
                }
                gamePanel.repaint();
            }
        });
        
        gameFrame.addWindowFocusListener(new WindowAdapter() { 
            @Override
            public void windowGainedFocus(WindowEvent e) { 
                gamePanel.requestFocusInWindow();
            }
        });
        
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);
        gameTimer.start();
    }
    private void createNewBall() {
        ballX = random.nextInt(800 - ballSize);
        ballY = -50;
    }
    
    private void togglePause() {
        gamePaused = !gamePaused;
        gamePanel.repaint();
    }
    
    private void endGame(boolean success) {
    gameRunning = false;
    gameTimer.stop();
    
    if (success) {
        gameFrame.dispose();
        parentFrame.setVisible(true);
        if (Main.play < 100) {
            Main.play += 100;
            if (Main.play > 100) {
                Main.play = 100;
            }
        }
        if (Main.playTimer != null) {
            Main.playTimer.start();
        }
        
        PetHome.show(petType);
    }
}
}