package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import controleur.JsonController;
import modele.Joueur;

@SuppressWarnings("serial")
public class PanelQuitter extends JPanel {
	private JLabel labelTitre;
	public JButton boutonContinuer;
	public JButton boutonMenuPrincipal;
	private ArrayList<Joueur> joueurs;
	public JButton boutonQuitter;

	public PanelQuitter(ArrayList<Joueur> joueurs) {
		this.setBounds(265, 98, 544, 440);
		this.setBackground(new Color(16, 22, 33));
		this.setOpaque(true);
		this.setLayout(null);
		this.setVisible(false);
		
		//Récupération de la liste des joueurs
		this.joueurs = joueurs;
		
		/** TITRE DU PANEL **/
		labelTitre = new JLabel("Partie en Pause");
		labelTitre.setForeground(new Color(255, 204, 0));
		labelTitre.setFont(new Font("Times new Roman", Font.BOLD, 20));
		labelTitre.setBounds(200, 10, 209, 41);
		this.add(labelTitre);
		
		afficherBoutonQuitter(this);
		AfficherBoutonMenuPrincipal();
		AfficherBoutonContinuer(this);
	}	
			
	/********************************************************************/
	/** AFFICHAGE D'UN BOUTON QUI PERMET AUX JOUEURS DE QUITTER LE JEU **/
	/********************************************************************/		
	public void afficherBoutonQuitter(PanelQuitter MenuPause) {
		boutonQuitter = new JButton("Quitter");
		boutonQuitter.setBorder(UIManager.getBorder("Button.border"));
		boutonQuitter.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonQuitter. setFont(new Font("Times New Roman", Font.PLAIN, 20));
		boutonQuitter. setForeground (Color.white);
		boutonQuitter.setBounds (100, 100, 172, 44);
		boutonQuitter.setHorizontalTextPosition(JButton.CENTER);
		
		boutonQuitter.setBounds(190, 340, 172, 48);
		this.add(boutonQuitter);
	}		
	
	
	/*************************************************************************************************/
	/** AFFICHAGE D'UN BOUTON, QUI RETOURNE AU MenuPrincipal                                        **/
	/*************************************************************************************************/
	public void AfficherBoutonMenuPrincipal() {
		boutonMenuPrincipal = new JButton("Menu Principal");
		boutonMenuPrincipal.setBorder(UIManager.getBorder("Button.border"));
		boutonMenuPrincipal.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonMenuPrincipal. setFont(new Font("Times New Roman", Font.PLAIN, 20));
		boutonMenuPrincipal. setForeground (Color.white);
		boutonMenuPrincipal.setBounds (100, 100, 172, 44);
		boutonMenuPrincipal.setHorizontalTextPosition(JButton.CENTER);
		boutonMenuPrincipal.setBounds(190, 270, 172, 48);
		this.add(boutonMenuPrincipal);
	}
	
	/*************************************************************************/
	/** AFFICHAGE D'UN BOUTON QUI PERMET AUX JOUEURS DE CONTINUER LA PARTIE **/
	/*************************************************************************/		
	public void AfficherBoutonContinuer(PanelQuitter MenuPause) {
		boutonContinuer = new JButton("Continuer");
		boutonContinuer.setBorder(UIManager.getBorder("Button.border"));
		boutonContinuer.setIcon(new ImageIcon("images/large-button-active.png"));
		boutonContinuer. setFont(new Font("Times New Roman", Font.PLAIN, 20));
		boutonContinuer. setForeground (Color.white);
		boutonContinuer.setBounds (100, 100, 172, 44);
		boutonContinuer.setHorizontalTextPosition(JButton.CENTER);
		boutonContinuer.setBounds(190, 200, 172, 48);
		this.add(boutonContinuer);
	}


	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}		
}	