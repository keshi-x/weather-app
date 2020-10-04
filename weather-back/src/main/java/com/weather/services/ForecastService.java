package com.weather.services;

import com.weather.models.beans.City;
import com.weather.models.beans.ForecastData;

import java.util.List;

public interface ForecastService {
    List<ForecastData> getDataFromApi(List<City> cityList);
}
