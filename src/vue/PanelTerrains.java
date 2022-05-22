package vue;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;

import modele.Camera;
import modele.Colline;
import modele.EauProfonde;
import modele.Foret;
import modele.Glacier;
import modele.Hexagone;
import modele.Joueur;
import modele.Ordinateur;
import modele.Forteresse;
import modele.Soldat;
import modele.Terrain;

/*
 * La classe PanelTerrains permet d'afficher toutes les pièces du plateau : les terrains et les soldats
 */

@SuppressWarnings("serial")
public class PanelTerrains extends JLayeredPane {

	private Soldat soldatSelec, ancienSoldatSelec;
	private JLabel labelSoldatSelec;
	private ArrayList<JLabel> labelsSoldats;
	private ArrayList<JProgressBar> progressBarSoldats;
	private Camera camera;
	private JScrollPane scrollPane;
	private ArrayList<Terrain> terrains;
	private ArrayList<Soldat> soldats;
	private Map<Integer, JLabel> labelsHexagones;
	private PanelInfosSoldat panelInfosSoldat;
	private PanelInfosJoueur panelInfosJoueur;
	private JLabel labelBonusDef;
	private Guide guide;
	private Joueur tourJoueur;
	private int indTourJoueur;
	private Hexagone hexagoneSelected;
	private ArrayList<JLabel> labelsHexagonesBrouillard;
	private MiniMap minimap;
	private int xBoutonFinirTour;
	private int yBoutonFinirTour;
	
	public PanelTerrains(Joueur tourJoueur, SoldatVue soldatVue, PanelInfosSoldat panelInfosSoldat, PanelInfosJoueur panelInfosJoueur, Guide guide, MiniMap minimap, int widthPlateau, int heightPlateau, int xBoutonFinirTour, int yBoutonFinirTour) {
		// Définition des données du panel
		this.setLayout(null);
		this.setVisible(true);
		this.setPreferredSize(new Dimension(0, 0));

		// Guide
		this.guide = guide;

		// Récupération du joueur commencant la partie
		this.tourJoueur = tourJoueur;

		// Création du HashMap qui contiendra les couples identifiant des hexagones et le label correspondant
		this.labelsHexagones = new HashMap<Integer, JLabel>();

		// Création d'une liste de labels brouillard qui contiendra tous les labels brouillard
		this.labelsHexagonesBrouillard = new ArrayList<JLabel>();

		// Création d'une liste de progress bar pour pouvoir consulter les points de vies des soldats
		this.progressBarSoldats = new ArrayList<JProgressBar>();

		// On affecte les coordonnées du bouton finir tour
		this.xBoutonFinirTour = xBoutonFinirTour;
		this.yBoutonFinirTour = yBoutonFinirTour;
		
		// Création d'une liste de terrains
		this.terrains = new ArrayList<Terrain>();

		// Création d'un terrain forêt
		int xImage = 0, yImage = 0, x = 0, y = 0;
		Foret terrainForet = new Foret(x, y);
		this.terrains.add(terrainForet);

		// Création des hexagones du terrain forêt
		ArrayList<Hexagone> hexagonesForet;
		hexagonesForet = ajouterTerrain(12, 9, xImage, yImage, terrainForet, 0);
		terrainForet.ajouterHexagones(hexagonesForet);

		// Création d'un terrain glacier
		x = 624; y = 0;
		Glacier terrainGlacier = new Glacier(x, y);
		this.terrains.add(terrainGlacier);

		// Création des hexagones du terrain glacier
		ArrayList<Hexagone> hexagonesGlacier;
		hexagonesGlacier = ajouterTerrain(12, 9, xImage, yImage, terrainGlacier, 0);
		terrainGlacier.ajouterHexagones(hexagonesGlacier);

		// Création d'un terrain colline
		x = 0; y = 648;
		Colline terrainColline = new Colline(x, y);
		this.terrains.add(terrainColline);

		// Création des hexagones du colline
		ArrayList<Hexagone> hexagonesColline;
		hexagonesColline = ajouterTerrain(12, 9, xImage, yImage, terrainColline, 0);
		terrainColline.ajouterHexagones(hexagonesColline);

		// Création d'un terrain montagne
		x = 624; y = 648;
		Forteresse terrainMontagne = new Forteresse(x, y);
		this.terrains.add(terrainMontagne);	

		// Création des hexagones du terrain montagne
		ArrayList<Hexagone> hexagonesMontagne;
		hexagonesMontagne = ajouterTerrain(12, 9, xImage, yImage, terrainMontagne, 0);
		terrainMontagne.ajouterHexagones(hexagonesMontagne);

		// Création d'un terrain eau profonde
		x = 0; y = 1294;
		EauProfonde terrainEauProfonde = new EauProfonde(x, y);
		this.terrains.add(terrainEauProfonde);	

		// Création des hexagones du terrain eau profonde
		ArrayList<Hexagone> hexagonesEauProfonde;
		hexagonesEauProfonde = ajouterTerrain(24, 5, xImage, yImage, terrainEauProfonde, 0);
		terrainEauProfonde.ajouterHexagones(hexagonesEauProfonde);

		// Récupération de la liste des soldats créés
		this.soldats = soldatVue.getSoldats();

		// Récupération de la liste des labels correspondant aux soldats créés
		this.labelsSoldats = soldatVue.getLabelsSoldats();

		// Récupération du panel permettant d'afficher les informations des soldats
		this.panelInfosSoldat = panelInfosSoldat;

		// Récupération du panel permettant d'afficher les informations du joueur
		this.panelInfosJoueur = panelInfosJoueur;

		//Récupération de minimap
		this.minimap = minimap;

		// Ajout des labels soldat au panel
		for (int i = 0; i<this.labelsSoldats.size(); i++) {
			JLabel labelSoldat = this.labelsSoldats.get(i);
			Soldat soldat = this.soldats.get(i);
			this.add(labelSoldat, JLayeredPane.MODAL_LAYER);
			Hexagone hexagone = getHexagone(soldat.getAbscisse(), soldat.getOrdonnees());
			hexagone.addInHexagone(soldat);
			JProgressBar progressBar = new JProgressBar();
			progressBar.setForeground(Color.GREEN);
			progressBar.setOpaque(true);
			progressBar.setValue(soldat.getPv());
			progressBar.setOrientation(SwingConstants.VERTICAL);
			progressBar.setBounds(labelSoldat.getX()+10, labelSoldat.getY()+10, 6, 44);
			progressBar.setName(labelSoldat.getName());
			this.progressBarSoldats.add(progressBar);
			this.add(progressBar, JLayeredPane.DRAG_LAYER);
		}

		// Création du scroll pane contenant le panel
		this.scrollPane = new JScrollPane(this);
		this.scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		this.scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		this.scrollPane.setBounds(10,13,widthPlateau,heightPlateau);
		this.scrollPane.setPreferredSize(this.getPreferredSize());
		this.scrollPane.getHorizontalScrollBar().setValue(1);
		this.scrollPane.getVerticalScrollBar().setValue(1);

		// Création d'une caméra
		this.camera = new Camera(0,0);

		// Centrer la caméra sur la position actuelle sur le joueur
		modifierCameraJoueur();
		// Mettre à jour l'image des hexagones sous chaque label des soldats
		mettreAjourHexagonesSoldats();

		this.panelInfosJoueur.getNomJoueur().setText(this.tourJoueur.getNomJoueur());
		this.panelInfosJoueur.getScore().setText(String.valueOf((Integer)this.tourJoueur.getScore()));
		this.panelInfosJoueur.getNombreSoldat().setText(String.valueOf((Integer)this.tourJoueur.getNombreSoldat()));

		creerLabelsBrouillard();
	}

	/*
	 * Cette fonction permet de mettre à jour l'affichage des soldats
	 * sur le plateau, en fonction du joueur donc c'est le tour
	 */

	public void mettreAjourHexagonesSoldats() {
		for (int i=0; i<this.soldats.size(); i++) {
			JLabel labelSoldat = this.labelsSoldats.get(i);
			Soldat soldat = this.soldats.get(i);

			Hexagone hexagone = getHexagone(soldat);
			ImageIcon bordure = new ImageIcon(imageAafficher(soldat));  
			JLabel label = getLabel(hexagone.getId());
			label.setIcon(bordure);

			for (MouseListener mouseL : labelSoldat.getMouseListeners()) {
				labelSoldat.removeMouseListener(mouseL);
			}

			labelSoldat.addMouseListener(new MouseLabelSoldat(soldat, labelSoldat, hexagone));
		}
	}

	/*
	 * Fonction pour vérfier les possiblités d'attaquer
	 * on peut attaquer seulement les 6 hexagones autour du soldat
	 */
	public boolean possibiliteAttaque(Hexagone amis, Hexagone ennemi) {
		boolean result = false;
		int intervalleAbscisse = 52;
		int intervalleOrdonnee1 = 36;
		int intervalleOrdonnee2 = 34;
		int abscisseAmis = amis.getAbscisse();
		int abscisseEnnemi = ennemi.getAbscisse();
		int ordonneeAmis = amis.getOrdonnees();
		int ordonneeEnnemi = ennemi.getOrdonnees();
		if(
				//Haut gauche
				(abscisseAmis - abscisseEnnemi == intervalleAbscisse && (ordonneeAmis - ordonneeEnnemi == intervalleOrdonnee1 || ordonneeAmis - ordonneeEnnemi == intervalleOrdonnee2))
				||
				//Bas gauche
				(abscisseAmis - abscisseEnnemi == intervalleAbscisse && (ordonneeAmis + intervalleOrdonnee1 == ordonneeEnnemi || ordonneeAmis + intervalleOrdonnee2 == ordonneeEnnemi))
				||
				//Haut
				(abscisseAmis == abscisseEnnemi && (ordonneeAmis - ordonneeEnnemi == intervalleOrdonnee1 + intervalleOrdonnee1 || ordonneeAmis - ordonneeEnnemi == intervalleOrdonnee2 + intervalleOrdonnee2 || ordonneeAmis - ordonneeEnnemi == intervalleOrdonnee1 + intervalleOrdonnee2))
				||
				//Bas
				(abscisseAmis == abscisseEnnemi && (ordonneeAmis + intervalleOrdonnee1 + intervalleOrdonnee1 == ordonneeEnnemi || ordonneeAmis + intervalleOrdonnee2 + intervalleOrdonnee2 == ordonneeEnnemi || ordonneeAmis + intervalleOrdonnee1 + intervalleOrdonnee2 == ordonneeEnnemi))
				||
				//Haut droite
				(abscisseAmis + intervalleAbscisse == abscisseEnnemi && (ordonneeAmis - ordonneeEnnemi == intervalleOrdonnee1 || ordonneeAmis - ordonneeEnnemi == intervalleOrdonnee2))
				||
				//Bas droite
				(abscisseAmis + intervalleAbscisse == abscisseEnnemi && (ordonneeAmis + intervalleOrdonnee1 == ordonneeEnnemi || ordonneeAmis + intervalleOrdonnee2 == ordonneeEnnemi))
				) {
			result = true;
		}
		return result;
	}

	/*
	 * Fonction permet d'attaquer le soldat ennemi avec l'animation
	 * Tuer un soldat
	 */
	public void tuersoldat(Hexagone hexagone, int degats) {
		if (hexagone != null) {
			Soldat tue = hexagone.getUnits().get(0);

			ImageIcon feu = new ImageIcon(new ImageIcon("images/feux.gif").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));  
			JLabel labelSoldatEnnemi = getLabel(hexagone.getId());
			labelSoldatEnnemi.setIcon(feu);

			JLabel labelDegats = new JLabel(Integer.toString(degats));
			labelDegats.setBounds(labelSoldatEnnemi.getX()+25, labelSoldatEnnemi.getY()-60, 100, 100);
			labelDegats.setForeground(Color.red);
			labelDegats.setFont(new Font("Arial", Font.BOLD, 16));
			labelDegats.setVisible(true);
			this.add(labelDegats, JLayeredPane.DRAG_LAYER);

			JProgressBar progressBarSoldatEnnemi = chercherProgressBar(tue);
			progressBarSoldatEnnemi.setValue(tue.getPv());
			if (tue.getPv() <= 50 && tue.getPv() > 30) {
				progressBarSoldatEnnemi.setForeground(Color.orange);
			}

			if (tue.getPv() <= 30) {
				progressBarSoldatEnnemi.setForeground(Color.red);
			}

			TimerTask task = new TimerTask() {
				public void run() {
					if(tue.getPv() <= 0)
					{
						tue.setPv(0);
						tue.setKo(true);
						hexagone.removeFromHexagone(tue);
						labelSoldatEnnemi.setIcon(new ImageIcon("images/hexagones/hexagone3.png"));
						JLabel lsoldat = chercherLabelSoldat(tue);
						lsoldat.setVisible(false);
						labelsSoldats.remove(lsoldat);
						soldats.remove(tue);
						remove(lsoldat);
						progressBarSoldatEnnemi.setVisible(false);
						progressBarSoldats.remove(progressBarSoldatEnnemi);
						remove(progressBarSoldatEnnemi);
						tourJoueur.ajouterSoldatTue(tue);
						//incrementer le score
						tourJoueur.setScore(tourJoueur.getScore()+10);
						panelInfosJoueur.getScore().setText(String.valueOf((Integer)tourJoueur.getScore()));
					}
					else {
						labelSoldatEnnemi.setIcon(new ImageIcon(imageAafficher(tue)));
					}
					labelDegats.setVisible(false);
					remove(labelDegats);
					revalidate();
				}
			};
			Timer timer = new Timer("Timer");
			long delay = 1000L;
			timer.schedule(task, delay);
		}
	}

	/*
	 * Cette fonction permet de diminuer les points de vie d'un soldat attaqué
	 */

	public int diminuerpointdeviesoldat(Hexagone selected, Hexagone ennemi) {
		Random random = new Random();
		Soldat s1 = selected.getUnits().get(0);
		Soldat s2 = ennemi.getUnits().get(0);
		int bd = s2.getDefense();
		s2.setDefense(bd+(bd*getBonusDep(ennemi.getTypeTerrain())));
		int max = 0;
		if(s1.getAttaque() > s2.getDefense())
		{
			max = (s1.getAttaque() - s2.getDefense()) - 1;
		}
		else if(s2.getDefense() > s1.getAttaque())
		{
			max = (s2.getDefense() - s1.getAttaque()) - 1;
		}
		int degats = random.nextInt(max);
		int value = ennemi.getUnits().get(0).getPv() - degats;
		s2.setPv(value);
		return degats;
	}

	/*
	 * Cette fonction permet de retourner l'image "allié" ou "ennemi"
	 * en fonction du joueur dont c'est le tour et du soldat en paramètres
	 */

	public String imageAafficher(Soldat soldat) {
		String image;
		image = "images/hexagones/hexagone2.png";

		if (tourJoueur.soldatExiste(soldat)) {
			image = "images/hexagones/hexagone4.png";
		}

		return image;
	}

	/*
	 * Cette fonction permet de mettre à jour le soldat
	 * sélectionné par le joueur
	 */

	public void mettreAjourSoldatSelec() {
		if (this.ancienSoldatSelec != null) {
			Hexagone hexagoneAncienSoldat = getHexagone(this.ancienSoldatSelec);
			ImageIcon bordure = new ImageIcon(imageAafficher(this.ancienSoldatSelec));  
			JLabel label = getLabel(hexagoneAncienSoldat.getId());
			label.setIcon(bordure);
			this.ancienSoldatSelec = null;
		}

		afficherImageSelec(false, this.soldatSelec.getAbscisse(), this.soldatSelec.getOrdonnees());
		this.minimap.setSoldatSelec(this.soldatSelec);
		this.minimap.rafraichirMiniSoldats();
	}

	/*
	 * Cette fonction permet d'afficher l'hexagone "sélection" sur un hexagone
	 */

	public void afficherImageSelec(boolean deplacement, int x, int y) {
		Hexagone hexagone = getHexagone(x, y);
		if (hexagone != null) {
			ImageIcon bordure = new ImageIcon("images/hexagones/hexagone6.png");  
			JLabel label = getLabel(hexagone.getId());
			label.setIcon(bordure);
			if (deplacement) {
				this.soldatSelec.setAbscisse(hexagone.getAbscisse());
				this.soldatSelec.setOrdonnees(hexagone.getOrdonnees());
				hexagone.addInHexagone(this.soldatSelec);
			}
		}
	}

	/*
	 * Cette fonction permet d'effacer l'hexagone "sélection" sur un hexagone
	 */

	public void effacerImageSelec(int x, int y) {
		Hexagone hexagone = getHexagone(x, y);
		boolean effacerHexSelec = true;
		if (hexagone != null) {
			ImageIcon bordure = new ImageIcon("images/hexagones/hexagone3.png");  
			JLabel label = getLabel(hexagone.getId());
			if (this.soldatSelec != null) {
				Hexagone hexagoneSoldat = getHexagone(this.soldatSelec);
				JLabel labelHexagoneSoldat = getLabel(hexagoneSoldat.getId());
				if (label == labelHexagoneSoldat) {
					effacerHexSelec = false;
				}
			}
			if (effacerHexSelec) label.setIcon(bordure);
		}
	}

	/*
	 * Cette fonction permet de mettre à jour l'image des hexagones
	 * sur lesquelles les soldats se déplacent (on remet leur image initiale)
	 */

	public void updateSoldatHexagone() {
		Hexagone ancienHexagone = getHexagone(this.soldatSelec);
		if (ancienHexagone != null) {
			ancienHexagone.removeFromHexagone(this.soldatSelec);
			ImageIcon ancienneBordure;
			ArrayList<Soldat> soldats = ancienHexagone.getUnits();
			if (soldats.isEmpty()) {
				ancienneBordure = new ImageIcon("images/hexagones/hexagone3.png");  
			}
			else {
				ancienneBordure = new ImageIcon("images/hexagones/hexagone4.png");
			}
			JLabel labelAncienneBordure = getLabel(ancienHexagone.getId());
			labelAncienneBordure.setIcon(ancienneBordure);
		}
		afficherImageSelec(true, this.soldatSelec.getAbscisse(), this.soldatSelec.getOrdonnees());
		this.minimap.rafraichirMiniSoldats();
	}

	/*
	 * Cette fonction permet d'ajouter des hexagones à un terrain
	 */

	public ArrayList<Hexagone> ajouterTerrain(int nbrHexagoneX, int nbrHexagoneY, int xImage, int yImage, Terrain terrain, int ordre) {
		int x, y, xBordure, yBordure, oldY;
		int i = 0, j = 0;

		x = terrain.getAbscisse();
		y = terrain.getOrdonnees();
		oldY = y;

		// Affichage de l'image du terrain
		ImageIcon imageIconTerrain = new ImageIcon(terrain.getImage());
		JLabel label = new JLabel(imageIconTerrain);
		label.setBounds(x, y, imageIconTerrain.getIconWidth(), imageIconTerrain.getIconHeight());
		this.add(label, JLayeredPane.DEFAULT_LAYER);

		ArrayList<Hexagone> hexagones = new ArrayList<Hexagone>();

		for (i=0; i<nbrHexagoneX; i++) {
			yImage = 0;
			y = oldY;

			xBordure = x;
			yBordure = y;


			if (ordre%2 != 0) {
				yBordure += 36;
			}

			for (j=0; j<nbrHexagoneY; j++) {
				Hexagone hexagone = new Hexagone(xBordure, yBordure, terrain.getTypeTerrain());
				hexagones.add(hexagone);

				// On affiche le contour de l'hexagone
				ImageIcon imageBordure = new ImageIcon("images/hexagones/hexagone3.png");
				JLabel labelBordure = new JLabel(imageBordure);
				labelBordure.setBounds(xBordure, yBordure, imageBordure.getIconWidth(), imageBordure.getIconHeight());

				// On ajoute un tooltip à l'hexagone
				ImageIcon imageToolTip = new ImageIcon("images/button.png");
				Border matteborder = BorderFactory.createMatteBorder(1, 1, 3, 1, imageToolTip);
				String titre = "Type terrain : " + labelBordure.getX() + " " + labelBordure.getY() + terrain.getTypeTerrain() + "\n Bonus défense : " + terrain.getBonusDefense() + "\n Point déplacement : " + terrain.getPointDeplacement();
				UIManager.put("ToolTip.background", Color.decode("#0B2161"));
				UIManager.put("ToolTip.border", new BorderUIResource(matteborder));
				UIManager.put("ToolTip.foreground", Color.decode("#B18904"));
				labelBordure.setToolTipText(titre);

				labelBordure.addMouseListener(new MouseHexagone(labelBordure));

				this.add(labelBordure, JLayeredPane.PALETTE_LAYER);

				labelsHexagones.put(hexagone.getId(), labelBordure);

				yImage += 73;
				y += 72;
				yBordure += 72;
			}
			xImage += 52;
			x += 52;
			ordre++;
		}
		this.setPreferredSize(new Dimension(this.getWidth() + (53 * nbrHexagoneX), this.getHeight() + (329 * nbrHexagoneY)));
		return hexagones;
	}

	/*
	 * Cette fonction permet de récupérer un hexagone à partir de cordoonnées 
	 */

	public Hexagone getHexagone(int x, int y) {
		Hexagone hexagone = null;
		for (Terrain terrain : this.terrains) {
			if (terrain.getHexagone(x, y).size() > 0) {
				hexagone = terrain.getHexagone(x, y).get(0);
			}
		}
		return hexagone;
	}

	/*
	 * Cette fonction permet de récupérer un hexagone à partir d'un soldat
	 */

	public Hexagone getHexagone(Soldat soldat) {
		Hexagone hexagone = null;
		for (Terrain terrain : this.terrains) {
			if (terrain.getHexagone(soldat).size() > 0) {
				hexagone = terrain.getHexagone(soldat).get(0);
			}
		}
		return hexagone;
	}

	/*
	 * Cette fonction permet de récupérer un label correspondant à un hexagone
	 * à partir de son identifiant
	 */

	public JLabel getLabel(int id) {
		JLabel labelAretourner = this.labelsHexagones.get(id);
		return labelAretourner;
	}

	/*
	 * Cette fonction permet de modifier les cordoonnées du scrollPane qui correspond à la caméra
	 */

	public void modifierCoordonneesCamera() {
		this.scrollPane.getHorizontalScrollBar().setValue(this.camera.getOffX());
		this.scrollPane.getVerticalScrollBar().setValue(this.camera.getOffY());
	}

	/*
	 * Replacer la caméra sur le joueur actif
	 */

	public void modifierCameraJoueur() {
		int ind = (int) (Math.random() * (this.tourJoueur.getSoldatList().size() - 0));
		Soldat soldat = this.tourJoueur.getSoldatList().get(ind);
		this.camera.update(soldat.getAbscisse(), soldat.getOrdonnees(), this.scrollPane);
		modifierCoordonneesCamera();
		try {
			minimap.rafraichirCadreCamera();
		}catch(Exception e) {
		}
	}

	/*
	 * Cette fonction permet de modifier le tour du joueur
	 */

	public void setTourJoueur(Joueur tourJoueur) {
		effacerBrouillard();
		afficherBrouillard();

		this.tourJoueur = tourJoueur;
		this.minimap.setTourJoueur(tourJoueur);
		this.minimap.rafraichirMiniSoldats();
		// Centrer la caméra sur la position actuelle sur le joueur
		modifierCameraJoueur();
		// Mettre à jour l'image des hexagones sous chaque label des soldats
		mettreAjourHexagonesSoldats();
		// Initialisation des soldats sélectionnés
		this.soldatSelec = null;
		this.labelSoldatSelec = null;
		this.panelInfosJoueur.getNomJoueur().setText(this.tourJoueur.getNomJoueur());
		this.panelInfosJoueur.getScore().setText(String.valueOf((Integer)this.tourJoueur.getScore()));
		this.panelInfosJoueur.getNombreSoldat().setText(String.valueOf((Integer)this.tourJoueur.getNombreSoldat()));

		for (Soldat s : this.tourJoueur.getSoldatList()) {
			s.setDeplacementRealises(0);
			s.setBloque(false);
		}

		if (this.tourJoueur.getClass().getName().equals("modele.Ordinateur")) {	
			try {
				Robot robot = new Robot();
				Ordinateur ordinateur = new Ordinateur(this.tourJoueur.getNomJoueur(), this.tourJoueur.getSoldatList(), this.tourJoueur.getScore(), this.tourJoueur.getImage(), this.tourJoueur.getAdversaires());

				Soldat soldat = ordinateur.choisirSoldat();

				Hexagone hexagone = getHexagone(soldat);

				JLabel label = getLabel(hexagone.getId());

				//do {
				TimerTask task = new TimerTask() {
					public void run() {
						Point p;
						int x, y;
						p = label.getLocationOnScreen();
						x = p.x;
						y = p.y;

						// Sélection robot
						robot.mouseMove(x, y);
						robot.setAutoDelay(500);
						robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						robot.setAutoDelay(500);
						robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

						ArrayList<JLabel> labelsHexagonesPossibles = recupereHexagoneVisionSoldat(soldat);

						JLabel labelHexDestination = ordinateur.choisirHexagone(labelsHexagonesPossibles);

						p = labelHexDestination.getLocationOnScreen();

						x = p.x;
						y = p.y;

						// Déplacement robot
						robot.mouseMove(x, y);
						robot.setAutoDelay(500);
						robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						robot.setAutoDelay(500);
						robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						robot.setAutoDelay(500);

						// Attaque robot si possible

						// Finir tour
						robot.mouseMove(xBoutonFinirTour+50, yBoutonFinirTour+40);
						robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					}
				};
				Timer timer = new Timer("Timer");
				long delay = 1000L;
				timer.schedule(task, delay);	

				//} while (!this.tourJoueur.allSoldatsBloques());

			} catch (AWTException ex) {
				ex.printStackTrace();
			}
		}
	}

	public ArrayList<JLabel> recupereHexagoneVisionSoldatEnnemi(Soldat soldat) {
		ArrayList<JLabel> labelsHexagonesVisions = new ArrayList<JLabel>();
		for (int i=0; i<this.terrains.size(); i++) {
			Terrain terrain = terrains.get(i);
			for (int j=0; j<terrain.getHexagones().size(); j++) {
				Hexagone hexagone = terrain.getHexagones().get(j);

				if (!hexagone.getUnits().isEmpty() && !tourJoueur.getSoldatList().contains(hexagone.getUnits().get(0))) {
					JLabel labelHexagone = getLabel(hexagone.getId());

					Point p = labelHexagone.getLocationOnScreen();
					if (p.x >= 0 && p.y >= 0 && p.x <= this.scrollPane.getWidth() && p.x >= 70 && p.y <= this.scrollPane.getHeight() && p.y >= 60) {
						int nbrHexagones = soldat.getVision()+1;
						int xClic = labelHexagone.getX();
						int yClic = labelHexagone.getY();

						if (xClic  > soldat.getAbscisse() && yClic > soldat.getOrdonnees()) {
							nbrHexagones = (xClic-soldat.getAbscisse()) / labelHexagone.getWidth();
						}
						else if (xClic  < soldat.getAbscisse() && yClic > soldat.getOrdonnees()) {
							nbrHexagones = (soldat.getAbscisse()-xClic) / labelHexagone.getWidth();
						}
						else if (xClic  > soldat.getAbscisse() && yClic < soldat.getOrdonnees()) {
							nbrHexagones = (xClic-soldat.getAbscisse()) / labelHexagone.getWidth();
						}
						else if (xClic  < soldat.getAbscisse() && yClic < soldat.getOrdonnees()) {
							nbrHexagones = (soldat.getAbscisse()-xClic) / labelHexagone.getWidth();
						}
						else if (yClic < soldat.getOrdonnees()) {
							nbrHexagones = (soldat.getOrdonnees()-yClic) / labelHexagone.getHeight();
						}
						else if (yClic > soldat.getOrdonnees()) {
							nbrHexagones = (yClic-soldat.getOrdonnees()) / labelHexagone.getHeight();
						}

						nbrHexagones++;

						if (nbrHexagones!= 0 && nbrHexagones <= (soldat.getDeplacement() + terrain.getPointDeplacement()) && nbrHexagones <= soldat.getVision()) {
							labelsHexagonesVisions.add(labelHexagone);
						}
					}

				}

			}
		}

		return labelsHexagonesVisions;
	}

	public ArrayList<JLabel> recupereHexagoneVisionSoldat(Soldat soldat) {
		ArrayList<JLabel> labelsHexagonesVisions = new ArrayList<JLabel>();
		for (int i=0; i<this.terrains.size(); i++) {
			Terrain terrain = terrains.get(i);
			for (int j=0; j<terrain.getHexagones().size(); j++) {
				Hexagone hexagone = terrain.getHexagones().get(j);

				if (hexagone.getUnits().size() == 0) {
					JLabel labelHexagone = getLabel(hexagone.getId());

					Point p = labelHexagone.getLocationOnScreen();
					if (p.x >= 0 && p.y >= 0 && p.x <= this.scrollPane.getWidth() && p.x >= 70 && p.y <= this.scrollPane.getHeight() && p.y >= 60) {
						int nbrHexagones = soldat.getVision()+1;
						int xClic = labelHexagone.getX();
						int yClic = labelHexagone.getY();

						if (xClic  > soldat.getAbscisse() && yClic > soldat.getOrdonnees()) {
							nbrHexagones = (xClic-soldat.getAbscisse()) / labelHexagone.getWidth();
						}
						else if (xClic  < soldat.getAbscisse() && yClic > soldat.getOrdonnees()) {
							nbrHexagones = (soldat.getAbscisse()-xClic) / labelHexagone.getWidth();
						}
						else if (xClic  > soldat.getAbscisse() && yClic < soldat.getOrdonnees()) {
							nbrHexagones = (xClic-soldat.getAbscisse()) / labelHexagone.getWidth();
						}
						else if (xClic  < soldat.getAbscisse() && yClic < soldat.getOrdonnees()) {
							nbrHexagones = (soldat.getAbscisse()-xClic) / labelHexagone.getWidth();
						}
						else if (yClic < soldat.getOrdonnees()) {
							nbrHexagones = (soldat.getOrdonnees()-yClic) / labelHexagone.getHeight();
						}
						else if (yClic > soldat.getOrdonnees()) {
							nbrHexagones = (yClic-soldat.getOrdonnees()) / labelHexagone.getHeight();
						}

						nbrHexagones++;

						if (nbrHexagones!= 0 && nbrHexagones <= (soldat.getDeplacement() + terrain.getPointDeplacement()) && nbrHexagones <= soldat.getVision()) {
							labelsHexagonesVisions.add(labelHexagone);
						}
					}

				}

			}
		}

		return labelsHexagonesVisions;
	}

	/*
	 * Récupérer les points de déplacement d'un terrain à partir de son nom 
	 */

	public int getBonusDep(String nomTerrain) {
		List<Terrain> terrainsNom = this.terrains.stream().filter(x -> x.getTypeTerrain().equals(nomTerrain)).collect(Collectors.toList());
		return terrainsNom.get(0).getPointDeplacement();
	}

	/*
	 * Récupérer les bonus de défense d'un terrain à partir de son nom 
	 */

	public int getBonusDef(String nomTerrain) {
		List<Terrain> terrainsNom = this.terrains.stream().filter(x -> x.getTypeTerrain().equals(nomTerrain)).collect(Collectors.toList());
		return terrainsNom.get(0).getBonusDefense();
	}

	/*
	 * Cette fonction permet supprimer le brouillard des hexagones qui appartient de zone de vision des soldats
	 */

	public void effacerBrouillardSoldatSelec()
	{
		ArrayList<JLabel> recupereHexagoneVisionSoldat=new ArrayList<JLabel>();
		recupereHexagoneVisionSoldat=recupereHexagoneVisionSoldat(soldatSelec);
		int i;
		for(i=0;i<recupereHexagoneVisionSoldat.size();i++)
		{
			effaceImageFog(recupereHexagoneVisionSoldat.get(i).getX(),recupereHexagoneVisionSoldat.get(i).getY());
		}
		effaceImageFog(labelSoldatSelec.getX(),labelSoldatSelec.getY());
	}

	/*
	 * Cette fonction permet de supprime le brouillard sur un hexagone
	 */

	public void effaceImageFog(int x, int y) {
		List<JLabel> chercheLabel = this.labelsHexagonesBrouillard.stream().filter(l -> l.getX() == x && l.getY() == y).collect(Collectors.toList());
		if (chercheLabel.size() > 0) {
			JLabel fogOfWar = chercheLabel.get(0);
			fogOfWar.setVisible(false);
			Hexagone hexagone = getHexagone(x, y);
			JLabel label = getLabel(hexagone.getId());
			ajouterMouseListenerHexagone(label);
		}
	}

	/*
	 * Cette fonction permet d'afficher le Brouillard sur un hexagone
	 */
	public void afficherImageFog(int x, int y) {
		Hexagone hexagone = getHexagone(x, y);
		if (hexagone != null) {
			ImageIcon fog = new ImageIcon("images/hexagones/hexagone1.png");  
			JLabel label = getLabel(hexagone.getId());
			JLabel fogOfWar = new JLabel(fog);
			fogOfWar.setBounds(label.getX(), label.getY(), label.getWidth(), label.getHeight());
			retirerMouseListenerHexagone(label);
			this.add(fogOfWar, JLayeredPane.MODAL_LAYER);
			this.labelsHexagonesBrouillard.add(fogOfWar);
		}
	}

	/*
	 * Cette fonction permet de creer les labels brouillard sur tout le terrain
	 */

	public void creerLabelsBrouillard()
	{ 
		int i, j;
		for(i=0;i<terrains.size();i++)
		{
			for (j=0; j<terrains.get(i).getHexagones().size(); j++) {
				Hexagone hexagone = terrains.get(i).getHexagones().get(j);
				afficherImageFog(hexagone.getAbscisse(), hexagone.getOrdonnees());
			}
		}

		return;
	}

	/*
	 * Cette fonction permet d'afficher le brouillard sur tout le terrain
	 */

	public void afficherBrouillard()
	{ 
		int i;
		for(i=0;i<labelsHexagonesBrouillard.size();i++)
		{
			Hexagone hexagone = getHexagone(labelsHexagonesBrouillard.get(i).getX(), labelsHexagonesBrouillard.get(i).getY());
			labelsHexagonesBrouillard.get(i).setVisible(true);
			JLabel label = getLabel(hexagone.getId());
			retirerMouseListenerHexagone(label);
		}

		return;
	}


	/*
	 * Cette fonction permet supprime les brouillards sur tout le terrain
	 */

	public void effacerBrouillard()
	{
		int i;
		for(i=0;i<labelsHexagonesBrouillard.size();i++)
		{
			Hexagone hexagone = getHexagone(labelsHexagonesBrouillard.get(i).getX(), labelsHexagonesBrouillard.get(i).getY());
			labelsHexagonesBrouillard.get(i).setVisible(false);
			JLabel label = getLabel(hexagone.getId());
			ajouterMouseListenerHexagone(label);
		}
	}

	/*
	 * Cette fonction permet de retirer tous les mouse listener des labels hexagones et des labels soldats
	 */

	public void retirerMouseListenerHexagones() {
		int i, j;
		for(i=0;i<terrains.size();i++)
		{
			for (j=0; j<terrains.get(i).getHexagones().size(); j++) {
				Hexagone hexagone = terrains.get(i).getHexagones().get(j);
				JLabel label = getLabel(hexagone.getId());
				retirerMouseListenerHexagone(label);
			}
		}
		for (i=0; i<this.labelsSoldats.size(); i++) {
			retirerMouseListenerHexagone(this.labelsSoldats.get(i));
		}
	}

	/*
	 * Cette fonction permet de retirer tous les mouse listener d'un label hexagone
	 */

	public void retirerMouseListenerHexagone(JLabel labelHexagone) {
		for (MouseListener mouseL : labelHexagone.getMouseListeners()) {
			labelHexagone.removeMouseListener(mouseL);
		}
	}

	/*
	 * Cette fonction permet d'ajouter tous les mouse listener des labels hexagones et des labels soldats
	 */

	public void ajouterMouseListenerHexagones() {
		int i, j;
		for(i=0;i<terrains.size();i++)
		{
			for (j=0; j<terrains.get(i).getHexagones().size(); j++) {
				Hexagone hexagone = terrains.get(i).getHexagones().get(j);
				JLabel label = getLabel(hexagone.getId());
				ajouterMouseListenerHexagone(label);
			}
		}
		for (i=0; i<this.soldats.size(); i++) {
			JLabel labelSoldat = this.labelsSoldats.get(i);
			Soldat soldat = this.soldats.get(i);
			Hexagone hexagone = getHexagone(soldat);
			labelSoldat.addMouseListener(new MouseLabelSoldat(soldat, labelSoldat, hexagone));
		}
	}

	/*
	 * Cette fonction permet d'ajouter tous les mouse listener d'un label hexagone
	 */

	public void ajouterMouseListenerHexagone(JLabel labelHexagone) {
		labelHexagone.addMouseListener(new MouseHexagone(labelHexagone));
	}


	/*
	 * Cette fonction permet de chercher un label soldat à partir de l'id d'un soldat
	 */

	public JLabel chercherLabelSoldat(Soldat soldat) {
		List<JLabel> chercheLabel = new ArrayList<JLabel>();
		chercheLabel.addAll(this.labelsSoldats.stream().filter(x -> Integer.parseInt(x.getName()) == soldat.getId()).collect(Collectors.toList()));
		return chercheLabel.get(0);
	}

	/*
	 * Cette fonction permet de chercher une progress bar à partir de l'id d'un soldat
	 */

	public JProgressBar chercherProgressBar(Soldat soldat) {
		List<JProgressBar> chercheProgressBar = new ArrayList<JProgressBar>();
		chercheProgressBar.addAll(this.progressBarSoldats.stream().filter(x -> Integer.parseInt(x.getName()) == soldat.getId()).collect(Collectors.toList()));
		return chercheProgressBar.get(0);
	}

	public class MouseLabelSoldat extends MouseAdapter {

		private Soldat soldat;
		private JLabel labelSoldat;
		private Hexagone hexagone;

		public MouseLabelSoldat(Soldat soldat, JLabel labelSoldat, Hexagone hexagone) {
			this.soldat = soldat;
			this.labelSoldat = labelSoldat;
			this.hexagone = hexagone;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (tourJoueur.soldatExiste(soldat)) {
				panelInfosSoldat.afficherInfosSoldats(soldat);

				// On affiche la partie 1 du tutoriel
				if (guide.isGuideActive() && !guide.aValideCompetence(1)) {
					guide.afficherIndicationsDeplacement();
				}
				SwingUtilities.updateComponentTreeUI(guide);

				if (soldat != soldatSelec) {
					ancienSoldatSelec = soldatSelec;
				}

				labelSoldatSelec = labelSoldat;
				soldatSelec = soldat;
				effacerBrouillardSoldatSelec();

				mettreAjourSoldatSelec();
			}
			else {
				// On affiche la partie 5 du tutoriel
				if (guide.isGuideActive() && !guide.aValideCompetence(5)) {
					guide.afficherFinTuto();
				}
				boolean attaque = possibiliteAttaque(hexagoneSelected, hexagone);
				if(attaque)  {
					int degats = diminuerpointdeviesoldat(hexagoneSelected, hexagone);
					tuersoldat(hexagone, degats);
				}
			}
		}
	}

	public class MouseHexagone extends MouseAdapter {

		private JLabel labelBordure;

		public MouseHexagone(JLabel label) {
			this.labelBordure = label;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			afficherImageSelec(false, labelBordure.getX(), labelBordure.getY());
			Hexagone hexagoneClique = getHexagone(labelBordure.getX(), labelBordure.getY());
			int bonusDefense = getBonusDef(hexagoneClique.getTypeTerrain());
			labelBonusDef = new JLabel(Integer.toString(bonusDefense) + "%");
			labelBonusDef.setFont(new Font("Arial", Font.BOLD, 20));
			labelBonusDef.setForeground(new Color(231, 206, 54));
			labelBonusDef.setHorizontalAlignment(SwingConstants.CENTER);
			labelBonusDef.setBounds(labelBordure.getBounds());
			add(labelBonusDef, JLayeredPane.PALETTE_LAYER);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			TimerTask task = new TimerTask() {
				public void run() {
					if (guide.isGuideActive() && !guide.aValideCompetence(3)) {
						guide.afficherIndicationsDeplacement3();
					}
				}
			};
			Timer timer = new Timer("Timer");
			long delay = 1000L;
			timer.schedule(task, delay);
			effacerImageSelec(labelBordure.getX(), labelBordure.getY());
			remove(labelBonusDef);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// On affiche la partie 2 du tutoriel
			if (guide.isGuideActive() && !guide.aValideCompetence(2)) {
				guide.afficherIndicationsDeplacement2();
			}
			remove(labelBonusDef);

			Hexagone hexagone = getHexagone(labelBordure.getX(), labelBordure.getY());
			if (soldatSelec != null && !hexagone.contientEnnemi(tourJoueur)) {

				int nbrHexagones, nouveauX, nouveauY, xClic, yClic;
				nouveauX = soldatSelec.getAbscisse();
				nouveauY = soldatSelec.getOrdonnees();

				nbrHexagones = 0;
				xClic = labelBordure.getX();
				yClic = labelBordure.getY();

				if (xClic  > soldatSelec.getAbscisse() && yClic > soldatSelec.getOrdonnees()) {
					String imageDroite = soldatSelec.getImage();
					ImageIcon image = new ImageIcon(imageDroite);
					labelSoldatSelec.setIcon(image);
					nouveauX = soldatSelec.deplacementDroit(xClic - soldatSelec.getAbscisse());
					nouveauY = soldatSelec.deplacementBas(yClic - soldatSelec.getOrdonnees());
					nbrHexagones = (nouveauX-soldatSelec.getAbscisse()) / labelBordure.getWidth();
				}
				else if (xClic  < soldatSelec.getAbscisse() && yClic > soldatSelec.getOrdonnees()) {
					String imageGauche= soldatSelec.getImagePivotee();
					ImageIcon image = new ImageIcon(imageGauche);
					labelSoldatSelec.setIcon(image);
					nouveauX = soldatSelec.deplacementGauche(soldatSelec.getAbscisse() - xClic);
					nouveauY = soldatSelec.deplacementBas(yClic - soldatSelec.getOrdonnees());
					nbrHexagones = (soldatSelec.getAbscisse()-nouveauX) / labelBordure.getWidth();
				}
				else if (xClic  > soldatSelec.getAbscisse() && yClic < soldatSelec.getOrdonnees()) {
					String imageDroite = soldatSelec.getImage();
					ImageIcon image = new ImageIcon(imageDroite);
					labelSoldatSelec.setIcon(image);
					nouveauX = soldatSelec.deplacementDroit(xClic - soldatSelec.getAbscisse());
					nouveauY = soldatSelec.deplacementHaut(soldatSelec.getOrdonnees() - yClic);
					nbrHexagones = (nouveauX-soldatSelec.getAbscisse()) / labelBordure.getWidth();
				}
				else if (xClic  < soldatSelec.getAbscisse() && yClic < soldatSelec.getOrdonnees()) {
					String imageGauche= soldatSelec.getImagePivotee();
					ImageIcon image = new ImageIcon(imageGauche);
					labelSoldatSelec.setIcon(image);
					nouveauX = soldatSelec.deplacementGauche(soldatSelec.getAbscisse() - xClic);
					nouveauY = soldatSelec.deplacementHaut(soldatSelec.getOrdonnees() - yClic);
					nbrHexagones = (soldatSelec.getAbscisse()-nouveauX) / labelBordure.getWidth();
				}
				else if (yClic < soldatSelec.getOrdonnees()) {
					nouveauY = soldatSelec.deplacementHaut(soldatSelec.getOrdonnees() - yClic);
					nbrHexagones = (soldatSelec.getOrdonnees()-nouveauY) / labelBordure.getHeight();
				}
				else if (yClic > soldatSelec.getOrdonnees()) {
					nouveauY = soldatSelec.deplacementBas(yClic - soldatSelec.getOrdonnees());
					nbrHexagones = (nouveauY-soldatSelec.getOrdonnees()) / labelBordure.getHeight();
				}

				nbrHexagones += 1;

				Hexagone hexagoneClique = getHexagone(labelBordure.getX(), labelBordure.getY());
				int bonusDeplacement = getBonusDep(hexagoneClique.getTypeTerrain());

				// On vérifie si en se déplacant le soldat se retrouve à proximité d'un ennemi
				List<Hexagone> listeHex = new ArrayList<Hexagone>();
				for (int ind=0; ind<terrains.size(); ind++) {
					Terrain terrain = terrains.get(ind);
					for (int ind2=0; ind2<terrain.getHexagones().size(); ind2++) {
						Hexagone hex = terrain.getHexagones().get(ind2);
						if (hex.getUnits().size() > 0 && !(tourJoueur.soldatExiste(hex.getUnits().get(0))) && (hex.getAbscisse()-hexagoneClique.getAbscisse()) <= 54 && (hex.getAbscisse()-hexagoneClique.getAbscisse()) >= -54 && (hex.getOrdonnees()-hexagoneClique.getOrdonnees()) <= 72 && (hex.getOrdonnees()-hexagoneClique.getOrdonnees()) >= -72) {
							listeHex.add(hex);
						}

					}
				}
				if (!listeHex.isEmpty()) {
					if (guide.isGuideActive() && !guide.aValideCompetence(4)) {
						guide.afficherIndicationsCombat();
					}
				}

				soldatSelec.deplacementPossible(0, getWidth() - 50, 0, getHeight() - 70, nouveauX, nouveauY, nbrHexagones, bonusDeplacement);
				labelSoldatSelec.setLocation(soldatSelec.getAbscisse(), soldatSelec.getOrdonnees());
				JProgressBar progressBarSoldat = chercherProgressBar(soldatSelec);
				progressBarSoldat.setLocation(labelSoldatSelec.getX()+10, labelSoldatSelec.getY()+10);
				camera.update(soldatSelec.getAbscisse(), soldatSelec.getOrdonnees(), scrollPane);
				modifierCoordonneesCamera();
				updateSoldatHexagone();
				hexagoneSelected = hexagoneClique;

				effacerBrouillard();
				afficherBrouillard();
				effacerBrouillardSoldatSelec();
			}
		}
	}

	public Joueur getTourJoueur() {
		return tourJoueur;
	}

	public Soldat getSoldatSelec() {
		return soldatSelec;
	}

	public void setSoldatSelec(Soldat soldatSelec) {
		this.soldatSelec = soldatSelec;
	}

	public JLabel getLabelSoldatSelec() {
		return labelSoldatSelec;
	}

	public void setLabelSoldatSelec(JLabel labelSoldatSelec) {
		this.labelSoldatSelec = labelSoldatSelec;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public ArrayList<Terrain> getTerrains() {
		return terrains;
	}

	public void setTerrains(ArrayList<Terrain> terrains) {
		this.terrains = terrains;
	}

	public ArrayList<Soldat> getSoldats() {
		return soldats;
	}

	public void setSoldats(ArrayList<Soldat> soldats) {
		this.soldats = soldats;
	}

	public int getIndTourJoueur() {
		return indTourJoueur;
	}

	public void setIndTourJoueur(int indTourJoueur) {
		this.indTourJoueur = indTourJoueur;
	}

	public MiniMap getMinimap() {
		return minimap;
	}

	public void setMinimap(MiniMap minimap) {
		this.minimap = minimap;
	}
}
