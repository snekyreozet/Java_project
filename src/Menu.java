import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu {
    public static void show() {
        JFrame frame = new JFrame("Меню");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        frame.getContentPane().setBackground(Main.LB);

        JLabel Label = new JLabel("MyPet", SwingConstants.CENTER);
        Label.setBounds(0, 100, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33)); 

        JButton button1 = Main.Buttons.Button("Start", 330, 250, 140, 40);
        JButton button2 = Main.Buttons.Button("Prev pets", 330, 300, 140, 40);
        JButton button3 = Main.Buttons.Button("Settings", 330, 350, 140, 40);
        JButton button4 = Main.Buttons.Button("Exit", 330, 400, 140, 40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                YourPet.show();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                PrevPets.show();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Settings.show();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.stopAllTimers();
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
}