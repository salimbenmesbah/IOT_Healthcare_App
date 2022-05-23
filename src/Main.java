import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import view.AjouterPatient;

import java.io.File;

public class Main {
    public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException {
        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
        File file = new File("D:\\ontologie\\OntologieFinale.owl");
        OWLOntology o = man.loadOntologyFromOntologyDocument(file);
        //System.out.println(o);
        ontologie_test o1=new ontologie_test("D:\\ontologie\\OntologieFinale.owl");
        //o1.getFuzzyDataTypes();
        //o1.addDataProperty("kamel","has_cancer","1");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AjouterPatient frame = new AjouterPatient();
                frame.setVisible(true);
            }
        });




    }
}