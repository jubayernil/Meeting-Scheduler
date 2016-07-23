package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import compiler.todotomorrow.R;
import model.MeetingModel;

/**
 * Created by mishu on 7/22/2016.
 */
public class CustomAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<MeetingModel> meetingModelArrayList;

    private class viewHolder {
        TextView row_time;
        TextView row_leftTime;
        TextView row_meetingTitle;
        TextView row_meetingAddress;
    }

    public CustomAdapter(Context context, ArrayList<MeetingModel> meetingModels) {
        super(context, R.layout.list_row, meetingModels);
        this.context = context;
        this.meetingModelArrayList = meetingModels;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new viewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row, null, true);
            viewHolder.row_time = (TextView) convertView.findViewById(R.id.row_time_tv);
            viewHolder.row_leftTime = (TextView) convertView.findViewById(R.id.row_remaining_time_tv);
            viewHolder.row_meetingTitle = (TextView) convertView.findViewById(R.id.row_title_tv);
            viewHolder.row_meetingAddress = (TextView) convertView.findViewById(R.id.row_description_tv);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CustomAdapter.viewHolder) convertView.getTag();
        }
        viewHolder.row_time.setText(meetingModelArrayList.get(position).getStartTime());
        viewHolder.row_leftTime.setText("not done");// time picker needed.
        viewHolder.row_meetingTitle.setText(meetingModelArrayList.get(position).getMeetingTitle());
        viewHolder.row_meetingAddress.setText(meetingModelArrayList.get(position).getMeetingLocation());
        return convertView;
    }
}
