package in.ashwanthkumar.gocd.client.types;

import com.google.gson.annotations.SerializedName;

public class ResultWrapper<T> {
    @SerializedName("_embedded")
    T value;

    public T getValue() {
        return value;
    }
}
