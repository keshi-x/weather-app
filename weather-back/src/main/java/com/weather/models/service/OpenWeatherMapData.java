package com.weather.models.service;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpenWeatherMapData implements DataInterface {

    @JsonProperty("main")
    private Map<String, String> main;

    @Transient
    public String getTemperature(){
        return main != null ? main.get("temp") : "";
    }

    @Transient
    public String getPressure(){ /* Приводим к одинаковым ед. изм.*/
        return main != null && Objects.nonNull(main.get("pressure"))
                ? String.valueOf(Math.round(Integer.parseInt(main.get("pressure")) * 100))
                : "";
    }

    @Transient
    public String getHumidity(){
        return main != null ? main.get("humidity") : "";
    }

}
