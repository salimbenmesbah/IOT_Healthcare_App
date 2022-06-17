package controller;

import model.FuzzyOntology;
import model.Patient;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import view.AcceuilPage;
import view.AjouterPatient;
import view.InferencePatient;
import view.ModifierPatient;

import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class AcceuilPageController {
    AcceuilPage acceuil;
    FuzzyOntology o;

    LinkedList<String> e;
    DefaultTableModel model = new DefaultTableModel();

    //constructeur
    public AcceuilPageController(AcceuilPage acceuil) throws Exception {
        this.acceuil = acceuil;
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
        o = new FuzzyOntology("C:\\Users\\PC-Service\\IdeaProjects\\IOT_Healthcare_App\\src\\ontologie\\OntologieFinale.owl");
    }

    public void initControlleur() {

//        continuer les events de modification de l'ontologie dans les boutons "ajouter modifier supprimer"
//        acceuil.getSupprimer();

        acceuil.getAjouter().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AjouterPatient frame2 = new AjouterPatient();
                frame2.setVisible(true);
                AjouterPatientController frame2controlleur = new AjouterPatientController(frame2);
                frame2controlleur.initControlleur();

            }


        });
        acceuil.getModifier().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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


        });
        acceuil.getInference().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InferencePatient frame4 = new InferencePatient();
                frame4.setVisible(true);
                int indice_ligne_patient = acceuil.getTable().getSelectedRow();
                InferencePatientController frame4controlleur = new InferencePatientController(frame4, new Patient(acceuil.getTable().getValueAt(indice_ligne_patient, 0).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 1).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 2).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 3).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 4).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 5).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 6).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 7).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 8).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 9).toString(), acceuil.getTable().getValueAt(indice_ligne_patient, 10).toString(),
                        acceuil.getTable().getValueAt(indice_ligne_patient, 11).toString()));
                frame4controlleur.initController();

            }
        });
        acceuil.getActualiser().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FuzzyOntology ontologiecharge;

                try {
                    ontologiecharge = new FuzzyOntology("C:\\Users\\PC-Service\\IdeaProjects\\IOT_Healthcare_App\\src\\ontologie\\OntologieFinale.owl");
                } catch (OWLOntologyCreationException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    ArrayList<String> fdt = ontologiecharge.getIndividulsByClass("patient");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                int i = 0;
                while (i < ontologiecharge.patient.size()) {
                    model.addRow(new Object[]{ontologiecharge.patient.get(i), ontologiecharge.age.get(i), ontologiecharge.gender.get(i), ontologiecharge.cholesterol.get(i),
                            ontologiecharge.glucose.get(i), ontologiecharge.systolic_bp.get(i), ontologiecharge.diastolic_bp.get(i),
                            (int) Math.round(Float.parseFloat(ontologiecharge.height.get(i))), (int) Math.round(Float.parseFloat(ontologiecharge.weight.get(i))),
                            ontologiecharge.bmi.get(i), ontologiecharge.waist.get(i), ontologiecharge.hip.get(i), ontologiecharge.Diagnostic_Final.get(i)});
                    i++;
                }
                acceuil.getTable().setModel(model);

            }

        });
        acceuil.getChercher().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                model.setRowCount(0);
                String texte_a_rechercher = acceuil.getRecherche().getText();
                FuzzyOntology ontologiecharge;
                try {
                    ontologiecharge = new FuzzyOntology("C:\\Users\\PC-Service\\IdeaProjects\\IOT_Healthcare_App\\src\\ontologie\\OntologieFinale.owl");
                } catch (OWLOntologyCreationException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    ArrayList<String> fdt = ontologiecharge.getIndividulsByClass("patient");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
}
