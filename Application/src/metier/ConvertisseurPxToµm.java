package metier;

public class ConvertisseurPxToµm {
	public static double convertir(double lr, double lp, double v) {
		return v * lr / lp;
	}

}
