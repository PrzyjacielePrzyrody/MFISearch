package info.linuxpl.abraham.rszczers.mfisearch.Features.SQL;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

import info.linuxpl.abraham.rszczers.mfisearch.Features.ActivityFactory;
import info.linuxpl.abraham.rszczers.mfisearch.Features.Classroom;
import info.linuxpl.abraham.rszczers.mfisearch.Features.Dates;
import info.linuxpl.abraham.rszczers.mfisearch.Features.ExamPlaned;
import info.linuxpl.abraham.rszczers.mfisearch.Features.PlanedActivity;

/**
 * Tutaj znajdą się wszystkie metody potrzebne do pobierania danych na potrzeby wyświetlania
 * tego w różnych elementach interfejsu użytkownika. Pakuj tutaj wszystko, co Ci przyjdzie do głowy,
 * a czego zabrakło w DatabaseHelper.
 */
public class DatabaseAdapter {
    private DatabaseHelper mfidb;

    public DatabaseAdapter(Context context) {
        this.mfidb = new DatabaseHelper(context);
    }

    /**
     * W tej funkcji chodzi o to, że podczas tworzenia nowego obiektu klasy PlanedActivity
     * nie jest od razu ustawiane pole id. Pole id obiektu MUSI odpowiadać wartości klucza głównego
     * w tabeli. Dlatego po stworzeniu obiektu i dodaniu go do bazy trzeba przeczytać jego numer id
     * w bazie i ustawić go w obiekcie. Do tego służy ta funkcja.
     * @param product
     * @return
     */
    public int getID(PlanedActivity product) {
        int id=0;
        SQLiteDatabase db=mfidb.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String table=product.getTable();
        String where="date LIKE '"+product.getDate()+"' AND instructor LIKE '"+product.getInstructor()+"'";
        String[] column={"_id"};

        Cursor c=db.query(table, column, where, null, null, null, null, null );
        if(c.moveToFirst()) {
             id = c.getInt(c.getColumnIndex("_id"));
        }
        db.close();
        return id;
    }


    /** DO SPRAWDZENIA
     * Dodaje do bazy dowolny obiekt PlanedActivity.
     * Trzeba tutaj napisać jakis parser
     * PlanedActivity -> HashMap; tam gdzie null powinien być HashMap
     * @param product
     */
    public void add(PlanedActivity product, int period) {

        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("name", product.getName());
        hm.put("date", Dates.dateTimeToString(product.getDate()));
        hm.put("period", Integer.toString(period));
        hm.put("room", product.getRoom().getName());
        hm.put("duration", product.getDuration());
        hm.put("instructor",product.getInstructor());
        hm.put("description", product.getDescription());

        mfidb.put(product.getTable(), hm);
        mfidb.close();
    }

    /**
     * Usuwa z wpis odpowiadający obiektowi PlanedActivity
     * @param product
     */
    public void delete(PlanedActivity product) {
        mfidb.delete(product.getTable(), product.id);
        mfidb.close();
    }


    public Cursor getActivity(String table, int id){
        SQLiteDatabase db=mfidb.getReadableDatabase();
        String where="_id="+id;
        Cursor c=db.query(table, null, where, null, null, null, null, null );
        c.moveToFirst();
        db.close();
        return c;
    }

    public Classroom getClassroom(String name){
        SQLiteDatabase db = mfidb.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String table="ROOMS";

        String where="name LIKE '"+name+"'";
        Cursor c=db.query(table, null, where, null, null, null, null, null );
        c.moveToFirst();
        int id= c.getInt(c.getColumnIndex("_id"));
        int X = c.getInt(c.getColumnIndex("coor_x"));
        int Y = c.getInt(c.getColumnIndex("coor_y"));
        int[] coords={X, Y};
        int level=c.getInt(c.getColumnIndex("level"));
        db.close();
        c.close();
        return new Classroom(id, name, coords , level);
    }



    /** DO SPRAWDZENIA
     * Zwraca współrzędne sali o nazwie name
     * @param name
     * @return
     */
    public int[] getXY(String name) {
        SQLiteDatabase db=mfidb.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String table="ROOMS";
        String where="name LIKE '"+name.trim()+"'";
        String[] column={"coor_x", "coor_y"};
        Cursor c=db.query(table, column, where, null, null, null, null, null );
        c.moveToFirst();
        int X = c.getInt(c.getColumnIndex("coor_x"));
        int Y = c.getInt(c.getColumnIndex("coor_y"));
        db.close();
        return new int[]{X, Y};
    }

    /** DO SPRAWDZENIA
     * Zwraca pary (nazwa_pokoju, współrzędne) z całego piętra
     * @param i numer piętra z którego należy przeczytać pokoje
     * @return
     */
    public HashMap<String, int[]> getLevelXY(int i) {
        HashMap output = new HashMap();
        SQLiteDatabase db=mfidb.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String table="ROOMS";
        String where="level="+ i;
        String[] column={"name", "coor_x", "coor_y"};

        Cursor c=db.query(table, column, where, null, null, null, null, null );
        c.moveToFirst();
        String name;
        while(!c.isAfterLast()) {
           name=c.getString(c.getColumnIndex("name"));
            output.put(name, getXY(name));
            c.moveToNext();
        }
        return output;
    }

    /**
     * Daje haszmapę (nazwa_pokoju, współrzędne) dla każdego piętra;
     * @return
     */
    public HashMap<String, int[]>[] getAllRooms() {
        HashMap[] output = new HashMap[]{getLevelXY(1), getLevelXY(2), getLevelXY(3)};
        return output;
    }


    /**
     * Zwraca tablice kursorów, która zawiera wszystkie zajęcia dla podanej daty i typów zajęć.
     * @param date
     * @param tables
     * @return
     */

    public Cursor[] getDayActivities(String date, String[] tables){
        Cursor[] output=new Cursor[tables.length];
        SQLiteDatabase db = mfidb.getReadableDatabase();
        String day = Dates.dateToString(date);
        String selection = "date LIKE '" + day + "%'";
        Cursor queries;
        for (int i = 0; i < tables.length; i++) {
            queries = db.query(tables[i], null, selection, null, null, null, null, null);
            queries.moveToFirst();
            output[i]=queries;
        }
        db.close();
        return output;
    }


    /**
     * Pobiera z bazy danych zajęcia odbywające się pomiędzy podanymi datami
     * @param date1
     * @param date2
     * @param tables
     * @return
     */
    public Cursor[] getWeek(String date1, String date2, String[] tables){
        Cursor[] output=new Cursor[4];
        SQLiteDatabase db = mfidb.getReadableDatabase();
        String from = Dates.dateToString(date1);
        String to=Dates.dateToString(date2);
        String selection = "date BETWEEN '" + from + "' AND '"+ to+"'";
        Cursor queries;
        for (int i = 0; i < tables.length; i++) {
            queries = db.query(tables[i], null, selection, null, null, null, null, null);
            queries.moveToFirst();
            output[i]=queries;
        }
        db.close();
        return output;
    }


    public Cursor getRoomNames() {
        SQLiteDatabase db = mfidb.getReadableDatabase();
        String sql = "select _id, name from ROOMS order by name ";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        db.close();
        return c;
    }

    public Cursor[] getEverything(){
        Cursor[] output=new Cursor[4];
        SQLiteDatabase db = mfidb.getReadableDatabase();
        String[] tables= {"EXAMS", "EXERCISES", "LECTURES", "OTHER"};

        Cursor queries;
        for (int i = 0; i < tables.length; i++) {
            queries = db.query(tables[i], null, null, null, null, null, null, null);
            queries.moveToFirst();
            output[i]=queries;
        }
        db.close();
        return output;
    }
}



