package modele;

import java.util.concurrent.atomic.AtomicInteger;

public class Piece {
	private static final AtomicInteger count = new AtomicInteger(0); 
	private int abscisse;
	private int ordonnee;
	private String image;
	private int id;

	public Piece(int abscisse, int ordonnee, String image) {
		this.abscisse = abscisse;
		this.ordonnee = ordonnee;
		this.image = image;
		this.id = count.incrementAndGet(); 
	}
	
	public int getAbscisse() {
		return abscisse;
	}

	public void setAbscisse(int abscisse) {
		this.abscisse = abscisse;
	}

	public int getOrdonnees() {
		return ordonnee;
	}

	public void setOrdonnees(int ordonnee) {
		this.ordonnee = ordonnee;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void deplacementPossible (int abscisseMin, int abscisseMax, int ordonneesMin, int ordonneesMax, int x, int y) {
		if (x >= abscisseMin && x <= abscisseMax && y >= ordonneesMin && y <= ordonneesMax) {
			this.abscisse = x;
			this.ordonnee = y;
		}
	}
	
	public int deplacementDroit(int cases) {
		return this.abscisse + cases;
	}
	
	public int deplacementGauche(int cases) {
		return this.abscisse - cases;
	}
	
	public int deplacementHaut(int cases) {
		return this.ordonnee - cases;
	}
	
	public int deplacementBas(int cases) {
		return this.ordonnee + cases;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Piece [abscisse=" + abscisse + ", ordonnee=" + ordonnee + ", image=" + image + ", id=" + id + "]";
	}

}
