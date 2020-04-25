package in.ashwanthkumar.gocd.client;

import in.ashwanthkumar.gocd.client.auth.Authentication;

public class TestGoCD {
    public static GoCD from(String host, Authentication authentication, String mockResponse) {
        return new GoCD(host, authentication, mockResponse);
    }
}
