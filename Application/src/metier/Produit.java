package metier;

import java.util.*;

/**
 * Classe representant un produit. Elle contient les informations lui etant lies.
 */
public class Produit {
	
	/**
     * Default constructor
     */
    public Produit(String nomProduit) {
    	this.nomProduit = nomProduit;
    }
	
	/**
     * ID de du produit dans la base de donnees.
     */
    public int idProduit;

    /**
     * Nom du produit sur l'image.
     */
    public String nomProduit;

	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public String getNomProduit() {
		return nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

}