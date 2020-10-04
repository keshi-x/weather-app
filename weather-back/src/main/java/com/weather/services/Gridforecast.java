package com.weather.services;

import com.weather.models.beans.City;
import com.weather.models.beans.ForecastData;
import com.weather.models.service.GridForecastData;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Gridforecast implements ForecastService {

    private final int id = 0;
    private static String url = "https://gridforecast.com/api/v1/forecast/{lat};{lon}/{dt}?api_token={token}";
    private final String apiKey;


    public Gridforecast(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public List<ForecastData> getDataFromApi(List<City> cityList){

        String timeURL = getNowDateFormatted("yyyyMMddHHmm");

        return cityList.stream()
                .map(city -> ApiRequestSender.getForecastData(id, url, city, GridForecastData.class,
                                                                city.getLat(), city.getLon(), timeURL, apiKey))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static String getNowDateFormatted(String pattern) {
        Instant instant = Instant.now();
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(pattern));
    }
}
