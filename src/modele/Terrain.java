package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Terrain extends Piece {
	private String typeTerrain;
	private int pointDeplacement;
	private int bonusDefense;
	private ArrayList<Hexagone> hexagones = new ArrayList<Hexagone>();

	public Terrain (int abscisse, int ordonnees, String image, String typeTerrain, int pointDeplacement, int bonusDefense) {
		super(abscisse, ordonnees, image);
		this.typeTerrain = typeTerrain;
		this.pointDeplacement = pointDeplacement;
		this.bonusDefense = bonusDefense;
	}
	
	public String getTypeTerrain() {
		return typeTerrain;
	}
	
	public void setTypeTerrain(String typeTerrain) {
		this.typeTerrain = typeTerrain;
	}
	
	public int getPointDeplacement() {
		return pointDeplacement;
	}
	
	public void setPointDeplacement(int pointDeplacement) {
		this.pointDeplacement = pointDeplacement;
	}
	
	public int getBonusDefense() {
		return bonusDefense;
	}
	
	public void setBonusDefense(int bonusDefense) {
		this.bonusDefense = bonusDefense;
	}
	
	public ArrayList<Hexagone> getHexagones() {
		return this.hexagones;
	}
	
	public void setHexagones(ArrayList<Hexagone> listeHexagones) {
		this.hexagones = listeHexagones;
	}
	
	public void ajouterHexagone(Hexagone hexagone) {
		this.hexagones.add(hexagone);
	}
	
	public void ajouterHexagones(ArrayList<Hexagone> listeHexagones) {
		for (Hexagone hex : listeHexagones) {
			ajouterHexagone(hex);
		}
	}
	
	public void retirerHexagone(Hexagone hexagone) {
		this.hexagones.remove(hexagone);
	}
	
	public void retirerAllHexagone() {
		this.hexagones.removeAll(this.hexagones);
	}
	
	public ArrayList<Hexagone> getHexagone(int abscisse, int ordonnees) {
		List<Hexagone> listeHexagonesFiltres = this.hexagones.stream().filter(x -> abscisse >= x.getAbscisse() && abscisse < (x.getAbscisse() + 52) && ordonnees >= x.getOrdonnees() && (ordonnees < (x.getOrdonnees() + 36) || ordonnees < (x.getOrdonnees() + 72))).collect(Collectors.toList());
		ArrayList<Hexagone> hexagonesFiltres = new ArrayList<Hexagone>(listeHexagonesFiltres);
		return hexagonesFiltres;
	}
	
	public ArrayList<Hexagone> getHexagone(Soldat soldat) {
		List<Hexagone> listeHexagonesFiltres = this.hexagones.stream().filter(x -> x.getUnits().contains(soldat)).collect(Collectors.toList());
		ArrayList<Hexagone> hexagonesFiltres = new ArrayList<Hexagone>(listeHexagonesFiltres);
		return hexagonesFiltres;
	}

}

