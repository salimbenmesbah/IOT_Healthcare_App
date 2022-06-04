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
    {  //Modifier les informations du patient dans l'ontologie lorsqu'on clique sur le bouton "Modifier",
        //remplacer getAjouter()par getModifier()
        public void mouseClicked (MouseEvent ev)
        {
            predictionaffichage.setVisible(false);
        }
    });
}}
