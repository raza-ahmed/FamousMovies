package in.ahmedraza.famousmovies;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ahmedraza on 14/04/17.
 */

public class DetailsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

       /* MoviesCollection.Movies movies;
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            movies = extras.getParcelable(DetailsFragment.MOVIE_ARG);
        }
        else {
            throw new NullPointerException("Intent extras is empty");
        }
*/

       FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DetailsFragment detailsFragment = new DetailsFragment();
        fragmentTransaction.replace(R.id.container, detailsFragment)
                .commit();




    }

}
