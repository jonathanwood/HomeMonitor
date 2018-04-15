/*
 * Copyright 2018 Jonathan Shaw Wood.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.woodcomputing.homemonitor.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.woodcomputing.homemonitor.model.SimpleForecastDay;
import com.woodcomputing.homemonitor.model.TenDayForecast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 *
 * @author <a href="mailto:jonathan@woodcomputing.com">Jonathan Wood</a>
 */
@Log4j2
public class WeatherManager {
    
    private static final String TEN_DAY_FORECAST_MESSAGE_FORMAT = "http://api.wunderground.com/api/3ea44acbfd56b977/forecast10day/q/OH/Wauseon.json";
    
    @FXML private ObservableList<SimpleForecastDay> tenDayForecastList;
    
    private final CloseableHttpClient httpClient;
    
    @Inject
    public WeatherManager(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
        tenDayForecastList = FXCollections.observableArrayList(getTenDayForecast());
    }
        
    List<SimpleForecastDay> getTenDayForecast() {
        List<SimpleForecastDay> forecasts = new ArrayList<>();
        HttpGet get = new HttpGet(TEN_DAY_FORECAST_MESSAGE_FORMAT);
        try(CloseableHttpResponse response = httpClient.execute(get)) {
            ObjectMapper mapper = new ObjectMapper();
            TenDayForecast tenDayForecast = mapper.readValue(response.getEntity().getContent(), TenDayForecast.class);
            forecasts = tenDayForecast.getForecast().getSimpleForecast().getForecastDays();
            log.debug("{}", forecasts);
        } catch (IOException ex) {
            Logger.getLogger(WeatherManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return forecasts;
    }

    public ObservableList<SimpleForecastDay> getTenDayForecastList() {
        return tenDayForecastList;
    }
    
}
