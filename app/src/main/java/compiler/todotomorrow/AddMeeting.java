package compiler.todotomorrow;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import database.MeetingDatabaseSource;
import model.MeetingModel;

public class AddMeeting extends AppCompatActivity {
    @InjectView(R.id.meeting_saveBtn)
    Button btnSaveMeeting;
    @InjectView(R.id.meeting_nameET)
    EditText meetingTitleEt;
    @InjectView(R.id.meeting_dateET)
    EditText meetingDateEt;
    @InjectView(R.id.meeting_timeET)
    EditText meetingTimeEt;
    @InjectView(R.id.meeting_addressET)
    EditText meetingAddressEt;
    @InjectView(R.id.meeting_descriptionET)
    EditText meetingDescriptionEt;
    @InjectView(R.id.meeting_priority_spnr)
    Spinner meetingPrioritySpinner;

    private Calendar pickTimeCalendar, pickDateCalendar;
    private ArrayList<String> priorityArray;
    private String meeting_priority, meeting_startTime,
            meeting_startDate, meetingTitle, meetingAddress,
            meetingDescription;
    MeetingModel addToMeetingModel;
    MeetingDatabaseSource meetingDatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        ButterKnife.inject(this);
        addAdapter_to_spinner();
    }

    public void save_meetingBTN(View view) {
        meetingTitle = meetingTitleEt.getText().toString();
        meeting_startDate = meetingDateEt.getText().toString();
        meeting_startTime = meetingTimeEt.getText().toString();
        meetingAddress = meetingAddressEt.getText().toString();
        meetingDescription = meetingDescriptionEt.getText().toString();
//        meeting_priority
        addToMeetingModel = new MeetingModel(meetingTitle,meetingAddress,meeting_startDate,meeting_startTime,meeting_priority,meetingDescription);
        meetingDatabaseSource = new MeetingDatabaseSource(this);
        boolean meetingAddStatus = meetingDatabaseSource.addMeeting(addToMeetingModel);
        if (meetingAddStatus==true){
            Toast.makeText(AddMeeting.this, "Meeting added successfully", Toast.LENGTH_SHORT).show();
            Intent mainActivity = new Intent(getBaseContext(),MainActivity.class);
            startActivity(mainActivity);
        } else {
            Toast.makeText(AddMeeting.this, "Meeting failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void showDatePickerETdate(View view) {
        getDate_from_EditTextOnClick();
    }

    public void showTimePickerETtime(View view) {
        getTime_from_EditTextOnClick();
    }

    private void getDate_from_EditTextOnClick() {
        pickDateCalendar = Calendar.getInstance();
//        Date date = pickDateCalendar.getTime();
        int year = pickDateCalendar.get(Calendar.YEAR);
        int month = pickDateCalendar.get(Calendar.MONTH);
        int day = pickDateCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mPickerDialog;
        mPickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                meetingDateEt.setText(dayOfMonth + ":" + monthOfYear + ":" + year);
            }
        }, day, month, year);
        mPickerDialog.setTitle("Select Meeting Start Date");
        mPickerDialog.show();
    }

    private void getTime_from_EditTextOnClick() {
        pickTimeCalendar = Calendar.getInstance();
        int hour = pickTimeCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = pickTimeCalendar.get(Calendar.MINUTE);
        TimePickerDialog mPickerDialog;
        mPickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                meetingTimeEt.setText(hourOfDay + ":" + minute + "00");
                meeting_startTime = meetingTimeEt.getText().toString();
            }
        }, hour, minute, true);
        mPickerDialog.setTitle("Select Meeting Start Time");
        mPickerDialog.show();
    }

    private void addAdapter_to_spinner() {
        priorityArray = new ArrayList<>();
        priorityArray.add("HIGH");
        priorityArray.add("MEDIUM");
        priorityArray.add("LOW");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_row, priorityArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meetingPrioritySpinner.setAdapter(adapter);
        meetingPrioritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                meeting_priority = meetingPrioritySpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
