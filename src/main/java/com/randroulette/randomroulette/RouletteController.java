/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.randroulette.randomroulette;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
    @FXML
    private Button btnRandList;
    @FXML
    private TextArea randomArea;
    @FXML
    private Button exportBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textArea.setWrapText(true);
        spinLabel.setVisible(false);
        getBackButton.setVisible(false);
        exportBtn.setVisible(false);
        randomArea.setEditable(false);
        randomArea.setVisible(false);
        Animate.setScaleAndFade(exitButton);
        Animate.setScaleAndFade(addButton);
        Animate.setScaleAndFade(clearButton);
        Animate.setScaleAndFade(getBackButton);
        Animate.setScaleAndFade(btnRandList);
        Animate.setScaleAndFade(exportBtn);
        try {
            showMessage("Words need to be separated by ENTER",false,"");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    private void spin(List<String> strList) {
        randomArea.setVisible(false);
        textArea.setVisible(false);
        spinLabel.setVisible(true);
        addButton.setVisible(false);
        btnRandList.setVisible(false);
        clearButton.setVisible(false);
        getBackButton.setVisible(false);
        exportBtn.setVisible(false);
        Random rand = new Random();
        Timeline tm = new Timeline();
        
        for(int i = 0; i < 100; i++){
            KeyFrame kFrame = new KeyFrame(Duration.millis(i * (10 + (i * 0.8))),e -> {
                spinLabel.setText(strList.get(rand.nextInt(strList.size())));
            });
            tm.getKeyFrames().add(kFrame);
        }
        tm.setOnFinished(e->{
            addButton.setVisible(true);
            btnRandList.setVisible(true);
            clearButton.setVisible(true);
            getBackButton.setVisible(true);
        });
        tm.play();
    }

    private void addText(boolean confList) {
        if(textArea.getText().isEmpty()){
            try {
                showMessage("You need to enter some text",false,"");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            return;
        }
        List<String> textList = extractText(textArea.getText());
        if(confList){
            showRandomList(textList);
        }else{
            spin(textList);
        }
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
        randomArea.clear();
        randomArea.setVisible(false);
        textArea.clear();
        textArea.setVisible(true);
        spinLabel.setText("");
        spinLabel.setVisible(false);
        getBackButton.setVisible(false);
        exportBtn.setVisible(false);
    }
    
    public void showMessage(String msg,boolean switchButton, String txtBtn) throws IOException{
        FXMLLoader loader = new FXMLLoader(RouletteController.class.getResource("PopUp.fxml"));
        Parent root = loader.load();
        PopUpController popUp = loader.getController();
        popUp.setWarningMessage(msg);
        if(switchButton){
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
        randomArea.setVisible(false);
        spinLabel.setVisible(false);
        textArea.setVisible(true);
        textArea.setEditable(true);
        getBackButton.setVisible(false);
        exportBtn.setVisible(false);
    }

    @FXML
    private void exit(ActionEvent event) throws IOException {
        showMessage("See you later :D",true, "Exit");
        System.exit(0);
    }

    @FXML
    private void spin(ActionEvent event) {
        addText(false);
    }

    @FXML
    private void exportList(ActionEvent event){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        FileChooser fChooser = new FileChooser();
        fChooser.setTitle("Save as...");
        ExtensionFilter extFilt = new ExtensionFilter("Text files (*.txt)","*.txt");
        fChooser.getExtensionFilters().add(extFilt);
        File file = fChooser.showSaveDialog(stage);

        try{
            if(file != null){
                if(!file.getName().toLowerCase().endsWith(".txt")){
                    file = new File(file.getAbsolutePath() + ".txt");
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(randomArea.getText());
                }
                showMessage("File saved on: " + file.getAbsolutePath(),false,"");
            }else{
                showMessage("Operation aborted", true, "Return");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void showRandomList(List<String> randList){
        randomArea.clear();
        randomArea.setVisible(true);
        textArea.setVisible(false);
        addButton.setVisible(false);
        btnRandList.setVisible(false);
        clearButton.setVisible(false);
        getBackButton.setVisible(false); 
        exportBtn.setVisible(false);
        Collections.shuffle(randList);
        Timeline tm = new Timeline();
        int i = 0;
        String temp = "";
        for (String str : randList) {
            final int index = i+1;
            temp+="[" + (index) + "] " + str + '\n';
            final String aux = temp;
            KeyFrame kFrame = new KeyFrame(Duration.seconds(i * 0.5),e -> {
                randomArea.setText(aux);
            });
            tm.getKeyFrames().add(kFrame);
            i++;
        }
        tm.setOnFinished(e ->{
            exportBtn.setVisible(true);
            getBackButton.setVisible(true);
            addButton.setVisible(true);
            btnRandList.setVisible(true);
            clearButton.setVisible(true);
        });
        
        tm.play();
    }
    
    @FXML
    private void makeRandomList(ActionEvent event) throws InterruptedException {
        spinLabel.setVisible(false);
        addText(true);
    }
}
