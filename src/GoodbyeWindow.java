import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GoodbyeWindow {
    
    public static void show(String petName) {
        JFrame frame = new JFrame("Прощание...");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        
        Color darkBrown = new Color(39, 27, 16);
        frame.getContentPane().setBackground(darkBrown);

        JLabel titleLabel = new JLabel("Письмо от " + petName, SwingConstants.CENTER);
        titleLabel.setBounds(0, 80, 800, 60);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);

        JTextArea letterText = new JTextArea();
        letterText.setText("Привет, хозяин. Это я, " + petName + ".\n\n" +
                          "Пишу тебе с соседней улицы. Я очень замерз и проголодался, \n" +
                          "но возвращаться пока не решаюсь. Я ушел, потому что ты забыл про меня: \n" +
                          "миска пустая, игрушки пылятся, а спать меня никто не укладывал. \n" +
                          "Я чувствовал себя совсем одиноким. Поэтому я и ушел искать того, \n" +
                          "кому я нужен.\n\n" +
                          "Но я никого не нашел лучше тебя. Если ты меня еще помнишь — \n" +
                          "положи в мою миску побольше еды и не убирай ее. \n" +
                          "Я приду, как только увижу это. Я хочу домой.");
        
        letterText.setBounds(100, 160, 600, 280);
        letterText.setFont(new Font("Arial", Font.PLAIN, 16));
        letterText.setForeground(Color.WHITE);
        letterText.setBackground(darkBrown);
        letterText.setEditable(false);
        letterText.setWrapStyleWord(true);
        letterText.setLineWrap(true);
        letterText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton feedButton = Main.Buttons.Button("Положить еду", 300, 470, 200, 50);

        feedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.currentAnimal != null) {
                    Main.currentAnimal.setHunger(100);
                    Main.saveCurrentPet();
                }

                frame.dispose();
                if (Main.currentAnimal != null) {
                    PetHome.show(Main.currentAnimal.getType());
                }
            }
        });
        frame.add(titleLabel);
        frame.add(letterText);
        frame.add(feedButton);

        frame.setVisible(true);
    }
}