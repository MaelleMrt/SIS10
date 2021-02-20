/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CIC;

import Connexion.ExempleJdbc;
import PageConnexion.InterfaceConnexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clara
 */
public class CicRechercherParticipant extends javax.swing.JFrame {

    private ArrayList<Participant> listeParticipants = new ArrayList<Participant>();
    private ArrayList<Participant> ancienneListe = new ArrayList<Participant>();
    private String nom;
    private String date;
    private int duree;
    private String login;
    private Cic cic;

    public CicRechercherParticipant(ArrayList<Participant> ancienneListe, ArrayList<Participant> listeParticipants, String nom, String date, int duree,String login) {
        
        
        this.listeParticipants = listeParticipants;
        this.ancienneListe = ancienneListe;
        this.nom = nom;
        this.date = date;
        this.duree = duree;
        this.login = login;
        trouverCic();
        initComponents();
        erreur.setVisible(false);
        this.setVisible(true);
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

    public ArrayList<String> Type() {
        ArrayList<String> liste = new ArrayList<>();
        if (type1.isSelected()) {
            liste.add("patient");
        }
        if (type2.isSelected()) {
            liste.add("volontaire sain");
        }
        if (!type1.isSelected() && !type2.isSelected()) {
            liste.add("patient");
            liste.add("volontaire sain");
        }
        return liste;
    }

    public ArrayList<String> Sexe() {
        ArrayList<String> liste = new ArrayList<>();
        if (sexe1.isSelected()) {
            liste.add("F");
        }
        if (sexe2.isSelected()) {
            liste.add("H");
        }
        if (!sexe1.isSelected() && !sexe2.isSelected()) {
            liste.add("F");
            liste.add("H");
        }
        return liste;
    }

    public ArrayList<String> Allergie() {
        ArrayList<String> liste = new ArrayList<>();
        if (allergie1.isSelected()) {
            liste.add("alimentaire");
        }
        if (allergie2.isSelected()) {
            liste.add("médicament");
        }
        if (allergie3.isSelected()) {
            liste.add("autre");
        }
        if (allergie4.isSelected()) {
            liste.add("aucune");
        }
        if (!allergie1.isSelected() && !allergie2.isSelected() && !allergie3.isSelected() && !allergie4.isSelected()) {
            liste.add("alimentaire");
            liste.add("médicament");
            liste.add("autre");
            liste.add("aucune");
        }
        return liste;
    }

    public ArrayList<String> Regime() {
        ArrayList<String> liste = new ArrayList<>();
        if (regime1.isSelected()) {
            liste.add("végétarien");
        }
        if (regime2.isSelected()) {
            liste.add("sans gluten");
        }
        if (regime3.isSelected()) {
            liste.add("sans sel");
        }
        if (regime4.isSelected()) {
            liste.add("autre");
        }
        if (regime5.isSelected()) {
            liste.add("normal");
        }
        if (!regime1.isSelected() && !regime2.isSelected() && !regime3.isSelected() && !regime4.isSelected() && !regime5.isSelected()) {
            liste.add("végétarien");
            liste.add("sans gluten");
            liste.add("sans sel");
            liste.add("autre");
            liste.add("normal");
        }
        return liste;
    }

    public ArrayList<String> Sport() {
        ArrayList<String> liste = new ArrayList<>();
        if (sport1.isSelected()) {
            liste.add("jamais");
        }
        if (sport2.isSelected()) {
            liste.add("rarement");
        }
        if (sport3.isSelected()) {
            liste.add("régulièrement");
        }
        if (sport4.isSelected()) {
            liste.add("sportif de haut niveau");
        }
        if (!sport1.isSelected() && !sport2.isSelected() && !sport3.isSelected() && !sport4.isSelected()) {
            liste.add("jamais");
            liste.add("rarement");
            liste.add("régulièrement");
            liste.add("sportif de haut niveau");
        }
        return liste;
    }

    public ArrayList<String> Fumeur() {
        ArrayList<String> liste = new ArrayList<>();
        if (fumeur1.isSelected()) {
            liste.add("oui");
        }
        if (fumeur2.isSelected()) {
            liste.add("non");
        }
        if (!fumeur1.isSelected() && !fumeur2.isSelected()) {
            liste.add("oui");
            liste.add("non");
        }
        return liste;
    }

    public ArrayList<String> Categorie() {
        ArrayList<String> liste = new ArrayList<>();
        if (cat1.isSelected()) {
            liste.add("agriculteurs exploitants");
        }
        if (cat2.isSelected()) {
            liste.add("artisans, commerçants, chefs d entreprise");
        }
        if (cat3.isSelected()) {
            liste.add("cadres et professions intellectuelles supérieures");
        }
        if (cat4.isSelected()) {
            liste.add("professions intermédiaires");
        }
        if (cat5.isSelected()) {
            liste.add("employés");
        }
        if (cat6.isSelected()) {
            liste.add("ouvriers");
        }
        if (cat7.isSelected()) {
            liste.add("retraités");
        }
        if (cat8.isSelected()) {
            liste.add("étudiants");
        }
        if (cat9.isSelected()) {
            liste.add("autres sans activité professionnelle");
        }
        if (!cat1.isSelected() && !cat2.isSelected() && !cat3.isSelected() && !cat4.isSelected() && !cat5.isSelected() && !cat6.isSelected() && !cat7.isSelected() && !cat8.isSelected() && !cat9.isSelected()) {
            liste.add("agriculteurs exploitants");
            liste.add("artisans, commerçants, chefs d entreprise");
            liste.add("cadres et professions intellectuelles supérieures");
            liste.add("professions intermédiaires");
            liste.add("employés");
            liste.add("ouvriers");
            liste.add("retraités");
            liste.add("étudiants");
            liste.add("autres sans activité professionnelle");
        }
        return liste;
    }

    public Date Date(String str) throws ParseException {
        Date d = new SimpleDateFormat("dd/MM/yyyy").parse(str);
        return d;
    }

    public ArrayList<Integer> Age() {
        ArrayList<Integer> liste = new ArrayList<>();

        if (age1.isSelected()) {
            liste.add(1);
        }
        if (age2.isSelected()) {
            liste.add(2);
        }
        if (age3.isSelected()) {
            liste.add(3);
        }
        if (age4.isSelected()) {
            liste.add(4);
        }
        if (!age1.isSelected() && !age2.isSelected() && !age3.isSelected() && !age4.isSelected()) {
            liste.add(1);
            liste.add(2);
            liste.add(3);
            liste.add(4);
        }
        return liste;
    }

    public ArrayList<String> Taille() {
        ArrayList<String> liste = new ArrayList<>();
        if (taille1.isSelected()) {
            liste.add(" AND taille < 150");
        }
        if (taille2.isSelected()) {
            liste.add(" AND taille BETWEEN 150 AND 170");
        }
        if (taille3.isSelected()) {
            liste.add(" AND taille BETWEEN 171 AND 190");
        }
        if (taille4.isSelected()) {
            liste.add(" AND taille > 190");
        }
        if (!taille1.isSelected() && !taille2.isSelected() && !taille3.isSelected() && !taille4.isSelected()) {
            liste.add(" AND taille < 150");
            liste.add(" AND taille BETWEEN 150 AND 170");
            liste.add(" AND taille BETWEEN 171 AND 190");
            liste.add(" AND taille > 190");
        }
        return liste;
    }

    public ArrayList<String> Poids() {
        ArrayList<String> liste = new ArrayList<>();
        if (poids1.isSelected()) {
            liste.add(" AND poids < 45");
        }
        if (poids2.isSelected()) {
            liste.add(" AND poids BETWEEN 45 AND 60");
        }
        if (poids3.isSelected()) {
            liste.add(" AND poids BETWEEN 61 AND 75");
        }
        if (poids4.isSelected()) {
            liste.add(" AND poids BETWEEN 76 AND 90");
        }
        if (poids5.isSelected()) {
            liste.add(" AND poids BETWEEN 91 AND 105");
        }
        if (poids6.isSelected()) {
            liste.add(" AND poids > 105");
        }
        if (!poids1.isSelected() && !poids2.isSelected() && !poids3.isSelected() && !poids4.isSelected() && !poids5.isSelected() && !poids6.isSelected()) {
            liste.add(" AND poids < 45");
            liste.add(" AND poids BETWEEN 45 AND 60");
            liste.add(" AND poids BETWEEN 61 AND 75");
            liste.add(" AND poids BETWEEN 76 AND 90");
            liste.add(" AND poids BETWEEN 91 AND 105");
            liste.add(" AND poids > 105");
        }
        return liste;
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
        jLabel3 = new javax.swing.JLabel();
        type1 = new javax.swing.JCheckBox();
        type2 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        sexe1 = new javax.swing.JCheckBox();
        sexe2 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        age1 = new javax.swing.JCheckBox();
        age2 = new javax.swing.JCheckBox();
        age3 = new javax.swing.JCheckBox();
        age4 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        taille1 = new javax.swing.JCheckBox();
        taille2 = new javax.swing.JCheckBox();
        taille3 = new javax.swing.JCheckBox();
        taille4 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        poids1 = new javax.swing.JCheckBox();
        poids2 = new javax.swing.JCheckBox();
        poids3 = new javax.swing.JCheckBox();
        poids4 = new javax.swing.JCheckBox();
        poids5 = new javax.swing.JCheckBox();
        poids6 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        pathologie = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        allergie1 = new javax.swing.JCheckBox();
        allergie2 = new javax.swing.JCheckBox();
        allergie3 = new javax.swing.JCheckBox();
        allergie4 = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        regime1 = new javax.swing.JCheckBox();
        regime2 = new javax.swing.JCheckBox();
        regime3 = new javax.swing.JCheckBox();
        regime4 = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        sport1 = new javax.swing.JCheckBox();
        sport2 = new javax.swing.JCheckBox();
        sport3 = new javax.swing.JCheckBox();
        sport4 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        fumeur1 = new javax.swing.JCheckBox();
        fumeur2 = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        cat1 = new javax.swing.JCheckBox();
        cat2 = new javax.swing.JCheckBox();
        cat3 = new javax.swing.JCheckBox();
        cat4 = new javax.swing.JCheckBox();
        cat5 = new javax.swing.JCheckBox();
        cat6 = new javax.swing.JCheckBox();
        cat7 = new javax.swing.JCheckBox();
        cat8 = new javax.swing.JCheckBox();
        cat9 = new javax.swing.JCheckBox();
        rechercher = new javax.swing.JButton();
        annuler = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        ville = new javax.swing.JTextField();
        regime5 = new javax.swing.JCheckBox();
        erreur = new javax.swing.JLabel();

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Ajouter un participant");

        type1.setText("Patient");
        type1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type1ActionPerformed(evt);
            }
        });

        type2.setText("Volontaire sain");

        jLabel4.setText("Sexe :");

        sexe1.setText("F");

        sexe2.setText("H");
        sexe2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexe2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Age :");

        age1.setText("18-25");

        age2.setText("26-40");

        age3.setText("41-65");
        age3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                age3ActionPerformed(evt);
            }
        });

        age4.setText(">65");

        jLabel6.setText("Taille (cm) :");

        taille1.setText("<150");
        taille1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taille1ActionPerformed(evt);
            }
        });

        taille2.setText("150-170");

        taille3.setText("171-190");

        taille4.setText(">190");

        jLabel7.setText("Poids (kg) :");

        poids1.setText("<45");
        poids1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poids1ActionPerformed(evt);
            }
        });

        poids2.setText("45-60");

        poids3.setText("61-75");

        poids4.setText("76-90");

        poids5.setText("91-105");

        poids6.setText(">105");

        jLabel8.setText("Pathologie(s) :");

        jLabel9.setText("Allergie(s) :");

        allergie1.setText("Alimentaire");
        allergie1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allergie1ActionPerformed(evt);
            }
        });

        allergie2.setText("Médicament");

        allergie3.setText("Autre");

        allergie4.setText("Aucune");
        allergie4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allergie4ActionPerformed(evt);
            }
        });

        jLabel10.setText("Régime alimentaire :");

        regime1.setText("Végétarien");

        regime2.setText("Sans gluten");
        regime2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regime2ActionPerformed(evt);
            }
        });

        regime3.setText("Sans sel");
        regime3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regime3ActionPerformed(evt);
            }
        });

        regime4.setText("Autre");
        regime4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regime4ActionPerformed(evt);
            }
        });

        jLabel11.setText("Sport :");

        sport1.setText("Jamais");
        sport1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sport1ActionPerformed(evt);
            }
        });

        sport2.setText("Rarement");

        sport3.setText("Régulièrement");

        sport4.setText("Sportif de haut niveau");

        jLabel12.setText("Fumeur :");

        fumeur1.setText("Oui");

        fumeur2.setText("Non");
        fumeur2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fumeur2ActionPerformed(evt);
            }
        });

        jLabel13.setText("Catégorie socio-professionnelle :");

        cat1.setText("Agriculteurs exploitants");

        cat2.setText("Artisans, commerçants, chefs d'entreprise");
        cat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cat2ActionPerformed(evt);
            }
        });

        cat3.setText("Cadres et professions intellectuelles supérieures");
        cat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cat3ActionPerformed(evt);
            }
        });

        cat4.setText("Professions intermédiaires ");

        cat5.setText("Employés");

        cat6.setText("Ouvriers");
        cat6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cat6ActionPerformed(evt);
            }
        });

        cat7.setText("Retraités");
        cat7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cat7ActionPerformed(evt);
            }
        });

        cat8.setText("Etudiants");

        cat9.setText("Autres sans activité professionnelle");
        cat9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cat9ActionPerformed(evt);
            }
        });

        rechercher.setText("Rechercher");
        rechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercherActionPerformed(evt);
            }
        });

        annuler.setText("Annuler");
        annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerActionPerformed(evt);
            }
        });

        jLabel14.setText("Ville :");

        regime5.setText("Normal");
        regime5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regime5ActionPerformed(evt);
            }
        });

        erreur.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        erreur.setForeground(new java.awt.Color(255, 0, 0));
        erreur.setText("Aucun participant ne correspond à votre recherche");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(452, 452, 452)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(454, 454, 454))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sexe1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sexe2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(type1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(type2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(age1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(age2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(age3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(age4))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(poids1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(poids2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(poids3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(poids4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(poids5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(poids6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(taille1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(taille2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(taille3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(taille4))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(allergie1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(allergie2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(allergie3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(allergie4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sport1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sport2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sport3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sport4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fumeur1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fumeur2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cat4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cat5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cat6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cat7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cat8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cat9))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cat1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cat2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cat3))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pathologie))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(regime1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(regime2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(regime3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(regime4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regime5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ville, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(erreur, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108)
                                .addComponent(annuler)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rechercher)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(type1)
                    .addComponent(type2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(sexe1)
                    .addComponent(sexe2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(age1)
                    .addComponent(age2)
                    .addComponent(age3)
                    .addComponent(age4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(taille1)
                    .addComponent(taille2)
                    .addComponent(taille3)
                    .addComponent(taille4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(poids1)
                    .addComponent(poids2)
                    .addComponent(poids3)
                    .addComponent(poids4)
                    .addComponent(poids5)
                    .addComponent(poids6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(pathologie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(allergie1)
                    .addComponent(allergie2)
                    .addComponent(allergie3)
                    .addComponent(allergie4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(regime1)
                    .addComponent(regime2)
                    .addComponent(regime3)
                    .addComponent(regime4)
                    .addComponent(regime5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(sport1)
                    .addComponent(sport2)
                    .addComponent(sport3)
                    .addComponent(sport4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(fumeur1)
                    .addComponent(fumeur2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cat1)
                    .addComponent(cat2)
                    .addComponent(cat3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cat4)
                            .addComponent(cat5)
                            .addComponent(cat6)
                            .addComponent(cat7)
                            .addComponent(cat8)
                            .addComponent(cat9))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(ville, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rechercher)
                            .addComponent(annuler)))
                    .addComponent(erreur, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void type1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_type1ActionPerformed

    private void sexe2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexe2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sexe2ActionPerformed

    private void age3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_age3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_age3ActionPerformed

    private void taille1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taille1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taille1ActionPerformed

    private void poids1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poids1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_poids1ActionPerformed

    private void allergie1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allergie1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_allergie1ActionPerformed

    private void allergie4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allergie4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_allergie4ActionPerformed

    private void regime2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regime2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regime2ActionPerformed

    private void regime3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regime3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regime3ActionPerformed

    private void regime4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regime4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regime4ActionPerformed

    private void sport1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sport1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sport1ActionPerformed

    private void fumeur2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fumeur2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fumeur2ActionPerformed

    private void cat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cat2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cat2ActionPerformed

    private void cat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cat3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cat3ActionPerformed

    private void cat6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cat6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cat6ActionPerformed

    private void cat7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cat7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cat7ActionPerformed

    private void cat9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cat9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cat9ActionPerformed

    private void rechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercherActionPerformed

        ArrayList<String> l1 = Type();
        ArrayList<String> l2 = Sexe();
        ArrayList<String> l3 = Allergie();
        ArrayList<String> l4 = Regime();
        ArrayList<String> l5 = Sport();
        ArrayList<String> l6 = Fumeur();
        ArrayList<String> l7 = Categorie();
        ArrayList<Integer> l8 = Age();
        ArrayList<String> l9 = Taille();
        ArrayList<String> l10 = Poids();
        String patho = pathologie.getText();
        String v = ville.getText();

        listeParticipants = new ArrayList<Participant>();
        erreur.setVisible(false);
        
        try {
            Statement st = ExempleJdbc.connexion();
            ResultSet rs;
            for (String t : l1) {
                for (String s : l2) {
                    for (String a : l3) {
                        for (String r : l4) {
                            for (String sp : l5) {
                                for (String f : l6) {
                                    for (String c : l7) {
                                        for (int age : l8) {
                                            String s1;
                                            if (age == 1) {
                                                Date aujourdhui = new Date();
                                                int annee1 = aujourdhui.getYear() - 18;
                                                int annee2 = aujourdhui.getYear() - 25;
                                                java.sql.Date d1 = new java.sql.Date(annee1, aujourdhui.getMonth(), aujourdhui.getDate());
                                                java.sql.Date d2 = new java.sql.Date(annee2, aujourdhui.getMonth(), aujourdhui.getDate());
                                                s1 = " BETWEEN '" + d2 + "' AND '" + d1 + "'";
                                            } else if (age == 2) {
                                                Date aujourdhui = new Date();
                                                int annee1 = aujourdhui.getYear() - 26;
                                                int annee2 = aujourdhui.getYear() - 40;
                                                java.sql.Date d1 = new java.sql.Date(annee1, aujourdhui.getMonth(), aujourdhui.getDate());
                                                java.sql.Date d2 = new java.sql.Date(annee2, aujourdhui.getMonth(), aujourdhui.getDate());
                                                s1 = " BETWEEN '" + d2 + "' AND '" + d1 + "'";
                                            } else if (age == 3) {
                                                Date aujourdhui = new Date();
                                                int annee1 = aujourdhui.getYear() - 41;
                                                int annee2 = aujourdhui.getYear() - 65;
                                                java.sql.Date d1 = new java.sql.Date(annee1, aujourdhui.getMonth(), aujourdhui.getDate());
                                                java.sql.Date d2 = new java.sql.Date(annee2, aujourdhui.getMonth(), aujourdhui.getDate());
                                                s1 = " BETWEEN '" + d2 + "' AND '" + d1 + "'";
                                            } else {
                                                Date aujourdhui = new Date();
                                                int annee1 = aujourdhui.getYear() - 65;
                                                java.sql.Date d1 = new java.sql.Date(annee1, aujourdhui.getMonth(), aujourdhui.getDate());
                                                s1 = " < '" + d1 + "'";

                                            }
                                            for (String ta : l9) {
                                                for (String p : l10) {
                                                    String requete = "SELECT nomUsuel, prenom, dateDeNaissance, type FROM Participant WHERE type ='" + t + "' AND sexe ='" + s + "' AND allergie = '" + a + "' AND regime = '" + r + "' AND sport ='" + sp + "' AND fumeur ='" + f + "' AND categorie ='" + c + "'" + ta + p + " AND dateDeNaissance" + s1;

                                                    if (!v.equals("")) {
                                                        if (!patho.equals("")) {

                                                            try {
                                                                rs = st.executeQuery(requete + " AND ville ='" + v + "' AND pathologie ='" + patho + "'");

                                                                while (rs.next()) {
                                                                    System.out.println(rs.getString("nomUsuel"));
                                                                    Participant participant = new Participant(rs.getString("nomUsuel"), rs.getString("prenom"), rs.getDate("dateDeNaissance"), rs.getString("type"));
                                                                    if (!listeParticipants.contains(participant)) {
                                                                        listeParticipants.add(participant);
                                                                    }
                                                                }

                                                            } catch (SQLException e) {
                                                                System.out.println(e);
                                                            }

                                                        } else {

                                                            try {
                                                                rs = st.executeQuery(requete + " AND ville ='" + v + "'");

                                                                while (rs.next()) {
                                                                    System.out.println(rs.getString("nomUsuel"));
                                                                    Participant participant = new Participant(rs.getString("nomUsuel"), rs.getString("prenom"), rs.getDate("dateDeNaissance"), rs.getString("type"));
                                                                    if (!listeParticipants.contains(participant)) {
                                                                        listeParticipants.add(participant);
                                                                    }
                                                                }

                                                            } catch (SQLException e) {
                                                                System.out.println(e);
                                                            }

                                                        }
                                                    } else {
                                                        if (!patho.equals("")) {

                                                            try {
                                                                rs = st.executeQuery(requete + " AND pathologie ='" + patho + ")");

                                                                while (rs.next()) {
                                                                    System.out.println(rs.getString("nomUsuel"));
                                                                    Participant participant = new Participant(rs.getString("nomUsuel"), rs.getString("prenom"), rs.getDate("dateDeNaissance"), rs.getString("type"));
                                                                    if (!listeParticipants.contains(participant)) {
                                                                        listeParticipants.add(participant);
                                                                    }
                                                                }

                                                            } catch (SQLException e) {
                                                                System.out.println(e);
                                                            }

                                                        } else {

                                                            try {
                                                                rs = st.executeQuery(requete);

                                                                while (rs.next()) {
                                                                    System.out.println(rs.getString("nomUsuel"));
                                                                    Participant participant = new Participant(rs.getString("nomUsuel"), rs.getString("prenom"), rs.getDate("dateDeNaissance"), rs.getString("type"));
                                                                    if (!listeParticipants.contains(participant)) {
                                                                        listeParticipants.add(participant);
                                                                    }
                                                                }

                                                            } catch (SQLException e) {
                                                                System.out.println(e);
                                                            }

                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        if (listeParticipants.isEmpty()) {
            erreur.setVisible(true);
        } else {
            this.setVisible(false);
            try {
                CicAjouterParticipant a = new CicAjouterParticipant(ancienneListe, listeParticipants, nom, date, duree,login);
            } catch (SQLException ex) {
                Logger.getLogger(CicRechercherParticipant.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_rechercherActionPerformed

    private void annulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerActionPerformed
        this.setVisible(false);
        try {
            CicAjouterParticipant a = new CicAjouterParticipant(ancienneListe, listeParticipants, nom, date, duree,login);
        } catch (SQLException ex) {
            Logger.getLogger(CicRechercherParticipant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_annulerActionPerformed

    private void regime5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regime5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regime5ActionPerformed

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
            java.util.logging.Logger.getLogger(CicRechercherParticipant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CicRechercherParticipant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CicRechercherParticipant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CicRechercherParticipant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new CicRechercherParticipant().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox age1;
    private javax.swing.JCheckBox age2;
    private javax.swing.JCheckBox age3;
    private javax.swing.JCheckBox age4;
    private javax.swing.JCheckBox allergie1;
    private javax.swing.JCheckBox allergie2;
    private javax.swing.JCheckBox allergie3;
    private javax.swing.JCheckBox allergie4;
    private javax.swing.JButton annuler;
    private javax.swing.JCheckBox cat1;
    private javax.swing.JCheckBox cat2;
    private javax.swing.JCheckBox cat3;
    private javax.swing.JCheckBox cat4;
    private javax.swing.JCheckBox cat5;
    private javax.swing.JCheckBox cat6;
    private javax.swing.JCheckBox cat7;
    private javax.swing.JCheckBox cat8;
    private javax.swing.JCheckBox cat9;
    private javax.swing.JButton deconnexion;
    private javax.swing.JLabel erreur;
    private javax.swing.JCheckBox fumeur1;
    private javax.swing.JCheckBox fumeur2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField pathologie;
    private javax.swing.JCheckBox poids1;
    private javax.swing.JCheckBox poids2;
    private javax.swing.JCheckBox poids3;
    private javax.swing.JCheckBox poids4;
    private javax.swing.JCheckBox poids5;
    private javax.swing.JCheckBox poids6;
    private javax.swing.JButton rechercher;
    private javax.swing.JCheckBox regime1;
    private javax.swing.JCheckBox regime2;
    private javax.swing.JCheckBox regime3;
    private javax.swing.JCheckBox regime4;
    private javax.swing.JCheckBox regime5;
    private javax.swing.JCheckBox sexe1;
    private javax.swing.JCheckBox sexe2;
    private javax.swing.JCheckBox sport1;
    private javax.swing.JCheckBox sport2;
    private javax.swing.JCheckBox sport3;
    private javax.swing.JCheckBox sport4;
    private javax.swing.JCheckBox taille1;
    private javax.swing.JCheckBox taille2;
    private javax.swing.JCheckBox taille3;
    private javax.swing.JCheckBox taille4;
    private javax.swing.JCheckBox type1;
    private javax.swing.JCheckBox type2;
    private javax.swing.JLabel utilisateur;
    private javax.swing.JTextField ville;
    // End of variables declaration//GEN-END:variables
}
