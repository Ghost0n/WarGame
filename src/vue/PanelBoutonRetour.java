package vue;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelBoutonRetour extends JPanel {

	public JButton boutonRetour;
	
	public PanelBoutonRetour() {
		this.setBounds(36, 46, 52, 29);
		this.setBackground(new Color(16, 22, 33));
		this.setLayout(null);
		
		afficherBoutonRetour();
	}
	
	public void afficherBoutonRetour() {
		boutonRetour = new JButton("");
		boutonRetour.setOpaque(false);
		boutonRetour.setIcon(new ImageIcon("images/arrows_ornate_left_30-active.png"));
		boutonRetour.setBounds(0, 0, 21, 28);
		boutonRetour.setBackground(new Color(16, 22, 33));
		boutonRetour.setBorder(null);
		this.add(boutonRetour);
	}
	

}
