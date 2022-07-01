package controller;

import model.Patient;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import view.AcceuilPage;
import view.AjouterPatient;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class AjouterPatientController {
    private AjouterPatient ap;
    private Patient patient;
    public AjouterPatientController(AjouterPatient ap){
        this.ap=ap; this.patient= new Patient();
    }
    public void initControlleur() {
        ap.getImc().setEditable(false);
        //pour ajouter un patient
        ap.getAjouter().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Recuperer les infos des textfield
                String nom_patient,age,sexe,cholesterol,glucose,tas,tad,taille,poids,imc,tt,th;
                nom_patient=ap.getNom().getText().toString();
                age=ap.getAge().getText().toString();
                sexe= Objects.requireNonNull(ap.getSexe().getSelectedItem()).toString();
                cholesterol=ap.getChol().getText().toString();
                glucose= ap.getGlu().getText().toString();
                tas=ap.getTas().getText().toString();
                tad=ap.getTad().getText().toString();
                taille=ap.getTaille().getText().toString();
                poids=ap.getPoids().getText().toString();
                imc=ap.getImc().getText().toString();
                tt=ap.getTt().getText().toString();
                th=ap.getTh().getText().toString();
                //Stocker ces infos dans un patient qui sera ajouté
                patient.setNom_patient(nom_patient);
                patient.setAge(age);
                patient.setSexe(sexe);
                patient.setChol(cholesterol);
                patient.setGlu(glucose);
                patient.setTas(tas);
                patient.setTad(tad);
                patient.setTaille(taille);
                patient.setPoids(poids);
                patient.setImc(imc);
                patient.setTt(tt);
                patient.setTh(th);
                {
                    try {
                        patient.AddToOntology();
                        JOptionPane.showMessageDialog(null, "Patient ajouté!", "Information", JOptionPane.INFORMATION_MESSAGE);

                    } catch (OWLOntologyStorageException ex) {
                        JOptionPane.showMessageDialog(null, "Revoyez vos attributs", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } catch (OWLOntologyCreationException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        ap.getAccueil().addMouseListener(new MouseAdapter() {  //Modifier les informations du patient dans l'ontologie lorsqu'on clique sur le bouton "Modifier",
            //remplacer getAjouter()par getModifier()
            public void mouseClicked(MouseEvent ev) {
                ap.setVisible(false);
                }
            }
        );
        ///////////
        ap.getNom().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_0 || ev.getKeyCode() == KeyEvent.VK_1 || ev.getKeyCode() == KeyEvent.VK_2 || ev.getKeyCode() == KeyEvent.VK_3 || ev.getKeyCode() == KeyEvent.VK_4
                        || ev.getKeyCode() == KeyEvent.VK_5 || ev.getKeyCode() == KeyEvent.VK_6 || ev.getKeyCode() == KeyEvent.VK_7 || ev.getKeyCode() == KeyEvent.VK_8 || ev.getKeyCode() == KeyEvent.VK_9 ||
                        ev.getKeyCode() == KeyEvent.VK_NUMPAD0 || ev.getKeyCode() == KeyEvent.VK_NUMPAD1 || ev.getKeyCode() == KeyEvent.VK_NUMPAD2 || ev.getKeyCode() == KeyEvent.VK_NUMPAD3 || ev.getKeyCode() == KeyEvent.VK_NUMPAD4
                        || ev.getKeyCode() == KeyEvent.VK_NUMPAD5 || ev.getKeyCode() == KeyEvent.VK_NUMPAD6 || ev.getKeyCode() == KeyEvent.VK_NUMPAD7 || ev.getKeyCode() == KeyEvent.VK_NUMPAD8 || ev.getKeyCode() == KeyEvent.VK_NUMPAD9)
                {JOptionPane.showMessageDialog(null, "le nom ne peut contenir de chiffres","Error",JOptionPane.ERROR_MESSAGE);
                    ap.getNom().setText("");
                }
            }
        });
        ///////////////////////////////////////////////////////////////////
        ap.getAge().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "l'âge ne peut contir de lettres","Error",JOptionPane.ERROR_MESSAGE);
                    ap.getAge().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////////////////
        ap.getChol().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le cholesterol ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    ap.getChol().setText("");
                }
            }
        });
        /////////////////////////////////////////////////////////////////
        ap.getGlu().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le glucose ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    ap.getGlu().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////////////////
        ap.getTas().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le tas ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    ap.getTas().setText("");
                }
            }
        });
        ////////////////////////////////////////////////
        ap.getTad().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le tad ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    ap.getTad().setText("");
                }
            }
        });
        ////////////////////////////////////////////////////
        ap.getTaille().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "la taille ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    ap.getTaille().setText("");
                }
                //code calcul automatique du bmi
                else
                {
                    if((!Objects.equals(ap.getTaille().getText(), ""))) //si le champ n'est pas vide
                    {
                        if (Objects.equals(ap.getPoids().getText(), "")) {
                        ap.getImc().setText("");
                        }
                        else
                        { double bmicalcule = Double.parseDouble(ap.getPoids().getText()) / Math.pow((Double.parseDouble(ap.getTaille().getText()) * 0.01), 2);
                        ap.getImc().setText(String.valueOf(bmicalcule));ap.getImc().setEditable(false);}
                    }
                }
            }
        });
        //////////////////////////////////////////////////////
        ap.getPoids().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le poids ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    ap.getPoids().setText("");
                }
              //code calcul automatique du bmi
                else
                {
                    if(!Objects.equals(ap.getPoids().getText(), "")){ //si le champ n'est pas vide
                     if(Objects.equals(ap.getTaille().getText(), "")){ap.getImc().setText("");}
                     else{double bmicalcule = Double.parseDouble(ap.getPoids().getText())/Math.pow((Double.parseDouble(ap.getTaille().getText())*0.01),2);
                        ap.getImc().setText(String.valueOf(bmicalcule));ap.getImc().setEditable(false);}
                    }
                }
            }
        });
        //////////////////////////////////////////////////////
        ap.getImc().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "l'imc' ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    ap.getImc().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////
        ap.getTt().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le tt ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    ap.getTt().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////
        ap.getTh().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le th ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    ap.getTh().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////

    }


}
