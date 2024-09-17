package controleurs;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import metier.GenerateurFichier;
import ihm.IHMExport;
import ihm.IHMStatistiques;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * La classe ControleExport gère le processus d'exportation des histogrammes au format PNG.
 */
public class ControleExport extends Application{
	

    private static IHMExport fenetreExport;
	@FXML 
	private Button boutonExport;
	@FXML 
	private Scene nouvelleScene;
	@FXML 
	private Button retour;
	@FXML 
	private static Stage popupStage;
  	@FXML
    private VBox checkBoxContainer;
	@FXML 
	private Button okExport;
    @FXML
    private Button boutonValider;
    private static IHMStatistiques ihmStatistiques;
    private int compteurExport=0;
    private static List<BarChart<String, Number>> listeHistogrammes = new ArrayList<>();
    private static GenerateurFichier generateurFichier;
    private static IHMExport ihmExport;

	
	/**
	 * Constructeur de la classe ContoleExport 
	 * @param ihmStat : instance de la classe IHMStatistique
	 * @param listeHistogrammes : liste des histogrammes
	 */
	public ControleExport(IHMStatistiques ihmStat, List<BarChart<String, Number>> listeHistogrammes) {
		this.ihmStatistiques = ihmStat;
		this.listeHistogrammes = listeHistogrammes;
		this.ihmExport = new IHMExport(this);
	}
	
	/**
	 * Constructeur sans paramètre de la classe ControleExport
	 */
	public ControleExport() {
		this.generateurFichier = new GenerateurFichier();
	
	}
    
	
	/**
	 * methode d'initialisation de la fenetre d'exportation
	 * @param histogrammes : liste des histogrammes
	 */
	public void initialiser(List<BarChart<String, Number>> histogrammes,Stage popup) {
	    ihmExport.creerCheckBox(histogrammes,checkBoxContainer);
	    boutonValider.setOnAction(event -> ihmExport.exporterHistogrammesEnPNG(event,checkBoxContainer,popup));
	  }
		
	
	  /**
	   * methode étant liée à un bouton fxml dont son action est initalisé dans la methode initialiser()
	   */
	  @FXML
	  public void boutonValide() {		  
	  }
	  
	  /**
	   * methode étant liée au bouton de confirmation d'exportation
	   */
	  @FXML
	  public void boutonExportOk() {
			 Stage stage = (Stage) okExport.getScene().getWindow();
			 stage.close();
	  }
	  
	  /**
	   * methode étant liée au bouton de retour sur le popup d'exportation
	   */
	  @FXML
	  public void boutonRetour() {
		  Stage stage = (Stage) retour.getScene().getWindow();
		  stage.close();
		  checkBoxContainer.getChildren().clear(); 
		  listeHistogrammes.clear();
	  }
	  
	  /**
	   * methode permettant de vider la liste d'histogramme lors de la fermeture de la fenetre
	   */
	  public void fermetureFenetre() {
		  listeHistogrammes.clear();

	  }
	  
	  /**
	   * methode permettant d'exporter des histogrammes en PNG appelant une methode créant ces même fichier
	   * @param histogramme : liste des histogrammes
	   * @param cheminFichier : chemin du dossier de destination des images à exporter
	   * @param ihmExport : instance de IHMExport
	   * @return retourne un compteur de type entier
	   */
	  
	  public int exporterHistogramme(BarChart<?, ?> histogramme, String cheminFichier,IHMExport ihmExport) {
		  boolean res =generateurFichier.enregistrerHistogrammeEnPNG(histogramme, cheminFichier);
	    	
	       if (res) {
	    	   compteurExport++;
	       } else {
	           ihmExport.FenetreErreurExport();
	           return 0;
	       }
	       return compteurExport;
	   }  

  /**
     * Constructeur prenant en paramètre la fenêtre d'exportation et le générateur de fichiers.
     *
     * @param fenetreExport     la fenêtre d'exportation
     * @param generateurFichier le générateur de fichiers
     */
    public ControleExport(IHMExport fenetreExport, GenerateurFichier generateurFichier) {
        this.fenetreExport = fenetreExport;
        this.generateurFichier = generateurFichier;
    }
    
    /**
     * Gère l'action d'exportation des images.
     */
    public void main() {
        // TODO implement here
    }

    /**
     * Getter de l'attribut FenetreExport
     * @return fenetreExport
     */
	 public IHMExport getFenetreExport() {
		 return fenetreExport;
	 }
	 
	    /**
	     * Getter de l'attribut FenetreExport
	     * @return fenetreExport
	     */
		 public IHMExport getIhmExport() {
			 return ihmExport;
		 }
    
	/**
	 * Getter de l'attribut generateurFichier
	 * @return generateurFichier
	 */
	public GenerateurFichier getGenerateurFichier() 
	{
		return generateurFichier;
	}
	
	//getter nouvelleScene
	public Scene getNouvelleScene() {
		return nouvelleScene;
	}
	   /**
	 * Gère l'événement de clic sur le bouton d'export
	 */
		    
	   
    	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}