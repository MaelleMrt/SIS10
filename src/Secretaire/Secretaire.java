/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secretaire;


import Connexion.ExempleJdbc;
import Medecin.Medecin;
import PageConnexion.InterfaceConnexion;
import Patient.PatientHop;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 * Fenêtre d'accueil d'une secrétaire
 * @author Elodie
 */
public class Secretaire extends javax.swing.JFrame {

    /**
     * nom de la secrétaire
     */
    String nom;
    /**
     * prénom de la secrétaire
     */
    String prenom;
    /**
     * service
     */
    String service;
    /**
     * liste des patients du service
     */
    private ArrayList<PatientDansService> listPatient = new ArrayList<>();
    /**
     * liste des médecins
     */
    private ArrayList<Medecin> listMedecin = new ArrayList<>();
    /**
     * liste de patients
     */
    private ArrayList<PatientHop> listPatient2 = new ArrayList<>();
    /**
     * connexion à la base de données
     */
    private Statement connexion;

    /**
     * Constructeur Secretaire
     * initialise les attributs et éléments de la fenêtre
     * @param nom nom de la secrétaire
     * @param prenom prénom de la secrétaire
     * @param service service
     */
    public Secretaire(String nom, String prenom, String service) {
        try{
            this.connexion=new ExempleJdbc().connexion();
         } catch (SQLException e) {
                System.out.println(e);
         }
        this.nom = nom;
        this.prenom = prenom;
        this.service = service;
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        affichageNom();
        Tableau1();
        Tableau2();

    }

    /**
     * affiche le service et les nom et prénom de la secrétaire
     */
    private void affichageNom() { // récupérer nom de la personne
        jLabel4.setText(prenom);
        jLabel2.setText(nom);
        jLabel6.setText(service);
    }

    /**
     * remplit le tableau des médecins
     */
    private void Tableau2() { // affichage des médecins (nom et prénom)
        try {
   
            ResultSet rs = connexion.executeQuery("SELECT nom, prenom, login FROM Médecin WHERE nomS ='" + service + "'");
            while (rs.next()) {
                Medecin medecin = new Medecin(rs.getString("nom"), rs.getString("prenom"), service, rs.getString("login"));
                listMedecin.add(medecin);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        DefaultTableModel model = new DefaultTableModel();
        int i = 0;
        for (Medecin e : listMedecin) {
            Vector<Object> v = new Vector<Object>();
            v.add(e.getNom());
            v.add(e.getPrenom());
            v.add(e.getLogin());
            model.setColumnIdentifiers(new String[]{"Nom du médecin", "Prénom du médecin"});
            model.insertRow(i, v);
            i++;
        }
        jTable2.setModel(model);
    }

    /**
     * remplit le tableau des patients
     */
    private void Tableau1() { // affichage des médecins (nom et prénom)
        try {

            ResultSet rs = connexion.executeQuery("SELECT id FROM PatientService WHERE service ='" + service + "'");
            while (rs.next()) {
                PatientDansService ps = new PatientDansService(rs.getInt("id"), service);
                listPatient.add(ps);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        DefaultTableModel model = new DefaultTableModel();
        int i = 0;
        for (PatientDansService e : listPatient) {
            Vector<Object> v = new Vector<Object>();
            try {

                ResultSet rs = connexion.executeQuery("SELECT nomusuel, prenom, datedenaissance FROM Patient WHERE id ='" + e.getId() + "'");
                while (rs.next()) {
                    PatientHop p = new PatientHop(rs.getString("nomusuel"), rs.getString("prenom"), rs.getString("datedenaissance"));
                    listPatient2.add(p);
                }

            } catch (SQLException excp) {
                System.out.println("ne marche pas");
            }
            v.add(listPatient2.get((i)).getNomUsuel());
            v.add(listPatient2.get((i)).getPrenom());
            v.add(listPatient2.get((i)).getNaissance());
            model.setColumnIdentifiers(new String[]{"Nom", "Prénom", "Date de naissance"});
            model.insertRow(i, v);
            i++;
        }
        jTable1.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(990, 551));

        jPanel1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Créer un nouveau dossier");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("Nom ");

        jButton3.setText("Déconnexion");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Prénom");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/AtlanTISpng.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Service ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("jLabel6");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Liste Patients");

        jTextField1.setText("Recherche ");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextField1PropertyChange(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Liste Médecins");

        jTextField2.setText("Recherche ");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextField2PropertyChange(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nom", "Prénom"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(388, 388, 388)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(0, 334, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                            .addComponent(jTextField2))))
                .addGap(93, 93, 93))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(224, 224, 224))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(71, 71, 71))
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
     * renvoie à la page de saisie d'un nouveau DMA
     * @param evt 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFrame DMA = new CreationDMA(this, nom, prenom, service);
        DMA.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * permet de se déconnecter
     * ferme la fenêtre actuelle et renvoie sur la page de connexion
     * @param evt 
     * @see InterfaceConnexion
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
        JFrame pageConnexion = new InterfaceConnexion();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    /**
     * permet de faire une recherche par le nom du médecin
     * affiche la liste des médecins ayant un nom contenant le texte qu'on a entré dans la barre de recherche
     * @param evt 
     */
    private void jTextField2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextField2PropertyChange
        jTextField2.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                afficherList();
            }

            public void removeUpdate(DocumentEvent e) {
                afficherList();
            }

            public void insertUpdate(DocumentEvent e) {
                afficherList();
            }

            public void afficherList() {
                DefaultTableModel model = new DefaultTableModel();
                String texte = jTextField2.getText();
                int i = 0;
                for (Medecin e : listMedecin) {
                    if (e.getNom().toUpperCase().contains(texte.toUpperCase())) {
                        Vector<Object> v = new Vector<Object>();
                        v.add(e.getNom());
                        v.add(e.getPrenom());
                        v.add(e.getLogin());
                        model.setColumnIdentifiers(new String[]{"Nom du médecin", "Prénom du médecin"});
                        model.insertRow(i, v);
                        i++;
                    }
                }
                jTable2.setModel(model);
            }

        });
    }//GEN-LAST:event_jTextField2PropertyChange

    /**
     * Quand on clique sur une ligne du tableau, affichage de la liste des rdv du médecin correspondant
     * @param evt 
     */
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int i = 0;
        while (i < jTable2.getRowCount() && !jTable2.isRowSelected(i)) {
            i++;
        }
        if (i < jTable2.getRowCount()) {
            String Nom = String.valueOf(jTable2.getValueAt(i, 0));
            String Prenom = String.valueOf(jTable2.getValueAt(i, 1));
            ListeRDV lr = new ListeRDV(this, nom, prenom, Nom, Prenom, listPatient2, service);
            lr.setVisible(true);
            this.setVisible(false);
        }

    }//GEN-LAST:event_jTable2MouseClicked

    /**
     * permet de faire une recherche par le nom du patient
     * affiche la liste des patients ayant un nom contenant le texte qu'on a entré dans la barre de recherche
     * @param evt 
     */
    private void jTextField1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextField1PropertyChange
         jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                afficherList();
            }

            public void removeUpdate(DocumentEvent e) {
                afficherList();
            }

            public void insertUpdate(DocumentEvent e) {
                afficherList();
            }

            public void afficherList() {
                DefaultTableModel model = new DefaultTableModel();
                String texte = jTextField1.getText();
                int i = 0;
                for (PatientHop e : listPatient2) {
                    if (e.getNomUsuel().toUpperCase().contains(texte.toUpperCase())) {
                        Vector<Object> v = new Vector<Object>();
                        v.add(e.getNomUsuel());
                        v.add(e.getPrenom());
                        v.add(e.getNaissance());
                        model.setColumnIdentifiers(new String[]{"Nom", "Prénom", "Date de naissance"});
                        model.insertRow(i, v);
                        i++;
                    }
                }
                jTable1.setModel(model);
            }

        });
    }//GEN-LAST:event_jTextField1PropertyChange

    /**
     * Quand on clique sur une ligne du tableau, affichage du DMA du patient correspondant
     * @param evt 
     */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
         int i = 0;
        while (i < jTable1.getRowCount() && !jTable1.isRowSelected(i)) {
            i++;
        }
        if (i < jTable1.getRowCount()) {
            String Nom = String.valueOf(jTable1.getValueAt(i, 0));
            String Prenom = String.valueOf(jTable1.getValueAt(i, 1));
            String date = String.valueOf(jTable1.getValueAt(i, 2));
            VisualisationDMA dma = new VisualisationDMA(this, prenom + " " + nom, Nom, Prenom, date);
            dma.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(Secretaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Secretaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Secretaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Secretaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //JFrame secretaire = new Secretaire(); 
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
