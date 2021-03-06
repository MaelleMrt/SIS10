/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIC;

import Connexion.ExempleJdbc;
import PageConnexion.InterfaceConnexion;
import Tri.Tri;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 * page qui affiche les informations d'une étude
 * @author clara
 */
public class CicEtude extends javax.swing.JFrame {

    /**
     * l'étude que l'on veut afficher
     * @see Etude
     */
    private Etude e;
    /**
     * liste des participants à l'étude
     */
    private ArrayList<Participant> listeParticipants= new ArrayList<Participant>();
    /**
     * PH qui est connecté
     */
    private Cic cic;
    /**
     * connexion à la base de données
     */
    private Statement s;
    
    /**
     * Constructeur CicEtude
     * initialise les éléments de la fenêtre
     * 
     * 
     * @param e correspond à l'étude qu'on affiche
     * @param cic correspond au PH connecté
     * @param s correspond à la connexion à la base de données
     * @throws SQLException gestion des exceptions
     */
    public CicEtude(Etude e,Cic cic,Statement s) throws SQLException {
        this.s=s;
        this.cic = cic;
        initComponents();
        this.e = e;
        jLabel13.setText(this.e.getNom());
        ph.setText(e.getPH());
        date.setText(e.getDate());
        duree.setText(String.valueOf(e.getDuree()) + " semaines" );
        remplirTableau();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
   }
    
    /**
     * remplit le tableau avec les participants de l'étude en interrogeant la base de données
     * @throws SQLException gestion des exceptions
     */
    public void remplirTableau() throws SQLException{
            try{
                ResultSet rs= s.executeQuery("SELECT nomUsuel, prenom, dateDeNaissance, type FROM Participant JOIN Etude on (nomUsuel = participantNomU AND prenom = participantPrenom AND dateDeNaissance = participantDate AND etude = nom) WHERE Etude.nom = '"+e.getNom()+"'");
                while(rs.next()){
                   Participant participant =new Participant(rs.getString("nomUsuel"), rs.getString("prenom"), rs.getDate("dateDeNaissance"), rs.getString("type"));
                   listeParticipants.add(participant);
                }   

            } catch(SQLException e){
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
        jScrollPane1 = new javax.swing.JScrollPane();
        participants = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Trier par...", "Nom", "Prénom", "Date de naissance", "Type" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rechercherTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rechercherTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    /**
     * ferme la fenêtre actuelle et retourne à la page d'accueil
     * @param evt 
     * @see CicAccueil
     */
    private void retourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retourActionPerformed
        this.setVisible(false);
        try {
            CicAccueil accueil = new CicAccueil(cic.getLogin(),s);
        } catch (SQLException ex) {
            Logger.getLogger(CicEtude.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_retourActionPerformed

    private void rechercherTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercherTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rechercherTextFieldActionPerformed

    /**
     * quand on clique sur une ligne du tableau des participants, ferme la fenêtre actuelle et renvoie à la page qui affiche les infos du participant
     * @param evt 
     * @see CicParticipant
     */
    private void participantsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_participantsMouseClicked
        int i = 0;
        while (i < participants.getRowCount() && !participants.isRowSelected(i)) {
            i++;
        }
        if (i < participants.getRowCount()) {

            try {
                String nom = String.valueOf(participants.getValueAt(i, 0));
                String prenom = String.valueOf(participants.getValueAt(i, 1));
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String da = String.valueOf(participants.getValueAt(i, 2));
                java.util.Date d = dateFormat.parse(da);
                Date date = new Date(d.getTime());
                String type = String.valueOf(participants.getValueAt(i, 3));
                Participant p = new Participant(nom, prenom, date,type);
                this.setVisible(false);  
                CicParticipant part = new CicParticipant(e,p,cic,s);
            } catch (ParseException ex) {
                Logger.getLogger(CicEtude.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_participantsMouseClicked

    /**
     * permet de faire une recherche par le nom du participant
     * affiche la liste des participants ayant un nom contenant le texte qu'on a entré dans la barre de recherche
     * @param evt 
     */
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
     * permet de se déconnecter
     * ferme la page actuelle et ouvre la page de connexion
     * @param evt 
     */
    private void deconnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deconnexionActionPerformed
        this.setVisible(false);
        InterfaceConnexion i = new InterfaceConnexion();
    }//GEN-LAST:event_deconnexionActionPerformed

    
    /**
     * permet de trier les participants par nom, prénom, date de naissance ou type
     * @param evt 
     */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (jComboBox1.getSelectedItem().equals("Nom")) {
            listeParticipants = new Tri().trierParticipantsParNom(listeParticipants);
        }
        else if (jComboBox1.getSelectedItem().equals("Prénom")) {
            listeParticipants = new Tri().trierParticipantsParPrenom(listeParticipants);
        }
        else if (jComboBox1.getSelectedItem().equals("Date de naissance")) {
            listeParticipants = new Tri().trierParticipantsParDates(listeParticipants);
        }
        else if (jComboBox1.getSelectedItem().equals("Type")) {
            listeParticipants = new Tri().trierParticipantsParType(listeParticipants);
        }
        //on remplit le tableau avec la liste triée
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
        participants.setModel(model);
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
//                Etude e1 = new Etude ("covid-19","house gregory","r",2);
//                try {
//                    new CicEtude(e1).setVisible(true);
//                } catch (SQLException ex) {
//                    Logger.getLogger(CicEtude.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date;
    private javax.swing.JButton deconnexion;
    private javax.swing.JLabel duree;
    private javax.swing.JComboBox jComboBox1;
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
    private javax.swing.JTextField rechercherTextField;
    private javax.swing.JButton retour;
    private javax.swing.JLabel utilisateur;
    // End of variables declaration//GEN-END:variables
}
