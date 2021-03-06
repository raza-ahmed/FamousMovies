package in.ahmedraza.famousmovies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import in.ahmedraza.famousmovies.fragments.FavouriteMoviesFragment;
import in.ahmedraza.famousmovies.fragments.PopularMoviesFragment;
import in.ahmedraza.famousmovies.fragments.TopratedMoviesFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView mBottomNav;
    private final static String API_KEY = "7b68fe1fe71d23838afc32790bd1c939";
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PopularMoviesFragment popularMoviesFragment = new PopularMoviesFragment();
        fragmentTransaction.replace(R.id.fragment_container, popularMoviesFragment, "HELLO");
        fragmentTransaction.commit();


        mBottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switchMovieFragment(item);
                return true;
            }
        });


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share_app) {

            Toast.makeText(MainActivity.this, "Sharing App", Toast.LENGTH_SHORT).show();
            // Handle the camera action
        } else if (id == R.id.nav_rate_app) {
            Toast.makeText(MainActivity.this, "Rating App", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_invite_friends) {
            Toast.makeText(MainActivity.this, "Inviting Friends", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_suggest_improvements) {
            Toast.makeText(MainActivity.this, "Suggesting Improvements", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_terms_of_use) {

            Toast.makeText(MainActivity.this, "Terms of use nobody reads", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_privacy_policy) {

            Toast.makeText(MainActivity.this, "Privacy Policy is scary", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void switchMovieFragment(MenuItem mItem){

        android.support.v4.app.Fragment mFragment = null;

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        int id = mItem.getItemId();

        if (id==R.id.menu_popular){
            mFragment = new PopularMoviesFragment();
            ft.replace(R.id.fragment_container, mFragment);
            ft.addToBackStack(null);
            ft.commit();
        }
        else if (id==R.id.menu_favourites){
            Toast.makeText(MainActivity.this, "Favourites and boring", Toast.LENGTH_SHORT).show();
            mFragment = new FavouriteMoviesFragment();
            ft.replace(R.id.fragment_container, mFragment);
            ft.addToBackStack(null);
            ft.commit();
        }

        else if (id==R.id.menu_rated){
            Toast.makeText(MainActivity.this, "Rated and marked®", Toast.LENGTH_SHORT).show();
            mFragment = new TopratedMoviesFragment();
            ft.replace(R.id.fragment_container, mFragment);
            ft.addToBackStack(null);
            ft.commit();
        }



    }


}
