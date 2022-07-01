package view;
import javax.swing.*;
import java.awt.*;

public class AcceuilPage extends JFrame{
    private JFrame frame;
    private JTextField recherche;
    private JTable table;

    public JButton ajouter,modifier,inference,supprimer,actualiser,chercher;

    public AcceuilPage(){
        this.setResizable(false);
        setBounds(100, 100, 890, 524);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBounds(0, 0, 889, 487);
        getContentPane().add(panel);
        JLabel bgc = new JLabel("");

         bgc.setIcon(new ImageIcon(AcceuilPage.class.getResource("/images/background.jpg")));
        bgc.setBounds(0, 72, 880, 430);


        /* JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(AcceuilPage.class.getResource("")));
        lblNewLabel.setBounds(-39, 0, 192, 69);
        panel.add(lblNewLabel); */
        JLabel titre = new JLabel("Gestion Patient ");
        titre.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 24));
        titre.setBounds(360, 10, 200, 30);
        panel.add(titre);

        recherche = new JTextField();
        recherche.setBounds(15, 94, 180, 40);
        recherche.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 16));
        panel.add(recherche);
        recherche.setColumns(10);

        JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(20, 226, 814, 200);
        panel.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        modifier = new JButton("Modifier");
        modifier.setBounds(535, 94, 100, 40);
        panel.add(modifier);

        inference = new JButton("Inférence");
        inference.setBounds(763, 94, 100, 40);
        panel.add(inference);

        ajouter = new JButton("Ajouter");
        ajouter.setBounds(421, 94, 100, 40);
        panel.add(ajouter);

        supprimer = new JButton("Supprimer");
        supprimer.setBounds(648, 94, 100, 40);
        panel.add(supprimer);

        this.setVisible(true);
        actualiser = new JButton("Actualiser");
        actualiser.setBounds(311, 94, 100, 40);
        panel.add(actualiser);

        chercher = new JButton("Chercher");
        chercher.setBounds(201, 94, 100, 40);
        panel.add(chercher);

        panel.add(bgc);
        this.setVisible(true);
    }

    public JButton getActualiser() {
        return actualiser;
    }

    public void setActualiser(JButton actualiser) {
        this.actualiser = actualiser;
    }


    public JFrame getFrame() {
        return frame;
    }

    public JButton getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(JButton search) {
        this.supprimer = search;
    }


    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField getRecherche() {
        return recherche;
    }

    public void setRecherche(JTextField recherche) {
        this.recherche = recherche;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JButton getAjouter() {
        return ajouter;
    }

    public void setAjouter(JButton ajouter) {
        this.ajouter = ajouter;
    }

    public JButton getModifier() {
        return modifier;
    }

    public void setModifier(JButton modifier) {
        this.modifier = modifier;
    }

    public JButton getInference() {
        return inference;
    }

    public void setInference(JButton inference) {
        this.inference = inference;
    }

    public JButton getChercher() {
        return chercher;
    }

    public void setChercher1(JButton chercher) {
        this.chercher = chercher;
    }

}

