/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.randroulette.randomroulette;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author stredhy
 */
public class RouletteController implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private Button addButton;
    @FXML
    private Button clearButton;
    @FXML
    private Label spinLabel;
    @FXML
    private Button getBackButton;
    @FXML
    private Button exitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textArea.setWrapText(true);
        spinLabel.setVisible(false);
        getBackButton.setVisible(false);
        Animate.setScaleAndFade(exitButton);
        Animate.setScaleAndFade(addButton);
        Animate.setScaleAndFade(clearButton);
        Animate.setScaleAndFade(getBackButton);
        try {
            showMessage("Words need to be separated by ENTER",false,"");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    private void spin(List<String> strList) {
        textArea.setVisible(false);
        spinLabel.setVisible(true);
        getBackButton.setVisible(true);
        Random rand = new Random();
        Timeline tm = new Timeline();
        
        for(int i = 0; i < 100; i++){
            KeyFrame kFrame = new KeyFrame(Duration.millis(i * (10 + (i * 0.8))),e -> {
                spinLabel.setText(strList.get(rand.nextInt(strList.size())));
            });
            tm.getKeyFrames().add(kFrame);
        }
        tm.play();
    }

    @FXML
    private void addText(ActionEvent event) {
        if(textArea.getText().isEmpty()){
            try {
                showMessage("You need to enter some text",false,"");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            return;
        }
        spin(extractText(textArea.getText()));
    }
    
    private List<String> extractText(String str){
        StringBuilder aux = new StringBuilder();
        List<String> strList = new ArrayList();
        for(char c : str.toCharArray()){
            if(c == '\n'){
                strList.add(aux.toString());
                aux = new StringBuilder();
                continue;
            }
            aux.append(c);
        }
        
        if(!aux.toString().isEmpty()){
            strList.add(aux.toString());
        }
        return strList;
    }

    @FXML
    private void clearAll(ActionEvent event) {
        textArea.clear();
        textArea.setVisible(true);
        spinLabel.setText("");
        spinLabel.setVisible(false);
        getBackButton.setVisible(false);
        
    }
    
    public void showMessage(String msg,boolean b, String txtBtn) throws IOException{
        FXMLLoader loader = new FXMLLoader(RouletteController.class.getResource("PopUp.fxml"));
        Parent root = loader.load();
        PopUpController popUp = loader.getController();
        popUp.setWarningMessage(msg);
        if(b){
            popUp.setTexTOnButton(txtBtn);
        }
        Stage warningStage = new Stage();
        warningStage.initModality(Modality.APPLICATION_MODAL);
        warningStage.setScene(new Scene(root));
        warningStage.initStyle(StageStyle.UNDECORATED);
        warningStage.setTitle(msg);
        popUp.setStage(warningStage);
        warningStage.showAndWait();
    }

    @FXML
    private void getBack(ActionEvent event) {
        spinLabel.setVisible(false);
        textArea.setVisible(true);
        getBackButton.setVisible(false);
    }

    @FXML
    private void exit(ActionEvent event) throws IOException {
        showMessage("See you later :D",true, "Exit");
        System.exit(0);
    }
}
