package controleurs;

import java.util.*;

import dao.OperateurDAO;
import dao.ProduitDAO;
import javafx.stage.FileChooser;
import dao.ImageDAO;
import metier.Operateur;
import metier.Produit;
import metier.Image;

/**
 * Cette classe controle les autres. Elle instancie IHMDepot et DBImage.
 */
public class ControleDepot {

    /**
     * Default constructor
     */
    public ControleDepot() {
    }

    /**
     * Methode pour deposer une image dans la base de donnees a partir des informations rentrees par l'operateur. Pour chaque image que l'operateur souhaite deposer, elle sera appeler.
     * @param nomOperateur Nom de la personne deposant l'image.
     * @param nomImage Nom de l'image.
     * @param largeurPx Largeur de l'image en pixel.
     * @param hauteurPx Hauteur de l'image en pixel.
     * @param grossissement Grossissement du microscope lors de la prise de l'image.
     * @param largeurImage Largeur réelle de l'image.
     * @param nomProduit Nom du produit sur l'image.
     * @return Booleen qui indique si l'image a ete deposee dans la base de donnees ou non.
     */
    public void deposerImage(String nomOperateur, String nomImage,double largeurPx, double hauteurPx, double grossissement, double largeurReelle, String nomProduit) {
		// on cree un objet OperateurDAO et Operateur qu'on creer dans la bdd
    	OperateurDAO operateurDAO = new OperateurDAO();
    	Operateur operateur = new Operateur(nomOperateur);
    	operateurDAO.creer(operateur);
    	int idOperateur = operateur.getIdOperateur();
    	// on cree un objet ProduitDAO et Produit qu'on creer dans la bdd
    	ProduitDAO produitDAO = new ProduitDAO();
    	Produit produit = new Produit(nomProduit);
    	produitDAO.creer(produit);
    	int idProduit = produit.getIdProduit();
        // on cree un objet ImageDAO et Image qu'on ajoute dans la bdd
    	ImageDAO imageDAO = new ImageDAO();
    	Image image = new Image(nomImage, largeurPx, hauteurPx, grossissement, largeurReelle, idOperateur, idProduit);
        imageDAO.creer(image);
    }

}