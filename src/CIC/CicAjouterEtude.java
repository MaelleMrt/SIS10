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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author clara
 */
public class CicAjouterEtude extends javax.swing.JFrame {

    private ArrayList<Participant> listeParticipants = new ArrayList<Participant>();
    private ArrayList<Participant> ancienneListe = new ArrayList<Participant>();
    private ArrayList<Participant> nouveaux = new ArrayList<Participant>();
    private String nomEtude;
    private String dateDemarrage;
    private int dureeEtude;
    private Cic cic;
    private String login;

    public CicAjouterEtude(ArrayList<Participant> ancienneListe, ArrayList<Participant> nouveaux, String nom, String date, int duree, String login) {

        this.ancienneListe = ancienneListe;
        this.nouveaux = nouveaux;
        for (Participant p : this.ancienneListe) {
            listeParticipants.add(p);
        }
        for (Participant p : this.nouveaux) {
            listeParticipants.add(p);
        }
        this.nomEtude = nom;
        this.dateDemarrage = date;
        this.dureeEtude = duree;
        this.login = login;
        trouverCic();
        initComponents();
//        this.nom.setText(nomEtude);
//        this.date.setText(dateDemarrage);
//        this.duree.setValue(dureeEtude);
        remplirTableau();
        erreur.setVisible(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void remplirTableau() {
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
        jTable1.setModel(model);
    }

    public void trouverCic() {
        try {
            Statement s = ExempleJdbc.connexion();
            try {
                ResultSet rs = s.executeQuery("SELECT nom, prenom FROM CIC WHERE login = '" + this.login + "'");
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
    
    public boolean verifDate(String s){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = format.parse(s);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
    
    public boolean verifNom(String str){
        Statement s;
            try {
                s = ExempleJdbc.connexion();
                ResultSet r = s.executeQuery("SELECT distinct nom FROM Etude");
                while (r.next()) {
                    if (str.toUpperCase().equals(r.getString("nom").toUpperCase())) {
                        return true;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(CicAjouterEtude.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            return false;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        ajouter = new javax.swing.JButton();
        supprimer = new javax.swing.JButton();
        annuler = new javax.swing.JButton();
        valider = new javax.swing.JButton();
        nom = new javax.swing.JTextField();
        date = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        duree = new javax.swing.JSpinner();
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

        utilisateur.setText(cic.getPrenom() + " " + cic.getNom());

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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(utilisateur)
                    .addComponent(deconnexion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Ajouter une étude clinique");

        jLabel3.setText("Nom de l'étude :");

        jLabel4.setText("Date de démarrage : ");

        jLabel5.setText("Durée :");

        jLabel6.setText("Liste de participants :");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        ajouter.setText("Ajouter un participant");
        ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterActionPerformed(evt);
            }
        });

        supprimer.setText("Supprimer");
        supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerActionPerformed(evt);
            }
        });

        annuler.setText("Annuler");
        annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerActionPerformed(evt);
            }
        });

        valider.setText("Valider");
        valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerActionPerformed(evt);
            }
        });

        nom.setText(nomEtude);

        date.setText(dateDemarrage);
        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });

        jLabel7.setText("semaines");

        duree.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(dureeEtude), null, null, Integer.valueOf(1)));

        erreur.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        erreur.setForeground(new java.awt.Color(255, 0, 51));
        erreur.setText("Erreur");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(jLabel2)
                .addGap(340, 340, 340))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ajouter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(supprimer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(erreur, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(valider)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(annuler))
                            .addComponent(jScrollPane1))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(duree, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nom)))
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(duree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ajouter)
                    .addComponent(supprimer)
                    .addComponent(annuler)
                    .addComponent(valider)
                    .addComponent(erreur))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

       
    
    private void ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterActionPerformed
        this.setVisible(false);
        ArrayList<Participant> liste = new ArrayList<>();
        try {
            Statement s = ExempleJdbc.connexion();
            try {
                ResultSet rs = s.executeQuery("SELECT distinct nomUsuel, prenom, dateDeNaissance, type FROM Participant");
                while (rs.next()) {
                    Participant participant = new Participant(rs.getString("nomUsuel"), rs.getString("prenom"), rs.getDate("dateDeNaissance"), rs.getString("type"));
                    liste.add(participant);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        try {
            CicAjouterParticipant a = new CicAjouterParticipant(listeParticipants, liste, nom.getText(), date.getText(), (int)duree.getValue(), login);
        } catch (SQLException ex) {
            Logger.getLogger(CicAjouterEtude.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ajouterActionPerformed

    private void supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerActionPerformed
        ArrayList<Participant> supp = new ArrayList<>();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (jTable1.isRowSelected(i)) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String da = String.valueOf(jTable1.getValueAt(i, 2));
                    java.util.Date d = dateFormat.parse(da);
                    Date date = new Date(d.getTime());
                    Participant p1 = new Participant(String.valueOf(jTable1.getValueAt(i, 0)), String.valueOf(jTable1.getValueAt(i, 1)), date, String.valueOf(jTable1.getValueAt(i, 3)));

                    for (Participant p : listeParticipants) {
                        if (p.egal(p1)) {
                            supp.add(p);
                        }
                    }
                    for (Participant p2 : supp) {
                        listeParticipants.remove(p2);
                    }

                } catch (ParseException ex) {
                    Logger.getLogger(CicAjouterEtude.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        remplirTableau();
    }//GEN-LAST:event_supprimerActionPerformed

    private void validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validerActionPerformed
       ArrayList<String> liste = new ArrayList<>();
        if (!nom.getText().equals("") && !date.getText().equals("") && (int) duree.getValue() != 0 && jTable1.getRowCount() > 0) {
            Statement s;
            try {
                s = ExempleJdbc.connexion();
                    if (verifNom(nom.getText())) {
                        erreur.setText("L'étude " + nom.getText() + " existe déjà.");
                        erreur.setVisible(true);
                    } else if (!verifDate(date.getText())) {
                        erreur.setText("La date n'est pas valide. Veuillez saisir une date sous la forme yyyy-MM-dd");
                        erreur.setVisible(true);
                    } else {
                        erreur.setVisible(false);
                        for (int i = 0; i < jTable1.getRowCount(); i++) {
                            try {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String da = String.valueOf(jTable1.getValueAt(i, 2));
                                java.util.Date d = dateFormat.parse(da);
                                Date d1 = new Date(d.getTime());
                                Participant p = new Participant(String.valueOf(jTable1.getValueAt(i, 0)), String.valueOf(jTable1.getValueAt(i, 1)), d1, String.valueOf(jTable1.getValueAt(i, 3)));
                                Etude e = new Etude(nom.getText(), login, date.getText(), (int) duree.getValue());
                                

//                                s = ExempleJdbc.connexion();
                                s.executeUpdate("INSERT INTO `Etude`(`nom`, `PH`, `date`, `duree`, `participantNomU`, `participantDate`, `participantPrenom`) VALUES ('" + e.getNom() + "','" + e.getPH() + "','" + e.getDate() + "','" + e.getDuree() + "','" + p.getNomU() + "','" + p.getDateN() + "','" + p.getPrenom() + "')");
                                ResultSet rs = s.executeQuery("SELECT distinct nomUsuel, nomDeNaissance, dateDeNaissance, prenom, type, sexe, taille, poids, pathologie, allergie, regime, sport, fumeur, categorie, ville FROM Participant WHERE (nomUsuel = '" + p.getNomU() + "' and dateDeNaissance = '" + p.getDateN() + "' and prenom = '" + p.getPrenom() + "')");
                                while (rs.next()) {
                                    s.executeUpdate("INSERT INTO `Participant`(`nomUsuel`, `nomDeNaissance`, `dateDeNaissance`, `prenom`, `type`, `sexe`, `taille`, `poids`, `pathologie`, `allergie`, `regime`, `sport`, `fumeur`, `categorie`, `ville`, `etude`) VALUES ('" + rs.getString("nomUsuel") + "','" + rs.getString("nomDeNaissance") + "','" + rs.getDate("dateDeNaissance") + "','" + rs.getString("prenom") + "','" + rs.getString("type") + "','" + rs.getString("sexe") + "','" + rs.getInt("taille") + "','" + rs.getInt("poids") + "','" + rs.getString("pathologie") + "','" + rs.getString("allergie") + "','" + rs.getString("regime") + "','" + rs.getString("sport") + "','" + rs.getString("fumeur") + "','" + rs.getString("categorie") + "','" + rs.getString("ville") + "','" + e.getNom() + "')");

                                }

                            } catch (ParseException ex) {
                                Logger.getLogger(CicAjouterEtude.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        JOptionPane.showMessageDialog(null, "L'étude " + nom.getText() + " a bien été ajoutée", "Message", JOptionPane.WARNING_MESSAGE);
                        this.setVisible(false);
                        try {
                            CicAccueil a = new CicAccueil(login);

                        } catch (SQLException ex) {
                            Logger.getLogger(CicAjouterEtude.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                
            } catch (SQLException ex) {
                Logger.getLogger(CicAjouterEtude.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            erreur.setText("Veuillez remplir tous les champs");
            erreur.setVisible(true);
        }

    }//GEN-LAST:event_validerActionPerformed

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateActionPerformed

    private void annulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerActionPerformed
        this.setVisible(false);
        try {
            CicAccueil accueil = new CicAccueil(login);

        } catch (SQLException ex) {
            Logger.getLogger(CicAjouterEtude.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_annulerActionPerformed

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
            java.util.logging.Logger.getLogger(CicAjouterEtude.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CicAjouterEtude.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CicAjouterEtude.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CicAjouterEtude.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CicAjouterEtude().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajouter;
    private javax.swing.JButton annuler;
    private javax.swing.JTextField date;
    private javax.swing.JButton deconnexion;
    private javax.swing.JSpinner duree;
    private javax.swing.JLabel erreur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nom;
    private javax.swing.JButton supprimer;
    private javax.swing.JLabel utilisateur;
    private javax.swing.JButton valider;
    // End of variables declaration//GEN-END:variables
}
