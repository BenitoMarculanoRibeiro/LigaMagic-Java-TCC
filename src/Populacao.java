public class Populacao {
	private Cromossomo[] vetC;
	private Cromossomo top1;

	Populacao(int tamPop, Card[] vetCard, Loja[] vetLoja, Pedido ped, int tamPopTop, int tipoCromossomo) {
		this.vetC = new Cromossomo[tamPop];
		boolean primeiro = true; // primeiro == top1

		for (int i = 0; i < tamPop; i++) {

			if(tipoCromossomo == 1) {
				this.vetC[i] = new Cromossomo(vetCard, vetLoja, ped); // cria 1 cromossomo
				// randomico
			}else if(tipoCromossomo == 2) {
				this.vetC[i] = new Cromossomo(vetCard, vetLoja, ped,tamPopTop ); // cria 1 cromossomo top
			}else {
				this.vetC[i] = new Cromossomo(vetCard, vetLoja, ped, tamPopTop, 1); // cria 1 cromossomo top frete 
			}
			//
			this.vetC[i].setPosPop(i); // posi��o do cromossomo na popula��o
			if (primeiro) {
				this.top1 = this.vetC[i];
				primeiro = false;
			} else if (this.top1.getFitness() > this.vetC[i].getFitness()) {
				this.top1 = this.vetC[i];
			}
		}
	}

	public Cromossomo getVetC(int pos) {
		return vetC[pos];
	}

	public int tam() {
		return vetC.length;
	}

	public void setPosVetC(int pos, Cromossomo novo) {
		this.vetC[pos] = novo;
	}

	public Cromossomo getTop1() {
		return top1;
	}

	public void setTop1(Cromossomo top1) {
		this.top1 = top1;
	}

	String exibe(Card[] vetCard, Loja[] vetLoja) {
		String saida = "";
		for (int i = 0; i < vetC.length; i++) {
			saida += this.vetC[i].exibe(vetCard, vetLoja) + "\n";
		}
		// saida += "Melhor:\n"+this.top1.exibe()+"\n";
		return saida;
	}
}
