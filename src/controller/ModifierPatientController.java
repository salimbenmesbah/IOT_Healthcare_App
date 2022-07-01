package controller;

import model.FuzzyOntology;
import model.Patient;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import view.AcceuilPage;
import view.ModifierPatient;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModifierPatientController {
    private ModifierPatient modifier_infos;
    private Patient patient;

    //constructeur////////////////////////////////
    public ModifierPatientController(ModifierPatient modifier_infos, Patient patient) {
        this.modifier_infos = modifier_infos;
        this.patient = patient;
        // initControlleur();
    }

    public void initControlleur() {
        //chargement des données du patient dans les textfield//////////////////////////
        modifier_infos.getNom().setText(patient.getNom_patient());
        modifier_infos.getAge().setText(patient.getAge());
        if (Objects.equals(patient.getSexe(), "MALE")) {
            modifier_infos.getSexe().setSelectedIndex(0);
        }
        if (Objects.equals(patient.getSexe(), "FEMALE")) {
            modifier_infos.getSexe().setSelectedIndex(1);
        }
        modifier_infos.getChol().setText(patient.getChol());
        modifier_infos.getGlu().setText(patient.getGlu());
        modifier_infos.getTas().setText(patient.getTas());
        modifier_infos.getTad().setText(patient.getTad());
        modifier_infos.getTaille().setText(patient.getTaille());
        modifier_infos.getPoids().setText(patient.getPoids());
        modifier_infos.getImc().setText(patient.getImc());
        modifier_infos.getTt().setText(patient.getTt());
        modifier_infos.getTh().setText(patient.getTh());

        modifier_infos.getModifier().addMouseListener(new MouseAdapter() {  //Modifier les informations du patient dans l'ontologie lorsqu'on clique sur le bouton "Modifier",
            //remplacer getAjouter()par getModifier()
            public void mouseClicked(MouseEvent ev) {
                FuzzyOntology NotreOntologie = null;
                String nom_patient, age, sexe, cholesterol, glucose, tas, tad, taille, poids, imc, tt, th;
                // prendre les infos des TextFields actuels
                nom_patient = modifier_infos.getNom().getText().toString();
                age = modifier_infos.getAge().getText().toString();
                sexe = modifier_infos.getSexe().getSelectedItem().toString();
                cholesterol = modifier_infos.getChol().getText().toString();
                glucose = modifier_infos.getGlu().getText().toString();
                tas = modifier_infos.getTas().getText().toString();
                tad = modifier_infos.getTad().getText().toString();
                taille = modifier_infos.getTaille().getText().toString();
                poids = modifier_infos.getPoids().getText().toString();
                imc = modifier_infos.getImc().getText().toString();
                tt = modifier_infos.getTt().getText().toString();
                th = modifier_infos.getTh().getText().toString();
                //enregistrer ces infos dans les attributs de la classe du nouveau patient issu de la modification
                Patient p2 =  new Patient();
                p2.setNom_patient(nom_patient);
                p2.setAge(age);
                p2.setSexe(sexe);
                p2.setChol(cholesterol);
                p2.setGlu(glucose);
                p2.setTas(tas);
                p2.setTad(tad);
                p2.setTaille(taille);
                p2.setPoids(poids);
                p2.setImc(imc);
                p2.setTt(tt);
                p2.setTh(th);

                try {//chargement de l'ontologie
                    NotreOntologie = new FuzzyOntology("C:\\Users\\PC-Service\\IdeaProjects\\IOT_Healthcare_App\\src\\ontologie\\OntologieFinale.owl");
                } catch (OWLOntologyCreationException ex) {
                    Logger.getLogger(ModifierPatientController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {//Supprimer l'ancien patient
                    if (NotreOntologie != null)
                        NotreOntologie.DELETE("patient", patient.getNom_patient());
                } catch (OWLOntologyCreationException | OWLOntologyStorageException e) {
                    Logger.getLogger(ModifierPatientController.class.getName()).log(Level.SEVERE, null, e);
                }
                try { // rajouter le nouveau patient
                    p2.AddToOntology();
                } catch (OWLOntologyStorageException | OWLOntologyCreationException e) {
                    Logger.getLogger(ModifierPatientController.class.getName()).log(Level.SEVERE, null, e);
                }// notifier que la patient a bien été modifié

                JOptionPane.showMessageDialog(null, "Patient Modifié!", "Information", JOptionPane.INFORMATION_MESSAGE);


            }
        });

        modifier_infos.getAccueil().addMouseListener(new MouseAdapter() {  //Modifier les informations du patient dans l'ontologie lorsqu'on clique sur le bouton "Modifier",
            //remplacer getAjouter()par getModifier()
            public void mouseClicked(MouseEvent ev) {
                modifier_infos.setVisible(false);
            }
        });

        ///////////////////////////////
        modifier_infos.getNom().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_0 || ev.getKeyCode() == KeyEvent.VK_1 || ev.getKeyCode() == KeyEvent.VK_2 || ev.getKeyCode() == KeyEvent.VK_3 || ev.getKeyCode() == KeyEvent.VK_4
                        || ev.getKeyCode() == KeyEvent.VK_5 || ev.getKeyCode() == KeyEvent.VK_6 || ev.getKeyCode() == KeyEvent.VK_7 || ev.getKeyCode() == KeyEvent.VK_8 || ev.getKeyCode() == KeyEvent.VK_9 ||
                        ev.getKeyCode() == KeyEvent.VK_NUMPAD0 || ev.getKeyCode() == KeyEvent.VK_NUMPAD1 || ev.getKeyCode() == KeyEvent.VK_NUMPAD2 || ev.getKeyCode() == KeyEvent.VK_NUMPAD3 || ev.getKeyCode() == KeyEvent.VK_NUMPAD4
                        || ev.getKeyCode() == KeyEvent.VK_NUMPAD5 || ev.getKeyCode() == KeyEvent.VK_NUMPAD6 || ev.getKeyCode() == KeyEvent.VK_NUMPAD7 || ev.getKeyCode() == KeyEvent.VK_NUMPAD8 || ev.getKeyCode() == KeyEvent.VK_NUMPAD9)
                {JOptionPane.showMessageDialog(null, "le nom ne peut contenir de chiffres","Error",JOptionPane.ERROR_MESSAGE);
                    modifier_infos.getNom().setText("");
                }
            }
        });
        ///////////////////////////////////////////////////////////////////
        modifier_infos.getAge().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "l'âge ne peut contir de lettres","Error",JOptionPane.ERROR_MESSAGE);
                    modifier_infos.getAge().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////////////////
        modifier_infos.getChol().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le cholesterol ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    modifier_infos.getChol().setText("");
                }
            }
        });
        /////////////////////////////////////////////////////////////////
        modifier_infos.getGlu().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le glucose ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    modifier_infos.getGlu().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////////////////
        modifier_infos.getTas().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le tas ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    modifier_infos.getTas().setText("");
                }
            }
        });
        ////////////////////////////////////////////////
        modifier_infos.getTad().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le tad ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    modifier_infos.getTad().setText("");
                }
            }
        });
        ////////////////////////////////////////////////////
        modifier_infos.getTaille().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "la taille ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    modifier_infos.getTaille().setText("");
                }
                //code calcul automatique du bmi
                else
                {
                    if((!Objects.equals(modifier_infos.getTaille().getText(), ""))) //si le champ n'est pas vide
                    {
                        if (Objects.equals(modifier_infos.getPoids().getText(), "")) {
                            modifier_infos.getImc().setText("");
                        }
                        else
                        { double bmicalcule = Double.parseDouble(modifier_infos.getPoids().getText()) / Math.pow((Double.parseDouble(modifier_infos.getTaille().getText()) * 0.01), 2);
                            modifier_infos.getImc().setText(String.valueOf(bmicalcule));modifier_infos.getImc().setEditable(false);}
                    }
                }
            }
        });
        //////////////////////////////////////////////////////
        modifier_infos.getPoids().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le poids ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    modifier_infos.getPoids().setText("");
                }
                //code calcul automatique du bmi
                else
                {
                    if(!Objects.equals(modifier_infos.getPoids().getText(), "")){ //si le champ n'est pas vide
                        if(Objects.equals(modifier_infos.getTaille().getText(), "")){modifier_infos.getImc().setText("");}
                        else{double bmicalcule = Double.parseDouble(modifier_infos.getPoids().getText())/Math.pow((Double.parseDouble(modifier_infos.getTaille().getText())*0.01),2);
                            modifier_infos.getImc().setText(String.valueOf(bmicalcule));modifier_infos.getImc().setEditable(false);}
                    }
                }
            }
        });
        //////////////////////////////////////////////////////
        modifier_infos.getImc().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "l'imc' ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    modifier_infos.getImc().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////
        modifier_infos.getTt().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le tt ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    modifier_infos.getTt().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////
        modifier_infos.getTh().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le th ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    modifier_infos.getTh().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////
    }
}
