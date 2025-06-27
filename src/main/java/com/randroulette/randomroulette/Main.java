/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.randroulette.randomroulette;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author stredhy
 */
public class Main extends Application{
    
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("Roulette.fxml"));
        Scene sc = new Scene(fxml.load());
        stage.setTitle("Roulette");
        stage.setScene(sc);
        stage.show();

    }
    
    public static void main(String[] args){
        launch();
    }
    
}
