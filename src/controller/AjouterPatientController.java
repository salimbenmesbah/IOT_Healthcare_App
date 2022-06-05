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
import javax.swing.*;

public class AjouterPatientController {
    private AjouterPatient ap;
    private Patient patient;
    public AjouterPatientController(AjouterPatient ap) {
        this.ap=ap; this.patient= new Patient();
    }
    public void initControlleur() {
        //pour ajouter un patient
        ap.getAjouter().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Recuperer les infos des textfield
                String nom_patient,age,sexe,cholesterol,glucose,tas,tad,taille,poids,imc,tt,th;
                nom_patient=ap.getNom().getText().toString();
                age=ap.getAge().getText().toString();
                sexe=ap.getSexe().getSelectedItem().toString();
                cholesterol=ap.getChol().getText().toString();
                glucose= ap.getGlu().getText().toString();
                tas=ap.getTas().getText().toString();
                tad=ap.getTad().getText().toString();
                taille=ap.getTaille().getText().toString();
                poids=ap.getPoids().getText().toString();
                imc=ap.getImc().getText().toString();
                tt=ap.getTt().getText().toString();
                th=ap.getTh().getText().toString();
                //Stocker ces infos dans un patient (celui qui est instancié lors du constructeur)
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
                    patient.AddToOntology();
                    JOptionPane.showMessageDialog(null, "Patient ajouté ","Information",JOptionPane.INFORMATION_MESSAGE);

                } catch (OWLOntologyStorageException ex) {
                    JOptionPane.showMessageDialog(null, "Revoyez vos attributs","Erreur",JOptionPane.ERROR_MESSAGE);
                } catch (OWLOntologyCreationException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        ap.getAccueil().addMouseListener(new MouseAdapter() {  //Modifier les informations du patient dans l'ontologie lorsqu'on clique sur le bouton "Modifier",
            //remplacer getAjouter()par getModifier()
            public void mouseClicked(MouseEvent ev) {
                ap.setVisible(false);
            }
        });
    }


}
