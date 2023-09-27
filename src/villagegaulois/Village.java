package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	
	private static class Marche{
		private Etal[] etals;
		
		private Marche(int nbEtals) {
			etals = new Etal[nbEtals];
			for(int i=0; i<etals.length;i++) {
				etals[i] = new Etal();
			}
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit ) {
			
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			
		}
		
		private int trouverEtalLibre() {
			for(int i=0; i < etals.length; i++) {
				if(etals[i].isEtalOccupe()) {
				}
				else
					return i;	
			}
			return -1;
			
		}
		
		
		private Etal[] trouverEtals(String produit) {
			
			int j=0;
			
			for(int i=0; i < etals.length; i++) {
				if(etals[i].contientProduit(produit)) {
					j++;
				}
			}
			
			Etal[] etalsProduit = new Etal[j];
			int n=0;
			for(int i=0; i < etals.length; i++) {
				if(etals[i].contientProduit(produit)) {
					etalsProduit[n] = etals[i];
					n++;
				}
			}
						
			return etalsProduit;	
				
		}
		
		
		public Etal trouverVendeur(Gaulois gaulois) {
			
			for(int i=0; i < etals.length; i++) {
				if(etals[i].getVendeur() == gaulois )
					return etals[i];
			}
			
			return null;
			
		}
		
		public String afficherMarche() {
			
			StringBuilder chaine = new StringBuilder();
			
			int nonOcuppe = etals.length;
			
			for(int i=0; i < etals.length; i++) {
				if(etals[i].isEtalOccupe()) {
					nonOcuppe--;
					chaine.append(etals[i].afficherEtal());
				}
					
			}
			
			if(nonOcuppe>0) {
				chaine.append("Il reste "+ nonOcuppe +" étals non utilisés dans le marché");
			}
			
			return chaine.toString();
		}
		
		
		
	}
	
	
	public static void main() {
		
		Gaulois obelix = new Gaulois("Obélix",5);
		Gaulois asterix = new Gaulois("Astérix",3);
		String produit1 = "Sanglier";
		Marche marche = new Marche(5);
		
		int indice1 = marche.trouverEtalLibre();
		marche.utiliserEtal(indice1, obelix, produit1, 4);
		
		int indice2 = marche.trouverEtalLibre();
		marche.utiliserEtal(indice2, asterix, produit1, 6);
		
		int nbEtalsProd = marche.trouverEtals(produit1).length;
		for(int i=0; i<nbEtalsProd;i++) {
			marche.trouverEtals(produit1)[i].afficherEtal();
		}
 	}
	
}