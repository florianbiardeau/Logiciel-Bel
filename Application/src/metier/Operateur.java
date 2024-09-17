package metier;

import java.util.*;

/**
 * Classe representant un operateur. Elle contient les informations lui etant lies.
 */
public class Operateur {
	
    /**
     * Default constructor
     */
    public Operateur(String nomOperateur) {
    	this.nomOperateur = nomOperateur;
    }
	
	/**
     * ID de l'operateur dans la base de donnees.
     */
    public int idOperateur;

    /**
     * Nom de la personne deposant l'image.
     */
    public String nomOperateur;

	public int getIdOperateur() {
		return idOperateur;
	}

	public void setIdOperateur(int idOperateur) {
		this.idOperateur = idOperateur;
	}

	public String getNomOperateur() {
		return nomOperateur;
	}

	public void setNomOperateur(String nomOperateur) {
		this.nomOperateur = nomOperateur;
	}

	// A SUPP
	@Override
	public String toString() {
		return "Operateur [idOperateur=" + idOperateur + ", nomOperateur=" + nomOperateur + "]";
	}
	// A SUPP
}