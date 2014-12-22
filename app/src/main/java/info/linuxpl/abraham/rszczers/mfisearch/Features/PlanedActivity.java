package info.linuxpl.abraham.rszczers.mfisearch.Features;


import java.util.Date;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */
public abstract class PlanedActivity {
    protected Date date;
    protected String startTime;
    protected int length;
    protected String instructor;

    // Trzeba przeciążyć konstruktor tak, żeby parsował datę ze stringa
    // żeby było wygodniej
    public PlanedActivity(Date date, String startTime, int length, String instructor) {
        this.date = date;
        this.startTime = startTime;
        this.length = length;
        this.instructor = instructor;
    }

    abstract void add();
    abstract void remove();
    abstract void edit();
    abstract void read();
}
