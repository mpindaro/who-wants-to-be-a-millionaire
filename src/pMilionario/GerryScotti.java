package pMilionario;

public class GerryScotti {
	private static String path;
	private int listagerry[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

	public GerryScotti(String path) {
		this.path=path;
	}

	public String getGerryIntroduzione() {
		return path + listagerry[7] + ".png";
	}

	public String getGerryFormale() {
		return path + listagerry[1] + ".png";
	}

	public String getGerryDomanda() {
		return path + listagerry[4] + ".png";
	}

	public String getGerryLaAccendiamo() {
		return path + listagerry[0] + ".png";
	}

	public String getGerryAttesaRisposta() {
		return path + listagerry[10] + ".png";
	}

	public String getGerryPenserioso1() {
		return path + listagerry[0] + ".png";
	}

	public String getGerryPenserioso2() {
		return path + listagerry[2] + ".png";
	}

	public String getGerryPenserioso3() {
		return path + listagerry[9] + ".png";
	}

	public String getGerryPenserioso4() {
		return path + listagerry[10] + ".png";
	}

	public String getGerryRispostaGiusta() {
		return path + listagerry[5] + ".png";
	}

	public String getGerryRispostaSbagliata() {
		return path + listagerry[6] + ".png";
	}

	public String getGerryVincita() {
		return path + listagerry[2] + ".png";
	}

	public String getGerryPerdita() {
		return path + listagerry[8] + ".png";
	}

}
