/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woodcomputing.homemonitor;

import com.google.inject.Guice;
import com.woodcomputing.homemonitor.controller.HomeMonitorController;
import com.woodcomputing.homemonitor.module.HomeMonitorModule;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jwood
 */
public class HomeMonitor extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        final HomeMonitorController hmc
                = Guice.createInjector(new HomeMonitorModule())
                        .getInstance(HomeMonitorController.class);
        final Scene scene = new Scene(hmc);
        scene.getStylesheets().add("/com/woodcomputing/homemonitor/css/homemonitor.css");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
