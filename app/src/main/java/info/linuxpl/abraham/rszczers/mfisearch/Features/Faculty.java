package info.linuxpl.abraham.rszczers.mfisearch.Features;

import android.content.Context;

/**
 * MFISearch
 *
 * @author Rafał Szczerski
 *         <rafal.szczerski(at)gmail.com>
 */

public interface Faculty <T, E> {
    T find(E pattern, Context context);
}
