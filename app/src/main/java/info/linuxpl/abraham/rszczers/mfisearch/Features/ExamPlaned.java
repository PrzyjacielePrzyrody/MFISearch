package info.linuxpl.abraham.rszczers.mfisearch.Features;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */
public class ExamPlaned extends PlanedActivity {
    private final static String TABLE = "EXAMS";

    public ExamPlaned(String date, String name, Classroom room, String duration, String instructor,
                              String description){
        this.setName(name);
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
