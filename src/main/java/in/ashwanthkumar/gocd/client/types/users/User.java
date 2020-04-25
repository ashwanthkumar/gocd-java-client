package in.ashwanthkumar.gocd.client.types.users;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("login_name")
    private String loginName;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("enabled")
    private Boolean enabled;
    @SerializedName("email")
    private String email;
    @SerializedName("checkin_aliases")
    private List<String> checkinAliases;
    @SerializedName("admin")
    private Boolean admin;
    @SerializedName("roles")
    private List<UserRole> roles;

    public String getLoginName() {
        return loginName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getCheckinAliases() {
        return checkinAliases;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public List<UserRole> getRoles() {
        return roles;
    }
}
