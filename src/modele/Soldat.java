package modele;

public class Soldat extends Piece {
	private String typeSoldat;
	private String imagePivotee;
	private int attaque;
	private int defense;
	private int deplacement;
	private int deplacementRealises;
	private int vision;
	private int pv;
	private boolean ko;
	private boolean bloque;
	
	public Soldat(int abscisse, int ordonnees, String image, String typeSoldat, String imagePivotee, int attaque, int defense,
			int deplacement, int vision, int pv, boolean ko) {
		super(abscisse, ordonnees, image);
		this.typeSoldat = typeSoldat;
		this.imagePivotee = imagePivotee;
		this.attaque = attaque;
		this.defense = defense;
		this.deplacement = deplacement;
		this.vision = vision;
		this.pv = pv;
		this.ko = ko;
		this.deplacementRealises = 0;
		this.bloque = true;
	}
	
	public String getTypeSoldat() {
		return typeSoldat;
	}
	public void setTypeSoldat(String typeSoldat) {
		this.typeSoldat = typeSoldat;
	}
	public String getImagePivotee() {
		return imagePivotee;
	}
	public void setImagePivotee(String imagePivotee) {
		this.imagePivotee = imagePivotee;
	}
	public int getAttaque() {
		return attaque;
	}
	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getDeplacement() {
		return deplacement;
	}
	public void setDeplacement(int deplacement) {
		this.deplacement = deplacement;
	}
	public int getVision() {
		return vision;
	}
	public void setVision(int vision) {
		this.vision = vision;
	}
	public int getPv() {
		return pv;
	}
	public void setPv(int pv) {
		this.pv = pv;
	}
	public boolean isKo() {
		return ko;
	}
	public void setKo(boolean ko) {
		this.ko = ko;
	}

	public void majSoldat(Soldat s)
	{
		if(pv==0)
		{
			s.setKo(true);
		}
	}
	
	public void deplacementPossible (int abscisseMin, int abscisseMax, int ordonneesMin, int ordonneesMax, int x, int y, int nbrHexagonesAparcourir, int bonusDeplacement) {
		if (this.deplacementRealises > (this.deplacement + bonusDeplacement)) {
			this.bloque = true;
		}
		
		else {
			if (x >= abscisseMin && x <= abscisseMax && y >= ordonneesMin && y <= ordonneesMax && nbrHexagonesAparcourir <= (this.deplacement + bonusDeplacement) && nbrHexagonesAparcourir <= this.vision) {
				super.setAbscisse(x);
				super.setOrdonnees(y);
				this.deplacementRealises+=nbrHexagonesAparcourir;
			}
		}

	}

	public int getDeplacementRealises() {
		return deplacementRealises;
	}

	public void setDeplacementRealises(int deplacementRealises) {
		this.deplacementRealises = deplacementRealises;
	}
	
	public boolean isBloque() {
		return bloque;
	}

	public void setBloque(boolean bloque) {
		this.bloque = bloque;
	}

	@Override
	public String toString() {
		return "Soldat [typeSoldat=" + typeSoldat + ", attaque=" + attaque + ", defense=" + defense + ", deplacement="
				+ deplacement + ", vision=" + vision + ", pv=" + pv + ", ko=" + ko + "]";
	}

}
