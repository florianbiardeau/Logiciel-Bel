package controleurs;

import java.util.ArrayList;
import java.util.Iterator;

import dao.ImageDAO;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import metier.Image;

public class ControleRecherche {

	ImageDAO imageDao = new ImageDAO();
	
	private SplitPane splitPane;
	private VBox afficherResultatContainer;
	private VBox imageSelectionner;
	
	/**
	 * liste d'image qui contiendra les images qui correspondent a la recherche
	 */
	private ArrayList<Image> listeImage = new ArrayList<>();
	
	/**
	 * Liste d'image Selectionner
	 */
	private ArrayList<Image> listeImageSelectionner = new ArrayList<>();
	
	/**
	 * Fonction de recherche 
	 * @param recherche, string
	 */
	public void recherche(String recherche) {
		// recuperer une liste d'image qui correspondent dans la bdd
		listeImage = imageDao.lire(recherche);
		
		//Si la liste n'est pas vide alors on affiche les resultats, sinon la liste est vide donc on vide le container
		if(!listeImage.isEmpty()){
			affichageRecherche(listeImage);
		} else {
			afficherResultatContainer.getChildren().clear();
		}
	}
	/**
	 * Gere l'affichage de la section de Recherche des images
	 * @param listeImage, liste objet image
	 */
	public void affichageRecherche(ArrayList<Image> listeImage){
		afficherResultatContainer.getChildren().clear();
		//parcourir les elements
		for (Image image: listeImage){
			// creer l'element hbox contenant le nom et une checkbox pour le selection
			HBox elementImage = creerElement(image);
			//ajouter l'element au container 
			afficherResultatContainer.getChildren().add(elementImage);
		}
		
	}
	/**
	 * Gere l'affichage de la section de Selection des images
	 * @param listeImage, liste objet image
	 */
	public void affichageSelection(ArrayList<Image> listeImage){
		imageSelectionner.getChildren().clear();
		//parcourir les elements
		for (Image image: listeImage){
			// creer l'element hbox contenant le nom et une checkbox pour le selection
			HBox elementImage = creerElement(image);
			//ajouter l'element au container 
			imageSelectionner.getChildren().add(elementImage);
		}
		
	}
	/**
	 * creer l'element hbox contenant le nom et une checkbox pour une image
	 * @param image
	 * @return HBox 
	 */
	public HBox creerElement(Image image){
		HBox elementImage = new HBox();
		//creer un Label fxml pour l'afficher le nom de l'image
		Label nomImage = new Label(image.getNomImage());
		// Creer une checkbox pour pouvoir effectuer une selection d'image
		CheckBox CheckBox = new CheckBox();
		
		CheckBox.setOnAction(e -> actualiserSelection(image));
		
		//check si l'image est deja dans la liste (et si on doit "check" la box)
		for (Image imageSelectionner: listeImageSelectionner){
			if(isEqual(imageSelectionner, image)) {
				CheckBox.setSelected(true);
			}
		}
		// ajouter les elements au container
		elementImage.getChildren().add(nomImage);
		elementImage.getChildren().add(CheckBox);
		
		// ajouter un id pour le css
		elementImage.setId("hContainer");
		
		return elementImage;
	}

	/**
	 * Actualise l'affichage des section de recherche et de selection des images
	 * @param image
	 */
	public void actualiserSelection(Image image){
		boolean isRemoved = false;
    	Iterator<Image> iterator = listeImageSelectionner.iterator();
    	
    	//si l'image est deja dans la liste alors on la retire sinon on l'ajoute
    	while(iterator.hasNext()) {
    		Image imageSelect = iterator.next();
    		if(isEqual(imageSelect, image)) {
    			isRemoved = true;
    			iterator.remove();
    			break;
    		}
    	}
    	if(!isRemoved) {
    		listeImageSelectionner.add(image);
    	}
    	// afficher ou cacher la section analyser
    	if(!getListeImageSelectionner().isEmpty()) {
    		splitPane.setDividerPositions(0.6);
    	} else {
    		splitPane.setDividerPositions(1);
    	}
   		// Mettre à jour les deux containers après la suppression ou l'ajout
        affichageSelection(listeImageSelectionner);
        affichageRecherche(listeImage);
	}
	
  /**
   * compare deux objets images
   * @param image1
   * @param image2
   * @return boolean
   */
    public static boolean isEqual(Image image1, Image image2) {
        if (image1 == image2) {
            return true;
        }
        if (image1.getIdImage() == image2.getIdImage()) {
            return true;
        }
        return false;
    }
	

    public ArrayList<Image> getListeImageSelectionner() {
		return listeImageSelectionner;
	}
	public void setSplitPane(SplitPane splitPane) {
		this.splitPane = splitPane;
	}
	public void setAfficherResultatContainer(VBox afficherResultatContainer) {
		this.afficherResultatContainer = afficherResultatContainer;
	}
	public void setImageSelectionner(VBox imageSelectionner) {
		this.imageSelectionner = imageSelectionner;
	}
    

}
