package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "meeting_scheduler";
    static final int DATABASE_VERSION = 1;

    static final String TABLE_MEETING = "meeting_schedul";
    static final String COL_ID = "id";
    static final String COL_MEETING_TITLE = "meeting_title";
    static final String COL_MEETING_LOCATION = "meeting_location";
    static final String COL_START_DATE = "start_date";
    static final String COL_START_TIME = "start_time";
    //    static final String COL_END_DATE = "end_date";
//    static final String COL_END_TIME = "end_time";
    static final String COL_MEETING_PRIORITY = "meeting_priority";
    static final String COL_OBJECTIVES = "objectives";

    static final String CREATE_TABLE_MEETING = "create table " + TABLE_MEETING +
            " ( " + COL_ID + " integer primary key, " + COL_MEETING_TITLE + " text, " + COL_MEETING_LOCATION + " text, " +
            COL_START_DATE + " text, " + COL_START_TIME + " text, " + COL_MEETING_PRIORITY + " text, " + COL_OBJECTIVES + " text);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MEETING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exist" + TABLE_MEETING);
        onCreate(db);
    }
}
