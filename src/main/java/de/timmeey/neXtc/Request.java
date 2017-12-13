package de.timmeey.neXtc;

import java.io.IOException;

/**
 * de.timmeey.neXtc.Request.
 * @author Tim Hinkes (timmeey@timmeey.de)
 * @version $Id:\$
 * @since 0.1
 */
public interface Request {

    com.squareup.okhttp.Request get() throws IOException;
}
