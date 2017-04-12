package in.ahmedraza.famousmovies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.ahmedraza.famousmovies.custom.MoviesCollection;
import in.ahmedraza.famousmovies.helper.ColumnUtility;
import in.ahmedraza.famousmovies.helper.NetworkStatus;
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
        final View rootview = inflater.inflate(R.layout.fragment_popular_movies, container, false);


        if (NetworkStatus.getInstance(getActivity()).isOnline()) {

            Toast.makeText(getActivity(),"You are online!!!!", Toast.LENGTH_SHORT).show();



        } else {

            Toast.makeText(getActivity(),"You are offline :(((", Toast.LENGTH_SHORT).show();

            LinearLayout linearLayout = (LinearLayout) rootview.findViewById(R.id.errorPopular);
            linearLayout.setVisibility(View.VISIBLE);


            android.support.v7.widget.RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler_view);
            recyclerView.setVisibility(View.INVISIBLE);

            TextView textView = (TextView) rootview.findViewById(R.id.errorText);
            textView.setText("Check your Internet Connection");


            //Toast t = Toast.makeText(this,"You are not online!!!!",8000).show();
            Log.v("Home", "############################You are not online!!!!");
        }

        android.support.v7.widget.AppCompatButton buttonRetry = (android.support.v7.widget.AppCompatButton) rootview.findViewById(R.id.buttonRetry);

        buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesCollection> call = apiService.getPopularMovie(API_KEY);
        String urlMovie = call.request().url().toString();

        Log.v("URL Movie", urlMovie);

        call.enqueue(new Callback<MoviesCollection>() {
            @Override
            public void onResponse(Call<MoviesCollection>call, Response<MoviesCollection> response) {

                if (response.isSuccessful()){

                ArrayList<MoviesCollection.Movies> moviesCollection = response.body().results;
                mAdapter.setItems(moviesCollection);

                }

                else{
                    LinearLayout linearLayout = (LinearLayout) rootview.findViewById(R.id.errorPopular);
                    linearLayout.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onFailure(Call<MoviesCollection>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });


        int mNoOfColumns = ColumnUtility.calculateNoOfColumns(getActivity());
        List<ItemObject> rowListItem = getAllItemList();
        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), mNoOfColumns);

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
