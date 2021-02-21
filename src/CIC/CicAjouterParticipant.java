/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIC;

import Connexion.ExempleJdbc;
import PageConnexion.InterfaceConnexion;
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
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author clara
 */
public class CicAjouterParticipant extends javax.swing.JFrame {

    private ArrayList<Participant> listeParticipants = new ArrayList<Participant>();
    private ArrayList<Participant> nouveaux = new ArrayList<Participant>();
    private ArrayList<Participant> ancienneListe = new ArrayList<Participant>();
    private String nom;
    private String date;
    private int duree;
    private ArrayList<Participant> supp = new ArrayList<>();
    private String login;
    private Cic cic;

    /**
     * Creates new form CicAjouterParticipant
     */
    public CicAjouterParticipant(ArrayList<Participant> ancienneListe, ArrayList<Participant> listeParticipants, String nom, String date, int duree,String login) throws SQLException {
        
        this.ancienneListe = ancienneListe;
        this.listeParticipants = listeParticipants;
        this.nom = nom;
        this.date = date;
        this.duree = duree;
        this.login = login;
        trouverCic();
        initComponents();
        remplirTableau();
        erreur.setVisible(false);
        this.setVisible(true);
    }

    public void remplirTableau() throws SQLException {

        DefaultTableModel model = new DefaultTableModel();
        int i = 0;
        for (Participant p : listeParticipants) {
            int j = 0;
            while (j < ancienneListe.size() && !p.egal(ancienneListe.get(j))) {
                j++;
            }
            if (j < ancienneListe.size()) {
                supp.add(p);
            }
        }
        for (Participant p1 : supp) {
            listeParticipants.remove(p1);
        }
        for (Participant part : listeParticipants) {
            Vector<Object> v = new Vector<Object>();
            v.add(part.getNomU());
            v.add(part.getPrenom());
            v.add(part.getDateN());
            v.add(part.getType());
            model.setColumnIdentifiers(new String[]{"Nom", "Prénom", "Date de naissance", "Type"});
            model.insertRow(i, v);
            i++;
        }

        participants.setModel(model);
    }
    
    public void trouverCic(){
        try {
            Statement s = ExempleJdbc.connexion();
            try {
                ResultSet rs = s.executeQuery("SELECT nom, prenom FROM CIC WHERE login = '"+this.login+"'");
                while (rs.next()) {
                    this.cic = new Cic(rs.getString("nom"), rs.getString("prenom"), login);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (SQLException e) {
            System.out.println(e);
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

        jPanel1 = new javax.swing.JPanel();
        deconnexion = new javax.swing.JButton();
        utilisateur = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        participants = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        rechercherTextField = new javax.swing.JTextField();
        erreur = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jButton1.setText("Annuler");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        participants.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nom", "Prénom", "Date de naissance", "Patient/Volontaire sain"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(participants);

        jButton2.setText("Recherche par critères");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Ajouter");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Ajouter des participants");

        rechercherTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercherTextFieldActionPerformed(evt);
            }
        });
        rechercherTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                rechercherTextFieldPropertyChange(evt);
            }
        });

        erreur.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        erreur.setForeground(new java.awt.Color(255, 0, 0));
        erreur.setText("Veuillez sélectionner un/des participant(s)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rechercherTextField))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(erreur, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(3, 3, 3)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(rechercherTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(erreur))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rechercherTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercherTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rechercherTextFieldActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        CicRechercherParticipant r = new CicRechercherParticipant(ancienneListe, listeParticipants, nom, date, duree,login);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        CicAjouterEtude a = new CicAjouterEtude(ancienneListe, nouveaux, nom, date, duree,login);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        for (int i = 0; i < participants.getRowCount(); i++) {
            if (participants.isRowSelected(i)) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String da = String.valueOf(participants.getValueAt(i, 2));
                    java.util.Date d = dateFormat.parse(da);
                    Date date = new Date(d.getTime());
                    Participant p = new Participant(String.valueOf(participants.getValueAt(i, 0)), String.valueOf(participants.getValueAt(i, 1)), date, String.valueOf(participants.getValueAt(i, 3)));
                    nouveaux.add(p);
                } catch (ParseException ex) {
                    Logger.getLogger(CicAjouterParticipant.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (nouveaux.isEmpty()) {
            erreur.setVisible(true);
        } else {
            erreur.setVisible(false);
            String s = "Le(s) participant(s) ";
            for (Participant p : nouveaux) {
                s += p.getPrenom() + " " + p.getNomU() + ", ";
            }
            JOptionPane.showMessageDialog(null, s + "a/ont bien été ajouté(s)", "Message", JOptionPane.WARNING_MESSAGE);
           
            this.setVisible(false);
            CicAjouterEtude a = new CicAjouterEtude(ancienneListe, nouveaux, nom, date, duree,login);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void rechercherTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_rechercherTextFieldPropertyChange
        rechercherTextField.getDocument().addDocumentListener(new DocumentListener() {
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
                String texte = rechercherTextField.getText();
                int i = 0;
                for (Participant p : listeParticipants) {
                    if (p.getNomU().toUpperCase().contains(texte.toUpperCase())) {
                        Vector<Object> v = new Vector<Object>();
                        v.add(p.getNomU());
                        v.add(p.getPrenom());
                        v.add(p.getDateN());
                        v.add(p.getType());
                        model.setColumnIdentifiers(new String[]{"Nom de l'étude", "Praticien hospitalier porteur", "Date de démarrage", "Durée (semaines)"});
                        model.insertRow(i, v);
                        i++;
                    }
                }
                participants.setModel(model);
            }

        });
    }//GEN-LAST:event_rechercherTextFieldPropertyChange

    private void deconnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deconnexionActionPerformed
        this.setVisible(false);
        InterfaceConnexion i = new InterfaceConnexion();
    }//GEN-LAST:event_deconnexionActionPerformed

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
            java.util.logging.Logger.getLogger(CicAjouterParticipant.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CicAjouterParticipant.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CicAjouterParticipant.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CicAjouterParticipant.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new CicAjouterParticipant().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deconnexion;
    private javax.swing.JLabel erreur;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable participants;
    private javax.swing.JTextField rechercherTextField;
    private javax.swing.JLabel utilisateur;
    // End of variables declaration//GEN-END:variables
}
