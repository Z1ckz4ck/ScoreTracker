package scoretracker.robert.scheffel.eu.scoretraker.contentProv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import scoretracker.robert.scheffel.eu.scoretraker.entity.User;
import scoretracker.robert.scheffel.eu.scoretraker.utils.Constants;


/**
 * Created by z1ckz4ck on 11.03.17.
 */
public class DbHandlerUser extends SQLiteOpenHelper {
    //DB
    private static final String DB_NAME = "ScoreTrackerDB";
    //Table name
    private static final String TABLE_USER = "USER_TAB";
    ///Table Colums
    private static final String USER_ID = "user_id";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String EMail = "email";


    public DbHandlerUser(Context context) {
        super(context, DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + EMail + " TEXT NOT" +
                " NULL, " + FIRSTNAME + " TEXT, " + LASTNAME + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        //create table
        onCreate(db);
    }

    /**
     * Adding new user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(USER_ID, user.getUserId());
        values.put(EMail, user.geteMail());
        values.put(FIRSTNAME, user.getFirstName());
        values.put(LASTNAME, user.getLastName());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    // Get single user
    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{USER_ID, EMail, FIRSTNAME, LASTNAME}, USER_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {

            User user = new User();
            user.setUserId(Integer.parseInt(cursor.getString(0)));
            user.seteMail(cursor.getString(1));
            user.setFirstName(cursor.getString(2));
            user.setLastName(cursor.getString(3));
            cursor.close();
            return user;
        }
        return null;
    }

    // Getting All Users
    public List<User> getAllUser() {

        List<User> users = new ArrayList<>();
        String selectAll = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUserId(Integer.parseInt(cursor.getString(0)));
                user.seteMail(cursor.getString(1));
                user.setFirstName(cursor.getString(2));
                user.setLastName(cursor.getString(3));
                users.add(user);

            } while (cursor.moveToNext());

        }
        cursor.close();
        return users;
    }

    // Getting users Count
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single user
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(USER_ID, user.getUserId());
        values.put(EMail, user.geteMail());
        values.put(FIRSTNAME, user.getFirstName());
        values.put(LASTNAME, user.getLastName());
        return db.update(TABLE_USER, values, USER_ID + "=?", new String[]{String.valueOf(user.getUserId())});
    }

    // Deleting single user
    public void deleteUser(User user) {
       /* SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, USER_ID + "=?", new String[]{String.valueOf(user.getUserId())});
        db.close();
        */
        deleteUser(user.getUserId());
    }

    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, USER_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
