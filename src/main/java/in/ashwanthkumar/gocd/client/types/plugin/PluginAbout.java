package in.ashwanthkumar.gocd.client.types.plugin;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PluginAbout {
    @SerializedName("name")
    private String name;
    @SerializedName("version")
    private String version;
    @SerializedName("target_go_version")
    private String targetGoVersion;
    @SerializedName("description")
    private String description;
    @SerializedName("target_operating_systems")
    private List<String> targetOS;
    // - vendor

    public PluginAbout(String name, String version, String targetGoVersion, String description, List<String> targetOS) {
        this.name = name;
        this.version = version;
        this.targetGoVersion = targetGoVersion;
        this.description = description;
        this.targetOS = targetOS;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getTargetGoVersion() {
        return targetGoVersion;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTargetOS() {
        return targetOS;
    }

}
