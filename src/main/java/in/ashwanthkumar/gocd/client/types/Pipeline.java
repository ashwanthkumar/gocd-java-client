package in.ashwanthkumar.gocd.client.types;

import com.google.gson.annotations.SerializedName;
import in.ashwanthkumar.gocd.client.GoCD;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class Pipeline {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("counter")
    private int counter;

    @SerializedName("preparing_to_schedule")
    private boolean preparingToSchedule;

    @SerializedName("can_run")
    private boolean canRun;

    @SerializedName("build_cause")
    private BuildCause buildCause;

    @SerializedName("stages")
    private List<Stage> stages;

    @SerializedName("label")
    private String label;

    // "comment"
    // "natural_order"


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCounter() {
        return counter;
    }

    public boolean isPreparingToSchedule() {
        return preparingToSchedule;
    }

    public boolean isCanRun() {
        return canRun;
    }

    public BuildCause getBuildCause() {
        return buildCause;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Collect all changed MaterialRevision objects, walking changed
     * "Pipeline" objects recursively instead of including them directly.
     */
    public List<MaterialRevision> rootChanges(GoCD client)
            throws MalformedURLException, IOException {
        List result = new ArrayList();
        addChangesRecursively(client, result);
        return result;
    }

    void addChangesRecursively(GoCD client, List<MaterialRevision> outChanges)
            throws MalformedURLException, IOException {
        for (MaterialRevision mr : buildCause.getMaterialRevisions()) {
            mr.addChangesRecursively(client, outChanges);
        }
    }

    @Override
    public String toString() {
        if (stages != null && stages.size() > 0) {
            return name + "/" + counter + "/" + stages.get(0).getName() + "/" + stages.get(0).getResult();
        } else {
            return name + "/" + counter;
        }
    }
}
