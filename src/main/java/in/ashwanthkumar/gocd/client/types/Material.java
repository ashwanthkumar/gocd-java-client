package in.ashwanthkumar.gocd.client.types;

import com.google.gson.annotations.SerializedName;

public class Material {
    @SerializedName("id")
    private int id;

    // Format: "Pipeline", etc.
    @SerializedName("type")
    private String type;

    // Format: "zoo" or "git@github.com:foo/bar.git, Branch: master"
    @SerializedName("description")
    private String description;

    @SerializedName("fingerprint")
    private String fingerprint;

    public Material(int id, String type, String description, String fingerprint) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.fingerprint = fingerprint;
    }

    public Material() {
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public boolean isPipeline() {
        return type.equals("Pipeline");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Material material = (Material) o;

        if (id != material.id) return false;
        if (type != null ? !type.equals(material.type) : material.type != null) return false;
        if (description != null ? !description.equals(material.description) : material.description != null)
            return false;
        return !(fingerprint != null ? !fingerprint.equals(material.fingerprint) : material.fingerprint != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (fingerprint != null ? fingerprint.hashCode() : 0);
        return result;
    }
}
