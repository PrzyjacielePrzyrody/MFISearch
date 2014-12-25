package info.linuxpl.abraham.rszczers.mfisearch.Features.SQL;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */

import android.content.Context;

import java.util.HashMap;

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
     * Zwraca współrzędne sali o nazwie name
     * @param name
     * @return
     */
    public int[] getXY(String name) {
        int X = 0;
        int Y = 0;
        return new int[]{X, Y};
    }

    /**
     * Zwraca pary (nazwa_pokoju, współrzędne) z całego piętra
     * @param i numer piętra z którego należy przeczytać pokoje
     * @return
     */
    public HashMap<String, int[]> getLevelXY(int i) {
        HashMap output = new HashMap();

        // w jakiejś pętli pakujemy wszystkie pary do hashmapy
        output.put("nazwa_pokoju1", getXY("nazwa_pokoju1"));
        output.put("nazwa_pokoju2", getXY("nazwa_pokoju2"));
        //liczę, że tutaj pojawi się jakaś pętla…

        return output;
    };

    /**
     * Daje haszmapę (nazwa_pokoju, współrzędne) dla każdego piętra;
     * @return
     */
    public HashMap<String, int[]>[] getAllRooms() {
        HashMap[] output = new HashMap[]{getLevelXY(1), getLevelXY(2), getLevelXY(3)};
        return output;
    }

}
