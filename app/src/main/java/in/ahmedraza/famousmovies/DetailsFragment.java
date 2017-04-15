package in.ahmedraza.famousmovies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import in.ahmedraza.famousmovies.custom.MoviesCollection;
import in.ahmedraza.famousmovies.helper.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {



    private MoviesCollection.Movies mMovies;
    private ImageView mPosterImageView;
    private TextView mMovieRatingText;
    private TextView mReleaseDateText;
    private TextView mMovieTitle;


    private TextView mMovieOverviewText;
    private MovieInfo movieInfo = new MovieInfo();
    private MovieRating movieRating = new MovieRating();

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment getInstance(MoviesCollection.Movies movies){

        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.MOVIE_ARG, movies);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mMovies = getArguments().getParcelable(Constants.MOVIE_ARG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_details, container, false);

        mPosterImageView = (ImageView) rootview.findViewById(R.id.poster_image);
        mReleaseDateText = (TextView) rootview.findViewById(R.id.movie_releasedate);
        mMovieRatingText = (TextView) rootview.findViewById(R.id.movie_rating);
        mMovieTitle = (TextView) rootview.findViewById(R.id.movie_title);

        Picasso.with(getActivity())
                .load(mMovies.getPosterUrl())
                .placeholder(R.drawable.movie_placeholder)
                .into(mPosterImageView);


        mMovieRatingText.setText(Float.toString(mMovies.voteAverage));
        mReleaseDateText.setText(mMovies.releasaeDate);
        mMovieTitle.setText(mMovies.title);



        return rootview;
    }




}
