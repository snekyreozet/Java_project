import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NamePet {
    public static void show(String petType) {
        JFrame frame = new JFrame("Имя вашего питомца");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(Main.LB);
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
                petIcon = new ImageIcon("src/dog.jpg");
                break;
            case "rabbit":
                petIcon = new ImageIcon("src/rabbit.jpg");
                break;
            default:
                petIcon = new ImageIcon("src/cat.png");
        }

        JButton petButton = Main.Buttons.ImgButton(petIcon, 200, 100, 400, 400);

        JTextField nameField = new JTextField("Введите имя");
        nameField.setBounds(300, 480, 200, 30);
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField.setHorizontalAlignment(JTextField.CENTER);
        
        JButton nextButton = Main.Buttons.Button("Next", 350, 520, 100, 30);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String petName = nameField.getText();
                Main.currentAnimal = new Animal(petName, petType);
                frame.dispose();
                PetHome.show(petType);
            }
        });

        frame.add(Label);
        frame.add(petButton);
        frame.add(nameField);
        frame.add(nextButton);
        frame.setVisible(true);
    }
}