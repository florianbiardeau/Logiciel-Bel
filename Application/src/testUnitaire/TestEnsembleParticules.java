package testUnitaire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import metier.EnsembleParticules;
import metier.Particule;

class TestEnsembleParticules {
	public EnsembleParticules ens;
	public EnsembleParticules ens2;

	@BeforeEach
	void setUp() throws Exception {
		this.ens = new EnsembleParticules();
		Particule p1 = new Particule(0, 20, 50, 10, 60, 20, 15, 55, 90, 10, 10, 10, 1);
		Particule p2 = new Particule(1, 30, 50, 10, 60, 20, 15, 55, 90, 10, 10, 10, 1);
		Particule p3 = new Particule(2, 40, 50, 10, 60, 20, 15, 55, 90, 10, 10, 10, 1);
		Particule p4 = new Particule(3, 50, 50, 10, 60, 20, 15, 55, 90, 10, 10, 10, 1);
		Particule p5 = new Particule(4, 60, 50, 10, 60, 20, 15, 55, 90, 10, 10, 10, 1);
		
		ens.ajouterParticule(p1);ens.ajouterParticule(p2);ens.ajouterParticule(p3);ens.ajouterParticule(p4);ens.ajouterParticule(p5);
		ens2 = new EnsembleParticules();
	}

	@Test
	void testEstVide() {
		assertTrue(!ens.estVide());
		assertTrue(ens2.estVide());
	}
	
	@Test
	void testEffacer() {
		//on vide l'ensemble déja rempli
		ens.effacer();
		assertTrue(ens.estVide());
		//on vérifi qu'un ensemble déja vide ne peut pas etre vidé
		ens2.effacer();
		assertTrue(ens2.estVide());
	}
	
	@Test 
	void testAjouterParticule(){
		Particule p1 = new Particule(0, 20, 50, 10, 60, 20, 15, 55, 90, 10, 10, 10, 1);
		ens2.ajouterParticule(p1);
		assertEquals(1, ens2.getNombreParticules());
		assertEquals(5, ens.getNombreParticules());
	}
	
	@Test
	void testAjoutesParticules() {
		//on veut ici ajouter plusieurs particules qui viennent d'un autre ensemble
		Particule p1 = new Particule(0, 20, 50, 10, 60, 20, 15, 55, 90, 10, 10, 10, 1);
		ens2.ajouterParticule(p1);
		ens2.ajouterParticules(ens);
		assertEquals(6, ens2.getNombreParticules());
		ens.ajouterParticules(ens2);
		//et si l'ensemble est vide
		EnsembleParticules ens3 = new EnsembleParticules();
		ens2.ajouterParticules(ens3);
		assertEquals(6, ens2.getNombreParticules());
	}
	
	@Test
	void testGetNombreParticules() {
		assertEquals(5, ens.getNombreParticules());
		assertEquals(0, ens2.getNombreParticules());
		Particule p1 = new Particule(0, 20, 50, 10, 60, 20, 15, 55, 90, 10, 10, 10, 1);
		ens2.ajouterParticule(p1);
		assertEquals(1, ens2.getNombreParticules());
	}
	
	@Test
	void testGetPremiereIdImage() {
		assertEquals(1 ,ens.getPremiereIdImage());
		//on test si l'ensemble est vide
		assertEquals(-1, ens2.getPremiereIdImage());
		Particule p1 = new Particule(0, 20, 50, 10, 60, 20, 15, 55, 90, 10, 10, 10, 10);
		ens2.ajouterParticule(p1);
		assertEquals(10,ens2.getPremiereIdImage());
	}
	
	@Test
	void testGetRatioSurfaceCouverte() {
		assertEquals(0.4, ens.getRatioSurfaceCouverte());
	}
	
	@Test
	void testGetMoyenneAires() {
		assertEquals(40, ens.getMoyenneAires());
	}
	
	@Test
	void testGetMoyenneDiametresEquivalents() {
		assertEquals(10, ens.getMoyenneDiametresEquivalents());
	}
	
	@Test
	void testGetEcartTypeAires() {
		double moy = ens.getMoyenneAires();
		double somme = Math.pow(20-moy,2) + Math.pow(30-moy,2)+Math.pow(40-moy,2)+Math.pow(50-moy,2)+Math.pow(60-moy,2);
		double ecartType = Math.sqrt(somme/ ens.getNombreParticules());
		assertEquals(ecartType, ens.getEcartTypeAires());
	}
	
	@Test
	void testGetEcartTypeDiametresEquivalents() {
		double moy = ens.getMoyenneDiametresEquivalents();
		double somme = Math.pow(10-moy,2) + Math.pow(10-moy,2)+Math.pow(10-moy,2)+Math.pow(10-moy,2)+Math.pow(10-moy,2);
		double ecartType = Math.sqrt(somme/ ens.getNombreParticules());
		assertEquals(ecartType, ens.getEcartTypeDiametresEquivalents());
	}
	
	@Test
	void testGetMinSurface() {
		assertEquals(20, ens.getMinSurface());
		//si il n'y a pas de valeurs renvoi +infini
		assertEquals(Double.POSITIVE_INFINITY, ens2.getMinSurface());
	}
	
	@Test
	void testGetMinDiametre() {
		assertEquals(10, ens.getMinDiametre());
		//si il n'y a pas de valeurs renvoi +infini
		assertEquals(Double.POSITIVE_INFINITY, ens2.getMinDiametre());
		ens.ajouterParticule(new Particule(0, 20, 50, 10, 60, 20, 15, 55, 90, 10, 10, 5, 10));
		assertEquals(5, ens.getMinDiametre());
	}
	
	@Test
	void testGetMaxSurface() {
		assertEquals(60, ens.getMaxSurface());
		//si il n'y a pas de valeurs renvoi +infini
		assertEquals(Double.NEGATIVE_INFINITY, ens2.getMaxSurface());
	}
	
	@Test
	void testGetMaxDiametre() {
		assertEquals(10, ens.getMaxDiametre());
		//si il n'y a pas de valeurs renvoi +infini
		assertEquals(Double.NEGATIVE_INFINITY, ens2.getMaxDiametre());
		ens.ajouterParticule(new Particule(0, 20, 50, 10, 60, 20, 15, 55, 90, 10, 10, 50, 10));
		assertEquals(50, ens.getMaxDiametre());
	}

}
