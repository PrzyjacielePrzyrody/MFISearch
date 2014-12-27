package info.linuxpl.abraham.rszczers.mfisearch.Features;

import java.util.Date;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */
public class ExercisePlaned extends PlanedActivity {
    private final static String TABLE = "EXERCISES";

    public ExercisePlaned(String date, Classroom room, int duration, String instructor,
                      String description){
        this.setDate(date);
        this.setRoom(room);
        this.setDuration(""+duration);
        this.setInstructor(instructor);
        setDescription(description);

    }
    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public void edit() {

    }

}
