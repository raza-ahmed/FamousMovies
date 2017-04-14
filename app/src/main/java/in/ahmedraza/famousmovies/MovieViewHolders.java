package in.ahmedraza.famousmovies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ahmedraza on 06/04/17.
 */

public class MovieViewHolders extends RecyclerView.ViewHolder {


    public TextView countryName;

    final public ImageView mThumbView;

    public MovieViewHolders(View itemView) {
        super(itemView);

        countryName = (TextView)itemView.findViewById(R.id.country_name);
        this.mThumbView = (ImageView) itemView.findViewById(R.id.country_photo);

    }

   /* @Override
    public void onClick(View view, int position) {
        MoviesCollection.Movies movies = mItems.get(position);
        Context context = itemView.getContext();
        Intent intent = new Intent(context, DetailActivity.class);
        context.startActivity(intent);
    }*/
}