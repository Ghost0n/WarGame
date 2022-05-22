package vue;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modele.Joueur;

@SuppressWarnings("serial")
public class PanelInfosJoueur extends JPanel {
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private JLabel NomJoueur;
	private JLabel score;
	private JLabel nombreSoldat;
	private JLabel profile;
	private BufferedImage master;
	
	
	public PanelInfosJoueur(ArrayList<Joueur> joueurs, int xBounds) {
		this.setLayout(null);
		this.setBounds(xBounds, 170, 201, 180);
		this.setLayout(null);
		this.setOpaque(false);
		
		this.joueurs = joueurs;
		
		try {
			master = ImageIO.read(new File(joueurs.get(0).getImage()));
			int diameter = Math.min(master.getWidth(), master.getHeight());
			BufferedImage mask = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);
			    
			Graphics2D g2d = mask.createGraphics();
			applyQualityRenderingHints(g2d);
			g2d.fillOval(0, 0, diameter - 1, diameter - 1);
		    g2d.dispose();

		    BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
		    g2d = masked.createGraphics();
		    applyQualityRenderingHints(g2d);
		    int x = (diameter - master.getWidth()) / 2;
		    int y = (diameter - master.getHeight()) / 2;
		    g2d.drawImage(master, x, Math.abs(y-30), null);
		    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
		    g2d.drawImage(mask, 0, 0, null);
		    g2d.dispose();
		    
		    profile = new JLabel((new ImageIcon(masked)));
		    profile.setBounds(21, 0, 102, 111);
		    profile.setBackground(new Color(16, 22, 33));
		    this.add(profile); 
		    
		    /** Nom de Joueur **/
		    afficherLabelNomJoueur();
		    afficherLabelScore();
		    afficherLabelNombreSoldat();
		    
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		    

		    
	}
	public static void applyQualityRenderingHints(Graphics2D g2d) {

	    g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

	}
	
	public void afficherLabelNomJoueur() {
		JLabel labelNomJoueur = new JLabel("Nom :");
		labelNomJoueur.setBounds(7, 121, 73, 13);
		labelNomJoueur.setForeground(new Color(200, 173, 10));
	    this.add(labelNomJoueur);
	    
	    NomJoueur = new JLabel();
		NomJoueur.setBounds(43, 121, 73, 13);
		NomJoueur.setForeground(new Color(200, 173, 10));
	    this.add(NomJoueur);
	}
	
	public void afficherLabelScore() {
		JLabel labelScoreJoueur = new JLabel("Score :");
		labelScoreJoueur.setBounds(7, 141, 73, 13);
		labelScoreJoueur.setForeground(new Color(200, 173, 10));
	    this.add(labelScoreJoueur);
	    
	    score = new JLabel();
	    score.setBounds(50, 141, 73, 13);
	    score.setForeground(new Color(200, 173, 10));
		this.add(score);
	}
	
	public void afficherLabelNombreSoldat() {
		JLabel labelNombreSoldat = new JLabel("Soldat :");
		labelNombreSoldat.setBounds(7, 161, 73, 13);
		labelNombreSoldat.setForeground(new Color(200, 173, 10));
	    this.add(labelNombreSoldat);
	    
	    nombreSoldat = new JLabel();
	    nombreSoldat.setBounds(52, 161, 73, 13);
	    nombreSoldat.setForeground(new Color(200, 173, 10));
		this.add(nombreSoldat);
	}
	
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}
	
	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	public JLabel getNomJoueur() {
		return NomJoueur;
	}
	public void setNomJoueur(JLabel nomJoueur) {
		NomJoueur = nomJoueur;
	}
	public JLabel getScore() {
		return score;
	}
	public void setScore(JLabel score) {
		this.score = score;
	}
	public JLabel getNombreSoldat() {
		return nombreSoldat;
	}
	public void setNombreSoldat(JLabel nombreSoldat) {
		this.nombreSoldat = nombreSoldat;
	}
	public JLabel getProfile() {
		return profile;
	}
	public void setProfile(JLabel profile) {
		this.profile = profile;
	}
	public BufferedImage getMaster() {
		return master;
	}
	public void setMaster(BufferedImage master) {
		this.master = master;
	}
	
	
}
