public class Pedido {
	private int[] vetCodigo; // vetor de codigo dos cards
	private int[] vetNumCop; // vetor de quandidade de cartas a serem compradas
	private int NumCard = 0;

	Pedido(int[] vetCodigo, int[] vetNumCop) {
		this.vetCodigo = vetCodigo;
		this.vetNumCop = vetNumCop;
		for (int i = 0; i < this.vetNumCop.length; i++) {
			NumCard += vetNumCop[i];
		}
	}

	public void setVetCodigo(int[] vetCodigo) {
		this.vetCodigo = vetCodigo;
	}

	public void setVetNumCop(int[] vetNumCop) {
		this.vetNumCop = vetNumCop;
	}

	public void setNumCard(int numCard) {
		NumCard = numCard;
	}

	public int[] getVetCodigo() {
		return vetCodigo;
	}

	public int[] getVetNumCop() {
		return vetNumCop;
	}

	public int getPosVetCodigo(int i) {
		return vetCodigo[i];
	}

	public int getPosVetNumCop(int i) {
		return vetNumCop[i];
	}

	public int getNumCard() {
		return NumCard;
	}
}
