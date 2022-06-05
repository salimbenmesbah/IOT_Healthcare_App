import com.fuzzylite.term.Trapezoid;
import com.fuzzylite.variable.InputVariable;
import réseauxBayésiens.Fuzzification;
import smile.Network;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class ValidationKFold {
    String dataset;
    public Network net;
    int k;
    String train;
    String[] node;
    String test;
    double accuracy, rappel, fm, precision;
    LinkedList<Float> allaccuracy1 = new LinkedList<>();
    LinkedList<Float> allrappel1 = new LinkedList<>();
    LinkedList<Float> allprecision1 = new LinkedList<>();
    LinkedList<Float> allfme1 = new LinkedList<>();
    LinkedList<Float> allaccuracy2 = new LinkedList<>();
    LinkedList<Float> allrappel2 = new LinkedList<>();
    LinkedList<Float> allprecision2 = new LinkedList<>();
    LinkedList<Float> allfme2 = new LinkedList<>();


    public ValidationKFold(String dataset, String train, String test, int k) {
        this.train = train;
        this.dataset = dataset;
        this.test = test;
        this.k = k;
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
        int tp1 = 0, tp2 = 0, tn1 = 0, tn2 = 0, fp1 = 0, fp2 = 0, fn1 = 0, fn2 = 0;
//        un=0,deux=0,trois=0,quatre=0,cinq=0,six=0,cet=0,huit=0,nef=0,dix=0,onze=0,douze=0,trese=0,
//                quatorse=0,quanze=0,saize=0,dixcet=0,dixhuit=0,dixnef=0,vint=0,vintun=0,vintdeux=0,
//                vinttrois=0,vintquatre=0,vintcinq=0;
        Fuzzification f = new Fuzzification();
        //ajouter var floue age
        InputVariable Age = f.createFuzzyNode("Age", 19, 92);
        f.addFuzzyState(Age, new Trapezoid("AgeYoung", 19, 19, 34, 63));
        f.addFuzzyState(Age, new Trapezoid("AgeOld", 34, 63, 92, 92));

        //ajouter var floue cholesterol
        InputVariable Cholesterol = f.createFuzzyNode("Cholesterol", 78, 443);
        f.addFuzzyState(Cholesterol, new Trapezoid("Low_Cholesterol", 78, 78, 180.36, 251.11));
        f.addFuzzyState(Cholesterol, new Trapezoid("High_Cholesterol", 180.36, 251.11, 443, 443));

        //ajouter var floue tas
        InputVariable Glucose = f.createFuzzyNode("Glucose", 48, 385);
        f.addFuzzyState(Glucose, new Trapezoid("Low_Glucose", 48, 48, 89.97, 242.93));
        f.addFuzzyState(Glucose, new Trapezoid("High_Glucose", 89.97, 242.93, 385, 385));

        InputVariable Systolic_bp = f.createFuzzyNode("Systolic_bp", 48, 124);
        f.addFuzzyState(Systolic_bp, new Trapezoid("Low_systolic_bp", 90, 90, 125.14, 164.15));
        f.addFuzzyState(Systolic_bp, new Trapezoid("High_systolic_bp", 125.14, 164.15, 250, 250));

        //ajouter var floue tad
        InputVariable Diastolic_bp = f.createFuzzyNode("Diastolic_bp", 48, 124);
        f.addFuzzyState(Diastolic_bp, new Trapezoid("Low_diastolic_bp", 48, 48, 73.23, 94.89));
        f.addFuzzyState(Diastolic_bp, new Trapezoid("High_diastolic_bp", 73.23, 94.89, 124, 124));

        //ajouter var floue taille
        InputVariable Height = f.createFuzzyNode("Height", 52, 76);
        f.addFuzzyState(Height, new Trapezoid("Low_Height", 52, 52, 62.82, 69.45));
        f.addFuzzyState(Height, new Trapezoid("High_Height", 62.82, 69.45, 76, 76));

        //ajouter var floue poids
        InputVariable Weight = f.createFuzzyNode("Weight", 99, 325);
        f.addFuzzyState(Weight, new Trapezoid("Low_Weight", 99, 99, 153.29, 220.55));
        f.addFuzzyState(Weight, new Trapezoid("High_Weight", 153.29, 220.55, 325, 325));

        //ajouter var floue imc
        InputVariable BMI = f.createFuzzyNode("BMI", 15.2, 55.8);
        f.addFuzzyState(BMI, new Trapezoid("BMIslim", 15.2, 15.2, 24.74, 35.94));
        f.addFuzzyState(BMI, new Trapezoid("BMIobese", 24.74, 35.94, 55.8, 55.8));

        //ajouter var floue tt
        InputVariable Waist = f.createFuzzyNode("Waist", 26, 56);
        f.addFuzzyState(Waist, new Trapezoid("Thin_Waist", 26, 26, 33.74, 43.55));
        f.addFuzzyState(Waist, new Trapezoid("Large_Waist", 33.74, 43.55, 56, 56));

        //ajouter var floue th
        InputVariable Hip = f.createFuzzyNode("Waist", 30, 64);
        f.addFuzzyState(Hip, new Trapezoid("Thin_Hip", 30, 30, 39.52, 48.98));
        f.addFuzzyState(Hip, new Trapezoid("Large_Hip", 39.52, 48.98, 64, 64));
        String oldFileName = "C:\\Users\\Winsido\\Desktop\\m\\les maladies UCI\\hepatits C\\nvprojet\\classificationfloue\\full.txt";
        Scanner scanner;
        int tp = 0, tn = 0, fp = 0, fn = 0;
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(test));
        String line;
        int cmp = 0;
        String temp = "";
        double somme = 0;
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
                if (i==1 && !temp.equals("*")) {
                    evaluer_age[0]= f.getMembershipDegree(Age,Double.parseDouble(temp), "AgeOld");
                    evaluer_age[1]=f.getMembershipDegree(Age,Double.parseDouble(temp), "AgeYoung");
                    somme = evaluer_age[0] + evaluer_age[1];
                    if (somme!=0 && somme!=1){  // je dois normaliser les valeurs pour que la somme soit 1
                        evaluer_age[0]= ( (float) evaluer_age[0] /somme);
                        evaluer_age[1]= ((float) evaluer_age[1]/somme);
                    }
                    if(somme != 0) net.setVirtualEvidence("Age", evaluer_age);
                }
                else if( i==2 && !temp.equals("*")){net.setEvidence(node[i], temp);}
                else if(i==3 && !temp.equals("*")){
                    evaluer_cholesterol[0]= f.getMembershipDegree(Cholesterol,Double.parseDouble(temp), "High_Cholesterol");
                    evaluer_cholesterol[1]=f.getMembershipDegree(Cholesterol,Double.parseDouble(temp), "Low_Cholesterol");
                    somme = evaluer_cholesterol[0] + evaluer_cholesterol[1];
                    if (somme!=0 && somme!=1){  // je dois normaliser les valeurs pour que la somme soit 1
                        evaluer_cholesterol[0]= ( (float) evaluer_cholesterol[0] /somme);
                        evaluer_cholesterol[1]= ((float) evaluer_cholesterol[1]/somme);
                    }
                    if(somme != 0) net.setVirtualEvidence("Cholesterol", evaluer_cholesterol);
                }
                else if(i==4 && !temp.equals("*")){
                    evaluer_glucose[0]= f.getMembershipDegree(Glucose,Double.parseDouble(temp), "High_Glucose");
                    evaluer_glucose[1]=f.getMembershipDegree(Glucose,Double.parseDouble(temp), "Low_Glucose");
                    somme = evaluer_glucose[0] + evaluer_glucose[1];
                    if (somme!=0 && somme!=1){  // je dois normaliser les derÃ©s pour que la somme soit 1
                        evaluer_glucose[0]= ( (float) evaluer_glucose[0] /somme);
                        evaluer_glucose[1]= ((float) evaluer_glucose[1]/somme);
                    }
                    if(somme != 0) net.setVirtualEvidence("Glucose", evaluer_glucose);
                }
                else if( i==5 && !temp.equals("*")){
                    evaluer_tas[0]= f.getMembershipDegree(Systolic_bp,Double.parseDouble(temp), "High_systolic_bp");
                    evaluer_tas[1]=f.getMembershipDegree(Systolic_bp,Double.parseDouble(temp), "Low_systolic_bp");
                    somme = evaluer_tas[0] + evaluer_tas[1];
                    if (somme!=0 && somme!=1){  // je dois normaliser les derÃ©s pour que la somme soit 1
                        evaluer_tas[0]= ( (float) evaluer_tas[0] /somme);
                        evaluer_tas[1]= ((float) evaluer_tas[1]/somme);
                    }
                    if(somme != 0) net.setVirtualEvidence("Systolic_bp", evaluer_tas);
                }
                else if( i==6 && !temp.equals("*")){
                    evaluer_tad[0]= f.getMembershipDegree(Diastolic_bp,Double.parseDouble(temp), "High_diastolic_bp");
                    evaluer_tad[1]=f.getMembershipDegree(Diastolic_bp,Double.parseDouble(temp), "Low_diastolic_bp");
                    somme = evaluer_tad[0] + evaluer_tad[1];
                    if (somme!=0 && somme!=1){  // je dois normaliser les vals pour que la somme soit 1
                        evaluer_tad[0]= ( (float) evaluer_tad[0] /somme);
                        evaluer_tad[1]= ((float) evaluer_tad[1]/somme);
                    }
                    if(somme != 0) net.setVirtualEvidence("Diastolic_bp", evaluer_tad);
                }
                else if( i==7 && !temp.equals("*")){
                    evaluer_taille[0]= f.getMembershipDegree(Height,Double.parseDouble(temp), "High_Height");
                    evaluer_taille[1]=f.getMembershipDegree(Height,Double.parseDouble(temp), "Low_Height");
                    somme = evaluer_taille[0] + evaluer_taille[1];
                    if (somme!=0 && somme!=1){  // je dois normaliser les vals pour que la somme soit 1
                        evaluer_taille[0]= ( (float) evaluer_taille[0] /somme);
                        evaluer_taille[1]= ((float) evaluer_taille[1]/somme);
                    }
                    if(somme != 0) net.setVirtualEvidence("Height", evaluer_taille);
                }
                else if( i==8 && !temp.equals("*")){
                    evaluer_poids[0]= f.getMembershipDegree(Weight,Double.parseDouble(temp), "High_Weight");
                    evaluer_poids[1]=f.getMembershipDegree(Weight,Double.parseDouble(temp), "Low_Weight");
                    somme = evaluer_poids[0] + evaluer_poids[1];
                    if (somme!=0 && somme!=1){  // je dois normaliser les vals pour que la somme soit 1
                        evaluer_poids[0]= ( (float) evaluer_poids[0] /somme);
                        evaluer_poids[1]= ((float) evaluer_poids[1]/somme);
                    }
                    if(somme != 0) net.setVirtualEvidence("Weight", evaluer_poids);
                }
                else if( i==9 && !temp.equals("*")){
                    evaluer_imc[0]= f.getMembershipDegree(BMI,Double.parseDouble(temp), "BMIobese");
                    evaluer_imc[1]=f.getMembershipDegree(BMI,Double.parseDouble(temp), "BMIslim");
                    somme = evaluer_imc[0] + evaluer_imc[1];
                    if (somme!=0 && somme!=1){  // je dois normaliser les vals pour que la somme soit 1
                        evaluer_imc[0]= ( (float) evaluer_imc[0] /somme);
                        evaluer_imc[1]= ((float) evaluer_imc[1]/somme);
                    }
                    if(somme != 0) net.setVirtualEvidence("BMI", evaluer_imc);
                }
                else if( i==10 && !temp.equals("*")){
                    evaluer_tour_taille[0]= f.getMembershipDegree(Waist,Double.parseDouble(temp), "Large_Waist");
                    evaluer_tour_taille[1]=f.getMembershipDegree(Waist,Double.parseDouble(temp), "Thin_Waist");
                    somme = evaluer_tour_taille[0] + evaluer_tour_taille[1];
                    if (somme !=0 && somme !=1){  // je dois normaliser les vals pour que la somme soit 1
                        evaluer_tour_taille[0]= ( (float) evaluer_tour_taille[0] / somme);
                        evaluer_tour_taille[1]= ((float) evaluer_tour_taille[1]/ somme);
                    }
                    if(somme != 0) net.setVirtualEvidence("Waist", evaluer_tour_taille);
                }
                else if( i==11 && !temp.equals("*")){
                    evaluer_tour_hanche[0]= f.getMembershipDegree(Hip,Double.parseDouble(temp), "Large_Hip");
                    evaluer_tour_hanche[1]=f.getMembershipDegree(Hip,Double.parseDouble(temp), "Thin_Hip");
                    somme = evaluer_tour_hanche[0] + evaluer_tour_hanche[1];
                    if (somme !=0 && somme !=1){  // je dois normaliser les vals pour que la somme soit 1
                        evaluer_tour_hanche[0]= ( (float) evaluer_tour_hanche[0] / somme);
                        evaluer_tour_hanche[1]= ((float) evaluer_tour_hanche[1]/ somme);
                    }
                    if(somme!= 0) net.setVirtualEvidence("Hip", evaluer_tour_hanche);
                }
                else if( i==12 && !temp.equals("*")){

                }


            }
        }
    }
}