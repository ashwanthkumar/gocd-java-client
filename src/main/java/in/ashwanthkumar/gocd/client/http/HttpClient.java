package in.ashwanthkumar.gocd.client.http;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import in.ashwanthkumar.gocd.client.auth.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URI;

public class HttpClient {
    private static Logger LOG = LoggerFactory.getLogger(HttpClient.class);

    private final static int DEFAULT_SOCKET_TIMEOUT = 600 * 1000;
    private final static int DEFAULT_READ_TIMEOUT = 600 * 1000;

    // for tests
    private String mockResponse;

    private final HttpRequestFactory requestFactory;
    private final int socketTimeout;
    private final int readTimeout;

    private String serverHost;

    @Deprecated(since = "0.0.8", forRemoval = true)
    public HttpClient(String username, String password, Proxy proxy, String serverHost) {
        this(username, password, proxy, DEFAULT_SOCKET_TIMEOUT, DEFAULT_READ_TIMEOUT, serverHost);
    }

    @Deprecated(since = "0.0.8", forRemoval = true)
    public HttpClient(String username, String password, String serverHost) {
        this(username, password, null, serverHost);
    }

    @Deprecated(since = "0.0.8", forRemoval = true)
    public HttpClient(String username, String password, Proxy proxy, int socketTimeout, int readTimeout, String serverHost) {
        NetHttpTransport.Builder builder = new NetHttpTransport.Builder();
        if (proxy != null) {
            builder.setProxy(proxy);
        }
        NetHttpTransport transport = builder.build();
        if (username != null && password != null) {
            requestFactory = transport.createRequestFactory(new BasicAuthentication(username, password));
        } else {
            requestFactory = transport.createRequestFactory();
        }
        this.socketTimeout = socketTimeout;
        this.readTimeout = readTimeout;
        this.serverHost = serverHost;
    }

    public HttpClient(Authentication authentication, String serverHost) {
        this(authentication, null, DEFAULT_SOCKET_TIMEOUT, DEFAULT_READ_TIMEOUT, serverHost);
    }

    public HttpClient(Authentication authentication, Proxy proxy, int socketTimeout, int readTimeout, String serverHost) {
        NetHttpTransport.Builder builder = new NetHttpTransport.Builder();
        if (proxy != null) {
            builder.setProxy(proxy);
        }
        NetHttpTransport transport = builder.build();
        this.requestFactory = authentication.addAuthentication(transport);
        this.socketTimeout = socketTimeout;
        this.readTimeout = readTimeout;
        this.serverHost = serverHost;
    }


    // for testing only
    public void setMockResponse(String response) {
        this.mockResponse = response;
    }


    public JsonElement getRawJson(String resource) throws IOException {
        if (this.mockResponse != null) {
            return new JsonParser().parse(this.mockResponse);
        } else {
            return new JsonParser().parse(invokeGET(resource).execute().parseAsString());
        }
    }

    public JsonElement getRawJson(String resource, int apiVersion) throws IOException {
        if (this.mockResponse != null) {
            return new JsonParser().parse(this.mockResponse);
        } else {
            return new JsonParser().parse(invokeGET(resource, apiVersion).execute().parseAsString());
        }
    }

    public <T> T getAs(String resource, Class<T> responseType) throws IOException {
        return getAs(resource, (Type) responseType);
    }

    public <T> T getAs(String resource, Type responseType) throws IOException {
        if (this.mockResponse != null) {
            return (T) new GsonObjectParser(new Gson()).parseAndClose(new StringReader(this.mockResponse), responseType);
        } else {
            Object object = invokeGET(resource)
                    .setParser(new GsonObjectParser(new Gson()))
                    .execute()
                    .parseAs(responseType);
            return (T) object;
        }
    }

    public <T> T getAs(String resource, Class<T> responseType, int apiVersion) throws IOException {
        return getAs(resource, (Type) responseType, apiVersion);
    }

    public <T> T getAs(String resource, Type responseType, int apiVersion) throws IOException {
        return invoke(invokeGET(resource, apiVersion), responseType);
    }

    public <T> T patchAs(String resource, Type responseType, int apiVersion, Object payload) throws IOException {
        return invoke(invokePATCH(resource, apiVersion, payload), responseType);
    }

    public <T> T invoke(HttpRequest request, Type type) throws IOException {
        if (this.mockResponse != null) {
            return (T) new GsonObjectParser(new Gson()).parseAndClose(new StringReader(this.mockResponse), type);
        } else {
            Object result = request
                    .setParser(new GsonObjectParser(new Gson()))
                    .execute()
                    .parseAs(type);
            return (T) result;
        }
    }

    public String getXML(String resource) throws IOException {
        if (this.mockResponse != null) {
            return this.mockResponse;
        } else {
            HttpResponse response = invokeGET(resource).execute();
            return response.parseAsString();
        }
    }

    @Deprecated
    protected HttpRequest invokeGET(String resource) throws IOException {
        String urlToFetch = buildUrl(resource);
        LOG.debug("Hitting " + urlToFetch);
        return requestFactory.buildGetRequest(new GenericUrl(urlToFetch))
                .setConnectTimeout(this.socketTimeout)
                .setReadTimeout(this.readTimeout);
    }

    protected HttpRequest invokeGET(String resource, int apiVersion) throws IOException {
        String urlToFetch = buildUrl(resource);
        LOG.debug("Hitting " + urlToFetch);
        HttpRequest httpRequest = requestFactory.buildGetRequest(new GenericUrl(urlToFetch));
        return httpRequest
                .setHeaders(httpRequest.getHeaders().setAccept("application/vnd.go.cd.v" + apiVersion + "+json"))
                .setConnectTimeout(this.socketTimeout)
                .setReadTimeout(this.readTimeout);
    }

    protected HttpRequest invokePATCH(String resource, int apiVersion, Object payload) throws IOException {
        String urlToFetch = buildUrl(resource);
        LOG.debug("Hitting " + urlToFetch);
        HttpRequest httpRequest = requestFactory.buildPatchRequest(new GenericUrl(urlToFetch), new JsonHttpContent(GsonFactory.getDefaultInstance(), payload));
        return httpRequest
                .setHeaders(httpRequest.getHeaders().setAccept("application/vnd.go.cd.v" + apiVersion + "+json"))
                .setConnectTimeout(this.socketTimeout)
                .setReadTimeout(this.readTimeout);
    }

    private String buildUrl(String resource) {
        try {
            return URI.create(String.format("%s/%s", this.serverHost, resource)).normalize().toURL().toExternalForm();
        } catch (MalformedURLException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
