package in.ashwanthkumar.gocd.client.types;

import com.google.gson.annotations.SerializedName;

public class Modification {
    @SerializedName("id")
    public int id;

    // Format: "cucumber/102/BuildAndPublish/1" for pipelines, and
    // "2d110a724f3e716f801b6e87d420d7f0c32a208f" for git commits.
    @SerializedName("revision")
    private String revision;

    @SerializedName("comment")
    private String comment;

    @SerializedName("user_name")
    private String userName;

    @SerializedName("modified_time")
    private long modificationTime;

    @SerializedName("email_address")
    private String email;

    public Modification(int id, String revision, String comment, String userName, long modificationTime, String email) {
        this.id = id;
        this.revision = revision;
        this.comment = comment;
        this.userName = userName;
        this.modificationTime = modificationTime;
        this.email = email;
    }

    public Modification() {
    }

    /*
     * Return a shortened form of the comment, or null if we have no comment.
     * Designed to reduce git commit messages to just their summary line.
     */
    public String summarizeComment() {
        if (comment == null)
            return null;

        String[] lines = comment.split("\\r?\\n");
        return lines[0];
    }

    public int getId() {
        return id;
    }

    public String getRevision() {
        return revision;
    }

    public String getComment() {
        return comment;
    }

    public String getUserName() {
        return userName;
    }

    public long getModificationTime() {
        return modificationTime;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Modification that = (Modification) o;

        if (id != that.id) return false;
        if (modificationTime != that.modificationTime) return false;
        if (revision != null ? !revision.equals(that.revision) : that.revision != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        return !(email != null ? !email.equals(that.email) : that.email != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (revision != null ? revision.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (int) (modificationTime ^ (modificationTime >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
