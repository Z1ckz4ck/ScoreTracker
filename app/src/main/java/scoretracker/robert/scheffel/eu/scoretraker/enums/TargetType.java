package scoretracker.robert.scheffel.eu.scoretraker.enums;

/**
 * Created by z1ckz4ck on 23.01.17.
 */
public enum TargetType {
    Standard,
    Hunter,
    Custom;

    public String valueOf(TargetType targetType){
     return targetType.toString();
    }
}
