package de.timmeey.neXtc;

import com.squareup.okhttp.Response;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * LogginHttpClient.
 * @author Tim Hinkes (timmeey@timmeey.de)
 * @version $Id:\$
 * @since 0.1
 */
@Slf4j
public class LogginHttpClient implements HttpClient {

    private final HttpClient src;

    public LogginHttpClient(final HttpClient src) {
        this.src = src;
    }

    @Override
    public Response execute(final Request request) throws IOException {
        com.squareup.okhttp.Request req = request.get();
        log.info("Making a {}  to {}",req.method(),req.urlString());
        Response resp = src.execute(request);
        log.info("Response was {} with body length {}",resp.code(),resp.body().contentLength());
        return resp;
    }
}
