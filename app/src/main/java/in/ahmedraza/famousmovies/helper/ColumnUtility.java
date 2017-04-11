package in.ahmedraza.famousmovies.helper;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by ahmedraza on 11/04/17.
 */

public class ColumnUtility {

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 180);

    }
}
