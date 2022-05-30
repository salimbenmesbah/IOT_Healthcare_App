package model;
import com.fuzzylite.term.Trapezoid;
import com.fuzzylite.variable.InputVariable;
//import org.jfree.ui.RefineryUtilities;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import réseauxBayésiens.Fuzzification;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import smile.Network;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import java.io.File;

public class Patient {
    String nom_patient,age,sexe,chol,glu,tas,tad,taille,poids,imc,tt,th;
    String age1,sexe1,chol1,glu1,tas1,tad1,taille1,poids1,imc1,tt1,th1;
    String k = "";
    double maxVal = 0;
    double[] resultat;

    FuzzyOntology o;

    //constructor 1
    public Patient(String nom_patient, String age, String sexe, String chol, String glu, String tas, String tad, String taille, String poids, String imc, String tt, String th) throws OWLOntologyCreationException, OWLOntologyStorageException {
        this.nom_patient = nom_patient; this.age= age; this.sexe=sexe; this.chol=chol; this.glu=glu; this.tas=tas; this.tad=tad; this.taille=taille; this.poids=poids; this.imc=imc;
        this.tt=tt; this.th=th;
        //

        o= new FuzzyOntology("C:\\Users\\USER\\IdeaProjects\\PFE_Master\\src\\ontologie\\OntologieFinale.owl");

    }

    //constructor 2
    public Patient() throws OWLOntologyCreationException {
        String chemin = "C:\\Users\\USER\\IdeaProjects\\PFE_Master\\src\\ontologie\\OntologieFinale.owl";
        o= new FuzzyOntology(chemin);
    }

    //remplir les attributs
    public void RemplirAttributs(){
        age1 ="Age_patient"+ nom_patient;
        sexe1 ="Sexe_patient"+ nom_patient;
        chol1="Cholesterol_patient"+ nom_patient;
        glu1="Glucose_patient"+ nom_patient;
        tas1="Tension_artérielle_systolique_patient"+ nom_patient;
        tad1 ="Tension_artérielle_diastolique_patient"+ nom_patient;
        taille1 ="taille_patient"+ nom_patient;
        poids1 ="Poids_patient"+ nom_patient;
        imc1 = "IMC_patient"+ nom_patient;
        tt1="Tour_de_taille_patient"+ nom_patient;
        th1 ="Tour_de_hanches_patient"+ nom_patient;
    }
    public void AddDiagnostic(int n) throws OWLOntologyStorageException {
        if (n == 0) o.addDataProperty(nom_patient,"Diagnostic_Final","false");
        if (n == 1) o.addDataProperty(nom_patient,"Diagnostic_Final","true");
    }

    //ajouter à l'ontologie
    public void AddToOntology() throws OWLOntologyStorageException {

        RemplirAttributs();

        //ajouter des individus aux classes     demander sexe a kamel
        o.addIndividual(nom_patient, "patient");
        o.addIndividual(age1,"Age");
        o.addIndividual(sexe1,"Gender");
        o.addIndividual(chol1, "Cholesterol");
        o.addIndividual(glu1, "Glucose");
        o.addIndividual(tas1, "Systolic_bp");
        o.addIndividual(tad1, "Diastolic_bp");
        o.addIndividual(taille1, "Height");
        o.addIndividual(poids1, "Weight");
        o.addIndividual(imc1, "BMI");
        o.addIndividual(tt1, "Waist");
        o.addIndividual(th1, "Hip");

        //ajouter data properties
        o.addDataProperty(age1,"Has_Age",age);
        o.addDataProperty(sexe1,"gender",sexe);
        o.addDataProperty(chol1,"Has_Cholesterol",chol);
        o.addDataProperty(glu1,"Has_Glucose",glu);
        o.addDataProperty(tas1,"Has_SBP_rate",tas);
        o.addDataProperty(tad1,"Has_DBP_rate",tad);
        o.addDataProperty(taille1,"Has_height",taille);
        o.addDataProperty(poids1,"Has_weight",poids);
        o.addDataProperty(imc1,"Has_BMI",imc);
        o.addDataProperty(tt1,"Has_waist",tt);
        o.addDataProperty(th1,"Has_hip",th);

        //link les propr aux individus  //changer pour object property
        o.linkIndividuals(nom_patient,age1,"Has_age");
        o.linkIndividuals(nom_patient,sexe1,"Has_gender");
        o.linkIndividuals(nom_patient,chol1,"Has_cholesterol");
        o.linkIndividuals(nom_patient,glu1,"Has_glucose");
        o.linkIndividuals(nom_patient,tas1,"Has_SBP");
        o.linkIndividuals(nom_patient,tad1,"Has_DBP");
        o.linkIndividuals(nom_patient,taille1,"Has_Height");
        o.linkIndividuals(nom_patient,poids1,"Has_Weight");
        o.linkIndividuals(nom_patient,imc1,"Has_bmi");
        o.linkIndividuals(nom_patient,tt1,"Has_Waist");
        o.linkIndividuals(nom_patient,th1,"Has_Hip");

    }




    public void FuzzificationPatient(Network net) throws OWLOntologyStorageException {
        Fuzzification f = new Fuzzification();

        //ajouter var floue age
        InputVariable Age = f.createFuzzyNode("Age", 19,92);
        f.addFuzzyState(Age, new Trapezoid("AgeYoung",19,19,34,63));
        f.addFuzzyState(Age, new Trapezoid("AgeOld",34,63,92,92));

        //ajouter var floue cholesterol
        InputVariable Cholesterol = f.createFuzzyNode("Cholesterol", 78,443);
        f.addFuzzyState(Cholesterol, new Trapezoid("Low_Cholesterol",78,78, 180.36,251.11));
        f.addFuzzyState(Cholesterol, new Trapezoid("High_Cholesterol",180.36,251.11,443,443));

        //ajouter var floue tas
        InputVariable Glucose = f.createFuzzyNode("Glucose", 48,385);
        f.addFuzzyState(Glucose, new Trapezoid("Low_Glucose",48,48,89.97,242.93));
        f.addFuzzyState(Glucose, new Trapezoid("High_Glucose",89.97,242.93,385,385));

        InputVariable Systolic_bp = f.createFuzzyNode("Systolic_bp", 48,124);
        f.addFuzzyState(Systolic_bp, new Trapezoid("Low_systolic_bp",90,90, 125.14,164.15));
        f.addFuzzyState(Systolic_bp, new Trapezoid("High_systolic_bp",125.14,164.15,250,250));

        //ajouter var floue tad
        InputVariable Diastolic_bp = f.createFuzzyNode("Diastolic_bp", 48,124);
        f.addFuzzyState(Diastolic_bp, new Trapezoid("Low_diastolic_bp",48,48,73.23,94.89));
        f.addFuzzyState(Diastolic_bp, new Trapezoid("High_diastolic_bp",73.23,94.89,124,124));

        //ajouter var floue taille
        InputVariable Height = f.createFuzzyNode("Height", 52,76);
        f.addFuzzyState(Height, new Trapezoid("Low_Height",52,52, 62.82,69.45));
        f.addFuzzyState(Height, new Trapezoid("High_Height",62.82,69.45,76,76));

        //ajouter var floue poids
        InputVariable Weight = f.createFuzzyNode("Weight", 99,325);
        f.addFuzzyState(Weight, new Trapezoid("Low_Weight",99,99, 153.29,220.55));
        f.addFuzzyState(Weight, new Trapezoid("High_Weight",153.29,220.55,325,325));

        //ajouter var floue imc
        InputVariable BMI = f.createFuzzyNode("BMI", 15.2,55.8);
        f.addFuzzyState(BMI, new Trapezoid("BMIslim",15.2, 15.2, 24.74,35.94));
        f.addFuzzyState(BMI, new Trapezoid("BMIobese",24.74,35.94,55.8,55.8));

        //ajouter var floue tt
        InputVariable Waist = f.createFuzzyNode("Waist", 26,56);
        f.addFuzzyState(Waist, new Trapezoid("Thin_Waist",26, 26, 33.74,43.55));
        f.addFuzzyState(Waist, new Trapezoid("Large_Waist",33.74,43.55,56,56));

        //ajouter var floue th
        InputVariable Hip = f.createFuzzyNode("Waist", 30,64);
        f.addFuzzyState(Hip, new Trapezoid("Thin_Hip",30,30,39.52,48.98));
        f.addFuzzyState(Hip, new Trapezoid("Large_Hip",39.52,48.98,64,64));

        ////////////////////////////////////
        double [] evaluer_age =new double[2];
        evaluer_age[0]= f.getMembershipDegree(Age,Double.parseDouble(age), "AgeOld");
        evaluer_age[1]=f.getMembershipDegree(Age,Double.parseDouble(age), "AgeYoung");
        double somme = evaluer_age[0] + evaluer_age[1];
        if (somme!=0 && somme!=1){  // je dois normaliser les valeurs pour que la somme soit 1
            evaluer_age[0]= ( (float) evaluer_age[0] /somme);
            evaluer_age[1]= ((float) evaluer_age[1]/somme);
        }
        if(somme != 0) net.setVirtualEvidence("Age", evaluer_age);

        /////////////////////////////////////
        double [] evaluer_cholesterol =new double[2];
        evaluer_cholesterol[0]= f.getMembershipDegree(Cholesterol,Double.parseDouble(chol), "High_Cholesterol");
        evaluer_cholesterol[1]=f.getMembershipDegree(Cholesterol,Double.parseDouble(chol), "Low_Cholesterol");
        somme = evaluer_cholesterol[0] + evaluer_cholesterol[1];
        if (somme!=0 && somme!=1){  // je dois normaliser les valeurs pour que la somme soit 1
            evaluer_cholesterol[0]= ( (float) evaluer_cholesterol[0] /somme);
            evaluer_cholesterol[1]= ((float) evaluer_cholesterol[1]/somme);
        }
        if(somme != 0) net.setVirtualEvidence("Cholesterol", evaluer_cholesterol);

        //---------------------------------------------------
        double [] evaluer_glucose =new double[2];
        evaluer_glucose[0]= f.getMembershipDegree(Glucose,Double.parseDouble(glu), "High_Glucose");
        evaluer_glucose[1]=f.getMembershipDegree(Glucose,Double.parseDouble(glu), "Low_Glucose");
        somme = evaluer_glucose[0] + evaluer_glucose[1];
        if (somme!=0 && somme!=1){  // je dois normaliser les derÃ©s pour que la somme soit 1
            evaluer_glucose[0]= ( (float) evaluer_glucose[0] /somme);
            evaluer_glucose[1]= ((float) evaluer_glucose[1]/somme);
        }
        if(somme != 0) net.setVirtualEvidence("Glucose", evaluer_glucose);

        //---------------------------------------------------
        double [] evaluer_tas =new double[2];
        evaluer_tas[0]= f.getMembershipDegree(Systolic_bp,Double.parseDouble(tas), "High_systolic_bp");
        evaluer_tas[1]=f.getMembershipDegree(Systolic_bp,Double.parseDouble(tas), "Low_systolic_bp");
        somme = evaluer_tas[0] + evaluer_tas[1];
        if (somme!=0 && somme!=1){  // je dois normaliser les derÃ©s pour que la somme soit 1
            evaluer_tas[0]= ( (float) evaluer_tas[0] /somme);
            evaluer_tas[1]= ((float) evaluer_tas[1]/somme);
        }
        if(somme != 0) net.setVirtualEvidence("Systolic_bp", evaluer_tas);

        //---------------------------------------------------
        double [] evaluer_tad =new double[2];
        evaluer_tad[0]= f.getMembershipDegree(Diastolic_bp,Double.parseDouble(tad), "High_diastolic_bp");
        evaluer_tad[1]=f.getMembershipDegree(Diastolic_bp,Double.parseDouble(tad), "Low_diastolic_bp");
        somme = evaluer_tad[0] + evaluer_tad[1];
        if (somme!=0 && somme!=1){  // je dois normaliser les vals pour que la somme soit 1
            evaluer_tad[0]= ( (float) evaluer_tad[0] /somme);
            evaluer_tad[1]= ((float) evaluer_tad[1]/somme);
        }
        if(somme != 0) net.setVirtualEvidence("Diastolic_bp", evaluer_tad);

        //---------------------------------------------------
        double [] evaluer_taille =new double[2];
        evaluer_taille[0]= f.getMembershipDegree(Height,Double.parseDouble(taille), "High_Height");
        evaluer_taille[1]=f.getMembershipDegree(Height,Double.parseDouble(taille), "Low_Height");
        somme = evaluer_taille[0] + evaluer_taille[1];
        if (somme!=0 && somme!=1){  // je dois normaliser les vals pour que la somme soit 1
            evaluer_taille[0]= ( (float) evaluer_taille[0] /somme);
            evaluer_taille[1]= ((float) evaluer_taille[1]/somme);
        }
        if(somme != 0) net.setVirtualEvidence("Height", evaluer_taille);

        //---------------------------------------------------
        double [] evaluer_poids =new double[2];
        evaluer_poids[0]= f.getMembershipDegree(Weight,Double.parseDouble(poids), "High_Weight");
        evaluer_poids[1]=f.getMembershipDegree(Weight,Double.parseDouble(poids), "Low_Weight");
        somme = evaluer_poids[0] + evaluer_poids[1];
        if (somme!=0 && somme!=1){  // je dois normaliser les vals pour que la somme soit 1
            evaluer_poids[0]= ( (float) evaluer_poids[0] /somme);
            evaluer_poids[1]= ((float) evaluer_poids[1]/somme);
        }
        if(somme != 0) net.setVirtualEvidence("Weight", evaluer_poids);

        //---------------------------------------------------
        double [] evaluer_imc =new double[2];
        evaluer_imc[0]= f.getMembershipDegree(BMI,Double.parseDouble(imc), "BMIobese");
        evaluer_imc[1]=f.getMembershipDegree(BMI,Double.parseDouble(imc), "BMIslim");
        somme = evaluer_imc[0] + evaluer_imc[1];
        if (somme!=0 && somme!=1){  // je dois normaliser les vals pour que la somme soit 1
            evaluer_imc[0]= ( (float) evaluer_imc[0] /somme);
            evaluer_imc[1]= ((float) evaluer_imc[1]/somme);
        }
        if(somme != 0) net.setVirtualEvidence("BMI", evaluer_imc);

        //---------------------------------------------------
        double [] evaluer_tour_taille =new double[2];
        evaluer_tour_taille[0]= f.getMembershipDegree(Waist,Double.parseDouble(tt), "Large_Waist");
        evaluer_tour_taille[1]=f.getMembershipDegree(Waist,Double.parseDouble(tt), "Thin_Waist");
        somme = evaluer_tour_taille[0] + evaluer_tour_taille[1];
        if (somme !=0 && somme !=1){  // je dois normaliser les vals pour que la somme soit 1
            evaluer_tour_taille[0]= ( (float) evaluer_tour_taille[0] / somme);
            evaluer_tour_taille[1]= ((float) evaluer_tour_taille[1]/ somme);
        }
        if(somme != 0) net.setVirtualEvidence("Waist", evaluer_tour_taille);

        //---------------------------------------------------
        double [] evaluer_tour_hanche =new double[2];
        evaluer_tour_hanche[0]= f.getMembershipDegree(Hip,Double.parseDouble(th), "Large_Hip");
        evaluer_tour_hanche[1]=f.getMembershipDegree(Hip,Double.parseDouble(th), "Thin_Hip");
        somme = evaluer_tour_hanche[0] + evaluer_tour_hanche[1];
        if (somme !=0 && somme !=1){  // je dois normaliser les vals pour que la somme soit 1
            evaluer_tour_hanche[0]= ( (float) evaluer_tour_hanche[0] / somme);
            evaluer_tour_hanche[1]= ((float) evaluer_tour_hanche[1]/ somme);
        }
        if(somme!= 0) net.setVirtualEvidence("Hip", evaluer_tour_hanche);
        /////////////////////////////////////////////

        net.setEvidence("Gender",sexe);
        net.updateBeliefs();
        resultat = net.getNodeValue(11);



        for (int i = 0; i <resultat.length ; i++) {
            if (resultat[i]>maxVal){
                maxVal = resultat[i];
            }
        }


        if (maxVal == resultat[0]) { k= "Ce patient est atteint du diabète"; o.addDataProperty(nom_patient,"Diagnostic_Final","true");}
        if (maxVal == resultat[1]) { k= "Ce patient n'est pas atteint du diabète"; o.addDataProperty(nom_patient,"Diagnostic_Final","false");}

        System.out.println(k);

    }

    public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException {
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

        Patient p =new Patient("55","28","female","146","77","120","90","64","126","21.6","28","32");
        System.loadLibrary("jsmile");
        Network net= new Network();
        net.readFile("C:\\Users\\USER\\IdeaProjects\\PFE_Master\\src\\réseauxBayésiens\\RéseauBayesienClassique2.xdsl");
        //p.RemplirAttributs();
        //p.AddToOntology();
        p.FuzzificationPatient(net);
    }
    // getters et setters
    public void setNom_patient(String nom_patient) {
        this.nom_patient = nom_patient;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setChol(String chol) {
        this.chol = chol;
    }

    public void setGlu(String glu) {
        this.glu = glu;
    }

    public void setTas(String tas) {
        this.tas = tas;
    }

    public void setTad(String tad) {
        this.tad = tad;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }

    public void setTh(String th) {
        this.th = th;
    }

    public String getNom_patient() {
        return nom_patient;
    }

    public String getAge() {
        return age;
    }

    public String getSexe() {
        return sexe;
    }

    public String getChol() {
        return chol;
    }

    public String getGlu() {
        return glu;
    }

    public String getTas() {
        return tas;
    }

    public String getTad() {
        return tad;
    }

    public String getTaille() {
        return taille;
    }

    public String getPoids() {
        return poids;
    }

    public String getImc() {
        return imc;
    }

    public String getTt() {
        return tt;
    }

    public String getTh() {
        return th;
    }

    public double[] getResultat() {
        return resultat;
    }
}
