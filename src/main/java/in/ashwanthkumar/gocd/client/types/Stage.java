package in.ashwanthkumar.gocd.client.types;

import com.google.gson.annotations.SerializedName;

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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCounter() {
        return counter;
    }

    public String getResult() {
        return result;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public List<String> jobNames() {
        return jobs.stream().map((input) -> input.name).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stage stage = (Stage) o;

        if (id != stage.id) return false;
        if (counter != stage.counter) return false;
        if (name != null ? !name.equals(stage.name) : stage.name != null) return false;
        if (result != null ? !result.equals(stage.result) : stage.result != null) return false;
        if (approvedBy != null ? !approvedBy.equals(stage.approvedBy) : stage.approvedBy != null) return false;
        return !(jobs != null ? !jobs.equals(stage.jobs) : stage.jobs != null);

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
