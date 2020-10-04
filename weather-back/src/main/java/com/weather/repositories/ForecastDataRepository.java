package com.weather.repositories;

import com.weather.models.beans.ForecastData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ForecastDataRepository extends CrudRepository<ForecastData, Integer> {

    @Query(value = "select * from forecast_data order by date_created desc limit (?1)", nativeQuery = true)
    List<ForecastData> findLast(int cityCount) ;
}
