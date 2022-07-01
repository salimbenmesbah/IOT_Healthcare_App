/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Winsido
 */
package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import javax.swing.JButton;

public class InferencePatient extends JFrame {

    private JFrame frame;
    private JTextField nom;
    private JTextField age;
    private JComboBox sexe;
    private JTextField chol;
    private JTextField glu;
    private JTextField tas;
    private JTextField tad;
    private JTextField taille;
    private JTextField poids;

    private JTextField imc;
    private JTextField tt;
    private JTextField th;
    private JButton inferer,accueil;


    public InferencePatient() {

        setBounds(100, 100, 894, 462);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setLayout(null);
        this.setResizable(false);

        this.setLocation(500, 300);
        JPanel paneln = new JPanel();
        paneln.setBackground(Color.WHITE);
        paneln.setBounds(0, 0, 900, 425);
        getContentPane().add(paneln);
        paneln.setLayout(null);

        JLabel label_nom = new JLabel("Nom :");
        label_nom.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_nom.setBounds(31, 74, 98, 43);
        paneln.add(label_nom);

        JLabel label_age = new JLabel("Age :");
        label_age.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_age.setBounds(31, 144, 98, 43);
        paneln.add(label_age);

        JLabel label_sexe = new JLabel("Sexe : ");
        label_sexe.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_sexe.setBounds(31, 209, 98, 43);
        paneln.add(label_sexe);

        JLabel label_chol = new JLabel("CHOL :");
        label_chol.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_chol.setBounds(31, 272, 98, 43);
        paneln.add(label_chol);

        JLabel label_glu = new JLabel("GLU : ");
        label_glu.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_glu.setBounds(31, 336, 98, 43);
        paneln.add(label_glu);

        JLabel label_tas = new JLabel("TAS :");
        label_tas.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_tas.setBounds(317, 74, 98, 43);
        paneln.add(label_tas);

        JLabel label_tad = new JLabel("TAD : ");
        label_tad.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_tad.setBounds(317, 144, 98, 43);
        paneln.add(label_tad);

        JLabel label_taille = new JLabel("Taille :");
        label_taille.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_taille.setBounds(317, 209, 98, 43);
        paneln.add(label_taille);

        JLabel label_poids = new JLabel("Poids :");
        label_poids.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_poids.setBounds(317, 272, 98, 43);
        paneln.add(label_poids);

        JLabel label_imc = new JLabel("IMC :");
        label_imc.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_imc.setBounds(317, 336, 98, 43);
        paneln.add(label_imc);

        JLabel label_tt = new JLabel("TT :");
        label_tt.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_tt.setBounds(594, 74, 98, 43);
        paneln.add(label_tt);

        JLabel label_th = new JLabel("TH : ");
        label_th.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_th.setBounds(594, 144, 98, 43);
        paneln.add(label_th);

        nom = new JTextField();
        nom.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        nom.setBounds(103, 83, 164, 29);
        nom.setEditable(false);
        paneln.add(nom);
        nom.setColumns(10);

        age = new JTextField();
        age.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        age.setColumns(10);
        age.setBounds(103, 152, 164, 29);
        age.setEditable(false);
        paneln.add(age);

        sexe = new JComboBox();
        sexe.setModel(new DefaultComboBoxModel(new String[] {"male", "female"}));
        sexe.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        sexe.setBounds(103, 217, 164, 29);
        sexe.setEnabled(false);
        paneln.add(sexe);

        chol = new JTextField();
        chol.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        chol.setColumns(10);
        chol.setBounds(103, 280, 164, 29);
        chol.setEditable(false);
        paneln.add(chol);

        glu = new JTextField();
        glu.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        glu.setColumns(10);
        glu.setBounds(103, 344, 164, 29);
        glu.setEditable(false);
        paneln.add(glu);

        tas = new JTextField();
        tas.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        tas.setColumns(10);
        tas.setBounds(381, 83, 164, 29);
        tas.setEditable(false);
        paneln.add(tas);

        tad = new JTextField();
        tad.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        tad.setColumns(10);
        tad.setBounds(381, 152, 164, 29);
        tad.setEditable(false);
        paneln.add(tad);

        taille = new JTextField();
        taille.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        taille.setColumns(10);
        taille.setBounds(381, 217, 164, 29);
        taille.setEditable(false);
        paneln.add(taille);

        poids = new JTextField();
        poids.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        poids.setColumns(10);
        poids.setBounds(381, 280, 164, 29);
        poids.setEditable(false);
        paneln.add(poids);

        imc = new JTextField();
        imc.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        imc.setColumns(10);
        imc.setBounds(381, 344, 164, 29);
        imc.setEditable(false);
        paneln.add(imc);

        tt = new JTextField();
        tt.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        tt.setColumns(10);
        tt.setBounds(667, 83, 164, 29);
        tt.setEditable(false);
        paneln.add(tt);

        th = new JTextField();
        th.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        th.setColumns(10);
        th.setBounds(667, 152, 164, 29);
        th.setEditable(false);
        paneln.add(th);


        JLabel titre = new JLabel("Diagnostiquer Patient ");
        titre.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD , 24));
        titre.setBounds(340, 10, 300, 30);
        //titre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        paneln.add(titre);

        inferer = new JButton("Prédire");
        inferer.setBounds(667, 298, 164, 35);
        paneln.add(inferer);
        accueil = new JButton("Accueil");
        accueil.setBounds(667, 349, 164, 35);
        paneln.add(accueil);
        JLabel bgc = new JLabel("");
        bgc.setIcon(new ImageIcon(InferencePatient.class.getResource("/images/background.jpg")));
        bgc.setBounds(0, 62, 890, 366);
        paneln.add(bgc);
        this.setVisible(true);
    }

    public JFrame getFrame() {return frame;}
    public void setFrame(JFrame frame) {this.frame = frame;}
    //Getters & Setters des facteurs
    public JTextField getNom() {return nom;}
    public void setNom(JTextField nom) {this.nom = nom;}

    public JTextField getAge() {return age;}
    public void setAge(JTextField age) {this.age = age;}

    public JTextField getChol() {return chol;}
    public void setChol(JTextField chol) {this.chol = chol;}

    public JComboBox getSexe() {
        return sexe;
    }
    public void setSexe(JComboBox sexe) {
        this.sexe = sexe;
    }

    public JTextField getGlu() {
        return glu;
    }
    public void setGlu(JTextField glu) {this.glu = glu;}

    public JTextField getTas() {
        return tas;
    }
    public void setTas(JTextField tas) {this.tas = tas;}

    public JTextField getTad() {
        return tad;
    }
    public void setTad(JTextField tad) {
        this.tad = tad;
    }

    public JTextField getTaille() {
        return taille;
    }
    public void setTaille(JTextField taille) {
        this.taille = taille;
    }

    public JTextField getPoids() {
        return poids;
    }
    public void setPoids(JTextField poids) {
        this.poids = poids;
    }

    public JTextField getImc() {
        return imc;
    }
    public void setImc(JTextField imc) {
        this.imc = imc;
    }

    public JTextField getTt() {return tt;}
    public void setTt(JTextField tt) {
        this.tt = tt;
    }

    public JTextField getTh() {
        return th;
    }
    public void setTh(JTextField th) {
        this.th = th;
    }



    public JButton getInferer() {
        return inferer;
    }

    public void setInferer(JButton ajouter) {
        this.inferer = ajouter;
    }

    public JButton getAccueil() {
        return accueil;
    }

    public void setAccueil(JButton accueil) {
        this.accueil = accueil;
    }




}
