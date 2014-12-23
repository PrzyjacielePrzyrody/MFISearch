package info.linuxpl.abraham.rszczers.mfisearch.Features;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */


/**
 * @param id unique id read from db
 * @param date date of activity read from db, YYYY-MM-DD HH:MM:SS.SSS format
 * @param period also date in format if repeated periodically
 * @param room classroom corresponding to activity
 * @param duration duration expressed in format HH:MM
 * @param instructor instructor's name
 * @param description place for user's description and notes
 *
 */
public abstract class PlanedActivity {
    public int id;
    protected String date;
    protected String period;
    protected Classroom room;
    protected String duration;
    protected String instructor;
    protected String description;

    abstract void add();
    abstract void remove();
    abstract void edit();
    abstract void read();
}
