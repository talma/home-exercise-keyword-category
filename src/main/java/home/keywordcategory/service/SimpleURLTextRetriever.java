package home.keywordcategory.service;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class SimpleURLTextRetriever implements IURLTextRetriever {

    private final static int PredictedSize = 100000;
    private final static int ChuckSize = 2048;
    private final static int MaxPageSize = 10000000;
    private final static int MaxReaderIterations = 500;
    public static final String EmptyString = "";

    /**
     * Get text out of given url
     *
     * @param url valid url
     * @return text
     */
    @Override
    public String getText(URI url) {
        try {
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);
            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("Could not retrieve URL " + url + " content. return empty string");
                return EmptyString;
            }

            InputStream content = response.getEntity().getContent();
            ByteArrayOutputStream out = new ByteArrayOutputStream(PredictedSize);

            byte[] outbuf = new byte[ChuckSize];
            long count = 0;
            int n;
            int iterationsCounter = 0;
            byte[] outbuffer;

            while (-1 != (n = content.read(outbuf))) {
                out.write(outbuf, 0, n);
                count += n;
                if (count > MaxPageSize || iterationsCounter > MaxReaderIterations) {
                    System.out.println("Abnormally large content. Url : " + url);
                    out.close();
                    out = null;
                    request.abort();
                    break;
                }
                iterationsCounter++;
            }
            if (out != null) {
                outbuffer = out.toByteArray();
                content.close();
                return new String(outbuffer);
            }
            return EmptyString;
        } catch (IOException e) {
            e.printStackTrace();
            return EmptyString;
        }
    }

    /**
     * Example usage
     * @param args
     * @throws URISyntaxException
     */
    public static void main(String[] args) throws URISyntaxException {
        String text = new SimpleURLTextRetriever().getText(new URI("http://www.starwars.com/"));
        System.out.println(text);
    }
}
