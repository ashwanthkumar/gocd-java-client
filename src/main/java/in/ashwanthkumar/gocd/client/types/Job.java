package in.ashwanthkumar.gocd.client.types;

import com.google.gson.annotations.SerializedName;

public class Job {
    @SerializedName("name")
    public String name;

    @SerializedName("result")
    public String result;

    @SerializedName("state")
    public String state;

    @SerializedName("id")
    private int id;

    @SerializedName("scheduled_date")
    private long scheduledDate;

    public Job(String name, String result, String state, int id, long scheduledDate) {
        this.name = name;
        this.result = result;
        this.state = state;
        this.id = id;
        this.scheduledDate = scheduledDate;
    }

    public Job() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (id != job.id) return false;
        if (scheduledDate != job.scheduledDate) return false;
        if (name != null ? !name.equals(job.name) : job.name != null) return false;
        if (result != null ? !result.equals(job.result) : job.result != null) return false;
        return !(state != null ? !state.equals(job.state) : job.state != null);

    }

    @Override
    public int hashCode() {
        int result1 = name != null ? name.hashCode() : 0;
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (state != null ? state.hashCode() : 0);
        result1 = 31 * result1 + id;
        result1 = 31 * result1 + (int) (scheduledDate ^ (scheduledDate >>> 32));
        return result1;
    }
}
