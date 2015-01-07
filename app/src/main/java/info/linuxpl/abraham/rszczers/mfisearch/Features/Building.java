package info.linuxpl.abraham.rszczers.mfisearch.Features;

import android.content.Context;

import info.linuxpl.abraham.rszczers.mfisearch.Features.SQL.DatabaseAdapter;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */
public class Building implements Faculty <int[], String>  {
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
    public int[] find(String id, Context context) {
        DatabaseAdapter adapter = new DatabaseAdapter(context);
        Classroom roomToFind = adapter.getClassroom(id);
        int[] roomCoords = roomToFind.getCoords();
        // Nested functions at it's finest!
        // Tutaj generuje się plan ze strzałką skierowaną na wyszukiwaną salę
        getFloors(roomToFind.getLevel()).markOn(context, roomCoords[0], roomCoords[1]);
        return roomCoords;
    }

    public Level getFloors(int i) {
        return this.floors[i];
    }
}
