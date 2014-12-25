package info.linuxpl.abraham.rszczers.mfisearch.Features;

import android.content.Context;

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

    /** KONSTRUKTORY NIE PRZEKAZUJĄ ARGUMENTÓW – DO UZUPEŁNIENIA
     * Metoda fabrykująca obiekty implementujące PlanedActivity
     * @param type determinuje jaki rodzaj obiektu chcemy otrzymać; możliwe to: lecture, exercise, exam, other
     * @param date
     * @param room
     * @param duration
     * @param instructor
     * @param description
     * @return
     */
    public PlanedActivity make(String type, String date, Classroom room, int duration, String instructor,
                              String description) {
        PlanedActivity product = null;
        type = type.toUpperCase();
        if(type.equals("LECTURE")) {
            product = new LecturePlaned();
            dbAdapter.add(product);
            //ustawia pole id w obiekcie po nadaniu go w bazie.
            product.setID(dbAdapter.getID(product));
        } else if(type.equals("EXERCISE")) {
            product = new ExercisePlaned();
            dbAdapter.add(product);
            //ustawia pole id w obiekcie po nadaniu go w bazie.
            product.setID(dbAdapter.getID(product));
        } else if(type.equals("EXAM")) {
            product = new ExamPlaned();
            dbAdapter.add(product);
            //ustawia pole id w obiekcie po nadaniu go w bazie.
            product.setID(dbAdapter.getID(product));
        } else if(type.equals("OTHER")) {
            product = new OtherPlaned();
            dbAdapter.add(product);
            //ustawia pole id w obiekcie po nadaniu go w bazie.
            product.setID(dbAdapter.getID(product));
        }
        return product;
    }
}
