import model.Patient;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class DataSetchargement {
    public static void main(String[] args) throws FileNotFoundException, OWLOntologyCreationException, OWLOntologyStorageException {
        String csvPath= "D:\\dataset\\NotreDT_ordonée1.csv";
        int i=1;
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(csvPath)); //ouvrir et lire dataset
            String lineText = null;


            lineReader.readLine(); // skip le header
            while ((lineText = lineReader.readLine()) != null){ //pour chaque ligne
                String[] data = lineText.split(",");

                String patient_num = "patient"+data[0];
                String age = data[1];
                String sexe = data[2];
                String chol = data[3];
                String glu = data[4];
                String tas = data[5];
                String tad = data[6];
                String taille = data[7];
                String poids = data[8];
                String imc = data[9];
                String tt = data[10];
                String th = data[11];

                Patient P = new Patient(patient_num,age,sexe,chol,glu,tas,tad,taille,poids,imc,tt,th);
                P.AddToOntology();
                if (data[12].equals("Diabetes")){
                    P.AddDiagnostic(1);
                } else if (data[12].equals("No diabetes")){
                    P.AddDiagnostic(0);
                }
                System.out.println("Fait!"+i);
                i++;

            }
            lineReader.close();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
