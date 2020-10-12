package in.ashwanthkumar.gocd.client.types.admin;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import in.ashwanthkumar.gocd.client.types.admin.Tab;
import in.ashwanthkumar.gocd.client.types.admin.Task;

public class Job {
  
    @SerializedName("name")
    public String name;

    @SerializedName("result")
    public String result;

    @SerializedName("state")
    public String state;

    @SerializedName("id")
    private int id;

    @SerializedName("run_instance_count")
    private int runInstanceCount;
    
    @SerializedName("timeout")
    private String timeout;
    
    @SerializedName("environment_variables")
    public List<EnvironmentVariable> environmentVariables;
    
    @SerializedName("resources")
    public List<String> resources;
    
    @SerializedName("tasks")
    public List<Task> tasks;
    
    @SerializedName("tabs")
    public List<Tab> tabs;
    
    @SerializedName("artifacts")
    public List<Artifact> artifacts;
    
    @SerializedName("elastic_profile_id")
    public String elasticProfileId;
    
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

        Job TemplateJob = (Job) o;

        if (id != TemplateJob.id) return false;
        if (scheduledDate != TemplateJob.scheduledDate) return false;
        if (name != null ? !name.equals(TemplateJob.name) : TemplateJob.name != null) return false;
        if (result != null ? !result.equals(TemplateJob.result) : TemplateJob.result != null) return false;
        return !(state != null ? !state.equals(TemplateJob.state) : TemplateJob.state != null);

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
