package metier;

import java.util.*;

/**
 * Permet l'instanciation de Particule trouvée en analysant une image
 */
public class Particule {
    private int idParticule;
    private double surfaceParticulePx;
    private double coCoinHautGaucheX;
    private double coCoinHautGaucheY;
    private double coCoinBasDroitX;
    private double coCoinBasDroitY;
    private double coCentreX;
    private double coCentreY;
    private double orientation;
    private double longueurAxeMajeur;
    private double longueurAxeMineur;
    private double diametreEquivalent;
    private int idImage;
    
    /**
     * Default constructor
     */
    public Particule(String[] attributs) {
    	surfaceParticulePx = Double.parseDouble(attributs[0]);
    	coCoinHautGaucheX = Double.parseDouble(attributs[1]);
    	coCoinHautGaucheY = Double.parseDouble(attributs[2]);
    	coCoinBasDroitX = Double.parseDouble(attributs[3]);
    	coCoinBasDroitY = Double.parseDouble(attributs[4]);
    	coCentreX = Double.parseDouble(attributs[5]);
    	coCentreY = Double.parseDouble(attributs[6]);
    	orientation = Double.parseDouble(attributs[7]);
    	longueurAxeMajeur = Double.parseDouble(attributs[8]);
    	longueurAxeMineur = Double.parseDouble(attributs[9]);
    	diametreEquivalent = Double.parseDouble(attributs[10]);
    }
    
    

	public Particule(int idParticule, double surfaceParticulePx, double coCoinHautGaucheX, double coCoinHautGaucheY,
			double coCoinBasDroitX, double coCoinBasDroitY, double coCentreX, double coCentreY, double orientation,
			double longueurAxeMajeur, double longueurAxeMineur, double diametreEquivalent, int idImage) {
		this.idParticule = idParticule;
		this.surfaceParticulePx = surfaceParticulePx;
		this.coCoinHautGaucheX = coCoinHautGaucheX;
		this.coCoinHautGaucheY = coCoinHautGaucheY;
		this.coCoinBasDroitX = coCoinBasDroitX;
		this.coCoinBasDroitY = coCoinBasDroitY;
		this.coCentreX = coCentreX;
		this.coCentreY = coCentreY;
		this.orientation = orientation;
		this.longueurAxeMajeur = longueurAxeMajeur;
		this.longueurAxeMineur = longueurAxeMineur;
		this.diametreEquivalent = diametreEquivalent;
		this.idImage = idImage;
	}



	@Override
	public String toString() {
		return "Particule [idParticule=" + idParticule + ", surfaceParticulePx=" + surfaceParticulePx
				+ ", coCoinHautGaucheX=" + coCoinHautGaucheX + ", coCoinHautGaucheY=" + coCoinHautGaucheY
				+ ", coCoinBasDroitX=" + coCoinBasDroitX + ", coCoinBasDroitY=" + coCoinBasDroitY
				+ ", coCentreX=" + coCentreX + ", coCentreY=" + coCentreY + ", orientation=" + orientation
				+ ", longueurAxeMajeur=" + longueurAxeMajeur + ", longueurAxeMineur=" + longueurAxeMineur
				+ ", diametreEquivalent=" + diametreEquivalent + "]";
	}


    public int getIdParticule() {
		return idParticule;
	}

	public void setIdParticule(int idParticule) {
		this.idParticule = idParticule;
	}

	public double getSurfaceParticulePx() {
		return surfaceParticulePx;
	}

	public void setSurfaceParticulePx(double surfaceParticulePx) {
		this.surfaceParticulePx = surfaceParticulePx;
	}

	public double getcoCoinHautGaucheX() {
		return coCoinHautGaucheX;
	}

	public void setcoCoinHautGaucheX(double coCoinHautGaucheX) {
		this.coCoinHautGaucheX = coCoinHautGaucheX;
	}

	public double getcoCoinHautGaucheY() {
		return coCoinHautGaucheY;
	}

	public void setcoCoinHautGaucheY(double coCoinHautGaucheY) {
		this.coCoinHautGaucheY = coCoinHautGaucheY;
	}

	public double getcoCoinBasDroitX() {
		return coCoinBasDroitX;
	}

	public void setcoCoinBasDroitX(double coCoinBasDroitX) {
		this.coCoinBasDroitX = coCoinBasDroitX;
	}

	public double getcoCoinBasDroitY() {
		return coCoinBasDroitY;
	}

	public void setcoCoinBasDroitY(double coCoinBasDroitY) {
		this.coCoinBasDroitY = coCoinBasDroitY;
	}

	public double getCoCentreX() {
		return coCentreX;
	}

	public void setCoCentreX(double coCentreX) {
		this.coCentreX = coCentreX;
	}

	public double getCoCentreY() {
		return coCentreY;
	}

	public void setCoCentreY(double coCentreY) {
		this.coCentreY = coCentreY;
	}

	public double getOrientation() {
		return orientation;
	}

	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}

	public double getLongueurAxeMajeur() {
		return longueurAxeMajeur;
	}

	public void setLongueurAxeMajeur(double longueurAxeMajeur) {
		this.longueurAxeMajeur = longueurAxeMajeur;
	}

	public double getLongueurAxeMineur() {
		return longueurAxeMineur;
	}

	public void setLongueurAxeMineur(double longueurAxeMineur) {
		this.longueurAxeMineur = longueurAxeMineur;
	}

	public double getDiametreEquivalent() {
		return diametreEquivalent;
	}

	public void setDiametreEquivalent(double diametreEquivalent) {
		this.diametreEquivalent = diametreEquivalent;
	}

	public int getIdImage() {
		return idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

}