/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secretaire;

import Connexion.ExempleJdbc;
import Medecin.DateChecker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

/**
 * Fenêtre de saisie d'un rdv
 * @author Elodie
 */
public class CreationRDV extends javax.swing.JFrame {
    /**
     * le médecin 
     */
    private String medecin;
    /**
     * la fenêtre précédente
     */
    private JFrame precedent;
    /**
     * liste des patients dans le service
     */
    private ArrayList liste;
    /**
     * identifiant du patient
     */
    private String IPP;
    /**
     * localisation
     * @see Localisation
     */
    private Localisation l;
    /**
     * le service
     */
    private String service;
    /**
     * le type de rdv (hospitalisation ou consultation)
     */
    private String type2;
    /**
     * un compteur qui vaut 0 quand on a pas choisi d'heure du rdv et 1 sinon
     */
    private int compteur;
    /**
     * connexion à la base de données
     */
    private Statement connexion;

    /**
     * Constructeur CreationRDV
     * Creates new form CreationRDV
     * initialise les attributs et les éléments de la fenêtre
     * @param precedent la fenêtre précédente
     * @param nom le nom du médecin
     * @param prenom prénom du médecin
     * @param liste liste des patients du service
     * @param service service
     */
    public CreationRDV(JFrame precedent, String nom, String prenom, ArrayList liste, String service) {
        initComponents();
        affichage(nom, prenom);
        try{
            this.connexion=new ExempleJdbc().connexion();
         } catch (SQLException e) {
                System.out.println(e);
         }
        this.precedent = precedent;
        this.liste = liste;
        jCheckBox1.setVisible(false);
        jCheckBox2.setVisible(false);
        jButton3.setVisible(false);
        jComboBox3.setVisible(false);
        jComboBox5.setVisible(false);
        jComboBox6.setVisible(false);
        jLabel11.setVisible(false);
        jLabel15.setVisible(false);
        jLabel16.setVisible(false);
        jLabel18.setVisible(false);
        jLabel19.setVisible(false);
        jLabel17.setVisible(false);
        jLabel20.setVisible(false);
        medecin = prenom + " " + nom;
        this.service = service;
        this.setLocationRelativeTo(null);
        jLabel14.setVisible(false);
        jLabel12.setVisible(false);
        afficherPatient();
        compteur = 0;

    }

    /**
     * affiche le nom du médecin suivi de son prénom
     * @param nom nom du médecin
     * @param prenom prénom du médecin
     */
    private void affichage(String nom, String prenom) {
        jLabel8.setText(nom + " " + prenom);
    }

    /**
     * on enregistre le rdv dans la base de données
     * si c'est une hospitalisation, on vérifie si le patient est déjà dans une chambre
     * On modifie le statut de la localisation dans la base de données
     */
    private void enregistrer() {
        // On récupère les info saisies
        String patient = jComboBox2.getSelectedItem().toString();
        String motif = jTextField2.getText();
        String catégorie = jComboBox1.getSelectedItem().toString();
        String date = jTextField5.getText();

        // On enregistre le nouveau patient dans BDD
        try {

            connexion.executeUpdate("INSERT INTO RendezVous(idPatient, patient, Médecin, Motif, Date, Catégorie, Localisation,Heure, idRdv)"
                    + " VALUES ('" + IPP + "', '" + patient + "', '" + medecin + "', '" + motif + "', '" + date + "', '" + catégorie + "', '" + l.getIdLocalisation() + "', '" + jComboBox3.getSelectedItem() + "', '" + generationIdRdv() + "')");
        } catch (SQLException e) {
            System.out.println(e);
        }

        // On vérifie si le patient est déjà dans une autre chambre 
        ArrayList listPatient = new ArrayList();
        try {

            ResultSet rs = connexion.executeQuery("SELECT idPatient FROM Localisation");
            while (rs.next()) {
                int p = rs.getInt("idPatient");
                listPatient.add(String.valueOf(p));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        if (listPatient.contains(IPP)) {
            try {
                connexion.executeUpdate("UPDATE  Localisation set idPatient = '0', statut = 'Non occupée' WHERE idPatient ='" + IPP + "'");

            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        // On modifie la statut de la localisation à Occupée si c'est une hospitalisation 
        if (type2.equals("Hospitalisation")) {
            try {
                connexion.executeUpdate("UPDATE  Localisation set idPatient ='" + IPP + "', statut = 'Occupée' WHERE idLocalisation ='" + l.getIdLocalisation() + "'");

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * on récupère les noms et prénoms de tous les patients et on les affiche dans un comboBox
     */
    private void afficherPatient() {
        // On récupère les noms et prénoms de tous les patients dans le service
        int taille = liste.size();
        for (int i = 0; i < taille; i++) {
            jComboBox2.addItem(liste.get(i));
        }
    }

    /**
     * associe un numéro au nom du service
     * @return le numéro
     */
    private String associerServiceNum() {
        String numero = new String();
        if (service.equals("Oncologie")) {
            numero = "1";
        }
        if (service.equals("Gynécologie")) {
            numero = "2";
        }
        return numero;
    }

    /**
     * associe un lit à la chambre
     */
    private void associerChambreLit() {
        String type = jComboBox5.getSelectedItem().toString();
        String numService = associerServiceNum();
        if (type.equals("Simple")) {
            jCheckBox1.setVisible(false);
            jCheckBox2.setVisible(false);
            for (int i = 0; i < 5; i++) {
                jComboBox6.addItem(numService + "0" + i);
            }
            // On veut enlever les lits simples étant déjà pris
            ArrayList listLit = new ArrayList();
            try {

                ResultSet rs = connexion.executeQuery("SELECT lit FROM Localisation WHERE statut = 'Occupée' AND chambre < 5");
                while (rs.next()) {
                    String lit = rs.getString("lit");
                    listLit.add(lit);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
            for (Object l : listLit) {
                jComboBox6.removeItem(l);
            }

        }
        if (type.equals("Double")) {
            jCheckBox1.setVisible(true);
            jCheckBox2.setVisible(true);
            for (int i = 5; i < 10; i++) {
                jComboBox6.addItem(numService + "0" + i);
            }
            // On veut enlever les lits doubles étant déjà pris
            ArrayList listLitD = new ArrayList();
            try {
                ResultSet rs = connexion.executeQuery("SELECT lit FROM Localisation WHERE statut = 'Occupée' AND chambre > 4 GROUP BY chambre HAVING COUNT(chambre) = 2");
                while (rs.next()) {
                    String lit = rs.getString("lit");
                    listLitD.add(lit);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
            for (Object l : listLitD) {
                String lit = l.toString();
                jComboBox6.removeItem(lit.substring(0,3));
            }
        }
    }

    /**
     * 
     * @return le côté de la chambre ou se trouve le lit
     */
    private String coteLit() { // De quel cote de la chambre se trouve le lit ?
        String numero = "";
        if (jCheckBox1.isSelected()) {
            numero = "P"; //il est à la porte
        } else if (jCheckBox2.isSelected()) {
            numero = "F"; // il est à la fenêtre
        }
        return numero;
    }

    /**
     * affiche dans le combo box la liste des horaires disponibles
     */
    private void HoraireRDV() {
        String date = jTextField5.getText();
        // On ajoute les horaires ArrayList couleurs = new ArrayList();
        jComboBox3.addItem("9h");
        jComboBox3.addItem("10h");
        jComboBox3.addItem("11h");
        jComboBox3.addItem("12h");
        jComboBox3.addItem("13h");
        jComboBox3.addItem("14h");
        jComboBox3.addItem("15h");
        jComboBox3.addItem("16h");
        jComboBox3.addItem("17h");
        // On récupère les dates des rdv déjà existants
        ArrayList listeDate = new ArrayList();

        try {
            ResultSet rs = connexion.executeQuery("SELECT date FROM RendezVous");
            while (rs.next()) {
                String d = rs.getString("date");
                listeDate.add(d);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        // On regarde si il y a déjà un rdv à la date renseigné
        if (listeDate.contains(date)) {
            // on regarde quels horaires sont déjà pris à cette date là
            try {
                ResultSet rs = connexion.executeQuery("SELECT heure FROM RendezVous WHERE date ='" + date + "' AND idPatient ='" + IPP + "'");
                while (rs.next()) {
                    String h = rs.getString("heure");
                    jComboBox3.removeItem(h);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
            try {
                ResultSet rs = connexion.executeQuery("SELECT heure FROM RendezVous WHERE date ='" + date + "' AND Médecin ='" + medecin + "'");
                while (rs.next()) {
                    String h = rs.getString("heure");
                    jComboBox3.removeItem(h);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * vérifie que l'on a bien sélectionné un côté pour la chambre
     */
    private void verificationChambreDouble() {
        if (jCheckBox1.isSelected() == false && jCheckBox2.isSelected() == false) {
            MessageErreur me = new MessageErreur("Le côté de la chambre n'a pas été saisi ");
            me.setVisible(true);
        }
    }

    /**
     * génère un identifiant pour le rdv
     * @return l'identifiant
     */
    private String generationIdRdv() {
        int max = 9999;
        int min = 0;
        ArrayList listID = new ArrayList<>();
        String ID = new String();
        Random rand = new Random();
        int nombreAleatoire = rand.nextInt(max - min + 1) + min;
        ID = String.valueOf(nombreAleatoire);

        // On récupère les id de rdv déjà existants
        try {

            ResultSet rs = connexion.executeQuery("SELECT idRdv FROM RendezVous");
            while (rs.next()) {
                int idPatient = rs.getInt("id");
                listID.add(idPatient);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        // on vérifie l'unicité de l'id
        if (listID.contains(ID)) {
            while (listID.contains(ID)) {
                ID.replace(ID, String.valueOf(nombreAleatoire));
            }
        }
        return ID;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Valider");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("Nom ");

        jLabel4.setText("Prénom");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/AtlanTISpng.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 783, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(32, 32, 32))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Nouveau rendez-vous");

        jLabel3.setText("Patient :");

        jLabel7.setText("Médecin :");

        jLabel8.setText("jLabel8");

        jLabel9.setText("Motif :");

        jLabel10.setText("Catégorie :");

        jLabel11.setText("Localisation :");

        jComboBox1.setMaximumRowCount(3);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----Choisir-----", "Hospitalisation", "Consultation", "" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Dr.");

        jLabel12.setText("IPP :");

        jLabel13.setText("Date :");

        jTextField5.setText("YYYY-MM-DD");

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel14.setText("jLabel14");

        jLabel15.setText("Chambre :");

        jComboBox5.setMaximumRowCount(3);
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----Choisir-----", "Simple", "Double" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel16.setText("Lit :");

        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("P");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("F");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jButton3.setText("Vérifier");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel18.setText("jLabel18");

        jLabel19.setText("Heure :");

        jButton4.setText("Trouver une heure");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel17.setText("N° chambre :");

        jLabel20.setText("jLabel20");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(179, 179, 179)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton4)
                                        .addGap(71, 71, 71)
                                        .addComponent(jLabel19)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(60, 60, 60)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addComponent(jCheckBox2))
                                            .addComponent(jCheckBox1))
                                        .addGap(56, 56, 56)
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                                        .addComponent(jButton3))))
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jLabel11)
                    .addComponent(jLabel17)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addContainerGap())
        );

        jButton2.setText("retour");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGap(279, 279, 279)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 1, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(45, 45, 45))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addGap(82, 82, 82)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * valide le rdv : affiche un message de validation
     * @param evt 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String date = jTextField5.getText();
        if (jComboBox1.getSelectedItem().toString().equals("-----Choisir-----")) {
            MessageErreur me = new MessageErreur("Le type de rendez-vous n'a pas été choisi ");
            me.setVisible(true);
        } else if (jTextField2.getText().equals("") || jTextField5.getText().equals("")) {
            MessageErreur me = new MessageErreur("Des informations n'ont pas été saisies ");
            me.setVisible(true);
        } else if (compteur == 0) {
            MessageErreur me = new MessageErreur("L'heure de rendez-vous n'a pas été choisie ");
            me.setVisible(true);
        } else if (DateChecker.isValid(date) == false) {
            JFrame erreur = new MessageErreur("- Le format de la date n'est pas valide ");
            erreur.setVisible(true);
        } else {
            enregistrer();
            MessageValidation mv = new MessageValidation(precedent, this,Integer.valueOf(this.IPP),this.l);
            mv.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * on remplit les labels selon le type hospitalisation ou consultation
     * @param evt 
     */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String catégorie = jComboBox1.getSelectedItem().toString();
        if (catégorie.equals("Hospitalisation")) {
            jButton3.setVisible(true);
            jComboBox5.setVisible(true);
            jComboBox6.setVisible(true);

            jLabel11.setVisible(true);
            jLabel15.setVisible(true);
            jLabel16.setVisible(true);

            type2 = "Hospitalisation";
        } else {
            jCheckBox1.setVisible(false);
            jCheckBox2.setVisible(false);
            jButton3.setVisible(false);
            jComboBox5.setVisible(false);
            jComboBox6.setVisible(false);

            jLabel11.setVisible(false);
            jLabel15.setVisible(false);
            jLabel16.setVisible(false);

            type2 = "Consultation";
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * on affiche l'IPP du patient sélectionné
     * @param evt 
     */
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // On récupère l'IPP du patient sélectionné
        try {

            ResultSet rs = connexion.executeQuery("SELECT id FROM Patient WHERE CONCAT(prenom, ' ', nomusuel) LIKE '" + jComboBox2.getSelectedItem().toString() + "'");
            while (rs.next()) {
                int i = rs.getInt("id");
                IPP = String.valueOf(i);
                jLabel14.setText(IPP);
                jLabel14.setVisible(true);
                jLabel12.setVisible(true);
            }


        } catch (SQLException e) {
            System.out.println(e);
        }
        

    }//GEN-LAST:event_jComboBox2ActionPerformed

    /**
     * associe un lit à la chambre sélectionnée
     * @param evt 
     */
    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        jComboBox6.removeAllItems();
        associerChambreLit();
    }//GEN-LAST:event_jComboBox5ActionPerformed

    /**
     * impose la condition 'une seule case pour le lit peut être cochée'
     * @param evt 
     */
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            jCheckBox2.setEnabled(false);
        } else {
            jCheckBox2.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    /**
     * impose la condition 'une seule case pour le lit peut être cochée'
     * @param evt 
     */
    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if (jCheckBox2.isSelected()) {
            jCheckBox1.setEnabled(false);
        } else {
            jCheckBox1.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    /**
     * On vérifie la disponibilité de la chambre
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String numLit = jComboBox6.getSelectedItem().toString();
        String chambre = new String();
        if (jComboBox5.getSelectedItem().toString().equals("Simple")) {
            chambre = numLit.substring(2);
        } else {
            chambre = numLit.substring(2, 3);
        }
        jLabel20.setText(chambre);
        jLabel17.setVisible(true);
        jLabel20.setVisible(true);
        
        String lit = jComboBox6.getSelectedItem().toString() + coteLit();
        jLabel18.setVisible(false);
        String lo = new String();
        if (type2.equals("Hospitalisation")) {
            if (jComboBox5.getSelectedItem().toString().equals("Double")) {
                verificationChambreDouble();
            }
            lo = associerServiceNum() + chambre + lit;

        } else {
            lo = "";
        }
        l = new Localisation(jComboBox2.getSelectedItem().toString(), chambre, lit, service, lo);

        // vérification disponiblilité
        if (l.dejaOccupee()) {
            MessageErreur me = new MessageErreur("Un patient se trouve déjà à cette localiation " + l.getIdLocalisation());
            me.setVisible(true);
        } else {
            jLabel18.setText("Cette localisation est disponible : " + l.getIdLocalisation());
            jLabel18.setVisible(true);
        }
        
        

    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * permet de retourner à la page précédente quand on clique sur le bouton
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        precedent.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * permet de trouver une heure pour le rdv
     * @param evt 
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jComboBox3.removeAllItems();
        jComboBox3.setVisible(true);
        jLabel19.setVisible(true);
        HoraireRDV();
        compteur = 1;
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        
    }//GEN-LAST:event_jComboBox6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreationRDV.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreationRDV.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreationRDV.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreationRDV.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new CreationRDV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
