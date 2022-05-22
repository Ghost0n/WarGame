package modele;

import java.util.ArrayList;

public class ScenarioTourLimite extends ScenarioStandard {

	public ScenarioTourLimite(ArrayList<Joueur> joueurs) {
		super(joueurs);
	}
	
	public Joueur appliquerScenarioTourLimite(Joueur joueur, int nombreTours) {
		Joueur gagnant = null;
		//Joueur gagnant = new Joueur(" ", new ArrayList<Soldat>(), 0, " ", new ArrayList<Joueur>());
		if (nombreTours == 30) {
			gagnant = chercherScoreMax(joueur);
		}
		return gagnant;
	}
	
	public Joueur chercherScoreMax (Joueur joueur) {
		int scoreMax = 0;
		Joueur gagnant = new Joueur(" ", new ArrayList<Soldat>(), 0, " ", new ArrayList<Joueur>());
		for (int i = 0; i< joueurs.size(); i++) {
			if (scoreMax < joueurs.get(i).getScore()) {
				scoreMax = joueurs.get(i).getScore();
				gagnant = joueurs.get(i);
			}

		}
		return gagnant;
	}

}
