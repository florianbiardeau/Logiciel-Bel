package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
// import des controleurs
import controleurs.ControleAnalyse;
import controleurs.ControleDepot;
import controleurs.ControleExport;
import controleurs.ControleRecherche;
import controleurs.ControleExportCSV;

//--module-path "C:\Program Files\javafx-sdk\lib" --add-modules javafx.controls,javafx.fxml,javafx.swing

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	try {
    		// instancier les differents controleurs
    		ControleRecherche CtrlRecherche = new ControleRecherche();
    		ControleAnalyse CtrlAnalyse = new ControleAnalyse();
    		ControleDepot CtrlDepot = new ControleDepot();
    		ControleExport CtrlExport = new ControleExport();
    		ControleExportCSV CtrlExportCSV = new ControleExportCSV();
    		
    		FXMLLoader root = new FXMLLoader(getClass().getResource("MainScene.fxml"));

    		//Initialisere le controleur principale
    		root.setController(new Controleur(CtrlRecherche, CtrlAnalyse, CtrlDepot, CtrlExport, CtrlExportCSV));
    		
            Scene scene = new Scene(root.load());
            
            //charger le fichier css
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            // Configurer la scène et afficher la fenêtre
            primaryStage.setScene(scene);
            
            //titre de la fenetre
            primaryStage.setTitle("Application");
            
            //dimension de la fenetre
            primaryStage.setWidth(1280);
            primaryStage.setHeight(720);
            
            //affichage de la fenetre
            primaryStage.show();
            
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    public static void main(String[] args) {
        launch(args);
    } 
}
