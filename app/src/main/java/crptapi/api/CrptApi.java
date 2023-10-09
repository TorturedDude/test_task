package crptapi.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import crptapi.models.Document;

import java.io.IOException;
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

    public void createDocument(Document document, String signature) {
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

            this.sendRequest("https://ismp.crpt.ru/api/v3/lk/documents/create", document, signature);

            this.requestCount.incrementAndGet();
        }
    }

    private void sendRequest(String urlString, Document document, String signature) {
        try {
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setUseCaches(false);
            

            String requestBody = this.getJsonDocument(document) + ", {signature: \"" + signature + "\"}";
            byte[] requestBodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(requestBodyBytes);
            }

            //TODO: обработку ответа
            Integer responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Запрос отработал успешно");
            } else {
                System.out.println("Запрос не отработал код ошибки: " + responseCode);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getJsonDocument(Document document) throws IOException {
        ObjectWriter ow = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(document);
    }
}