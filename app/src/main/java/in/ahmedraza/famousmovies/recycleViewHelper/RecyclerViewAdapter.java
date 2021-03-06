package in.ahmedraza.famousmovies.recycleViewHelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.ahmedraza.famousmovies.R;
import in.ahmedraza.famousmovies.custom.MoviesCollection;

/**
 * Created by ahmedraza on 06/04/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<MovieViewHolders> {

    private ArrayList<MoviesCollection.Movies> mItems;
    private Context mContext;
    private ListActionListener mActionListener;



    public RecyclerViewAdapter(Context context, ListActionListener listener) {
        mActionListener = listener;
        mItems = new ArrayList<>();
        this.mContext = context;
    }

    @Override
    public MovieViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card_grid, null);
        MovieViewHolders rcv = new MovieViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(MovieViewHolders holder, final int position) {

        Picasso.with(mContext)
                .load(mItems.get(position).getPosterUrl())
                .placeholder(R.drawable.thumb)
                .fit().centerCrop()
                .into(holder.mThumbView);
        holder.movieName.setText(mItems.get(position).title);


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(ArrayList<MoviesCollection.Movies> items){
        mItems = items;
        notifyDataSetChanged();
    }

    public ArrayList<MoviesCollection.Movies> getItems() {
        return mItems;
    }




    public interface ListActionListener{
        void onMovieSelected(MoviesCollection.Movies movie);
    }
}

