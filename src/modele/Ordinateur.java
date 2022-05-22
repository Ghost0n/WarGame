package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;

import modele.*;

public class Ordinateur  extends Joueur{

	public Ordinateur(String nomJoueur, ArrayList<Soldat> soldats, int score, String image,
			ArrayList<Joueur> adversaires) {
		super(nomJoueur, soldats, score, image, adversaires);
	}
	
	public Soldat choisirSoldat()
	{ 	
		int min=0;
		int max=super.getSoldatList().size();
		Random random = new Random();
		int numsoldat = random.nextInt(max+min)+min;
		return super.getSoldatList().get(numsoldat) ;
	}
	
	public JLabel choisirHexagone(ArrayList<JLabel> labelsHexagonesVisions)
    {
        int nbhexagonesPossibles=labelsHexagonesVisions.size();
        int min=0;
        int max=nbhexagonesPossibles;
        Random random = new Random();
        int Numhexagone = random.nextInt(max+min)+min;
        JLabel label = labelsHexagonesVisions.get(Numhexagone);
        return label;
    }

	/*************************************************************************************/
	/** FONCTION POUR RECHERCHE SI IL Y A UNE VILLAGE DISPONIBLE POUR SOIGNE LE SOLDAT **/
	/***********************************************************************************/
	public int rechercherVillage(ArrayList<Hexagone> hexagone,ArrayList<Soldat> soldats)
	{
		int etatVillage = 0,j,k;
		for(j=0;j<hexagone.size();j++)
		{
			if(hexagone.get(j).getTypeTerrain().equals("Village")&&hexagone.get(j).getUnits().isEmpty())
			{
				etatVillage=j;
				return j;//cette village est disponible 
			}
			else 
			{
				for(k=0;k<soldats.size();k++)
				{
					if(soldats.get(k).getAbscisse()==hexagone.get(j).getAbscisse()
						&&soldats.get(k).getOrdonnees()==hexagone.get(j).getOrdonnees()
						&&hexagone.get(j).getTypeTerrain().equals("Village"))
					{
						etatVillage=-1;
						return -1;// il y a aucun village disponible
					}
						
				}
				
			}
		}
		return etatVillage ;
	}
		
	
	
	
	

}
