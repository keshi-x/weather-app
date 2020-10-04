package com.weather.controllers;

import com.weather.models.beans.City;
import com.weather.repositories.CityRepository;
import com.weather.repositories.ForecastDataRepository;
import com.weather.services.ForecastService;
import com.weather.services.Gridforecast;
import com.weather.services.OpenWeatherMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WeatherController {

	@Autowired
	private Environment env;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ForecastDataRepository forecastDataRepository;


	@GetMapping("/update")
	public ResponseEntity<?> getUpdateData() {
		loadWeatherData();
		return ResponseEntity.ok().body("OK");

	}

	public void loadWeatherData() {

		List<City> cityList  = cityRepository.findAll();
		if (!cityList.isEmpty()) {

			ForecastService forecastService = new Gridforecast(env.getProperty("token.gridforecast"));
			forecastDataRepository.saveAll(forecastService.getDataFromApi(cityList));

			forecastService = new OpenWeatherMap(env.getProperty("token.openweathermap"));
			forecastDataRepository.saveAll(forecastService.getDataFromApi(cityList));
		}
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAllWeatherData() {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");

		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.headers(responseHeaders)
				.body(forecastDataRepository.findAll());
	}

}