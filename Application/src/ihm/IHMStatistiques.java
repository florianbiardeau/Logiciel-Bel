package ihm;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import controleurs.ControleExport;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import metier.Diagramme;
import metier.EnsembleParticules;
import metier.HistogrammeDiametre;
import metier.HistogrammeSurface;
import metier.Image;
import metier.Statistique;
import metier.Tableau;

/**
 * Vue permettant l'affichage des différents diagrammes souhaités pour afficher les statistiques, Propose aussi les différents boutons utiles à l'utilisateur
 */
public class IHMStatistiques {
	private static ControleExport controleurExport;
	private ArrayList<Diagramme> listeDiagrammes;
    private int nbIntervallesHS;
    private int nbIntervallesHD;
    private Tableau tab;
    private HistogrammeDiametre histoDiam;
    private HistogrammeSurface histoSurf;
    private boolean slideDisHS;
    private boolean slideDisHD;
    private boolean tabCheck;
    private boolean afficher;
    private ArrayList<BarChart<String,Number>> listeGraphiques;

	 @FXML
	 private Button idAfficherButton;
	 @FXML
	 private CheckBox idHScheck;
	 @FXML
	 private Slider idSliderHS;
	 @FXML
	 private Label idLabelHS;
	 @FXML
	 private CheckBox idHDcheck;
	 @FXML
	 private Slider idSliderHD;
	 @FXML
	 private Label idLabelHD;
	 @FXML
	 private CheckBox idTABcheck;
	 @FXML
	 private HBox conteneurBar;
    

    public IHMStatistiques() {
		this.listeDiagrammes = new ArrayList<Diagramme>();
		this.tab = new Tableau();
		this.histoDiam = new HistogrammeDiametre();
		this.histoSurf = new HistogrammeSurface();
		slideDisHS = true;
	    slideDisHD = true;
	    tabCheck = true;
	    afficher = false;
	    listeGraphiques = new ArrayList<BarChart<String,Number>>();
	    controleurExport = new ControleExport(this,listeGraphiques);
	}

	/**
     * Permet d'afficher les différents diagrammes de statistiques qui sont dans la liste listeDiagrammes
	 * @param container 
     */
    public void afficherDiagrammes(ObservableList<Node> container) {
        // TODO implement here
    	for (Diagramme diag : listeDiagrammes) {
			diag.afficher(container);
		}
    }
    
    /**
     * ajoute les statistiques d'une image à mettre dans le tableau
     * @param stat
     * @param image 
     */
    public void alimenterTableau(Statistique stat, Image image) {
    	tab.alimenter(stat, image);
    }
    
    /**
     * permet l'alimentation des histogrammes de surface
     * @param ens
     */
    public void alimenterHistoS(EnsembleParticules ens) {
    	histoSurf.alimenterHistoSurface(ens, nbIntervallesHS);
    	histoSurf.alimenterHistoSurfaceCumu(ens);
    }
    
    /**
     * permet l'alimentation des histogrammes de diamètres équivalents
     * @param ens
     */
    public void alimenterHistoD(EnsembleParticules ens) {
    	histoDiam.alimenterHistoDiametre(ens, nbIntervallesHD);
    	histoDiam.alimenterHistoDiametreCumu(ens);
    }

    /**
     * permet d'ajouter un diagramme dans la liste listeDiagrammes
     * @param diag Diagram à ajouter
     */
    public void ajouter(Diagramme diag) {
        listeDiagrammes.add(diag);
    }

    /**
     * Gère l'événement de clic sur le bouton d'export
     */
    public void boutonExport() {
    }
    
    public boolean tabPresent() {
    	for (Diagramme diag : listeDiagrammes) {
			if (diag instanceof Tableau) {
				return true;
			}
		}return false;
    }
    
    public boolean histoSPresent() {
    	for (Diagramme diag : listeDiagrammes) {
			if (diag instanceof HistogrammeSurface) {
				return true;
			}
		}return false;
    }
    
    public boolean histoDPresent() {
    	for (Diagramme diag : listeDiagrammes) {
			if (diag instanceof HistogrammeDiametre) {
				return true;
			}
		}return false;
    }
    
	public ArrayList<Diagramme> getListeDiagrammes() {
		return listeDiagrammes;
	}

	public void setListeDiagrammes(ArrayList<Diagramme> listeDiagrammes) {
		this.listeDiagrammes = listeDiagrammes;
	}
	
	public int getNbIntervallesHS() {
		return nbIntervallesHS;
	}

	public void setNbIntervallesHS(int nbIntervallesHS) {
		this.nbIntervallesHS = nbIntervallesHS;
	}

	public int getNbIntervallesHD() {
		return nbIntervallesHD;
	}

	public void setNbIntervallesHD(int nbIntervallesHD) {
		this.nbIntervallesHD = nbIntervallesHD;
	}

	public Tableau getTab() {
		return tab;
	}

	public void setTab(Tableau tab) {
		this.tab = tab;
	}

	public HistogrammeDiametre getHistoDiam() {
		return histoDiam;
	}

	public void setHistoDiam(HistogrammeDiametre histoDiam) {
		this.histoDiam = histoDiam;
	}

	public HistogrammeSurface getHistoSurf() {
		return histoSurf;
	}

	public void setHistoSurf(HistogrammeSurface histoSurf) {
		this.histoSurf = histoSurf;
	}
	
	public boolean isAfficher() {
		return afficher;
	}

	@FXML
	 public void initialize() {
		//permet de choisir le nombre d'intervalles
		idHScheck.setOnAction(e -> {
			if (slideDisHS) {idSliderHS.setDisable(false);slideDisHS=false;}
			else {idSliderHS.setDisable(true);slideDisHS=true;}
		});
		//permet d'actualiser le label avec la valeure du Slider
		idSliderHS.valueProperty().addListener(new ChangeListener<Number>() {
	         public void changed(ObservableValue <?extends Number>observable, Number oldValue, Number newValue){
	             idLabelHS.setText(String.valueOf(Math.round(newValue.floatValue())));
	         }
		});
		//permet de choisir le nombre d'intervalles
		idHDcheck.setOnAction(e -> {
			if (slideDisHD) {idSliderHD.setDisable(false);slideDisHD=false;}
			else {idSliderHD.setDisable(true);slideDisHD=true;}
		});
		//permet d'actualiser le label avec la valeure du Slider
		idSliderHD.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue <?extends Number>observable, Number oldValue, Number newValue){
				idLabelHD.setText(String.valueOf(Math.round(newValue.floatValue())));
			}
		});
		//permet de demander l'affichage du tableau
		idTABcheck.setOnAction(e -> {
			if (tabCheck) tabCheck=false;
			else tabCheck=true;
		});
		//lance l'analyse et l'affichage
		idAfficherButton.setOnAction(e -> {
			//ajoute les diagrammes dans la liste
			if (!slideDisHS) {
				nbIntervallesHS = Integer.valueOf(idLabelHS.getText());
				if (!histoSPresent())listeDiagrammes.add(histoSurf);
				}
			if (!slideDisHD) {
				nbIntervallesHD = Integer.valueOf(idLabelHD.getText());
				if (!histoDPresent())listeDiagrammes.add(histoDiam);
				}
			if (!tabCheck && !tabPresent())listeDiagrammes.add(tab);
			
			afficher = true;
			Stage stage = (Stage) idAfficherButton.getScene().getWindow();
			EventHandler<WindowEvent> closeRequestHandler = stage.getOnCloseRequest();
			//simule la fermeture de la fenetre pour declencher le setOnCloseRequest() du Controleur principale
	        if (closeRequestHandler != null) {
	            closeRequestHandler.handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	        }
	        stage.close();
		});
	}
	
	public ArrayList<BarChart<String,Number>> getGraphiques(){
		if(histoDPresent()) {
			listeGraphiques.add(histoDiam.getBc());
			listeGraphiques.add(histoDiam.getBcc());
		}
		if(histoSPresent()) {
			listeGraphiques.add(histoSurf.getBc());
			listeGraphiques.add(histoSurf.getBcc());
		}
		return listeGraphiques;
	}
	
    public ControleExport getControleurExport() {
        return controleurExport;
    }

}