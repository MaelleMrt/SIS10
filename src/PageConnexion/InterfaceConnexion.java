/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PageConnexion;

import MedecinAnesthesiste.MedecinAnesthesisteAcceuil;
import CIC.CicAccueil;
import Connexion.ExempleJdbc;
import Infirmieres.InfirmierAcceuil;
import Medecin.MedecinAcceuil;
import Secretaire.Secretaire;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.KeyEvent;

/**
 * Interface de connexion
 *
 * @author Elodie
 */
public class InterfaceConnexion extends javax.swing.JFrame {

    /**
     * login de l'utilisateur
     */
    String Login;
    /**
     * mot de passe de l'utilisateur
     */
    String mdp;

    /**
     * Constructeur InterfaceConnexion initialise les commposants de la fenêtre
     */
    public InterfaceConnexion() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        jLabel5.setVisible(false);

    }

    /**
     * récupère le nom de l'utilisateur en interrogeant la base de données
     *
     * @param table la table dans laquelle on va chercher le nom
     * @return le nom
     */
    private String RecupereNom(String table) {
        String nom = null;
        try {
            Statement s = ExempleJdbc.connexion();
            ResultSet rs = s.executeQuery("SELECT nom FROM " + table + " WHERE login='" + Login + "'");
            while (rs.next()) {
                nom = rs.getString("nom");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return nom;
    }

    /**
     * récupère le prénom de l'utilisateur en interrogeant la base de données
     *
     * @param table la table dans laquelle on va chercher le prénom
     * @return le prénom
     */
    private String RecuperePrenom(String table) {
        String prenom = null;
        try {
            Statement s = ExempleJdbc.connexion();
            ResultSet rs = s.executeQuery("SELECT prenom FROM " + table + " WHERE login='" + Login + "'");
            while (rs.next()) {
                prenom = rs.getString("prenom");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return prenom;
    }

    /**
     * récupère le nom du service en interrogeant la base de données
     *
     * @param table la table dans laquelle on va chercher le nom
     * @return le nom
     */
    private String RecupereNomS(String table) {
        String nomS = null;
        try {
            Statement s = ExempleJdbc.connexion();
            ResultSet rs = s.executeQuery("SELECT  nomS FROM " + table + " WHERE login='" + Login + "'");
            while (rs.next()) {
                nomS = rs.getString("nomS");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return nomS;
    }

    /**
     * récupère l'identifiant qu'on a rentré dans le champ de texte
     *
     * @return l'identifiant
     */
    private String RecupereID() {
        String id;
        id = jTextField1.getText();
        return id;
    }

    /**
     * récupère le mot de passe qu'on a rentré dans le champ de texte
     *
     * @return le mot de passe
     */
    private String RecupereMDP() {
        char[] motPasse;
        motPasse=jPasswordField1.getPassword();
        String MDP= new String(motPasse);
        System.out.println("mdpNC ="+MDP);
        MDP= Cryptage.Cryptage.chiffre(5,MDP);
        System.out.println("mdpC = "+ MDP);
        return MDP;
    }

    /**
     * Vérifie si la connexion est 'valide' : si les identifiant et mot de passe
     * sont bons
     *
     * @return true si c'est valide, false sinon
     */
    private boolean Connexion() {
        boolean result = false;
        String MDP = null;
        ArrayList listlogin = new ArrayList<>();

        try {  // On recupere les logins 
            Statement s = ExempleJdbc.connexion();
            ResultSet rs = s.executeQuery("SELECT login FROM Utilisateur");
            while (rs.next()) {
                String log = rs.getString("login");
                listlogin.add(log);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        if (listlogin.contains(Login)) { // On vérifie que les identifiants sont bons
            try {
                Statement s = ExempleJdbc.connexion();
                ResultSet rs = s.executeQuery("SELECT mdp FROM Utilisateur WHERE login='" + Login + "'");
                while (rs.next()) {
                    MDP = rs.getString("mdp");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            if (mdp.equals(MDP)) {
                result = true;
            }
        }

        return result;
    }

    /**
     * on récupère le métier de l'utilisateur en interrogeant la base de données
     * @return le métier
     */
    private String Metier() {
        String metier = null;
        try {  // On recupee le metier de la personne qui se connecte 
            Statement s = ExempleJdbc.connexion();
            ResultSet rs = s.executeQuery("SELECT type FROM Utilisateur WHERE login='" + Login + "'");
            while (rs.next()) {
                metier = rs.getString("type");

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return metier;
    }

    /**
     * on récupère le service du médecin si l'utilisateur est un médecin
     * @return le service
     */
    private String Service() {
        String service = null;
        try {
            Statement s = ExempleJdbc.connexion();
            ResultSet rs = s.executeQuery("SELECT nomS  FROM Médecin WHERE login='" + Login + "'");
            while (rs.next()) {
                service = rs.getString("nomS");
                System.out.println(service);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return service;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Identifiant :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mot de passe :");

        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jButton1.setBackground(java.awt.SystemColor.activeCaption);
        jButton1.setText("Se connecter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("Mauvais identifiant ou mot de passe");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/logo_petit.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo/AtlanTISpng.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(41, 41, 41)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap(23, Short.MAX_VALUE))
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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        valider();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed

    }//GEN-LAST:event_jButton1KeyPressed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            valider();
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            valider();
        }
    }//GEN-LAST:event_jTextField1KeyPressed
    
    /**
     * on valide la connexion
     * si la connexion est valide, renvoie à la page d'accueil de l'utilisateur en fonction de son métier
     */
    public void valider() {
        this.Login = RecupereID();
        this.mdp = RecupereMDP();
        String metier = Metier();
        if (Connexion() == true) {
            this.setVisible(false);
            switch (metier) {
                case "secrétaire":
                    String nom = RecupereNom("Secrétaire");
                    String prenom = RecuperePrenom("Secrétaire");
                    String service = RecupereNomS("Secrétaire");
                    Secretaire s = new Secretaire(nom, prenom, service);
                    s.setVisible(true);
                    break;
                case "médecin":
                    if ("Anésthésie".equals(Service())) {
                        MedecinAnesthesisteAcceuil m = new MedecinAnesthesisteAcceuil(Login);
                    } else {
                        MedecinAcceuil m = new MedecinAcceuil(Login);
                    }
                    break;
                case "infirmière":
                    InfirmierAcceuil i = new InfirmierAcceuil(Login);
                    break;
                case "CIC":

                    try {
                        CicAccueil c = new CicAccueil(Login, ExempleJdbc.connexion());
                    } catch (SQLException ex) {
                        Logger.getLogger(InterfaceConnexion.class.getName()).log(Level.SEVERE, null, ex);
                    }

            }

        } else {
            jLabel5.setVisible(true);
        }
    }

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
            java.util.logging.Logger.getLogger(InterfaceConnexion.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceConnexion.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceConnexion.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceConnexion.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceConnexion();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
