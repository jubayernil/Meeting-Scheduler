package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import model.MeetingModel;

/**
 * Created by mishu on 7/21/2016.
 */
public class MeetingDatabaseSource {
    DatabaseHelper databaseHelper;
    SQLiteDatabase database;
    MeetingModel meetingModel;

    public MeetingDatabaseSource(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
    }

    public boolean addMeeting(MeetingModel meetingModel) {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_MEETING_TITLE, meetingModel.getMeetingTitle());
        contentValues.put(DatabaseHelper.COL_MEETING_LOCATION, meetingModel.getMeetingLocation());
        contentValues.put(DatabaseHelper.COL_START_DATE, meetingModel.getStartDate());
        contentValues.put(DatabaseHelper.COL_START_TIME, meetingModel.getStartTime());
        contentValues.put(DatabaseHelper.COL_MEETING_PRIORITY, meetingModel.getMeetingPriority());
        contentValues.put(DatabaseHelper.COL_OBJECTIVES, meetingModel.getObjectives());

        long inserted = database.insert(DatabaseHelper.TABLE_MEETING, null, contentValues);
        this.close();

        if (inserted > 0) {
            return true;
        } else {
            return false;
        }

    }

    public MeetingModel getMeetingModel(int id) {
        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_MEETING, new String[]
                        {DatabaseHelper.COL_ID, DatabaseHelper.COL_MEETING_TITLE, DatabaseHelper.COL_MEETING_LOCATION,
                                DatabaseHelper.COL_START_DATE, DatabaseHelper.COL_START_TIME, DatabaseHelper.COL_MEETING_PRIORITY, DatabaseHelper.COL_OBJECTIVES},
                DatabaseHelper.COL_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();

        int mId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
        String mMeetingTitle = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEETING_TITLE));
        String mMeetingLocation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEETING_LOCATION));
        String mStartDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_START_DATE));
        String mStartTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_START_TIME));
        String mPriority = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEETING_PRIORITY));
        String mObjectives = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_OBJECTIVES));

        cursor.close();
        this.close();

        meetingModel = new MeetingModel(mId, mMeetingTitle, mMeetingLocation, mStartDate, mStartTime, mPriority, mObjectives);
        return meetingModel;
    }

    public ArrayList<MeetingModel> getAllMeeting() {
        ArrayList<MeetingModel> meetingModels = new ArrayList<>();
        this.open();

        Cursor cursor = database.rawQuery("select * from " + DatabaseHelper.TABLE_MEETING, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                int mId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                String mMeetingTitle = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEETING_TITLE));
                String mMeetingLocation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEETING_LOCATION));
                String mStartDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_START_DATE));
                String mStartTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_START_TIME));
                String mPriority = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEETING_PRIORITY));
                String mObjectives = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_OBJECTIVES));

                meetingModel = new MeetingModel(mId, mMeetingTitle, mMeetingLocation, mStartDate, mStartTime, mPriority, mObjectives);

                cursor.moveToNext();
                meetingModels.add(meetingModel);

            }
        }
        cursor.close();
        this.close();
        return meetingModels;
    }

    public boolean updateMeeting(int id, MeetingModel meetingModel) {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_MEETING_TITLE, meetingModel.getMeetingTitle());
        contentValues.put(DatabaseHelper.COL_MEETING_LOCATION, meetingModel.getMeetingLocation());
        contentValues.put(DatabaseHelper.COL_START_DATE, meetingModel.getStartDate());
        contentValues.put(DatabaseHelper.COL_START_TIME, meetingModel.getStartTime());
        contentValues.put(DatabaseHelper.COL_MEETING_PRIORITY, meetingModel.getMeetingPriority());
        contentValues.put(DatabaseHelper.COL_OBJECTIVES, meetingModel.getObjectives());

        int updated = database.update(DatabaseHelper.TABLE_MEETING, contentValues, DatabaseHelper.COL_ID + " = " + id, null);

        this.close();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteMeeting(int id){
        this.open();

        int deleted = database.delete(DatabaseHelper.TABLE_MEETING, DatabaseHelper.COL_ID + " = " + id, null);
        this.close();

        if (deleted > 0){
            return true;
        } else {
            return false;
        }
    }
}