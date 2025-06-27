/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.randroulette.randomroulette;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 *
 * @author stredhy
 */
public class Animate {
    public static void setFadeAnimation(Node n){
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.2),n);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.2),n);
        
        fadeIn.setFromValue(1.0);
        fadeIn.setToValue(0.5);
        fadeIn.setAutoReverse(false);
        
        fadeOut.setFromValue(0.5);
        fadeOut.setToValue(1.0);
        fadeOut.setCycleCount(1);
        fadeOut.setAutoReverse(false);
        
        n.setOnMouseEntered(e->{
            fadeIn.play();
        });
        n.setOnMouseExited(e->{
            fadeOut.play();
        });
        
    }
    
    public static void setScaleAnimation(Node node){
        ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(0.2), node);
        ScaleTransition scaleOut = new ScaleTransition(Duration.seconds(0.2), node);

        scaleIn.setToX(1.1);
        scaleIn.setToY(1.1);
        
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);

        node.setOnMouseEntered(e -> {
            scaleOut.stop();  
            scaleIn.playFromStart();
        });

        node.setOnMouseExited(e -> {
            scaleIn.stop();
            scaleOut.playFromStart();
        });
    }
    
    public static void setScaleAndFade(Node n){
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3),n);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3),n);
        ScaleTransition expand = new ScaleTransition(Duration.seconds(0.3),n);
        ScaleTransition reduce = new ScaleTransition(Duration.seconds(0.3),n);
        
        fadeIn.setFromValue(1.0);
        fadeIn.setToValue(0.5);
        fadeIn.setAutoReverse(false);
        
        expand.setToX(1.1);
        expand.setToY(1.1);
        
        n.setOnMouseEntered(e -> {
            expand.play();
            fadeIn.play();
        });
        
        fadeOut.setFromValue(0.5);
        fadeOut.setToValue(1.0);
        fadeOut.setCycleCount(1);
        fadeOut.setAutoReverse(false);
        
        reduce.setToX(1);
        reduce.setToY(1);
        
        n.setOnMouseExited(e -> {
            reduce.play();
            fadeOut.play();
        });
    }
}
