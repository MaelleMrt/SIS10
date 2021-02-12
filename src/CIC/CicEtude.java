/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIC;

import Connexion.ExempleJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author clara
 */
public class CicEtude extends javax.swing.JFrame {

    private Etude e;
    private ArrayList<Participant> listeParticipants= new ArrayList<Participant>();
    
    public CicEtude(Etude e) throws SQLException {
        initComponents();
        this.e = e;
        jLabel13.setText(this.e.getNom());
        ph.setText(e.getPH());
        date.setText(e.getDate());
        duree.setText(String.valueOf(e.getDuree()) + " semaines" );
        this.setVisible(true);
        remplirTableau(); 
   }
    
    public void remplirTableau() throws SQLException{
        try{
        Statement s= ExempleJdbc.connexion();
            try{
                ResultSet rs= s.executeQuery("SELECT nomUsuel, nomDeNaissance, prenom, dateDeNaissance, type FROM Participant JOIN Etude on (nomUsuel = participantNomU AND nomDeNaissance = participantNomN AND prenom = participantPrenom AND dateDeNaissance = participantDate) WHERE Etude.nom = '"+e.getNom()+"'");
                while(rs.next()){
                   Participant participant =new Participant(rs.getString("nomUsuel"), rs.getString("prenom"), rs.getString("dateDeNaissance"), rs.getString("type"));
                   listeParticipants.add(participant);
                }   

            } catch(SQLException e){
                    System.out.println(e);
            }

        } catch (SQLException e){
            System.out.println(e);
        }
        
        DefaultTableModel model = new DefaultTableModel();
        int i = 0;
        for (Participant p : listeParticipants) {
            Vector<Object> v = new Vector<Object>();
            v.add(p.getNomU());
            v.add(p.getPrenom());
            v.add(p.getDateN());
            v.add(p.getType());
            model.setColumnIdentifiers(new String[]{"Nom", "Prénom", "Date de naissance", "Type"});
            model.insertRow(i, v);
            i++;
        }
        participants.setModel (model);
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
        retour = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ph = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        duree = new javax.swing.JLabel();
        rechercherTextField = new javax.swing.JTextField();
        rechercher = new javax.swing.JButton();
        reinitialiser = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        participants = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        deconnexion.setText("Déconnexion");
        deconnexion.setToolTipText("");

        utilisateur.setText("Prénom Nom");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/AtlanTISpng.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(utilisateur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deconnexion)
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(utilisateur)
                    .addComponent(deconnexion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        retour.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        retour.setText("Retour");
        retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retourActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("Praticien Hospitalier porteur :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("Date de démarrage :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("Liste des participants :");

        ph.setText("Nom Prénom");

        date.setText("date");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setText("Durée :");

        duree.setText("durée");

        rechercherTextField.setDisabledTextColor(new java.awt.Color(204, 204, 204));
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

        rechercher.setText("Rechercher");
        rechercher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rechercherMouseClicked(evt);
            }
        });
        rechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercherActionPerformed(evt);
            }
        });

        reinitialiser.setText("Réinitialiser");
        reinitialiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reinitialiserActionPerformed(evt);
            }
        });

        participants.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
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
        participants.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                participantsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(participants);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setText("Nom de l'étude");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(retour)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rechercherTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rechercher)
                        .addGap(3, 3, 3)
                        .addComponent(reinitialiser)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(duree))
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ph))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date)))
                        .addGap(638, 638, 638))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(409, 409, 409)
                    .addComponent(jLabel13)
                    .addContainerGap(412, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(retour)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ph))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(date))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(duree))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rechercherTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(rechercher)
                    .addComponent(reinitialiser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(80, 80, 80)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(408, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void retourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retourActionPerformed
        this.setVisible(false);
        try {
            CicAccueil accueil = new CicAccueil();
        } catch (SQLException ex) {
            Logger.getLogger(CicEtude.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_retourActionPerformed

    private void rechercherTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercherTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rechercherTextFieldActionPerformed

    private void rechercherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rechercherMouseClicked

    }//GEN-LAST:event_rechercherMouseClicked

    private void rechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercherActionPerformed

    }//GEN-LAST:event_rechercherActionPerformed

    private void reinitialiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reinitialiserActionPerformed
        rechercherTextField.setText("");
        // il faudra remettre toutes les valeurs dans le tableau -> à faire quand on aura la BDD
    }//GEN-LAST:event_reinitialiserActionPerformed

    private void participantsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_participantsMouseClicked
        int i = 0;
        while (i < participants.getRowCount() && !participants.isRowSelected(i)) {
            i++;
        }
        if (i < participants.getRowCount()) {

            String nom = String.valueOf(participants.getValueAt(i, 0));
            String prenom = String.valueOf(participants.getValueAt(i, 1));
            String date = String.valueOf(participants.getValueAt(i, 2));
            String type = String.valueOf(participants.getValueAt(i, 3));
            Participant p = new Participant(nom, prenom, date,type);
            this.setVisible(false);
            CicParticipant part = new CicParticipant(e,p);  
        }
    }//GEN-LAST:event_participantsMouseClicked

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
                String texte=rechercherTextField.getText();
                int i=0;
                for (Participant p : listeParticipants) {
                    if(p.getNomU().toUpperCase().contains(texte.toUpperCase())){
                        Vector<Object> v=new Vector<Object>();
                        v.add(p.getNomU());
                        v.add(p.getPrenom());
                        v.add(p.getDateN());
                        v.add(p.getType());
                        model.setColumnIdentifiers(new String[]{"Nom","Prénom","Date de naissance","Type"});
                        model.insertRow(i,v) ;
                        i++;
                    }
                }
                participants.setModel(model);
            }

            
            
        });
    }//GEN-LAST:event_rechercherTextFieldPropertyChange

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
            java.util.logging.Logger.getLogger(CicEtude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CicEtude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CicEtude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CicEtude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Etude e1 = new Etude ("covid-19","house gregory","r",2);
                try {
                    new CicEtude(e1).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(CicEtude.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date;
    private javax.swing.JButton deconnexion;
    private javax.swing.JLabel duree;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable participants;
    private javax.swing.JLabel ph;
    private javax.swing.JButton rechercher;
    private javax.swing.JTextField rechercherTextField;
    private javax.swing.JButton reinitialiser;
    private javax.swing.JButton retour;
    private javax.swing.JLabel utilisateur;
    // End of variables declaration//GEN-END:variables
}
