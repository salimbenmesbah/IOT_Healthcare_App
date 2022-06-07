import model.Patient;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import smile.Network;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;

public class ValidationPrg {

    public static void main(String[] args) throws FileNotFoundException, OWLOntologyCreationException, OWLOntologyStorageException {
        float TP = 0,FN = 0,FP = 0,TN = 0;
        new smile.License(
                "SMILE LICENSE d41161b6 8b72612c ed417b5a " +
                        "THIS IS AN ACADEMIC LICENSE AND CAN BE USED " +
                        "SOLELY FOR ACADEMIC RESEARCH AND TEACHING, " +
                        "AS DEFINED IN THE BAYESFUSION ACADEMIC " +
                        "SOFTWARE LICENSING AGREEMENT. " +
                        "Serial #: en05exaj9ztpxsp2znslb6es6 " +
                        "Issued for: ta meree (laughoutloudly417@gmail.com) " +
                        "Academic institution: Saad dahleb Blida1 " +
                        "Valid until: 2022-11-24 " +
                        "Issued by BayesFusion activation server",
                new byte[] {
                        -116,120,-3,100,57,-127,27,-41,10,19,4,-86,-88,-126,-15,86,
                        -47,59,-93,-127,-3,-101,-56,-63,-100,75,11,-32,86,107,-91,53,
                        -30,82,42,-14,-34,-118,-103,-79,22,-128,-51,1,96,53,119,38,
                        80,54,58,-1,107,-47,-26,42,49,64,100,38,-115,-121,111,27
                }
        );
        System.loadLibrary("jsmile");
        Network net= new Network();
        net.readFile("C:\\Users\\PC-Service\\IdeaProjects\\IOT_Healthcare_App\\src\\réseauxBayésiens\\RéseauBayesienClassique2.xdsl");

        String csvPath= "D:\\dataset\\NotreDT_ordonée.csv";
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(csvPath));
            String lineText = null;
            lineReader.readLine(); // skip le header
            while ((lineText = lineReader.readLine()) != null){
                String[] data = lineText.split(",");  //instanciation de chaque ligne
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
                P.FuzzificationPatient(net);         //inference


                if (P.getK().equals("Diabetes") && data[12].equals("Diabetes")) {
                    TP++;
//                        System.out.println(P.getK().equals("Diabetes") && data[12].equals("Diabetes"));
                }
                if (P.getK().equals("Diabetes") && data[12].equals("No diabetes") ) {
                    FN++;
//                        System.out.println(P.getK().equals("Diabetes") && data[12].equals("No diabetes"));
                }
                if (P.getK().equals("No diabetes") && data[12].equals("Diabetes") ) {
                    FP++;
//                        System.out.println(P.getK().equals("No diabetes") && data[12].equals("Diabetes"));
                }
                if (P.getK().equals("No diabetes") &&  data[12].equals("No diabetes")) {
                    TN++;
//                        System.out.println(P.getK().equals("No diabetes") &&  data[12].equals("No diabetes"));
                }

            }
            lineReader.close();
            float accuracy=0,rappel=0,precision=0;
            if ((TP + TN + FP + FN) != 0.0)
                accuracy = ((float) ((TP + TN) / (TP + TN + FP + FN)))*100; //l'accuracy est le pourcentage de bonnes prédictions.
            if ((TP + FN) != 0.0)
                rappel = ((float) (TP / (TP + FN)))*100;               //le rappel est le ratio de des instances positives jugées correctement par rapport à toutes les instances positives
            if ((TP + FP) != 0.0)
                precision = ((float) (TP / (TP + FP)))*100;            // la precision est le ratio des instances positives jugées correctement par rapport aux instances jugées possitifs.
            System.out.println("accuracy: "+accuracy+" rappel :"+rappel+" precision :"+precision);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

