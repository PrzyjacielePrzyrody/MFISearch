package info.linuxpl.abraham.rszczers.mfisearch.Features;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */
public class Level {
    Bitmap blueprint;
    List room = new ArrayList<Classroom>();

    ActivityFactory activity = new ActivityFactory();

    void markOn() {};
    void read() {};
}
