package vue;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Soldat;

@SuppressWarnings("serial")
public class PanelInfosSoldat extends JPanel {

	private JLabel labelImage, attaque, defense, deplacement, vision, pv;
	
	public PanelInfosSoldat(int xBounds) {
		this.setLayout(null);
		this.setBounds(xBounds, 350, 150, 400);
		this.setOpaque(false);
		
		this.labelImage = new JLabel("");
		this.labelImage.setBounds(25, 0, 100, 100);

		JLabel attaqueLabel = new JLabel("Attaque : ");
		attaqueLabel.setForeground(Color.white);
		attaqueLabel.setBounds(0,100, 80, 20);
		this.attaque = new JLabel();
		this.attaque.setBounds(60,101, 40, 20);
		this.attaque.setForeground(new Color(200, 173, 10));

		JLabel defenseLabel = new JLabel("Defense : ");
		defenseLabel.setForeground(Color.white);
		defenseLabel.setBounds(0,130, 80, 20);
		this.defense = new JLabel();
		this.defense.setBounds(65,131, 80, 20);
		this.defense.setForeground(new Color(200, 173, 10));
		
		JLabel deplacementLabel = new JLabel("Deplacement : ");
		deplacementLabel.setForeground(Color.white);
		deplacementLabel.setBounds(0,160, 90, 20);
		this.deplacement = new JLabel();
		this.deplacement.setBounds(90,161, 90, 20);
		this.deplacement.setForeground(new Color(200, 173, 10));

		JLabel visionLabel = new JLabel("Vision : ");
		visionLabel.setForeground(Color.white);
		visionLabel.setBounds(0,190, 80, 20);
		this.vision = new JLabel();
		this.vision.setBounds(50,191, 80, 20);
		this.vision.setForeground(new Color(200, 173, 10));

		JLabel pvLabel = new JLabel("Point de vie : ");
		pvLabel.setForeground(Color.white);
		pvLabel.setBounds(0,220, 90, 20);
		this.pv = new JLabel();
		this.pv.setBounds(85,221, 90, 20);
		this.pv.setForeground(new Color(200, 173, 10));
		
		this.add(labelImage);
		this.add(attaqueLabel);
		this.add(attaque);
		this.add(defenseLabel);
		this.add(defense);
		this.add(deplacementLabel);
		this.add(deplacement);
		this.add(visionLabel);
		this.add(vision);
		this.add(pvLabel);
		this.add(pv);
		
	}
	
	public void afficherInfosSoldats(Soldat soldat) {
		this.repaint();
		this.validate();
		
		this.labelImage.setIcon(new ImageIcon(soldat.getImage()));
		
		this.attaque.setText(String.valueOf(soldat.getAttaque()));
		
		this.defense.setText(String.valueOf(soldat.getDefense()));
		
		this.deplacement.setText(String.valueOf(soldat.getDeplacement()));
		
		this.vision.setText(String.valueOf(soldat.getVision()));
		
		this.pv.setText(String.valueOf(soldat.getPv()));
	}
}
