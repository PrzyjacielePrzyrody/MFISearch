package info.linuxpl.abraham.rszczers.mfisearch.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */
public class DatabaseAdapter {
    protected static final String TAG = "DatabaseAdapter";
    private final Context mContext;
    private DatabaseHelper MFIHelper;
    private SQLiteDatabase MFIdb;

    public DatabaseAdapter(Context context) {
        this.mContext = context;
        MFIHelper = new DatabaseHelper(mContext);
        MFIdb = MFIHelper.getWritableDatabase();
    }

}
