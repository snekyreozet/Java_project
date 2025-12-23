import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorSettings {
    public static void show() {
        JFrame frame = new JFrame("Изменение цвета");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Main.LB);

        JLabel Label = new JLabel("Settings", SwingConstants.CENTER);
        Label.setBounds(0, 100, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33));

        JButton button1 = Main.Buttons.Button("default", 330, 250, 140, 40);
        JButton button2 = Main.Buttons.Button("dark", 330, 300, 140, 40);
        JButton button3 = Main.Buttons.Button("menu", 330, 350, 140, 40);
        JButton button4 = Main.Buttons.Button("exit", 330, 400, 140, 40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.LB = new Color(210, 180, 140);
                frame.getContentPane().setBackground(Main.LB);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.LB = new Color(39,27,16);
                frame.getContentPane().setBackground(Main.LB);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Menu.show();
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