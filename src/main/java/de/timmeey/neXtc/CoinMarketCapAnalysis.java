package de.timmeey.neXtc;

import de.timmeey.neXtc.coinMarketCapApi.CoinMarketCapTicker;
import de.timmeey.neXtc.notification.CoinMarketCapTickerNotificationMessage;
import de.timmeey.neXtc.notification.NotificationChannel;
import java.io.IOException;
import java.util.Set;

/**
 * de.timmeey.neXtc.CoinMarketCapAnalysis.
 * @author Tim Hinkes (timmeey@timmeey.de)
 * @version $Id:\$
 * @since 0.1
 */
public class CoinMarketCapAnalysis {
    private static final double THRESHOLD = 20;
    private static final double RE_NOTIFY_THRESHOLD = 10;

    private final Set<NotificationChannel> notifactionChannels;
    private double lastHour = 0;
    private double lastDay = 0;
    private double lastWeek = 0;

    public CoinMarketCapAnalysis(final Set<NotificationChannel>
        notifactionChannels) {
        this.notifactionChannels = notifactionChannels;
    }

    public void analyze(CoinMarketCapTicker ticker) {
        if (hourTrigger(ticker) || dayTrigger(ticker) || weekTrigger(ticker)) {
            CoinMarketCapTickerNotificationMessage message = new
                CoinMarketCapTickerNotificationMessage(ticker);
            this.notifactionChannels.forEach(nc -> sendMessage(nc, message));
        }
        this.lastHour = Math.abs(ticker.percentChangeHour());
        this.lastDay = Math.abs(ticker.percentChangeDay());
        this.lastWeek = Math.abs(ticker.percentChangeWeek());
    }

    private void sendMessage(NotificationChannel channel,
        CoinMarketCapTickerNotificationMessage message) {
        try {
            channel.notify(message);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private boolean hourTrigger(CoinMarketCapTicker ticker) {
        return crossed(this.lastHour, ticker.percentChangeHour(), THRESHOLD);
    }

    private boolean dayTrigger(CoinMarketCapTicker ticker) {
        return crossed(this.lastDay, ticker.percentChangeDay(), THRESHOLD);
    }

    private boolean weekTrigger(CoinMarketCapTicker ticker) {
        return crossed(this.lastWeek, ticker.percentChangeWeek(), THRESHOLD);
    }

    /**
     * Returns true if the value jumped over the threshold
     * @param last
     * @param now
     * @param threshold
     * @return
     */
    private boolean crossed(double last, double now, double threshold) {
        last = Math.abs(last);
        now = Math.abs(now);
        return last < threshold && now > threshold ||
            last < threshold+10 && now > threshold+10;
    }
}
