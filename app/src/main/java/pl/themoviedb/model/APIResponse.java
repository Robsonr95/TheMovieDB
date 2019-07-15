package pl.themoviedb.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;
public class APIResponse implements Parcelable {

    @SerializedName("results")
    private List<Result> results;

    private APIResponse(Parcel in) {
        results = in.createTypedArrayList(Result.CREATOR);
    }

    public static final Creator<APIResponse> CREATOR = new Creator<APIResponse>() {
        @Override
        public APIResponse createFromParcel(Parcel in) {
            return new APIResponse(in);
        }

        @Override
        public APIResponse[] newArray(int size) {
            return new APIResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(results);
    }

    public List<Result> getResults() {
        return results;
    }
}
