package crptapi.api;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CrptApi {

    private final TimeUnit timeUnit;
    private final Integer requestsLimit;
    private final AtomicInteger requestCount;
    private final Object lock;

    public CrptApi(TimeUnit timeUnit, Integer requestsLimit) {
        this.timeUnit = timeUnit;
        this.requestsLimit = requestsLimit;
        this.requestCount = new AtomicInteger(0);
        this.lock = new Object();
    }

    public void createDocument(Object document, String signature) {
        synchronized (this.lock) {
            if (requestCount.get() >= requestsLimit) {
                try {
                    // Если превышено, блокируем вызов до окончания временного интервала
                    lock.wait(timeUnit.toMillis(1));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            this.requestCount.incrementAndGet();
        }
    }

    private void sendRequest(String urlString, Object document, String signature) {
        try {
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setUseCaches(false);
            
            String requestBody = "{\"document\": " + document.toString() + ", \"signature\": \"" + signature + "\"}";
            byte[] requestBodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(requestBodyBytes);
            }

            //TODO: обработку ответа
            Integer responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Обработка успешного ответа от API
            } else {
                // Обработка ошибки от API
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}