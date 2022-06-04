package controller;

import model.FuzzyOntology;
import model.Patient;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import view.AcceuilPage;
import view.AjouterPatient;

import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class AcceuilPageController {
     AcceuilPage acceuil;
    FuzzyOntology o;
    DefaultTableModel model = new DefaultTableModel();
    LinkedList<String> e ;

    //constructeur
    public AcceuilPageController(AcceuilPage acceuil) throws Exception{
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
        o= new FuzzyOntology("C:\\Users\\USER\\IdeaProjects\\PFE_Master\\src\\ontologie\\OntologieFinale.owl");
    }
    public void initControlleur(){

    }

    //ajouter controlleur
    /*acceuil.ajouter.addMouseListener(new MouseAdapter() throws OWLOntologyCreationException {
        public void mouseClicked(MouseEvent ev) {
            Patient p = new Patient();
            AjouterPatientController c = new AjouterPatientController(new AjouterPatient(), patient);
            //mnv.setEnabled(false);
            acceuil.setVisible(false);
            c.initControlleur();
        }
    }) ; */

}
