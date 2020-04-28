package in.ashwanthkumar.gocd.client.auth;

import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;

public class NoAuthentication implements Authentication {
    private NoAuthentication() {}
    @Override
    public HttpRequestFactory addAuthentication(HttpTransport transport) {
        return transport.createRequestFactory();
    }

    // Expose a singleton instance of this class
    public static NoAuthentication get() {
        return new NoAuthentication();
    }
}
