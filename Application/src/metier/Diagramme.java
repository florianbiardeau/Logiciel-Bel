package metier;

import java.util.*;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * Permet la généralisation des différents diagrammes (Histogrammes et tableau)
 */
public abstract class Diagramme {

    /**
     * Default constructor
     */
    public Diagramme() {
    }
    
    public abstract void afficher(ObservableList<Node> container);

}