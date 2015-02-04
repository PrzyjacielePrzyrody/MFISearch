package info.linuxpl.abraham.rszczers.mfisearch.Features;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
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

    public TreeMap<Calendar, PlanedActivity> getActivitiesTree(Cursor[] cursor, String[] tables, Context context) {
        DatabaseAdapter db = new DatabaseAdapter(context);
        TreeMap<Calendar, PlanedActivity> tree = new TreeMap<Calendar, PlanedActivity>();
        PlanedActivity pa;
        ActivityFactory af = new ActivityFactory(context);
        Cursor query;
        String dateA;
        String name;
        String roomName;
        Classroom room;
        String duration;
        String instructor;
        String description;

        for (int i = 0; i < cursor.length; i++) {
            query = cursor[i];
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
                    tree.put(Dates.stringToCalendar(pa.getDate()), pa);
                    query.moveToNext();
                }
            }


        }
        return tree;
    }

    /**
     * Tworzy plan zajęć dla podanego dnia.
     *
     * @param date
     * @param context
     * @return
     */

    public TreeMap<Calendar, PlanedActivity> getDaySchedule(String date, Context context) {

        DatabaseAdapter db = new DatabaseAdapter(context);
        TreeMap<Calendar, PlanedActivity> dayShedule = new TreeMap<Calendar, PlanedActivity>();
        ActivityFactory af = new ActivityFactory(context);
        String[] tables = {"EXAMS", "EXERCISES", "LECTURES", "OTHER"};
        //Cursor[] activities=db.getEverything();
        Cursor[] activities = db.getDayActivities(date, tables);
        int o=0;
        for(int i=0; i<activities.length;i++){
            o=o+activities[i].getCount();

        }

        dayShedule = getActivitiesTree(activities, tables, context);

        return dayShedule;
    }

    /**
     * Tablica zajęć w tygodniu (Sunday-Monday, 1-7)
     *
     * @param date
     * @param context
     * @return
     */
    public ArrayList<PlanedActivity>[] getWeekSchedule(String date, Context context) {
        DatabaseAdapter db = new DatabaseAdapter(context);
        Calendar day = Dates.stringToCalendar(date);
        day.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String from = Dates.calendarToString(day);
        day.add(Calendar.DAY_OF_MONTH, 7);
        String to = Dates.calendarToString(day);
        ActivityFactory af = new ActivityFactory(context);
        PlanedActivity pa;

        String[] tables = {"EXAMS", "EXERCISES", "LECTURES", "OTHER"};
        Cursor[] activities = db.getWeek(from, to, tables);
        TreeMap<Calendar, PlanedActivity> weekShedule = getActivitiesTree(activities, tables, context);
        Calendar key;
        int dayOfWeek;


        ArrayList<PlanedActivity>[] weekTable= new ArrayList[7];
        for(int i=0; i<weekTable.length; i++){
            if(weekTable[i]==null){
                weekTable[i]=new ArrayList<PlanedActivity>();                }
        }
        while (!weekShedule.isEmpty()) {
            key = weekShedule.firstKey();
            pa = weekShedule.remove(key);


            dayOfWeek = key.get(Calendar.DAY_OF_WEEK);

                weekTable[dayOfWeek-1].add(pa);

        }

        return weekTable;
    }

    public ArrayList<PlanedActivity> treeToArray(TreeMap<Calendar, PlanedActivity> sched) {
        PlanedActivity p;
        Calendar key;
        ArrayList<PlanedActivity> list = new ArrayList<PlanedActivity>();
        while (!sched.isEmpty()) {
            key = sched.firstKey();
            p = sched.remove(key);
            list.add(p);
        }
        return list;
    }

    public PlanedActivity findNextClasses(Context context){
        Calendar cal=Calendar.getInstance();
        DatabaseAdapter db=new DatabaseAdapter(context);
        String[] date=Dates.calendarToString(cal).split(" ");
        TreeMap<Calendar, PlanedActivity> day=getDaySchedule(date[0], context);
        Calendar key;
        PlanedActivity pa=null;
        String bef="2014-01-01 00:00:00";
        int i=1;
        while(Dates.stringToCalendar(bef).before(cal) && i<30) {

            if (!day.isEmpty()) {
                key = day.firstKey();
                pa = day.remove(key);
                bef = pa.getDate();
            } else {
                cal.add(Calendar.DATE, 1);
                date = Dates.calendarToString(cal).split(" ");
                day = getDaySchedule(date[0], context);
                i++;
            }
        }
       return pa;
    }

}