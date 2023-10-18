package sean.oEmbed.handler;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataHandlerImpl implements DataHandler {
    @Override
    public String getData(String oembedEndpoint) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(oembedEndpoint);
        request.addHeader("Content-Type", "application/json");
        CloseableHttpResponse httpResponse = httpClient.execute(request);
        return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
    }
}
