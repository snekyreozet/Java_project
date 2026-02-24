import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PetHome {
    private static Timer overlayTimer;
    private static JLabel sleepOverlay;
    private static boolean isSleeping = false;
    private static Timer gameAutoSaveTimer;
    
    public static void show(String petType) {
        if (Main.currentAnimal == null) {
            Main.currentAnimal = new Animal("", petType);
        }
        
        Animal animal = Main.currentAnimal;
        animal.setType(petType);
        animal.checkAndUpdateOnLoad();
        
        JFrame frame = new JFrame(animal.getName());
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

        JLabel nameLabel = new JLabel(animal.getName(), SwingConstants.LEFT);
        nameLabel.setBounds(550, 10, 300, 60);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 32));
        nameLabel.setForeground(new Color(0, 0, 0));

        JLabel statusLabel = new JLabel(animal.getOverallStatus(), SwingConstants.LEFT);
        statusLabel.setBounds(20, 20, 350, 40);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setForeground(new Color(0, 0, 0));

        JLabel hungerLabel = new JLabel("Голод: " + animal.getHunger(), SwingConstants.RIGHT);
        hungerLabel.setBounds(460, 45, 250, 30);
        hungerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        hungerLabel.setForeground(new Color(0, 0, 0));

        JLabel playLabel = new JLabel("Игра: " + animal.getPlay(), SwingConstants.RIGHT);
        playLabel.setBounds(460, 75, 250, 30);
        playLabel.setFont(new Font("Arial", Font.BOLD, 18));
        playLabel.setForeground(new Color(0, 0, 0));

        JLabel sleepLabel = new JLabel("Сон: " + animal.getSleep(), SwingConstants.RIGHT);
        sleepLabel.setBounds(460, 105, 250, 30);
        sleepLabel.setFont(new Font("Arial", Font.BOLD, 18));
        sleepLabel.setForeground(new Color(0, 0, 0));

        JButton feedButton = Main.Buttons.Button("Покормить", 220, 440, 120, 50);
        JButton playButton = Main.Buttons.Button("Играть", 370, 440, 120, 50);
        JButton sleepButton = Main.Buttons.Button("Спать", 520, 440, 120, 50);
        JButton menuButton = Main.Buttons.Button("Меню", 325, 505, 150, 50);

        ImageIcon petIcon;
        switch(petType) {
            case "cat":
                petIcon = new ImageIcon("src/cat.png");
                break;
            case "dog":
                petIcon = new ImageIcon("src/dog.jpg");
                break;
            case "rabbit":
                petIcon = new ImageIcon("src/rabbit.jpg");
                break;
            default:
                petIcon = new ImageIcon("src/cat.png");
        }

        JButton petButton = Main.Buttons.ImgButton(petIcon, animal.getPetX(), 80, 400, 400);
        
        Timer movementTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSleeping) {
                    animal.movePet();
                    petButton.setBounds(animal.getPetX(), 80, 400, 400);
                    petButton.repaint();
                }
            }
        });
        movementTimer.start();

        JButton[] buttons = {feedButton, playButton, sleepButton, menuButton, petButton};
        
        feedButton.addActionListener(e -> {
    if (!isSleeping) {
        if (animal.getHungerTimer() != null && animal.getHungerTimer().isRunning()) {
            animal.getHungerTimer().stop();
        }
        if (movementTimer != null && movementTimer.isRunning()) {
            movementTimer.stop();
        }
        if (gameAutoSaveTimer != null && gameAutoSaveTimer.isRunning()) {
            gameAutoSaveTimer.stop();
        }
        Main.saveCurrentPet();
        frame.dispose();
        Kitchen.show(petType);
    }
});

        playButton.addActionListener(e -> {
            if (!isSleeping && animal.getPlay() < 100) {
                if (animal.getHungerTimer() != null && animal.getHungerTimer().isRunning()) {
                    animal.getHungerTimer().stop();
                }
                if (movementTimer != null && movementTimer.isRunning()) {
                    movementTimer.stop();
                }
                if (gameAutoSaveTimer != null && gameAutoSaveTimer.isRunning()) {
                    gameAutoSaveTimer.stop();
                }
                frame.setVisible(false);
                MiniGame miniGame = new MiniGame(frame, petType);
                miniGame.startGame();
            } else if (animal.getPlay() >= 100) {
                showTemporaryMessage(frame, "Ваш питомец не хочет играть");
            }
        });

        sleepButton.addActionListener(e -> {
            if (!isSleeping && animal.getSleep() < 100) {
                animal.setSleep(animal.getSleep()+50);
                sleepLabel.setText("Сон: " + animal.getSleep());
                statusLabel.setText(animal.getOverallStatus());
                
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
            } else if (animal.getSleep() >= 100) {
                showTemporaryMessage(frame, "Ваш питомец не хочет спать");
            }
        });

        menuButton.addActionListener(e -> {
            if (!isSleeping) {
                animal.stopAllTimers();
                movementTimer.stop();
                if (overlayTimer != null && overlayTimer.isRunning()) {
                    overlayTimer.stop();
                }
                if (gameAutoSaveTimer != null && gameAutoSaveTimer.isRunning()) {
                    gameAutoSaveTimer.stop();
                }
                Main.saveCurrentPet();
                frame.dispose();
                Menu.show();
            }
        });

        Timer hungerTimer = new Timer(3600000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSleeping && animal.getHunger() > 0) {
                    animal.decreaseHunger(5);
                    hungerLabel.setText("Голод: " + animal.getHunger());
                    statusLabel.setText(animal.getOverallStatus());
                }
            }
        });
        animal.setHungerTimer(hungerTimer);
        animal.getHungerTimer().start();

        Timer playTimer = new Timer(3600000*6, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSleeping && animal.getPlay() > 0) {
                    animal.decreasePlay(5);
                    playLabel.setText("Игра: " + animal.getPlay());
                    statusLabel.setText(animal.getOverallStatus());
                }
            }
        });
        animal.setPlayTimer(playTimer);
        animal.getPlayTimer().start();

        Timer sleepTimer = new Timer(3600000*12, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSleeping && animal.getSleep() > 0) {
                    animal.decreaseSleep(1);
                    sleepLabel.setText("Сон: " + animal.getSleep());
                    statusLabel.setText(animal.getOverallStatus());
                }
            }
        });
        animal.setSleepTimer(sleepTimer);
        animal.getSleepTimer().start();
        gameAutoSaveTimer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSleeping && Main.currentAnimal != null) {
                    Main.currentAnimal.autoSave();
                    System.out.println("Автосохранение проверено");
                }
            }
        });
        gameAutoSaveTimer.start();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (Main.currentAnimal != null) {
                    Main.saveCurrentPet();
                }
                animal.stopAllTimers();
                movementTimer.stop();
                if (gameAutoSaveTimer != null && gameAutoSaveTimer.isRunning()) {
                    gameAutoSaveTimer.stop();
                }
            }
        });
        
        frame.setContentPane(backgroundPanel);
        frame.getContentPane().add(nameLabel);
        frame.getContentPane().add(statusLabel);
        frame.getContentPane().add(hungerLabel);
        frame.getContentPane().add(playLabel);
        frame.getContentPane().add(sleepLabel);
        frame.getContentPane().add(feedButton);
        frame.getContentPane().add(playButton);
        frame.getContentPane().add(sleepButton);
        frame.getContentPane().add(menuButton);
        frame.getContentPane().add(petButton);

        frame.setVisible(true);
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
                sleepIcon = new ImageIcon("src/sleeprabbit.jpg");
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