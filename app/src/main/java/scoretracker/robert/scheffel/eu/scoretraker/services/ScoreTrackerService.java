package scoretracker.robert.scheffel.eu.scoretraker.services;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import scoretracker.robert.scheffel.eu.scoretraker.contentProv.DbHandlerParcours;
import scoretracker.robert.scheffel.eu.scoretraker.contentProv.DbHandlerTarget;
import scoretracker.robert.scheffel.eu.scoretraker.contentProv.DbHandlerUser;
import scoretracker.robert.scheffel.eu.scoretraker.contentProv.DbParcour;
import scoretracker.robert.scheffel.eu.scoretraker.entity.Parcour;
import scoretracker.robert.scheffel.eu.scoretraker.entity.Target;
import scoretracker.robert.scheffel.eu.scoretraker.entity.User;
import scoretracker.robert.scheffel.eu.scoretraker.enums.ScoreType;


/**
 * Created by z1ckz4ck on 11.03.17.
 */
public class ScoreTrackerService extends Application {
    private static final String TAG = "ScoreTrackerService";

    private DbHandlerUser dbHandlerUser;
    private List<User> activeUserL;
    private Parcour activeParcour;
    private DbHandlerParcours dbHandlerParcours;
    private DbHandlerTarget dbHanderTargets;

    public ScoreTrackerService() {
        dbHandlerUser = new DbHandlerUser(this);
        dbHandlerParcours = new DbHandlerParcours(this);
        dbHanderTargets = new DbHandlerTarget(this);
        activeUserL = new ArrayList<>();
    }

    public synchronized void addUser(User user) {
        dbHandlerUser.addUser(user);
    }

    public synchronized List<User> refreshAndgetAllUser() {
        return dbHandlerUser.getAllUser();
    }

    public synchronized void deleteUser(User user) {
        dbHandlerUser.deleteUser(user);
    }

    ;

    public synchronized void deleteUser(int userId) {
        dbHandlerUser.deleteUser(userId);
    }

    ;

    public synchronized User getUser(int id) {
        return dbHandlerUser.getUser(id);
    }

    public synchronized void saveOrUpdate(User user) {
        if (user != null && user.geteMail() != null) {
            if (user.getUserId() == -1) {
                dbHandlerUser.addUser(user);
            } else {
                User userDb = dbHandlerUser.getUser(user.getUserId());
                userDb.setFirstName(user.getFirstName());
                userDb.setLastName(user.getLastName());
                userDb.seteMail(user.geteMail());
                dbHandlerUser.updateUser(userDb);
            }
        }
    }

    public synchronized void addParcour(Parcour parcour) {
        Log.i(TAG, "Start add Parcour");
        //Id autoincrement
        DbParcour par = new DbParcour(-1, parcour.getName(), parcour.getTargetCount(), parcour.getLenght(), parcour.getTimeToFinish(), 0);
        dbHandlerParcours.addParcour(par);
        dbHanderTargets.addAll(parcour.getListTargets());
    }

    public List<Parcour> getPacours() {
        List<Parcour> result = new ArrayList<>();
        List<DbParcour> allParcours = dbHandlerParcours.getAllParcours();

        for (DbParcour dbP : allParcours) {
            // List<Target> targetsOfParcour = dbHanderTargets.getTargetsOfParcour(dbP.getId());
            List<Target> targetsOfParcour = new ArrayList<>();
            Parcour p = new Parcour(dbP.getId(), dbP.getName(), dbP.getTargetCount(), targetsOfParcour, dbP.getLenght(), dbP.getTimeToFinish(), ScoreType.NFAS_STANDARD, dbP.getLastChosen());
            result.add(p);
        }
        return result;
    }

    public Parcour getLastParcour() {
        DbParcour lastParcour = dbHandlerParcours.getLastParcour();
        if (lastParcour != null) {
            List<Target> targetsOfParcour = dbHanderTargets.getTargetsOfParcour(lastParcour.getId());
            Parcour result = new Parcour(lastParcour.getId(), lastParcour.getName(), lastParcour.getTargetCount(), targetsOfParcour, lastParcour.getLenght(), lastParcour.getTimeToFinish(), ScoreType.NFAS_STANDARD, lastParcour.getLastChosen());
            return result;
        } else {
            return null;
        }
    }

    public synchronized void deleteParcour(Parcour parcour) {
        dbHandlerParcours.deleteParcour(parcour.getId());
    }

    public synchronized void updateLastParcour(Parcour parcour) {
        dbHandlerParcours.setLastChosenToZero();
        dbHandlerParcours.setLastChosen(parcour.getId());
    }

    public List<User> getActiveUserL() {
        return activeUserL;
    }

    public void setActiveUserL(List<User> activeUserL) {
        this.activeUserL = activeUserL;
    }

    public Parcour getActiveParcour() {
        return activeParcour;
    }

    public void setActiveParcour(Parcour activeParcour) {
        this.activeParcour = activeParcour;
    }

}
