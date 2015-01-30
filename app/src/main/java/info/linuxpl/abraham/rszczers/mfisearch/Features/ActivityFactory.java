package info.linuxpl.abraham.rszczers.mfisearch.Features;

import android.content.Context;
import android.util.Log;

import java.util.Calendar;

import info.linuxpl.abraham.rszczers.mfisearch.Features.SQL.DatabaseAdapter;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */
public class ActivityFactory {
    DatabaseAdapter dbAdapter;


    public ActivityFactory(Context context) {
        dbAdapter = new DatabaseAdapter(context);
    }

    /**
     * Metoda fabrykująca obiekty implementujące PlanedActivity.
     * @param type determinuje jaki rodzaj obiektu chcemy otrzymać; możliwe to: lecture, exercise, exam, other.            .
     * @param date
     * @param room
     * @param duration
     * @param instructor
     * @param description
     * @return
     */
    public PlanedActivity make(String type, String name, String date, String howLong, int period, Classroom room, String duration, String instructor,
                              String description) {
        PlanedActivity product = null;
        type = type.toUpperCase();

        while (Dates.stringToCalendar(date).before(Dates.stringToCalendar(howLong))) {
            if (type.equals("LECTURE")) {
                product = new LecturePlaned(date, name, room, duration, instructor, description);
                dbAdapter.add(product);
                //ustawia pole id w obiekcie po nadaniu go w bazie.
                product.setID(dbAdapter.getID(product));
            } else if (type.equals("EXERCISE")) {
                product = new ExercisePlaned(date, name, room, duration, instructor, description);
                dbAdapter.add(product);
                //ustawia pole id w obiekcie po nadaniu go w bazie.
                product.setID(dbAdapter.getID(product));
            } else if (type.equals("EXAM")) {
                product = new ExamPlaned(date, name, room, duration, instructor, description);
                dbAdapter.add(product);
                //ustawia pole id w obiekcie po nadaniu go w bazie.
                product.setID(dbAdapter.getID(product));
            } else if (type.equals("OTHER")) {
                product = new OtherPlaned(date, name, room, duration, instructor, description);
                dbAdapter.add(product);
                //ustawia pole id w obiekcie po nadaniu go w bazie.
                product.setID(dbAdapter.getID(product));
            }
        Log.d("Tworze pierwsze zajęcie", ""+product.getDate());

            Calendar when=Dates.stringToCalendar(date);
            when.add(Calendar.DATE, period);
            date=Dates.calendarToString(when);

              // PlanedActivity product2=make(type, name, date, howLong, period, room, duration, instructor, description);
               // Log.d("Tworze zajęcie", ""+product.getDate());


        }
        return product;
    }

    /**
     * Czasem istnieje potrzeba skonstruowania obiektu bez dodawania go do bazy. Z sytuacją taką
     * spotykamy się, gdy chcemy odtworzyć plan zajęć z bazy danych. Wówczas interesuje nas lista
     * PlanedActivities otrzymana z bazy danych.
     *
     * Ta metoda fabrykująca różni się tylko tym, że nie dodaje nowo stworzonych obiektów do bazy.
     * Nowy obiekt można stworzyć tylko pod warunkiem, że istnieje już w bazie.
     * @param type
     * @param date
     * @param room
     * @param duration
     * @param instructor
     * @param description
     * @return
     */
    public PlanedActivity get(String type, String name, String date, Classroom room, String duration, String instructor,
                               String description) {
        PlanedActivity product = null;
        type = type.toUpperCase();


        if(type.equals("LECTURES")) {
            product = new LecturePlaned(date, name, room, duration, instructor, description);
            product.setID(dbAdapter.getID(product));
        } else if(type.equals("EXERCISES")) {
            product = new ExercisePlaned(date, name, room, duration, instructor, description);
            product.setID(dbAdapter.getID(product));
        } else if(type.equals("EXAMS")) {
            product = new ExamPlaned(date,name, room, duration, instructor, description);
            product.setID(dbAdapter.getID(product));
        } else if(type.equals("OTHERS")) {
            product = new OtherPlaned(date, name, room, duration, instructor, description);
            product.setID(dbAdapter.getID(product));
        }
        return product;
    }
}
