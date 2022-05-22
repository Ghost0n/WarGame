package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Hexagone {
	private static final AtomicInteger count = new AtomicInteger(0); 
	private int abscisse;
	private int ordonnees;
	private String typeTerrain;
	private ArrayList<Soldat> units;
	private int id;

	public Hexagone (int abscisse, int ordonnees, String typeTerrain) {
		this.typeTerrain = typeTerrain;
		this.abscisse = abscisse;
		this.ordonnees = ordonnees;
		this.id = count.incrementAndGet();
		this.units = new ArrayList<Soldat>();
	}

	public void addInHexagone (Soldat s) {
		units.add(s);
	}
	
	public void removeFromHexagone (Soldat s) {
		units.remove(s);
	}

	public String getTypeTerrain() {
		return typeTerrain;
	}

	public void setTypeTerrain(String typeTerrain) {
		this.typeTerrain = typeTerrain;
	}

	public int getAbscisse() {
		return abscisse;
	}

	public void setAbscisse(int abscisse) {
		this.abscisse = abscisse;
	}

	public int getOrdonnees() {
		return ordonnees;
	}

	public void setOrdonnees(int ordonnees) {
		this.ordonnees = ordonnees;
	}

	public ArrayList<Soldat> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Soldat> units) {
		this.units = units;
	}
	
	public boolean contientEnnemi(Joueur joueur) {
		List<Soldat> soldatsEnnemis = this.units.stream().filter(x -> !joueur.soldatExiste(x)).collect(Collectors.toList());
		return soldatsEnnemis.size() > 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Hexagone [abscisse=" + abscisse + ", ordonnees=" + ordonnees + ", typeTerrain=" + typeTerrain
				+ ", units=" + units + ", id=" + id + "]";
	}
	
}
