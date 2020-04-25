package in.ashwanthkumar.gocd.client.types.plugin;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PluginStatus {
    @SerializedName("state")
    private String state;
    @SerializedName("messages")
    private List<String> messages;

    public PluginStatus(String state, List<String> messages) {
        this.state = state;
        this.messages = messages;
    }

    public String getState() {
        return state;
    }

    public List<String> getMessages() {
        return messages;
    }
}
