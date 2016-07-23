package compiler.todotomorrow;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

import adapter.CustomAdapter;
import database.MeetingDatabaseSource;
import model.MeetingModel;

public class MainActivity extends AppCompatActivity {
    ListView main_meeting_listView;
    ArrayList<MeetingModel> meetingModelsArray;
    CustomAdapter customAdapter;
    MeetingDatabaseSource meetingDatabaseSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_meeting_listView = (ListView) findViewById(R.id.list_today);
        addDataToArrayList();
        addListViewToAdapter();
    }

    private void addListViewToAdapter() {
        customAdapter=new CustomAdapter(this,meetingModelsArray);
        main_meeting_listView.setAdapter(customAdapter);

    }

    private void addDataToArrayList() {
        meetingDatabaseSource = new MeetingDatabaseSource(this);
        meetingModelsArray = new ArrayList<>();
        meetingModelsArray = meetingDatabaseSource.getAllMeeting();
    }

    public void add_meeting(View view) {
        Intent addMeetingActivity = new Intent(this, AddMeeting.class);
        startActivity(addMeetingActivity);
    }

}
