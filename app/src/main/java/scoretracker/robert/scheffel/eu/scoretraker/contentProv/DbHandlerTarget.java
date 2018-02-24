package scoretracker.robert.scheffel.eu.scoretraker.contentProv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import scoretracker.robert.scheffel.eu.scoretraker.entity.Target;
import scoretracker.robert.scheffel.eu.scoretraker.enums.TargetType;
import scoretracker.robert.scheffel.eu.scoretraker.utils.Constants;


public class DbHandlerTarget extends SQLiteOpenHelper{
    //DB
    private static final String DB_NAME = "ScoreTrackerDB";

    //Table name
    private static final String TABLE_TARGETS="TARGETS_TAB";

    //Table Colums
    private static final String TARGET_ID = "target_id";
    private static final String TARGET_NR = "target_nr";
    private static final String TARGET_NAME = "target_name";
    private static final String TARGET_DISTANCE = "target_distance";
    private static final String TARGET_TYPE ="target_type";
    private static final String PARCOUR_ID = "parcour_id";

    public DbHandlerTarget(Context context){
        super(context,DB_NAME,null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TARTGETS_TABLE = "CREATE TABLE " + TABLE_TARGETS + "(" + TARGET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                TARGET_NR + " INTEGER NOT NULL, " + TARGET_NAME + " TEXT, " + TARGET_DISTANCE + " REAL, " + TARGET_TYPE + " TEXT, " + PARCOUR_ID +
                " INTEGER NOT NULL, FOREIGN KEY( " + PARCOUR_ID + " ) REFERENCES PARCOURS_TAB( parcours_id ) ) ";
        db.execSQL(CREATE_TARTGETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TARGETS);
        onCreate(db);
    }

    public void addTarget(Target target){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TARGET_NR, target.getNrTarget());
        values.put(TARGET_NAME, target.getNameTarget());
        values.put(TARGET_DISTANCE, target.getDistance());
        values.put(TARGET_TYPE, target.getTargetType().valueOf(target.getTargetType()));
        values.put(PARCOUR_ID, target.getParcourId());

        db.insert(TABLE_TARGETS, null, values);
        db.close();
    }


    /**
     * deletes a Target.
     * calls deleteTarget(int id)
     *
     * @param target
     */
    public void deleteTarget(Target target) {
        deleteTarget(target.getTargetId());
    }

    public void deleteTarget(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TARGETS, TARGET_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateTarget(Target target){
        //TODO: implement
    }


    public void addAll(List<Target> listTargets) {
        if(!listTargets.isEmpty()){
        //TODO: implement
        }
    }

    public List<Target> getTargetsOfParcour(int parcourId){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Target> result = new ArrayList<>();
        Cursor cur = db.rawQuery("select * from " + TABLE_TARGETS +" where " + PARCOUR_ID + " = " + parcourId, null);

        if (cur.moveToFirst()) {
            do {
                Target target = new Target(Integer.parseInt(cur.getString(1)), cur.getString(2));
                target.setTargetId(Integer.parseInt(cur.getString(0)));
                target.setDistance(Integer.parseInt(cur.getString(3)));
                target.setTargetType(TargetType.valueOf(cur.getString(4)));
                target.setParcourId(Integer.parseInt(cur.getString(5)));

                result.add(target);
            } while (cur.moveToNext());
        }

        if (cur != null && !cur.isClosed()) {
            cur.close();
        }
        return result;
    }
}
