package in.ashwanthkumar.gocd.client;

import in.ashwanthkumar.gocd.client.auth.UsernameAndPasswordAuthentication;
import in.ashwanthkumar.gocd.client.types.*;
import in.ashwanthkumar.utils.collections.Lists;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;

public class GoCDTest {

    @Test
    public void shouldParsPipelineHistory() throws IOException {
        GoCD client = new GoCD("http://server", new UsernameAndPasswordAuthentication("foo", "bar"), TestUtils.readFile("/responses/pipeline_history.json"));
        Map<Integer, PipelineRunStatus> statusMap = client.pipelineRunStatus("Build-Linux");
        assertThat(statusMap, hasEntry(634, PipelineRunStatus.FAILED));
        assertThat(statusMap, hasEntry(635, PipelineRunStatus.PASSED));
        assertThat(statusMap, hasEntry(636, PipelineRunStatus.PASSED));
        assertThat(statusMap, hasEntry(642, PipelineRunStatus.FAILED));
    }

    @Test
    public void shouldParsePipelineStatus() throws IOException {
        GoCD client = new GoCD("http://server", new UsernameAndPasswordAuthentication("foo", "bar"), TestUtils.readFile("/responses/pipeline_status.json"));
        PipelineStatus pipelineStatus = client.pipelineStatus("Build-Linux");
        assertThat(pipelineStatus.isLocked(), is(false));
        assertThat(pipelineStatus.isPaused(), is(true));
        assertThat(pipelineStatus.isSchedulable(), is(false));
    }

    @Test
    public void shouldParseUpstreamDependenciesForPipelineRun() throws IOException {
        GoCD client = new GoCD("http://server", new UsernameAndPasswordAuthentication("foo", "bar"), TestUtils.readFile("/responses/pipeline_value_stream_map.json"));
        List<PipelineDependency> pipelineDependencies = client.upstreamDependencies("distributions-all", 327);
        assertThat(pipelineDependencies.size(), is(9));
        assertThat(pipelineDependencies, hasItem(new PipelineDependency().setPipelineName("distributions-all").setVersion(327)));
        assertThat(pipelineDependencies, hasItem(new PipelineDependency().setPipelineName("create-maven-release").setVersion(18)));
        assertThat(pipelineDependencies, hasItem(new PipelineDependency().setPipelineName("build-linux").setVersion(641)));
        assertThat(pipelineDependencies, hasItem(new PipelineDependency().setPipelineName("build-windows").setVersion(566)));
        assertThat(pipelineDependencies, hasItem(new PipelineDependency().setPipelineName("plugins").setVersion(500)));
        assertThat(pipelineDependencies, hasItem(new PipelineDependency().setPipelineName("qa-packages").setVersion(499)));
        assertThat(pipelineDependencies, hasItem(new PipelineDependency().setPipelineName("acceptance-gauge").setVersion(63)));
        assertThat(pipelineDependencies, hasItem(new PipelineDependency().setPipelineName("smoke-go-gauge").setVersion(31)));
        assertThat(pipelineDependencies, hasItem(new PipelineDependency().setPipelineName("regression").setVersion(430)));
    }

    @Test
    public void shouldParseAllPipelineNames() throws IOException {
        GoCD client = new GoCD("http://server", new UsernameAndPasswordAuthentication("foo", "bar"), TestUtils.readFile("/responses/pipelines.xml"));
        List<String> pipelines = client.allPipelineNames("");
        assertThat(pipelines.size(), is(17));
        assertThat(pipelines, hasItem("create-maven-release"));
        assertThat(pipelines, hasItem("build-linux"));
        assertThat(pipelines, hasItem("build-windows"));
        assertThat(pipelines, hasItem("plugins"));
        assertThat(pipelines, hasItem("qa-packages"));
        assertThat(pipelines, hasItem("smoke-go-gauge"));
        assertThat(pipelines, hasItem("acceptance-gauge"));
        assertThat(pipelines, hasItem("regression"));
        assertThat(pipelines, hasItem("distributions-all"));
        assertThat(pipelines, hasItem("goutils"));
        assertThat(pipelines, hasItem("plugin-api-upload"));
        assertThat(pipelines, hasItem("installer_testing"));
        assertThat(pipelines, hasItem("user-documentation"));
        assertThat(pipelines, hasItem("smoke"));
        assertThat(pipelines, hasItem("acceptance"));
        assertThat(pipelines, hasItem("create-maven-release-PR"));
        assertThat(pipelines, hasItem("build-linux-PR"));
    }

    @Test
    public void shouldParsePipelineNamesWithSpecifiedPrefix() throws IOException {
        GoCD client = new GoCD("http://server", new UsernameAndPasswordAuthentication("foo", "bar"), TestUtils.readFile("/responses/pipelines.xml"));
        List<String> pipelines = client.allPipelineNames("build");
        assertThat(pipelines.size(), is(3));
        assertThat(pipelines, hasItem("build-linux"));
        assertThat(pipelines, hasItem("build-windows"));
        assertThat(pipelines, hasItem("build-linux-PR"));
    }

    @Test
    public void shouldParserPipelineInstanceOutput() throws IOException {
        GoCD client = new GoCD("http://server", new UsernameAndPasswordAuthentication("foo", "bar"), TestUtils.readFile("/responses/pipeline_instance.json"));
        Pipeline pipeline = client.pipelineInstance("PipelineName", 1);
        assertThat(pipeline.getName(), is("PipelineName"));
        assertThat(pipeline.getCounter(), is(1));
        assertThat(pipeline.getId(), is(1));
        assertThat(pipeline.getLabel(), is("14.1.0.1-b14a81825d081411993853ea5ea45266ced578b4"));
        assertThat(pipeline.getStages().size(), is(1));
        Job job = new Job("jsunit", "Passed", "Completed", 1, 1398332981981l);
        Stage stage = new Stage(1, "stage1", 1, "Passed", "changes", Lists.of(job));
        assertThat(pipeline.getStages(), hasItem(stage));

        Material material = new Material(4, "Git", "URL: https://github.com/gocd/gocd, Branch: master", "61e2da369d0207a7ef61f326eed837f964471b35072340a03f8f55d993afe01d");
        Modification modification = new Modification(7225, "a788f1876e2e1f6e5a1e91006e75cd1d467a0edb", "my hola mundo changes", "Pick E Reader <pick.e.reader@example.com>", 1435728005000l, null);
        MaterialRevision materialRevision = new MaterialRevision(true, material, Lists.of(modification));
        BuildCause buildCause = new BuildCause("", false, "modified by Pick E Reader <pick.e.reader@example.com>", Lists.of(materialRevision));
        assertThat(pipeline.getBuildCause(), is(buildCause));
    }

    @Test
    public void shouldParsePipelineHistoryOutput() throws IOException {
        GoCD client = new GoCD("http://server", new UsernameAndPasswordAuthentication("foo", "bar"), TestUtils.readFile("/responses/pipeline_history.json"));
        History history = client.pipelineHistory("Build-Linux");
        assertThat(history.getPipelines().size(), is(10));


        assertThat(history.getPagination(), is(new Pagination(0, 643, 10)));
    }


}
