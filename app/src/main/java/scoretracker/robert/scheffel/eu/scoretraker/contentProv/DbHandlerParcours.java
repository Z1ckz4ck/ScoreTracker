package scoretracker.robert.scheffel.eu.scoretraker.contentProv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import scoretracker.robert.scheffel.eu.scoretraker.utils.Constants;


/**
 * Created by z1ckz4ck on 14.04.17.
 */
public class DbHandlerParcours extends SQLiteOpenHelper {
    //DB
    private static final String DB_NAME = "ScoreTrackerDB";

    //Table name
    private static final String TABLE_PARCOURS = "PARCOURS_TAB";

    //Table Colums
    private static final String PARCOURS_ID = "parcours_id";
    private static final String PARCOURS_NAME = "parcours_name";
    private static final String PARCOURS_COUNT_TARGETS = "count_targets";
    private static final String PARCOURS_TIME_TO_FINISH = "time_to_finish";
    private static final String PARCOURS_LENGTH = "parcours_length";
    private static final String PARCOURS_LAST_CHOSEN = "last_parcour";

    public DbHandlerParcours(Context context) {
        super(context, DB_NAME, null, Constants.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PARCOUR_TABLE = "CREATE TABLE " + TABLE_PARCOURS + "(" + PARCOURS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                PARCOURS_NAME + " TEXT NOT NULL, " + PARCOURS_COUNT_TARGETS + " INTEGER, " + PARCOURS_TIME_TO_FINISH + " TEXT, " + PARCOURS_LENGTH + " TEXT, " + PARCOURS_LAST_CHOSEN +" INTEGER NOT NULL )";
        db.execSQL(CREATE_PARCOUR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARCOURS);
        //create table
        onCreate(db);
    }

    /**
     * Adding new parcour
     */
    public void addParcour(DbParcour parcour) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PARCOURS_NAME, parcour.getName());
        values.put(PARCOURS_COUNT_TARGETS, parcour.getTargetCount());
        values.put(PARCOURS_TIME_TO_FINISH, parcour.getTimeToFinish());
        values.put(PARCOURS_LENGTH, parcour.getLenght());
        values.put(PARCOURS_LAST_CHOSEN, parcour.getLastChosen());


        db.insert(TABLE_PARCOURS, null, values);
        db.close();
    }

    /**
     * deletes a Parcour.
     * calls deleteParcour(int id)
     *
     * @param parcour
     */
    public void deleteParcour(DbParcour parcour) {
        deleteParcour(parcour.getId());
    }

    public void deleteParcour(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PARCOURS, PARCOURS_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    /**
     * Returns single Parcour by id
     *
     * @param id - of the Parcour
     * @return - a Parcour
     */
    public DbParcour getParcour(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PARCOURS, new String[]{PARCOURS_ID, PARCOURS_NAME, PARCOURS_COUNT_TARGETS, PARCOURS_TIME_TO_FINISH, PARCOURS_LENGTH,PARCOURS_LAST_CHOSEN}, PARCOURS_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {

            return new DbParcour(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)));
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return null;
    }

    /**
     * Returns the last used Parcour
     * @return - a Parcour
     */
    public DbParcour getLastParcour() {
        SQLiteDatabase db = this.getReadableDatabase();
        int chosen = 1;
        Cursor cursor = db.query(TABLE_PARCOURS, new String[]{PARCOURS_ID, PARCOURS_NAME, PARCOURS_COUNT_TARGETS, PARCOURS_TIME_TO_FINISH, PARCOURS_LENGTH, PARCOURS_LAST_CHOSEN}, PARCOURS_LAST_CHOSEN + "=?", new String[]{String.valueOf(chosen)}, null, null, null);
        if(cursor.getCount() > 1){
            throw new SQLiteAbortException("To Manny Rows");
        }

        if (cursor.moveToFirst()) {
            return new DbParcour(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)));
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return null;
    }

    public void updateParcour(DbParcour parcour) {
        //TODO: implement
    }

    public List<DbParcour> getAllParcours() {
        ArrayList<DbParcour> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from " + TABLE_PARCOURS, null);
        if (cur.moveToFirst()) {
            do {
                result.add(new DbParcour(Integer.parseInt(cur.getString(0)), cur.getString(1), Integer.parseInt(cur.getString(2)), cur.getString(3), cur.getString(4) , Integer.parseInt(cur.getString(5))));
            } while (cur.moveToNext());
        }

        if (cur != null && !cur.isClosed()) {
            cur.close();
        }

        return result;
    }


    public void setLastChosenToZero() {
        String sql = "UPDATE "+ TABLE_PARCOURS+" SET "+ PARCOURS_LAST_CHOSEN+ " = 0 WHERE "+PARCOURS_LAST_CHOSEN+ " = 1";
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(sql, null);

    }

    public void setLastChosen(DbParcour parcour){
        setLastChosen(parcour.getId());
    }

    public void setLastChosen(int parcourId){
        String sql = "UPDATE "+ TABLE_PARCOURS+" SET "+ PARCOURS_LAST_CHOSEN+ " = 1 WHERE " +PARCOURS_ID+ " = " +parcourId;
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(sql, null);
    }
}
