package vue;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import modele.Archer;
import modele.Cavalerie;
import modele.InfanterieLegere;
import modele.InfanterieLourde;
import modele.Joueur;
import modele.Mage;
import modele.Soldat;

import java.util.ArrayList;
import java.awt.Color;

public class SoldatVue {

	private ArrayList<Soldat> soldats;
	private ArrayList<JLabel> labelsSoldats;
	
	public SoldatVue() {
		JPanel infoSoldat = new JPanel();
		infoSoldat.setBounds(555, 0, 555, 599);
		infoSoldat.setBackground(Color.GRAY);

		this.labelsSoldats = new ArrayList<JLabel>();
		this.soldats = new ArrayList<Soldat>();
	}

	public void creerSoldats(ArrayList<Joueur> joueurs) {
		int x, y, oldY;

		for (int i=0; i<joueurs.size(); i++) {
			if (i == 0) {
				x = 0;
				y = 0;
			}
			else if (i == 1) {
				x = 0;
				y = 1294;
			}
			else if (i == 2) {
				x = 1040;
				y = 0;
			}
			else {
				x = 1040;
				y = 1294;
			}
			oldY = y;

			ArrayList<Soldat> soldatsJoueurs = new ArrayList<Soldat>();
			soldatsJoueurs.add(new Archer(x,y));
			y += 72;
			soldatsJoueurs.add(new Mage(x,y));
			y += 72;
			soldatsJoueurs.add(new InfanterieLegere(x,y));
			y += 72;
			soldatsJoueurs.add(new Archer(x,y));
			x += 52;
			y = oldY + 36;
			soldatsJoueurs.add(new InfanterieLourde(x,y));
			y += 72;
			soldatsJoueurs.add(new Cavalerie(x,y));
			y += 72;
			soldatsJoueurs.add(new Mage(x,y));
			x += 52;
			y = oldY;
			soldatsJoueurs.add(new InfanterieLegere(x,y));
			y += 72;
			soldatsJoueurs.add(new InfanterieLourde(x, y));
			y += 72;
			soldatsJoueurs.add(new Cavalerie(x, y));

			this.soldats.addAll(soldatsJoueurs);

			JLabel labelSoldat = new JLabel("");
			for(Soldat soldat : soldatsJoueurs)
			{
				labelSoldat = new JLabel("");
				labelSoldat.setIcon(new ImageIcon(soldat.getImage()));
				labelSoldat.setBounds(soldat.getAbscisse(), soldat.getOrdonnees(), 72, 72);
				labelSoldat.setName(Integer.toString(soldat.getId()));
				this.labelsSoldats.add(labelSoldat);
			}
			joueurs.get(i).getSoldatList().addAll(soldatsJoueurs);
		}
	}

	public ArrayList<Soldat> getSoldats() {
		return soldats;
	}

	public void setSoldats(ArrayList<Soldat> soldats) {
		this.soldats = soldats;
	}

	public ArrayList<JLabel> getLabelsSoldats() {
		return labelsSoldats;
	}

	public void setLabelsSoldats(ArrayList<JLabel> labelsSoldats) {
		this.labelsSoldats = labelsSoldats;
	}

}
