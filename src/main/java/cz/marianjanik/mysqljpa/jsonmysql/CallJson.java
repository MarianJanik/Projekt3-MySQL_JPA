package cz.marianjanik.mysqljpa.jsonmysql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.lang.System.lineSeparator;

public class CallJson {

    public String callApi() {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("https://euvatrates.com/rates.json")).GET().build();

        HttpResponse<String> httpResponse = null;
            try {
                httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                throw new RuntimeException(e + lineSeparator() + " Spojeni z danou adresou se nezdarilo navazat, napr. chybi konektivita nebo nespravne napsana adresa.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e + lineSeparator() + " Spojeni bylo preruseno.");
            }
        return httpResponse.body();
    }

    public VatResponse mapToObject(String body) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        VatResponse vatResponse = null;
        try {
            vatResponse = objectMapper.readValue(body, VatResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e + lineSeparator() + " Namapovani JSONu na adrese se nezdarilo. Muze byt chybne zapsana adresa.");
        }
        return vatResponse;
    }
}