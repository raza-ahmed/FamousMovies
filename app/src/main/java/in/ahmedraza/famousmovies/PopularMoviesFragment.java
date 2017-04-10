package in.ahmedraza.famousmovies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.ahmedraza.famousmovies.custom.MoviesCollection;
import in.ahmedraza.famousmovies.retrofit.ApiClient;
import in.ahmedraza.famousmovies.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMoviesFragment extends Fragment {


    private final static String API_KEY = "7b68fe1fe71d23838afc32790bd1c939";
    private static final String TAG = FavouriteMoviesFragment.class.getSimpleName();

    private MoviesCollection movies;
    private RecyclerViewAdapter mAdapter;
    private RecyclerViewAdapter.ListActionListener mActionListener;
    public PopularMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_popular_movies, container, false);


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesCollection> call = apiService.getPopularMovie(API_KEY);
        String urlMovie = call.request().url().toString();

        Log.v("URL Movie", urlMovie);

        call.enqueue(new Callback<MoviesCollection>() {
            @Override
            public void onResponse(Call<MoviesCollection>call, Response<MoviesCollection> response) {



                ArrayList<MoviesCollection.Movies> moviesCollection = response.body().results;
                mAdapter.setItems(moviesCollection);
                // String result = response.body().getPosterUrl();

               /* ArrayList<MoviesCollection.Movies> newresult = response.body().results;
                StringBuilder sb = new StringBuilder();

                for (MoviesCollection.Movies s : newresult)
                {
                    sb.append(s);
                    sb.append("\t");
                }*/
                //MoviesCollection movies = response.body().getPosterUrl();

               // Log.v("Movie list", sb.toString());
            }

            @Override
            public void onFailure(Call<MoviesCollection>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });



        List<ItemObject> rowListItem = getAllItemList();
        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), 3);

        RecyclerView rView = (RecyclerView) rootview.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        mAdapter = new RecyclerViewAdapter(getActivity(), mActionListener);
        rView.setAdapter(mAdapter);

        return rootview;
    }


    private List<ItemObject> getAllItemList(){

        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject("United States", R.drawable.thumb));
        allItems.add(new ItemObject("Canada", R.drawable.thumb));
        allItems.add(new ItemObject("United Kingdom", R.drawable.thumb));
        allItems.add(new ItemObject("Germany", R.drawable.thumb));
        allItems.add(new ItemObject("Sweden", R.drawable.thumb));
        allItems.add(new ItemObject("United Kingdom", R.drawable.thumb));
        allItems.add(new ItemObject("Germany", R.drawable.thumb));
        allItems.add(new ItemObject("Sweden", R.drawable.thumb));
        allItems.add(new ItemObject("United States", R.drawable.thumb));
        allItems.add(new ItemObject("Canada", R.drawable.thumb));
        allItems.add(new ItemObject("United Kingdom", R.drawable.thumb));
        allItems.add(new ItemObject("Germany", R.drawable.thumb));
        allItems.add(new ItemObject("Sweden", R.drawable.thumb));
        allItems.add(new ItemObject("United Kingdom", R.drawable.thumb));
        allItems.add(new ItemObject("Germany", R.drawable.thumb));
        allItems.add(new ItemObject("Sweden", R.drawable.thumb));

        return allItems;
    }

}
