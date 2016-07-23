package model;

import java.util.ArrayList;

/**
 * Created by mishu on 7/21/2016.
 */
public class MeetingModel {
    private int id;
    private String meetingTitle;
    private String meetingLocation;
    private String startDate;
    private String startTime;
    private String meetingPriority;
    private String objectives;

    public MeetingModel() {
    }

    public MeetingModel(int id, String meetingTitle, String meetingLocation, String startDate, String startTime, String meetingPriority, String objectives) {
        this.id = id;
        this.meetingTitle = meetingTitle;
        this.meetingLocation = meetingLocation;
        this.startDate = startDate;
        this.startTime = startTime;
        this.meetingPriority = meetingPriority;
        this.objectives = objectives;
    }

    public MeetingModel(String meetingTitle, String meetingLocation, String startDate, String startTime, String meetingPriority, String objectives) {
        this.meetingTitle = meetingTitle;
        this.meetingLocation = meetingLocation;
        this.startDate = startDate;
        this.startTime = startTime;
        this.meetingPriority = meetingPriority;
        this.objectives = objectives;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeetingTitle() {
        return meetingTitle;
    }

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }

    public String getMeetingLocation() {
        return meetingLocation;
    }

    public void setMeetingLocation(String meetingLocation) {
        this.meetingLocation = meetingLocation;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getMeetingPriority() {
        return meetingPriority;
    }

    public void setMeetingPriority(String meetingPriority) {
        this.meetingPriority = meetingPriority;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    public ArrayList<MeetingModel> getAllMeeting(){
        ArrayList<MeetingModel>meetingModelArrayList=new ArrayList<>();
        meetingModelArrayList.add(new MeetingModel("Test application","adding from MeetingModel.java","21/7/2016","1:13am","priority High","this is test only"));
        return meetingModelArrayList;
    }
}
