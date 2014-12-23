package info.linuxpl.abraham.rszczers.mfisearch.Features;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */
public class ActivityFactory {
    public PlanedActivity make(String type, int id, String date, Classroom room, int duration, String instructor,
                              String description) {
        PlanedActivity product = null;
        type = type.toUpperCase();
        if(type.equals("LECTURE")) {
            product = new LecturePlaned();
        } else if(type.equals("EXERCISE")) {
            product = new ExercisePlaned();
        } else if(type.equals("EXAM")) {
            product = new ExamPlaned();
        } else if(type.equals("OTHER")) {
            product = new OtherPlaned();
        }
        return product;
    }
}
