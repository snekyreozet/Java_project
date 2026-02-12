import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

class Main {
    public static Color LB = new Color(210, 180, 140);
    public static Animal currentAnimal = null;
    private static final String SAVE_FILE = "pet_saves.txt";
    private static final List<PetSave> savedPets = new ArrayList<>();
    
    public static void main(String[] args) {
        loadSavedPets();
        Menu.show();
    }
    
    public static void saveCurrentPet() {
        if (currentAnimal != null) {
            PetSave currentPet = currentAnimal.createSave();
            savedPets.add(currentPet);
            
            try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE, true))) {
                writer.println(currentAnimal.getName() + "|" + currentAnimal.getType() + "|" + currentAnimal.getHunger() + "|" + currentAnimal.getPlay() + "|" + currentAnimal.getSleep() + "|" + currentPet.saveDate);
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
    
    public static void loadSavedPets() {
        savedPets.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    String name = parts[0];
                    String type = parts[1];
                    int hunger = Integer.parseInt(parts[2]);
                    int play = Integer.parseInt(parts[3]);
                    int sleep = Integer.parseInt(parts[4]);
                    String date = parts[5];
                    PetSave pet = new PetSave(name, type, hunger, play, sleep);
                    pet.saveDate = date;
                    savedPets.add(pet);
                }
            }
        } catch (IOException e) { 
            e.getMessage();
        } catch (NumberFormatException e) {
        }
    }
    
    public static List<PetSave> getSavedPets() {
        return new ArrayList<>(savedPets);
    }
    
    public static boolean loadPet(int index) {
        if (index >= 0 && index < savedPets.size()) {
            PetSave pet = savedPets.get(index);
            currentAnimal = new Animal(pet.name, pet.type, pet.hunger, pet.play, pet.sleep);
            return true;
        }
        return false;
    }
    
    public static class Buttons {
        public static JButton Button(String text, int x, int y, int width, int height) {
            JButton button = new JButton(text);
            button.setBounds(x, y, width, height);
            button.setBackground(new Color(101, 67, 33));
            button.setForeground(Color.WHITE);
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            return button;
        }
        
        public static JButton ImgButton(ImageIcon icon, int x, int y, int width, int height) {
            JButton button = new JButton();
            button.setIcon(icon);
            button.setBounds(x, y, width, height);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            return button;
        }
    }
}