/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;

import javax.swing.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import javax.swing.JButton;

public class AjouterPatient extends JFrame {

    private JFrame frame;
    private JTextField nom;
    private JTextField age;
    private JTextField alb;
    private JTextField alp;
    private JTextField alt;
    private JTextField ast;
    private JTextField bil;
    private JTextField che;
    private JTextField chol;
    private JTextField crea;
    private JTextField ggt;
    private JTextField prot;
    private JButton ajouter, accueil;
    private JComboBox sexe;


    public AjouterPatient() {

        setBounds(100, 100, 894, 462);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        this.setResizable(false);

        this.setLocation(500, 300);
        JPanel paneln = new JPanel();
        paneln.setBackground(Color.WHITE);
        paneln.setBounds(0, 0, 900, 425);
        getContentPane().add(paneln);
        paneln.setLayout(null);

        JLabel lblNewLabel = new JLabel("Nom :");
        lblNewLabel.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        lblNewLabel.setBounds(31, 74, 98, 43);
        paneln.add(lblNewLabel);

        JLabel lblSexe = new JLabel("Age :");
        lblSexe.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        lblSexe.setBounds(31, 144, 98, 43);
        paneln.add(lblSexe);

        JLabel lblNewLabel_1_1 = new JLabel("Sexe : ");
        lblNewLabel_1_1.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(31, 209, 98, 43);
        paneln.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("ALB :");
        lblNewLabel_1_1_1.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        lblNewLabel_1_1_1.setBounds(31, 272, 98, 43);
        paneln.add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("ALP : ");
        lblNewLabel_1_1_1_1.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        lblNewLabel_1_1_1_1.setBounds(31, 336, 98, 43);
        paneln.add(lblNewLabel_1_1_1_1);

        JLabel lblNewLabel_1_1_1_2 = new JLabel("ALT :");
        lblNewLabel_1_1_1_2.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        lblNewLabel_1_1_1_2.setBounds(317, 74, 98, 43);
        paneln.add(lblNewLabel_1_1_1_2);

        JLabel lblNewLabel_1_1_1_2_1 = new JLabel("AST : ");
        lblNewLabel_1_1_1_2_1.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        lblNewLabel_1_1_1_2_1.setBounds(317, 144, 98, 43);
        paneln.add(lblNewLabel_1_1_1_2_1);

        JLabel lblNewLabel_1_1_1_2_1_1 = new JLabel("BIL :");
        lblNewLabel_1_1_1_2_1_1.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        lblNewLabel_1_1_1_2_1_1.setBounds(317, 209, 98, 43);
        paneln.add(lblNewLabel_1_1_1_2_1_1);

        JLabel lblNewLabel_1_1_1_2_1_1_1 = new JLabel("CHE :");
        lblNewLabel_1_1_1_2_1_1_1.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        lblNewLabel_1_1_1_2_1_1_1.setBounds(317, 272, 98, 43);
        paneln.add(lblNewLabel_1_1_1_2_1_1_1);

        JLabel lblNewLabel_1_1_1_2_1_1_1_1 = new JLabel("CHOL :");
        lblNewLabel_1_1_1_2_1_1_1_1.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        lblNewLabel_1_1_1_2_1_1_1_1.setBounds(317, 336, 98, 43);
        paneln.add(lblNewLabel_1_1_1_2_1_1_1_1);

        JLabel lblNewLabel_1_1_1_2_1_1_1_1_1 = new JLabel("CREA :");
        lblNewLabel_1_1_1_2_1_1_1_1_1.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        lblNewLabel_1_1_1_2_1_1_1_1_1.setBounds(594, 74, 98, 43);
        paneln.add(lblNewLabel_1_1_1_2_1_1_1_1_1);

        JLabel lblNewLabel_1_1_1_2_1_1_1_1_1_1 = new JLabel("GGT : ");
        lblNewLabel_1_1_1_2_1_1_1_1_1_1.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        lblNewLabel_1_1_1_2_1_1_1_1_1_1.setBounds(594, 144, 98, 43);
        paneln.add(lblNewLabel_1_1_1_2_1_1_1_1_1_1);

        JLabel lblNewLabel_1_1_1_2_1_1_1_1_1_1_1 = new JLabel("PROT :");
        lblNewLabel_1_1_1_2_1_1_1_1_1_1_1.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        lblNewLabel_1_1_1_2_1_1_1_1_1_1_1.setBounds(594, 209, 98, 43);
        paneln.add(lblNewLabel_1_1_1_2_1_1_1_1_1_1_1);

        nom = new JTextField();
        nom.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        nom.setBounds(103, 83, 164, 29);
        paneln.add(nom);
        nom.setColumns(10);

        age = new JTextField();
        age.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        age.setColumns(10);
        age.setBounds(103, 152, 164, 29);
        paneln.add(age);

        alb = new JTextField();
        alb.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        alb.setColumns(10);
        alb.setBounds(103, 280, 164, 29);
        paneln.add(alb);

        alp = new JTextField();
        alp.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        alp.setColumns(10);
        alp.setBounds(103, 344, 164, 29);
        paneln.add(alp);

        alt = new JTextField();
        alt.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        alt.setColumns(10);
        alt.setBounds(381, 83, 164, 29);
        paneln.add(alt);

        ast = new JTextField();
        ast.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        ast.setColumns(10);
        ast.setBounds(381, 152, 164, 29);
        paneln.add(ast);

        bil = new JTextField();
        bil.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        bil.setColumns(10);
        bil.setBounds(381, 217, 164, 29);
        paneln.add(bil);

        che = new JTextField();
        che.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        che.setColumns(10);
        che.setBounds(381, 280, 164, 29);
        paneln.add(che);

        chol = new JTextField();
        chol.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        chol.setColumns(10);
        chol.setBounds(381, 344, 164, 29);
        paneln.add(chol);

        crea = new JTextField();
        crea.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        crea.setColumns(10);
        crea.setBounds(667, 83, 164, 29);
        paneln.add(crea);

        ggt = new JTextField();
        ggt.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        ggt.setColumns(10);
        ggt.setBounds(667, 152, 164, 29);
        paneln.add(ggt);

        prot = new JTextField();
        prot.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        prot.setColumns(10);
        prot.setBounds(667, 217, 164, 29);
        paneln.add(prot);

        sexe = new JComboBox();
        sexe.setModel(new DefaultComboBoxModel(new String[] {"Homme", "Femme"}));
        sexe.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        sexe.setBounds(103, 217, 164, 29);
        paneln.add(sexe);

        /* JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(AjouterPatient.class.getResource("/images/logoi.png")));
        lblNewLabel_1.setBounds(-33, 0, 205, 64);
        paneln.add(lblNewLabel_1); */

        JLabel titre = new JLabel("Ajouter un patient ");
        titre.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD , 24));
        titre.setBounds(340, 10, 300, 30);
        //titre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        paneln.add(titre);

        ajouter = new JButton("Ajouter");
        ajouter.setBounds(667, 298, 164, 35);
        paneln.add(ajouter);
        accueil = new JButton("Accueil");
        accueil.setBounds(667, 349, 164, 35);
        paneln.add(accueil);
        JLabel bgc = new JLabel("");
        bgc.setIcon(new ImageIcon(AjouterPatient.class.getResource("/images/background.jpg")));
        bgc.setBounds(0, 62, 890, 366);
        paneln.add(bgc);
        this.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField getNom() {
        return nom;
    }

    public void setNom(JTextField nom) {
        this.nom = nom;
    }

    public JTextField getAge() {
        return age;
    }

    public void setAge(JTextField age) {
        this.age = age;
    }

    public JTextField getAlb() {
        return alb;
    }

    public void setAlb(JTextField alb) {
        this.alb = alb;
    }

    public JTextField getAlp() {
        return alp;
    }

    public void setAlp(JTextField alp) {
        this.alp = alp;
    }

    public JTextField getAlt() {
        return alt;
    }

    public void setAlt(JTextField alt) {
        this.alt = alt;
    }

    public JTextField getAst() {
        return ast;
    }

    public void setAst(JTextField ast) {
        this.ast = ast;
    }

    public JTextField getBil() {
        return bil;
    }

    public void setBil(JTextField bil) {
        this.bil = bil;
    }

    public JTextField getChe() {
        return che;
    }

    public void setChe(JTextField che) {
        this.che = che;
    }

    public JTextField getChol() {
        return chol;
    }

    public void setChol(JTextField chol) {
        this.chol = chol;
    }

    public JTextField getCrea() {
        return crea;
    }

    public JButton getAccueil() {
        return accueil;
    }

    public void setAccueil(JButton accueil) {
        this.accueil = accueil;
    }


    public void setCrea(JTextField crea) {
        this.crea = crea;
    }

    public JTextField getGgt() {
        return ggt;
    }

    public void setGgt(JTextField ggt) {
        this.ggt = ggt;
    }

    public JTextField getProt() {
        return prot;
    }

    public void setProt(JTextField prot) {
        this.prot = prot;
    }

    public JButton getAjouter() {
        return ajouter;
    }

    public void setAjouter(JButton ajouter) {
        this.ajouter = ajouter;
    }

    public JComboBox getSexe() {
        return sexe;
    }

    public void setSexe(JComboBox sexe) {
        this.sexe = sexe;
    }


}
















