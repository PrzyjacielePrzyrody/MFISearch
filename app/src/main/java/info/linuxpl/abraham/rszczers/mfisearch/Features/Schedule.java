package info.linuxpl.abraham.rszczers.mfisearch.Features;

import android.content.Context;
import android.database.Cursor;

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

    public Schedule(){

    }

    public TreeMap<String, PlanedActivity> getDaySchedule(String date, Context context) {

        DatabaseAdapter db = new DatabaseAdapter(context);
        TreeMap<String, PlanedActivity> dayShedule = new TreeMap<String, PlanedActivity>();
        PlanedActivity pa;
        ActivityFactory af = new ActivityFactory();
        String[] tables = {"EXAMS", "EXERCISES", "LECTURES", "OTHER"};
        Cursor[] activities = db.getDayActivities(date, tables);
        Cursor query;

        for (int i = 0; i < activities.length; i++) {
            query = activities[i];
            while (!query.isAfterLast()) {
                pa = af.get(tables[i],
                        query.getString(query.getColumnIndex("date")),
                        query.getString(query.getColumnIndex("name")),
                        db.getClassroom(query.getString(query.getColumnIndex("room"))),
                        query.getString(query.getColumnIndex("duration")),
                        query.getString(query.getColumnIndex("instructor")),
                        query.getString(query.getColumnIndex("description")));
                dayShedule.put(pa.getDate(), pa);
                query.moveToNext();
            }
        }

        return dayShedule;
    }
}


