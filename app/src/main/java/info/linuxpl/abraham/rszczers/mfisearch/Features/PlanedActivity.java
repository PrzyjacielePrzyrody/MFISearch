package info.linuxpl.abraham.rszczers.mfisearch.Features;

import java.util.Comparator;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */



public abstract class PlanedActivity {
    /**
     * Po stworzeniu obiektu tego typu powstanie też odpowiedni wpis w bazie danych.
     *
     * id  – unique id read from the db
     * name – a name of the activity
     * date – a date of the activity read from the db, YYYY-MM-DD HH:MM:SS.SSS format
     * period – how often activity is repeated, in days
     * room – a classroom corresponding to the activity
     * duration – duration of the activity expressed in format HH:MM
     * howLong – a date when activity is no longer current
     * instructor – an instructor's name
     * description – place for user's description and notes
     */

    public int id;
    protected String name;
    protected String date;
    protected Classroom room;
    protected String duration;
    protected String instructor;
    protected String description;
    private static final String TABLE = null;

    public abstract String getTable();

    /**
     * implements editing the object and corresponding entity in table
     */
    public abstract void edit();

    public String getName() { return this.name;}

    public String getDate(){ return this.date;}

    public Classroom getRoom() { return room; }

    public String getDuration() { return duration; }

    public String getDescription() { return description;}

    public String getInstructor() { return instructor; }

    public void setName(String name){ this.name=name;}

    public void setDate(String date) { this.date = date; }



    public void setRoom(Classroom room) {this.room = room;}

    public void setDuration(String duration) {this.duration = duration;}

    public void setDescription(String description) {this.description = description;}



    public void setInstructor(String instructor) {this.instructor = instructor;}



    public void setID(int i) {
        this.id = i;
    }
}
