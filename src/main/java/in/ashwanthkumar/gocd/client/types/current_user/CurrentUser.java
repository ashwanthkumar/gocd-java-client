package in.ashwanthkumar.gocd.client.types.current_user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentUser {
    @SerializedName("login_name")
    private String loginName;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("enabled")
    private Boolean enabled;
    @SerializedName("email")
    private String email;
    @SerializedName("email_me")
    private Boolean emailMe;
    @SerializedName("checkin_aliases")
    private List<String> checkinAliases;

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

    public Boolean getEmailMe() {
        return emailMe;
    }

    public List<String> getCheckinAliases() {
        return checkinAliases;
    }
}
