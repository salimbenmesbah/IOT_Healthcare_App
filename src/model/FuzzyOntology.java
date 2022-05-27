package model;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.model.OWLDataFactory;

import java.io.File;
import java.util.LinkedList;

public class FuzzyOntology {
    private File file;
    private IRI ontologyIRI;
    private OWLOntology ontology;
    private LinkedList<String> classes;
    private LinkedList<String> individuals;
    private LinkedList<String> objectproperties;
    private LinkedList<String> dataproperties;
    private LinkedList<String> datatypes;
    private LinkedList<String> fuzzydatatypes;
    private PrefixManager pm;
    private OWLDataFactory df = OWLManager.getOWLDataFactory();

    //constructeur
    public FuzzyOntology(String link) throws OWLOntologyCreationException {
        file = new File(link);
        classes = new LinkedList<String>();
        individuals = new LinkedList<String>();
        objectproperties = new LinkedList<String>();
        dataproperties = new LinkedList<String>();
        datatypes = new LinkedList<String>();
        fuzzydatatypes = new LinkedList<String>();
        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
        ontology = man.loadOntologyFromOntologyDocument(file);
        ontologyIRI = ontology.getOntologyID().getOntologyIRI();
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
        pm = new DefaultPrefixManager(ontologyIRI.toString().concat("#"));
        OWLNamedIndividual indivi = df.getOWLNamedIndividual(":"+ind, pm);
        OWLDataProperty dpro = df.getOWLDataProperty(":"+dp, pm);
        OWLDataPropertyAssertionAxiom axiomDp = df.getOWLDataPropertyAssertionAxiom(dpro, indivi, v);
        AddAxiom addAxiomDp = new AddAxiom(ontology, axiomDp);
        manager.applyChange(addAxiomDp);
        manager.saveOntology(ontology, owlxmlFormat, IRI.create(file.toURI()));
    }

    //add an individual
    public void addIndividual(String ind, String classe) throws OWLOntologyStorageException{

        OWLOntologyManager manager;
        OWLXMLOntologyFormat owlxmlFormat;
        OWLOntologyFormat format;
        manager = OWLManager.createOWLOntologyManager();
        owlxmlFormat = new OWLXMLOntologyFormat();
        format = manager.getOntologyFormat(ontology);
        pm = new DefaultPrefixManager(ontologyIRI.toString().concat("#"));
        OWLClass simpleTypeClass = df.getOWLClass(":"+classe, pm);
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
