package vue;

import java.awt.Container;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;

import modele.Camera;
import modele.Joueur;
import modele.Soldat;

@SuppressWarnings("serial")
public class MiniMap extends JLayeredPane{
	
	private ArrayList<Soldat> ListeSoldats;
	private ArrayList<JLabel> labelminiSoldats;
	private Joueur tourJoueur;
	private Soldat SoldatSelec;
	private JLabel cadre;
	private PanelTerrains panelT;
	private Camera camera;
	private int x = 0 ,y = 0;
	private MyMouseAdapter myMouseAdapter;
	
	
	public MiniMap(ArrayList<Joueur> joueurs, Joueur tourJoueur, SoldatVue soldatVue, PlateauVue vue, int xBounds, PanelTerrains panelT) {
		super();
		// Définition des données du panel
		this.setLayout(null);
		this.setVisible(true);
		this.setBounds(xBounds, 35, 124, 165);
		this.setOpaque(true);
		
		ImageIcon imageIconTerrain;
		JLabel label;
		
		//Récupération du Plateau vue
		this.panelT = panelT;
		
		//Affichage Mini terrain foret
		imageIconTerrain = new ImageIcon("images/minimap/miniterrains/miniforet.png");
		label = new JLabel(imageIconTerrain);
		label.setBounds(0, 0, imageIconTerrain.getIconWidth(), imageIconTerrain.getIconHeight());
		this.add(label, JLayeredPane.DEFAULT_LAYER);
		
		//Affichage Mini terrain glacier
		imageIconTerrain = new ImageIcon("images/minimap/miniterrains/miniglacier.png");
		label = new JLabel(imageIconTerrain);
		label.setBounds(62, 0, imageIconTerrain.getIconWidth(), imageIconTerrain.getIconHeight());
		this.add(label, JLayeredPane.DEFAULT_LAYER);
		
		//Affichage Mini terrain colline
		imageIconTerrain = new ImageIcon("images/minimap/miniterrains/minicolline.png");
		label = new JLabel(imageIconTerrain);
		label.setBounds(0, 65, imageIconTerrain.getIconWidth(), imageIconTerrain.getIconHeight());
		this.add(label, JLayeredPane.DEFAULT_LAYER);

		//Affichage Mini terrain forteresse
		imageIconTerrain = new ImageIcon("images/minimap/miniterrains/miniforteresse.png");
		label = new JLabel(imageIconTerrain);
		label.setBounds(62, 65, imageIconTerrain.getIconWidth(), imageIconTerrain.getIconHeight());
		this.add(label, JLayeredPane.DEFAULT_LAYER);	
		
		//Affichage Mini terrain eau profonde
		imageIconTerrain = new ImageIcon("images/minimap/miniterrains/minieauprofonde.png");
		label = new JLabel(imageIconTerrain);
		label.setBounds(0, 130, imageIconTerrain.getIconWidth(), imageIconTerrain.getIconHeight());
		this.add(label, JLayeredPane.DEFAULT_LAYER);
		
		//Initialistion de Soldat séléctioné
		this.SoldatSelec = null;
		
		//Récupération de tour de joueur
		this.tourJoueur = tourJoueur;
		
		////Récupération de la liste des soldats
		this.ListeSoldats = soldatVue.getSoldats();
		
		//Init de des jlabel soldat
		this.labelminiSoldats = new ArrayList<JLabel>() ;
		AfficherMiniSoldat();
		
		//Récupération de la caméra
		this.camera = this.panelT.getCamera();
		x =GetMiniOffX(camera.getOffX());
		y =GetMiniOffY(camera.getOffY());
		
		//Initialisation de cadre caméra
		ImageIcon imageCadre = new ImageIcon("images/minimap/cadre.png");
		this.cadre = new JLabel(imageCadre);
		this.cadre.setBounds(x, y, imageCadre.getIconWidth(), imageCadre.getIconHeight());
		this.add(this.cadre, JLayeredPane.MODAL_LAYER);
		
		//Ajout de Mouse Adapter pour déplacer la caméra
		myMouseAdapter = new MyMouseAdapter(this.panelT, this.cadre, this);
	    this.addMouseListener(myMouseAdapter);
	    this.addMouseMotionListener(myMouseAdapter);
		
	}
	

	public void AfficherMiniSoldat() {
		this.labelminiSoldats.clear();
		
			for(Soldat s : this.ListeSoldats) {
				ImageIcon imageIconSoldat = new ImageIcon(ImageAafficher(s,this.tourJoueur));
				JLabel labelSoldat = new JLabel(imageIconSoldat);
				labelSoldat.setBounds(GetMiniX(s), GetMiniY(s), imageIconSoldat.getIconWidth(), imageIconSoldat.getIconHeight());
				this.labelminiSoldats.add(labelSoldat);
				
				this.add(labelSoldat, JLayeredPane.MODAL_LAYER);	
			}
		
	}
	
	//Suppression des miniSoldat pour les re-afficher avec leurs nouveaux paramétres
	public void SupprimerMiniSoldat() {
		for (JLabel minisoldat : this.labelminiSoldats) {
			Container parent = minisoldat.getParent();
			parent.remove(minisoldat);
			parent.validate();
			parent.repaint();
		}
	}
	
	public void rafraichirMiniSoldats() {
		SupprimerMiniSoldat();
		AfficherMiniSoldat();
	}
	
	public void rafraichirCadreCamera() {
		x =GetMiniOffX(camera.getOffX());
		y =GetMiniOffY(camera.getOffY());
		Container parent = this.cadre.getParent();
		parent.remove(this.cadre);
		parent.validate();
		parent.repaint();
		ImageIcon imageCadre = new ImageIcon("images/minimap/cadre.png");
		this.cadre.setBounds(x, y, imageCadre.getIconWidth(), imageCadre.getIconHeight());
		this.add(this.cadre, JLayeredPane.MODAL_LAYER);
	}
	
	//Cette fonction permet de changer la couleur de pointeur de soldat sur le minimap selon le tour de joueur et le soldat selectionné  
	public String ImageAafficher(Soldat soldat, Joueur tourJoueur) {
		String image;
		
		image = "images/minimap/position/enemy.png";
		
		if (tourJoueur.soldatExiste(soldat)) {
			if (soldat == this.SoldatSelec){	
				
				image = "images/minimap/position/selected.png";
			}
			else{
				image = "images/minimap/position/ally.png";
			}
		}
		
		return image;
	}
	
	
	//Ces deux fonctions calcule et retourne des coordonnées réduites à partir des coordonnées réel d'un soldat
	private int GetMiniX(Soldat soldat) {
		int x = soldat.getAbscisse();
			return (x/10);
		
	}

	private int GetMiniY(Soldat soldat) {
		int y = soldat.getOrdonnees();
		return (y/10);
	}
	
	//Ces deux fonctions calcule et retourne les coordonnées réduites de la caméra pour s'adapter au minimap
	public int GetMiniOffX(int X) {
		int Xmax = 70, Xmin= 54;
		X =(X+300) /10;
		if (X > Xmax) {
			return Xmax - Xmin;
    	}else if (X < Xmin) {
    		return 0;
    	}else {
    		return X - Xmin;
    	}
	}
	
	public int GetMiniOffY(int Y) {
		int Ymax = 134, Ymin = 31;
		Y =(Y+300)/10;
		if (Y > Ymax) {
			return Ymax - Ymin;
    	}else if (Y < Ymin) {
    		return 0;
    	}else {
    		return Y - Ymin;
    	}
	}
	

	public void setSoldatSelec(Soldat soldatSelec) {
		SoldatSelec = soldatSelec;
	}
	public void setTourJoueur(Joueur tourJoueur) {
		this.tourJoueur = tourJoueur;
	}

	public void setListeSoldats(ArrayList<Soldat> listeSoldats) {
		ListeSoldats = listeSoldats;
	}
	
	//Classe pour gérer le déplacement de mouvement du cadre de caméra
    private class MyMouseAdapter extends MouseAdapter {
    	private PanelTerrains panelT;
    	private boolean dragging = false;
    	private JLabel border;
    	private MiniMap minimap;
    	private Container parent;
    	private JScrollPane scrollpane;
    	private int Xmax = 70, Xmin= 54, Ymax = 134, Ymin = 31, sX = 0, sY = 0, cX = 0 ,cY = 0;
    	private Point point;
    	
    	public MyMouseAdapter(PanelTerrains panelT, JLabel border, MiniMap minimap) {
    		super();
    		//Récupération de panel terrains
    		this.panelT = panelT;
    		//Récupération de ScrollPane du panel terrains
    		this.scrollpane = this.panelT.getScrollPane();
    		//Récupération de la minimap
			this.minimap = minimap;
			//Récupération du cadre de caméra
			this.border = border;
			//Récupération de conteneur du cadre afin de le rafraîchir
			parent = this.border.getParent();
    	}
    	
        @Override
        public void mousePressed(MouseEvent me) {
        	point = me.getPoint();
        	if (point.x > Xmax) {
        		sX = Xmax;
        	}else if (point.x < Xmin) {
        		sX = Xmin;
        	}else {
        		sX = point.x;
        		cX = sX;
        	}
        	if (point.y > Ymax){
        		sY = Ymax;
        	}else if (point.y < Ymin) {
        		sY = Ymin;
        	}else {
        		sY = point.y;
        	}
        	parent.remove(this.border);
			parent.validate();
			parent.repaint();
			ImageIcon imageCadre = new ImageIcon("images/minimap/cadre.png");
			this.border.setBounds(sX-Xmin, sY-Ymin, imageCadre.getIconWidth(), imageCadre.getIconHeight());
			minimap.add(this.border, JLayeredPane.MODAL_LAYER);
        	dragging = true;
        	cX = GetBigSx(point.x);
			cY = GetBigSy(point.y);
			this.scrollpane.getHorizontalScrollBar().setValue(GetBigSx(point.x));
			this.scrollpane.getVerticalScrollBar().setValue(GetBigSy(point.y));
        }

        @Override
        public void mouseDragged(MouseEvent me) {
        	Point point = me.getPoint();
        	if (point.x > Xmax) {
        		sX = Xmax;
        	}else if (point.x < Xmin) {
        		sX = Xmin;
        	}else {
        		sX = point.x;
        	}
        	if (point.y > Ymax){
        		sY = Ymax;
        	}else if (point.y < Ymin) {
        		sY = Ymin;
        	}else {
        		sY = point.y;
        	}
        	if (dragging) { 
        		parent.remove(this.border);
    			parent.validate();
    			parent.repaint();
    			ImageIcon imageCadre = new ImageIcon("images/minimap/cadre.png");
    			this.border.setBounds(sX-Xmin, sY-Ymin, imageCadre.getIconWidth(), imageCadre.getIconHeight());
    			minimap.add(this.border, JLayeredPane.MODAL_LAYER);
    			cX = GetBigSx(point.x);
    			cY = GetBigSy(point.y);
    			this.scrollpane.getHorizontalScrollBar().setValue(GetBigSx(point.x));
    			this.scrollpane.getVerticalScrollBar().setValue(GetBigSy(point.y));
        	} 
        }

        @Override
        public void mouseReleased(MouseEvent me) {  
        	dragging = false;
        }
        
        //Ces deux fonctions sert à adapter les coordonnées pour le JscollPane
        public int GetBigSx(int x) {
        	int cX;
        	x = x * 10;
        	if (x >= 1000) {
        		cX = 200;
        	}else if (x <= 700) {
        		cX = 0;
        	}else {
        		cX = x;
        	}
        	return cX;
        }
        public int GetBigSy(int y) {
        	int cY;
        	y = y * 10;
        	if (y >= 3000) {
        		cY = 1600;
        	}else if (y <= 300) {
        		cY = 0;
        	}else {
        		cY = y;
        	}
        	return cY;
        }
    }
	
}


