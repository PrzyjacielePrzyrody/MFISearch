package info.linuxpl.abraham.rszczers.mfisearch.Features;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */



public abstract class PlanedActivity {
    /**
     * id  – unique id read from the db
     * date – a date of the activity read from the db, YYYY-MM-DD HH:MM:SS.SSS format
     * period – a date in format MM-DD if is repeated periodically
     * room – a classroom corresponding to the activity
     * duration – duration of the activity expressed in format HH:MM
     * howLong – how long the activity gonna last in days
     * instructor – an instructor's name
     * description – place for user's description and notes
     */

    public int id;
    protected String date;
    protected String period;
    protected Classroom room;
    protected String duration;
    protected int howLong;
    protected String instructor;
    protected String description;

    /**
     * implements adding the entity corresponding to the object to table
     */
    abstract void add();

    /**
     * implements removing the entity corresponding to the object from the table
     */
    abstract void remove();

    /**
     * implements editing the object and corresponding entity in table
     */
    abstract void edit();
}
