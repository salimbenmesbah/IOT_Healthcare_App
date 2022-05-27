package model;
import com.fuzzylite.term.Trapezoid;
import com.fuzzylite.variable.InputVariable;
//import org.jfree.ui.RefineryUtilities;
import réseauxBayésiens.Fuzzification;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import smile.Network;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class Patient {
    String nom_patient,age,sexe,chol,glu,tas,tad,taille,poids,imc,tt,th;
    String age1,sexe1,chol1,glu1,tas1,tad1,taille1,poids1,imc1,tt1,th1;
    String k = "";
    double maxVal = 0;
    double[] resultat;
    FuzzyOntology o;

    //constructor 1
    public Patient(String nom_patient, String age, String sexe, String chol, String glu, String tas, String tad, String taille, String poids, String imc, String tt, String th) throws OWLOntologyCreationException {
        this.nom_patient = nom_patient; this.age= age; this.sexe=sexe; this.chol=chol; this.glu=glu; this.tas=tas; this.tad=tad; this.taille=taille; this.poids=poids; this.imc=imc;
        this.tt=tt; this.th=th;
        String chemin = "D:\\ontologie\\OntologieFinale.owl";
        o= new FuzzyOntology(chemin);
    }

    //constructor 2
    public Patient() throws OWLOntologyCreationException {
        String chemin = "D:\\ontologie\\OntologieFinale.owl";
        o= new FuzzyOntology(chemin);
    }

    //remplir les attributs
    public void RemplirAttributs(){
        age1 ="Age_patient "+ nom_patient;
        sexe1 ="Sexe_patient "+ nom_patient;
        chol1="Cholesterol_patient "+ nom_patient;
        glu1="Glucose_patient "+ nom_patient;
        tas1="Tension_artérielle_systolique_patient "+ nom_patient;
        tad1 ="Tension_artérielle_diastolique_patient "+ nom_patient;
        taille1 ="taille_patient "+ nom_patient;
        poids1 ="Poids_patient "+ nom_patient;
        imc1 = "IMC_patient"+ nom_patient;
        tt1="Tour_de_taille_patient "+ nom_patient;
        th1 ="Tour_de_hanches_patient "+ nom_patient;
    }

    //ajouter à l'ontologie
    public void AddToOntology() throws OWLOntologyStorageException {
        //o.addIndividual(patient, "Patient");
        RemplirAttributs();

        //ajouter des individus aux classes     demander sexe a kamel
        o.addIndividual(age,"Age");
        o.addIndividual(chol, "Cholesterol");
        o.addIndividual(glu, "Glucose");
        o.addIndividual(tas, "Systolic_bp");
        o.addIndividual(tad, "Diastolic_bp");
        o.addIndividual(taille, "Height");
        o.addIndividual(poids, "Weight");
        o.addIndividual(imc, "BMI");
        o.addIndividual(tt, "Waist");
        o.addIndividual(th, "Hip");

        //ajouter data properties
        o.addDataProperty(nom_patient,"Has_Age",age);
        o.addDataProperty(nom_patient,"",sexe); //demander à kamel
        o.addDataProperty(chol1,"Has_Cholesterol",chol);
        o.addDataProperty(glu1,"Has_Glucose",glu);
        o.addDataProperty(tas1,"Has_SBP_rate",tas);
        o.addDataProperty(tad1,"Has_DBP_rate",tad);
        o.addDataProperty(taille1,"Has_Height",taille);
        o.addDataProperty(poids1,"Has_weight",poids);
        o.addDataProperty(imc1,"Has_BMI",imc);
        o.addDataProperty(tt1,"Has_waist",tt);
        o.addDataProperty(th1,"Has_hip",th);

        //link les propr aux individus
        o.linkIndividuals(nom_patient,age1,"Has_Age");
        o.linkIndividuals(nom_patient,chol1,"Has_Cholesterol");
        o.linkIndividuals(nom_patient,glu1,"Has_Glucose");
        o.linkIndividuals(nom_patient,tas1,"Has_SBP_rate");
        o.linkIndividuals(nom_patient,tad1,"Has_DBP_rate");
        o.linkIndividuals(nom_patient,taille1,"Has_Height");
        o.linkIndividuals(nom_patient,poids1,"Has_Weight");
        o.linkIndividuals(nom_patient,imc1,"Has_BMI");
        o.linkIndividuals(nom_patient,tt1,"Has_waist");
        o.linkIndividuals(nom_patient,th1,"Has_Hip");

    }

    public void FuzzificationPatient(Network net){
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

        InputVariable Tension_a_systo = f.createFuzzyNode("Tension_a_systo", 48,124);
        f.addFuzzyState(Tension_a_systo, new Trapezoid("Low_systolic_bp",90,90, 125.14,164.15));
        f.addFuzzyState(Tension_a_systo, new Trapezoid("High_systolic_bp ",125.14,164.15,250,250));

        //ajouter var floue tad
        InputVariable Tension_a_diasto = f.createFuzzyNode("Tension_a_diasto", 48,124);
        f.addFuzzyState(Tension_a_diasto, new Trapezoid("Low_diastolic_bp",48,48,73.23,94.89));
        f.addFuzzyState(Tension_a_diasto, new Trapezoid("High_diastolic_bp ",73.23,94.89,124,124));

        //ajouter var floue taille
        InputVariable Taille = f.createFuzzyNode("Taille", 52,76);
        f.addFuzzyState(Taille, new Trapezoid("Low_Height",52,52, 62.82,69.45));
        f.addFuzzyState(Taille, new Trapezoid("High_Height",62.82,69.45,76,76));

        //ajouter var floue poids
        InputVariable Poids = f.createFuzzyNode("Poids", 99,325);
        f.addFuzzyState(Poids, new Trapezoid("Low_Weight",99,99, 153.29,220.55));
        f.addFuzzyState(Poids, new Trapezoid("High_Weight",153.29,220.55,325,325));

        //ajouter var floue imc
        InputVariable Imc = f.createFuzzyNode("IMC", 15.2,55.8);
        f.addFuzzyState(Imc, new Trapezoid("BMIslim",15.2, 15.2, 24.74,35.94));
        f.addFuzzyState(Imc, new Trapezoid("BMIobese",24.74,35.94,55.8,55.8));

        //ajouter var floue tt
        InputVariable Tour_Taille = f.createFuzzyNode("Tour_Taille", 26,56);
        f.addFuzzyState(Tour_Taille, new Trapezoid("Thin_Waist",26, 26, 33.74,43.55));
        f.addFuzzyState(Tour_Taille, new Trapezoid("Large_Waist",33.74,43.55,56,56));

        //ajouter var floue th
        InputVariable Tour_Hanche = f.createFuzzyNode("Tour_Taille", 30,64);
        f.addFuzzyState(Tour_Hanche, new Trapezoid("Thin_Hip",30,30,39.52,48.98));
        f.addFuzzyState(Tour_Hanche, new Trapezoid("Large_Hip",39.52,48.98,64,64));

        ////////////////////////////////////
        double [] evaluer_age =new double[2];
        evaluer_age[0]= f.getMembershipDegree(Age,Double.parseDouble(age), "AgeYoung");
        evaluer_age[1]=f.getMembershipDegree(Age,Double.parseDouble(age), "AgeOld");
        double somme = evaluer_age[0] + evaluer_age[1];
        if (somme!=0 && somme!=1){  // je dois normaliser les valeurs pour que la somme soit 1
            evaluer_age[0]= ( (float) evaluer_age[0] /somme);
            evaluer_age[1]= ((float) evaluer_age[1]/somme);
        }
        if(somme != 0) net.setVirtualEvidence("Age", evaluer_age);

        /////////////////////////////////////
        double [] evaluer_cholesterol =new double[2];
        evaluer_cholesterol[0]= f.getMembershipDegree(Cholesterol,Double.parseDouble(age), "Low_Cholesterol");
        evaluer_cholesterol[1]=f.getMembershipDegree(Cholesterol,Double.parseDouble(age), "High_Cholesterol");
        double somme1 = evaluer_cholesterol[0] + evaluer_cholesterol[1];
        if (somme1!=0 && somme1!=1){  // je dois normaliser les valeurs pour que la somme soit 1
            evaluer_cholesterol[0]= ( (float) evaluer_cholesterol[0] /somme);
            evaluer_cholesterol[1]= ((float) evaluer_cholesterol[1]/somme);
        }
        if(somme1 != 0) net.setVirtualEvidence("Cholesterol", evaluer_cholesterol);

        //---------------------------------------------------
        double [] evaluer_glucose =new double[2];
        evaluer_glucose[0]= f.getMembershipDegree(Glucose,Double.parseDouble(age), "Low_Glucose");
        evaluer_glucose[1]=f.getMembershipDegree(Glucose,Double.parseDouble(age), "High_Glucose");
        double somme2 = evaluer_glucose[0] + evaluer_glucose[1];
        if (somme2!=0 && somme2!=1){  // je dois normaliser les derÃ©s pour que la somme soit 1
            evaluer_glucose[0]= ( (float) evaluer_glucose[0] /somme);
            evaluer_glucose[1]= ((float) evaluer_glucose[1]/somme);
        }
        if(somme2 != 0) net.setVirtualEvidence("Glucose", evaluer_glucose);

        //---------------------------------------------------
        double [] evaluer_tas =new double[2];
        evaluer_tas[0]= f.getMembershipDegree(Tension_a_systo,Double.parseDouble(age), "Low_systolic_bp");
        evaluer_tas[1]=f.getMembershipDegree(Tension_a_systo,Double.parseDouble(age), "High_systolic_bp");
        double somme3 = evaluer_tas[0] + evaluer_tas[1];
        if (somme3!=0 && somme3!=1){  // je dois normaliser les derÃ©s pour que la somme soit 1
            evaluer_tas[0]= ( (float) evaluer_tas[0] /somme);
            evaluer_tas[1]= ((float) evaluer_tas[1]/somme);
        }
        if(somme3 != 0) net.setVirtualEvidence("Tension_a_systo", evaluer_tas);

        //---------------------------------------------------
        double [] evaluer_tad =new double[2];
        evaluer_tad[0]= f.getMembershipDegree(Tension_a_diasto,Double.parseDouble(age), "Low_diastolic_bp");
        evaluer_tad[1]=f.getMembershipDegree(Tension_a_diasto,Double.parseDouble(age), "High_diastolic_bp");
        double somme4 = evaluer_tad[0] + evaluer_tad[1];
        if (somme4!=0 && somme4!=1){  // je dois normaliser les vals pour que la somme soit 1
            evaluer_tad[0]= ( (float) evaluer_tad[0] /somme);
            evaluer_tad[1]= ((float) evaluer_tad[1]/somme);
        }
        if(somme4 != 0) net.setVirtualEvidence("Tension_a_diasto", evaluer_tad);

        //---------------------------------------------------
        double [] evaluer_taille =new double[2];
        evaluer_taille[0]= f.getMembershipDegree(Taille,Double.parseDouble(age), "Low_Height");
        evaluer_taille[1]=f.getMembershipDegree(Taille,Double.parseDouble(age), "High_Height");
        double somme5 = evaluer_taille[0] + evaluer_taille[1];
        if (somme5!=0 && somme5!=1){  // je dois normaliser les vals pour que la somme soit 1
            evaluer_taille[0]= ( (float) evaluer_taille[0] /somme);
            evaluer_taille[1]= ((float) evaluer_taille[1]/somme);
        }
        if(somme5 != 0) net.setVirtualEvidence("Taille", evaluer_taille);

        //---------------------------------------------------
        double [] evaluer_poids =new double[2];
        evaluer_poids[0]= f.getMembershipDegree(Poids,Double.parseDouble(age), "Low_Weight");
        evaluer_poids[1]=f.getMembershipDegree(Poids,Double.parseDouble(age), "High_Height");
        double somme6 = evaluer_poids[0] + evaluer_poids[1];
        if (somme6!=0 && somme6!=1){  // je dois normaliser les vals pour que la somme soit 1
            evaluer_poids[0]= ( (float) evaluer_poids[0] /somme6);
            evaluer_poids[1]= ((float) evaluer_poids[1]/somme6);
        }
        if(somme6 != 0) net.setVirtualEvidence("Poids", evaluer_poids);

        //---------------------------------------------------
        double [] evaluer_imc =new double[2];
        evaluer_imc[0]= f.getMembershipDegree(Imc,Double.parseDouble(age), "BMIslim");
        evaluer_imc[1]=f.getMembershipDegree(Imc,Double.parseDouble(age), "BMIobese");
        double somme7 = evaluer_imc[0] + evaluer_imc[1];
        if (somme7!=0 && somme7!=1){  // je dois normaliser les vals pour que la somme soit 1
            evaluer_imc[0]= ( (float) evaluer_imc[0] /somme7);
            evaluer_imc[1]= ((float) evaluer_imc[1]/somme7);
        }
        if(somme7 != 0) net.setVirtualEvidence("Imc", evaluer_imc);

        //---------------------------------------------------
        double [] evaluer_tour_taille =new double[2];
        evaluer_tour_taille[0]= f.getMembershipDegree(Tour_Taille,Double.parseDouble(age), "Thin_Waist");
        evaluer_tour_taille[1]=f.getMembershipDegree(Tour_Taille,Double.parseDouble(age), "Large_Waist");
        double somme8 = evaluer_tour_taille[0] + evaluer_tour_taille[1];
        if (somme8 !=0 && somme8 !=1){  // je dois normaliser les vals pour que la somme soit 1
            evaluer_tour_taille[0]= ( (float) evaluer_tour_taille[0] / somme8);
            evaluer_tour_taille[1]= ((float) evaluer_tour_taille[1]/ somme8);
        }
        if(somme8 != 0) net.setVirtualEvidence("Tour_Taille", evaluer_tour_taille);

        //---------------------------------------------------
        double [] evaluer_tour_hanche =new double[2];
        evaluer_tour_hanche[0]= f.getMembershipDegree(Tour_Hanche,Double.parseDouble(age), "Thin_Hip");
        evaluer_tour_hanche[1]=f.getMembershipDegree(Tour_Hanche,Double.parseDouble(age), "Large_Hip");
        double somme9 = evaluer_tour_hanche[0] + evaluer_tour_hanche[1];
        if (somme9 !=0 && somme9 !=1){  // je dois normaliser les vals pour que la somme soit 1
            evaluer_tour_hanche[0]= ( (float) evaluer_tour_hanche[0] / somme9);
            evaluer_tour_hanche[1]= ((float) evaluer_tour_hanche[1]/ somme9);
        }
        if(somme9!= 0) net.setVirtualEvidence("Tour_Taille", evaluer_tour_hanche);
        /////////////////////////////////////////////
        net.setEvidence("Sexe",sexe);
        net.updateBeliefs();
        resultat = net.getNodeValue("diagnosis"); // à changer


    }
}
