package view;
import java.awt.*;

import javax.swing.*;
public class Ajouter extends JFrame {


    private JTextField patient;
    private JTextField age;
    private JTextField bmi;
    private JTextField WBC;
    private JTextField RBC;
    private JTextField HGB;
    private JTextField PLAT;
    private JTextField AST1;
    private JTextField ALT1;
    private JTextField ALT4;
    private JTextField ALT12;
    private JTextField ALT24;
    private JTextField ALT36;
    private JTextField ALT48;
    private JTextField ALT24W;
    private JTextField RNABASE;
    private JTextField RNA4;
    private JTextField RNA12;
    private JTextField RNAEOT;
    private JTextField RNAEF;
    private JComboBox grading, sexe, fievre, nausee, maux, diarrhee, fatigue, boneache, jaunisse, douleur;
    private JButton addi;


    public Ajouter() {

        setBounds(500, 250, 963, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setTitle("Ajouter un nouveau patient");
        setResizable(false);


        JLabel titre = new JLabel("Ajouter nouveau patient");
        titre.setBounds(335, 25, 537, 30);
        titre.setFont(new Font("Serif", Font.CENTER_BASELINE, 14));
        add(titre);

        JLabel label_nom = new JLabel("Nom : ");
        label_nom.setBounds(25, 78, 75, 24);
        add(label_nom);

        JLabel label_age = new JLabel("Age : ");
        label_age.setBounds(25, 112, 75, 24);
        add(label_age);

        JLabel label_sexe = new JLabel("Sexe : ");
        label_sexe.setBounds(25, 155, 75, 24);
        add(label_sexe);

        JLabel lblNewLabel_1_3 = new JLabel("BMI : ");
        lblNewLabel_1_3.setBounds(25, 203, 75, 24);
        add(lblNewLabel_1_3);

        JLabel lblNewLabel_1_4 = new JLabel("Fi\u00E9vre : ");
        lblNewLabel_1_4.setBounds(25, 237, 75, 24);
        add(lblNewLabel_1_4);

        JLabel lblNewLabel_1_5 = new JLabel("Jaunisse: ");
        lblNewLabel_1_5.setBounds(327, 78, 75, 24);
        add(lblNewLabel_1_5);

        JLabel lblNewLabel_1_6 = new JLabel("Douleur epigastric : ");
        lblNewLabel_1_6.setBounds(327, 112, 99, 24);
        add(lblNewLabel_1_6);

        JLabel lblNewLabel_1_7 = new JLabel("WBC : ");
        lblNewLabel_1_7.setBounds(327, 148, 75, 24);
        add(lblNewLabel_1_7);

        JLabel lblNewLabel_1_8 = new JLabel("RBC : ");
        lblNewLabel_1_8.setBounds(327, 203, 75, 24);
        add(lblNewLabel_1_8);

        JLabel lblNewLabel_1_9 = new JLabel("HGB : ");
        lblNewLabel_1_9.setBounds(327, 237, 75, 24);
        add(lblNewLabel_1_9);

        JLabel lblNewLabel_1_10 = new JLabel("ALT24 : ");
        lblNewLabel_1_10.setBounds(623, 78, 99, 24);
        add(lblNewLabel_1_10);

        JLabel lblNewLabel_1_11 = new JLabel("ALT36 : ");
        lblNewLabel_1_11.setBounds(623, 114, 99, 24);
        add(lblNewLabel_1_11);

        JLabel lblNewLabel_1_12 = new JLabel("ALT48 : ");
        lblNewLabel_1_12.setBounds(623, 154, 99, 24);
        add(lblNewLabel_1_12);

        JLabel lblNewLabel_1_13 = new JLabel("ALTafter24w: ");
        lblNewLabel_1_13.setBounds(623, 203, 99, 24);
        add(lblNewLabel_1_13);

        JLabel lblNewLabel_1_14 = new JLabel("RNA BASE : ");
        lblNewLabel_1_14.setBounds(623, 245, 99, 24);
        add(lblNewLabel_1_14);

        JLabel lblNewLabel_1_15 = new JLabel("Naus\u00E9e : ");
        lblNewLabel_1_15.setBounds(25, 279, 75, 24);
        add(lblNewLabel_1_15);

        JLabel lblNewLabel_1_16 = new JLabel("Maux de tete  : ");
        lblNewLabel_1_16.setBounds(25, 334, 75, 24);
        add(lblNewLabel_1_16);

        JLabel lblNewLabel_1_17 = new JLabel("PLAT : ");
        lblNewLabel_1_17.setBounds(327, 279, 75, 24);
        add(lblNewLabel_1_17);

        JLabel lblNewLabel_1_18 = new JLabel("AST1: ");
        lblNewLabel_1_18.setBounds(327, 334, 75, 24);
        add(lblNewLabel_1_18);

        JLabel lblNewLabel_1_19 = new JLabel("RNA4 : ");
        lblNewLabel_1_19.setBounds(623, 284, 99, 24);
        add(lblNewLabel_1_19);

        JLabel lblNewLabel_1_20 = new JLabel("RNA12: ");
        lblNewLabel_1_20.setBounds(623, 334, 99, 24);
        add(lblNewLabel_1_20);

        JLabel lblNewLabel_1_21 = new JLabel("Diarrh\u00E9e : ");
        lblNewLabel_1_21.setBounds(25, 385, 75, 24);
        add(lblNewLabel_1_21);

        JLabel lblNewLabel_1_22 = new JLabel("ALT1: ");
        lblNewLabel_1_22.setBounds(327, 385, 75, 24);
        add(lblNewLabel_1_22);

        JLabel lblNewLabel_1_23 = new JLabel("RNA EOT: ");
        lblNewLabel_1_23.setBounds(623, 385, 99, 24);
        add(lblNewLabel_1_23);

        JLabel lblNewLabel_1_24 = new JLabel("Fatigue : ");
        lblNewLabel_1_24.setBounds(25, 429, 75, 24);
        add(lblNewLabel_1_24);

        JLabel lblNewLabel_1_25 = new JLabel("ALT4 : ");
        lblNewLabel_1_25.setBounds(327, 429, 75, 24);
        add(lblNewLabel_1_25);

        JLabel lblNewLabel_1_26 = new JLabel("RNAEF : ");
        lblNewLabel_1_26.setBounds(623, 429, 99, 24);
        add(lblNewLabel_1_26);

        JLabel lblNewLabel_1_27 = new JLabel("Bone ache : ");
        lblNewLabel_1_27.setBounds(25, 487, 75, 24);
        add(lblNewLabel_1_27);

        JLabel lblNewLabel_1_28 = new JLabel("ALT12 : ");
        lblNewLabel_1_28.setBounds(327, 487, 75, 24);
        add(lblNewLabel_1_28);

        JLabel lblNewLabel_1_29 = new JLabel("Grading : ");
        lblNewLabel_1_29.setBounds(623, 487, 99, 24);
        add(lblNewLabel_1_29);

        patient = new JTextField();
        patient.setBounds(114, 81, 134, 19);
        add(patient);
        patient.setColumns(10);

        age = new JTextField();
        age.setColumns(10);
        age.setBounds(114, 117, 134, 19);
        add(age);

        sexe = new JComboBox();
        sexe.setModel(new DefaultComboBoxModel(new String[]{"Homme", "Femme"}));
        sexe.setBounds(117, 156, 134, 21);
        add(sexe);

        bmi = new JTextField();
        bmi.setBounds(114, 206, 134, 19);
        add(bmi);
        bmi.setColumns(10);

        fievre = new JComboBox();
        fievre.setModel(new DefaultComboBoxModel(new String[]{"Absent", "Present"}));
        fievre.setBounds(114, 247, 134, 21);
        add(fievre);

        nausee = new JComboBox();
        nausee.setModel(new DefaultComboBoxModel(new String[]{"Absent", "Present"}));
        nausee.setBounds(114, 286, 134, 21);
        add(nausee);

        maux = new JComboBox();
        maux.setModel(new DefaultComboBoxModel(new String[]{"Absent", "Present"}));
        maux.setBounds(114, 336, 134, 21);
        add(maux);

        diarrhee = new JComboBox();
        diarrhee.setModel(new DefaultComboBoxModel(new String[]{"Absent", "Present"}));
        diarrhee.setBounds(114, 387, 134, 21);
        add(diarrhee);

        fatigue = new JComboBox();
        fatigue.setModel(new DefaultComboBoxModel(new String[]{"Absent", "Present"}));
        fatigue.setBounds(114, 431, 134, 21);
        add(fatigue);

        boneache = new JComboBox();
        boneache.setModel(new DefaultComboBoxModel(new String[]{"Absent", "Present"}));
        boneache.setBounds(114, 489, 134, 21);
        add(boneache);

        jaunisse = new JComboBox();
        jaunisse.setModel(new DefaultComboBoxModel(new String[]{"Absent", "Present"}));
        jaunisse.setBounds(449, 80, 134, 21);
        add(jaunisse);

        douleur = new JComboBox();
        douleur.setModel(new DefaultComboBoxModel(new String[]{"Absent", "Present"}));
        douleur.setBounds(449, 114, 134, 21);
        add(douleur);

        WBC = new JTextField();
        WBC.setBounds(449, 151, 134, 19);
        add(WBC);
        WBC.setColumns(10);

        RBC = new JTextField();
        RBC.setBounds(449, 206, 134, 19);
        add(RBC);
        RBC.setColumns(10);

        HGB = new JTextField();
        HGB.setBounds(449, 240, 134, 19);
        add(HGB);
        HGB.setColumns(10);

        PLAT = new JTextField();
        PLAT.setBounds(449, 282, 134, 19);
        add(PLAT);
        PLAT.setColumns(10);

        AST1 = new JTextField();
        AST1.setBounds(449, 337, 134, 19);
        add(AST1);
        AST1.setColumns(10);

        ALT1 = new JTextField();
        ALT1.setBounds(449, 388, 134, 19);
        add(ALT1);
        ALT1.setColumns(10);

        ALT4 = new JTextField();
        ALT4.setBounds(447, 432, 136, 19);
        add(ALT4);
        ALT4.setColumns(10);

        ALT12 = new JTextField();
        ALT12.setText("");
        ALT12.setBounds(449, 490, 134, 19);
        add(ALT12);
        ALT12.setColumns(10);

        ALT24 = new JTextField();
        ALT24.setBounds(738, 81, 134, 19);
        add(ALT24);
        ALT24.setColumns(10);

        ALT36 = new JTextField();
        ALT36.setText("");
        ALT36.setBounds(738, 117, 134, 19);
        add(ALT36);
        ALT36.setColumns(10);

        ALT48 = new JTextField();
        ALT48.setText("");
        ALT48.setBounds(738, 157, 134, 19);
        add(ALT48);
        ALT48.setColumns(10);

        ALT24W = new JTextField();
        ALT24W.setText("");
        ALT24W.setBounds(738, 206, 134, 19);
        add(ALT24W);
        ALT24W.setColumns(10);

        RNABASE = new JTextField();
        RNABASE.setText("");
        RNABASE.setBounds(738, 248, 134, 19);
        add(RNABASE);
        RNABASE.setColumns(10);

        RNA4 = new JTextField();
        RNA4.setBounds(738, 287, 134, 19);
        add(RNA4);
        RNA4.setColumns(10);

        RNA12 = new JTextField();
        RNA12.setText("");
        RNA12.setBounds(738, 337, 134, 19);
        add(RNA12);
        RNA12.setColumns(10);

        RNAEOT = new JTextField();
        RNAEOT.setText("");
        RNAEOT.setBounds(738, 388, 134, 19);
        add(RNAEOT);
        RNAEOT.setColumns(10);

        RNAEF = new JTextField();
        RNAEF.setBounds(738, 432, 134, 19);
        add(RNAEF);
        RNAEF.setColumns(10);

        grading = new JComboBox();
        grading.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"}));
        grading.setBounds(738, 489, 134, 21);
        add(grading);

        addi = new JButton("Ajouter");
        addi.setBounds(397, 563, 106, 21);
        add(addi);


        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public JTextField getPatient() {
        return patient;
    }

    public JTextField getAge() {
        return age;
    }

    public JTextField getBmi() {
        return bmi;
    }

    public JTextField getWBC() {
        return WBC;
    }

    public JTextField getRBC() {
        return RBC;
    }

    public JTextField getHGB() {
        return HGB;
    }

    public JTextField getPLAT() {
        return PLAT;
    }

    public JTextField getAST1() {
        return AST1;
    }

    public JTextField getALT1() {
        return ALT1;
    }

    public JTextField getALT4() {
        return ALT4;
    }

    public JTextField getALT12() {
        return ALT12;
    }

    public JTextField getALT24() {
        return ALT24;
    }

    public JTextField getALT36() {
        return ALT36;
    }

    public JTextField getALT48() {
        return ALT48;
    }

    public JTextField getALT24W() {
        return ALT24W;
    }

    public JTextField getRNABASE() {
        return RNABASE;
    }

    public JTextField getRNA4() {
        return RNA4;
    }

    public JTextField getRNA12() {
        return RNA12;
    }

    public JTextField getRNAEOT() {
        return RNAEOT;
    }

    public JTextField getRNAEF() {
        return RNAEF;
    }

    public JComboBox getGrading() {
        return grading;
    }

    public JButton getAddi() {
        return addi;
    }

    public JComboBox getSexe() {
        return sexe;
    }

    public JComboBox getFievre() {
        return fievre;
    }

    public JComboBox getNausee() {
        return nausee;
    }

    public JComboBox getMaux() {
        return maux;
    }

    public JComboBox getDiarrhee() {
        return diarrhee;
    }

    public JComboBox getFatigue() {
        return fatigue;
    }

    public JComboBox getBoneache() {
        return boneache;
    }

    public JComboBox getJaunisse() {
        return jaunisse;
    }

    public JComboBox getDouleur() {
        return douleur;
    }
}

