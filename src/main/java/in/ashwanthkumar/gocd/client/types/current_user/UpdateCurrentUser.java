package in.ashwanthkumar.gocd.client.types.current_user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateCurrentUser {
    @SerializedName("email")
    private String email;
    @SerializedName("email_me")
    private Boolean emailMe;
    @SerializedName("checkin_aliases")
    private List<String> checkinAliases;

    public UpdateCurrentUser(String email, Boolean emailMe, List<String> checkinAliases) {
        this.email = email;
        this.emailMe = emailMe;
        this.checkinAliases = checkinAliases;
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
