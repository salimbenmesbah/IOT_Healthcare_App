import model.FuzzyOntology;
import model.Patient;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import smile.Network;
import view.AcceuilPage;
import view.AjouterPatient;
import view.InferencePatient;
import view.ModifierPatient;

import java.io.File;

public class Main {
    public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException {

        InferencePatient frame = new InferencePatient();
        frame.setVisible(true);
        AjouterPatient frame2 = new AjouterPatient();
        frame2.setVisible(true);
        AcceuilPage frame3 = new AcceuilPage();
        frame3.setVisible(true);
        ModifierPatient frame4 = new ModifierPatient();
        frame4.setVisible(true);
         /* java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //AjouterPatient frame = new AjouterPatient();
                //AcceuilPage frame = new AcceuilPage();
                //ModifierPatient frame = new ModifierPatient();
                //frame.setVisible(true);
            }
        }); */


    }
}