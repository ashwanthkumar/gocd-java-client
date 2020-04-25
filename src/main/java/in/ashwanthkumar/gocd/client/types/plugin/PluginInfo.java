package in.ashwanthkumar.gocd.client.types.plugin;

import com.google.gson.annotations.SerializedName;

public class PluginInfo {
    @SerializedName("id")
    private String id;
    @SerializedName("status")
    private PluginStatus status;
    @SerializedName("plugin_file_location")
    private String pluginFileLocation;
    @SerializedName("bundled_plugin")
    private boolean bundledPlugin;
    @SerializedName("about")
    private PluginAbout about;
    // - extensions;


    public PluginInfo(String id, PluginStatus status, String pluginFileLocation, boolean bundledPlugin, PluginAbout about) {
        this.id = id;
        this.status = status;
        this.pluginFileLocation = pluginFileLocation;
        this.bundledPlugin = bundledPlugin;
        this.about = about;
    }

    public String getId() {
        return id;
    }

    public PluginStatus getStatus() {
        return status;
    }

    public String getPluginFileLocation() {
        return pluginFileLocation;
    }

    public boolean isBundledPlugin() {
        return bundledPlugin;
    }

    public PluginAbout getAbout() {
        return about;
    }
}
