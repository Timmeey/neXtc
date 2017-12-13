package de.timmeey.neXtc;

import de.timmeey.neXtc.coinMarketCapApi.CoinMarketCapApi;
import com.google.common.collect.Sets;
import com.squareup.okhttp.OkHttpClient;
import java.io.IOException;
import java.time.Duration;
import de.timmeey.neXtc.notification.MattermostWebhook;

/**
 * de.timmeey.neXtc.NeXtc.
 * @author Tim Hinkes (timmeey@timmeey.de)
 * @version $Id:\$
 * @since 0.1
 */
public class NeXtc {

    public static void main(String[] args) throws IOException,
        InterruptedException {
        if (args.length != 1) {
            System.out.println("Error, not enough arguments");
            return;
        }
        HttpClient client = new LogginHttpClient(
            new OkClient(
                new OkHttpClient()
            )
        );
        CoinMarketCapApi api = new CoinMarketCapApi(client);
        CoinMarketCapAnalysis analysis = new CoinMarketCapAnalysis(
            Sets.newHashSet(
                new MattermostWebhook(
                    client,
                    args[0])
            )
        );

        while (true) {
            analysis.analyze(
                api.getTicker("bitcoin")
            );
            Thread.sleep(Duration.ofMinutes(2).toMillis());
        }

    }
}
