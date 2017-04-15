package in.ahmedraza.famousmovies;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.ahmedraza.famousmovies.custom.MoviesCollection;
import in.ahmedraza.famousmovies.helper.ColumnUtility;
import in.ahmedraza.famousmovies.helper.Constants;
import in.ahmedraza.famousmovies.helper.NetworkStatus;
import in.ahmedraza.famousmovies.helper.RecyclerItemClickListener;
import in.ahmedraza.famousmovies.retrofit.ApiClient;
import in.ahmedraza.famousmovies.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMoviesFragment extends Fragment {

    private static final String TAG = PopularMoviesFragment.class.getSimpleName();
    private MoviesCollection movies;
    private LinearLayout mLinearLayout;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private TextView mTextview;
    private ProgressBar mProgressBar;
    private ArrayList<MoviesCollection.Movies> moviesCollection;
    private RecyclerViewAdapter.ListActionListener mActionListener;

    public PopularMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();
        loadData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_popular_movies, container, false);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.recycler_view);
        mRecyclerView.setNestedScrollingEnabled(false);
        mLinearLayout = (LinearLayout) rootview.findViewById(R.id.errorPopular);
        mTextview = (TextView) rootview.findViewById(R.id.errorText);
        mProgressBar = (ProgressBar) rootview.findViewById(R.id.progress_bar);

        AppCompatButton buttonRetry = (AppCompatButton) rootview.findViewById(R.id.buttonRetry);
        buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Trying to Reconnect", Toast.LENGTH_SHORT).show();
                loadData();
            }
        });

        loadData();

        int mNoOfColumns = ColumnUtility.calculateNoOfColumns(getActivity());
        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), mNoOfColumns);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(lLayout);
        mAdapter = new RecyclerViewAdapter(getActivity(), mActionListener);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        MoviesCollection.Movies movies = moviesCollection.get(position);
                        Intent intent = new Intent(getActivity(), DetailsActivity.class);
                        intent.putExtra(Constants.MOVIE_ARG, movies);
                        getActivity().startActivity(intent);

                    }
                })
        );

        return rootview;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(Constants.ARG_ITEMS, mAdapter.getItems());
        super.onSaveInstanceState(outState);
    }

    private void loadData() {


        if (NetworkStatus.getInstance(getActivity()).isOnline()) {
            mProgressBar.setVisibility(View.VISIBLE);
            mLinearLayout.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);

        } else {

            Toast.makeText(getActivity(), "You are offline :(((", Toast.LENGTH_SHORT).show();

            mLinearLayout.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
            mTextview.setText(R.string.internet_eror_message);

            //Toast t = Toast.makeText(this,"You are not online!!!!",8000).show();
            Log.v("Home", "You are not offline!!!!");
        }


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesCollection> call = apiService.getPopularMovie(Constants.API_KEY);
        String urlMovie = call.request().url().toString();

        Log.v("URLMovie", urlMovie);

        call.enqueue(new Callback<MoviesCollection>() {
            @Override
            public void onResponse(Call<MoviesCollection> call, Response<MoviesCollection> response) {

                if (response.isSuccessful()) {

                    moviesCollection = response.body().results;
                    mAdapter.setItems(moviesCollection);
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mLinearLayout.setVisibility(View.INVISIBLE);
                    mRecyclerView.setVisibility(View.VISIBLE);

                } else {

                    mLinearLayout.setVisibility(View.VISIBLE);
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mRecyclerView.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onFailure(Call<MoviesCollection> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });


    }


}
