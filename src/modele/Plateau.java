package modele;

import java.util.Random;

public class Plateau {
	public int HauteurPlateau;   //Hauteur et largeur plateau en nombre d'hexagone
	public int LargeurPlateau;
	public Hexagone[][] PlateauJeu;
	
	public Plateau(int HauteurPlateau,int LargeurPlateau) {
		this.HauteurPlateau = HauteurPlateau;
		this.LargeurPlateau = LargeurPlateau;
	}
	
	 private static int getRandomNumberInRange(int min, int max) {
	        Random r = new Random();
	        return r.nextInt((max - min) + 1) + min;
	    }
	
	public void InitPlateau() {
		/*PlateauJeu = new Hexagone[HauteurPlateau][LargeurPlateau];
		for (int i = 0;i < HauteurPlateau;i++) {
			for (int j = 0;j < LargeurPlateau;j++) {
				
				int x = getRandomNumberInRange(0,6);
				TerrainType typeTerrain = TerrainType.fromInt(x);
				Hexagone h = new Hexagone (i,j,typeTerrain);
				
				
				PlateauJeu[i][j] = h;
			}
		}*/
	}
	
	
	
}
