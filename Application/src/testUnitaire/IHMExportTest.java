package testUnitaire;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controleurs.ControleExport;
import ihm.IHMExport;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class IHMExportTest {

	private IHMExport ihmExport;
    private VBox checkBoxContainer;
    private List<BarChart<String, Number>> listeHistogrammes;

    
    @BeforeEach
    void setUp() {
        Platform.startup(() -> {});

        ControleExport controleExport = new ControleExport();
        ihmExport = new IHMExport(controleExport);

        // Initialisation des composants nécessaires pour les tests
        checkBoxContainer = new VBox();
        listeHistogrammes = new ArrayList<>();

        // Ajout d'histogrammes à la liste pour les tests
        for (int i = 0; i < 3; i++) {
            BarChart<String, Number> histogramme = new BarChart<>(new CategoryAxis(), new NumberAxis());
            histogramme.setTitle("Histogramme " + (i + 1));
            listeHistogrammes.add(histogramme);
        }
    }
	
    @Test
    void testCreerCheckBox() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        
        Platform.runLater(() -> {
            ihmExport.creerCheckBox(listeHistogrammes, checkBoxContainer);
            assertEquals(listeHistogrammes.size(), checkBoxContainer.getChildren().size());
            for (int i = 0; i < checkBoxContainer.getChildren().size(); i++) {
                Node child = checkBoxContainer.getChildren().get(i);
                assertTrue(child instanceof CheckBox);
                CheckBox checkBox = (CheckBox) child;
                assertEquals("Histogramme " + (i + 1), checkBox.getText());
            }

            latch.countDown();
        });

        assertTrue(latch.await(5, TimeUnit.SECONDS));

    }
    

}
