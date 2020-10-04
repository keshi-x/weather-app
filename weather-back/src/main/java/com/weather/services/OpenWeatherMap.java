package com.weather.services;

import com.weather.models.beans.City;
import com.weather.models.beans.ForecastData;
import com.weather.models.service.OpenWeatherMapData;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OpenWeatherMap implements ForecastService {

    private final int id = 1;
    private static String url = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&units=metric&appid={token}";
    private final String apiKey;


    public OpenWeatherMap(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public List<ForecastData> getDataFromApi(List<City> cityList){

        return cityList.stream()
                .map(city -> ApiRequestSender.getForecastData(id, url, city, OpenWeatherMapData.class,
                                                                city.getLat(), city.getLon(), apiKey))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
