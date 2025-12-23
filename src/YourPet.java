import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class YourPet {
    public static void show() {
        ImageIcon catIcon = new ImageIcon("src/caticon.png");
        ImageIcon dogIcon = new ImageIcon("src/dogicon.png");
        ImageIcon rabbitIcon = new ImageIcon("src/rabbiticon.png");
        
        JFrame frame = new JFrame("питомец");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        
        frame.getContentPane().setBackground(Main.LB);

        JLabel Label = new JLabel("Choose your pet!", SwingConstants.CENTER);
        Label.setBounds(0, 10, 800, 100);
        Label.setFont(new Font("Arial", Font.BOLD, 72));
        Label.setForeground(new Color(101, 67, 33));

        JButton catButton = Main.Buttons.ImgButton(catIcon, 270, 150, 250, 250);
        JButton dogButton = Main.Buttons.ImgButton(dogIcon, 10, 150, 250, 250);
        JButton rabbitButton = Main.Buttons.ImgButton(rabbitIcon, 530, 150, 250, 250);

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

        frame.add(Label);
        frame.add(catButton);
        frame.add(dogButton);
        frame.add(rabbitButton);
        frame.setVisible(true);
    }
}