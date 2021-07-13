package in.ashwanthkumar.gocd.client.types.admin;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Job {
  
    @SerializedName("name")
    private String name;

    @SerializedName("result")
    private String result;

    @SerializedName("state")
    private String state;

    @SerializedName("id")
    private int id;

    @SerializedName("run_instance_count")
    private int runInstanceCount;
    
    @SerializedName("timeout")
    private String timeout;
    
    @SerializedName("environment_variables")
    private List<EnvironmentVariable> environmentVariables;
    
    @SerializedName("resources")
    private List<String> resources;
    
    @SerializedName("tasks")
    private List<Task> tasks;
    
    @SerializedName("tabs")
    private List<Tab> tabs;
    
    @SerializedName("artifacts")
    private List<Artifact> artifacts;
    
    @SerializedName("elastic_profile_id")
    private String elasticProfileId;
    
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
    
    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public String getResult()
    {
      return result;
    }

    public void setResult(String result)
    {
      this.result = result;
    }

    public String getState()
    {
      return state;
    }

    public void setState(String state)
    {
      this.state = state;
    }

    public int getId()
    {
      return id;
    }

    public void setId(int id)
    {
      this.id = id;
    }

    public int getRunInstanceCount()
    {
      return runInstanceCount;
    }

    public void setRunInstanceCount(int runInstanceCount)
    {
      this.runInstanceCount = runInstanceCount;
    }

    public String getTimeout()
    {
      return timeout;
    }

    public void setTimeout(String timeout)
    {
      this.timeout = timeout;
    }

    public List<EnvironmentVariable> getEnvironmentVariables()
    {
      return environmentVariables;
    }

    public void setEnvironmentVariables(List<EnvironmentVariable> environmentVariables)
    {
      this.environmentVariables = environmentVariables;
    }

    public List<String> getResources()
    {
      return resources;
    }

    public void setResources(List<String> resources)
    {
      this.resources = resources;
    }

    public List<Task> getTasks()
    {
      return tasks;
    }

    public void setTasks(List<Task> tasks)
    {
      this.tasks = tasks;
    }

    public List<Tab> getTabs()
    {
      return tabs;
    }

    public void setTabs(List<Tab> tabs)
    {
      this.tabs = tabs;
    }

    public List<Artifact> getArtifacts()
    {
      return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts)
    {
      this.artifacts = artifacts;
    }

    public String getElasticProfileId()
    {
      return elasticProfileId;
    }

    public void setElasticProfileId(String elasticProfileId)
    {
      this.elasticProfileId = elasticProfileId;
    }

    public long getScheduledDate()
    {
      return scheduledDate;
    }

    public void setScheduledDate(long scheduledDate)
    {
      this.scheduledDate = scheduledDate;
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
