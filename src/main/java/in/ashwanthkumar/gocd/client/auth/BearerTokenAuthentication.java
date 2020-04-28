package in.ashwanthkumar.gocd.client.auth;

import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;

import java.io.IOException;

public class BearerTokenAuthentication implements HttpRequestInitializer, HttpExecuteInterceptor {
    private String apiToken;

    public BearerTokenAuthentication(String apiToken) {
        this.apiToken = apiToken;
    }

    @Override
    public void intercept(HttpRequest httpRequest) throws IOException {
        HttpHeaders existingHeaders = httpRequest.getHeaders();
        existingHeaders.setAuthorization("bearer " + apiToken);
    }

    @Override
    public void initialize(HttpRequest httpRequest) throws IOException {
        httpRequest.setInterceptor(this);
    }
}
