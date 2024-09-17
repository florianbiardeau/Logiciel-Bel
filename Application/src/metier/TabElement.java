package metier;

public class TabElement {
	public String nomImage;
	public double grossissement;
	private int nbParticulesTrouvees;
	private double ratioSurfaceCouverte;
	private double moyenneAiresPx;
	private double moyenneDiametresEquivalentsPx;
	private double ecartTypeAiresPx;
	private double ecartTypeDiametreEquivalentPx;
	private double moyenneAires;
	private double moyenneDiametresEquivalents;
	private double ecartTypeAires;
	private double ecartTypeDiametreEquivalent;
	
	public TabElement(String nomImage, double grossissement, int nbParticulesTrouvees, double ratioSurfaceCouverte,
			double moyenneAiresPx, double moyenneDiametresEquivalentsPx, double ecartTypeAiresPx,
			double ecartTypeDiametreEquivalentPx,Image image ) {
		this.nomImage = nomImage;
		this.grossissement = grossissement;
		this.nbParticulesTrouvees = nbParticulesTrouvees;
		this.ratioSurfaceCouverte = ratioSurfaceCouverte;
		this.moyenneAiresPx = moyenneAiresPx;
		this.moyenneDiametresEquivalentsPx = moyenneDiametresEquivalentsPx;
		this.ecartTypeAiresPx = ecartTypeAiresPx;
		this.ecartTypeDiametreEquivalentPx = ecartTypeDiametreEquivalentPx;
		double lr = image.getLargeurReelle();
		double lp = image.getLargeurPx();
		this.moyenneAires = ConvertisseurPxToµm.convertir(lr, lp, moyenneAiresPx);
		this.moyenneDiametresEquivalents = ConvertisseurPxToµm.convertir(lr, lp, moyenneDiametresEquivalentsPx);
		this.ecartTypeAires = ConvertisseurPxToµm.convertir(lr, lp, ecartTypeAiresPx);
		this.ecartTypeDiametreEquivalent = ConvertisseurPxToµm.convertir(lr, lp, ecartTypeDiametreEquivalentPx);
	}

	public String getNomImage() {
		return nomImage;
	}

	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}

	public double getGrossissement() {
		return grossissement;
	}

	public void setGrossissement(int grossissement) {
		this.grossissement = grossissement;
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

	public double getMoyenneAiresPx() {
		return moyenneAiresPx;
	}

	public void setMoyenneAiresPx(double moyenneAiresPx) {
		this.moyenneAiresPx = moyenneAiresPx;
	}

	public double getMoyenneDiametresEquivalentsPx() {
		return moyenneDiametresEquivalentsPx;
	}

	public void setMoyenneDiametresEquivalentsPx(double moyenneDiametresEquivalentsPx) {
		this.moyenneDiametresEquivalentsPx = moyenneDiametresEquivalentsPx;
	}

	public double getEcartTypeAiresPx() {
		return ecartTypeAiresPx;
	}

	public void setEcartTypeAiresPx(double ecartTypeAiresPx) {
		this.ecartTypeAiresPx = ecartTypeAiresPx;
	}

	public double getEcartTypeDiametreEquivalentPx() {
		return ecartTypeDiametreEquivalentPx;
	}

	public void setEcartTypeDiametreEquivalentPx(double ecartTypeDiametreEquivalentPx) {
		this.ecartTypeDiametreEquivalentPx = ecartTypeDiametreEquivalentPx;
	}

	public void setGrossissement(double grossissement) {
		this.grossissement = grossissement;
	}
	
	
	
	
}
