package in.ashwanthkumar.gocd.client.http;

import com.google.api.client.util.ObjectParser;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * <p>ObjectParser implementation to be used along with google-http-client backed by Gson.</p>
 * <p>The default implementation doesn't work at all for some reason, and I'm not sure either why the whole json
 * parsing was re-invented in the http-client instead of just delegating it to the underlying framework.</p>
 * <p>I'm just going to assume Google engineers are way smarter than me but it's just that I don't know how to use
 * the in-built <code>new JsonObjectParser(new GsonFactory)</code>.</p>
 */
public class GsonObjectParser implements ObjectParser {

    final Gson gson;

    public GsonObjectParser(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> T parseAndClose(InputStream in, Charset charset, Class<T> dataClass) throws IOException {
        return parseAndClose(new InputStreamReader(in), dataClass);
    }

    @Override
    public Object parseAndClose(InputStream in, Charset charset, Type dataType) throws IOException {
        return parseAndClose(new InputStreamReader(in), dataType);
    }

    @Override
    public <T> T parseAndClose(Reader reader, Class<T> dataClass) throws IOException {
        return gson.fromJson(reader, dataClass);
    }

    @Override
    public Object parseAndClose(Reader reader, Type dataType) throws IOException {
        return gson.fromJson(reader, dataType);
    }
}
