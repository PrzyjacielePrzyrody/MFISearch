package info.linuxpl.abraham.rszczers.mfisearch.Activities;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import info.linuxpl.abraham.rszczers.mfisearch.Features.Dates;
import info.linuxpl.abraham.rszczers.mfisearch.Features.PlanedActivity;
import info.linuxpl.abraham.rszczers.mfisearch.R;

/**
 * Created by edys on 08.01.15.
 */
public class ActivityRowAdapter extends ArrayAdapter<PlanedActivity> {

    Context context;
    int layoutResousceID;
    ArrayList<PlanedActivity> activities=null;

    public ActivityRowAdapter(Context context,int layoutResousceID, ArrayList<PlanedActivity> activities){
        super(context,layoutResousceID, activities);
        this.context=context;
        this.layoutResousceID=layoutResousceID;
        this.activities=activities;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View row=convertView;
        PlanedActivityHolder holder=null;

        if(row==null){
            LayoutInflater inflater=((Activity)context).getLayoutInflater();
            row=inflater.inflate(layoutResousceID, parent, false);

            holder=new PlanedActivityHolder();
            LinearLayout dane=(LinearLayout) row.findViewById(R.id.coKtoGdzie);
            holder.name = (TextView)dane.findViewById(R.id.activityName); //coś nie działa z name
            holder.time = (TextView)row.findViewById(R.id.timeText);
            holder.room = (TextView)row.findViewById(R.id.classroom);
            holder.instructor = (TextView)dane.findViewById(R.id.lector);
            holder.description=(TextView)dane.findViewById(R.id.activityDescription);
            holder.id=(TextView) row.findViewById(R.id.id_planed_activity);

            row.setTag(holder);

        } else {
            holder= (PlanedActivityHolder) row.getTag();
        }

        PlanedActivity act=activities.get(position);
        holder.name.setText(act.getName());
        holder.time.setText(Dates.fromToTime(act.getDate(), act.getDuration()));
        holder.room.setText(act.getRoom().getName());
        holder.instructor.setText(act.getInstructor());
        holder.description.setText(act.getDescription());
        holder.id.setText(""+act.getID());

        return row;

    }

    static class PlanedActivityHolder{
        public TextView name;
        protected TextView time;
        protected TextView room;
        protected TextView instructor;
        protected  TextView description;
        protected TextView id;

    }
}
