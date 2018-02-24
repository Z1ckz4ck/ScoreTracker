package scoretracker.robert.scheffel.eu.scoretraker.enums;

/**
 * Created by z1ckz4ck on 22.01.17.
 */
public enum ScoreType {

    NFAS_STANDARD(20,16,14,10,8,4,"20/16,14/10,8/4"),
    DBSV_STANDARD(15,12,10,7,5,2,"15/12,10/7,5/2"),
    HUNTER_3D(15,0,12,0,7,0,"Center Kill, Kill , Body Hit");



   private int firstKill;
   private int firstHit;
   private int secondKill;
   private int secondHit;
   private int thirdKill;
   private int thirdHit;
   private String discription;


    private ScoreType(int firstKill, int firstHit, int secondKill, int secondHit, int thirdKill, int thirdHit, String discription){
        this.firstKill = firstKill;
        this.firstHit = firstHit;
        this.secondKill = secondKill;
        this.secondHit = secondHit;
        this.thirdKill = thirdKill;
        this.thirdHit = thirdHit;
        this.discription = discription;
    }


    public int getFirsKill(){
        return firstKill;
    }

    public int getFirstHit() {
        return firstHit;
    }

     public int getSecondKill() {
        return secondKill;
    }

    public int getSecondHit() {
        return secondHit;
    }

    public int getThirdKill() {
        return thirdKill;
    }

    public int getThirdHit() {
        return thirdHit;
    }
    public String getDiscription(){
        return discription;
    }

 }
