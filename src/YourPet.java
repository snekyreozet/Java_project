import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class YourPet {
    public static void show() {
        JFrame frame = new JFrame("Выбор питомца");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Main.LB);

        JLabel Label = new JLabel("Выберите питомца", SwingConstants.CENTER);
        Label.setBounds(0, 30, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33));

        JButton catButton = Main.Buttons.ImgButton(new ImageIcon("src/cat.png"), 0, 130, 250, 250);
        JButton dogButton = Main.Buttons.ImgButton(new ImageIcon("src/dog.png"), 280, 130, 250, 250);
        JButton rabbitButton = Main.Buttons.ImgButton(new ImageIcon("src/rabbit.png"), 500, 130, 250, 250);

        JLabel catLabel = new JLabel("Кот", SwingConstants.CENTER);
        catLabel.setBounds(100, 380, 150, 30);
        catLabel.setFont(new Font("Arial", Font.BOLD, 24));
        catLabel.setForeground(new Color(101, 67, 33));

        JLabel dogLabel = new JLabel("Собака", SwingConstants.CENTER);
        dogLabel.setBounds(325, 380, 150, 30);
        dogLabel.setFont(new Font("Arial", Font.BOLD, 24));
        dogLabel.setForeground(new Color(101, 67, 33));

        JLabel rabbitLabel = new JLabel("Кролик", SwingConstants.CENTER);
        rabbitLabel.setBounds(550, 380, 150, 30);
        rabbitLabel.setFont(new Font("Arial", Font.BOLD, 24));
        rabbitLabel.setForeground(new Color(101, 67, 33));

        JButton backButton = Main.Buttons.Button("Назад", 330, 450, 140, 40);

        catButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                NamePet.show("cat");
            }
        });

        dogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                NamePet.show("dog");
            }
        });

        rabbitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                NamePet.show("rabbit");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Menu.show();
            }
        });

        frame.add(Label);
        frame.add(catButton);
        frame.add(dogButton);
        frame.add(rabbitButton);
        frame.add(catLabel);
        frame.add(dogLabel);
        frame.add(rabbitLabel);
        frame.add(backButton);
        frame.setVisible(true);
    }
}