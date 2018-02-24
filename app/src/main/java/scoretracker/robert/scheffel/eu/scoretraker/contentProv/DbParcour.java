package scoretracker.robert.scheffel.eu.scoretraker.contentProv;

public class DbParcour {

    private int id;
    private String name;
    private int targetCount;
    private String lenght;
    private String timeToFinish;
    private int lastChosen;



    public DbParcour(int id, String name, int targetCount, String lenght, String timeToFinish, int lastChosen) {
        this.id = id;
        this.name = name;
        this.targetCount = targetCount;
        this.lenght = lenght;
        this.timeToFinish = timeToFinish;
        this.lastChosen = lastChosen != 1 && lastChosen != 0 ? 0 : lastChosen;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Name of the Parcour
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTargetCount() {
        return targetCount;
    }

    public void setTargetCount(int targetCount) {
        this.targetCount = targetCount;
    }

    /**
     * @return Parcourlength
     */
    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }

    public String getTimeToFinish() {
        return timeToFinish;
    }

    public void setTimeToFinish(String timeToFinish) {
        this.timeToFinish = timeToFinish;
    }

    /**
     * @return 1 if Parcour was the last chosen; 0 otherwise
     */
    public int getLastChosen() {
        return lastChosen;
    }

    public void setLastChosen(int lastChosen) {
        this.lastChosen = lastChosen != 1 && lastChosen != 0 ? 0 : lastChosen;
    }
}
