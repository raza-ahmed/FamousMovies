package in.ahmedraza.famousmovies.recycleViewHelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.ahmedraza.famousmovies.R;
import in.ahmedraza.famousmovies.custom.MovieReview;

/**
 * Created by ahmedraza on 15/04/17.
 */

public class ReviewViewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    private ArrayList<MovieReview.Reviews> mReviews;
    private Context mContext;


    public ReviewViewAdapter(){
        mReviews = new ArrayList<>();

    }
    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_view_holder, null);
        ReviewViewHolder rcv = new ReviewViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.reviewAuthor.setText(mReviews.get(position).author);
        holder.reviewContent.setText(mReviews.get(position).content);
    }


    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public void setItems(ArrayList<MovieReview.Reviews> items){
        mReviews = items;
        notifyDataSetChanged();
    }

    public ArrayList<MovieReview.Reviews> getItems() {
        return mReviews;
    }
}
