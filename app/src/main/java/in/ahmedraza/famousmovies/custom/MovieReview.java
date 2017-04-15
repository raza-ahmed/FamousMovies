package in.ahmedraza.famousmovies.custom;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ahmedraza on 15/04/17.
 */

public class MovieReview {

    public ArrayList<Reviews> reviewResults;

    public static class Reviews implements Parcelable {

        @SerializedName("author")
        public String author;

        @SerializedName("content")
        private String content;



        protected Reviews(Parcel in) {
            author = in.readString();
            content = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(author);
            dest.writeString(content);

        }



        @Override
        public int describeContents() {
            return 0;
        }


        public static final Parcelable.Creator<Reviews> CREATOR = new Parcelable.Creator<Reviews>() {
            @Override
            public Reviews createFromParcel(Parcel in) {
                return new Reviews(in);
            }

            @Override
            public Reviews[] newArray(int size) {
                return new Reviews[size];
            }
        };
    }

}