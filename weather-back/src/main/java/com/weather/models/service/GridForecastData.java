package com.weather.models.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GridForecastData implements DataInterface {

    @JsonProperty("t")
    private String temperature;

    @JsonProperty("r")
    private String humidity;

    @JsonProperty("sp")
    private String pressure;

}