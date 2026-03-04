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

        JLabel Label = new JLabel("Настройки", SwingConstants.CENTER);
        Label.setBounds(0, 100, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33));

        JButton button1 = Main.Buttons.Button("Цвет фона", 330, 250, 140, 40);
        JButton button2 = Main.Buttons.Button("Меню", 330, 300, 140, 40);
        JButton button3 = Main.Buttons.Button("Выход", 330, 350, 140, 40);

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

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.currentAnimal != null) {
                    Main.currentAnimal.stopAllTimers();
                }
                System.exit(0);
            }
        });

        frame.add(Label);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.setVisible(true);
    }
}