package de.timmeey.neXtc;

import com.squareup.okhttp.Response;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * de.timmeey.neXtc.HttpClient.
 * @author Tim Hinkes (timmeey@timmeey.de)
 * @version $Id:\$
 * @since 0.1
 */
public interface HttpClient {

    Response execute(Request request) throws IOException;

    @Slf4j
    class FakeHttpClient implements HttpClient{

        @Override
        public Response execute(final Request request) throws IOException {
            com.squareup.okhttp.Request req = request.get();
            log.info("Making a {} to {}",req.method(),req.urlString());
            return new Response.Builder().build();
        }
    }
}
