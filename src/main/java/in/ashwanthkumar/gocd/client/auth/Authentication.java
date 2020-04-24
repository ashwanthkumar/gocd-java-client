package in.ashwanthkumar.gocd.client.auth;

import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

public interface Authentication {
    HttpRequestFactory addAuthentication(NetHttpTransport transport);
}
