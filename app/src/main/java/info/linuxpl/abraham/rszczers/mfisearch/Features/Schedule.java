package info.linuxpl.abraham.rszczers.mfisearch.Features;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.TreeMap;

import info.linuxpl.abraham.rszczers.mfisearch.Features.SQL.DatabaseAdapter;


/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */

public class Schedule implements Faculty <PlanedActivity, String> {
    @Override
    public PlanedActivity find(String pattern, Context context) {
        return null;
    }

    public Schedule() {

    }

    /**
     * Tworzy plan zajęć dla podanego dnia.
     * @param date
     * @param context
     * @return
     */

    public TreeMap<String, PlanedActivity> getDaySchedule(String date, Context context) {

        DatabaseAdapter db = new DatabaseAdapter(context);
        TreeMap<String, PlanedActivity> dayShedule = new TreeMap<String, PlanedActivity>();
        PlanedActivity pa;
        ActivityFactory af = new ActivityFactory(context);
        String[] tables = {"EXAMS", "EXERCISES", "LECTURES", "OTHER"};
        Cursor[] activities = db.getDayActivities(date, tables);
        Cursor query;
        String dateA;
        String name;
        String roomName;
        Classroom room;
        String duration;
        String instructor;
        String description;

        for (int i = 0; i < activities.length; i++) {
            query = activities[i];
            if (query.moveToFirst()) {
                while (!query.isAfterLast()) {
                    dateA = query.getString(query.getColumnIndex("date"));
                    name = query.getString(query.getColumnIndex("name"));
                    roomName = query.getString(query.getColumnIndex("room"));
                    room = db.getClassroom(roomName);
                    duration = query.getString(query.getColumnIndex("duration"));
                    description = query.getString(query.getColumnIndex("description"));
                    instructor = query.getString(query.getColumnIndex("instructor"));
                    pa = af.get(tables[i], name, dateA, room, duration, instructor, description);
                    dayShedule.put(pa.getDate(), pa);
                    query.moveToNext();
                }
            }


        } return dayShedule;
    }
}


