package in.ahmedraza.famousmovies.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.ahmedraza.famousmovies.R;
import in.ahmedraza.famousmovies.custom.MovieReview;
import in.ahmedraza.famousmovies.custom.MoviesCollection;
import in.ahmedraza.famousmovies.custom.VideoCollection;
import in.ahmedraza.famousmovies.helper.Constants;
import in.ahmedraza.famousmovies.recycleViewHelper.ReviewViewAdapter;
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
public class DetailsFragment extends Fragment {



    private MoviesCollection.Movies mMovies;
    private ImageView mPosterImageView;
    private TextView mMovieRatingText;
    private TextView mReleaseDateText;
    private TextView mMovieTitle;
    private TextView mMovieDescription;
    private  RecyclerView recyclerView;
    private ReviewViewAdapter mReviewAdapter;
    private ArrayList<MovieReview.Reviews> mReviewsArrayList;
    private ArrayList<VideoCollection.Videos> mVideoArrayList;
    private RelativeLayout mVideoBackdrop;
    private ImageView backdropImg;
    private FloatingActionButton mFab;

    private android.support.v7.widget.AppCompatRatingBar mRatingBar;

    private TextView mMovieOverviewText;

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
        mMovieDescription = (TextView) rootview.findViewById(R.id.movie_description);
        mRatingBar = (android.support.v7.widget.AppCompatRatingBar) rootview.findViewById(R.id.rating);

        loadReviews();
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler_review_view);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        mReviewAdapter = new ReviewViewAdapter();
        recyclerView.setAdapter(mReviewAdapter);


        Picasso.with(getActivity())
                .load(mMovies.getPosterUrl())
                .placeholder(R.drawable.movie_placeholder)
                .into(mPosterImageView);


        float voteStars = mMovies.voteAverage/2;
        mMovieRatingText.setText(Float.toString(mMovies.voteAverage));
        mReleaseDateText.setText(mMovies.releasaeDate);
        mMovieTitle.setText(mMovies.title);
        mMovieDescription.setText(mMovies.overview);
        mRatingBar.setRating(voteStars);


        mVideoBackdrop = (RelativeLayout) rootview.findViewById(R.id.video_backdrop);
        backdropImg = (ImageView) rootview.findViewById(R.id.toolbarImage);

        Picasso.with(getActivity())
                .load(mMovies.getBackdropUrl())
                .placeholder(R.drawable.thumb)
                .into(backdropImg);



        mVideoBackdrop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                loadVideo();
                //Toast.makeText(DetailsActivity.this, "I will launch Youtube video dude", Toast.LENGTH_SHORT).show();
            }
        });

        mFab = (FloatingActionButton) rootview.findViewById(R.id.favourite_button);
        mFab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "I will save your favourite Image :)", Toast.LENGTH_SHORT).show();
            }
        });



        final Toolbar toolbar = (Toolbar) rootview.findViewById(R.id.toolbarDetail);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");


        return rootview;
    }



    public void loadReviews(){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MovieReview> call = apiService.getMovieReview(mMovies.id, API_KEY);
        String urlReview = call.request().url().toString();

        Log.v("URLReview", urlReview);

        call.enqueue(new Callback<MovieReview>() {
            @Override
            public void onResponse(Call<MovieReview> call, Response<MovieReview> response) {

                if (response.isSuccessful()) {

                    mReviewsArrayList = response.body().results;

                    if (mReviewsArrayList.size() != 0) {
                        mReviewAdapter.setItems(mReviewsArrayList);
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



    public void loadVideo(){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<VideoCollection> call = apiService.getMovieVideo(mMovies.id, API_KEY);
        String urlReview = call.request().url().toString();

        Log.v("URLReview", urlReview);

        call.enqueue(new Callback<VideoCollection>() {
            @Override
            public void onResponse(Call<VideoCollection> call, Response<VideoCollection> response) {

                if (response.isSuccessful()) {

                    mVideoArrayList = response.body().results;
                    mVideoArrayList.get(0);
                    String youtubeKey = mVideoArrayList.get((0)).key;
                    String youtubeURL = "http://www.youtube.com/watch?v="+ youtubeKey;
                    Uri uri = Uri.parse(youtubeURL);

                    if (mVideoArrayList.get((0)).site.equals("YouTube")){

                        uri = Uri.parse("vnd.youtube:"  + uri.getQueryParameter("v"));
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

                        //Toast.makeText(DetailsActivity.this, youtubeURL, Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(getActivity(), "There is no Youtube video", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.e("Missed the network call", "Sorry");
                }

            }

            @Override
            public void onFailure(Call<VideoCollection> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }


}
