package in.ashwanthkumar.gocd.client.apis;

import com.google.gson.reflect.TypeToken;
import in.ashwanthkumar.gocd.client.http.HttpClient;
import in.ashwanthkumar.gocd.client.types.current_user.CurrentUser;
import in.ashwanthkumar.gocd.client.types.current_user.UpdateCurrentUser;

import java.io.IOException;

public class CurrentUserResources {
    HttpClient client;

    public CurrentUserResources(HttpClient client) {
        this.client = client;
    }

    public CurrentUser get() throws IOException {
        return client.getAs("/go/api/current_user", TypeToken.get(CurrentUser.class).getType(), 1);
    }

    public CurrentUser update(UpdateCurrentUser updateCurrentUser) throws IOException {
        return client.patchAs("/go/api/current_user", TypeToken.get(CurrentUser.class).getType(), 1, updateCurrentUser);
    }

}
