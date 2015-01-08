package info.linuxpl.abraham.rszczers.mfisearch.Features;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import info.linuxpl.abraham.rszczers.mfisearch.R;
import info.linuxpl.abraham.rszczers.mfisearch.Features.SQL.DatabaseAdapter;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */
public class Building implements Faculty <Bitmap, String>  {
    private Level[] floors;

    public Building(Context context) {
        this.floors = new Level[]{
            new Level(1, context),
            new Level(2, context),
        };
    }

    @Override
    /**
     * Metoda wyszukuje w bazie danych wzorca id
     */
    public Bitmap find(String id, Context context) {
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        Classroom roomToFind = adapter.getClassroom(id);
        int[] roomCoords = roomToFind.getCoords();
        // Nested functions at it's finest!
        // Tutaj generuje się plan ze strzałką skierowaną na wyszukiwaną salę
        String path = getFloors(roomToFind.getLevel()-1).markOn(context, roomCoords[0], roomCoords[1]);

        Bitmap b = null;
        //tutaj odczytuje się Bitmapa z pamięci wewnętrznej
        //szczegóły w klasie Level
        try {
            File f = new File(path, "searchresult.png");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            Log.d("Search error", "Counldn't load search data");
        }
        if(b == null) {
            Log.d("dupa", "dupa" + path);
        }

        return b;
    }

    public Level getFloors(int i) {
        return this.floors[i];
    }
}
