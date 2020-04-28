package in.ashwanthkumar.gocd.client.examples;

import com.google.gson.Gson;

public class Jsons {
    public static String toJson(Object x) {
        return new Gson().toJson(x);
    }
}
