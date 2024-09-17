package metier;

public class Statistique {
	private int idStatistique;
	private int nbParticulesTrouvees;
	private double ratioSurfaceCouverte;
	private double moyenneAires;
	private double moyenneDiametresEquivalents;
	private double ecartTypeAires;
	private double ecartTypeDiametreEquivalent;
	private int idImage;
	
	/**
	 * Constructeur permettant de calculer directement à partir d'un ensemble de particules
	 * @param ens Ensemble dans lequel il y a les particules d'une seule image
	 */
	public Statistique(EnsembleParticules ens) {
		nbParticulesTrouvees = ens.getNombreParticules();
		ratioSurfaceCouverte = ens.getRatioSurfaceCouverte();
		moyenneAires = ens.getMoyenneAires();
		moyenneDiametresEquivalents = ens.getMoyenneDiametresEquivalents();
		ecartTypeAires = ens.getEcartTypeAires();
		ecartTypeDiametreEquivalent = ens.getEcartTypeDiametresEquivalents();
		idImage = ens.getPremiereIdImage();
	}

	public Statistique(int idStatistique, int nbParticulesTrouvees, double ratioSurfaceCouverte, double moyenneAires,
			double moyenneDiametresEquivalents, double ecartTypeAires, double ecartTypeDiametreEquivalent,
			int idImage) {
		this.idStatistique = idStatistique;
		this.nbParticulesTrouvees = nbParticulesTrouvees;
		this.ratioSurfaceCouverte = ratioSurfaceCouverte;
		this.moyenneAires = moyenneAires;
		this.moyenneDiametresEquivalents = moyenneDiametresEquivalents;
		this.ecartTypeAires = ecartTypeAires;
		this.ecartTypeDiametreEquivalent = ecartTypeDiametreEquivalent;
		this.idImage = idImage;
	}
	
	public Statistique() {
		
	}



	public int getIdStatistique() {
		return idStatistique;
	}

	public void setIdStatistique(int idStatistique) {
		this.idStatistique = idStatistique;
	}

	public int getNbParticulesTrouvees() {
		return nbParticulesTrouvees;
	}

	public void setNbParticulesTrouvees(int nbParticulesTrouvees) {
		this.nbParticulesTrouvees = nbParticulesTrouvees;
	}

	public double getRatioSurfaceCouverte() {
		return ratioSurfaceCouverte;
	}

	public void setRatioSurfaceCouverte(double ratioSurfaceCouverte) {
		this.ratioSurfaceCouverte = ratioSurfaceCouverte;
	}

	public double getMoyenneAires() {
		return moyenneAires;
	}

	public void setMoyenneAires(double moyenneAires) {
		this.moyenneAires = moyenneAires;
	}

	public double getMoyenneDiametresEquivalents() {
		return moyenneDiametresEquivalents;
	}

	public void setMoyenneDiametresEquivalents(double moyenneDiametresEquivalents) {
		this.moyenneDiametresEquivalents = moyenneDiametresEquivalents;
	}

	public double getEcartTypeAires() {
		return ecartTypeAires;
	}

	public void setEcartTypeAires(double ecartTypeAires) {
		this.ecartTypeAires = ecartTypeAires;
	}

	public double getEcartTypeDiametreEquivalent() {
		return ecartTypeDiametreEquivalent;
	}

	public void setEcartTypeDiametreEquivalent(double ecartTypeDiametreEquivalent) {
		this.ecartTypeDiametreEquivalent = ecartTypeDiametreEquivalent;
	}

	public int getIdImage() {
		return idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}
	
}
