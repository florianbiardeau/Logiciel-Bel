package metier;

import java.util.*;

/**
 * Propose une liste de Particules
 */
public class EnsembleParticules {
    private ArrayList<Particule> listeParticules;

    /**
     * Default constructor
     */
    public EnsembleParticules() {
    	listeParticules = new ArrayList<Particule>();
    }
    
    /**
     * efface toutes les particules de la liste
     */
    public void effacer() {
    	this.listeParticules.clear();
    }

    /**
     * Ajoute une Particule dans la liste
     * @param particule Particule à ajoutée
     */
    public void ajouterParticule(Particule particule) {
        listeParticules.add(particule);
    }
    
    /**
     * ajoute des Particules contenues dans un ensemble
     * @param ens ensemble contenant les particules à ajouter
     */
    public void ajouterParticules(EnsembleParticules ens) {
    	this.listeParticules.addAll(ens.getListeParticules());
    }
    
    /**
     * Teste si l'ensemble est vide
     * @return
     */
    public boolean estVide() {
    	return listeParticules.isEmpty();
    }
    
    /**
     * retourne le nombre total de particules dans la liste
     * @return
     */
    public int getNombreParticules() {
    	return listeParticules.size();
    }
    
    /**
     * retourne l'idImage de la premiere particule dans la liste
     * @return idImage ou -1 si pas de particules
     */
    public int getPremiereIdImage() {
    	if(!estVide()) {
    		return listeParticules.get(0).getIdImage();
    	}else return -1;
    }
    
    /**
     * Calcul le ratio de surface couverte
     * @return
     */
    public double getRatioSurfaceCouverte() {
    	double sommeAireP = 0;
    	double sommeAireBb = 0;
    	for (Particule p : listeParticules) {
			sommeAireP += p.getSurfaceParticulePx();
			sommeAireBb += Math.abs((p.getcoCoinBasDroitX() - p.getcoCoinHautGaucheX())*(p.getcoCoinBasDroitY()-p.getcoCoinHautGaucheY()));
		}
    	return sommeAireP / sommeAireBb;
    }
    
    /**
     * permet le calcul de la moyenne des aires des particules dans l'ensemble
     * @return moyenne des aires
     */
    public double getMoyenneAires() {
    	double somme = 0;
    	for (Particule particule : listeParticules) {
			somme += particule.getSurfaceParticulePx();
		}
    	return somme / listeParticules.size();
    }
    
    /**
     * permet le calcul de la moyenne des diamètres équivalents des particules dans l'ensemble
     * @return moyenne des diamètres équivalents
     */
    public double getMoyenneDiametresEquivalents() {
    	double somme = 0;
    	for (Particule particule : listeParticules) {
			somme += particule.getDiametreEquivalent();
		}
    	return somme / listeParticules.size();
    }
    
    /**
     * calcule l'écart type des aires des particules
     * @return
     */
    public double getEcartTypeAires() {
    	double moyenne = getMoyenneAires();
    	double somme = 0;
    	for (Particule particule : listeParticules) {
    		double ecartALaMoyenne = particule.getSurfaceParticulePx() - moyenne;
			somme += ecartALaMoyenne * ecartALaMoyenne;
		}
    	return Math.sqrt(somme/listeParticules.size());
    }
    
    /**
     * calcule l'écart type des diamètres équivalents des particules
     * @return
     */
    public double getEcartTypeDiametresEquivalents() {
    	double moyenne = getMoyenneDiametresEquivalents();
    	double somme = 0;
    	for (Particule particule : listeParticules) {
    		double ecartALaMoyenne = particule.getDiametreEquivalent() - moyenne;
			somme += ecartALaMoyenne * ecartALaMoyenne;
		}
    	return Math.sqrt(somme/listeParticules.size());
    }
    
    /**
     * permet de trouver le minimum des surfaces
     * @return Double.POSITIVE_INFINITY si vide
     */
    public double getMinSurface() {
    	double min = Double.POSITIVE_INFINITY;
    	for (Particule particule : listeParticules) {
			if(particule.getSurfaceParticulePx() < min) min = particule.getSurfaceParticulePx();
		}
    	return min;
    }
    
    /**
     * permet de trouver le minimum des diamètres
     * @return Double.POSITIVE_INFINITY si vide
     */
    public double getMinDiametre() {
    	double min = Double.POSITIVE_INFINITY;
    	for (Particule particule : listeParticules) {
			if(particule.getDiametreEquivalent() < min) min = particule.getDiametreEquivalent();
		}
    	return min;
    }
    
    /**
     * permet de trouver le maximum des surfaces
     * @return Double.NEGATIVE_INFINITY si vide
     */
    public double getMaxSurface() {
    	double max = Double.NEGATIVE_INFINITY;
    	for (Particule particule : listeParticules) {
			if(particule.getSurfaceParticulePx() > max) max = particule.getSurfaceParticulePx();
		}
    	return max;
    }
    
    /**
     * permet de trouver le maximum des diamètres
     * @return Double.NEGATIVE_INFINITY si vide
     */
    public double getMaxDiametre() {
    	double max = Double.NEGATIVE_INFINITY;
    	for (Particule particule : listeParticules) {
			if(particule.getDiametreEquivalent() > max) max = particule.getDiametreEquivalent();
		}
    	return max;
    }

	@Override
	public String toString() {
		return "EnsembleParticules [listeParticules=" + listeParticules + "]";
	}

	public ArrayList<Particule> getListeParticules() {
		return listeParticules;
	}

	public void setListeParticules(ArrayList<Particule> listeParticules) {
		this.listeParticules = listeParticules;
	}

	
	

}