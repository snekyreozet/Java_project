import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PrevPets {
    public static void show() {
        Main.loadSavedPets();
        List<PetSave> savedPets = Main.getSavedPets();
        
        JFrame frame = new JFrame("Ваши питомцы");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Main.LB);

        JLabel titleLabel = new JLabel("Ваши питомцы", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(new Color(101, 67, 33));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel petsPanel = new JPanel();
        petsPanel.setLayout(new BoxLayout(petsPanel, BoxLayout.Y_AXIS));
        petsPanel.setBackground(Main.LB);
        petsPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        if (savedPets.isEmpty()) {
            JLabel emptyLabel = new JLabel("Нет сохраненных питомцев", SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            emptyLabel.setForeground(Color.GRAY);
            petsPanel.add(emptyLabel);
        } else {
            for (int i = 0; i < savedPets.size(); i++) {
                PetSave pet = savedPets.get(i);
                JPanel petPanel = createPetPanel(pet, i, frame);
                petsPanel.add(petPanel);
                petsPanel.add(Box.createVerticalStrut(10));
            }
        }

        JScrollPane scrollPane = new JScrollPane(petsPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Main.LB);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        
        JButton backButton = Main.Buttons.Button("Назад в меню", 0, 0, 200, 50);
        backButton.addActionListener(e -> {
            frame.dispose();
            Menu.show();
        });
        
        buttonPanel.add(backButton);

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    
    private static JPanel createPetPanel(PetSave pet, int index, JFrame parentFrame) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(new Color(240, 230, 210));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(101, 67, 33), 2),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        panel.setMaximumSize(new Dimension(700, 80));

        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 0, 5));
        infoPanel.setBackground(new Color(240, 230, 210));
        
        JLabel nameLabel = new JLabel(pet.name + " (" + getPetTypeName(pet.type) + ")");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel statsLabel = new JLabel("Голод: " + pet.hunger + "  Игра: " + pet.play + "  Сон: " + pet.sleep);
        statsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel dateLabel = new JLabel("Сохранен: " + pet.saveDate);
        dateLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        dateLabel.setForeground(Color.GRAY);
        
        infoPanel.add(nameLabel);
        infoPanel.add(statsLabel);
        infoPanel.add(dateLabel);
        panel.add(infoPanel, BorderLayout.CENTER);

        JButton loadButton = Main.Buttons.Button("Загрузить", 0, 0, 100, 40);
        loadButton.addActionListener(e -> {
            Main.loadPet(index);
            parentFrame.dispose();
            PetHome.show(pet.type);
        });
        
        panel.add(loadButton, BorderLayout.EAST);

        return panel;
    }
    
    private static String getPetTypeName(String type) {
        switch(type) {
            case "cat": return "Кот";
            case "dog": return "Собака";
            case "rabbit": return "Кролик";
            default: return type;
        }
    }
}