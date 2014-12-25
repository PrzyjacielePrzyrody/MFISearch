package info.linuxpl.abraham.rszczers.mfisearch.Features.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.HashMap;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */

public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "mfi.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Pobiera dane z bazy, zwraca kursor ustawiony na pierwszym wierszu.
     * @return
     */
    public Cursor getRooms() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect = {"0 _id", "name", "coor_x", "coor_y", "level"};
        String sqlTables = "ROOMS";
        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null,
                null, null, null);
        c.moveToFirst();
        return c;
    }

    public Cursor getExams() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect = {"0 _id", "date", "period", "room", "duration", "howLong", "instructor", "description"};
        String sqlTables = "EXAMS";
        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null,
                null, null, null);
        c.moveToFirst();
        return c;
    }

    public Cursor getExercises() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect = {"0 _id", "date", "period", "room", "duration", "howLong", "instructor", "description"};
        String sqlTables = "EXERCISES";
        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null,
                null, null, null);
        c.moveToFirst();
        return c;
    }

    public Cursor getLectures() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect = {"0 _id", "date", "period", "room", "duration", "howLong", "instructor", "description"};
        String sqlTables = "LECTURES";
        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null,
                null, null, null);
        c.moveToFirst();
        return c;
    }

    public Cursor getOther() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect = {"0 _id", "date", "period", "room", "duration", "howLong", "instructor", "description"};
        String sqlTables = "OTHER";
        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null,
                null, null, null);
        c.moveToFirst();
        return c;
    }

    /**
     * Wpisuje wiersze do odpowiedniej tabeli. Na razie ustawiłem, że wpisują tylko Stringi.
     * W tabeli ustawione są inne typy (nie String, ale np. INTEGER). Trzeba sprawdzić czy da się
     * w ten sposób wpisać dowolną wartość do bazy, a jeśli nie, to odpowiednio zrzutować dane
     * w tych metodach.
     * @param queryValues pary (nazwa_kolumny, wartość)
     */
    public void putExam(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", queryValues.get("date"));
        values.put("period", queryValues.get("period"));
        values.put("room", queryValues.get("room"));
        values.put("duration", queryValues.get("duration"));
        values.put("howLong", queryValues.get("howLong"));
        values.put("instructor", queryValues.get("instructor"));
        values.put("description", queryValues.get("description"));
        database.insert("EXAMS", null, values);
        database.close();
    }

    public void putLecture(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", queryValues.get("date"));
        values.put("period", queryValues.get("period"));
        values.put("room", queryValues.get("room"));
        values.put("duration", queryValues.get("duration"));
        values.put("howLong", queryValues.get("howLong"));
        values.put("instructor", queryValues.get("instructor"));
        values.put("description", queryValues.get("description"));
        database.insert("LECTURES", null, values);
        database.close();
    }

    public void putExercise(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", queryValues.get("date"));
        values.put("period", queryValues.get("period"));
        values.put("room", queryValues.get("room"));
        values.put("duration", queryValues.get("duration"));
        values.put("howLong", queryValues.get("howLong"));
        values.put("instructor", queryValues.get("instructor"));
        values.put("description", queryValues.get("description"));
        database.insert("EXERCISES", null, values);
        database.close();
    }

    public void putOther(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", queryValues.get("date"));
        values.put("period", queryValues.get("period"));
        values.put("room", queryValues.get("room"));
        values.put("duration", queryValues.get("duration"));
        values.put("howLong", queryValues.get("howLong"));
        values.put("instructor", queryValues.get("instructor"));
        values.put("description", queryValues.get("description"));
        database.insert("OTHER", null, values);
        database.close();
    }

    /**
     * Metoda powinna wyrzucać wiersz o zadanym id; trzeba sprawdzić
     * @param id
     * @return true, jeśli sie udało, false – jeśli nie
     */
    public boolean deleteExam(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("EXAMS", "_id" + "=" + id, null) > 0;
    }

    public boolean deleteLecture(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("LECTURES", "_id" + "=" + id, null) > 0;
    }

    public boolean deleteExercise(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("EXERCISES", "_id" + "=" + id, null) > 0;
    }

    public boolean deleteOther(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("OTHER", "_id" + "=" + id, null) > 0;
    }

    /**
     * modyfikacja zadanego wiersza to teraz tylko przeczytanie go, usunięcie i dodanie zmienionego
     */
}