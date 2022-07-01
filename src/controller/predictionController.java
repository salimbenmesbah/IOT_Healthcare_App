package controller;

import view.AcceuilPage;
import view.prediction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class predictionController {
private prediction predictionaffichage;

public predictionController(prediction predictionaffichage){
    this.predictionaffichage = predictionaffichage;
}
public void initControlleur(){
    predictionaffichage.getRetour().addMouseListener(new MouseAdapter()
    {
        public void mouseClicked (MouseEvent ev) {predictionaffichage.setVisible(false);}
    });
}}
