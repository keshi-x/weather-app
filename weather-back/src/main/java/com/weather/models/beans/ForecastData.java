package com.weather.models.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "forecast_data")
public class ForecastData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "server_id")
    private int serverId;

    @JsonIgnore
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    @ManyToOne()
    private City city;

    private String temperature;
    private String humidity;
    private String pressure;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;


    public String getCityName(){
        return city != null ? city.getName() : "";
    }
}
