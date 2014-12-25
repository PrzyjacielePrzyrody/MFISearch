package info.linuxpl.abraham.rszczers.mfisearch.Features.SQL;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */

import android.content.Context;

import java.util.HashMap;

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

    /** BARDZO WAŻNA FUNKCJA!
     * W tej funkcji chodzi o to, że podczas tworzenia nowego obiektu klasy PlanedActivity
     * nie jest od razu ustawiane pole id. Pole id obiektu MUSI odpowiadać wartości klucza głównego
     * w tabeli. Dlatego po stworzeniu obiektu i dodaniu go do bazy trzeba przeczytać jego numer id
     * w bazie i ustawić go w obiekcie. Do tego służy ta funkcja.
     * @param product
     * @return
     */
    public int getID(PlanedActivity product) {
        int id=0;
        return id;
    }

    /**
     * Dodaje do bazy dowolny obiekt PlanedActivity.
     * Trzeba tutaj napisać jakis parser
     * PlanedActivity -> HashMap; tam gdzie null powinien być HashMap
     * @param product
     */
    public void add(PlanedActivity product) {
        mfidb.put(product.getTable(), null);
    }

    /**
     * Usuwa z wpis odpowiadający obiektowi PlanedActivity
     * @param product
     */
    public void delete(PlanedActivity product) {
        mfidb.delete(product.getTable(), product.id);
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
