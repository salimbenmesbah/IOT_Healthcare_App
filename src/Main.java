import model.FuzzyOntology;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import java.io.File;

public class Main {
    public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException {
        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
        File file = new File("D:\\ontologie\\OntologieFinale.owl");
        OWLOntology o = man.loadOntologyFromOntologyDocument(file);
        //System.out.println(o);
        FuzzyOntology o1=new FuzzyOntology("D:\\ontologie\\OntologieFinale.owl");
        //o1.getFuzzyDataTypes();
        //o1.addIndividual("kamel","doctor");
        //o1.addDataProperty("kamel","Has_Age","23");
         /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //AjouterPatient frame = new AjouterPatient();
                //AcceuilPage frame = new AcceuilPage();
                //ModifierPatient frame = new ModifierPatient();
                //frame.setVisible(true);
            }
        }); */
        //o1.addIndividual("kamel","doctor");
        //o1.addIndividual("salim","doctor");
        //o1.linkIndividuals("kamel","salim","Has_Age");

    }
}