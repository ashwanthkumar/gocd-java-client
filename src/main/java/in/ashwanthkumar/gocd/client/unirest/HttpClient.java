package in.ashwanthkumar.gocd.client.unirest;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClient {
    static {
        // Having a large timeout (10 min) because sometimes a pipeline VSM could be very very large
        Unirest.setTimeouts(600 * 1000L, 600 * 1000L);
    }

    private static Logger LOG = LoggerFactory.getLogger(HttpClient.class);

    private String username;
    private String password;
    // for tests
    private String mockResponse;

    public HttpClient(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // for testing only
    public void setMockResponse(String response) {
        this.mockResponse = response;
    }


    public JsonNode getJSON(String url) {
        if (this.mockResponse != null) return new JsonNode(this.mockResponse);
        else try {
            return invokeGET(url).asJson().getBody();
        } catch (UnirestException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public String getXML(String url) {
        if (this.mockResponse != null) return this.mockResponse;
        else try {
            return invokeGET(url).asString().getBody();
        } catch (UnirestException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public GetRequest invokeGET(String url) throws UnirestException {
        LOG.debug("Hitting " + url);
        return Unirest.get(url)
                .basicAuth(username, password);
    }


}
