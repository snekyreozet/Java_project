import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetSave {
    public String name;
    public String type;
    public int hunger;
    public int play;
    public int sleep;
    public String saveDate;
    public String lastUpdateTime;
    public String lastSaveTime;
    
    public PetSave(String name, String type, int hunger, int play, int sleep) {
        this.name = name;
        this.type = type;
        this.hunger = hunger;
        this.play = play;
        this.sleep = sleep;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        this.saveDate = sdf.format(new Date());
        this.lastUpdateTime = saveDate;
        this.lastSaveTime = saveDate;
    }
    
    public PetSave(String name, String type, int hunger, int play, int sleep, 
                   LocalDateTime lastUpdate, LocalDateTime lastSave) {
        this.name = name;
        this.type = type;
        this.hunger = hunger;
        this.play = play;
        this.sleep = sleep;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        this.saveDate = formatter.format(LocalDateTime.now());
        this.lastUpdateTime = formatter.format(lastUpdate);
        this.lastSaveTime = formatter.format(lastSave);
    }
    
    @Override
    public String toString() {
        return name + " (" + type + ") - " + saveDate;
    }
}