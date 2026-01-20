import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PetHome {
    public static void show(String petType) {
        JFrame frame = new JFrame(Main.petname);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(Main.LB);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("src/homebg.png").getImage();
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, 800, 600, this);
                }
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 800, 600);

        JLabel nameLabel = new JLabel(Main.petname, SwingConstants.LEFT);
        nameLabel.setBounds(550, 10, 300, 60);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 32));
        nameLabel.setForeground(new Color(0, 0, 0));

        JLabel statusLabel = new JLabel(Main.getHappinessStatus(), SwingConstants.CENTER);
        statusLabel.setBounds(518, 90, 200, 40);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setForeground(new Color(0, 0, 0));

        JLabel happinessLabel = new JLabel("Happiness: " + Main.happiness, SwingConstants.RIGHT);
        happinessLabel.setBounds(460, 45, 250, 60);
        happinessLabel.setFont(new Font("Arial", Font.BOLD, 24));
        happinessLabel.setForeground(new Color(0, 0, 0));

        JButton feedButton = Main.Buttons.Button("Feed", 190, 440, 120, 50);
        JButton playButton = Main.Buttons.Button("Play", 340, 440, 120, 50);
        JButton sleepButton = Main.Buttons.Button("Sleep", 490, 440, 120, 50);
        JButton menuButton = Main.Buttons.Button("Menu", 325, 505, 150, 50);

        ImageIcon petIcon;
        switch(petType) {
            case "cat":
                petIcon = new ImageIcon("src/cat.png");
                break;
            case "dog":
                petIcon = new ImageIcon("src/dog.png");
                break;
            case "rabbit":
                petIcon = new ImageIcon("src/rabbit.png");
                break;
            default:
                petIcon = new ImageIcon("src/cat.png");
        }

        JButton petButton = Main.Buttons.ImgButton(petIcon, 210, 80, 400, 400);
        
        Timer movementTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.movingRight) {
                    Main.petX += 2;
                    if (Main.petX >= 60 + 120) {
                        Main.movingRight = false;
                    }
                } 
                else {
                    Main.petX -= 2;
                    if (Main.petX <= 60) {
                        Main.movingRight = true;
                    }
                }
                petButton.setBounds(Main.petX, 80, 400, 400);
                petButton.repaint();
            }
        });
        movementTimer.start();

        feedButton.addActionListener(e -> {
            if (Main.happiness < 100) {
                Main.happiness += 10;
                if (Main.happiness > 100) {
                    Main.happiness = 100;
                }
                happinessLabel.setText("Happiness: " + Main.happiness);
                statusLabel.setText(Main.getHappinessStatus());
            }
        });

        playButton.addActionListener(e -> {
            if (Main.happiness < 100) {
                if (Main.happinessTimer != null && Main.happinessTimer.isRunning()) {
                    Main.happinessTimer.stop();
                }
                if (movementTimer != null && movementTimer.isRunning()) {
                    movementTimer.stop();
                }
                frame.setVisible(false);
                MiniGame miniGame = new MiniGame(frame, petType);
                miniGame.startGame();
            }
        });

        sleepButton.addActionListener(e -> {
            if (Main.happiness < 100) {
                Main.happiness += 5;
                if (Main.happiness > 100) {
                    Main.happiness = 100;
                }
                happinessLabel.setText("Happiness: " + Main.happiness);
                statusLabel.setText(Main.getHappinessStatus());
            }
        });

        menuButton.addActionListener(e -> {
            Main.stopAllTimers();
            movementTimer.stop();
            frame.dispose();
            Menu.show();
        });

        Main.happinessTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.happiness > 0) {
                    Main.happiness -= 5;
                    if (Main.happiness < 0) {
                        Main.happiness = 0;
                    }
                    happinessLabel.setText("Happiness: " + Main.happiness);
                    statusLabel.setText(Main.getHappinessStatus());
                }
            }
        });
        Main.happinessTimer.start();
        
        frame.setContentPane(backgroundPanel);
        frame.getContentPane().add(nameLabel);
        frame.getContentPane().add(statusLabel);
        frame.getContentPane().add(happinessLabel);
        frame.getContentPane().add(feedButton);
        frame.getContentPane().add(playButton);
        frame.getContentPane().add(sleepButton);
        frame.getContentPane().add(menuButton);
        frame.getContentPane().add(petButton);

        frame.setVisible(true);
    }
}