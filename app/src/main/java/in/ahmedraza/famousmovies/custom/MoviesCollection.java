package in.ahmedraza.famousmovies.custom;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ahmedraza on 09/04/17.
 */

public class MoviesCollection {

    public ArrayList<Movies> results;


    public static class Movies implements Parcelable {

        @SerializedName("original_title")
        public String title;

        @SerializedName("overview")
        public String overview;

        @SerializedName("poster_path")
        public String posterPath;

        @SerializedName("vote_average")
        public Float voteAverage;

        @SerializedName("release_date")
        public String releasaeDate;



        protected Movies(Parcel in) {
            title = in.readString();
            posterPath = in.readString();
            overview = in.readString();
            voteAverage = in.readFloat();
            releasaeDate = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(posterPath);
            dest.writeString(overview);
            dest.writeFloat(voteAverage);
            dest.writeString(releasaeDate);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
            @Override
            public Movies createFromParcel(Parcel in) {
                return new Movies(in);
            }

            @Override
            public Movies[] newArray(int size) {
                return new Movies[size];
            }
        };


        public String getPosterUrl(){
            return THUMB_BASE_URL + "w185" + this.posterPath;
        }
        private final static String THUMB_BASE_URL = "http://image.tmdb.org/t/p/";
    }
}