import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Kitchen {
    private static boolean isEating = false;
    
    public static void show(String petType) {
        Animal animal = Main.currentAnimal;
        
        JFrame frame = new JFrame("Кухня");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("src/Kitchen_bg.png").getImage();
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, 800, 600, this);
                } else {
                    g.setColor(Main.LB);
                    g.fillRect(0, 0, 800, 600);
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
        statusLabel.setBounds(20, 20, 400, 40);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setForeground(new Color(0, 0, 0));

        JLabel hungerLabel = new JLabel("Голод: " + animal.getHunger(), SwingConstants.RIGHT);
        hungerLabel.setBounds(460, 45, 250, 30);
        hungerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        hungerLabel.setForeground(new Color(0, 0, 0));

        JButton fishButton = Main.Buttons.Button("Рыба", 200, 440, 120, 50);
        JButton meatButton = Main.Buttons.Button("Мясо", 350, 440, 120, 50);
        JButton carrotButton = Main.Buttons.Button("Морковь", 500, 440, 120, 50);
        JButton homeButton = Main.Buttons.Button("Домой", 350, 505, 120, 50);

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

        JLabel petLabel = new JLabel(petIcon);
        petLabel.setBounds(250, 150, 300, 300);
        petLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton[] buttons = {fishButton, meatButton, carrotButton, homeButton};

        fishButton.addActionListener(e -> {
            if (!isEating && animal.getHunger() < 100) {
                animal.increaseHunger(40);
                if (animal.getHunger() > 100) animal.setHunger(100);
                hungerLabel.setText("Голод: " + animal.getHunger());
                statusLabel.setText(animal.getOverallStatus());
                
                showEatingOverlay(frame, "рыбу");
                disableButtons(buttons);
                isEating = true;
                
                Timer eatTimer = new Timer(2000, ev -> {
                    hideEatingOverlay(frame);
                    enableButtons(buttons);
                    isEating = false;
                });
                eatTimer.setRepeats(false);
                eatTimer.start();
            } else if (animal.getHunger() >= 100) {
                showTemporaryMessage(frame, "Питомец уже сыт!");
            }
        });

        meatButton.addActionListener(e -> {
            if (!isEating && animal.getHunger() < 100) {
                animal.increaseHunger(50);
                if (animal.getHunger() > 100) animal.setHunger(100);
                hungerLabel.setText("Голод: " + animal.getHunger());
                statusLabel.setText(animal.getOverallStatus());
                
                showEatingOverlay(frame, "мясо");
                disableButtons(buttons);
                isEating = true;
                
                Timer eatTimer = new Timer(2000, ev -> {
                    hideEatingOverlay(frame);
                    enableButtons(buttons);
                    isEating = false;
                });
                eatTimer.setRepeats(false);
                eatTimer.start();
            } else if (animal.getHunger() >= 100) {
                showTemporaryMessage(frame, "Питомец уже сыт!");
            }
        });

        carrotButton.addActionListener(e -> {
            if (!isEating && animal.getHunger() < 100) {
                animal.increaseHunger(30);
                if (animal.getHunger() > 100) animal.setHunger(100);
                hungerLabel.setText("Голод: " + animal.getHunger());
                statusLabel.setText(animal.getOverallStatus());
                
                showEatingOverlay(frame, "морковь");
                disableButtons(buttons);
                isEating = true;
                
                Timer eatTimer = new Timer(2000, ev -> {
                    hideEatingOverlay(frame);
                    enableButtons(buttons);
                    isEating = false;
                });
                eatTimer.setRepeats(false);
                eatTimer.start();
            } else if (animal.getHunger() >= 100) {
                showTemporaryMessage(frame, "Питомец уже сыт!");
            }
        });

        homeButton.addActionListener(e -> {
            if (!isEating) {
                frame.dispose();
                PetHome.show(petType);
            }
        });

        frame.setContentPane(backgroundPanel);
        backgroundPanel.add(nameLabel);
        backgroundPanel.add(statusLabel);
        backgroundPanel.add(hungerLabel);
        backgroundPanel.add(fishButton);
        backgroundPanel.add(meatButton);
        backgroundPanel.add(carrotButton);
        backgroundPanel.add(homeButton);
        backgroundPanel.add(petLabel);

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

    private static void showEatingOverlay(JFrame frame, String food) {
        JPanel eatPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRect(0, 0, 800, 600);
                
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 48));
                String eatText = "Спасибо!";
                int textWidth = g.getFontMetrics().stringWidth(eatText);
                g.drawString(eatText, (800 - textWidth) / 2, 300);
                
                g.setFont(new Font("Arial", Font.PLAIN, 24));
                String foodText = "Питомец ест " + food;
                textWidth = g.getFontMetrics().stringWidth(foodText);
                g.drawString(foodText, (800 - textWidth) / 2, 370);
            }
        };
        eatPanel.setBounds(0, 0, 800, 600);
        eatPanel.setOpaque(false);
        eatPanel.setName("eatOverlay");
        
        frame.getContentPane().add(eatPanel);
        frame.getContentPane().setComponentZOrder(eatPanel, 0);
        frame.getContentPane().repaint();
        
    }

    private static void hideEatingOverlay(JFrame frame) {
        Component[] components = frame.getContentPane().getComponents();
        for (Component comp : components) {
            if (comp.getName() != null && comp.getName().equals("eatOverlay")) {
                frame.getContentPane().remove(comp);
                break;
            }
        }
        frame.getContentPane().repaint();
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