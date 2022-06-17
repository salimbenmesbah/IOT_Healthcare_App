package controller;

import model.Patient;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import smile.License;
import smile.Network;
import view.AcceuilPage;
import view.InferencePatient;
import view.prediction;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InferencePatientController {
    private InferencePatient inferencePatient;
    private Patient patient;
    Network net;
    public static License Notrelicense = new smile.License(
            "SMILE LICENSE d41161b6 8b72612c ed417b5a " +
                    "THIS IS AN ACADEMIC LICENSE AND CAN BE USED " +
                    "SOLELY FOR ACADEMIC RESEARCH AND TEACHING, " +
                    "AS DEFINED IN THE BAYESFUSION ACADEMIC " +
                    "SOFTWARE LICENSING AGREEMENT. " +
                    "Serial #: en05exaj9ztpxsp2znslb6es6 " +
                    "Issued for: ta meree (laughoutloudly417@gmail.com) " +
                    "Academic institution: Saad dahleb Blida1 " +
                    "Valid until: 2022-11-24 " +
                    "Issued by BayesFusion activation server",
            new byte[]
                    {
                            -116,120,-3,100,57,-127,27,-41,10,19,4,-86,-88,-126,-15,86,
                            -47,59,-93,-127,-3,-101,-56,-63,-100,75,11,-32,86,107,-91,53,
                            -30,82,42,-14,-34,-118,-103,-79,22,-128,-51,1,96,53,119,38,
                            80,54,58,-1,107,-47,-26,42,49,64,100,38,-115,-121,111,27
                    }
    );
    public InferencePatientController(InferencePatient inferencePatient,Patient patient){
        this.inferencePatient=inferencePatient;
        this.patient=patient;
            System.loadLibrary("jsmile");
            Network net = new Network();
            // load the network created by Tutorial1
            net.readFile("C:\\Users\\PC-Service\\IdeaProjects\\IOT_Healthcare_App\\src\\réseauxBayésiens\\RéseauBayesienClassique2.xdsl");

    }
    public void initController(){
        int i =0;//chargement des données du patient dans les textfield//////////////////////////
        inferencePatient.getNom().setText(patient.getNom_patient());
        inferencePatient.getAge().setText(patient.getAge());
        inferencePatient.getSexe().getSelectedItem();
        if(patient.getSexe().equals("FEMALE")){i=1;}
        inferencePatient.getSexe().setSelectedIndex(i);
        inferencePatient.getChol().setText(patient.getChol());
        inferencePatient.getGlu().setText(patient.getGlu());
        inferencePatient.getTas().setText(patient.getTas());
        inferencePatient.getTad().setText(patient.getTad());
        inferencePatient.getTaille().setText(patient.getTaille());
        inferencePatient.getPoids().setText(patient.getPoids());
        inferencePatient.getImc().setText(patient.getImc());
        inferencePatient.getTt().setText(patient.getTt());
        inferencePatient.getTh().setText(patient.getTh());
        ///
        inferencePatient.getInferer().addMouseListener(new MouseAdapter() {  //recuperer les elements dans un noeud
            public void mouseClicked (MouseEvent ev) {
                String nom_patient,age,sexe,cholesterol,glucose,tas,tad,taille,poids,imc,tt,th;// prendre les infos des TextFields actuels
                nom_patient=inferencePatient.getNom().getText().toString();
                age=inferencePatient.getAge().getText().toString();
                sexe=inferencePatient.getSexe().getSelectedItem().toString();
                cholesterol=inferencePatient.getChol().getText().toString();
                glucose= inferencePatient.getGlu().getText().toString();
                tas=inferencePatient.getTas().getText().toString();
                tad=inferencePatient.getTad().getText().toString();
                taille=inferencePatient.getTaille().getText().toString();
                poids=inferencePatient.getPoids().getText().toString();
                imc=inferencePatient.getImc().getText().toString();
                tt=inferencePatient.getTt().getText().toString();
                th=inferencePatient.getTh().getText().toString();
                //enregistrer ces infos dans les attributs de la classe patient
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
                try {
                    patient.FuzzificationPatient(net);
                } catch (OWLOntologyStorageException e) {
                    throw new RuntimeException(e);
                }
                double[] resultat = patient.getResultat();
                prediction pframe = new prediction(resultat);
                predictionController pframecontrolleur = new predictionController(pframe);
                pframe.setVisible(true);
                pframecontrolleur.initControlleur();

            }
    });
        inferencePatient.getAccueil().addMouseListener(new MouseAdapter() {  //Modifier les informations du patient dans l'ontologie lorsqu'on clique sur le bouton "Modifier",
            //remplacer getAjouter()par getModifier()
            public void mouseClicked(MouseEvent ev) {
                AcceuilPage mnv=new AcceuilPage();
                AcceuilPageController c;
                try {
                    c = new AcceuilPageController(mnv);
                    inferencePatient.setVisible(false);
                    c.initControlleur();
                } catch (Exception ex) {
                    Logger.getLogger(AjouterPatientController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        /////////
        inferencePatient.getNom().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_0 || ev.getKeyCode() == KeyEvent.VK_1 || ev.getKeyCode() == KeyEvent.VK_2 || ev.getKeyCode() == KeyEvent.VK_3 || ev.getKeyCode() == KeyEvent.VK_4
                        || ev.getKeyCode() == KeyEvent.VK_5 || ev.getKeyCode() == KeyEvent.VK_6 || ev.getKeyCode() == KeyEvent.VK_7 || ev.getKeyCode() == KeyEvent.VK_8 || ev.getKeyCode() == KeyEvent.VK_9 ||
                        ev.getKeyCode() == KeyEvent.VK_NUMPAD0 || ev.getKeyCode() == KeyEvent.VK_NUMPAD1 || ev.getKeyCode() == KeyEvent.VK_NUMPAD2 || ev.getKeyCode() == KeyEvent.VK_NUMPAD3 || ev.getKeyCode() == KeyEvent.VK_NUMPAD4
                        || ev.getKeyCode() == KeyEvent.VK_NUMPAD5 || ev.getKeyCode() == KeyEvent.VK_NUMPAD6 || ev.getKeyCode() == KeyEvent.VK_NUMPAD7 || ev.getKeyCode() == KeyEvent.VK_NUMPAD8 || ev.getKeyCode() == KeyEvent.VK_NUMPAD9)
                {
                    JOptionPane.showMessageDialog(null, "le nom ne peut contenir de chiffres","Error",JOptionPane.ERROR_MESSAGE);
                    inferencePatient.getNom().setText("");
                }
            }
        });
        ///////////////////////////////////////////////////////////////////
        inferencePatient.getAge().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "l'âge ne peut contir de lettres","Error",JOptionPane.ERROR_MESSAGE);
                    inferencePatient.getAge().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////////////////
        inferencePatient.getChol().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le cholesterol ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    inferencePatient.getChol().setText("");
                }
            }
        });
        /////////////////////////////////////////////////////////////////
        inferencePatient.getGlu().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le glucose ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    inferencePatient.getGlu().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////////////////
        inferencePatient.getTas().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
               if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le tas ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    inferencePatient.getTas().setText("");
                }
            }
        });
        ////////////////////////////////////////////////
        inferencePatient.getTad().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le tad ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    inferencePatient.getTad().setText("");
                }
            }
        });
        ////////////////////////////////////////////////////
        inferencePatient.getTaille().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "la taille ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    inferencePatient.getTaille().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////
        inferencePatient.getPoids().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le poids ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    inferencePatient.getPoids().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////
        inferencePatient.getImc().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "l'imc' ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    inferencePatient.getImc().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////
        inferencePatient.getTt().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le tt ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    inferencePatient.getTt().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////
        inferencePatient.getTh().addKeyListener(new KeyAdapter() {
            public void keyReleased (KeyEvent ev) {
                if(ev.getKeyCode() == KeyEvent.VK_A || ev.getKeyCode() == KeyEvent.VK_Z || ev.getKeyCode() == KeyEvent.VK_E || ev.getKeyCode() == KeyEvent.VK_R || ev.getKeyCode() == KeyEvent.VK_T || ev.getKeyCode() == KeyEvent.VK_Y || ev.getKeyCode() == KeyEvent.VK_U || ev.getKeyCode() == KeyEvent.VK_I || ev.getKeyCode() == KeyEvent.VK_O || ev.getKeyCode() == KeyEvent.VK_P
                        || ev.getKeyCode() == KeyEvent.VK_Q || ev.getKeyCode() == KeyEvent.VK_S || ev.getKeyCode() == KeyEvent.VK_D || ev.getKeyCode() == KeyEvent.VK_F || ev.getKeyCode() == KeyEvent.VK_G || ev.getKeyCode() == KeyEvent.VK_H || ev.getKeyCode() == KeyEvent.VK_J || ev.getKeyCode() == KeyEvent.VK_K || ev.getKeyCode() == KeyEvent.VK_L || ev.getKeyCode() == KeyEvent.VK_M || ev.getKeyCode() == KeyEvent.VK_W
                        || ev.getKeyCode() == KeyEvent.VK_X || ev.getKeyCode() == KeyEvent.VK_C || ev.getKeyCode() == KeyEvent.VK_V || ev.getKeyCode() == KeyEvent.VK_B || ev.getKeyCode() == KeyEvent.VK_N ){
                    JOptionPane.showMessageDialog(null, "le th ne contient pas des lettres","Error",JOptionPane.ERROR_MESSAGE);
                    inferencePatient.getTh().setText("");
                }
            }
        });
        //////////////////////////////////////////////////////












    }}
