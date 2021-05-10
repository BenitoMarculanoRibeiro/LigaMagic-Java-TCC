public class Card {
	private String nome;
	private float[] vetPreco;
	private int[] vetQtd;

	Card(String nome, float[] vetPreco, int[] vetQtd) {
		this.nome = nome;
		this.vetPreco = vetPreco;
		this.vetQtd = vetQtd;
	}

	public Card clone() {
		String nome = this.nome;
		float[] vetPreco = new float[this.vetPreco.length];
		int[] vetQtd = new int[this.vetQtd.length];
		for (int i = 0; i < this.vetPreco.length; i++) {
			vetPreco[i] = this.vetPreco[i];
			vetQtd[i] = this.vetQtd[i];
		}
		return new Card(nome, vetPreco, vetQtd);
	}

	public String toString() {
		String texto = this.nome + ":\n";
		for (int i = 0; i < this.vetPreco.length; i++) {
			texto += "|" + vetPreco[i];
		}
		texto += "|\n";
		for (int i = 0; i < this.vetQtd.length; i++) {
			texto += "|" + vetQtd[i];
		}
		texto += "|";
		return texto;
	}

	public String getNome() {
		return nome;
	}

	public float[] getVetPreco() {
		return vetPreco;
	}

	public float getPrecoPos(int pos) {
		return vetPreco[pos];
	}

	public int[] getVetQtd() {
		return vetQtd;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setVetPreco(float[] vetPreco) {
		this.vetPreco = vetPreco;
	}

	public void setVetPreco(int pos, int valor) {
		this.vetPreco[pos] = valor;
	}

	public void setVetQtd(int pos, int valor) {
		this.vetQtd[pos] = valor;
	}

	public void decVetQtd(int i) {
		vetQtd[i]--;
	}

	public float getPosVetPreco(int i) {
		return vetPreco[i];
	}

	public int getPosVetQtd(int i) {
		return vetQtd[i];
	}
}
