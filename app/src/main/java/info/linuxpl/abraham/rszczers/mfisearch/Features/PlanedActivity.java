package info.linuxpl.abraham.rszczers.mfisearch.Features;


import java.util.Date;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */
public abstract class PlanedActivity {
    protected Date startTime;
    protected int length;
    protected String instructor;

    abstract void add();
    abstract void remove();
    abstract void edit();
    abstract void read();
}
