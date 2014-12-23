package info.linuxpl.abraham.rszczers.mfisearch.Features;

import java.util.ArrayList;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */
public class Classroom implements Faculty <Integer[], String> {
    private String id;
    private float[] coords;
    Level level;
    private ArrayList <PlanedActivity> planedHere;

    @Override
    /**
     * Metoda wyszukuje w bazie danych wzorca id
     */
    public Integer[] find(String id) {
        return null;
    }
}
