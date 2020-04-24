package in.ashwanthkumar.gocd.client.auth;

import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

public class PersonalAccessTokenAuthentication implements Authentication {
    private String apiToken;

    public PersonalAccessTokenAuthentication(String apiToken) {
        this.apiToken = apiToken;
    }

    @Override
    public HttpRequestFactory addAuthentication(NetHttpTransport transport) {
        return transport.createRequestFactory(new BearerTokenAuthentication(apiToken));
    }
}
