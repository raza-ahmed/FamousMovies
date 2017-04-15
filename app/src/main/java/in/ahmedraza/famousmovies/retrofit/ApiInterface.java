package in.ahmedraza.famousmovies.retrofit;

import in.ahmedraza.famousmovies.custom.MovieReview;
import in.ahmedraza.famousmovies.custom.MoviesCollection;
import in.ahmedraza.famousmovies.custom.VideoCollection;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ahmedraza on 09/04/17.
 */

public interface ApiInterface {


    @GET("/3/movie/popular")
    Call<MoviesCollection> getPopularMovie(@Query("api_key") String apiKey);


    @GET("/3/movie/top_rated")

    Call<MoviesCollection> getTopRatedMovie(@Query("api_key") String apiKey);


    @GET("movie/{id}/reviews")
    Call<MovieReview> getMovieReview(@Path("id") Integer id, @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<VideoCollection> getMovieVideo(@Path("id") Integer id, @Query("api_key") String apiKey);


}

