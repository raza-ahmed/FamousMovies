package in.ahmedraza.famousmovies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ahmedraza on 15/04/17.
 */

public class ReviewViewHolder extends RecyclerView.ViewHolder {

    public TextView reviewAuthor;
    public TextView reviewContent;

    public ReviewViewHolder(View itemView) {
        super(itemView);

        reviewAuthor = (TextView)itemView.findViewById(R.id.review_author);
        reviewContent = (TextView)itemView.findViewById(R.id.review_content);


    }

}
