package model;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.OWLEntityRemover;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;

public class FuzzyOntology {
    File file;

    private LinkedList<String> classes;
    private LinkedList<String> individuals;
    private LinkedList<String> objectproperties;
    private LinkedList<String> dataproperties;
    private LinkedList<String> datatypes;
    private LinkedList<String> fuzzydatatypes;

    private OWLOntology ontology;

    private IRI ontologyIRI;
    private PrefixManager pm;
    private OWLReasoner reasoner;
    private OWLReasonerFactory reasonerFactory;
    private OWLDataFactory df = OWLManager.getOWLDataFactory();
    OWLOntologyManager manager;
    //listes de stockage pour la Jtable de la page accueil
    public ArrayList<String> patient = new ArrayList<String>(), age = new ArrayList<String>(), gender = new ArrayList<String>(), cholesterol = new ArrayList<String>(),
            glucose = new ArrayList<String>(), systolic_bp = new ArrayList<String>(), diastolic_bp = new ArrayList<String>(),
            height = new ArrayList<String>(), weight = new ArrayList<String>(), bmi = new ArrayList<String>(),
            waist = new ArrayList<String>(), hip = new ArrayList<String>(), Diagnostic_Final = new ArrayList<String>();

    //constructeur
    public FuzzyOntology(String link) throws OWLOntologyCreationException {
        file = new File(link);
        classes = new LinkedList<String>();
        individuals = new LinkedList<String>();
        objectproperties = new LinkedList<String>();
        dataproperties = new LinkedList<String>();
        datatypes = new LinkedList<String>();
        fuzzydatatypes = new LinkedList<String>();

        try {
            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

            ontology = manager.loadOntologyFromOntologyDocument(file);
            ontologyIRI = ontology.getOntologyID().getOntologyIRI();
            pm = new DefaultPrefixManager(ontologyIRI.toString());
            reasonerFactory = new StructuralReasonerFactory();
            reasoner = reasonerFactory.createNonBufferingReasoner(ontology);
            OWLDataFactory df = OWLManager.getOWLDataFactory();


        } catch (OWLOntologyCreationException ex) {
        }


    }

    //to get classes
    public void getClasses() {
        for (OWLClass i : ontology.getClassesInSignature(true)) {
            classes.add(i.getIRI().getFragment());
        }

        for (String aClass : classes) {
            System.out.println(aClass);
        }
    }

    //get individuals
    public void getIndividuals() {
        for (OWLNamedIndividual i : ontology.getIndividualsInSignature(true)) {
            individuals.add(i.getIRI().getFragment());
        }

        for (String individual : individuals) {
            System.out.println(individual);
        }
    }

    //to get object properties
    public void getObjectProperties() {
        for (OWLObjectProperty i : ontology.getObjectPropertiesInSignature(true)) {
            objectproperties.add(i.getIRI().getFragment());
        }

        for (String objectproperty : objectproperties) {
            System.out.println(objectproperty);
        }
    }

    // to get data properties
    public void getDataProperties() {
        for (OWLDataProperty i : ontology.getDataPropertiesInSignature(true)) {
            dataproperties.add(i.getIRI().getFragment());
        }

        for (String dataproperty : dataproperties) {
            System.out.println(dataproperty);
        }
    }

    //to get datatypes
    public void getDataTypes() {
        for (OWLDatatype i : ontology.getDatatypesInSignature(true)) {
            datatypes.add(i.getIRI().getFragment());
        }

        for (String datatype : datatypes) {
            System.out.println(datatype);
        }
    }

    //to get the fuzzy data types
    public void getFuzzyDataTypes() {
        for (OWLDatatype d : ontology.getDatatypesInSignature(true)) {
            for (OWLAnnotation annotation1 : d.getAnnotations(ontology)) {
                if ((annotation1.getProperty().getIRI().getFragment().contains("fuzzyLabel"))) {
                    // fuzzydatatypes.add(d.getIRI().getFragment());
                    OWLLiteral literal = (OWLLiteral) annotation1.getValue();
                    String literalString = literal.getLiteral();
                    fuzzydatatypes.add(literalString);
                }
            }
        }
        for (String fuzzydatatype : fuzzydatatypes) {
            System.out.println(fuzzydatatype);
        }
    }

    //add data property
    public void addDataProperty(String ind, String dp, String v) throws OWLOntologyStorageException {
        OWLOntologyManager manager;
        OWLXMLOntologyFormat owlxmlFormat;
        OWLOntologyFormat format;
        manager = OWLManager.createOWLOntologyManager();
        owlxmlFormat = new OWLXMLOntologyFormat();
        format = manager.getOntologyFormat(ontology);
        //System.out.println("1");
        pm = new DefaultPrefixManager(ontologyIRI.toString().concat("#"));
        OWLNamedIndividual indivi = df.getOWLNamedIndividual(":" + ind, pm);
        OWLDataProperty dpro = df.getOWLDataProperty(":" + dp, pm);
        OWLDataPropertyAssertionAxiom axiomDp = df.getOWLDataPropertyAssertionAxiom(dpro, indivi, v);
        AddAxiom addAxiomDp = new AddAxiom(ontology, axiomDp);
        manager.applyChange(addAxiomDp);
        manager.saveOntology(ontology, owlxmlFormat, IRI.create(file.toURI()));
    }

    //add an individual
    public void addIndividual(String ind, String classes) throws OWLOntologyStorageException {

        OWLOntologyManager manager;
        OWLXMLOntologyFormat owlxmlFormat;
        OWLOntologyFormat format;
        manager = OWLManager.createOWLOntologyManager();
        owlxmlFormat = new OWLXMLOntologyFormat();
        format = manager.getOntologyFormat(ontology);
        // System.out.println("1");
        pm = new DefaultPrefixManager(ontologyIRI.toString().concat("#"));
        OWLClass simpleTypeClass = df.getOWLClass(":" + classes, pm);
        //System.out.println(simpleTypeClass.getIRI().getFragment());
        OWLNamedIndividual indi = df.getOWLNamedIndividual(":" + ind, pm);
        //System.out.println(indi.getIRI().getFragment());
        OWLAxiom axiomI = df.getOWLClassAssertionAxiom(simpleTypeClass, indi);
        AddAxiom addAxiomI = new AddAxiom(ontology, axiomI);
        manager.applyChange(addAxiomI);
        manager.saveOntology(ontology, owlxmlFormat, IRI.create(file.toURI()));
    }

    //link the individuals
    public void linkIndividuals(String ind1, String ind2, String relation) throws OWLOntologyStorageException {

        OWLOntologyManager manager;
        OWLXMLOntologyFormat owlxmlFormat;
        OWLOntologyFormat format;
        manager = OWLManager.createOWLOntologyManager();
        owlxmlFormat = new OWLXMLOntologyFormat();
        format = manager.getOntologyFormat(ontology);
//System.out.println("1");
        pm = new DefaultPrefixManager(ontologyIRI.toString().concat("#"));
///////////////////////////////////////////////////////////////////////////
        OWLNamedIndividual i1 = df.getOWLNamedIndividual(":" + ind1, pm);
        OWLNamedIndividual i2 = df.getOWLNamedIndividual(":" + ind2, pm);
        OWLObjectProperty rel = df.getOWLObjectProperty(":" + relation, pm);
///////////////////////////////////////////////////////////////////////////
        OWLObjectPropertyAssertionAxiom axiomOp = df.getOWLObjectPropertyAssertionAxiom(rel, i1, i2);
        AddAxiom addAxiomOp = new AddAxiom(ontology, axiomOp);
        manager.applyChange(addAxiomOp);
///////////////////////////////////////////////////////////////////////////
        manager.saveOntology(ontology, owlxmlFormat, IRI.create(file.toURI()));
    }

    public void DELETE(String nameClass, String nameP) throws OWLOntologyCreationException, OWLOntologyStorageException {
        manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
        OWLDataProperty dp;
        OWLReasoner reasoner = reasonerFactory.createNonBufferingReasoner(ontology);

        OWLEntityRemover removerToDeleteAlreadyAssignedInds = new OWLEntityRemover(manager, Collections.singleton(ontology));
        for (OWLClass cls : ontology.getClassesInSignature()) {
            if (cls.getIRI().getFragment().equals(nameClass)) {

                NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cls, false);
                System.out.println("Number of instances of class" + nameClass + " is  " + instances.getFlattened().size());
                for (OWLNamedIndividual ind : instances.getFlattened()) {
                    //je récupére touts les instances de patient
                    if (ind.getIRI().getFragment().equals(nameP)) {
                        ind.accept(removerToDeleteAlreadyAssignedInds);
                    }
                }
                manager.applyChanges(removerToDeleteAlreadyAssignedInds.getChanges());
                removerToDeleteAlreadyAssignedInds.reset();
                manager.saveOntology(ontology);
            }
        }
    }

    public ArrayList<String> getIndividulsByClass(String nameClass) throws Exception {
        ArrayList<String> ip=new ArrayList<String>();

        manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
        OWLDataProperty dp;

        OWLReasoner reasoner = reasonerFactory.createNonBufferingReasoner(ontology);
        for (OWLClass cls : ontology.getClassesInSignature()) {   //pour chaque classe de l'ontologie
            if (cls.getIRI().getFragment().equals(nameClass)) {         //pour selectionner la classe patient

                NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cls, false); // recuperer les individus de la classe patient
                System.out.println("Number of instances of the class \"" + nameClass + "\" is  " + instances.getFlattened().size());
                for (OWLNamedIndividual ind : instances.getFlattened()) {  //parcourir chaque individu de la classe patient

                    ip.add(ind.getIRI().getFragment());

                    System.out.println(" Individual " + ind.getIRI().getFragment());
                    //ajouter patient
                    patient.add(ind.getIRI().getFragment());                   //ajouter l'instance patient à l'array list
                    dp = null;
                    // récupérer la dataproperty "Diagnostic_Final" pour le patient courant
                    for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()){ //pour chaque dataproperty
                        if (ont.getIRI().getFragment().equals("Diagnostic_Final") ){              //selectionner le dataproperty Diagnostic_Final
                            dp = ont  ;
                            Set<OWLLiteral>  values = reasoner.getDataPropertyValues(ind, dp);       //trouver les valeurs de Diagnostic_Final du patient
                            if (values == null) {Diagnostic_Final.add(null); }
                            for(OWLLiteral ol: values){ System.out.print(" \t: Diagnostic_Final = " + ol.getLiteral());
                                //ajouter leur Age
                                Diagnostic_Final.add(ol.getLiteral());                                                   //ajouter ces valeurs à l'arraylist Diagnostic_Final
                            }
                        }
                    }
                    /////////////////////////////////////////////////////////
                    //recuperer les instances de chaque autre classe liées avec l'instance patient en question
                    //ensuite parcourir leurs dataproperty
                    //AGE
                    OWLObjectProperty op = null;
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {    //pour chaque objectproperty
                        if (opp.getIRI().getFragment().equals("Has_age")) {                   // selectionner le objectproperty hasage

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesAge = reasoner.getObjectPropertyValues(ind, op).getFlattened(); //trouver les instances age reliées avec l'individu patient
                    for (OWLNamedIndividual indage : valuesAge) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("Has_Age")) {
                                dp = ont;
                                Set<OWLLiteral> valuesage = reasoner.getDataPropertyValues(indage, dp);// trouver les valeur des dataproperty has_AGE du patient

                                for (OWLLiteral ol : valuesage) {
                                    System.out.print(" \t: Has_Age = " + ol.getLiteral());
                                    //ajouter leur alt
                                    age.add(ol.getLiteral());
                                }
                            }
                        }
                    }
                    ////////////////////////////////////////////////////////
                    //Gender
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has_gender")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesGender = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indgender : valuesGender) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("gender")) {
                                dp = ont;
                                Set<OWLLiteral> valuesgender = reasoner.getDataPropertyValues(indgender, dp);

                                for (OWLLiteral ol : valuesgender) {
                                    System.out.print(" \t: gender = " + ol.getLiteral());
                                    //ajouter leur alt
                                    gender.add(ol.getLiteral());
                                }
                            }
                        }


                    }
                    ////////////////////////////////////////////////////////
                    //cholesterol
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has_cholesterol")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesCholesterol = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indcholesterol : valuesCholesterol) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("Has_Cholesterol")) {
                                dp = ont;
                                Set<OWLLiteral> valuescholesterol = reasoner.getDataPropertyValues(indcholesterol, dp);

                                for (OWLLiteral ol : valuescholesterol) {
                                    System.out.print(" \t: Has_Cholesterol = " + ol.getLiteral());
                                    //ajouter leur alt
                                    cholesterol.add(ol.getLiteral());
                                }
                            }
                        }


                    }
                    ////////////////////////////////////////////////////////
                    //Glucose
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has_glucose")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesGlucose = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indglucose : valuesGlucose) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("Has_Glucose")) {
                                dp = ont;
                                Set<OWLLiteral> valuesglucose = reasoner.getDataPropertyValues(indglucose, dp);

                                for (OWLLiteral ol : valuesglucose) {
                                    System.out.print(" \t: Has_Glucose = " + ol.getLiteral());
                                    //ajouter leur alt
                                    glucose.add(ol.getLiteral());
                                }
                            }
                        }
                    }
                    ////////////////////////////////////////////////////////
                    //Systolic_bp
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has_SBP")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesSBP = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indSBP : valuesSBP) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("Has_SBP_rate")) {
                                dp = ont;
                                Set<OWLLiteral> valuessbp = reasoner.getDataPropertyValues(indSBP, dp);

                                for (OWLLiteral ol : valuessbp) {
                                    System.out.print(" \t: Has_SBP_rate = " + ol.getLiteral());
                                    //ajouter leur alt
                                    systolic_bp.add(ol.getLiteral());
                                }
                            }
                        }
                    }
                    ////////////////////////////////////////////////////////
                    //Diastolic_bp
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has_DBP")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesDBP = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indDBP : valuesDBP) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("Has_DBP_rate")) {
                                dp = ont;
                                Set<OWLLiteral> valuesdbp = reasoner.getDataPropertyValues(indDBP, dp);

                                for (OWLLiteral ol : valuesdbp) {
                                    System.out.print(" \t: Has_DBP_rate = " + ol.getLiteral());
                                    //ajouter leur alt
                                    diastolic_bp.add(ol.getLiteral());
                                }
                            }
                        }
                    }
                    ////////////////////////////////////////////////////////
                    //Height
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has_Height")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesHeight = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indHeight : valuesHeight) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("Has_height")) {
                                dp = ont;
                                Set<OWLLiteral> valuesheight = reasoner.getDataPropertyValues(indHeight, dp);

                                for (OWLLiteral ol : valuesheight) {
                                    System.out.print(" \t: Has_height = " + ol.getLiteral());
                                    //ajouter leur alt
                                    height.add(ol.getLiteral());
                                }
                            }
                        }
                    }
                    ////////////////////////////////////////////////////////
                    //Weight
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has_Weight")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesWeight = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indWeight : valuesWeight) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("Has_weight")) {
                                dp = ont;
                                Set<OWLLiteral> valuesweight = reasoner.getDataPropertyValues(indWeight, dp);

                                for (OWLLiteral ol : valuesweight) {
                                    System.out.print(" \t: Has_weight = " + ol.getLiteral());
                                    //ajouter leur alt
                                    weight.add(ol.getLiteral());
                                }
                            }
                        }
                    }
                    ////////////////////////////////////////////////////////
                    //bmi
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has_bmi")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesBMI = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indBMI : valuesBMI) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("Has_BMI")) {
                                dp = ont;
                                Set<OWLLiteral> valuesbmi = reasoner.getDataPropertyValues(indBMI, dp);

                                for (OWLLiteral ol : valuesbmi) {
                                    System.out.print(" \t: Has_BMI = " + ol.getLiteral());
                                    //ajouter leur alt
                                    bmi.add(ol.getLiteral());
                                }
                            }
                        }
                    }
                    ////////////////////////////////////////////////////////
                    //waist
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has_Waist")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesWaist = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indWaist : valuesWaist) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("Has_waist")) {
                                dp = ont;
                                Set<OWLLiteral> valueswaist = reasoner.getDataPropertyValues(indWaist, dp);

                                for (OWLLiteral ol : valueswaist) {
                                    System.out.print(" \t: Has_waist = " + ol.getLiteral());
                                    //ajouter leur alt
                                    waist.add(ol.getLiteral());
                                }
                            }
                        }
                    }
                    ////////////////////////////////////////////////////////
                    //hip
                    for (OWLObjectProperty opp : ontology.getObjectPropertiesInSignature()) {
                        if (opp.getIRI().getFragment().equals("Has_Hip")) {

                            op = opp;
                        }
                    }


                    Set<OWLNamedIndividual> valuesHip = reasoner.getObjectPropertyValues(ind, op).getFlattened();
                    for (OWLNamedIndividual indHip : valuesHip) {
                        for (OWLDataProperty ont : ontology.getDataPropertiesInSignature()) {
                            if (ont.getIRI().getFragment().equals("Has_hip")) {
                                dp = ont;
                                Set<OWLLiteral> valueship = reasoner.getDataPropertyValues(indHip, dp);

                                for (OWLLiteral ol : valueship) {
                                    System.out.println(" \t: Has_hip = " + ol.getLiteral());
                                    //ajouter leur alt
                                    hip.add(ol.getLiteral());
                                }
                            }
                        }
                    }
                }
            }
        }
    return ip;}
}