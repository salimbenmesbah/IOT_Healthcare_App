package view;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.*;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;

public class prediction extends JFrame{

    private double[] resultatDuPAtient;
    JTextArea monaffichage;
    JButton retour;
    JScrollPane scrollText;



    public prediction(double[] resultatDuPatient) {
        this.resultatDuPAtient = resultatDuPatient;
        //dessinViewPrediction;
        this.setBackground(Color.WHITE);
        this.setBounds(100, 100, 1010, 554);
        this.getContentPane().setLayout(null);
        this.setResizable(false);
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 996, 517);
        panel.setLayout(null);
        getContentPane().add(panel);
        getContentPane().setBackground(Color.WHITE);
        this.setBackground(Color.WHITE);
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(prediction.class.getResource("/images/Health.png")));
        lblNewLabel.setBounds(462, 40, 566, 356);
        panel.add(lblNewLabel);

        monaffichage = new JTextArea();
        monaffichage.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        monaffichage.setBounds(10, 135, 400, 171);
        if (resultatDuPatient != null)
        {
            monaffichage.append("La probabilité que vous n'ayez pas le diabète est de\n"+resultatDuPatient[1]*100+"%\n");
            monaffichage.append("La probabilité que vous ayez le diabète est de\n"+resultatDuPatient[0]*100+"%\n");
        }
        if (resultatDuPatient[1]>resultatDuPatient[0]){
            monaffichage.append("Donc vous avez plus de chance de ne pas être atteint du diabète\n");
        }
        else {
            monaffichage.append("Donc vous avez plus de chance d'être diabètique\n");
        }
//        JScrollPane scrollText = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        scrollText.add(monaffichage);
        panel.add(monaffichage);
//        panel.add(scrollText);


        JLabel titre = new JLabel("Résultat");
        titre.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 18));

        titre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        titre.setBounds(309, 10, 101, 44);
        panel.add(titre);

        retour = new JButton("Retour");
        retour.setBounds(55, 437, 133, 44);
        panel.add(retour);

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public JTextArea getMonaffichage() {
        return monaffichage;
    }

    public void setMonaffichage(JTextArea monaffichage) {
        this.monaffichage = monaffichage;
    }

    public JButton getRetour() {
        return retour;
    }

    public void setRetour(JButton retour) {
        this.retour = retour;
    }

    public double[] getResultatDuPAtient() {
        return resultatDuPAtient;
    }
    public void setResultatDuPAtient(double[] resultatDuPAtient) {
        this.resultatDuPAtient = resultatDuPAtient;
    }

}
