import java.text.SimpleDateFormat;
import java.util.Date;

public class PetSave {
    public String name;
        public String type;
        public int hunger;
        public int play;
        public int sleep;
        public String saveDate;
        
        public PetSave(String name, String type, int hunger, int play, int sleep) {
            this.name = name;
            this.type = type;
            this.hunger = hunger;
            this.play = play;
            this.sleep = sleep;
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            this.saveDate = sdf.format(new Date());
        }
        
        @Override
        public String toString() {
            return name + " (" + type + ") - " + saveDate;
        }
}
