package view;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

public class prediction extends JFrame{

    JTextArea monaffichage;
    JButton visiong,retour;
    public prediction() {

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
        panel.add(monaffichage);


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

    public JButton getVisiong() {
        return visiong;
    }

    public void setVisiong(JButton visiong) {
        this.visiong = visiong;
    }

    public JButton getRetour() {
        return retour;
    }

    public void setRetour(JButton retour) {
        this.retour = retour;
    }



}
