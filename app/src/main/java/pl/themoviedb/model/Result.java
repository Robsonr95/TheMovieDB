package pl.themoviedb.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

//Klasa modelu przechowująca pojedynczy wynik zwrócony z API - tytuł, ocenę, datę oraz nazwę pliku z okładką.
public class Result implements Parcelable {

    @SerializedName("vote_average")
    private float voteAverage;

    //Title zawiera tytuł filmu, name zawiera nazwę serialu.
    @SerializedName("title")
    private String title;

    @SerializedName("name")
    private String name;

    @SerializedName("poster_path")
    private String posterPath;

    //Filmy jako release_date z datą premiery filmu.
    //Seriale jako first_air_date z datą kiedy został wypuszczony pierwszy odcinek
    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("first_air_date")
    private String firstAirDate;

    private Result(Parcel in) {
        voteAverage = in.readFloat();
        title = in.readString();
        name = in.readString();
        posterPath = in.readString();
        releaseDate = in.readString();
        firstAirDate = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(voteAverage);
        dest.writeString(title);
        dest.writeString(name);
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
        dest.writeString(firstAirDate);
    }

    public float getVoteAverage() {
        return voteAverage;
    }


    public String getTitle() {
        if (title != null)
            return title;
        else
            return name;
    }

    public String getPosterPath() {
        return posterPath;
    }


    public String getDate() {
        if (releaseDate != null)
            return releaseDate;
        else
            return firstAirDate;
    }

}
