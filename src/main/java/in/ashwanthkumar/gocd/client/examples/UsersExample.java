package in.ashwanthkumar.gocd.client.examples;

import com.google.gson.Gson;
import in.ashwanthkumar.gocd.client.GoCD;
import in.ashwanthkumar.gocd.client.auth.PersonalAccessTokenAuthentication;
import in.ashwanthkumar.gocd.client.types.current_user.CurrentUser;

import java.io.IOException;

public class UsersExample {
    public static void main(String[] args) throws IOException {
        String personalAccessToken = args[0];
        GoCD goCD = new GoCD("http://localhost:8153", new PersonalAccessTokenAuthentication(personalAccessToken));
        CurrentUser currentUser = goCD.currentUser().get();
        System.out.println(Jsons.toJson(currentUser));
    }
}
