package in.ahmedraza.famousmovies.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ahmedraza on 09/04/17.
 */

public class ApiClient {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";

   // private static final String KEY = "7b68fe1fe71d23838afc32790bd1c939";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit==null) {
             retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }



}
