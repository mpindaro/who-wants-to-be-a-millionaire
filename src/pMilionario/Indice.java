package pMilionario;

public class Indice {
	private int i=0;

	public Indice(int i) {
		super();
		this.i = i;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
	public void azzeraI(){
		i=0;
	}
	
	public void incremento(){
		i++;
	}
}
