package in.ahmedraza.famousmovies.retrofit;

import in.ahmedraza.famousmovies.custom.MoviesCollection;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ahmedraza on 09/04/17.
 */

public interface ApiInterface {


    @GET("/3/movie/popular")
    Call<MoviesCollection> getPopularMovie(@Query("api_key") String apiKey);


    @GET("/3/movie/top_rated")

    Call<MoviesCollection> getTopRatedMovie(@Query("api_key") String apiKey);


}

