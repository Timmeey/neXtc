package de.timmeey.neXtc.coinMarketCapApi;

import de.timmeey.neXtc.HttpClient;
import java.io.IOException;
import org.json.JSONArray;

/**
 * de.timmeey.neXtc.coinMarketCapApi.de.timmeey.neXtc.coinMarketCapApi.
 * @author Tim Hinkes (timmeey@timmeey.de)
 * @version $Id:\$
 * @since 0.1
 */
public class CoinMarketCapApi {
    private final HttpClient client;

    public CoinMarketCapApi(final HttpClient client) {
        this.client = client;
    }

    public CoinMarketCapTicker getTicker(String id) throws IOException {
        return new CoinMarketCapTicker(
            new JSONArray(
                client.execute(new TickerRequest(id)).body().string()
            ).getJSONObject(0)
        );
    }
}
