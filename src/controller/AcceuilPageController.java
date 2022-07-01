package controller;

import model.FuzzyOntology;
import model.Patient;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import view.AcceuilPage;
import view.AjouterPatient;
import view.InferencePatient;
import view.ModifierPatient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AcceuilPageController {
    AcceuilPage acceuil;
    FuzzyOntology o;

    LinkedList<String> e;
    DefaultTableModel model = new DefaultTableModel();

    //constructeur
    public AcceuilPageController(AcceuilPage acceuil) throws Exception {
        this.acceuil = acceuil;
//        model.addColumn("Identifiant");
        model.addColumn("Patient");
        model.addColumn("Age");
        model.addColumn("Sexe");
        model.addColumn("CHOL");
        model.addColumn("GLU");
        model.addColumn("TAS");
        model.addColumn("TAD");
        model.addColumn("Taille");
        model.addColumn("Poids");
        model.addColumn("IMC");
        model.addColumn("TT");
        model.addColumn("TH");
        model.addColumn("Diagnostic");
    }

    public void initControlleur() {

        acceuil.getAjouter().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AjouterPatient frame2 = new AjouterPatient();
                frame2.setVisible(true);
                AjouterPatientController frame2controlleur = new AjouterPatientController(frame2);
                frame2controlleur.initControlleur();
            }


        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        acceuil.getModifier().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (acceuil.getTable().getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Veuillez selectionner le patient à modifier","Information",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                ModifierPatient frame3 = new ModifierPatient();
                frame3.setVisible(true);
                int indice_ligne_patient = acceuil.getTable().getSelectedRow();
                ModifierPatientController frame3controlleur = new ModifierPatientController(frame3, new Patient(acceuil.getTable().getValueAt(indice_ligne_patient, 0).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 1).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 2).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 3).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 4).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 5).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 6).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 7).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 8).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 9).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 10).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 11).toString()));
                frame3controlleur.initControlleur();
                }
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        acceuil.getInference().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InferencePatient frame4 = new InferencePatient();
                frame4.setVisible(true);
                int indice_ligne_patient = acceuil.getTable().getSelectedRow();
                InferencePatientController frame4controlleur = null;
                frame4controlleur = new InferencePatientController(frame4, new Patient(acceuil.getTable().getValueAt(indice_ligne_patient, 0).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 1).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 2).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 3).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 4).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 5).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 6).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 7).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 8).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 9).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 10).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 11).toString()));
                frame4controlleur.initController();

            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        acceuil.getActualiser().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model.setRowCount(0);
                try {
                    o = new FuzzyOntology("C:\\Users\\PC-Service\\IdeaProjects\\IOT_Healthcare_App\\src\\ontologie\\OntologieFinale.owl");
                } catch (OWLOntologyCreationException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    ArrayList<String> fdt = o.getIndividulsByClass("patient");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println(o.patient.size() + "" + o.age.size() + "" + o.gender.size() + "" + o.cholesterol.size());
                for (int i = 0; i < o.patient.size(); i++) {
                    model.addRow(new Object[]{o.patient.get(i), o.age.get(i), o.gender.get(i), o.cholesterol.get(i),
                            o.glucose.get(i), o.systolic_bp.get(i), o.diastolic_bp.get(i),
                            o.height.get(i), o.weight.get(i),
                            o.bmi.get(i), o.waist.get(i), o.hip.get(i),o.Diagnostic_Final.get(i)});
                }
                acceuil.getTable().setModel(model);

            }

        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        acceuil.getChercher().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setRowCount(0);
                try {
                    o = new FuzzyOntology("C:\\Users\\PC-Service\\IdeaProjects\\IOT_Healthcare_App\\src\\ontologie\\OntologieFinale.owl");
                } catch (OWLOntologyCreationException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    ArrayList<String> fdt = o.getIndividulsByClass("patient");
                    for (int i = 0; i < o.patient.size(); i++) {
                        if (o.patient.get(i).contains(acceuil.getRecherche().getText())) {
                            model.addRow(new Object[]{o.patient.get(i), o.age.get(i), o.gender.get(i), o.cholesterol.get(i),
                                    o.glucose.get(i), o.systolic_bp.get(i), o.diastolic_bp.get(i),
                                    (int) Math.round(Float.parseFloat(o.height.get(i))), (int) Math.round(Float.parseFloat(o.weight.get(i))),
                                    o.bmi.get(i), o.waist.get(i), o.hip.get(i),o.Diagnostic_Final.get(i)});
                        }
                    }
                    if(model.getRowCount()==0){
                        JOptionPane.showMessageDialog(null, "Aucun résultat trouvé","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else {acceuil.getTable().setModel(model);}
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        acceuil.getSupprimer().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // s'assurer qu'un patient est selectionné
                if (acceuil.getTable().getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Veuillez selectionner le patient à supprimer","Information",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    int indice_ligne_patient = acceuil.getTable().getSelectedRow();
                    System.out.println(indice_ligne_patient);
                    try {
                        o = new FuzzyOntology("C:\\Users\\PC-Service\\IdeaProjects\\IOT_Healthcare_App\\src\\ontologie\\OntologieFinale.owl");
                        o.DELETE("patient",acceuil.getTable().getValueAt(indice_ligne_patient, 0).toString());
                        JOptionPane.showMessageDialog(null, "Le patient \""+acceuil.getTable().getValueAt(indice_ligne_patient, 0).toString()+"\" a été supprimé ","Information",JOptionPane.INFORMATION_MESSAGE);
                        model.removeRow(indice_ligne_patient);
                        acceuil.getTable().setModel(model);
                    } catch (OWLOntologyCreationException | OWLOntologyStorageException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

        });

    }
}
