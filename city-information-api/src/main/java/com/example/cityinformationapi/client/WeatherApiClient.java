package com.example.cityinformationapi.client;

import com.example.cityinformationapi.model.WeatherApiConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WeatherApiClient {
    private final WeatherApiConfig apiConfig;
    @Autowired
    public WeatherApiClient(WeatherApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }


    public double getWeatherInfo(String cityName) {
        String apiUrl = apiConfig.getBaseUrl() + apiConfig.getApiKey() + "&q="+ cityName;
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet(apiUrl);

            HttpResponse response = httpClient.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String responseBody = EntityUtils.toString(response.getEntity());

                return temperatureExtractor(responseBody);

            } else {
                System.out.println("Error: HTTP request failed, response code: " + statusCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    private double temperatureExtractor(String responseBody) {
        double temperatureCelsius = 0;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            // Extract the temperature in Celsius
            temperatureCelsius = jsonNode.path("current").path("temp_c").asDouble();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return temperatureCelsius;
    }
}



