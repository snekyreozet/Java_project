import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Main {
    private static Color LB = new Color(210, 180, 140);
    private static String petname = "";
    private static int happiness = 70;
    private static Timer happinessTimer;
    private static int petX = 60; 
    private static boolean movingRight = true;
    
    private static JButton Button(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(101, 67, 33));
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }
    
    private static JButton ImgButton(ImageIcon icon, int x, int y, int width, int height) {
        JButton button = new JButton();
        button.setIcon(icon);
        button.setBounds(x, y, width, height);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }
    
    private static String getHappinessStatus() {
        if (happiness >= 90) {
            return "Your pet is happy";
        } 
        else if (happiness >= 50) {
            return "Your pet is ok";
        } 
        else {
            return "Your pet is sad";
        }
    }
    
    public static void main(String[] args) {
        menu();
    }
    
    private static void menu() {
        JFrame frame = new JFrame("Меню");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        frame.getContentPane().setBackground(LB);

        JLabel Label = new JLabel("MyPet", SwingConstants.CENTER);
        Label.setBounds(0, 100, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33)); 

        JButton button1 = Button("Start", 330, 250, 140, 40);
        JButton button2 = Button("Prev pets", 330, 300, 140, 40);
        JButton button3 = Button("Settings", 330, 350, 140, 40);
        JButton button4 = Button("Exit", 330, 400, 140, 40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                yourpet();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                prevpets();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                settings();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (happinessTimer != null) {
                    happinessTimer.stop();
                }
                System.exit(0);
            }
        });

        frame.add(Label);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.setVisible(true);
    }

    private static void prevpets() {
        JFrame frame = new JFrame("Ваши питомцы");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        frame.getContentPane().setBackground(LB);

        JLabel Label = new JLabel("YourPets", SwingConstants.CENTER);
        Label.setBounds(0, 100, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33)); 

        JButton button1 = Button("First", 330, 250, 140, 40);
        JButton button2 = Button("Second", 330, 300, 140, 40);
        JButton button3 = Button("Last", 330, 350, 140, 40);
        JButton button4 = Button("Menu", 330, 400, 140, 40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                firstpet();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                secpet();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                thirdpet();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                menu();
            }
        });

        frame.add(Label);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.setVisible(true);
    }

    private static void firstpet() {
        JFrame frame = new JFrame("Первый питомец");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        frame.getContentPane().setBackground(LB);

        JLabel Label = new JLabel("There is no pet yet", SwingConstants.CENTER);
        Label.setBounds(0, 100, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33)); 

        JButton button4 = Button("Back", 330, 400, 140, 40);

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                prevpets();
            }
        });

        frame.add(Label);
        frame.add(button4);
        frame.setVisible(true);
    }

    private static void secpet() {
        JFrame frame = new JFrame("Второй питомец");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        frame.getContentPane().setBackground(LB);

        JLabel Label = new JLabel("There is no pet yet", SwingConstants.CENTER);
        Label.setBounds(0, 100, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33)); 

        JButton button4 = Button("Back", 330, 400, 140, 40);

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                prevpets();
            }
        });

        frame.add(Label);
        frame.add(button4);
        frame.setVisible(true);
    }
    private static void thirdpet() {
        JFrame frame = new JFrame("Третий питомец");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        frame.getContentPane().setBackground(LB);

        JLabel Label = new JLabel("There is no pet yet", SwingConstants.CENTER);
        Label.setBounds(0, 100, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33)); 

        JButton button4 = Button("Back", 330, 400, 140, 40);

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                prevpets();
            }
        });

        frame.add(Label);
        frame.add(button4);
        frame.setVisible(true);
    }

    private static void settings() {
        JFrame frame = new JFrame("Настройки");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        frame.getContentPane().setBackground(LB);

        JLabel Label = new JLabel("Settings", SwingConstants.CENTER);
        Label.setBounds(0, 100, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33));

        JButton button1 = Button("Change color", 330, 250, 140, 40);
        JButton button2 = Button("Back", 330, 300, 140, 40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                chcolor();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                menu();
            }
        });

        frame.add(Label);
        frame.add(button1);
        frame.add(button2);
        frame.setVisible(true);
    }
    private static void chcolor() {
        JFrame frame = new JFrame("Изменение цвета");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        frame.getContentPane().setBackground(LB);

        JLabel Label = new JLabel("Settings", SwingConstants.CENTER);
        Label.setBounds(0, 100, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33));

        JButton button1 = Button("default", 330, 250, 140, 40);
        JButton button2 = Button("green", 330, 300, 140, 40);
        JButton button3 = Button("menu", 330, 350, 140, 40);
        JButton button4 = Button("exit", 330, 400, 140, 40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LB = new Color(210, 180, 140);
                frame.getContentPane().setBackground(LB);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LB = new Color(189,236,182);
                frame.getContentPane().setBackground(LB);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                menu();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (happinessTimer != null) {
                    happinessTimer.stop();
                }
                System.exit(0);
            }
        });

        frame.add(Label);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.setVisible(true);

    }
    
    private static void yourpet() {
        ImageIcon catIcon = new ImageIcon("src/caticon.png");
        ImageIcon dogIcon = new ImageIcon("src/dogicon.png");
        ImageIcon rabbitIcon = new ImageIcon("src/rabbiticon.png");
        
        JFrame frame = new JFrame("питомец");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        
        frame.getContentPane().setBackground(LB);

        JLabel Label = new JLabel("Choose your pet!", SwingConstants.CENTER);
        Label.setBounds(0, 10, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33));

        JButton catButton = ImgButton(catIcon, 270, 150, 250, 250);
        JButton dogButton = ImgButton(dogIcon, 10, 150, 250, 250);
        JButton rabbitButton = ImgButton(rabbitIcon, 530, 150, 250, 250);

        catButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                name("cat");
            }
        });
        
        dogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                name("dog");
            }
        });
        
        rabbitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                name("rabbit");
            }
        });

        frame.add(Label);
        frame.add(catButton);
        frame.add(dogButton);
        frame.add(rabbitButton);
        frame.setVisible(true);
    }
    
    private static void name(String petType) {
        JFrame frame = new JFrame("Имя вашего питомца");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(LB);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel Label = new JLabel("Name your pet!", SwingConstants.CENTER);
        Label.setBounds(0, 30, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33));

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

        JButton petButton = ImgButton(petIcon, 200, 100, 400, 400);

        JTextField nameField = new JTextField("Введите имя");
        nameField.setBounds(300, 480, 200, 30);
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField.setHorizontalAlignment(JTextField.CENTER);
        

        JButton nextButton = Button("Next", 350, 520, 100, 30);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                petname = nameField.getText();
                frame.dispose();
                if (petType == "cat"){
                    home("cat");
                }
                if (petType == "dog"){
                    home("dog");
                }
                if (petType == "rabbit"){
                    home("rabbit");
                }
            }
        });

        frame.add(Label);
        frame.add(petButton);
        frame.add(nameField);
        frame.add(nextButton);
        frame.setVisible(true);
    }

    private static void home(String petType) {
        JFrame frame = new JFrame(petname);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(LB);
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

        JLabel nameLabel = new JLabel(petname, SwingConstants.LEFT);
        nameLabel.setBounds(550, 10, 300, 60);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 32));
        nameLabel.setForeground(new Color(0, 0, 0));

        JLabel statusLabel = new JLabel(getHappinessStatus(), SwingConstants.CENTER);
        statusLabel.setBounds(518, 90, 200, 40);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setForeground(new Color(0, 0, 0));

        JLabel happinessLabel = new JLabel("Happiness: " + happiness, SwingConstants.RIGHT);
        happinessLabel.setBounds(460, 45, 250, 60);
        happinessLabel.setFont(new Font("Arial", Font.BOLD, 24));
        happinessLabel.setForeground(new Color(0, 0, 0));

        JButton feedButton = Button("Feed", 190, 440, 120, 50);

        JButton playButton = Button("Play", 340, 440, 120, 50);

        JButton sleepButton = Button("Sleep", 490, 440, 120, 50);

        JButton menuButton = Button("Menu", 325, 505, 150, 50);

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

        JButton petButton = ImgButton(petIcon, 210, 80, 400, 400);
        
        Timer movementTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (movingRight) {
                    petX += 2;
                    if (petX >= 60 + 120) {
                        movingRight = false;
                    }
                } 
                else {
                    petX -= 2;
                    if (petX <= 60) {
                        movingRight = true;
                    }
                }
                petButton.setBounds(petX, 80, 400, 400);
                petButton.repaint();
            }
        });
        movementTimer.start();

        feedButton.addActionListener(e -> {
            if (happiness < 100) {
                happiness += 10;
                if (happiness > 100) {
                    happiness = 100;
                }
                happinessLabel.setText("Happiness: " + happiness);
                statusLabel.setText(getHappinessStatus());
            }
        });

        playButton.addActionListener(e -> {
            if (happiness < 100) {
                happiness += 15;
                if (happiness > 100) {
                    happiness = 100;
                }
                happinessLabel.setText("Happiness: " + happiness);
                statusLabel.setText(getHappinessStatus());
            }
        });

        sleepButton.addActionListener(e -> {
            if (happiness < 100) {
                happiness += 5;
                if (happiness > 100) {
                    happiness = 100;
                }
                happinessLabel.setText("Happiness: " + happiness);
                statusLabel.setText(getHappinessStatus());
            }
        });

        menuButton.addActionListener(e -> {
            if (happinessTimer != null) {
                happinessTimer.stop();
            }
            frame.dispose();
            menu();
        });

        happinessTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (happiness > 0) {
                    happiness -= 5;
                    if (happiness < 0) {
                        happiness = 0;
                    }
                    happinessLabel.setText("Happiness: " + happiness);
                    statusLabel.setText(getHappinessStatus());
                }
            }
        });
        happinessTimer.start();
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