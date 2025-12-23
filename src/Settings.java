import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Settings {
    public static void show() {
        JFrame frame = new JFrame("Настройки");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Main.LB);

        JLabel Label = new JLabel("Settings", SwingConstants.CENTER);
        Label.setBounds(0, 100, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33));

        JButton button1 = Main.Buttons.Button("Change color", 330, 250, 140, 40);
        JButton button2 = Main.Buttons.Button("Back", 330, 300, 140, 40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ColorSettings.show();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Menu.show();
            }
        });

        frame.add(Label);
        frame.add(button1);
        frame.add(button2);
        frame.setVisible(true);
    }
}