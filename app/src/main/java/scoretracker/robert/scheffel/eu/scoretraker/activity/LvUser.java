package scoretracker.robert.scheffel.eu.scoretraker.activity;

import android.widget.ImageButton;

/**
 * Created by z1ckz4ck on 14.04.17.
 */
public class LvUser {

    private static final String TAG = "LvUser";

    private int userId;
    private String firstName;
    private String lastName;
    private ImageButton config;
    private boolean isSelected = false;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ImageButton getConfig() {
        return config;
    }

    public void setConfig(ImageButton config) {
        this.config = config;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
