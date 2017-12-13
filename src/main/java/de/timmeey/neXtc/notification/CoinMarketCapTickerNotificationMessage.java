package de.timmeey.neXtc.notification;

import de.timmeey.neXtc.coinMarketCapApi.CoinMarketCapTicker;
import java.io.IOException;

/**
 * MarkDownMessage.
 * @author Tim Hinkes (timmeey@timmeey.de)
 * @version $Id:\$
 * @since 0.1
 */
public class CoinMarketCapTickerNotificationMessage {

    private final CoinMarketCapTicker src;
    private final static String markdownMessage =
        "US$ price of %s is: %s \n\n" +
            "| Period  | Change | \n" +
            "  | :-------|:------|\n" +
            "  | 1 Hour  | %s     |\n" +
            "  | 1 Day   | %s     |\n" +
            "  | 7 Days  | %s     |\n";

    private final static String plaintextMessage =
        "US$ price of %s is: %s. With changes of 1Hour: %s, 1Day: %s, 7Days: " +
            "%s";

    public CoinMarketCapTickerNotificationMessage(final CoinMarketCapTicker ticker) {
        this.src = ticker;
    }

    public String asMarkdownString() throws IOException {
        return replace(markdownMessage);
    }

    public String asPlaintextString() throws IOException {
        return replace(plaintextMessage);
    }

    private String replace(String formatString) {
        return String.format(formatString,
            src.currency(),
            src.price(),
            src.percentChangeHour(),
            src.percentChangeDay(),
            src.percentChangeWeek());
    }
}
