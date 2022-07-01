import controller.AcceuilPageController;
import model.FuzzyOntology;
import model.Patient;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import smile.Network;
import view.*;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

//        InferencePatient frame = new InferencePatient();
//        frame.setVisible(true);
//        AjouterPatient frame2 = new AjouterPatient();
//        frame2.setVisible(true);

          AcceuilPage frame3 = new AcceuilPage();
          frame3.setVisible(true);
          AcceuilPageController  frame3controlleur = new AcceuilPageController(frame3);
          frame3controlleur.initControlleur();

//        ModifierPatient frame4 = new ModifierPatient();
//        frame4.setVisible(true);
//          prediction frame5 = new prediction(new double[]{0.00, 1.00});
//          frame5.setVisible(true);
//          java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                AjouterPatient frame = new AjouterPatient();
//                //AcceuilPage frame = new AcceuilPage();
//                //ModifierPatient frame = new ModifierPatient();
//                frame.setVisible(true);
//            }
//          });


    }
}