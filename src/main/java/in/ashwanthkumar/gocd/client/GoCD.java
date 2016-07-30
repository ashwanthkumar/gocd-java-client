package in.ashwanthkumar.gocd.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import in.ashwanthkumar.gocd.client.types.PipelineDependency;
import in.ashwanthkumar.gocd.client.types.PipelineRunStatus;
import in.ashwanthkumar.gocd.client.types.PipelineStatus;
import in.ashwanthkumar.gocd.client.unirest.HttpClient;
import in.ashwanthkumar.utils.collections.Lists;
import in.ashwanthkumar.utils.func.Function;
import in.ashwanthkumar.utils.func.Predicate;
import in.ashwanthkumar.utils.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static in.ashwanthkumar.utils.collections.Lists.filter;
import static in.ashwanthkumar.utils.collections.Lists.map;

public class GoCD {

    private static Logger LOG = LoggerFactory.getLogger(GoCD.class);

    private String server;
    private HttpClient client;

    public GoCD(String server, String username, String password) {
        this.server = server;
        this.client = new HttpClient(username, password);
    }

    /* for tests */ GoCD(String server, String username, String password, String mockResponse) {
        this(server, username, password);
        this.client.setMockResponse(mockResponse);
    }

    public List<String> allPipelineNames(final String pipelinePrefix) throws IOException {
        String xml = client.getXML(buildUrl("/go/api/pipelines.xml"));
        Document doc = Jsoup.parse(xml);
        Elements pipelineElements = doc.select("pipeline[href]");
        List<String> pipelines = filter(map(pipelineElements, new Function<Element, String>() {
            @Override
            public String apply(Element element) {
                String href = element.attr("href");
                String apiPrefix = "/go/api/pipelines/";
                return href.substring(href.indexOf(apiPrefix) + apiPrefix.length(), href.indexOf("/stages.xml"));
            }
        }), new Predicate<String>() {
            @Override
            public Boolean apply(String s) {
                return StringUtils.isEmpty(pipelinePrefix) || s.startsWith(pipelinePrefix);
            }
        });

        return pipelines;
    }

    public List<PipelineDependency> upstreamDependencies(String pipeline, int version) throws IOException {
        JsonObject result = client.getJSON(buildUrl("/go/pipelines/value_stream_map/" + pipeline + "/" + version + ".json")).getAsJsonObject();
        List<PipelineDependency> dependencies = Lists.of(new PipelineDependency(pipeline, version));

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
        return client.getAs(buildUrl("/go/api/pipelines/" + pipeline + "/status"), PipelineStatus.class);
    }

    public Map<Integer, PipelineRunStatus> pipelineRunStatus(String pipeline) throws IOException {
        return pipelineRunStatus(pipeline, 0);
    }

    public Map<Integer, PipelineRunStatus> pipelineRunStatus(String pipeline, int offset) throws IOException {
        Map<Integer, PipelineRunStatus> result = new TreeMap<>(Collections.reverseOrder());
        JsonArray history = client.getJSON(buildUrl("/go/api/pipelines/" + pipeline + "/history/" + offset))
                .getAsJsonObject().getAsJsonArray("pipelines");
        for (JsonElement instance : history) {
            JsonObject instanceObj = instance.getAsJsonObject();
            if (instanceObj.get("preparing_to_schedule").getAsBoolean())
                continue;

            PipelineRunStatus status = pipelineStatusFrom(instanceObj);
            int counter = instanceObj.get("counter").getAsInt();
            LOG.debug(pipeline + "@" + counter + " has " + status);
            result.put(counter, status);
        }
        return result;
    }

    private PipelineRunStatus pipelineStatusFrom(JsonObject instance) {
        JsonArray pipelineStages = instance.getAsJsonArray("stages");
        for (JsonElement pipelineStage : pipelineStages) {
            // Since there isn't an universal way to say if the pipeline has failed or not, because
            // A stage could fail, but we could deem it unimportant (for the time being) and continue the pipeline.

            // We are a little sensitive about what we call failures of a pipeline. Possible Reasons -
            // 1. Any 1 stage failure is considered a pipeline failure.
            // 2. If the pipeline doesn't run to completion (paused or locked) is considered a failure.
            JsonObject stageRun = pipelineStage.getAsJsonObject();
            boolean stageFailed = !stageRun.has("result") || stageRun.get("result").getAsString().equalsIgnoreCase("failed");
            if (stageFailed) {
                return PipelineRunStatus.FAILED;
            }
        }

        return PipelineRunStatus.PASSED;
    }

    private String buildUrl(String resource) {
        try {
            return URI.create(String.format("%s/%s", server, resource)).normalize().toURL().toExternalForm();
        } catch (MalformedURLException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
