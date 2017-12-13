package de.timmeey.neXtc.coinMarketCapApi;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.json.JSONObject;

/**
 * de.timmeey.neXtc.coinMarketCapApi.CoinMarketCapTicker.
 * @author Tim Hinkes (timmeey@timmeey.de)
 * @version $Id:\$
 * @since 0.1
 */
public class CoinMarketCapTicker {

    private final JSONObject src;

    public CoinMarketCapTicker(JSONObject o) {
        this.src = o;
    }

    public String currency() {
        return src.getString("symbol");
    }

    public double price() {
        return src.getDouble("price_usd");
    }

    public double percentChangeHour() {
        return src.getDouble("percent_change_1h");
    }

    public double percentChangeDay() {
        return src.getDouble("percent_change_24h");
    }

    public double percentChangeWeek() {
        return src.getDouble("percent_change_7d");
    }

    public ZonedDateTime updated() {
        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(src.getLong
            ("last_updated")), ZoneId.of("UTC"));
    }

}
