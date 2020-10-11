package in.ashwanthkumar.gocd.client.types.admin;

import com.google.gson.annotations.SerializedName;

import in.ashwanthkumar.gocd.client.types.admin.templates.Approval;

import java.util.List;
import java.util.stream.Collectors;

public class Stage {
  
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("counter")
    private int counter;

    @SerializedName("result")
    private String result;

    @SerializedName("approved_by")
    private String approvedBy;

    @SerializedName("fetch_materials")
    private boolean fetchMaterials;
    
    @SerializedName("clean_working_directory")
    private boolean cleanWorkingDirectory;
    
    @SerializedName("never_cleanup_artifacts")
    private boolean neverCleanupArtifacts;
    
    @SerializedName("approval")
    private Approval approval;
    
    @SerializedName("environment_variables")
    private List<EnvironmentVariable> environmentVariables;
    
    @SerializedName("jobs")
    private List<Job> jobs;

    // "approval_type"
    // "can_run"
    // "operate_permission"
    // "rerun_of_counter"
    // "scheduled"

    public Stage() {
      
    }
    
    public Stage(int id, String name, int counter, String result, String approvedBy, List<Job> jobs) {
      this.id = id;
      this.name = name;
      this.counter = counter;
      this.result = result;
      this.approvedBy = approvedBy;
      this.jobs = jobs;
  }

    public List<String> jobNames() {
        return jobs.stream().map((input) -> input.name).collect(Collectors.toList());
    }

    public int getId()
    {
      return id;
    }

    public void setId(int id)
    {
      this.id = id;
    }

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public int getCounter()
    {
      return counter;
    }

    public void setCounter(int counter)
    {
      this.counter = counter;
    }

    public String getResult()
    {
      return result;
    }

    public void setResult(String result)
    {
      this.result = result;
    }

    public String getApprovedBy()
    {
      return approvedBy;
    }

    public void setApprovedBy(String approvedBy)
    {
      this.approvedBy = approvedBy;
    }

    public boolean isFetchMaterials()
    {
      return fetchMaterials;
    }

    public void setFetchMaterials(boolean fetchMaterials)
    {
      this.fetchMaterials = fetchMaterials;
    }

    public boolean isCleanWorkingDirectory()
    {
      return cleanWorkingDirectory;
    }

    public void setCleanWorkingDirectory(boolean cleanWorkingDirectory)
    {
      this.cleanWorkingDirectory = cleanWorkingDirectory;
    }

    public boolean isNeverCleanupArtifacts()
    {
      return neverCleanupArtifacts;
    }

    public void setNeverCleanupArtifacts(boolean neverCleanupArtifacts)
    {
      this.neverCleanupArtifacts = neverCleanupArtifacts;
    }

    public Approval getApproval()
    {
      return approval;
    }

    public void setApproval(Approval approval)
    {
      this.approval = approval;
    }

    public List<EnvironmentVariable> getEnvironmentVariables()
    {
      return environmentVariables;
    }

    public void setEnvironmentVariables(List<EnvironmentVariable> environmentVariables)
    {
      this.environmentVariables = environmentVariables;
    }

    public List<Job> getJobs()
    {
      return jobs;
    }

    public void setJobs(List<Job> jobs)
    {
      this.jobs = jobs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stage TemplateStage = (Stage) o;

        if (id != TemplateStage.id) return false;
        if (counter != TemplateStage.counter) return false;
        if (name != null ? !name.equals(TemplateStage.name) : TemplateStage.name != null) return false;
        if (result != null ? !result.equals(TemplateStage.result) : TemplateStage.result != null) return false;
        if (approvedBy != null ? !approvedBy.equals(TemplateStage.approvedBy) : TemplateStage.approvedBy != null) return false;
        return !(jobs != null ? !jobs.equals(TemplateStage.jobs) : TemplateStage.jobs != null);

    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + (name != null ? name.hashCode() : 0);
        result1 = 31 * result1 + counter;
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (approvedBy != null ? approvedBy.hashCode() : 0);
        result1 = 31 * result1 + (jobs != null ? jobs.hashCode() : 0);
        return result1;
    }
}
