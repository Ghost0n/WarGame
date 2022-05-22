package modele;

import java.util.ArrayList;

public class ScenarioTempsLimite extends ScenarioStandard {

	public ScenarioTempsLimite(ArrayList<Joueur> joueurs) {
		super(joueurs);

	}
	
	public Joueur appliquerScenarioTempsLimite(Joueur joueur) {
		boolean termine = false;
		Joueur gagnant = null;
		//do
		//{
			gagnant = chercherScoreMax(joueur);
			termine = true;
		// tant que le temps n'est pas achevé
		//while(1==1)}
		
		return gagnant;
	}
	
	public Joueur chercherScoreMax (Joueur joueur) {
		int scoreMax = 0;
		Joueur gagnant = null;
		for (int i = 0; i< joueurs.size(); i++) {
			if (scoreMax < joueurs.get(i).getScore()) {
				scoreMax = joueurs.get(i).getScore();
				gagnant = joueurs.get(i);
			}
		}
		return gagnant;
	}

}
