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
     * period – a date in format MM-DD if is repeated periodically
     * room – a classroom corresponding to the activity
     * duration – duration of the activity expressed in format HH:MM
     * howLong – how long the activity gonna last in days
     * instructor – an instructor's name
     * description – place for user's description and notes
     */

    public int id;
    public String name;
    protected String date;
    protected String period;
    protected Classroom room;
    protected String duration;
    protected int howLong;
    protected String instructor;
    protected String description;
    private static final String TABLE = null;

    public abstract String getTable();

    /**
     * implements editing the object and corresponding entity in table
     */
    public abstract void edit();

    public String getName() { return name;}

    public String getDate(){
        return this.date;
    }

    public String getPeriod() { return this.period;}

    public Classroom getRoom() { return room; }

    public String getDuration() { return duration; }

    public String getDescription() { return description;}

    public int getHowLong() {  return howLong;}

    public String getInstructor() { return instructor; }

    public void setName(String name){ this.name=name;}

    public void setDate(String date) { this.date = date; }

    public void setPeriod(String period) { this.period = period;}

    public void setRoom(Classroom room) {this.room = room;}

    public void setDuration(String duration) {this.duration = duration;}

    public void setDescription(String description) {this.description = description;}

    public void setHowLong(int howLong) {this.howLong = howLong; }

    public void setInstructor(String instructor) {this.instructor = instructor;}



    public void setID(int i) {
        this.id = i;
    }
}
