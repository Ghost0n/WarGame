package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;

import modele.Humain;
import modele.Joueur;
import modele.Ordinateur;
import modele.Soldat;

/************************************************************************************************************/
/* 							CLASSE HERITANT DE LA CLASSE MenuMultiJoueurs             	                    */
/* 							PAS BESOIN DE PRECISER LE NOMBRE DE JOUEURS: AUTOMATIQUEMENT 2                  */
/* 							ON A EFFACE TOUS LES WIDGET QUI SONT INUTILES                                   */
/************************************************************************************************************/

@SuppressWarnings("serial")
public class MenuSolo extends MenuMultiJoueurs {
	private JButton boutonValider;
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private ArrayList<Joueur> adversaires_humain = new ArrayList<Joueur>();
	private ArrayList<Joueur> adversaires_ordinateur = new ArrayList<Joueur>();
	public MenuSolo() {
		super();
		choix = false;
		
		effacerNombreJoueur();
		
		/** METTANT LE NOMBRE DE JOUEURS A 2 **/
		nombreJoueur = 2;
		pressed = true;
		afficherChampTextPseudo();
		afficherLabelNumJoueur();
		afficherCombobox();
		effacerSpinner();
		effacerBoutonNombreJoueur();
		recupererPseudo();
		
		afficherBoutonValider();
		
		/** METTANT UN NOM POUR L'ORDINATEUR **/
		listeChampText.get(1).setText("IA");
		listeChampText.get(1).setEditable(false);
		listeCombobox.get(1).setEnabled(false);
		
	}
	
	/************************************/
	/** EFFACER LE LABEL NOMBRE JOUEUR **/
	/************************************/
	public void effacerNombreJoueur() {
		labelNombreJoueur.setVisible(false);
	}
	
	/********************************************************************/
	/** EFFACER LE SPINNER : AUTOMATIQUEMENT LE NOMBRE DE JOUEUR EST 2 **/
	/********************************************************************/
	public void effacerSpinner() {
		spinnerNombreJoueur.setVisible(false);
	}
	
	/*************************************/
	/** EFFACER LE BOUTON NOMBRE JOUEUR **/
	/*************************************/
	public void effacerBoutonNombreJoueur() {
		boutonNombreJoueur.setVisible(false);
	}
	
	/**********************************************************************************************************/
	/** AFFICHAGE D'UN BOUTON QUI PERMET DE STOCKER LES PSEUDOS POUR CHAQUE JOUEUR ET PASSER AU MENU SUIVANT **/
	/**********************************************************************************************************/
	public void afficherBoutonValider() {
		boutonValider = new JButton();
		boutonValider.setText("Valider");
		boutonValider.setBorder(UIManager.getBorder("Button.border"));
		boutonValider.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		boutonValider.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				recupererPseudo();
				recupererImage();
				
				String photoProfile = "images/profile/" + images.get(0) +".png";
				
				Humain humain = new Humain(pseudos.get(0), new ArrayList<Soldat>(),0, photoProfile, adversaires);
				Ordinateur IA = new Ordinateur(pseudos.get(1), new ArrayList<Soldat>(), 0, "images/profile/Gario.png", adversaires);
				
				adversaires_humain.add(IA);
				adversaires_ordinateur.add(humain);
				
				humain.setAdversaires(adversaires_humain);
				IA.setAdversaires(adversaires_ordinateur);
				
				humain.setNomJoueur(listeChampText.get(0).getText());
				IA.setNomJoueur("IA");
				
				joueurs.add(humain);
				joueurs.add(IA);
				
				MenuScenario menuScenario = new MenuScenario(choix,joueurs);
				menuScenario.show();
				dispose();
			}
		});
		boutonValider.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		boutonValider.setForeground(Color.white);
		boutonValider.setHorizontalTextPosition(JButton.CENTER);
		boutonValider.setIcon(new ImageIcon("images/menu_button_small_H18-active@2x.png"));
		boutonValider.setBounds(443, 401, 109, 34);
		panelMenu.add(boutonValider);
	}

}
