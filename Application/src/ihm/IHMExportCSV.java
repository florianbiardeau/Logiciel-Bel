package ihm;


import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import metier.GenerateurFichierCSV;
import metier.TabElement;

public class IHMExportCSV {
	@FXML
	private Button export;
	@FXML
	private Button retour;
	@FXML
	private AnchorPane anchorMain;

	private VBox vbox;
	
	private boolean valideStatistiques;
	private boolean valideHistogrammeSurface;
	private boolean valideHistogrammeSurfaceCumulatif;
	private boolean valideHistogrammeDiametreEquivalent;
	private boolean valideHistogrammeDiametreEquivalentCumulatif;
	
	private BarChart histoDiamBarChart;
	private BarChart histoDiamCumBarChar;
	private BarChart histoSurfaceBarChar;
	private BarChart histoSurfaceCumBarChar;
	
	private TableView<TabElement> statistiques;
	
	private GenerateurFichierCSV gen;

	/**
	 * constructeur de la classe IHMExportCSV
	 */
	public IHMExportCSV() {
		gen = new GenerateurFichierCSV();
	}
	
	/**
	 * methode permetant l'initialisation des bouton de la fenetre popup exportCSV
	 */
	@FXML
	 public void initialize() {
		 export.setOnAction(e -> export());
		 retour.setOnAction(e -> boutonRetour());
	 }
	
	/**
	 * Bouton permettant la fermeture de la fenetre popup exportCSV
	 */
	@FXML
	  public void boutonRetour() {
		  Stage stage = (Stage) retour.getScene().getWindow();
		  stage.close();
	  }
	
	
	/**
	 * setter de l'attribut valideStatistiques
	 */
	public void cocheStatistiques() {
		this.valideStatistiques = !this.valideStatistiques;
	}
	/**
	 * setter de l'attribut valideHistogrammeSurface
	 */
	public void cocheHistogrammeSurface() {
		this.valideHistogrammeSurface = !this.valideHistogrammeSurface;
	}
	/**
	 * setter de l'attribut valideHistogrammeSurfaceCumulatif
	 */
	public void cocheHistogrammeSurfaceCumulatif() {
		this.valideHistogrammeSurfaceCumulatif = !this.valideHistogrammeSurfaceCumulatif;
	}
	/**
	 * setter de l'attribut valideHistogrammeDiametreEquivalent
	 */
	public void cocheHistogrammeDiametreEquivalent() {
		this.valideHistogrammeDiametreEquivalent = !this.valideHistogrammeDiametreEquivalent;
	}
	/**
	 * setter de l'attribut valideHistogrammeDiametreEquivalentCumulatif
	 */
	public void cocheHistogrammeDiametreEquivalentCumulatif() {
		this.valideHistogrammeDiametreEquivalentCumulatif = !this.valideHistogrammeDiametreEquivalentCumulatif;
	}
	
	
	/**
	 * setter de l'attribut vbox
	 */
	public void setVbox(VBox vbox) {
		this.vbox = vbox;
	}
	
	/**
	 * methode permettant d'alimenter la fenetre popup exportCSV
	 */
	public void alimenterFenetre() {
		anchorMain.getChildren().add(vbox);
	}
	
	/**
	 * methode permettant alimenter les checkbox en fonction de leur Id
	 * @param checkbox parametre de type ArrayList<CheckBox> qui représente les CheckBox à alimenter
	 */
	public void alimenterCheckBox(ArrayList<CheckBox> checkbox) {
		Insets insets = new Insets(10, 10, 10, 10);
		for (CheckBox CheckBox : checkbox) {
			CheckBox.setPadding(insets);
			if(CheckBox.getId() == "statistiques") {
				CheckBox.setOnAction(e -> cocheStatistiques());
			}
			if(CheckBox.getId() == "histogrammeSurface") {
				CheckBox.setOnAction(e -> cocheHistogrammeSurface());
			}
			if(CheckBox.getId() == "histogrammeSurfaceCumulatif") {
				CheckBox.setOnAction(e -> cocheHistogrammeSurfaceCumulatif());
			}
			if(CheckBox.getId() == "histogrammeDiametreEquivalent") {
				CheckBox.setOnAction(e -> cocheHistogrammeDiametreEquivalent());
			}
			if(CheckBox.getId() == "histogrammeDiametreEquivalentCumulatif") {
				CheckBox.setOnAction(e -> cocheHistogrammeDiametreEquivalentCumulatif());
			}
		}
	}
	
	
	/**
	 * getter de l'attribut valideStatistiques
	 * @return un booleen qui correspond à l'etat de la CheckBox (coche ou non)
	 */
	public boolean isValideStatistiques() {
		return valideStatistiques;
	}

	/**
	 * getter de l'attribut valideHistogrammeSurface
	 * @return un booleen qui correspond à l'etat de la CheckBox (coche ou non)
	 */
	public boolean isValideHistogrammeSurface() {
		return valideHistogrammeSurface;
	}

	/**
	 * getter de l'attribut valideHistogrammeSurfaceCumulatif
	 * @return un booleen qui correspond à l'etat de la CheckBox (coche ou non)
	 */
	public boolean isValideHistogrammeSurfaceCumulatif() {
		return valideHistogrammeSurfaceCumulatif;
	}

	/**
	 * getter de l'attribut valideHistogrammeDiametreEquivalent
	 * @return un booleen qui correspond à l'etat de la CheckBox (coche ou non)
	 */
	public boolean isValideHistogrammeDiametreEquivalent() {
		return valideHistogrammeDiametreEquivalent;
	}

	/**
	 * getter de l'attribut valideHistogrammeDiametreEquivalentCumulatif
	 * @return un booleen qui correspond à l'etat de la CheckBox (coche ou non)
	 */
	public boolean isValideHistogrammeDiametreEquivalentCumulatif() {
		return valideHistogrammeDiametreEquivalentCumulatif;
	}
	
	
	/**
	 * setter de l'atribut histoDiamBarChart
	 * @param barChart parametre de type BarChart qui represente un histogramme
	 */
	public void setHistoDiamBarChart(BarChart barChart) {
		this.histoDiamBarChart = barChart;
	}
	
	/**
	 * setter de l'atribut histoDiamCumBarChar
	 * @param barChart parametre de type BarChart qui represente un histogramme
	 */
	public void setHistoDiamCumBarChar(BarChart barChart) {
		this.histoDiamCumBarChar = barChart;
	}
	
	/**
	 * setter de l'atribut histoSurfaceBarChar
	 * @param barChart parametre de type BarChart qui represente un histogramme
	 */
	public void setHistoSurfaceBarChar(BarChart barChart) {
		this.histoSurfaceBarChar = barChart;
	}
	
	/**
	 * setter de l'atribut histoSurfaceCumBarChar
	 * @param barChart parametre de type BarChart qui represente un histogramme
	 */
	public void setHistoSurfaceCumBarChar(BarChart barChart) {
		this.histoSurfaceCumBarChar = barChart;
	}
	
	/**
	 * setter de l'atribut statistiques
	 * @param statistiques parametre de type TableView<TabElement> qui represente le tableau de statistiques d'une image
	 */
	public void setStatistiques(TableView<TabElement> statistiques) {
		this.statistiques = statistiques;
	}
	
	
	
	/**
     * methode ouvrant une nouvelle fenetre validant l'exportation
     */
    @FXML
    public void FenetreValideExport() {
    	openFXMLWindow("../application/IHMValideExport.fxml");  
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
  
    
    /**
     * methode qui s'active lorsque le bouton export est actionné, qui se sert de getter afin
     * d'actionner ou non les methodes qui exporte les différents histogrammes ou les statistiques
     */
	public void export() {
		if(isValideHistogrammeDiametreEquivalent()) {
			exportHistogramme(this.histoDiamBarChart, "HistoDiamEq.csv", "pourcentage de particule");
		}
		if (isValideHistogrammeDiametreEquivalentCumulatif()){
			exportHistogramme(this.histoDiamCumBarChar, "HistoDiamEqCumulatif.csv", "pourcentage cumulatif de particule");
		}
		if(isValideHistogrammeSurface()) {
			exportHistogramme(this.histoSurfaceBarChar, "HistoSur.csv", "pourcentage de particule");
		}
		if(isValideHistogrammeSurfaceCumulatif()) {
			exportHistogramme(this.histoSurfaceCumBarChar, "HistoSurCumulatif.csv", "pourcentage cumulatif de particule");
		}
		if(isValideStatistiques()) {
			exportStatistiques(this.statistiques);
		}
		if(isValideHistogrammeDiametreEquivalent() || isValideHistogrammeDiametreEquivalentCumulatif()
			|| isValideHistogrammeSurface() || isValideHistogrammeSurfaceCumulatif() || isValideStatistiques()) {
			FenetreValideExport();
		}else {
			afficherMessageAucuneSelection();
		}
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
	 * methode qui sert a exporter les differents hisotgrammes
	 * @param histo parametre de type BarChart<String,Number> qui represente un histogramme
	 * @param nom parametre de type String qui represente le nom du fichier CSV
	 * @param colonne parametre de type String qui represente le nom de la seconde colonne du fichier CSV
	 */
	public void exportHistogramme(BarChart<String,Number> histo, String nom, String colonne) {
		ArrayList<String> intervalles = new ArrayList<>();
		ArrayList<Number> pourcentParticule = new ArrayList<>();
		ObservableList<Series<String, Number>> list = histo.getData();
		for (XYChart.Series<String, Number> chartSeries : histo.getData()) {
            for (XYChart.Data<String, Number> data : chartSeries.getData()) {
                String intervalle = data.getXValue();
                Number pourcentage = data.getYValue();
                intervalles.add(intervalle);
                pourcentParticule.add(pourcentage);
            }
        }
		ArrayList<Integer> numberEntier = new ArrayList<>();
		for (Number PourcentParticule : pourcentParticule) {
			int valeurEntiere = PourcentParticule.intValue();
			numberEntier.add(valeurEntiere);
		}
		gen.creerFichierHistoCSV(intervalles, numberEntier, nom, colonne);
	}
	
	/**
	 * methode qui sert a exporter les statistiques
	 * @param statistiques parametre de type TableView<TabElement> qui represente le tableau de statistiques d'une image
	 */
	public void exportStatistiques(TableView<TabElement> statistiques) {
		ArrayList<String> nomImage = new ArrayList<>();
		ArrayList<Double> grossissement = new ArrayList<>();
		ArrayList<Integer> nbParticuleTrouve = new ArrayList<>();
		ArrayList<Double> ratioSurfaceCouverte = new ArrayList<>();
		ArrayList<Double> moyenneAiresPx = new ArrayList<>();
		ArrayList<Double> moyenneDiametresEquivalentsPx = new ArrayList<>();
		ArrayList<Double> ecartTypeAiresPx = new ArrayList<>();
		ArrayList<Double> ecartTypeDiametreEquivalentPx = new ArrayList<>();
		ArrayList<Double> moyenneAires = new ArrayList<>();
		ArrayList<Double> moyenneDiametresEquivalents = new ArrayList<>();
		ArrayList<Double> ecartTypeAires = new ArrayList<>();
		ArrayList<Double> ecartTypeDiametreEquivalent = new ArrayList<>();
		for (TabElement element : statistiques.getItems()) {
			nomImage.add(element.getNomImage());
			grossissement.add(element.getGrossissement());
			nbParticuleTrouve.add(element.getNbParticulesTrouvees());
			ratioSurfaceCouverte.add(element.getRatioSurfaceCouverte());
			moyenneAiresPx.add(element.getMoyenneAiresPx());
			moyenneDiametresEquivalentsPx.add(element.getMoyenneDiametresEquivalentsPx());
			ecartTypeAiresPx.add(element.getEcartTypeAiresPx());
			ecartTypeDiametreEquivalentPx.add(element.getEcartTypeDiametreEquivalentPx());
			moyenneAires.add(element.getMoyenneAires());
			moyenneDiametresEquivalents.add(element.getMoyenneDiametresEquivalents());
			ecartTypeAires.add(element.getEcartTypeAires());
			ecartTypeDiametreEquivalent.add(element.getEcartTypeDiametreEquivalent());
        }
		gen.creerFichierStatistiquesCSV(nomImage, grossissement, nbParticuleTrouve, ratioSurfaceCouverte, moyenneAiresPx, moyenneDiametresEquivalentsPx, ecartTypeAiresPx, ecartTypeDiametreEquivalentPx, moyenneAires, moyenneDiametresEquivalents, ecartTypeAires, ecartTypeDiametreEquivalent);
	}
}