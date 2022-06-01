package view;
import javax.swing.*;
import java.awt.*;

public class AcceuilPage extends JFrame{
    private JFrame frame;
    private JTextField recherche;
    private JTable table;

    public JButton ajouter,modifier,inference,search,afficher,chercher1;

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

        String [] ent = {"Produit","Prix"};
        Object [][] data  = {{"PCqs1","30"},{"PCas","30"},{"PQSQC","30"},{"ADPQSQC","30"},{"SDPQSQC","30"},{"QSDPQSQC","30"},{"VCPQSQC","30"}};
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(37, 226, 814, 200);
        panel.add(scrollPane);

        table = new JTable();

        scrollPane.setViewportView(table);
        //	DefaultTableModel model = new DefaultTableModel();
        //   model.addColumn("Test");
        //  Object [] a = {"a","b","c","d","e","f","g","e","h","i","g","cs","abc"};
        //  model.addRow(new Object[]{"a","b","c","d","e","f","g","e","h","i","g","cs","abc"});
        // table.setModel(model);

        modifier = new JButton("Modifier");
        modifier.setBounds(535, 94, 100, 40);
        panel.add(modifier);

        inference = new JButton("Inférence");
        inference.setBounds(763, 94, 100, 40);
        panel.add(inference);

        ajouter = new JButton("Ajouter");
        ajouter.setBounds(421, 94, 100, 40);
        panel.add(ajouter);

        search = new JButton("Supprimer");
        search.setBounds(648, 94, 100, 40);
        panel.add(search);

        this.setVisible(true);
        afficher = new JButton("Afficher");
        afficher.setBounds(311, 94, 100, 40);
        panel.add(afficher);

        chercher1 = new JButton("Chercher");
        chercher1.setBounds(201, 94, 100, 40);
        panel.add(chercher1);

        panel.add(bgc);
        this.setVisible(true);
    }

    public JButton getAfficher() {
        return afficher;
    }

    public void setAfficher(JButton afficher) {
        this.afficher = afficher;
    }


    public JFrame getFrame() {
        return frame;
    }

    public JButton getSearch() {
        return search;
    }

    public void setSearch(JButton search) {
        this.search = search;
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

    public JButton getChercher1() {
        return chercher1;
    }

    public void setChercher1(JButton chercher1) {
        this.chercher1 = chercher1;
    }

}

