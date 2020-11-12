package pMilionario;

public class Domande {
	private int partita;
	private static final int LPARTITA = 3;
	private int sequenza;
	private static final int LSEQUENZA = 2;
	private String domanda;
	private static final int LDOMANDA = 120;
	private String a, b, c, d;
	private static final int LRISPOSTE = 100;
	private int corretta;
	private static final int LESATTA = 1;
	private String aiutocasa;
	private static final int LAIUTOCASA = 150;
	private static final int LREC = LPARTITA + LSEQUENZA + LDOMANDA +(LRISPOSTE * 4) + LESATTA + LAIUTOCASA;

	public Domande(String domanda, String a, String b, String c, String d, int corretta, String aiutocasa, int partita,
			int sequenza) {
		this.domanda = domanda;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.corretta = corretta;
		this.sequenza = sequenza;
		this.partita = partita;
		this.aiutocasa = aiutocasa;
	}

	public Domande(String x) {
		
		int off = 0;
		partita = Integer.parseInt(x.substring(off, LPARTITA).trim());
		off += LPARTITA;
		sequenza = Integer.parseInt(x.substring(off, off + LSEQUENZA).trim());
		off += LSEQUENZA;
		domanda = x.substring(off, off + LDOMANDA).trim();
		off += LDOMANDA;
		a = x.substring(off, off + LRISPOSTE).trim();
		off += LRISPOSTE;
		b = x.substring(off, off + LRISPOSTE).trim();
		off += LRISPOSTE;
		c = x.substring(off, off + LRISPOSTE).trim();
		off += LRISPOSTE;
		d = x.substring(off, off + LRISPOSTE).trim();
		off += LRISPOSTE;
		corretta = Integer.parseInt(x.substring(off, off + LESATTA).trim());
		off += LESATTA;
		aiutocasa = x.substring(off, off + LAIUTOCASA).trim();
		off += LAIUTOCASA;

	}

	public String toBuffer() {
		String p, tot = "";
		p = String.format("%-" + LPARTITA + "s", partita + "");
		tot += p;
		p = String.format("%-" + LSEQUENZA + "s", sequenza);
		tot += p;
		p = String.format("%-" + LDOMANDA + "s", domanda + "");
		tot += p;
		p = String.format("%-" + LRISPOSTE + "s", a + "");
		tot += p;
		p = String.format("%-" + LRISPOSTE + "s", b + "");
		tot += p;
		p = String.format("%-" + LRISPOSTE + "s", c + "");
		tot += p;
		p = String.format("%-" + LRISPOSTE + "s", d + "");
		tot += p;
		p = String.format("%-" + LESATTA + "s", corretta + "");
		tot += p;
		p = String.format("%-" + LAIUTOCASA + "s", aiutocasa + "");
		tot += p;
		return tot;
	}

	public int CinquantaECinquanta() {

		int x;
		do {
			x = (int) (Math.random() * 4) + 1;
		} while (x == this.getRispostaesatta());
		return x;
	}

	public String aiutoPubblico() {

		String x;
		int perc1, perc2;
		perc1 = ((int) (Math.random() * 100) + 1);
		perc2 = 100 - perc1;
		int k;
		do {
			k = (int) (Math.random() * 4) + 1;
			
		} while (k == this.getRispostaesatta());

		x = "Il pubblico ha votato: " + perc1 + ": " + this.getRisposta(k) + " " + perc2+ ": " + this.getRisposta(corretta);
		return x;

	}
	
	public String aiutoPubblico(int n1) {

		String x;
		int perc1, perc2;
		perc1 = ((int) (Math.random() * 100) + 1);
		perc2 = 100 - perc1;
		String y;
		y=this.getRisposta(n1);
		

		x = "Il pubblico ha votato: " + perc1 + ": " + y + " " + perc2+ ": " + this.getRisposta(corretta);
		return x;

	}

	public String getDomanda() {
		return domanda;
	}

	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}

	public String getRisposta(int i) {
		if (i == 1)
			return a;
		else if (i == 2)
			return b;
		else if (i == 3)
			return c;
		else
			return d;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public int getRispostaesatta() {
		return corretta;
	}

	public void setRispostaesatta(int rispostaesatta) {
		this.corretta = rispostaesatta;
	}

	public int getPartita() {
		return partita;
	}

	public void setPartita(int partita) {
		this.partita = partita;
	}

	public int getSequenza() {
		return sequenza;
	}

	public void setSequenza(int sequenza) {
		this.sequenza = sequenza;
	}

	public int getCorretta() {
		return corretta;
	}

	public void setCorretta(int corretta) {
		this.corretta = corretta;
	}

	public static int getLpartita() {
		return LPARTITA;
	}

	public static int getLsequenza() {
		return LSEQUENZA;
	}

	public static int getLesatta() {
		return LESATTA;
	}

	public static int getLrec() {
		return LREC;
	}

	public static int getLdomanda() {
		return LDOMANDA;
	}

	public static int getLrisposte() {
		return LRISPOSTE;
	}

	public String getAiutocasa() {
		return aiutocasa;
	}

	public void setAiutocasa(String aiutocasa) {
		this.aiutocasa = aiutocasa;
	}

	public static int getLaiutocasa() {
		return LAIUTOCASA;
	}

}
