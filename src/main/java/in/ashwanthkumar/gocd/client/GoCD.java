package in.ashwanthkumar.gocd.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import in.ashwanthkumar.gocd.client.apis.CurrentUserResources;
import in.ashwanthkumar.gocd.client.apis.PluginResources;
import in.ashwanthkumar.gocd.client.apis.UserResources;
import in.ashwanthkumar.gocd.client.auth.Authentication;
import in.ashwanthkumar.gocd.client.http.HttpClient;
import in.ashwanthkumar.gocd.client.types.*;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>GoCD Client that's used for accessing some official and un-official APIs.</p>
 *
 * <strong>Note - This is by no means a complete implementation of all the endpoints. Feel free to submit a PR of an
 * endpoint that's missing.</strong>
 */
public class GoCD {

    private static Logger LOG = LoggerFactory.getLogger(GoCD.class);

    private HttpClient client;
    private UserResources users;
    private CurrentUserResources currentUserResources;
    private PluginResources plugins;

    public GoCD(HttpClient client) {
        this.client = client;

        this.users = new UserResources(this.client);
        this.currentUserResources = new CurrentUserResources(this.client);
        this.plugins = new PluginResources(this.client);
    }

    public GoCD(String server, Authentication authenticationMechanism) {
        this(new HttpClient(authenticationMechanism, server));
    }

    @Deprecated(since = "0.0.8", forRemoval = true)
    public GoCD(String server, String username, String password) {
        this(new HttpClient(username, password, server));
    }

    /* for tests */ GoCD(String server, String username, String password, String mockResponse) {
        this(server, username, password);
        this.client.setMockResponse(mockResponse);
    }

    /* for tests */ GoCD(String server, Authentication authentication, String mockResponse) {
        this(server, authentication);
        this.client.setMockResponse(mockResponse);
    }

    public List<String> allPipelineNames(final String pipelinePrefix) throws IOException {
        String xml = client.getXML("/go/api/pipelines.xml");
        Document doc = Jsoup.parse(xml);
        Elements pipelineElements = doc.select("pipeline[href]");
        Stream<String> pipelines = pipelineElements.stream()
                .map(element -> {
                    String href = element.attr("href");
                    String apiPrefix = "/go/api/pipelines/";
                    return href.substring(href.indexOf(apiPrefix) + apiPrefix.length(), href.indexOf("/stages.xml"));
                })
                .filter(s -> StringUtils.isEmpty(pipelinePrefix) || s.startsWith(pipelinePrefix));
        return pipelines.collect(Collectors.toList());
    }

    public List<PipelineDependency> upstreamDependencies(String pipeline, int version) throws IOException {
        JsonObject result = client.getRawJson("/go/pipelines/value_stream_map/" + pipeline + "/" + version + ".json").getAsJsonObject();
        List<PipelineDependency> dependencies = new ArrayList<>(List.of(new PipelineDependency(pipeline, version)));

        // happens typically when we check for next run
        // in case of connection errors it should fail before this
        if (!result.has("levels")) return dependencies;

        JsonArray levels = result.getAsJsonArray("levels");
        for (JsonElement level : levels) {
            JsonArray nodes = level.getAsJsonObject().getAsJsonArray("nodes");
            for (JsonElement node : nodes) {
                JsonObject nodeObj = node.getAsJsonObject();
                String name = nodeObj.get("name").getAsString();
                // The VSM JSON is always ordered (left to right in VSM view) set of dependencies
                if (name.equals(pipeline)) return dependencies;

                // We pick only the PIPELINE type dependencies
                if (nodeObj.get("node_type").getAsString().equalsIgnoreCase("PIPELINE")) {
                    JsonArray instances = nodeObj.getAsJsonArray("instances");
                    for (JsonElement instance : instances) {
                        int counter = instance.getAsJsonObject().get("counter").getAsInt();
                        dependencies.add(
                                new PipelineDependency()
                                        .setPipelineName(name)
                                        .setVersion(counter)
                        );
                    }
                }
            }
        }

        return dependencies;
    }

    public PipelineStatus pipelineStatus(String pipeline) throws IOException {
        return client.getAs("/go/api/pipelines/" + pipeline + "/status", PipelineStatus.class);
    }

    public Pipeline pipelineInstance(String pipeline, int pipelineCounter) throws IOException {
        return client.getAs("/go/api/pipelines/" + pipeline + "/instance/" + pipelineCounter, Pipeline.class);
    }

    public History pipelineHistory(String pipeline) throws IOException {
        return pipelineHistory(pipeline, 0);
    }

    public History pipelineHistory(String pipeline, int offset) throws IOException {
        return client.getAs("/go/api/pipelines/" + pipeline + "/history/" + offset, History.class);
    }

    public Map<Integer, PipelineRunStatus> pipelineRunStatus(String pipeline) throws IOException {
        return pipelineRunStatus(pipeline, 0);
    }

    public Map<Integer, PipelineRunStatus> pipelineRunStatus(String pipelineName, int offset) throws IOException {
        Map<Integer, PipelineRunStatus> result = new TreeMap<>(Collections.reverseOrder());
        History history = pipelineHistory(pipelineName, offset);
        for (Pipeline pipeline : history.getPipelines()) {
            if (pipeline.isPreparingToSchedule())
                continue;

            PipelineRunStatus status = pipelineStatusFrom(pipeline.getStages());
            LOG.debug(pipeline + "@" + pipeline.getCounter() + " has " + status);
            result.put(pipeline.getCounter(), status);
        }
        return result;
    }

    private PipelineRunStatus pipelineStatusFrom(List<Stage> stages) {
        for (Stage stage : stages) {
            // Since there isn't an universal way to say if the pipeline has failed or not, because
            // A stage could fail, but we could deem it unimportant (for the time being) and continue the pipeline.

            // We are a little sensitive about what we call failures of a pipeline. Possible Reasons -
            // 1. Any 1 stage failure is considered a pipeline failure.
            // 2. If the pipeline doesn't run to completion (paused or locked) is considered a failure.
            boolean stageFailed = StringUtils.isEmpty(stage.getResult()) || stage.getResult().equalsIgnoreCase("failed");
            if (stageFailed) {
                return PipelineRunStatus.FAILED;
            }
        }

        return PipelineRunStatus.PASSED;
    }

    /**
     * Access all User related resources.
     *
     * @return UserResources
     */
    public UserResources users() {
        return users;
    }

    /**
     * Access all Current User related resources.
     *
     * @return CurrentUserResources
     */
    public CurrentUserResources currentUser() {
        return currentUserResources;
    }

    /**
     * Access all PluginInfo Resources
     *
     * @return PluginResources
     */
    public PluginResources plugins() {
        return plugins;
    }
}
