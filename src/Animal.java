import java.io.Serializable;
import javax.swing.Timer;
import java.time.LocalDateTime;
import java.time.Duration;

public class Animal implements Serializable {
    private String name;
    private String type;
    private int hunger;
    private int play;
    private int sleep;
    private int petX = 60;
    private boolean movingRight = true;
    
    private Timer hungerTimer;
    private Timer playTimer;
    private Timer sleepTimer;
    
    private LocalDateTime lastSaveTime; 
    private LocalDateTime lastUpdateTime; 
    
    public Animal(String name, String type) {
        this.name = name;
        this.type = type;
        this.hunger = 50;
        this.play = 50;
        this.sleep = 50;
        this.lastSaveTime = LocalDateTime.now();
        this.lastUpdateTime = LocalDateTime.now();
    }
    
    public Animal(String name, String type, int hunger, int play, int sleep) {
        this.name = name;
        this.type = type;
        this.hunger = hunger;
        this.play = play;
        this.sleep = sleep;
        this.lastSaveTime = LocalDateTime.now();
        this.lastUpdateTime = LocalDateTime.now();
    }
    public void updateStatsByRealTime() {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(lastUpdateTime, now);
        long hoursPassed = duration.toHours();
        
        if (hoursPassed > 0) {
            int hungerDecrease = (int)(hoursPassed * 15);
            decreaseHunger(hungerDecrease);
            int playDecrease = (int)((hoursPassed / 6) * 20);
            decreasePlay(playDecrease);
            int sleepDecrease = (int)((hoursPassed / 12) * 50);
            decreaseSleep(sleepDecrease);
        }
        
        lastUpdateTime = now;
    }
    public void checkAndUpdateOnLoad() {
        updateStatsByRealTime();
    }
    
    public void autoSave() {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(lastSaveTime, now);
        if (duration.toHours() >= 1) {
            Main.saveCurrentPet();
            lastSaveTime = now;
            System.out.println("Автосохранение выполнено в " + now);
        }
    }
    
    public LocalDateTime getLastUpdateTime() { return lastUpdateTime; }
    public void setLastUpdateTime(LocalDateTime time) { this.lastUpdateTime = time; }
    
    public LocalDateTime getLastSaveTime() { return lastSaveTime; }
    public void setLastSaveTime(LocalDateTime time) { this.lastSaveTime = time; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public int getHunger() { return hunger; }
    public void setHunger(int hunger) { this.hunger = Math.max(0, Math.min(100, hunger)); }
    public void increaseHunger(int amount) { setHunger(hunger + amount); }
    public void decreaseHunger(int amount) { setHunger(hunger - amount); }
    
    public int getPlay() { return play; }
    public void setPlay(int play) { this.play = Math.max(0, Math.min(100, play)); }
    public void increasePlay(int amount) { setPlay(play + amount); }
    public void decreasePlay(int amount) { setPlay(play - amount); }
    
    public int getSleep() { return sleep; }
    public void setSleep(int sleep) { this.sleep = Math.max(0, Math.min(100, sleep)); }
    public void increaseSleep(int amount) { setSleep(sleep + amount); }
    public void decreaseSleep(int amount) { setSleep(sleep - amount); }
    
    public int getPetX() { return petX; }
    public void setPetX(int petX) { this.petX = petX; }
    
    public boolean isMovingRight() { return movingRight; }
    public void setMovingRight(boolean movingRight) { this.movingRight = movingRight; }
    
    public Timer getHungerTimer() { return hungerTimer; }
    public void setHungerTimer(Timer timer) { this.hungerTimer = timer; }
    
    public Timer getPlayTimer() { return playTimer; }
    public void setPlayTimer(Timer timer) { this.playTimer = timer; }
    
    public Timer getSleepTimer() { return sleepTimer; }
    public void setSleepTimer(Timer timer) { this.sleepTimer = timer; }
    
    public void stopAllTimers() {
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
    
    public String getOverallStatus() {
        int lowCount = 0;
        StringBuilder status = new StringBuilder();
        
        if (hunger <= 50) {
            status.append("Голоден");
            lowCount++;
        }
        
        if (play <= 50) {
            if (lowCount > 0) {
                status.append(" и хочет играть");
            } else {
                status.append("Хочет играть");
            }
            lowCount++;
        }
        
        if (sleep <= 50) {
            if (lowCount > 0) {
                status.append(" и хочет спать");
            } else {
                status.append("Хочет спать");
            }
            lowCount++;
        }
        
        if (lowCount >= 3) {
            return "Питомец очень недоволен";
        }
        if (lowCount == 0) {
            return "Доволен";
        }
        if (lowCount == 1) {
            return status.toString();
        }
        return "Питомец " + status.toString();
    }
    
    public void movePet() {
        if (movingRight) {
            petX += 2;
            if (petX >= 60 + 120) {
                movingRight = false;
            }
        } else {
            petX -= 2;
            if (petX <= 60) {
                movingRight = true;
            }
        }
    }
    
    public PetSave createSave() {
        return new PetSave(name, type, hunger, play, sleep, lastUpdateTime, lastSaveTime);
    }
    
    @Override
    public String toString() {
        return name + " (" + type + ") - Г:" + hunger + " И:" + play + " С:" + sleep;
    }
}