package in.ashwanthkumar.gocd.client.auth;

import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

public interface Authentication {
    HttpRequestFactory addAuthentication(HttpTransport transport);
}
