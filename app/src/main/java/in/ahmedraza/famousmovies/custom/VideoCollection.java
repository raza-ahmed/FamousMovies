package in.ahmedraza.famousmovies.custom;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ahmedraza on 16/04/17.
 */

public class VideoCollection {

    public ArrayList<Videos> results;

    public static class Videos implements Parcelable{


        @SerializedName("key")
        public String key;

        @SerializedName("site")
        public String site;

        protected Videos(Parcel in) {
            key = in.readString();
            site = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(key);
            dest.writeString(site);

        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Parcelable.Creator<Videos> CREATOR = new Parcelable.Creator<Videos>() {
            @Override
            public Videos createFromParcel(Parcel in) {
                return new Videos(in);
            }

            @Override
            public Videos[] newArray(int size) {
                return new Videos[size];
            }
        };

    }
}
