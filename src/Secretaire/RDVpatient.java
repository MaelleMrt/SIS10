/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secretaire;

import Connexion.ExempleJdbc;
import Medecin.DateChecker;
import Medecin.Medecin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import java.util.Set;
import java.util.Vector;
import javax.swing.JFrame;

/**
 * fenêtre de saisie d'un nouveau rdv pour un patient
 * @author Elodie
 */
public class RDVpatient extends javax.swing.JFrame {

    /**
     * service
     */
    String service;
    /**
     * liste des médecins
     */
    private ArrayList<Medecin> listMedecin = new ArrayList<>();
    /**
     * liste des services
     */
    private ArrayList<String> listService = new ArrayList<>();
    /**
     * liste des patients
     */
    private ArrayList<String> listPatient = new ArrayList<>();
    /**
     * page précédente
     */
    private JFrame precedent;
    /**
     * identifiant du patient
     */
    private String id;
    /**
     * patient
     */
    private String patient;
    /**
     * localisation
     */
    private String localisation;
    /**
     * localisation
     * @see Localisation
     */
    private Localisation l;
    /**
     * type de la chambre
     */
    private String type;
    /**
     * type du rdv
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
     * Constructeur RDVpatient
     * initialise les attributs et les éléments de la fenêtre
     * @param secretaire secrétaire
     * @param patient patient
     * @param id identifiant du patient
     * @param precedent page précédente
     */
    public RDVpatient(String secretaire, String patient, String id, JFrame precedent) {
        initComponents();
        try{
            this.connexion=new ExempleJdbc().connexion();
         } catch (SQLException e) {
                System.out.println(e);
         }
        this.setLocationRelativeTo(null);
        jCheckBox1.setVisible(false);
        jCheckBox2.setVisible(false);
        jButton3.setVisible(false);
        jComboBox5.setVisible(false);
        jComboBox3.setVisible(false);
        jComboBox6.setVisible(false);
        jLabel11.setVisible(false);
        jLabel4.setVisible(false);
        jLabel15.setVisible(false);
        jLabel16.setVisible(false);
        jLabel17.setVisible(false);
        jLabel18.setVisible(false);
        jLabel20.setVisible(false);
        jLabel8.setText(patient);
        jLabel14.setText(id);
        jLabel2.setText(secretaire);
        this.precedent = precedent;
        this.id = id;
        this.patient = patient;
        compteur = 0;
    }

    /**
     * remplit la liste des médecins en fonction du service
     */
    private void remplirMedecins() {
        service = jComboBox2.getSelectedItem().toString();
        try {
            ResultSet rs = connexion.executeQuery("SELECT nom, prenom, login FROM Médecin WHERE nomS ='" + service + "'");
            while (rs.next()) {
                Medecin medecin = new Medecin(rs.getString("nom"), rs.getString("prenom"), service, rs.getString("login"));
                listMedecin.add(medecin);
                jComboBox4.addItem(medecin.getPrenom() + " " + medecin.getNom());

            }

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    /**
     * on regarde si le patient est déjà dans le service concerné
     * on enregistre le patient dans le nouveau service
     * on enregistre le rdv dans la base de données
     * on vérifie si le patient est déjà dans une autre chambre
     * On modifie la statut de la localisation à Occupée si c'est une hospitalisation 
     */
    private void enregistrer() {
        // On regarde si le patient est déjà dans le service concerné
        try {

            ResultSet rs = connexion.executeQuery("SELECT service FROM PatientService WHERE id ='" + id + "'");
            while (rs.next()) {
                String ser = rs.getString("service");
                listService.add(ser);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        if (!listService.contains(service)) {
            // On enregistre le patient dans le nouveau service
            try {

                connexion.executeUpdate("INSERT INTO PatientService(id, service)"
                        + " VALUES ('" + id + "', '" + service + "')");
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        // On enregistre le nouveau rdv dans BDD

        try {

            connexion.executeUpdate("INSERT INTO RendezVous(idPatient, patient, Médecin, Motif, Date, Catégorie, Localisation, Heure, idRdv)"
                    + " VALUES ('" + id + "', '" + patient + "', '" + jComboBox4.getSelectedItem() + "', '" + jTextField2.getText() + "', '" + jTextField5.getText() + "', '" + jComboBox1.getSelectedItem() + "', '" + localisation + "', '" + jComboBox3.getSelectedItem() + "', '" + generationIdRdv() + "')");
        } catch (SQLException e) {
            System.out.println(e);
        }
        // On vérifie si le patient est déjà dans une autre chambre 
        try {

            ResultSet rs = connexion.executeQuery("SELECT idPatient FROM Localisation");
            while (rs.next()) {
                int p = rs.getInt("idPatient");
                listPatient.add(String.valueOf(p));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        if (listPatient.contains(id)) {
            try {

                System.out.println(id);
                connexion.executeUpdate("UPDATE  Localisation set idPatient = '0', statut = 'Non occupée' WHERE idPatient ='" + id + "'");
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        // On modifie la statut de la localisation à Occupée si c'est une hospitalisation 
        if (type2.equals("Hospitalisation")) {
            try {
                System.out.println(id);
                connexion.executeUpdate("UPDATE  Localisation set idPatient ='" + id + "', statut = 'Occupée' WHERE idLocalisation ='" + localisation + "'");
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * on associe un numéro au service
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
     * on associe un lit à la chambre
     */
    private void associerChambreLit() {
        type = jComboBox5.getSelectedItem().toString();
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

                ResultSet rs = connexion.executeQuery("SELECT lit, chambre FROM Localisation WHERE statut = 'Occupée' AND chambre < 5");
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
     * @return de quel côté de la chambre se trouve le lit (porte ou fenêtre)
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

                ResultSet rs = connexion.executeQuery("SELECT heure FROM RendezVous WHERE date ='" + date + "' AND idPatient ='" + id + "'");
                while (rs.next()) {
                    String h = rs.getString("heure");
                    jComboBox3.removeItem(h);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
            try {

                ResultSet rs = connexion.executeQuery("SELECT heure FROM RendezVous WHERE date ='" + date + "' AND Médecin ='" + jComboBox4.getSelectedItem().toString() + "'");
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
     * Vérifie si on a bien choisi un lit si c'est une chambre double
     */
    private void verificationChambreDouble() {
        if (jCheckBox1.isSelected() == false && jCheckBox2.isSelected() == false) {
            MessageErreur me = new MessageErreur("Le côté de la chambre n'a pas été saisi ");
            me.setVisible(true);
        }
    }

    /**
     * Vérification qu'on a bien sélectionné un service
     */
    private void verificationSelectionService() {
        if (jComboBox2.getSelectedItem().toString().equals("-----Choisir-----")) {
            MessageErreur me = new MessageErreur("Le service n'a pas été choisi ");
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("retour");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Valider");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("Nom ");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/AtlanTISpng.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(32, 32, 32))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Nouveau rendez-vous");

        jLabel3.setText("Patient :");

        jLabel7.setText("Médecin :");

        jLabel9.setText("Motif :");

        jLabel10.setText("Catégorie :");

        jLabel11.setText("Localisation :");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jComboBox1.setMaximumRowCount(3);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----Choisir-----", "Hospitalisation", "Consultation" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel12.setText("IPP :");

        jLabel13.setText("Date :");

        jTextField5.setText("YYYY-MM-DD");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel6.setText("Service :");

        jComboBox2.setMaximumRowCount(3);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----Choisir-----", "Radiologie", "Oncologie", "Gynécologie", "Anésthésie" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jLabel8.setText("jLabel8");

        jLabel14.setText("jLabel14");

        jLabel4.setText("Heure :");

        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jButton3.setText("Vérifier");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

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

        jLabel18.setText("jLabel18");

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
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(159, 159, 159)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jButton4)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox2)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20)
                                .addGap(38, 38, 38)
                                .addComponent(jButton3))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel7))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12)
                    .addComponent(jLabel8)
                    .addComponent(jLabel14))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(jLabel20))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jButton3)
                        .addComponent(jLabel15)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox1)
                        .addComponent(jCheckBox2)))
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton2)
                        .addGap(267, 267, 267)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(106, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(72, 72, 72))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            System.out.println("identifiant "+ id);
            MessageValidation mv = new MessageValidation(precedent, this,Integer.valueOf(this.id),this.l);
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
            jLabel11.setVisible(true);
            jButton3.setVisible(true);
            jComboBox5.setVisible(true);
            jComboBox6.setVisible(true);
            jLabel11.setVisible(true);
            jLabel15.setVisible(true);
            jLabel16.setVisible(true);
            type2 = "Hospitalisation";

        } else {
            jLabel11.setVisible(false);
            jButton3.setVisible(false);
            jComboBox5.setVisible(false);
            jComboBox6.setVisible(false);
            jLabel11.setVisible(false);
            jLabel15.setVisible(false);
            jLabel16.setVisible(false);
            jLabel17.setVisible(false);
            jLabel20.setVisible(false);
            localisation = "";
            type2 = "Consultation";

        }
        verificationSelectionService();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * remplit le combo box avec la liste des médecins
     * @param evt 
     */
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        jComboBox4.removeAllItems();
        remplirMedecins();
        associerServiceNum();
    }//GEN-LAST:event_jComboBox2ActionPerformed

    /**
     * permet de retourner à la page précédente quand on clique sur le bouton
     * @param evt 
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        precedent.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
        String lo = associerServiceNum() + chambre + lit;
        l = new Localisation(id, chambre, lit, service, lo);
        localisation = l.getIdLocalisation();
        if (type2.equals("Hospitalisation")) {
            if (jComboBox5.getSelectedItem().toString().equals("Double")){
                verificationChambreDouble();
            }           
        }
        // vérification disponiblilité
        if (l.dejaOccupee()) {
            MessageErreur me = new MessageErreur("Un patient se trouve déjà à cette localiation " + localisation);
            me.setVisible(true);
        } else {
            jLabel18.setText("Cette localisation est disponible : " + localisation);
            jLabel18.setVisible(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed

    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed

    }//GEN-LAST:event_jTextField5ActionPerformed

    /**
     * permet de trouver une heure pour le rdv
     * @param evt 
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jComboBox3.removeAllItems();
        jComboBox3.setVisible(true);
        jLabel4.setVisible(true);
        HoraireRDV();
        compteur = 1;
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(RDVpatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RDVpatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RDVpatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RDVpatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

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
    private javax.swing.JComboBox jComboBox4;
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
