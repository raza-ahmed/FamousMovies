package in.ahmedraza.famousmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import in.ahmedraza.famousmovies.custom.MoviesCollection;
import in.ahmedraza.famousmovies.fragments.DetailsFragment;
import in.ahmedraza.famousmovies.helper.Constants;

/**
 * Created by ahmedraza on 14/04/17.
 */

public class DetailsActivity extends AppCompatActivity {

    private MoviesCollection.Movies movies;

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
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.movie_detail_container, fragment)
                .commit();


    }




}
