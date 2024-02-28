package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private Marche marche;
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	

	public Village(String nom, int nbVillageoisMaximum, int nbEtalsMarche) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtalsMarche);
		
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
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} 
		else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbproduits) {
		
		StringBuilder chaine = new StringBuilder();
		
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbproduits + " " + produit + ".\n" );
		
		int etalLibre = marche.trouverEtalLibre();
		
		if (etalLibre < 0) {
			
			chaine.append("Il n'y a plus d'étals disponibles \n");
				
		}
		else {
			marche.utiliserEtal(etalLibre, vendeur, produit, nbproduits);
			
			chaine.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n° " + etalLibre + ".\n" );
		}

		return chaine.toString();
		
	}
	
	
	public String rechercherVendeursProduit(String produit) {
		
		Etal[] etals = marche.trouverEtals(produit);
		StringBuilder chaine = new StringBuilder();
		
		if(etals.length < 1) {
			chaine.append("Il n'y a pas de vendeur qui propose des " + produit + " au marché \n");
		}
		
		else if(etals.length == 1) {
			
			chaine.append("Seul le vendeur " + etals[0].getVendeur().getNom() + " propose des " + produit + " au marché \n");
		}
		else {
			chaine.append("Les vendeurs qui proposent des fleurs sont : \n");
			
			for(int i = 0; i < etals.length; i++ ) {
				chaine.append("- " + etals[i].getVendeur().getNom() + " \n");
			}
		}
		
		return chaine.toString();
		
	}
	
	
	
	public Etal rechercherEtal(Gaulois vendeur) {
		Etal etal = marche.trouverVendeur(vendeur);
		return etal;
	}
	
	
	
	public String partirVendeur(Gaulois vendeur) {
	
		return rechercherEtal(vendeur).libererEtal();
		
	}
	
	 public String afficherMarche() {
		 return marche.afficherMarche();
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
			
			for (int i=0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					j++;
				}
			}
			
			Etal[] etalsProduit = new Etal[j];
			int n=0;
			for (int i=0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					etalsProduit[n] = etals[i];
					n++;
				}
			}
						
			return etalsProduit;	
				
		}
		
		
		public Etal trouverVendeur(Gaulois gaulois) {
			
			for (int i=0; i < etals.length; i++) {
				if (etals[i].getVendeur() == gaulois )
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
	
	
 	}	
