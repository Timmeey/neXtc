package de.timmeey.neXtc.coinMarketCapApi;

import com.squareup.okhttp.Request;

/**
 * TicketRequest.
 * @author Tim Hinkes (timmeey@timmeey.de)
 * @version $Id:\$
 * @since 0.1
 */
public class TickerRequest implements de.timmeey.neXtc.Request{

    private static final String API = " https://api.coinmarketcap.com/v1/ticker/";
    private final String url;

    private final String id;

    public TickerRequest(final String id) {
        super();
        this.id = id;
        this.url=API+id;
    }

    @Override
    public Request get(){
        return new Request.Builder().get().url(url).build();
    }

}
