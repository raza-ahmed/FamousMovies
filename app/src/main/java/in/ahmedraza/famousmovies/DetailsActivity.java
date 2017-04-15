package in.ahmedraza.famousmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.ahmedraza.famousmovies.custom.MoviesCollection;
import in.ahmedraza.famousmovies.custom.VideoCollection;
import in.ahmedraza.famousmovies.fragments.DetailsFragment;
import in.ahmedraza.famousmovies.fragments.MovieInfo;
import in.ahmedraza.famousmovies.fragments.MovieRating;
import in.ahmedraza.famousmovies.helper.Constants;
import in.ahmedraza.famousmovies.helper.ViewPagerAdapter;
import in.ahmedraza.famousmovies.retrofit.ApiClient;
import in.ahmedraza.famousmovies.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.ahmedraza.famousmovies.helper.Constants.API_KEY;

/**
 * Created by ahmedraza on 14/04/17.
 */

public class DetailsActivity extends AppCompatActivity {


    private RelativeLayout mVideoBackdrop;
    private FloatingActionButton mFab;
    private ImageView backdopImg;
    MovieInfo mMovieInfoFragment;
    private MoviesCollection.Movies movies;
    private ArrayList<VideoCollection.Videos> mVideoArrayList;
    MovieRating mMovieReviewFragment;
    private static final String TAG = DetailsActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            movies = extras.getParcelable(Constants.MOVIE_ARG);
        }
        else {
            throw new NullPointerException("Intent extras is empty");
        }



        DetailsFragment fragment = DetailsFragment.getInstance(movies);
        mMovieInfoFragment = MovieInfo.getInstance(movies);
        mMovieReviewFragment = MovieRating.getInstance(movies);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        mVideoBackdrop = (RelativeLayout) findViewById(R.id.video_backdrop);
        backdopImg = (ImageView) findViewById(R.id.toolbarImage);

        Picasso.with(DetailsActivity.this)
                .load(movies.getBackdropUrl())
                .placeholder(R.drawable.thumb)
                .into(backdopImg);



        mVideoBackdrop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                loadVideo();
                //Toast.makeText(DetailsActivity.this, "I will launch Youtube video dude", Toast.LENGTH_SHORT).show();
            }
        });

        mFab = (FloatingActionButton) findViewById(R.id.favourite_button);
        mFab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(DetailsActivity.this, "I will save your favourite Image :)", Toast.LENGTH_SHORT).show();
            }
        });

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
               setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                toolbar.setTitle("");

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(mMovieInfoFragment , "Info");
       // Log.w("Movie Overview coming", mMovies.overview);
        adapter.addFragment(mMovieReviewFragment, "Reviews");
        viewPager.setAdapter(adapter);

    }


    public void loadVideo(){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<VideoCollection> call = apiService.getMovieVideo(movies.id, API_KEY);
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
                        Toast.makeText(DetailsActivity.this, "There is no Youtube video", Toast.LENGTH_SHORT).show();
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
