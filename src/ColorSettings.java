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

        JLabel Label = new JLabel("Цвет фона", SwingConstants.CENTER);
        Label.setBounds(0, 100, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33));

        JPanel previewPanel = new JPanel();
        previewPanel.setBounds(300, 200, 200, 80);
        previewPanel.setBackground(Main.LB);
        previewPanel.setBorder(BorderFactory.createLineBorder(new Color(101, 67, 33), 3));
        
        JLabel previewLabel = new JLabel("Предпросмотр", SwingConstants.CENTER);
        previewLabel.setFont(new Font("Arial", Font.BOLD, 16));
        previewLabel.setForeground(new Color(101, 67, 33));
        previewPanel.add(previewLabel);

        JButton button1 = Main.Buttons.Button("Светлый", 280, 300, 100, 40);
        JButton button2 = Main.Buttons.Button("Темный", 400, 300, 100, 40);
        JButton button3 = Main.Buttons.Button("Назад", 340, 400, 100, 40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.LB = new Color(210, 180, 140);
                frame.getContentPane().setBackground(Main.LB);
                previewPanel.setBackground(Main.LB);
                previewPanel.repaint();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.LB = new Color(39, 27, 16);
                frame.getContentPane().setBackground(Main.LB);
                previewPanel.setBackground(Main.LB);
                previewLabel.setForeground(Color.WHITE);
                previewPanel.repaint();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Settings.show();
            }
        });

        frame.add(Label);
        frame.add(previewPanel);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.setVisible(true);
    }
}