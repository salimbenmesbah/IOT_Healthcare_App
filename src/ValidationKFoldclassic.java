import com.fuzzylite.term.Trapezoid;
import com.fuzzylite.variable.InputVariable;
import réseauxBayésiens.Fuzzification;
import smile.Network;
import smile.SMILEException;
import smile.learning.DataMatch;
import smile.learning.DataSet;
import smile.learning.EM;
import smile.learning.Validator;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class ValidationKFoldclassic {
    String dataset;
    public Network net;
    int k;
    String train;
    String[] node;
    String test;
    float accuracy, rappel, precision;
    LinkedList<Float> allaccuracy = new LinkedList<>();
    LinkedList<Float> allrappel = new LinkedList<>();
    LinkedList<Float> allprecision = new LinkedList<>();


    public ValidationKFoldclassic(String dataset, String train, String test, int k,Network net) {
        this.train = train;
        this.dataset = dataset;
        this.test = test;
        this.k = k;
        this.net = net;
    }

    public void inference() throws IOException {
        double TP = 0.0, TN = 0.0, FP = 0.0, FN = 0.0;
        Scanner scanner;
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(test));
        String line;
        int cmp = 0;
        String temp = "";
        double somme = 0;
        double[] resultat= new double[2];
        double maxVal = 0;
        //String []node;
        int i;
        line = br.readLine();
        //node=line.split(",");
        while ((line = br.readLine()) != null) {

            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            i = 0;
            while (scanner.hasNext()) {
                temp = scanner.next();
                if (i == 0 && !temp.equals("*")) {
                    net.clearAllEvidence();
                }
                if (i == 1 && !temp.equals("*")) {
                    net.setEvidence("age",temp);
                } else if (i == 2 && !temp.equals("*")) {
                    net.setEvidence("gender", temp);
                } else if (i == 3 && !temp.equals("*")) {
                    net.setEvidence("cholesterol",temp);

                } else if (i == 4 && !temp.equals("*")) {
                    net.setEvidence("glucose",temp);

                }
                else if (i == 5 && !temp.equals("*")) {
                    net.setEvidence("systolic_bp",temp);

                }
                else if (i == 6 && !temp.equals("*")) {
                    net.setEvidence("diastolic_bp",temp);

                }
                else if (i == 7 && !temp.equals("*")) {
                    net.setEvidence("height",temp);

                }
                else if (i == 8 && !temp.equals("*")) {
                    net.setEvidence("weight",temp);

                }
                else if (i == 9 && !temp.equals("*")) {
                    net.setEvidence("bmi",temp);

                }
                else if (i == 10 && !temp.equals("*")) {
                    net.setEvidence("waist",temp);

                }
                else if (i == 11 && !temp.equals("*")) {
                    net.setEvidence("hip",temp);

                }
                else if (i == 12 && !temp.equals("*")) {
                    net.updateBeliefs();
                    resultat = net.getNodeValue("diabetes");
                    //gestion tablo resultat
//                    for (int j = 0; j < resultat.length; j++) {
//                        System.out.println("resultat"+j+":"+resultat[j]);
//                    }
                    String k = "";
                    maxVal = Math.max(resultat[1], resultat[0]);

                    if (maxVal == resultat[0]) {
                        k = "Diabetes";
                    }
                    if (maxVal == resultat[1]) {
                        k = "No diabetes";
                    }
//                    System.out.println(temp+" == "+k);
                    //////////////////////////////////////////////////////////////
                    if (temp.equals("Yes") & k.equals("Diabetes")) {
                        TP++;
//                        System.out.println(temp.equals("Diabetes") & k.equals("Diabetes"));
                    }
                    else if (temp.equals("Yes") & k.equals("No diabetes")) {
                        FN++;
//                        System.out.println(temp.equals("Diabetes") & k.equals("No diabetes"));
                    }
                    else if (temp.equals("No") & k.equals("Diabetes")) {
                        FP++;
//                        System.out.println(temp.equals("No diabetes") & k.equals("Diabetes"));
                    }
                    else if (temp.equals("No") & k.equals("No diabetes")) {
                        TN++;
//                        System.out.println(temp.equals("No diabetes") & k.equals("No diabetes"));
                    }
                }
                i++;
            }

        }
//Calculer accuracy rappel precision
//        System.out.println("TP:"+TP+",TN:"+TN+",FP:"+FP+",FN:"+FN);
        if ((TP + TN + FP + FN) != 0.0)
            accuracy = ((float) ((TP + TN) / (TP + TN + FP + FN)))*100; //l'accuracy est le pourcentage de bonnes prédictions.
        if ((TP + FN) != 0.0)
            rappel = ((float) (TP / (TP + FN)))*100;               //le rappel est le ratio de des instances positives jugées correctement par rapport à toutes les instances positives
        if ((TP + FP) != 0.0)
            precision = ((float) (TP / (TP + FP)))*100;            // la precision est le ratio des instances positives jugées correctement par rapport aux instances jugées possitifs.

        allaccuracy.add(accuracy);
        allprecision.add(precision);
        allrappel.add(rappel);
    }




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int inRange(int i, int d, int f) {
        if (i >= d && i <= f) return 1;
        else return 0;
    }

    ///////////////////////////////////////////
    ///////////////////////////////////////////
    public String getTrain(int iter, int sizezOffold, String dataset) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader(dataset));
        String line;
        String content = "";
        line = br.readLine();
        content += line + "\n";
        int d = sizezOffold * (iter - 1);
        int f = (sizezOffold * iter) - 1;
        int i = 0;
        while ((line = br.readLine()) != null) {
            if (inRange(i, d, f) == 0) {

                content += line;
                content += "\n";
            }
            i++;
        }
        return content;
    }

    public String getTest(int iter, int sizezOffold, String dataset) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader(dataset));
        String line;
        line = br.readLine();
        String content = "";
        int d = sizezOffold * (iter - 1);
        int f = sizezOffold * iter;

        int i = 0;
        while ((line = br.readLine()) != null) {

            if (inRange(i, d, f) == 1) {

                content += line;
                content += "\n";
            }

            i++;

        }

        return content;

    }

    public static void usingBufferedWritter(String fileContent, String file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(fileContent);
        writer.close();
    }

    public static void main(String args[]) throws FileNotFoundException, IOException {
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



        for (int fold=2;fold<=12;fold++){ // on applique fold fois l'agorithme de kfold
            Network Net = new Network();
            Net.readFile("C:\\Users\\PC-Service\\IdeaProjects\\IOT_Healthcare_App\\src\\réseauxBayésiens\\Network2.xdsl");
            ValidationKFoldclassic c=new ValidationKFoldclassic("D:\\dataset\\NotreDT_ordonée_nominale.csv",
                    "D:\\dataset\\Train.txt",
                    "D:\\dataset\\Test.txt",
                    fold,Net);
            BufferedReader br = new BufferedReader(new FileReader(c.dataset));
            String line;
            line = br.readLine();
            c.node=line.split(",");
            c.rappel=0;
            c.accuracy=0;
            c.precision=0;


            int k=c.k;
            int sizeoffold= 560 /c.k;
            System.out.println("Inférence classique:");
//            System.out.print (" K="+fold+"\n");           //l'execution du k fold
            // cette 2eme boucle pour les itérations de chaque algorithme k fold appliqué
            for (int i=1;i<=k;i++){
                String content1=  c.getTest(i, sizeoffold,c.dataset) ; //gettest est unemethode qui récupère les données pour le test a partir du dataset
                String content2= c.getTrain(i, sizeoffold, c.dataset);//gettrain est unemethode qui récupère les données pour le train a partir du dataset
                usingBufferedWritter(content1,c.test);
                usingBufferedWritter(content2,c.train);
                DataSet ds = new DataSet();
                ds.readFile(c.train);
                DataMatch[] matching = ds.matchNetwork(c.net);
                EM em = new EM();
                em.learn(ds, c.net, matching);   //apprentissage a partir du DT_train fait
                c.inference();
                c.net.clearAllEvidence();
            }
            System.out.println("Pour le Kfold égal à "+fold+" les résultats:");
            double sommeaccuracy=0;
            for(int l=0;l<c.allaccuracy.size();l++)
                sommeaccuracy += c.allaccuracy.get(l);
            double sommerappel=0;
            for(int l=0;l<c.allrappel.size();l++)
                sommerappel += c.allrappel.get(l);
            double sommeprecision=0;
            for(int l=0;l<c.allprecision.size();l++)
                sommeprecision += c.allprecision.get(l);

            double moyenneaccuracy = sommeaccuracy / c.allaccuracy.size();
            double moyennerappel = sommerappel / c.allrappel.size();
            double moyenneprecision =sommeprecision / c.allprecision.size();
            System.out.println("accuracy : "+moyenneaccuracy);
            System.out.println("rappel : "+moyennerappel);
            System.out.println("precision : "+moyenneprecision);
        }

    }

}






