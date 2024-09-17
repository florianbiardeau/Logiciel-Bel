package testUnitaire;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dao.ImageDAO;
import metier.Image;

class TestImageDAO {

	ImageDAO imageDao = new ImageDAO();
	Image ref = imageDao.lire(1);
	
	@Test
	void testImageLireId() {
		Image test = new Image("113.tif",2048.0,2048.0,10.0,10.0,1,1);
		test.setIdImage(1);
		
		assert isEqual(ref, test);
	}
	@Test
	void testImageLireString() {
		ArrayList<Image> test2 = imageDao.lire("113.tif");
		
		assert isEqual(ref,test2.get(0));
	}
	@Test
	void testImageCreer() {
		String nomImage = "imageTest";
		String url = "urlTest";
		double largeurPx = 2048.0;
	    double hauteurPx = 2048.0;
	    double grossissement = 10.0;
	    double largeurReelle = 15.0;
	    int idOperateur = 1;
	    int idProduit = 1;
	    
	    //creer une nouvelle image dans la base de donnée
	    Image imagetest = new Image(nomImage,largeurPx,hauteurPx,grossissement,largeurReelle,idOperateur,idProduit);
	    Image imageCreer = imageDao.creer(imagetest);
        // recuperer son id et lire dans la bdd si l'image qui a cette id correspond a notre image qu'on a inserer
        Image imageInsere = imageDao.lire(imageCreer.getIdImage());
        
        assert isEqual(imageInsere, imagetest);
	}
	@Test
	void testImageSupp() {
		String nomImage = "imageTestSupprimer";
		String url = "urlTestSupprimer";
		double largeurPx = 2048.0;
	    double hauteurPx = 2048.0;
	    double grossissement = 10.0;
	    double largeurReelle = 15.0;
	    int idOperateur = 1;
	    int idProduit = 1;
	    
	    //creer une nouvelle image dans la base de donnée
	    Image imagetest = new Image(nomImage,largeurPx,hauteurPx,grossissement,largeurReelle,idOperateur,idProduit);
	    Image imageCreer = imageDao.creer(imagetest);
		//supprimer l'image qui a etait inserer plus tot
		imageDao.supprimer(imageCreer);
		//essayer de recuperer cette image
		Image recup = imageDao.lire(imageCreer.getIdImage());
		
		assert recup == null;
	}
    /*
     * Compare si deux image sont eguale
     */
    public static boolean isEqual(Image image1, Image image2) {
        if (image1.getIdImage() == image2.getIdImage()) {
            if (image1.getNomImage().equals(image2.getNomImage())) {
        		if (image1.getLargeurPx() == image2.getLargeurPx()) {
        			if (image1.getHauteurPx() == image2.getLargeurPx()) {
        				if (image1.getGrossissement() == image2.getGrossissement()) {
        					if (image1.getLargeurReelle() == image2.getLargeurReelle()) {
        						if (image1.getIdOperateur() == image2.getIdOperateur()) {
        							if (image1.getIdProduit() == image2.getIdProduit()) {
        	                        	return true;
        	                        }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
