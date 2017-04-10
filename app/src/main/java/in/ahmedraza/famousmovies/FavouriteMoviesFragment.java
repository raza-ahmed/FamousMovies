package in.ahmedraza.famousmovies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import in.ahmedraza.famousmovies.custom.MoviesCollection;
import in.ahmedraza.famousmovies.retrofit.ApiClient;
import in.ahmedraza.famousmovies.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteMoviesFragment extends Fragment {

    private String API_KEY2 = "7b68fe1fe71d23838afc32790bd1c939";

    /*private final static String API_KEY = "7b68fe1fe71d23838afc32790bd1c939";
    private static final String TAG = FavouriteMoviesFragment.class.getSimpleName();*/
    public FavouriteMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_favourite_movies, container, false);


        Button button = (Button) rootview.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);

                Call<MoviesCollection> call = apiService.getPopularMovie(API_KEY2);
                String urlMovie = call.request().url().toString();

                Log.v("URL Movie", urlMovie);

                call.enqueue(new Callback<MoviesCollection>() {
                    @Override
                    public void onResponse(Call<MoviesCollection>call, Response<MoviesCollection> response) {

                        // String result = response.body().getPosterUrl();

                        ArrayList<MoviesCollection.Movies> newresult = response.body().results;
                        StringBuilder sb = new StringBuilder();

                        for (MoviesCollection.Movies s : newresult)
                        {
                            sb.append(s);
                            sb.append("\t");
                        }
                        //MoviesCollection movies = response.body().getPosterUrl();

                        Log.v("Movie list", sb.toString());
                    }

                    @Override
                    public void onFailure(Call<MoviesCollection>call, Throwable t) {
                        // Log error here since request failed
                        Log.e(TAG, t.toString());
                    }
                });


            }
        });

        return rootview;
    }

}
