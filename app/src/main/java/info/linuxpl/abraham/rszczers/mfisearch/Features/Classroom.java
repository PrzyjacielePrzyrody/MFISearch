package info.linuxpl.abraham.rszczers.mfisearch.Features;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */
public class Classroom{
    private int id;
    private String name;
    private int[] coords;
    private int level;

    public String getName() {
        return name;
    }

    public Classroom(int id, String name, int[] coords, int level){
        this.id=id;
        this.name=name;
        this.coords=coords;
        this.level=level;
    }
}
