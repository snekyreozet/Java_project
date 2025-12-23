import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrevPets {
    public static void show() {
        JFrame frame = new JFrame("Ваши питомцы");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Main.LB);

        JLabel Label = new JLabel("YourPets", SwingConstants.CENTER);
        Label.setBounds(0, 100, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33)); 

        JButton button1 = Main.Buttons.Button("First", 330, 250, 140, 40);
        JButton button2 = Main.Buttons.Button("Second", 330, 300, 140, 40);
        JButton button3 = Main.Buttons.Button("Last", 330, 350, 140, 40);
        JButton button4 = Main.Buttons.Button("Menu", 330, 400, 140, 40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showPetDetail("First Pet");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showPetDetail("Second Pet");
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showPetDetail("Third Pet");
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Menu.show();
            }
        });

        frame.add(Label);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.setVisible(true);
    }
    
    private static void showPetDetail(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Main.LB);

        JLabel messageLabel = new JLabel("There is no pet yet", SwingConstants.CENTER);
        messageLabel.setBounds(0, 100, 800, 100);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 72));
        messageLabel.setForeground(new Color(101, 67, 33)); 

        JButton backButton = Main.Buttons.Button("Back", 330, 400, 140, 40);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                PrevPets.show();
            }
        });

        frame.add(messageLabel);
        frame.add(backButton);
        frame.setVisible(true);
    }
}