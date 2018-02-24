package scoretracker.robert.scheffel.eu.scoretraker.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import scoretracker.robert.scheffel.eu.scoretraker.enums.ScoreType;


/**
 * Created by z1ckz4ck on 21.01.17.
 */
public class Parcour implements Serializable{

    private Integer id;
    private String name;
    private int targetCount;
    private List<Target> listTargets;
    private String lenght;
    private String timeToFinish;
    private ScoreType scoreType;
    private int lastChosen;

    public Parcour(String name) {
        this(null,name,0);
    }

    /** Construktor initinalize lenght, time and lastChosen with zero
     * @param name -name of the pparcour
     * @param targetCount - count of Targets
     */
    public Parcour(Integer id, String name, int targetCount) {
        this(id, name,targetCount,new ArrayList<Target>(),"0","", ScoreType.NFAS_STANDARD, 0);
    }

    public Parcour(Integer id, String name, int targetCount, int lastChosen) {
        this(id, name,targetCount,new ArrayList<Target>(),"0","", ScoreType.NFAS_STANDARD, lastChosen);
    }

    public Parcour(Integer id, String name, int targetCount, List<Target> listTargets, String lenght, String timeToFinish, ScoreType scoreType, int lastChosen) {
        this.id = id;
        this.name = name;
        this.targetCount = targetCount;
        this.listTargets = listTargets;
        this.lenght = lenght;
        this.timeToFinish = timeToFinish;
        this.scoreType = scoreType;
        this.lastChosen = lastChosen;
    }

    /**++++++++++++++++++++++Getter & Setter++++++++++++++++++++++*/

    public int getTargetCount() {
        return targetCount;
    }

    public void setTargetCount(int targetCount) {
        this.targetCount = targetCount;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addTargetToList(Target target){
        listTargets.add(target);
    }

    public void addTargetAtPosition(Target target, int position){
        if(position > listTargets.size()){
            addTargetToList(target);
        }
    }
    public void removeTargetFromList(Target target){
        listTargets.remove(target);
    }
    public List<Target> getListTargets(){
        return listTargets;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public void setScoreType(ScoreType scoreType) {
        this.scoreType = scoreType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parcour)) return false;

        Parcour parcour = (Parcour) o;

        if (targetCount != parcour.targetCount) return false;
        if (name != null ? !name.equals(parcour.name) : parcour.name != null) return false;
        if (listTargets != null ? !listTargets.equals(parcour.listTargets) : parcour.listTargets != null) return false;
        if (lenght != null ? !lenght.equals(parcour.lenght) : parcour.lenght != null) return false;
        if (timeToFinish != null ? !timeToFinish.equals(parcour.timeToFinish) : parcour.timeToFinish != null)
            return false;
        return scoreType == parcour.scoreType;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + targetCount;
        result = 31 * result + (listTargets != null ? listTargets.hashCode() : 0);
        result = 31 * result + (lenght != null ? lenght.hashCode() : 0);
        result = 31 * result + (timeToFinish != null ? timeToFinish.hashCode() : 0);
        result = 31 * result + (scoreType != null ? scoreType.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
