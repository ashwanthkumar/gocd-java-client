package in.ashwanthkumar.gocd.client;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;

public class TestUtils {
    public static String readFile(String file) throws IOException {
        URL resource = TestUtils.class.getResource(file);
        return String.join("\n", IOUtils.readLines(resource.openStream(), "UTF-8"));
    }

    public static String fileName(String resource) {
        return TestUtils.class.getResource(resource).getPath();
    }
}
