package scoretracker.robert.scheffel.eu.scoretraker.entity;

import java.io.Serializable;

import scoretracker.robert.scheffel.eu.scoretraker.enums.TargetType;


/**
 * Created by z1ckz4ck on 22.01.17.
 */
public class Target implements Serializable{

    private int targetId;
    private Integer nrTarget;
    private String nameTarget;
    private Integer distance;
    private TargetType targetType;
    private int parcourId;



    public Target(int nr) {
        this(nr,"Target");
    }

    public  Target(int nr, String name){
        this.nrTarget = nr;
        this.nameTarget = name;
    }


    /**++++++++++++++++++++++Getter & Setter++++++++++++++++++++++*/

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }


    public String getNameTarget() {
        return nameTarget;
    }

    public void setNameTarget(String nameTarget) {
        this.nameTarget = nameTarget;
    }

    public Integer getNrTarget() {
        return nrTarget;
    }

    public void setNrTarget(Integer nrTarget) {
        this.nrTarget = nrTarget;
    }

    public int getParcourId() { return parcourId; }

    public void setParcourId(int parcourId) { this.parcourId = parcourId; }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Target target = (Target) o;

        if (targetId != target.targetId) return false;
        if (parcourId != target.parcourId) return false;
        if (nrTarget != null ? !nrTarget.equals(target.nrTarget) : target.nrTarget != null)
            return false;
        if (nameTarget != null ? !nameTarget.equals(target.nameTarget) : target.nameTarget != null)
            return false;
        if (distance != null ? !distance.equals(target.distance) : target.distance != null)
            return false;
        return targetType == target.targetType;
    }

    @Override
    public int hashCode() {
        int result = targetId;
        result = 31 * result + (nrTarget != null ? nrTarget.hashCode() : 0);
        result = 31 * result + (nameTarget != null ? nameTarget.hashCode() : 0);
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (targetType != null ? targetType.hashCode() : 0);
        result = 31 * result + parcourId;
        return result;
    }
}
