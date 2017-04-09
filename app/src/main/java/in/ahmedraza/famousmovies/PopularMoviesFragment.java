package in.ahmedraza.famousmovies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMoviesFragment extends Fragment {


    public PopularMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_popular_movies, container, false);

        List<ItemObject> rowListItem = getAllItemList();
        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), 3);

        RecyclerView rView = (RecyclerView) rootview.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);

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
