package in.ashwanthkumar.gocd.client.apis;

import com.google.gson.reflect.TypeToken;
import in.ashwanthkumar.gocd.client.http.HttpClient;
import in.ashwanthkumar.gocd.client.types.ResultWrapper;
import in.ashwanthkumar.gocd.client.types.plugin.PluginInfo;
import in.ashwanthkumar.gocd.client.types.plugin.Plugins;

import java.io.IOException;
import java.lang.reflect.Type;

public class PluginResources {
    HttpClient client;

    public PluginResources(HttpClient client) {
        this.client = client;
    }

    public Plugins getAllPlugins() throws IOException {
        Type resultType = new TypeToken<ResultWrapper<Plugins>>() {
        }.getType();
        ResultWrapper<Plugins> result = client.getAs("/go/api/admin/plugin_info", resultType, 4);
        return result.getValue();
    }

    public PluginInfo getPlugin(String pluginId) throws IOException {
        PluginInfo result = client.getAs("/go/api/admin/plugin_info/" + pluginId, TypeToken.get(PluginInfo.class).getType(), 4);
        return result;
    }

}
