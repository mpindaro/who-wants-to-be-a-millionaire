package pMilionario;

public class Giocatore {
	String nome;
	String somma;
	String checkpoint;

	public Giocatore(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSomma() {
		return somma;
	}

	public void setSomma(String somma) {
		this.somma = somma;
	}

	public String getCheckpoint() {
		return checkpoint;
	}

	public void setCheckpoint(String checkpoint) {
		this.checkpoint = checkpoint;
	}

	public void aggiungiSoldi(int x) {
		somma += x;
	}
}
