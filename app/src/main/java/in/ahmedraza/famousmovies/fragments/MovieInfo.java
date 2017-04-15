package in.ahmedraza.famousmovies.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.ahmedraza.famousmovies.R;
import in.ahmedraza.famousmovies.custom.MovieReview;
import in.ahmedraza.famousmovies.custom.MoviesCollection;
import in.ahmedraza.famousmovies.helper.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieInfo extends Fragment {

    private TextView mMovieInfo;
    private MoviesCollection.Movies mMovies;
    private ArrayList<MovieReview.Reviews> mReviews;



    private static final String TAG = MovieInfo.class.getSimpleName();

    public MovieInfo() {
        // Required empty public constructor
    }



    public static MovieInfo getInstance(MoviesCollection.Movies movies){

        MovieInfo fragment = new MovieInfo();
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
        View rootview = inflater.inflate(R.layout.fragment_movie_info, container, false);
        mMovieInfo = (TextView) rootview.findViewById(R.id.movie_info);
        mMovieInfo.setText(mMovies.overview);

        return rootview;
    }






}
