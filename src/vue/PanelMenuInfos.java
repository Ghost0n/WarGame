package vue;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


/************************************************************************************************************************************/
/* 			           CLASSE CONSISTE A AFFICHER UN PANEL QUI VA ETRE UTILISER DANS PLUSIEURS MENU             	                */
/* 			          POUR ETRE CAPABLE D'UTILISER CE PANEL LIBREMENT, ON LUI A DONNEE (X, Y, LARGEUR, LONGEUR) COMME ATTRIBUT      */
/* 					  ON A UTILISE CETTE CLASSE DANS LE MenuPrincipa, ET DANS MenuMultiJoueurs (--> MenuSolo)                       */
/************************************************************************************************************************************/

@SuppressWarnings("serial")
public class PanelMenuInfos extends JPanel {

	
	private int x;
	private int y;
	private int width;
	private int height;
	
	public PanelMenuInfos(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.setBounds(x, y, 596, 480);
		this.setBackground(new Color(16, 22, 33));
		this.setLayout(null);
		
		afficherCoin();
		afficherLogoJeu();
		afficherLabel();
		afficherBorder();
		
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	/*******************************************/
	/** AFFICHER TOUS LES COINS (SUP, ET INF) **/
	/*******************************************/
	public void afficherCoin() {
		afficherCoinSup();
		afficherCoinInf();
	}
	
	/***********************************/
	/** AFFICHER LES COINS SUPERIEURS **/
	/************************************/
	public void afficherCoinSup() {
		afficherCoinSupDroite();
		afficherCoinSupGauche();
	}
	
	/*********************************************/
	/** AFFICHER LES COINS SUPERIEURES A DROITE **/
	/*********************************************/
	public void afficherCoinSupDroite() {

		JLabel coinSupDroite = new JLabel("");
		coinSupDroite.setIcon(new ImageIcon("images/strong_opaque-border-topright.png"));
		coinSupDroite.setBounds(Math.abs(width - 25), 0, Math.abs(width - 569), Math.abs(height - 454));
		this.add(coinSupDroite);
	}
	
	/*********************************************/
	/** AFFICHER LES COINS SUPERIEURES A GAUCHE **/
	/*********************************************/
	public void afficherCoinSupGauche() {
		JLabel coinSupGauche = new JLabel("");
		coinSupGauche.setIcon(new ImageIcon("images/strong_opaque-border-topleft.png"));
		coinSupGauche.setBounds(0, 0, Math.abs(width - 570), Math.abs(height - 451));
		this.add(coinSupGauche);
	}
	
	/************************************/
	/** AFFICHER LES COINS INFERIEURES **/
	/************************************/
	public void afficherCoinInf() {
		afficherCoinInfGauche();
		afficherCoinInfDroite();
	}
	
	/*********************************************/
	/** AFFICHER LES COINS INFERIEURES A GAUCHE **/
	/*********************************************/
	public void afficherCoinInfGauche() {
		JLabel coinInfGauche = new JLabel("");
		coinInfGauche.setIcon(new ImageIcon("images/strong_opaque-border-botleft.png"));
		coinInfGauche.setBounds(0, Math.abs(height - 25) , Math.abs(width - 570), Math.abs(height - 451));
		this.add(coinInfGauche);
	}
	
	/*********************************************/
	/** AFFICHER LES COINS INFERIEURES A DROITE **/
	/*********************************************/
	public void afficherCoinInfDroite() {
		JLabel coinInfDroite = new JLabel("\r\n");
		coinInfDroite.setIcon(new ImageIcon("images/strong_opaque-border-botright.png"));
		coinInfDroite.setBounds(Math.abs(width - 25), Math.abs(height - 25), Math.abs(width - 569), Math.abs(height - 451));
		this.add(coinInfDroite);
	}
	
	/******************************/
	/** AFFICHER TOUS LES LABELS **/
	/******************************/
	public void afficherLabel() {
		afficherLabelGauche();
		afficherLabelDroite();
	}
	
	/**********************************/
	/** AFFICHER LES LABELS A GAUCHE **/
	/**********************************/
	public void afficherLabelGauche() {
		int new_y = 23;
		int i = 0;
		
		for(i=0; i<4; i++) {
			JLabel labelGauche = new JLabel("");
			labelGauche.setIcon(new ImageIcon("images/strong_opaque-border-left.png"));
			labelGauche.setBounds(0, Math.abs(y - (y-new_y)), Math.abs(width - 570), Math.abs(height - 340));
			this.add(labelGauche);
			
			new_y += 100;
		}
	}
	
	/**********************************/
	/** AFFICHER LES LABELS A DOITE **/
	/**********************************/
	public void afficherLabelDroite() {
		int new_y = 23;
		int i = 0;
		
		for(i=0; i<4; i++) {
			JLabel labelDroite = new JLabel("");
			labelDroite.setIcon(new ImageIcon("images/strong_opaque-border-right.png"));
			labelDroite.setBounds(Math.abs(width - 25), Math.abs(y - (y-new_y)), Math.abs(width - 570), Math.abs(height - 340));
			this.add(labelDroite);
			
			new_y += 100;
		}
	}
	
	/**********************************/
	/** AFFICHER LES LABELS A GAUCHE **/
	/**********************************/
	public void afficherBorder() {
		afficherTopBorder();
		afficherBottomBorder();
	}
	
	/**********************************/
	/** AFFICHER LES BORDERS EN HAUT **/
	/**********************************/
	public void afficherTopBorder() {
		int new_x = 24;
		int i = 0;
		
		for(i=0; i<2; i++) {
			JLabel topBorder = new JLabel("");
			topBorder.setIcon(new ImageIcon("images/strong_opaque-border-top.png"));
			topBorder.setBounds(Math.abs(x - (x-new_x)), 0, Math.abs(width - 30), Math.abs(height - 454));
			this.add(topBorder);
			
			new_x += 274;
		}
	}
	
	/*********************************/
	/** AFFICHER LES BORDERS EN BAS **/
	/*********************************/
	public void afficherBottomBorder() {
		int new_x = 24;
		int i = 0;
		
		for(i=0; i<2; i++) {
			JLabel bottomBorder = new JLabel("");
			bottomBorder.setIcon(new ImageIcon("images/strong_opaque-border_bottom.png"));
			bottomBorder.setBounds(Math.abs(x - (x-new_x)), Math.abs(height - 25), Math.abs(width - 30), Math.abs(height - 451));
			this.add(bottomBorder);
			
			new_x += 274;
		}
	}
	
	/******************************/
	/** AFFICHAGE DU LOGO DU JEU **/
	/******************************/
	public void afficherLogoJeu() {
		JLabel labelLogo = new JLabel("");
		labelLogo.setIcon(new ImageIcon("images/wesnoth-icon.png"));
		labelLogo.setBounds(Math.abs(width/2 - 60), 0, Math.abs(width - 464), Math.abs(height - 362));
		this.add(labelLogo);
	}

}
