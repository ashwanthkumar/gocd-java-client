package in.ashwanthkumar.gocd.client.unirest;

import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Proxy;

public class HttpClient {
    private static Logger LOG = LoggerFactory.getLogger(HttpClient.class);

    // TODO - Would we ever want to make this configurable?
    private final static int SOCKET_TIMEOUT = 600 * 1000;
    private final static int READ_TIMEOUT = 600 * 1000;


    // for tests
    private String mockResponse;

    private final HttpRequestFactory requestFactory;

    public HttpClient(String username, String password, Proxy proxy) {
        NetHttpTransport.Builder builder = new NetHttpTransport.Builder();
        if (proxy != null) {
            builder.setProxy(proxy);
        }
        requestFactory = builder.build().createRequestFactory(new BasicAuthentication(username, password));
    }

    public HttpClient(String username, String password) {
        this(username, password, null);
    }

    // for testing only
    public void setMockResponse(String response) {
        this.mockResponse = response;
    }


    public JsonElement getJSON(String url) throws IOException {
        if (this.mockResponse != null) {
            return new JsonParser().parse(this.mockResponse);
        } else {
            return new JsonParser().parse(invokeGET(url).parseAsString());
        }
    }

    public <T> T getAs(String url, Class<T> type) throws IOException {
        if (this.mockResponse != null) {
            return new Gson().fromJson(this.mockResponse, type);
        } else {
            return invokeGET(url).parseAs(type);
        }
    }

    public String getXML(String url) throws IOException {
        if (this.mockResponse != null) {
            return this.mockResponse;
        } else {
            HttpResponse response = requestFactory.buildGetRequest(new GenericUrl(url)).execute();
            return response.parseAsString();
        }
    }

    public HttpResponse invokeGET(String url) throws IOException {
        LOG.debug("Hitting " + url);
        return requestFactory.buildGetRequest(new GenericUrl(url))
                .setConnectTimeout(SOCKET_TIMEOUT)
                .setReadTimeout(READ_TIMEOUT)
                .execute();
    }


}
