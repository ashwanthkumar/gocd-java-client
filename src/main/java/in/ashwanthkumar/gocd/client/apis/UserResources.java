package in.ashwanthkumar.gocd.client.apis;

import com.google.gson.reflect.TypeToken;
import in.ashwanthkumar.gocd.client.http.HttpClient;
import in.ashwanthkumar.gocd.client.types.ResultWrapper;
import in.ashwanthkumar.gocd.client.types.users.User;
import in.ashwanthkumar.gocd.client.types.users.Users;

import java.io.IOException;
import java.lang.reflect.Type;

public class UserResources {
    HttpClient client;

    public UserResources(HttpClient client) {
        this.client = client;
    }

    public Users getAllUsers() throws IOException {
        Type resultType = new TypeToken<ResultWrapper<Users>>() {
        }.getType();
        ResultWrapper<Users> result = client.getAs("/go/api/users", resultType, 3);
        return result.getValue();
    }

    public User getOneUser(String loginName) throws IOException {
        return client.getAs("/go/api/users/" + loginName, TypeToken.get(User.class).getType(), 3);
    }
}
