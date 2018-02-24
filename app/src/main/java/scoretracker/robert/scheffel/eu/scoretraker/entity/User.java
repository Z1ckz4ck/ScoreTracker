package scoretracker.robert.scheffel.eu.scoretraker.entity;

import java.io.Serializable;

/**
 * Created by z1ckz4ck on 22.01.17.
 */
public class User implements Serializable{

    private int userId;
    private String firstName;
    private String lastName;
    private String eMail;

    public User(){
        userId = -1;
    }

      /**++++++++++++++++++++++Getter & Setter++++++++++++++++++++++*/
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        return eMail.equals(user.eMail);
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + eMail.hashCode();
        return result;
    }
}
