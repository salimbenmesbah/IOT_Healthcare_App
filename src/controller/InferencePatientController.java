package controller;

import model.Patient;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import smile.Network;
import view.InferencePatient;
import view.prediction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InferencePatientController {
    private InferencePatient inferencePatient;
    private Patient patient;
    Network net;
    public InferencePatientController(InferencePatient inferencePatient,Patient patient){
        this.inferencePatient=inferencePatient;
        this.patient=patient;
        new smile.License(
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
        System.loadLibrary("jsmile");
        Network net = new Network();
        // load the network created by Tutorial1
        net.readFile("C:\\Users\\USER\\IdeaProjects\\PFE_Master\\src\\réseauxBayésiens\\RéseauBayesienClassique2.xdsl");

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
                inferencePatient.setVisible(false);
            }
        });












}}
