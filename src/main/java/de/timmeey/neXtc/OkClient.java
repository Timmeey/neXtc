package de.timmeey.neXtc;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import java.io.IOException;

/**
 * OkHttpClient.
 * @author Tim Hinkes (timmeey@timmeey.de)
 * @version $Id:\$
 * @since 0.1
 */
public class OkClient implements HttpClient{

    private final OkHttpClient client;

    public OkClient(final OkHttpClient client) {
        this.client = client;
    }

    @Override
    public Response execute(final Request request) throws IOException {
        com.squareup.okhttp.Request req = request.get();
        return this.client.newCall(req).execute();
    }
}
