package in.ahmedraza.famousmovies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.ahmedraza.famousmovies.custom.MovieReview;
import in.ahmedraza.famousmovies.custom.MoviesCollection;
import in.ahmedraza.famousmovies.helper.Constants;
import in.ahmedraza.famousmovies.retrofit.ApiClient;
import in.ahmedraza.famousmovies.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.ahmedraza.famousmovies.helper.Constants.API_KEY;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieInfo extends Fragment {

    private TextView mMovieInfo;
    private MoviesCollection.Movies mMovies;
    private ArrayList<MoviesCollection.Movies> mReviews;
    private int movieId;

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


      movieId =  mMovies.id;

        loadReviews();
        return rootview;
    }




    public void loadReviews(){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MovieReview> call = apiService.getMovieReview(movieId, API_KEY);
        String urlReview = call.request().url().toString();

        Log.v("URL Review", urlReview);

        call.enqueue(new Callback<MovieReview>() {
            @Override
            public void onResponse(Call<MovieReview> call, Response<MovieReview> response) {

                if (response.isSuccessful()) {

                    Toast.makeText(getActivity(), "You did it", Toast.LENGTH_SHORT).show();


                } else {
                            Log.e("Missed the network call", "Sorry");
                }

            }

            @Override
            public void onFailure(Call<MovieReview> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

}
