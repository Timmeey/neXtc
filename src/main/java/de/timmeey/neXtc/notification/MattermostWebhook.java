package de.timmeey.neXtc.notification;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import de.timmeey.neXtc.HttpClient;
import java.io.IOException;
import org.cactoos.Text;
import org.cactoos.text.TextOf;
import org.json.JSONObject;

/**
 * MattermostWebhook.
 * @author Tim Hinkes (timmeey@timmeey.de)
 * @version $Id:\$
 * @since 0.1
 */
public class MattermostWebhook implements NotificationChannel {

    private final HttpClient client;
    private final String url;

    public MattermostWebhook(final HttpClient client, final String url) {
        this.client = client;
        this.url = url;
    }

    @Override
    public void notify(final Text notificationText) throws IOException {
        client.execute(new MattermostWebhookPost(this.url, notificationText));
    }

    @Override
    public void notify(final CoinMarketCapTickerNotificationMessage message)
        throws IOException {
        this.notify(new TextOf(message.asMarkdownString()));
    }

    private static class MattermostWebhookPost implements de.timmeey.neXtc
        .Request {
        private final String url;
        private final Text message;

        private MattermostWebhookPost(final String url, final Text message) {
            this.url = url;
            this.message = message;
        }

        public Request get() throws IOException {
            MediaType mediaType = MediaType.parse("application/json");
            String payload = new JSONObject().put(
                "text",
                message.asString()
            ).toString();

            return new Request.Builder().post(
                RequestBody.create(mediaType, payload))
                .url(url)
                .build();
        }

    }
}
