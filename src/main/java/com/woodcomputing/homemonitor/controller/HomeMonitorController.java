/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woodcomputing.homemonitor.controller;

import com.google.inject.Inject;
import com.woodcomputing.homemonitor.manager.WeatherManager;
import com.woodcomputing.homemonitor.model.SimpleForecastDay;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import lombok.extern.log4j.Log4j2;

/**
 * FXML Controller class
 *
 * @author jwood
 */
@Log4j2
public class HomeMonitorController extends StackPane implements Initializable {

    @FXML private ListView<SimpleForecastDay> forcastListView;
    
    @Inject 
    private final WeatherManager weatherManager;
    
    @Inject
    public HomeMonitorController(WeatherManager weatherManager) {
        this.weatherManager = weatherManager;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/woodcomputing/homemonitor/fxml/HomeMonitor.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            log.catching(ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        forcastListView.setItems(weatherManager.getTenDayForecastList());
    }    

}
