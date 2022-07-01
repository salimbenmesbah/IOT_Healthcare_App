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

public class ValidationKFold {
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


    public ValidationKFold(String dataset, String train, String test, int k,Network net) {
        this.train = train;
        this.dataset = dataset;
        this.test = test;
        this.k = k;
        this.net = net;
    }

    public void inference() throws IOException {
        double[] evaluer_age = new double[2];
        double[] evaluer_cholesterol = new double[2];
        double[] evaluer_glucose = new double[2];
        double[] evaluer_tas = new double[2];
        double[] evaluer_tad = new double[2];
        double[] evaluer_taille = new double[2];
        double[] evaluer_poids = new double[2];
        double[] evaluer_imc = new double[2];
        double[] evaluer_tour_taille = new double[2];
        double[] evaluer_tour_hanche = new double[2];
        double TP = 0.0, TN = 0.0, FP = 0.0, FN = 0.0;
        Fuzzification f = new Fuzzification();//Là je règle les fonctions d'appartenance pour les etats des noeuds du réseau bayésien pourle rendre flou
        //ajouter var floue age
        InputVariable Age = f.createFuzzyNode("Age", 19, 92);
        f.addFuzzyState(Age, new Trapezoid("AgeYoung", 15, 15, 31, 65));
        f.addFuzzyState(Age, new Trapezoid("AgeOld", 31, 65, 92, 92));

        //ajouter var floue cholesterol
        InputVariable Cholesterol = f.createFuzzyNode("Cholesterol", 78, 443);
        f.addFuzzyState(Cholesterol, new Trapezoid("Low_Cholesterol", 78, 78, 182, 251.11));
        f.addFuzzyState(Cholesterol, new Trapezoid("High_Cholesterol", 182, 251.11, 443, 443));

        //ajouter var floue tas
        InputVariable Glucose = f.createFuzzyNode("Glucose", 48, 385);
        f.addFuzzyState(Glucose, new Trapezoid("Low_Glucose", 48, 48, 87, 217));
        f.addFuzzyState(Glucose, new Trapezoid("High_Glucose", 87, 217, 385, 385));

        InputVariable Systolic_bp = f.createFuzzyNode("Systolic_bp", 90, 250);
        f.addFuzzyState(Systolic_bp, new Trapezoid("Low_systolic_bp", 90, 90, 125, 157));
        f.addFuzzyState(Systolic_bp, new Trapezoid("High_systolic_bp", 125, 157, 250, 250));

        //ajouter var floue tad
        InputVariable Diastolic_bp = f.createFuzzyNode("Diastolic_bp", 48, 124);
        f.addFuzzyState(Diastolic_bp, new Trapezoid("Low_diastolic_bp", 48, 48, 75, 104));
        f.addFuzzyState(Diastolic_bp, new Trapezoid("High_diastolic_bp", 75, 104, 124, 124));

        //ajouter var floue taille
        InputVariable Height = f.createFuzzyNode("Height", 122.08,203.04);
        f.addFuzzyState(Height, new Trapezoid("Low_Height",122.08,122.08, 150,178));
        f.addFuzzyState(Height, new Trapezoid("High_Height",150,178,203.04,203.04));

        //ajouter var floue poids
        InputVariable Weight = f.createFuzzyNode("Weight", 40, 150);
        f.addFuzzyState(Weight, new Trapezoid("Low_Weight", 40, 40, 69,98));
        f.addFuzzyState(Weight, new Trapezoid("High_Weight", 69,98, 150, 150));

        //ajouter var floue imc
        InputVariable BMI = f.createFuzzyNode("BMI", 15.2, 56);
        f.addFuzzyState(BMI, new Trapezoid("BMIslim", 15, 15, 24, 34));
        f.addFuzzyState(BMI, new Trapezoid("BMIobese", 24, 34, 56, 56));

        //ajouter var floue tt
        InputVariable Waist = f.createFuzzyNode("Waist", 26, 60);
        f.addFuzzyState(Waist, new Trapezoid("Thin_Waist", 26, 26, 32, 43));
        f.addFuzzyState(Waist, new Trapezoid("Large_Waist", 32, 43, 60, 60));

        //ajouter var floue th
        InputVariable Hip = f.createFuzzyNode("Hip", 30, 69);
        f.addFuzzyState(Hip, new Trapezoid("Thin_Hip", 30, 30, 41, 47));
        f.addFuzzyState(Hip, new Trapezoid("Large_Hip", 41, 47, 69, 69));
        Scanner scanner;

        BufferedReader br = null;

        br = new BufferedReader(new FileReader(test));//charger le dataset pour le lire et le parcourir
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
        while ((line = br.readLine()) != null) {    //parcourir les lignes  donc dans cette boucle chaque itération correspond à une ligne

            scanner = new Scanner(line);
            scanner.useDelimiter(",");//ici j'ai séparé par une virgule pour dire que les différentes valeurs du dataset sont séparées par une virgule
            //comme ci 9essemna chaque lignes en un vecteur
            i = 0;//ici on va lire  la ligne et utiliser les valeurs de cette ligne en tant que evidences/ virtualevidances pour les affecter aux états des noeuds
            while (scanner.hasNext()) {
                temp = scanner.next();
                if (i == 0 && !temp.equals("*")) {
                    net.clearAllEvidence();
                }
                if (i == 1 && !temp.equals("*")) {
                    evaluer_age[0] = f.getMembershipDegree(Age, Double.parseDouble(temp), "AgeOld");
                    evaluer_age[1] = f.getMembershipDegree(Age, Double.parseDouble(temp), "AgeYoung");
                    somme = evaluer_age[0] + evaluer_age[1];
                    if (somme != 0 && somme != 1) {  // je dois normaliser les valeurs pour que la somme soit 1
                        evaluer_age[0] = ((float) evaluer_age[0] / somme);
                        evaluer_age[1] = ((float) evaluer_age[1] / somme);
                    }
                    if (somme != 0) net.setVirtualEvidence("age", evaluer_age);

                } else if (i == 2 && !temp.equals("*")) {
                    net.setEvidence("gender", temp);
                } else if (i == 3 && !temp.equals("*")) {
                    evaluer_cholesterol[0] = f.getMembershipDegree(Cholesterol, Double.parseDouble(temp), "High_Cholesterol");
                    evaluer_cholesterol[1] = f.getMembershipDegree(Cholesterol, Double.parseDouble(temp), "Low_Cholesterol");
                    somme = evaluer_cholesterol[0] + evaluer_cholesterol[1];
                    if (somme != 0 && somme != 1) {  // je dois normaliser les valeurs pour que la somme soit 1
                        evaluer_cholesterol[0] = ((float) evaluer_cholesterol[0] / somme);
                        evaluer_cholesterol[1] = ((float) evaluer_cholesterol[1] / somme);
                    }
                    if (somme != 0) net.setVirtualEvidence("cholesterol", evaluer_cholesterol);

                } else if (i == 4 && !temp.equals("*")) {
                    evaluer_glucose[0] = f.getMembershipDegree(Glucose, Double.parseDouble(temp), "High_Glucose");
                    evaluer_glucose[1] = f.getMembershipDegree(Glucose, Double.parseDouble(temp), "Low_Glucose");
                    somme = evaluer_glucose[0] + evaluer_glucose[1];
                    if (somme != 0 && somme != 1) {  // je dois normaliser les derÃ©s pour que la somme soit 1
                        evaluer_glucose[0] = ((float) evaluer_glucose[0] / somme);
                        evaluer_glucose[1] = ((float) evaluer_glucose[1] / somme);
                    }
                    if (somme != 0) net.setVirtualEvidence("glucose", evaluer_glucose);

                }
                  else if (i == 5 && !temp.equals("*")) {
                    evaluer_tas[0] = f.getMembershipDegree(Systolic_bp, Double.parseDouble(temp), "High_systolic_bp");
                    evaluer_tas[1] = f.getMembershipDegree(Systolic_bp, Double.parseDouble(temp), "Low_systolic_bp");
                    somme = evaluer_tas[0] + evaluer_tas[1];
                    if (somme != 0 && somme != 1) {  // je dois normaliser les derÃ©s pour que la somme soit 1
                        evaluer_tas[0] = ((float) evaluer_tas[0] / somme);
                        evaluer_tas[1] = ((float) evaluer_tas[1] / somme);
                    }
                    if (somme != 0) net.setVirtualEvidence("systolic_bp", evaluer_tas);

                }
                  else if (i == 6 && !temp.equals("*")) {
                    evaluer_tad[0] = f.getMembershipDegree(Diastolic_bp, Double.parseDouble(temp), "High_diastolic_bp");
                    evaluer_tad[1] = f.getMembershipDegree(Diastolic_bp, Double.parseDouble(temp), "Low_diastolic_bp");
                    somme = evaluer_tad[0] + evaluer_tad[1];
                    if (somme != 0 && somme != 1) {  // je dois normaliser les vals pour que la somme soit 1
                        evaluer_tad[0] = ((float) evaluer_tad[0] / somme);
                        evaluer_tad[1] = ((float) evaluer_tad[1] / somme);
                    }
                    if (somme != 0) net.setVirtualEvidence("diastolic_bp", evaluer_tad);

                }
                  else if (i == 7 && !temp.equals("*")) {
                    evaluer_taille[0] = f.getMembershipDegree(Height, Double.parseDouble(temp), "High_Height");
                    evaluer_taille[1] = f.getMembershipDegree(Height, Double.parseDouble(temp), "Low_Height");
                    somme = evaluer_taille[0] + evaluer_taille[1];
                    if (somme != 0 && somme != 1) {  // je dois normaliser les vals pour que la somme soit 1
                        evaluer_taille[0] = ((float) evaluer_taille[0] / somme);
                        evaluer_taille[1] = ((float) evaluer_taille[1] / somme);
                    }
                    if (somme != 0) net.setVirtualEvidence("height", evaluer_taille);

                }
                  else if (i == 8 && !temp.equals("*")) {
                    evaluer_poids[0] = f.getMembershipDegree(Weight, Double.parseDouble(temp), "High_Weight");
                    evaluer_poids[1] = f.getMembershipDegree(Weight, Double.parseDouble(temp), "Low_Weight");
                    somme = evaluer_poids[0] + evaluer_poids[1];
                    if (somme != 0 && somme != 1) {  // je dois normaliser les vals pour que la somme soit 1
                        evaluer_poids[0] = ((float) evaluer_poids[0] / somme);
                        evaluer_poids[1] = ((float) evaluer_poids[1] / somme);
                    }
                    if (somme != 0) net.setVirtualEvidence("weight", evaluer_poids);

                }
                  else if (i == 9 && !temp.equals("*")) {
                    evaluer_imc[0] = f.getMembershipDegree(BMI, Double.parseDouble(temp), "BMIobese");
                    evaluer_imc[1] = f.getMembershipDegree(BMI, Double.parseDouble(temp), "BMIslim");
                    somme = evaluer_imc[0] + evaluer_imc[1];
                    if (somme != 0 && somme != 1) {  // je dois normaliser les vals pour que la somme soit 1
                        evaluer_imc[0] = ((float) evaluer_imc[0] / somme);
                        evaluer_imc[1] = ((float) evaluer_imc[1] / somme);
                    }
                    if (somme != 0) net.setVirtualEvidence("bmi", evaluer_imc);

                }
                  else if (i == 10 && !temp.equals("*")) {
                    evaluer_tour_taille[0] = f.getMembershipDegree(Waist, Double.parseDouble(temp), "Large_Waist");
                    evaluer_tour_taille[1] = f.getMembershipDegree(Waist, Double.parseDouble(temp), "Thin_Waist");
                    somme = evaluer_tour_taille[0] + evaluer_tour_taille[1];
                    if (somme != 0 && somme != 1) {  // je dois normaliser les vals pour que la somme soit 1
                        evaluer_tour_taille[0] = ((float) evaluer_tour_taille[0] / somme);
                        evaluer_tour_taille[1] = ((float) evaluer_tour_taille[1] / somme);
                    }
                    if (somme != 0) net.setVirtualEvidence("waist", evaluer_tour_taille);

                }
                  else if (i == 11 && !temp.equals("*")) {
                    evaluer_tour_hanche[0] = f.getMembershipDegree(Hip, Double.parseDouble(temp), "Large_Hip");
                    evaluer_tour_hanche[1] = f.getMembershipDegree(Hip, Double.parseDouble(temp), "Thin_Hip");
                    somme = evaluer_tour_hanche[0] + evaluer_tour_hanche[1];
                    if (somme != 0 && somme != 1) {  // je dois normaliser les vals pour que la somme soit 1
                        evaluer_tour_hanche[0] = ((float) evaluer_tour_hanche[0] / somme);
                        evaluer_tour_hanche[1] = ((float) evaluer_tour_hanche[1] / somme);
                    }
                    if (somme != 0) net.setVirtualEvidence("hip", evaluer_tour_hanche);

                }
                  else if (i == 12 && !temp.equals("*")) {//c'est le noeud qui représente le diagnostic donc ici on rajoute une comparaison entre la valeur originale du dataset et la valeur qu'on a prédit pour ensuite calculer les accuracy, rappel, precision
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
                    if (temp.equals("Diabetes") & k.equals("Diabetes")) {
                        TP++;
//                        System.out.println(temp.equals("Diabetes") & k.equals("Diabetes"));
                    }
                    else if (temp.equals("Diabetes") & k.equals("No diabetes")) {
                        FN++;
//                        System.out.println(temp.equals("Diabetes") & k.equals("No diabetes"));
                    }
                    else if (temp.equals("No diabetes") & k.equals("Diabetes")) {
                        FP++;
//                        System.out.println(temp.equals("No diabetes") & k.equals("Diabetes"));
                    }
                    else if (temp.equals("No diabetes") & k.equals("No diabetes")) {
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
    public String getTrain(int iter, int sizezOffold, String dataset) throws FileNotFoundException, IOException { //fct pour generer un dataset pour le train a partir du dataset original

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

    public String getTest(int iter, int sizezOffold, String dataset) throws FileNotFoundException, IOException {//fct pour generer un dataset pour le test a partir du dataset original

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



        for (int fold=2;fold<=10;fold++){ // on applique fold fois l'agorithme de kfold
            Network Net = new Network();
            Net.readFile("C:\\Users\\PC-Service\\IdeaProjects\\IOT_Healthcare_App\\src\\réseauxBayésiens\\Network2.xdsl");
            ValidationKFold c=new ValidationKFold("D:\\dataset\\NotreDT_ordonée1.csv",
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
            System.out.println("Inférence systeme:");
//            System.out.print (" K="+fold+"\n");           //l'execution du k fold
           // cette 2eme boucle pour les itérations de chaque algorithme k fold appliqué
            for (int i=1;i<=k;i++){  //lahna dirou les dataset en parametres 3la hsab khedmetkoum
                String content1=  c.getTest(i, sizeoffold,c.dataset) ; //gettest est unemethode qui récupère les données pour le test a partir du dataset
                String content2= c.getTrain(i, sizeoffold, "D:\\dataset\\NotreDT_ordonée_nominale.csv");//gettrain est unemethode qui récupère les données pour le train a partir du dataset
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






