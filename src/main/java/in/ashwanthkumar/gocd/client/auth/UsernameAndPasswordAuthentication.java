package in.ashwanthkumar.gocd.client.auth;

import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;

public class UsernameAndPasswordAuthentication implements Authentication {
    private String username;
    private String password;

    public UsernameAndPasswordAuthentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public HttpRequestFactory addAuthentication(HttpTransport transport) {
        return transport.createRequestFactory(new BasicAuthentication(username, password));
    }
}
