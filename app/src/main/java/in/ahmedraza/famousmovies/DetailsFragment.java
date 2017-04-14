package in.ahmedraza.famousmovies;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import in.ahmedraza.famousmovies.custom.MoviesCollection;
import in.ahmedraza.famousmovies.helper.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {



    private MoviesCollection.Movies mMovies;
    private ImageView mPosterImageView;
    private TextView mMovieRatingText;
    private TextView mReleaseDateText;
    private TextView mMovieTitle;
    private TextView mMovieOverviewText;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment getInstance(MoviesCollection.Movies movies){

        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.MOVIE_ARG, movies);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mMovies = getArguments().getParcelable(Constants.MOVIE_ARG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_details, container, false);

        mPosterImageView = (ImageView) rootview.findViewById(R.id.poster_image);
        mReleaseDateText = (TextView) rootview.findViewById(R.id.movie_releasedate);
        mMovieRatingText = (TextView) rootview.findViewById(R.id.movie_rating);
        mMovieTitle = (TextView) rootview.findViewById(R.id.movie_title);

        Picasso.with(getActivity())
                .load(mMovies.getPosterUrl())
                .placeholder(R.drawable.movie_placeholder)
                .into(mPosterImageView);


        mMovieRatingText.setText(Float.toString(mMovies.voteAverage));
        mReleaseDateText.setText(mMovies.releasaeDate);
        mMovieTitle.setText(mMovies.title);


        ViewPager viewPager = (ViewPager) rootview.findViewById(R.id.pager);
        setupViewPager(viewPager);


        TabLayout tabLayout = (TabLayout) rootview.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return rootview;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new MovieInfo(), "Info");
        adapter.addFragment(new MovieRating(), "Rating");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
