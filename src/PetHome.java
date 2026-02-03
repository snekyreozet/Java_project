import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PetHome {
    private static Timer overlayTimer;
    private static JLabel sleepOverlay;
    private static boolean isSleeping = false;
    
    public static void show(String petType) {
        Main.petType = petType; 
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

        JLabel statusLabel = new JLabel(getOverallStatus(), SwingConstants.LEFT);
        statusLabel.setBounds(20, 20, 350, 40); 
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setForeground(new Color(0, 0, 0));

        JLabel hungerLabel = new JLabel("Голод: " + Main.hunger, SwingConstants.RIGHT);
        hungerLabel.setBounds(460, 45, 250, 30);
        hungerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        hungerLabel.setForeground(new Color(0, 0, 0));

        JLabel playLabel = new JLabel("Игра: " + Main.play, SwingConstants.RIGHT);
        playLabel.setBounds(460, 75, 250, 30);
        playLabel.setFont(new Font("Arial", Font.BOLD, 18));
        playLabel.setForeground(new Color(0, 0, 0));

        JLabel sleepLabel = new JLabel("Сон: " + Main.sleep, SwingConstants.RIGHT);
        sleepLabel.setBounds(460, 105, 250, 30);
        sleepLabel.setFont(new Font("Arial", Font.BOLD, 18));
        sleepLabel.setForeground(new Color(0, 0, 0));

        JButton feedButton = Main.Buttons.Button("Покормить", 190, 440, 120, 50);
        JButton playButton = Main.Buttons.Button("Играть", 340, 440, 120, 50);
        JButton sleepButton = Main.Buttons.Button("Спать", 490, 440, 120, 50);
        JButton saveButton = Main.Buttons.Button("Сохранить", 20, 440, 120, 50); 
        JButton menuButton = Main.Buttons.Button("Меню", 325, 505, 150, 50);

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
                if (!isSleeping) {
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
            }
        });
        movementTimer.start();

        JButton[] buttons = {feedButton, playButton, sleepButton, saveButton, menuButton, petButton};
        
        feedButton.addActionListener(e -> {
            if (!isSleeping && Main.hunger < 100) {
                Main.hunger += 10;
                if (Main.hunger > 100) Main.hunger = 100;
                hungerLabel.setText("Голод: " + Main.hunger);
                statusLabel.setText(getOverallStatus());
            } else if (Main.hunger >= 100) {
                showTemporaryMessage(frame, "Ваш питомец не хочет есть");
            }
        });

        playButton.addActionListener(e -> {
            if (!isSleeping && Main.play < 100) { 
                if (Main.hungerTimer != null && Main.hungerTimer.isRunning()) {
                    Main.hungerTimer.stop();
                }
                if (movementTimer != null && movementTimer.isRunning()) {
                    movementTimer.stop();
                }
                frame.setVisible(false);
                MiniGame miniGame = new MiniGame(frame, petType);
                miniGame.startGame();
            } else if (Main.play >= 100) {
                showTemporaryMessage(frame, "Ваш питомец не хочет играть");
            }
        });

        sleepButton.addActionListener(e -> {
            if (!isSleeping && Main.sleep < 100) { 
                Main.sleep += 100;
                if (Main.sleep > 100) Main.sleep = 100;
                sleepLabel.setText("Сон: " + Main.sleep);
                statusLabel.setText(getOverallStatus());
                
                showSleepOverlay(frame, petType);
                disableButtons(buttons);
                isSleeping = true;
                
                if (overlayTimer != null && overlayTimer.isRunning()) {
                    overlayTimer.stop();
                }
                overlayTimer = new Timer(10000, ev -> {
                    hideSleepOverlay(frame);
                    enableButtons(buttons); 
                    isSleeping = false;
                    overlayTimer.stop();
                });
                overlayTimer.setRepeats(false);
                overlayTimer.start();
            } else if (Main.sleep >= 100) {
                showTemporaryMessage(frame, "Ваш питомец не хочет спать");
            }
        });
        
        saveButton.addActionListener(e -> {
            if (!isSleeping) {
                Main.saveCurrentPet();
            }
        });

        menuButton.addActionListener(e -> {
            if (!isSleeping) { 
                Main.stopAllTimers();
                movementTimer.stop();
                if (overlayTimer != null && overlayTimer.isRunning()) {
                    overlayTimer.stop();
                }
                frame.dispose();
                Menu.show();
            }
        });
        Main.hungerTimer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.hunger > 0) {
                    Main.hunger -= 5;
                    if (Main.hunger < 0) Main.hunger = 0;
                    hungerLabel.setText("Голод: " + Main.hunger);
                    statusLabel.setText(getOverallStatus());
                }
            }
        });
        Main.hungerTimer.start();

        Main.playTimer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.play > 0) {
                    Main.play -= 5;
                    if (Main.play < 0) Main.play = 0;
                    playLabel.setText("Игра: " + Main.play);
                    statusLabel.setText(getOverallStatus());
                }
            }
        });
        Main.playTimer.start();

        Main.sleepTimer = new Timer(15000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.sleep > 0) {
                    Main.sleep -= 1;
                    if (Main.sleep < 0) Main.sleep = 0;
                    sleepLabel.setText("Сон: " + Main.sleep);
                    statusLabel.setText(getOverallStatus());
                }
            }
        });
        Main.sleepTimer.start();
        
        frame.setContentPane(backgroundPanel);
        frame.getContentPane().add(nameLabel);
        frame.getContentPane().add(statusLabel);
        frame.getContentPane().add(hungerLabel);
        frame.getContentPane().add(playLabel);
        frame.getContentPane().add(sleepLabel);
        frame.getContentPane().add(feedButton);
        frame.getContentPane().add(playButton);
        frame.getContentPane().add(sleepButton);
        frame.getContentPane().add(saveButton);
        frame.getContentPane().add(menuButton);
        frame.getContentPane().add(petButton);

        frame.setVisible(true);
    }
    private static String getOverallStatus() {
        int lowCount = 0;
        StringBuilder status = new StringBuilder();
        if (Main.hunger <= 50) {
            status.append("Голоден");
            lowCount++;
        }
        
        if (Main.play <= 50) {
            if (lowCount > 0) {
                status.append(" и хочет играть");
            } else {
                status.append("Хочет играть");
            }
            lowCount++;
        }
        
        if (Main.sleep <= 50) {
            if (lowCount > 0) {
                status.append(" и хочет спать");
            } else {
                status.append("Хочет спать");
            }
            lowCount++;
        }
        if (lowCount >= 3) {
            return "Питомец очень недоволен";
        }
        if (lowCount == 0) {
            return "Доволен";
        }
        if (lowCount == 1) {
            return status.toString();
        }
        return "Питомец " + status.toString();
    }
    private static void showTemporaryMessage(JFrame frame, String message) {
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setBounds(200, 200, 400, 50);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 24));
        messageLabel.setForeground(Color.RED);
        messageLabel.setBackground(new Color(255, 255, 255, 200));
        messageLabel.setOpaque(true);
        
        frame.getContentPane().add(messageLabel);
        frame.getContentPane().repaint();
        
        Timer messageTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().remove(messageLabel);
                frame.getContentPane().repaint();
            }
        });
        messageTimer.setRepeats(false);
        messageTimer.start();
    }
    private static void showSleepOverlay(JFrame frame, String petType) {
        ImageIcon sleepIcon;
        switch(petType) {
            case "cat":
                sleepIcon = new ImageIcon("src/sleepcat.png");
                break;
            case "dog":
                sleepIcon = new ImageIcon("src/sleepdog.png");
                break;
            case "rabbit":
                sleepIcon = new ImageIcon("src/sleeprabbit.png");
                break;
            default:
                sleepIcon = new ImageIcon("src/sleepcat.png");
        }
        sleepOverlay = new JLabel(sleepIcon);
        sleepOverlay.setBounds(0, 0, 800, 600);
        sleepOverlay.setName("sleepOverlay");
        
        frame.getContentPane().add(sleepOverlay);
        frame.getContentPane().setComponentZOrder(sleepOverlay, 0);
        frame.getContentPane().repaint();
    }
    private static void hideSleepOverlay(JFrame frame) {
        if (sleepOverlay != null) {
            frame.getContentPane().remove(sleepOverlay);
            frame.getContentPane().repaint();
            sleepOverlay = null;
        }
    }
    private static void disableButtons(JButton[] buttons) {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
    private static void enableButtons(JButton[] buttons) {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
    }
}