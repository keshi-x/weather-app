package com.weather.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.models.beans.City;
import com.weather.models.service.DataInterface;
import com.weather.models.beans.ForecastData;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ApiRequestSender {

    public static ForecastData getForecastData(int serverId, String url, City city, Class<?> clazz, Object... params){

        RestTemplate restTemplate = new RestTemplate();

        String jsonString = restTemplate.getForObject(url, String.class, params);
//        System.out.println(jsonString);

        try {
            DataInterface data = (DataInterface) new ObjectMapper().readValue(jsonString, clazz);

            return ForecastData.builder()
                    .serverId(serverId)
                    .city(city)
                    .dateCreated(LocalDateTime.now().atZone(ZoneId.systemDefault()).toLocalDateTime())
                    .temperature(data.getTemperature())
                    .pressure(data.getPressure())
                    .humidity(data.getHumidity())
                    .build();

        } catch (JsonProcessingException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
