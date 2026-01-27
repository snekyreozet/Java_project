import javax.swing.*;
import java.awt.*;

class Main {
    public static Color LB = new Color(210, 180, 140);
    public static String petname = "";
    public static int hunger = 50;
    public static int play = 50;
    public static int sleep = 50;
    
    public static Timer hungerTimer;
    public static Timer playTimer;
    public static Timer sleepTimer;
    public static int petX = 60; 
    public static boolean movingRight = true;
    
    public static void main(String[] args) {
        Menu.show();
    }
    
    public static void stopAllTimers() {
        if (hungerTimer != null && hungerTimer.isRunning()) {
            hungerTimer.stop();
        }
        if (playTimer != null && playTimer.isRunning()) {
            playTimer.stop();
        }
        if (sleepTimer != null && sleepTimer.isRunning()) {
            sleepTimer.stop();
        }
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
