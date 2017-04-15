package in.ahmedraza.famousmovies.recycleViewHelper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import in.ahmedraza.famousmovies.R;

/**
 * Created by ahmedraza on 06/04/17.
 */

public class MovieViewHolders extends RecyclerView.ViewHolder {


    public TextView movieName;

    final public ImageView mThumbView;

    public MovieViewHolders(View itemView) {
        super(itemView);

        movieName = (TextView)itemView.findViewById(R.id.country_name);
        this.mThumbView = (ImageView) itemView.findViewById(R.id.country_photo);

    }


}