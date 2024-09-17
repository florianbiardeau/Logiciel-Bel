package application;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
//import des controleurs
import controleurs.ControleRecherche;
import ihm.IHMStatistiques;
import controleurs.ControleAnalyse;
import controleurs.ControleDepot;
import controleurs.ControleExport;
import controleurs.ControleExportCSV;


public class Controleur {
	
	 private ControleAnalyse CtrlAnalyse;
	 private ControleDepot CtrlDepot;
	 private ControleExport CtrlExport;
	 private ControleRecherche CtrlRecherche;
	 private ControleExportCSV CtrlExportCSV;
	 
	 @FXML
	 private TabPane tabPane;
	 @FXML
	 private HBox conteneurExports;
	 @FXML
	 private Button boutonExportPNG;
	 @FXML
	 private Button boutonExportCSV;
	 @FXML
	 private TextField rechercher;
	 @FXML
	 private VBox afficherResultatContainer;
	 @FXML
	 private VBox mainContainer;
	 @FXML
	 public VBox imageSelected;
	 @FXML
	 private Button btnValiderDepot;
	 @FXML
	 private Button btnSelectionImage;
	 @FXML
	 private TextField champOperateur;
	 @FXML
	 private TextField champGrossissement;
	 @FXML
	 private TextField champLargeurReelle;
	 @FXML
	 private TextField champNomProduit;
	 @FXML
	 private Label LabelGlisserImage;
	 @FXML
	 private Label LabelNomImage;
	 @FXML
	 private AnchorPane glisserDeposer;
	 @FXML
	 private Button idAnalyser;
	 @FXML 
	 private static Stage popupStage;
	 @FXML 
	 private Scene nouvelleScene;
	 @FXML
	 private SplitPane splitPane;
	 @FXML
	 private Tab tabAfficherStats;
	 
	// Variable servant a plusieurs methodes
	private String nomImage;
	private String url;
	private double largeurPx;
	private double hauteurPx;
	private java.io.File selectedFile;
	 
	 Controleur(ControleRecherche CtrlRecherche, ControleAnalyse CtrlAnalyse, ControleDepot CtrlDepot, ControleExport CtrlExport, ControleExportCSV CtrlExportCSV) {
		 this.CtrlRecherche = CtrlRecherche;
		 this.CtrlAnalyse = CtrlAnalyse;
		 this.CtrlDepot = CtrlDepot;
		 this.CtrlExport = CtrlExport;
		 this.CtrlExportCSV = CtrlExportCSV;
	 }
	 
	 @FXML
	 public void initialize() {
		 //envoyer la recherche d'image au controleur
		 CtrlRecherche.setSplitPane(splitPane);
		 CtrlRecherche.setAfficherResultatContainer(afficherResultatContainer);
		 CtrlRecherche.setImageSelectionner(imageSelected);
		 
		 CtrlRecherche.recherche("");
		 rechercher.setOnAction(e -> CtrlRecherche.recherche(rechercher.getText()));
		 
		 //actionne l'analyse des images selectionnées
		 idAnalyser.setOnAction(e -> afficherFormulaireAnalyse());
		  
		 glisserDeposer.setOnDragOver(e -> gestionnaireDragOver(e));
		 glisserDeposer.setOnDragDropped(e -> selectionImageParDrag(e));
		 
		 btnSelectionImage.setOnAction(e -> selectionImageParBouton());
		 btnValiderDepot.setOnAction(e -> validerDepot());
		 
		 //bouton d'export des resultats d'analyse
		 boutonExportPNG.setOnAction(e -> boutonExport());
		 boutonExportCSV.setOnAction(e -> afficherFormulaireCSV());
		 
		 LabelNomImage.setVisible(false);
	 }
	 
	 /**
	  * Methode d'affichage de l'objet Alert de type Information affichant le resultat du depot de l'image dans la base de donnees.
	  * @param titre Titre de l'Alert
	  * @param haut Texte se situant en haut de l'Alert
	  * @param bas Texte se situant en bas de l'Alert
	  */
	 public void afficherEtDisparaitreStatutDepot(String titre, String haut, String bas) {
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(haut);
        alert.setContentText(bas);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                if (alert.isShowing()) {
                    Platform.runLater(alert::close);
                }
            }
        };

        timer.schedule(task, 5000); // Programme la tâche pour s'exécuter après 5000 millisecondes (5 secondes)
        alert.show();
        CtrlRecherche.recherche("");
	 }
	 
	 /**
	  * Methode appelee par le bouton "Valider le depot". Elle sert a valider son depot d'image dans la base de donnees. Elle verifie si tout est en regle dans les differents champs a renseigner et dans l'image puis affiche une pop-up informant l'operateur du statut de son depot d'image. 
	  */
	 private void validerDepot() {
		 String nomOperateur = champOperateur.getText();
		 String grossissement = champGrossissement.getText();
		 String largeurReelle = champLargeurReelle.getText();
		 String nomProduit = champNomProduit.getText();

		// si tous les champs sont bien remplis
		 if ((nomOperateur != null) && (nomImage != null) && (url != null) && (largeurPx != 0) && (hauteurPx != 0) && (grossissement != null) && (largeurReelle != null) && (nomProduit != null) ) {
			 try {
				 Double grossissementDouble = Double.parseDouble(grossissement);
				 Double largeurReelleDouble = Double.parseDouble(largeurReelle);
				 
				 CtrlDepot.deposerImage(nomOperateur, nomImage,
						 largeurPx, hauteurPx, grossissementDouble, 
						 largeurReelleDouble, nomProduit);
				 
				 // copier coller l'image dans le repertoire scriptsImages
				 try {
		            // Copie du fichier source vers le répertoire de destination
					String urlFinal = System.getProperty("user.dir") + "\\scriptsImages\\" + nomImage;
		            Files.copy(Paths.get(url), Paths.get(urlFinal), StandardCopyOption.REPLACE_EXISTING);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				 
				 // on remet tout de l'image selectionnee a zero
				 nomImage = null;
				 url = null;
	             largeurPx = 0;
	             hauteurPx = 0;
	             selectedFile = null;
	             
	             // faire disparaitre Label LabelNomImage
	             LabelNomImage.setVisible(false);
	             // vider le Label LabelNomImage
	             LabelNomImage.setText(null);
	             // faire apparaitre Label LabelGlisserImage
	             LabelGlisserImage.setVisible(true);
	             
	             // vider tous les champs
	             champOperateur.setText(null);
	             champGrossissement.setText(null);
	             champLargeurReelle.setText(null);
	             champNomProduit.setText(null);
	             afficherEtDisparaitreStatutDepot("Statut de votre dépôt", "Dépôt effectué !", "L'image a bien été déposée dans la base de donnée.");
			 } catch (NumberFormatException e) {
				 afficherEtDisparaitreStatutDepot("Statut de votre dépôt", "Erreur lors du dépôt", "L'image n'a pas été déposée dans la base de donnée.\nVeillez à bien renseigner des informations correctes.");
			 }
		 } else {
			 afficherEtDisparaitreStatutDepot("Statut de votre dépôt", "Erreur lors du dépôt", "L'image n'a pas été déposée dans la base de donnée.\nVeillez à bien remplir tous les champs et à sélectionner\nune image.");
		 }
	 }
	 
	 /**
	  * Methode appelee par le bouton "Selectionner une image". Elle sert a selectionner un fichier .tif a deposer dans la base de donnees. Elle ouvre l'explorateur de fichier, restreint le choix au fichier avec l'extension .tif, et finit par afficher le nom de l'image selectionnee.
	  */
	 private void selectionImageParBouton() {
		 FileChooser fileChooser = new FileChooser();
         fileChooser.setTitle("Sélectionner un fichier");

         // Affichage de la boîte de dialogue et recuperation de l'image selectionnee
         selectedFile = fileChooser.showOpenDialog(null);

         if (selectedFile != null && selectedFile.getName().toLowerCase().endsWith(".tif")) {
         	 nomImage = selectedFile.getName();
         	 url = selectedFile.getAbsolutePath();

         	 // modifie les variables largeurPx et hauteurPx avec la largeur et la hauteur de l'image
         	 calculerDimensionImage(url);
             
             // faire disparaitre Label LabelGlisserImage
             LabelGlisserImage.setVisible(false);
             // associer a Label LabelNomImage le nom de l'image selectionne
             LabelNomImage.setText(nomImage);
             // faire apparaitre Label LabelNomImage
             LabelNomImage.setVisible(true);
         }
	 }
	 
	 /**
	  * Methode appelee lors d'un drag au dessus de la zone predefinie. C'est la methode de gestion de l'evenement de Drag Over, au dessus de la zone de Drag And Drop. Elle indique a l'utilisateur si sont fichier est accepte ou non.
	  * @param event
	  */
 	 private void gestionnaireDragOver(DragEvent event) {
        if (event.getGestureSource() != glisserDeposer && event.getDragboard().hasFiles()) {
        	List<File> files = event.getDragboard().getFiles();
            boolean contientFichierTif = files.stream().anyMatch(file -> file.getName().toLowerCase().endsWith(".tif"));
            if (contientFichierTif && files.size() == 1) {
                event.acceptTransferModes(TransferMode.COPY);
            }
        }
        event.consume();
 	 }
	 
 	 /**
	  * Methode appelee lors du drop au dessus de la zone predefinie. C'est la methode de gestion de l'evenement de Drag Dropped, au dessus de la zone de Drag And Drop. Elle sert a selectionner une image en Drag And Drop, a restreindre le choix du fichier avec l'extension .tif. Elle affiche le nom de l'image selectionnee dans la zone.
	  */
	 private void selectionImageParDrag(DragEvent event) {
		 Dragboard db = event.getDragboard();
         boolean success = false;

         if (db.hasFiles()) {
             selectedFile = db.getFiles().get(0);

   	         if (selectedFile != null && selectedFile.getName().toLowerCase().endsWith(".tif")) {
   	        	 nomImage = selectedFile.getName();
   	         	 url = selectedFile.getAbsolutePath();
   	        	 
   	         	 // modifie les variables largeurPx et hauteurPx avec la largeur et la hauteur de l'image
   	         	calculerDimensionImage(url);
   	             
   	             // faire disparaitre Label LabelGlisserImage
   	             LabelGlisserImage.setVisible(false);
   	             // associer a Label LabelNomImage le nom de l'image selectionne
   	             LabelNomImage.setText(nomImage);
   	             // faire apparaitre Label LabelNomImage
   	             LabelNomImage.setVisible(true);
   	             success = true;
   	         }
         }
         event.setDropCompleted(success);
         event.consume();
	 }

	 /**
	  * Methode servant a recuperer la largeur et la hauteur d'une image en utilisant BufferedImage pour lire le fichier.
	  * @param url Chemin d'acces a l'image
	  */
	 private void calculerDimensionImage(String url) {
		 File fichierImage = new File(url);
		 try {
             BufferedImage image = ImageIO.read(fichierImage);

             largeurPx = image.getWidth();
             hauteurPx = image.getHeight();
         } catch (IOException e) {
             e.printStackTrace();
         }
	 }
	 
	 private void afficherFormulaireAnalyse() {
		 if(!(CtrlRecherche.getListeImageSelectionner().isEmpty())) {
			//affiche la popup
				try {
					CtrlAnalyse.setIhm(new IHMStatistiques());
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popUpAfficherStats.fxml"));
					fxmlLoader.setController(CtrlAnalyse.getIhm());
					Parent root1 = (Parent) fxmlLoader.load();
					//charger le fichier css
		            root1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Stage stage = new Stage();
					stage.setTitle("Sélection des affichages souhaités");
					stage.setScene(new Scene(root1));
					stage.show();
					stage.setOnCloseRequest(e-> {
						if (CtrlAnalyse.getIhm().isAfficher()) {
							CtrlAnalyse.afficher(CtrlRecherche.getListeImageSelectionner());
							mainContainer.getChildren().clear();
							ObservableList<Node> container = mainContainer.getChildren();
							CtrlAnalyse.getIhm().afficherDiagrammes(container);
							conteneurExports.setVisible(true);
							tabAfficherStats.setDisable(false);
							tabPane.getSelectionModel().select(1);
						}
					});
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		 
	}
	 
	 	/**
		* Methode gerant l'action du clique sur le bouton exportPNG ouvrant une fenetre popup 
		*/
		public void boutonExport(){
			    
			try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/IHMExport.fxml"));
		        Parent nouvelleSceneParent = loader.load();
		        ControleExport controleur = loader.getController();	   	         
			   	popupStage = new Stage();
			   	popupStage.initModality(Modality.APPLICATION_MODAL);
			   	popupStage.setTitle("Export d'Histogrammes");
			   	nouvelleScene = new Scene(nouvelleSceneParent);
			   	popupStage.setScene(nouvelleScene);
			    controleur.initialiser(CtrlAnalyse.getIhm().getGraphiques(),popupStage);
			   	CtrlExport = CtrlAnalyse.getIhm().getControleurExport();  	   		
			   	CtrlExport.getIhmExport().setControleurExport(CtrlExport);
			   	popupStage.setOnCloseRequest(event -> {
		            CtrlExport.fermetureFenetre();});
			    popupStage.showAndWait();
		     } catch (Exception e) {
		       e.printStackTrace();
		     }
		  }
		
		/**
		 * Methode gerant l'action du clique sur le bouton exportCSV ouvrant une fenetre popup 
		 */
		public void afficherFormulaireCSV() {
			 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopUpExportCSV.fxml"));
	         fxmlLoader.setController(CtrlExportCSV.getIHM());
			try {
				Parent root1 = (Parent) fxmlLoader.load();
				//charger le fichier css
	            root1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				Stage stage = new Stage();
		        stage.setTitle("Export au format CSV");
		        stage.setScene(new Scene(root1));
		        IHMStatistiques IHM = CtrlAnalyse.getIhm();
		        VBox vbox = new VBox();
		        ArrayList<CheckBox> listCh = new ArrayList<>();
		        if(IHM.histoSPresent()) {
					CheckBox ch1 = new CheckBox();
					CheckBox ch2 = new CheckBox();
					ch1.setId("histogrammeSurfaceCumulatif");
					ch1.setText("Histogramme de Surface Cumulatif");
					ch2.setId("histogrammeSurface");
					ch2.setText("Histogramme de Surface");
					vbox.getChildren().addAll(ch1, ch2);
					listCh.add(ch1);
					listCh.add(ch2);
				}
				if(IHM.histoDPresent()) {
					CheckBox ch3 = new CheckBox();
					CheckBox ch4 = new CheckBox();
					ch3.setId("histogrammeDiametreEquivalentCumulatif");
					ch3.setText("Histogramme de Diametre Equivalent Cumulatif");
					ch4.setId("histogrammeDiametreEquivalent");
					ch4.setText("Histogramme de Diametre Equivalent");
					vbox.getChildren().addAll(ch3, ch4);
					listCh.add(ch3);
					listCh.add(ch4);
				}
				if(IHM.tabPresent()) {
					CheckBox ch5 = new CheckBox();
					ch5.setId("statistiques");
					ch5.setText("Statistiques");
					vbox.getChildren().add(ch5);
					listCh.add(ch5);
				}
				CtrlExportCSV.getIHM().setVbox(vbox);
				CtrlExportCSV.getIHM().alimenterFenetre();
				CtrlExportCSV.getIHM().alimenterCheckBox(listCh);
				CtrlExportCSV.getIHM().setHistoDiamBarChart(CtrlAnalyse.getIhm().getHistoDiam().getBc());
				CtrlExportCSV.getIHM().setHistoDiamCumBarChar(CtrlAnalyse.getIhm().getHistoDiam().getBcc());
				CtrlExportCSV.getIHM().setHistoSurfaceBarChar(CtrlAnalyse.getIhm().getHistoSurf().getBc());
				CtrlExportCSV.getIHM().setHistoSurfaceCumBarChar(CtrlAnalyse.getIhm().getHistoSurf().getBcc());
				CtrlExportCSV.getIHM().setStatistiques(CtrlAnalyse.getIhm().getTab().getTab());
		        stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
}



