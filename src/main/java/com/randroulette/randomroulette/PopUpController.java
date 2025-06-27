/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.randroulette.randomroulette;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author stredhy
 */
public class PopUpController implements Initializable {

    @FXML
    private Button closebutton;
    @FXML
    private Label warningLabel;
    
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        warningLabel.setWrapText(true);
        Animate.setScaleAndFade(closebutton);
    }    

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void setWarningMessage(String msg){
        warningLabel.setText(msg);
    }
    public void setStage(Stage s){
        stage = s;
    }
    
    public Stage getStage(){
        return stage;
    }
    
    public void setTexTOnButton(String str){
        this.closebutton.setText(str);
    }
    
    public Button closeButton(){
        return this.closebutton;
    }
}
