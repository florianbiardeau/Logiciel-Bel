package metier;

import java.util.*;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import metier.Diagramme;
import metier.EnsembleParticules;

/**
 * Permet l'affichage d'un tableau dans lequel toutes les Particules des particules seront affichés
 */
public class Tableau extends Diagramme {
	private TableView<TabElement> tab;
    
    public Tableau() {
    	tab = new TableView<TabElement>();
    	
    	TableColumn<TabElement, String> nomImageCol = new TableColumn<TabElement, String>("Nom de l'image");
    	nomImageCol.setCellValueFactory(new PropertyValueFactory<>("nomImage"));
    	TableColumn<TabElement, String> grossissementCol = new TableColumn<TabElement, String>("grossissement de l'image");
    	grossissementCol.setCellValueFactory(new PropertyValueFactory<>("grossissement"));
    	TableColumn<TabElement, String> nbPartCol = new TableColumn<TabElement, String>("Nombre de Particules Trouvees");
    	nbPartCol.setCellValueFactory(new PropertyValueFactory<>("nbParticulesTrouvees"));
    	TableColumn<TabElement, String> ratioSurfaceCouverteCol = new TableColumn<TabElement, String>("ratio de Surface Couverte");
    	ratioSurfaceCouverteCol.setCellValueFactory(new PropertyValueFactory<>("ratioSurfaceCouverte"));
    	TableColumn<TabElement, String> moyenneAiresCol = new TableColumn<TabElement, String>("moyenne des Aires");
    	moyenneAiresCol.setCellValueFactory(new PropertyValueFactory<>("moyenneAires"));
    	TableColumn<TabElement, String> moyenneDiametresEquivalentsCol = new TableColumn<TabElement, String>("moyenne des Diametres Equivalents");
    	moyenneDiametresEquivalentsCol.setCellValueFactory(new PropertyValueFactory<>("moyenneDiametresEquivalents"));
    	TableColumn<TabElement, String> ecartTypeAiresCol = new TableColumn<TabElement, String>("ecartType des Aires");
    	ecartTypeAiresCol.setCellValueFactory(new PropertyValueFactory<>("ecartTypeAires"));
    	TableColumn<TabElement, String> ecartTypeDiametreEquivalentCol = new TableColumn<TabElement, String>("ecartType des Diametres Equivalents");
    	ecartTypeDiametreEquivalentCol.setCellValueFactory(new PropertyValueFactory<>("ecartTypeDiametreEquivalent"));
    	TableColumn<TabElement, String> moyenneAiresPxCol = new TableColumn<TabElement, String>("moyenne des Aires en pixel");
    	moyenneAiresPxCol.setCellValueFactory(new PropertyValueFactory<>("moyenneAiresPx"));
    	TableColumn<TabElement, String> moyenneDiametresEquivalentsPxCol = new TableColumn<TabElement, String>("moyenne des Diametres Equivalents en pixel");
    	moyenneDiametresEquivalentsPxCol.setCellValueFactory(new PropertyValueFactory<>("moyenneDiametresEquivalentsPx"));
    	TableColumn<TabElement, String> ecartTypeAiresPxCol = new TableColumn<TabElement, String>("ecartType des Aires en pixel");
    	ecartTypeAiresPxCol.setCellValueFactory(new PropertyValueFactory<>("ecartTypeAiresPx"));
    	TableColumn<TabElement, String> ecartTypeDiametreEquivalentPxCol = new TableColumn<TabElement, String>("ecartType des Diametres Equivalents en pixel");
    	ecartTypeDiametreEquivalentPxCol.setCellValueFactory(new PropertyValueFactory<>("ecartTypeDiametreEquivalentPx"));
    	
    	tab.getColumns().add(nomImageCol);
    	tab.getColumns().add(grossissementCol);
    	tab.getColumns().add(nbPartCol);
    	tab.getColumns().add(ratioSurfaceCouverteCol);
    	tab.getColumns().add(moyenneAiresCol);
    	tab.getColumns().add(moyenneDiametresEquivalentsCol);
    	tab.getColumns().add(ecartTypeAiresCol);
    	tab.getColumns().add(ecartTypeDiametreEquivalentCol);
    	tab.getColumns().add(moyenneAiresPxCol);
    	tab.getColumns().add(moyenneDiametresEquivalentsPxCol);
    	tab.getColumns().add(ecartTypeAiresPxCol);
    	tab.getColumns().add(ecartTypeDiametreEquivalentPxCol);
    }
    
    /**
     * affiche le tableau sur la fenetre principale
     */
	@Override
	public void afficher(ObservableList<Node> container) {
		container.add(tab);
		
	}
	
	/**
	 * alimente les données dans le tableau qui sera affiché dans la fenêtre
	 * @param stat
	 * @param image
	 */
	public void alimenter(Statistique stat, Image image) {
		TabElement tabel = new TabElement(image.getNomImage(), image.getGrossissement(), stat.getNbParticulesTrouvees(),
				stat.getRatioSurfaceCouverte(), stat.getMoyenneAires(), stat.getMoyenneDiametresEquivalents(),
				stat.getEcartTypeAires(), stat.getEcartTypeDiametreEquivalent(), image);
		tab.getItems().add(tabel);
	}

	public TableView<TabElement> getTab() {
		return tab;
	}

}