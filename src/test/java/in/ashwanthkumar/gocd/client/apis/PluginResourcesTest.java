package in.ashwanthkumar.gocd.client.apis;

import in.ashwanthkumar.gocd.client.GoCD;
import in.ashwanthkumar.gocd.client.TestGoCD;
import in.ashwanthkumar.gocd.client.TestUtils;
import in.ashwanthkumar.gocd.client.auth.UsernameAndPasswordAuthentication;
import in.ashwanthkumar.gocd.client.types.plugin.PluginInfo;
import in.ashwanthkumar.gocd.client.types.plugin.Plugins;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PluginResourcesTest {
    @Test
    public void shouldParseForAllPlugins() throws IOException {
        GoCD client = TestGoCD.from("http://server", new UsernameAndPasswordAuthentication("foo", "bar"), TestUtils.readFile("/responses/plugins/get_all_plugin_info.json"));
        Plugins allPlugins = client.plugins().getAllPlugins();
        assertThat(allPlugins.sizeOf(), is(1));
        PluginInfo plugin = allPlugins.getPlugins().get(0);
        assertThat(plugin.getId(), is("json.config.plugin"));
    }

    @Test
    public void shouldParseForOnePlugin() throws IOException {
        GoCD client = TestGoCD.from("http://server", new UsernameAndPasswordAuthentication("foo", "bar"), TestUtils.readFile("/responses/plugins/get_one_plugin_info.json"));
        PluginInfo plugin = client.plugins().getPlugin("my_plugin");
        assertThat(plugin.getId(), is("my_plugin"));
    }

}