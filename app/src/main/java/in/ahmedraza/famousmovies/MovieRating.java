package in.ahmedraza.famousmovies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static android.content.ContentValues.TAG;
import static in.ahmedraza.famousmovies.helper.Constants.API_KEY;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieRating extends Fragment {

    private MoviesCollection.Movies mMovies;
    private ArrayList<MovieReview.Reviews> mReviewsArrayList;
    private MovieReview.Reviews mMovieReview;
    private int movieId;
    private ReviewViewAdapter mAdapter;
    private  RecyclerView recyclerView;




    public MovieRating() {
        // Required empty public constructor
    }

    public static MovieRating getInstance(MoviesCollection.Movies movies){

        MovieRating fragment = new MovieRating();
        Bundle args = new Bundle();
        args.putParcelable(Constants.MOVIE_ARG, movies);
        fragment.setArguments(args);
        return fragment;
    }

    /*@Override
    public void onStart(){
        super.onStart();

        Log.v("IDMovie", String.valueOf(movieId));

        loadReviews();


     //   int x = mReviewsArrayList.size();
      //  Log.w("author", String.valueOf(x));

    }*/


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mMovies = getArguments().getParcelable(Constants.MOVIE_ARG);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_movie_rating, container, false);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler_review_view);
        movieId =  mMovies.id;
        loadReviews();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ReviewViewAdapter();
        recyclerView.setAdapter(mAdapter);


        return rootview;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(Constants.REVIEW_ARG, mAdapter.getItems());
        super.onSaveInstanceState(outState);
    }


    public void loadReviews(){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MovieReview> call = apiService.getMovieReview(movieId, API_KEY);
        String urlReview = call.request().url().toString();

        Log.v("URLReview", urlReview);

        call.enqueue(new Callback<MovieReview>() {
            @Override
            public void onResponse(Call<MovieReview> call, Response<MovieReview> response) {

                if (response.isSuccessful()) {

                    mReviewsArrayList = response.body().results;

                    if (mReviewsArrayList.size() != 0) {
                        mAdapter.setItems(mReviewsArrayList);
                    }
                    else{
                        Toast.makeText(getActivity(), "No reviews here", Toast.LENGTH_SHORT).show();
                    }
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
