package metier;

import java.util.*;

/**
 * Classe representant une image. Elle contient les informations lui etant liees.
 */
public class Image {
    /**
     * ID de l'image dans la base de donnees.
     */
    private int idImage;

    /**
     * Nom du fichier de l'image.
     */
    private String nomImage;  

    /**
     * Largeur de l'image en pixel.
     */
    private double largeurPx;

    /**
     * Hauteur de l'image en pixel.
     */
    private double hauteurPx;

    /**
     * Grossissement du microscope lors de la prise de l'image.
     */
    private double grossissement;

    /**
     * Largeur réelle de l'image.
     */
    private double largeurReelle;
    
    /*
     * id de l'operateur qui a stocker l'image
     */
    private int idOperateur;
    
    /*
     * id du produit auquel cette image est associée
     */
    private int idProduit;

	/**
     * Default constructor
     */
    public Image(String nomImage, double largeurPx, double hauteurPx, double grossissement, double largeurReelle, int idOperateur, int idProduit) {
    	this.nomImage = nomImage;
    	this.largeurPx = largeurPx;
    	this.hauteurPx = hauteurPx;
    	this.grossissement = grossissement;
    	this.largeurReelle = largeurReelle;
    	this.idOperateur = idOperateur;
    	this.idProduit = idProduit;
    	
    }

	@Override
	public String toString() {
		return "Image [idImage=" + idImage + ", nomImage=" + nomImage +  ", largeurPx=" + largeurPx
				+ ", hauteurPx=" + hauteurPx + ", grossissement=" + grossissement + ", largeurReelle=" + largeurReelle
				+ ", idOperateur=" + idOperateur + ", idProduit=" + idProduit + "]";
	}

	public int getIdImage() {
		return idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

	public String getNomImage() {
		return nomImage;
	}

	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}
	public double getLargeurPx() {
		return largeurPx;
	}

	public void setLargeurPx(int largeurPx) {
		this.largeurPx = largeurPx;
	}

	public double getHauteurPx() {
		return hauteurPx;
	}

	public void setHauteurPx(int hauteurPx) {
		this.hauteurPx = hauteurPx;
	}

	public double getGrossissement() {
		return grossissement;
	}

	public void setGrossissement(int grossissement) {
		this.grossissement = grossissement;
	}

	public double getLargeurReelle() {
		return largeurReelle;
	}

	public void setLargeurReelle(double largeurReelle) {
		this.largeurReelle = largeurReelle;
	}

	public int getIdOperateur() {
		return idOperateur;
	}

	public void setIdOperateur(int idOperateur) {
		this.idOperateur = idOperateur;
	}

	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

}