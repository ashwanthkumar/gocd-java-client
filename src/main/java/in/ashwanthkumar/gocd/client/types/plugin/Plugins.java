package in.ashwanthkumar.gocd.client.types.plugin;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Plugins {
    @SerializedName("plugin_info")
    private List<PluginInfo> plugins;

    public List<PluginInfo> getPlugins() {
        return plugins;
    }

    public int sizeOf() {
        return plugins.size();
    }
}
