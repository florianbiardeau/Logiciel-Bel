package ihm;

import java.io.File;

import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import controleurs.ControleExport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import metier.GenerateurFichier;

/**
 * La classe IHMExport représente une fenêtre d'exportation
 */
public class IHMExport 
{
    private static GenerateurFichier generateurFichier;
	private static ControleExport controleurExport;
	@FXML
    private VBox checkBoxContainer;
	@FXML 
	private Button retour;
	@FXML 
	private Button okExport;
	@FXML
	private Button boutonValider;
	private int compteurCheckBox;
	private int compteurExport;
	private List<BarChart<String, Number>> listeHistogrammes; 
    private List<CheckBox> listeCheckBox = new ArrayList<>();

    /**
     * constucteur de la classe IHMExport
     * @param controleur : instance de la classe ContoleExport
     */
    public IHMExport(ControleExport controleur) {
    	this.controleurExport=controleur;
    }

    /**
     * methode permettant de créer les checkbox en fonction des histogrammes passés en paramètre
     * @param listeHistogrammes : liste des histogrammes
     * @param checkBoxContainer : conteneur de type VBox
     */
    public void creerCheckBox(List<BarChart<String, Number>> listeHistogrammes,VBox checkBoxContainer) {
    	checkBoxContainer.getChildren().clear();
        int countCheckBoxes = 0; 
        // parcourt la liste des histogrammes
        for (int i = 0; i < listeHistogrammes.size(); i++) {
            BarChart<String, Number> histogramme = listeHistogrammes.get(i);
            CheckBox checkBox = new CheckBox(histogramme.getTitle());
            checkBox.setUserData(histogramme);
            // ajoute la checkbox correspondant à l'histogramme au checkBoxContainer
            checkBoxContainer.getChildren().add(checkBox); 
            countCheckBoxes++;
            checkBox.setMinWidth(150); 
        }
    	
    }
    
    /**
     * methode gerant l'action d'exportation des histogrammes lors du clique sur le bouton exporter, on va vérifier les checkbox sélectionnés lors du clique sur le bouton et si aucune checkbox n'est selectionnée alors ouvre une alert
     * @param event : action du clique sur le bouton
     * @param checkBoxContainer : conteneur de type VBox
     * @param popup : popup d'exportation de type Stage
     */
    public void exporterHistogrammesEnPNG(ActionEvent event,VBox checkBoxContainer,Stage popup) {
    	compteurCheckBox=0;
    	// booléen renvoyant true si au moins une checkbox est sélectionnée
    	boolean isSelected = checkBoxContainer.getChildren().stream()
    	        .filter(node -> node instanceof CheckBox)
    	        .map(node -> (CheckBox) node)
    	        .anyMatch(CheckBox::isSelected);

    	//test si aucune checkbox n'est sélectionnée
    	if (!isSelected) {
    	    	
    	    afficherMessageAucuneSelection();
    	} else {
    	  
	    File selectedDirectory = choisirDossierDeDestination();
	
	    if (selectedDirectory != null) {
	        for (Node node : checkBoxContainer.getChildren()) {
	            if (node instanceof CheckBox) {
	                CheckBox checkBox = (CheckBox) node;
	                if (checkBox.isSelected()) {
	                	compteurCheckBox++;
	                    BarChart<?, ?> histogramme = (BarChart<?, ?>) checkBox.getUserData();
	                    String cheminFichier = selectedDirectory.getAbsolutePath()  + checkBox.getText() + ".png";
	                    compteurExport=controleurExport.exporterHistogramme(histogramme,cheminFichier,this);
	                    popup.close();
	                    	               
	                }
	             }
	         }
	      }
       }
    	afficheFenetreValide();
    }	
    
    /**
     * compare le compteur des histogrammes exportés et des checkbox sélectionnées lors du clique sur le bouton exporter puis ouvre une popup validant l'exportation si le compte est bon
     * 
     */
    public void afficheFenetreValide() {
    	if (compteurExport==compteurCheckBox && compteurCheckBox != 0) {
    		FenetreValideExport(); 		
    	}
    	
    }
    
	/**
	 * methode permettant de choisir le dossier de destination des histogrammes à exporter
	 * @return retourne le chemin du dossier de destination
	 */
    public File choisirDossierDeDestination() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un dossier de destination");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setInitialFileName("histogrammes_");
        return fileChooser.showSaveDialog(null); 
    }

    /**
     * methode affichant une alert dans le cas où aucune checkbox n'est selectionnée
     */
    public void afficherMessageAucuneSelection() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune sélection");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner au moins un histogramme à exporter.");
        alert.showAndWait();
    }
    
    /**
     * methode ouvrant une nouvelle fenetre validant l'exportation
     */
    @FXML
    public void FenetreValideExport() {
    	openFXMLWindow("../application/IHMValideExport.fxml");  
    }
    
    /**
     * methode ouvrant une nouvelle fenetre affichant une erreur d'exportation
     */
    @FXML
    public void FenetreErreurExport() {
    	openFXMLWindow("../application/IHMErreurExport.fxml");
    }
    
    /**
     * methode permettant d'ouvrir une nouvelle fenetre en fonction du chemin passé en paramètre
     * @param fxmlFilePath : chemin du fichier fxml
     */
    public void openFXMLWindow(String fxmlFilePath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	public Button getBoutonValider() {
		return boutonValider;
	}
	
   public void setControleurExport(ControleExport controleurExport) {
        this.controleurExport = controleurExport;
    }
	
}