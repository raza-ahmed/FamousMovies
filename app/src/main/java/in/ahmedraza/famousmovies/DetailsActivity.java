package in.ahmedraza.famousmovies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import in.ahmedraza.famousmovies.custom.MoviesCollection;
import in.ahmedraza.famousmovies.helper.Constants;
import in.ahmedraza.famousmovies.helper.ViewPagerAdapter;

/**
 * Created by ahmedraza on 14/04/17.
 */

public class DetailsActivity extends AppCompatActivity {


    private RelativeLayout mVideoBackdrop;
    private FloatingActionButton mFab;
    private ImageView backdopImg;
    MovieInfo mMovieInfoFragment;
    MovieRating mMovieReviewFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        MoviesCollection.Movies movies;
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
                Toast.makeText(DetailsActivity.this, "I will launch Youtube video dude", Toast.LENGTH_SHORT).show();
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
        adapter.addFragment(mMovieReviewFragment, "Rating");
        viewPager.setAdapter(adapter);

    }

}
