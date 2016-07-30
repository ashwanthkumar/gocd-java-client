package in.ashwanthkumar.gocd.client.types;

import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class History {
    private Logger LOG = LoggerFactory.getLogger(History.class);

    @SerializedName("pipelines")
    private List<Pipeline> pipelines;

    @SerializedName("pagination")
    private Pagination pagination;

    public History(List<Pipeline> pipelines, Pagination pagination) {
        this.pipelines = pipelines;
        this.pagination = pagination;
    }

    public History() {
    }

    public List<Pipeline> getPipelines() {
        return pipelines;
    }

    public Pagination getPagination() {
        return pagination;
    }

    /**
     * Find the most recent run of the specified stage _before_ this one.
     *
     * @param pipelineCounter
     * @param stageName
     * @param stageCounter
     * @return Stage
     */
    public Stage previousRun(int pipelineCounter, String stageName, int stageCounter) {
        LOG.debug(String.format("Looking for stage before %d/%s/%d",
                pipelineCounter, stageName, stageCounter));

        // Note that pipelines and stages are stored in reverse
        // chronological order.
        for (Pipeline pipeline : pipelines) {
            for (Stage stage : pipeline.getStages()) {
                LOG.debug(String.format("Checking %d/%s/%d",
                        pipeline.getCounter(), stage.getName(), stage.getCounter()));

                if (stage.getName().equals(stageName)) {

                    // Same pipeline run, earlier instance of stage.
                    if (pipeline.getCounter() == pipelineCounter &&
                            stage.getCounter() < stageCounter)
                        return stage;

                    // Previous pipeline run.
                    if (pipeline.getCounter() < pipelineCounter)
                        return stage;
                }
            }
        }
        // Not found.
        return null;
    }

    @Override
    public String toString() {
        if (pipelines != null && pipelines.size() > 0) {
            if (pipelines.size() > 1) {
                return pipelines.get(0).toString() + "...";
            } else {
                return pipelines.get(0).toString();
            }
        } else {
            return "No history";
        }
    }
}

