package controller;

import model.FuzzyOntology;
import model.Patient;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import view.ModifierPatient;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModifierPatientController {
    private ModifierPatient modifier_infos;
    private Patient patient;

    //constructeur////////////////////////////////
    public ModifierPatientController(ModifierPatient modifier_infos, Patient patient)  {
        this.modifier_infos = modifier_infos;
        this.patient = patient;
        // initControlleur();
    }
    public void initControlleur() {
        int i =0;//chargement des données du patient dans les textfield//////////////////////////
        modifier_infos.getNom().setText(patient.getNom_patient());
        modifier_infos.getAge().setText(patient.getAge());
        modifier_infos.getSexe().getSelectedItem();
        if(patient.getSexe().equals("FEMALE")){i=1;}
        modifier_infos.getSexe().setSelectedIndex(i);
        modifier_infos.getChol().setText(patient.getChol());
        modifier_infos.getGlu().setText(patient.getGlu());
        modifier_infos.getTas().setText(patient.getTas());
        modifier_infos.getTad().setText(patient.getTad());
        modifier_infos.getTaille().setText(patient.getTaille());
        modifier_infos.getPoids().setText(patient.getPoids());
        modifier_infos.getImc().setText(patient.getImc());
        modifier_infos.getTt().setText(patient.getTt());
        modifier_infos.getTh().setText(patient.getTh());
        modifier_infos.getModifier().addMouseListener(new MouseAdapter()
        {  //Modifier les informations du patient dans l'ontologie lorsqu'on clique sur le bouton "Modifier",
            //remplacer getAjouter()par getModifier()
            public void mouseClicked (MouseEvent ev)
            {

                FuzzyOntology NotreOntologie = null;
                String nom_patient,age,sexe,cholesterol,glucose,tas,tad,taille,poids,imc,tt,th;// prendre les infos des TextFields actuels
                nom_patient=modifier_infos.getNom().getText().toString();
                age=modifier_infos.getAge().getText().toString();
                sexe=modifier_infos.getSexe().getSelectedItem().toString();
                cholesterol=modifier_infos.getChol().getText().toString();
                glucose= modifier_infos.getGlu().getText().toString();
                tas=modifier_infos.getTas().getText().toString();
                tad=modifier_infos.getTad().getText().toString();
                taille=modifier_infos.getTaille().getText().toString();
                poids=modifier_infos.getPoids().getText().toString();
                imc=modifier_infos.getImc().getText().toString();
                tt=modifier_infos.getTt().getText().toString();
                th=modifier_infos.getTh().getText().toString();
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

                try {//chargement de l'ontologie
                    NotreOntologie = new FuzzyOntology("C:\\Users\\USER\\IdeaProjects\\PFE_Master\\src\\ontologie\\OntologieFinale.owl");
                } catch (OWLOntologyCreationException ex) {
                    Logger.getLogger(ModifierPatientController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {//Supprimer l'ancien patient
                    if (NotreOntologie != null)
                        NotreOntologie.DELETE("Patient", patient.getNom_patient());
                    }
                    catch (OWLOntologyCreationException | OWLOntologyStorageException e)
                    {
                    Logger.getLogger(ModifierPatientController.class.getName()).log(Level.SEVERE, null, e);
                    }
                try { // rajouter le nouveau patient
                    patient.AddToOntology();
                } catch (OWLOntologyStorageException e) {
                    Logger.getLogger(ModifierPatientController.class.getName()).log(Level.SEVERE, null, e);
                }// notifier que la patient a bien été modifié
                JOptionPane.showMessageDialog(null, "Patient Modifier ","Information",JOptionPane.INFORMATION_MESSAGE);





            }
        });
    }
}
