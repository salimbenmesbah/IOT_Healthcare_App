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
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import java.io.File;
import java.util.LinkedList;

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
    private OWLReasonerFactory reasonerFactory ;
    private OWLDataFactory df = OWLManager.getOWLDataFactory();
    OWLOntologyManager manager;

    //constructeur
    public FuzzyOntology(String link) throws OWLOntologyCreationException {
        file = new File(link);
        classes = new LinkedList<String>();
        individuals = new LinkedList<String>();
        objectproperties = new LinkedList<String>();
        dataproperties = new LinkedList<String>();
        datatypes = new LinkedList<String>();
        fuzzydatatypes = new LinkedList<String>();

        try {OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

            ontology = manager.loadOntologyFromOntologyDocument(file);
            ontologyIRI = ontology.getOntologyID().getOntologyIRI();
            pm = new DefaultPrefixManager(ontologyIRI.toString());
            reasonerFactory =new StructuralReasonerFactory();
            reasoner = reasonerFactory.createNonBufferingReasoner(ontology);
            OWLDataFactory df = OWLManager.getOWLDataFactory();


        }catch (OWLOntologyCreationException ex) {}


    }

    //to get classes
    public void getClasses(){
        for (OWLClass i : ontology.getClassesInSignature(true)) {
            classes.add(i.getIRI().getFragment());}

        for (String aClass : classes) {
            System.out.println(aClass);
        }
    }
    //get individuals
    public void getIndividuals(){
        for (OWLNamedIndividual i : ontology.getIndividualsInSignature(true)) {
            individuals.add(i.getIRI().getFragment());}

        for (String individual : individuals) {
            System.out.println(individual);
        }
    }

    //to get object properties
    public void getObjectProperties(){
        for (OWLObjectProperty i : ontology.getObjectPropertiesInSignature(true)) {
            objectproperties.add(i.getIRI().getFragment());}

        for (String objectproperty : objectproperties) {
            System.out.println(objectproperty);
        }
    }

    // to get data properties
    public void getDataProperties(){
        for (OWLDataProperty i : ontology.getDataPropertiesInSignature(true)) {
            dataproperties.add(i.getIRI().getFragment());}

        for (String dataproperty : dataproperties) {
            System.out.println(dataproperty);
        }
    }

    //to get datatypes
    public void getDataTypes(){
        for (OWLDatatype i : ontology.getDatatypesInSignature(true)) {
            datatypes.add(i.getIRI().getFragment());}

        for (String datatype : datatypes) {
            System.out.println(datatype);
        }
    }

    //to get the fuzzy data types
    public void getFuzzyDataTypes(){
        for (OWLDatatype d : ontology.getDatatypesInSignature(true)) {
            for (OWLAnnotation annotation1 : d.getAnnotations(ontology)) {
                if ((annotation1.getProperty().getIRI().getFragment().contains("fuzzyLabel"))){
                    // fuzzydatatypes.add(d.getIRI().getFragment());
                    OWLLiteral literal = (OWLLiteral) annotation1.getValue();
                    String literalString = literal.getLiteral();
                    fuzzydatatypes.add(literalString);
                }}
        }
        for (String fuzzydatatype : fuzzydatatypes) {
            System.out.println(fuzzydatatype);
        }
    }

    //add data property
    public void addDataProperty(String ind,String dp, String v) throws OWLOntologyStorageException{
        OWLOntologyManager manager;
        OWLXMLOntologyFormat owlxmlFormat;
        OWLOntologyFormat format;
        manager = OWLManager.createOWLOntologyManager();
        owlxmlFormat = new OWLXMLOntologyFormat();
        format = manager.getOntologyFormat(ontology);
        //System.out.println("1");
        pm = new DefaultPrefixManager(ontologyIRI.toString().concat("#"));
        OWLNamedIndividual indivi = df.getOWLNamedIndividual(":"+ind, pm);
        OWLDataProperty dpro = df.getOWLDataProperty(":"+dp, pm);
        OWLDataPropertyAssertionAxiom axiomDp = df.getOWLDataPropertyAssertionAxiom(dpro, indivi, v);
        AddAxiom addAxiomDp = new AddAxiom(ontology, axiomDp);
        manager.applyChange(addAxiomDp);
        manager.saveOntology(ontology, owlxmlFormat, IRI.create(file.toURI()));
    }

    //add an individual
    public void addIndividual(String ind, String classes) throws OWLOntologyStorageException{

        OWLOntologyManager manager;
        OWLXMLOntologyFormat owlxmlFormat;
        OWLOntologyFormat format;
        manager = OWLManager.createOWLOntologyManager();
        owlxmlFormat = new OWLXMLOntologyFormat();
        format = manager.getOntologyFormat(ontology);
        // System.out.println("1");
        pm = new DefaultPrefixManager(ontologyIRI.toString().concat("#"));
        OWLClass simpleTypeClass = df.getOWLClass(":"+classes, pm);
        //System.out.println(simpleTypeClass.getIRI().getFragment());
        OWLNamedIndividual indi = df.getOWLNamedIndividual(":"+ind, pm);
        //System.out.println(indi.getIRI().getFragment());
        OWLAxiom axiomI = df.getOWLClassAssertionAxiom(simpleTypeClass, indi);
        AddAxiom addAxiomI = new AddAxiom(ontology, axiomI);
        manager.applyChange(addAxiomI);
        manager.saveOntology(ontology, owlxmlFormat, IRI.create(file.toURI()));
    }

    //link the individuals
    public void linkIndividuals(String ind1, String ind2, String relation) throws OWLOntologyStorageException{

        OWLOntologyManager manager;
        OWLXMLOntologyFormat owlxmlFormat;
        OWLOntologyFormat format;
        manager = OWLManager.createOWLOntologyManager();
        owlxmlFormat = new OWLXMLOntologyFormat();
        format = manager.getOntologyFormat(ontology);
//System.out.println("1");
        pm = new DefaultPrefixManager(ontologyIRI.toString().concat("#"));
///////////////////////////////////////////////////////////////////////////
        OWLNamedIndividual i1 = df.getOWLNamedIndividual(":"+ind1, pm);
        OWLNamedIndividual i2 = df.getOWLNamedIndividual(":"+ind2, pm);
        OWLObjectProperty rel = df.getOWLObjectProperty(":"+relation, pm);
///////////////////////////////////////////////////////////////////////////
        OWLObjectPropertyAssertionAxiom axiomOp = df.getOWLObjectPropertyAssertionAxiom(rel, i1, i2);
        AddAxiom addAxiomOp = new AddAxiom(ontology, axiomOp);
        manager.applyChange(addAxiomOp);
///////////////////////////////////////////////////////////////////////////
        manager.saveOntology(ontology, owlxmlFormat, IRI.create(file.toURI()));
    }


}
