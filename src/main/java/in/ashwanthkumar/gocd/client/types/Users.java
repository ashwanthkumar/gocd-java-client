package in.ashwanthkumar.gocd.client.types;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Users {
    @SerializedName("users")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public int sizeOfUsers() {
        return users.size();
    }
}
