package in.ahmedraza.famousmovies.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.ahmedraza.famousmovies.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopratedMoviesFragment extends Fragment {


    public TopratedMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_toprated_movies, container, false);
    }

}
