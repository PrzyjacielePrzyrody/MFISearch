package info.linuxpl.abraham.rszczers.mfisearch.Features;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import info.linuxpl.abraham.rszczers.mfisearch.R;
/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */
public class Level {
    Bitmap blueprint;

    public Level(int floor, Context context) {
        switch(floor) {
            case 1:
                this.blueprint = BitmapFactory.decodeResource(context.getResources(), R.drawable.level1);
                break;
            case 2:
                this.blueprint = BitmapFactory.decodeResource(context.getResources(), R.drawable.level2);
                break;
        }
    }

    public String markOn(Context context, int x, int y) {
        Bitmap src = getBlueprint();
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, src.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, null);

        Bitmap waterMark = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow);
        canvas.drawBitmap(waterMark, x, y, null);

        return saveToInternalStorage(result, context);
    };

    private String saveToInternalStorage(Bitmap bitmapImage, Context context){
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath = new File(directory,"searchresult.png");
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(mypath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("save", directory.getAbsolutePath());
        return directory.getAbsolutePath();
    }



    void read() {};

    public Bitmap getBlueprint() {
        return blueprint;
    }
}
