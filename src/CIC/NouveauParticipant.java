/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIC;

import Connexion.ExempleJdbc;
import PageConnexion.InterfaceConnexion;
import Patient.PatientHop;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Fenêtre de création d'un nouveau participant
 * @author clara
 */
public class NouveauParticipant extends javax.swing.JFrame {

    /**
     * PH qui est connecté
     */
    private Cic cic;
    /**
     * liste des participants que l'on veut ajouter
     */
    private ArrayList<Participant> listeParticipants = new ArrayList<Participant>();
    /**
     * liste des participants qui participent déjà à l'étude que l'on est en train de créer
     */
    private ArrayList<Participant> ancienneListe = new ArrayList<Participant>();
    /**
     * nom de l'étude que l'on est en train de créer
     */
    private String nom;
    /**
     * date de démarrage de l'étude
     */
    private String date;
    /**
     * durée de l'étude
     */
    private int duree;
    /**
     * liste des patients de l'hôpital qui ne participent pas encore à une étude clinique
     */
    ArrayList<PatientHop> listePatients = new ArrayList<>();
    /**
     * connexion à la base de données
     */
    private Statement s;

    /**
     * Constructeur NouveauParticipant
     * Creates new form NouveauParticipant
     * initialise les attributs et les éléments de la fenêtre
     * @param ancienneListe liste des participants qui participent déjà à l'étude
     * @param listeParticipants liste des participants que l'on veut ajouter
     * @param nom nom de l'étude
     * @param date date de démarrage de l'étude
     * @param duree durée de l'étude
     * @param cic PH connecté
     * @param s connexion à la BD
     * @throws ParseException gestion des exceptions
     */
    public NouveauParticipant(ArrayList<Participant> ancienneListe, ArrayList<Participant> listeParticipants, String nom, String date, int duree, Cic cic,Statement s) throws ParseException {
        this.s = s;
        this.cic = cic;
        this.listeParticipants = listeParticipants;
        this.ancienneListe = ancienneListe;
        this.nom = nom;
        this.date = date;
        this.duree = duree;
        initComponents();
        remplirPatients();
        type.add(patient);
        type.add(vol);
        sexe.add(H);
        sexe.add(F);
        hopital.setVisible(false);
        princ.setVisible(false);
        patients.setVisible(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * remplit le combo box avec la liste des patients qui ne participent pas encore à une étude clinique
     * @throws ParseException gestion des exceptions
     */
    public void remplirPatients() throws ParseException {
        try {
            Statement s = ExempleJdbc.connexion();
            try {
                ResultSet rs = s.executeQuery("SELECT distinct nomusuel, nomdenaissance, prenom, datedenaissance, id FROM Patient");
                while (rs.next()) {
                    PatientHop p = new PatientHop(rs.getString("nomusuel"), rs.getString("nomdenaissance"), rs.getString("prenom"), rs.getString("datedenaissance"), rs.getInt("id"));
                    listePatients.add(p);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        for (PatientHop p : listePatients) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String da = p.getNaissance();
            java.util.Date d = dateFormat.parse(da);
            Date dateN = new Date(d.getTime());
            
            Participant part = new Participant(p.getNomUsuel(),p.getPrenom(),dateN,"patient");
            
            int i = 0;
            while(i < listeParticipants.size() && !listeParticipants.get(i).egal(part)){
                i++;
            }
            int j = 0;
            while(j < ancienneListe.size() && !ancienneListe.get(j).egal(part)){
                j++;
            }
            if(i==listeParticipants.size() && j==ancienneListe.size()){
                patients.addItem(p.toString2());
            }
        }
    }

    /**
     * Vérifie si une date en String est valide
     * @param strdate la date en String
     * @return true si la date est valide, false sinon
     */
    public boolean dateValide(String strdate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            java.util.Date date = df.parse(strdate);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        type = new javax.swing.ButtonGroup();
        sexe = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        deconnexion = new javax.swing.JButton();
        utilisateur = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        patient = new javax.swing.JRadioButton();
        vol = new javax.swing.JRadioButton();
        hopital = new javax.swing.JLabel();
        patients = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        nomU = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nomN = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        prenom = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        H = new javax.swing.JRadioButton();
        F = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        naissance = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        taille = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        poids = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        patho = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        allergie = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        regime = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        sport = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        fumeur = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        categorie = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        ville = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        princ = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        deconnexion.setText("Déconnexion");
        deconnexion.setToolTipText("");
        deconnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deconnexionActionPerformed(evt);
            }
        });

        utilisateur.setText(cic.getPrenom()+" "+cic.getNom());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/AtlanTISpng.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 626, Short.MAX_VALUE)
                .addComponent(utilisateur)
                .addGap(26, 26, 26)
                .addComponent(deconnexion)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(utilisateur)
                    .addComponent(deconnexion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        patient.setText("Patient");
        patient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientActionPerformed(evt);
            }
        });

        vol.setText("Volontaire sain");

        hopital.setText("Hôpital :");

        patients.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "patient..." }));
        patients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientsActionPerformed(evt);
            }
        });

        jLabel3.setText("Nom usuel :");

        jLabel4.setText("Nom de naissance :");

        jLabel5.setText("Prénom :");

        jLabel6.setText("Sexe :");

        H.setText("H");

        F.setText("F");

        jLabel7.setText("Date de naissance :");

        naissance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                naissanceActionPerformed(evt);
            }
        });

        jLabel8.setText("Taille (cm) :");

        jLabel9.setText("Poids (kg) :");

        jLabel10.setText("Pathologie :");

        jLabel11.setText("Allergie :");

        allergie.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "alimentaire", "médicament", "autre", "aucune" }));

        jLabel12.setText("Régime alimentaire :");

        regime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "végétarien", "sans gluten", "sans sel", "autre", "normal" }));

        jLabel13.setText("Sport :");

        sport.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "jamais", "rarement", "régulièrement", "sportif de haut niveau" }));

        jLabel14.setText("Fumeur :");

        fumeur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "oui", "non" }));

        jLabel15.setText("Catégorie socio-professionnelle :");

        categorie.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "agriculteurs exploitants", "artisans, commerçants, chefs d entreprise", "cadres et professions intellectuelles supérieures", "professions intermédiaires", "employés", "ouvriers", "retraités", "étudiants", "autres sans activité professionnelle" }));

        jLabel16.setText("Ville :");

        jButton1.setText("Valider");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Annuler");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        princ.setText("Princeton Plainsboro");
        princ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                princActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(H)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(F))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(patho, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(allergie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(regime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fumeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(patient)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vol)
                                .addGap(18, 18, 18)
                                .addComponent(hopital)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(princ)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(patients, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(poids))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(taille, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ville))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(naissance, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nomU, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nomN, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patient)
                    .addComponent(vol)
                    .addComponent(hopital)
                    .addComponent(patients, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(princ))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nomU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(nomN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(prenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(H)
                    .addComponent(F))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(naissance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(ville, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(taille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(poids, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(patho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(allergie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(regime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(sport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(fumeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(categorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * permet de se déconnecter
     * ferme la fenêtre actuelle et renvoie sur la page de connexion
     * @param evt 
     * @see InterfaceConnexion
     */
    private void deconnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deconnexionActionPerformed
        this.setVisible(false);
        InterfaceConnexion i = new InterfaceConnexion();
    }//GEN-LAST:event_deconnexionActionPerformed

    /**
     * si on sélectionne patient, on affiche la case Princeton Plainsboro
     * @param evt 
     */
    private void patientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientActionPerformed
        if (patient.isSelected()) {
            hopital.setVisible(true);
            princ.setVisible(true);
        } else {
            hopital.setVisible(false);
            princ.setVisible(false);
        }
    }//GEN-LAST:event_patientActionPerformed

    /**
     * Quand on sélectionne un patient, on pré-remplit les informations du patient
     * @param evt 
     */
    private void patientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientsActionPerformed
        int i = 0;
        String s = listePatients.get(i).toString() + " (" + listePatients.get(i).getNomNaissance() + ")";
        while (i<listePatients.size() && !s.equals(patients.getSelectedItem())) {
            i++;
            s = listePatients.get(i).toString() + " (" + listePatients.get(i).getNomNaissance() + ")";
        }          
            if (i<listePatients.size()) {
                PatientHop p = listePatients.get(i);
                nomU.setText(p.getNomUsuel());
                nomN.setText(p.getNomNaissance());
                prenom.setText(p.getPrenom());
                naissance.setText(p.getNaissance());
                ville.setText(p.getVille());
                if (p.getSexe().equals("F")) {
                    F.setSelected(true);
                } else {
                    H.setSelected(true);
                }
            }
        
        
    }//GEN-LAST:event_patientsActionPerformed

    /**
     * on vérifie que toutes les informations sont saisies et valides
     * On valide la création du participant et on l'enregistre dans la base de données
     * @param evt 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (type.getSelection() == null || sexe.getSelection() == null || nomU.getText().equals("") || nomN.getText().equals("") || prenom.getText().equals("") || naissance.getText().equals("") || ville.getText().equals("") || (int) taille.getValue() == 0 || (int) poids.getValue() == 0 || allergie.getSelectedItem().equals(" ") || regime.getSelectedItem().equals(" ") || sport.getSelectedItem().equals(" ") || fumeur.getSelectedItem().equals(" ") || categorie.getSelectedItem().equals(" ")) {
            System.out.println(fumeur.getSelectedItem());
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!dateValide(naissance.getText())) {
                JOptionPane.showMessageDialog(null, "La date n'est pas valide. Veuillez saisir une date sous la forme yyyy-MM-dd", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {

                String t;
                if (patient.isSelected()) {
                    t = "patient";
                } else {
                    t = "volontaire sain";
                }
                String sx;
                if (H.isSelected()) {
                    sx = "H";
                } else {
                    sx = "F";
                }
                Statement s;
                try {
                    s = ExempleJdbc.connexion();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String da = naissance.getText();
                    java.util.Date d = dateFormat.parse(da);
                    Date dateN = new Date(d.getTime());
                    s.executeUpdate("INSERT INTO `Participant`(`nomUsuel`, `nomDeNaissance`, `dateDeNaissance`, `prenom`, `type`, `sexe`, `taille`, `poids`, `pathologie`, `allergie`, `regime`, `sport`, `fumeur`, `categorie`, `ville`, `etude`) VALUES ('" + nomU.getText() + "','" + nomN.getText() + "','" + dateN + "','" + prenom.getText() + "','" + t + "','" + sx + "','" + (int) taille.getValue() + "','" + (int) poids.getValue() + "','" + patho.getText() + "','" + allergie.getSelectedItem() + "','" + regime.getSelectedItem() + "','" + sport.getSelectedItem() + "','" + fumeur.getSelectedItem() + "','" + categorie.getSelectedItem() + "','" + ville.getText() + "','')");

                    Participant p = new Participant(nomU.getText(), prenom.getText(), dateN, t);
                    ArrayList<Participant> l = new ArrayList<>();
                    l.add(p);
                    JOptionPane.showMessageDialog(null, "Le participant " + p.getPrenom() + " " + p.getNomU() + " a bien été créé.", "Message", JOptionPane.WARNING_MESSAGE);
                    CicAjouterEtude e = new CicAjouterEtude(ancienneListe, l, nom, date, duree, cic,this.s);
                    this.setVisible(false);

                } catch (SQLException ex) {
                    Logger.getLogger(NouveauParticipant.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(NouveauParticipant.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * on annule la création du participant et on retourne à la page précédente
     * @param evt 
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            CicAjouterParticipant a = new CicAjouterParticipant(ancienneListe, listeParticipants, nom, date, duree, cic,s);
            this.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(NouveauParticipant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * quand on sélectionne Princeton Plainsboro, on affiche le combobox avec la liste des patients
     * @param evt 
     */
    private void princActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_princActionPerformed
        if (princ.isSelected()) {
            patients.setVisible(true);
        } else {
            patients.setVisible(false);
        }
    }//GEN-LAST:event_princActionPerformed

    private void naissanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naissanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_naissanceActionPerformed

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
            java.util.logging.Logger.getLogger(NouveauParticipant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NouveauParticipant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NouveauParticipant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NouveauParticipant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton F;
    private javax.swing.JRadioButton H;
    private javax.swing.JComboBox allergie;
    private javax.swing.JComboBox categorie;
    private javax.swing.JButton deconnexion;
    private javax.swing.JComboBox fumeur;
    private javax.swing.JLabel hopital;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField naissance;
    private javax.swing.JTextField nomN;
    private javax.swing.JTextField nomU;
    private javax.swing.JTextField patho;
    private javax.swing.JRadioButton patient;
    private javax.swing.JComboBox patients;
    private javax.swing.JSpinner poids;
    private javax.swing.JTextField prenom;
    private javax.swing.JCheckBox princ;
    private javax.swing.JComboBox regime;
    private javax.swing.ButtonGroup sexe;
    private javax.swing.JComboBox sport;
    private javax.swing.JSpinner taille;
    private javax.swing.ButtonGroup type;
    private javax.swing.JLabel utilisateur;
    private javax.swing.JTextField ville;
    private javax.swing.JRadioButton vol;
    // End of variables declaration//GEN-END:variables
}
