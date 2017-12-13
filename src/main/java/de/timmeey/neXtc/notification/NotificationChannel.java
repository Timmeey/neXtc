package de.timmeey.neXtc.notification;

import java.io.IOException;
import org.cactoos.Text;

/**
 * de.timmeey.neXtc.notification.NotificationChannel.
 * @author Tim Hinkes (timmeey@timmeey.de)
 * @version $Id:\$
 * @since 0.1
 */
public interface NotificationChannel {

    void notify(Text notificationText) throws IOException;
    void notify(CoinMarketCapTickerNotificationMessage notificationText) throws IOException;

}
