package in.ashwanthkumar.gocd.client.types.users;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class UserRole {
    @SerializedName("type")
    private String type;
    @SerializedName("name")
    private String name;

    public UserRole(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return getType().equals(userRole.getType()) &&
                getName().equals(userRole.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getName());
    }
}
