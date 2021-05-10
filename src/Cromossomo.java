import java.text.DecimalFormat;

public class Cromossomo {
	private String label;
	private int[][] matGene; // 0 = loja / 1 = card
	private float fitness = 0;
	private float valorTotalCards = 0;
	private float valorTotalFrete = 0;
	private int geracao; // pega a maior gera��o dentre os seus pais +1
	private int posPop; // posi��o deste cromossomo no vetor de cromossomo na popula��o

	Cromossomo(int NumGenes) {
		matGene = new int[2][NumGenes];
	}

	// Gera Pop randomica
	Cromossomo(Card[] vetCard, Loja[] vetLoja, Pedido ped) {
		this.matGene = new int[3][ped.getNumCard()];
		Loja[] vetTempLoja = vetLoja.clone();
		Card[] vetTempCard = new Card[ped.getVetCodigo().length];
		int cont = 0;
		for (int i = 0; i < ped.getVetCodigo().length; i++) { // numero de cards diferentes do pedido
			vetTempCard[i] = vetCard[ped.getPosVetCodigo(i)].clone();
			int j = 0;
			do {
				int pos = (int) (Math.random() * vetTempLoja.length); // pos = uma loja aleatoria
				// verifica se a quantidade de cartas � maior q zero e se o valor � maior q zero
				if (vetTempCard[i].getPosVetQtd(pos) > 0 && vetTempCard[i].getPosVetPreco(pos) > 0) {
					matGene[0][cont] = pos; // preenche a coluna 0 com as lojas
					matGene[1][cont] = ped.getPosVetCodigo(i); // preenche a coluna 1 com o cod das cartas
					vetTempCard[i].decVetQtd(pos); // diminui 1 carta do estoque das cartas
					cont++;
					j++;
				}
			} while (j < ped.getPosVetNumCop(i)); // quando tiver mais de uma copia da msm carta no pedido
		}
		avaliacao(vetCard, vetLoja);
	}

	// // gera Pop top
	Cromossomo(Card[] vetCard, Loja[] vetLoja, Pedido ped, int tamPopTop) {

		this.matGene = new int[3][ped.getNumCard()];
		Loja[] vetTempLoja = vetLoja.clone();
		Card[] vetTempCard = new Card[ped.getVetCodigo().length];
		int cont = 0, posLoja = 0;
		float[] lojasTop10 = new float[tamPopTop];
		for (int i = 0; i < ped.getVetCodigo().length; i++) {// numero de cards diferentes do pedido
			vetTempCard[i] = vetCard[ped.getPosVetCodigo(i)].clone();
			for (int j = 0; j < vetTempCard[i].getVetPreco().length - 1; j++) { // numero de lojas
				if (j < lojasTop10.length) {
					if (vetTempCard[i].getPosVetQtd(posLoja) == 0.0)
						lojasTop10[j] = 100; // revisar isso
					else
						lojasTop10[j] = vetTempCard[i].getPrecoPos(posLoja);
				} else {
					Util.insertionSort(lojasTop10);
					// if (vetTempCard[i].getPosVetQtd(posLoja) == 0.0) {

					if (lojasTop10[lojasTop10.length - 1] > vetTempCard[i].getPrecoPos(posLoja)
							&& vetTempCard[i].getPrecoPos(posLoja) != 0.0) {
						lojasTop10[lojasTop10.length - 1] = vetTempCard[i].getPrecoPos(posLoja);
					}
					// }
				}
				posLoja++;
			}
			posLoja = 0;

			for (int k = 0; k < vetTempCard[i].getVetPreco().length - 1; k++) {
				if (vetTempCard[i].getPrecoPos(k) > lojasTop10[lojasTop10.length - 1]) {
					vetTempCard[i].setVetPreco(k, 0);
					vetTempCard[i].setVetQtd(k, 0);
				}
			}
			int j = 0;
			do {
				int pos = (int) (Math.random() * vetTempLoja.length); // pos = uma loja aleatoria
				// verifica se a quantidade de cartas � maior q zero e se o valor � maior q zero
				if (vetTempCard[i].getPosVetQtd(pos) > 0 && vetTempCard[i].getPosVetPreco(pos) > 0) {
					matGene[0][cont] = pos; // preenche a coluna 0 com as lojas
					matGene[1][cont] = ped.getPosVetCodigo(i); // preenche a coluna 1 com o cod das cartas
					vetTempCard[i].decVetQtd(pos); // diminui 1 carta do estoque das cartas
					// vetTemporarioCartas = vetTempCard;
					cont++;
					j++;
				}
			} while (j < ped.getPosVetNumCop(i)); // quando tiver mais de uma copia da msm carta no pedido
		}
		avaliacao(vetCard, vetLoja);
	}

	// gera Pop top frete
	Cromossomo(Card[] vetCard, Loja[] vetLoja, Pedido ped, int tamPopTop, int tipoCriacao) {

		this.matGene = new int[3][ped.getNumCard()];
		Loja[] vetTempLoja = vetLoja.clone();
		Card[] vetTempCard = new Card[ped.getVetCodigo().length];
		int cont = 0, posLoja = 0;
		float[] lojasTop10 = new float[tamPopTop];
		for (int i = 0; i < ped.getVetCodigo().length; i++) {// numero de cards diferentes do pedido
			vetTempCard[i] = vetCard[ped.getPosVetCodigo(i)].clone();
			for (int j = 0; j < vetTempCard[i].getVetPreco().length - 1; j++) { // numero de lojas
				if (j < lojasTop10.length) {
					if (vetTempCard[i].getPosVetQtd(posLoja) == 0.0)
						lojasTop10[j] = 100; // revisar isso
					else
						lojasTop10[j] = vetTempLoja[posLoja].getFrete();
				} else {
					Util.insertionSort(lojasTop10);
					if (lojasTop10[lojasTop10.length - 1] > vetTempLoja[posLoja].getFrete()
							&& vetTempLoja[posLoja].getFrete() != 0.0) {
						lojasTop10[lojasTop10.length - 1] = vetTempLoja[posLoja].getFrete();
					}
				}
				posLoja++;
			}
			posLoja = 0;

			for (int k = 0; k < vetTempCard[i].getVetPreco().length - 1; k++) {
				if (vetTempLoja[k].getFrete() > lojasTop10[lojasTop10.length - 1]) {
					vetTempCard[i].setVetQtd(k, 0);
				}
			}
			int j = 0;
			do {
				int pos = (int) (Math.random() * vetTempLoja.length);// pos = uma loja aleatoria
				// verifica se a quantidade de cartas � maior q zero e se o valor � maior q zero
				if (vetTempCard[i].getPosVetQtd(pos) > 0 && vetTempCard[i].getPosVetPreco(pos) > 0) {
					matGene[0][cont] = pos; // preenche a coluna 0 com as lojas
					matGene[1][cont] = ped.getPosVetCodigo(i); // preenche a coluna 1 com o cod das cartas
					vetTempCard[i].decVetQtd(pos); // diminui 1 carta do estoque das cartas
					cont++;
					j++;
				}
			} while (j < ped.getPosVetNumCop(i)); // quando tiver mais de uma copia da msm carta no pedido
		}

		avaliacao(vetCard, vetLoja);
	}

	void avaliacao(Card[] vetCard, Loja[] vetLoja) { // o fitness soma o valor das cartas e dos fretes, quanto menor o
														// valor melhor
		this.fitness = 0;
		this.valorTotalCards = 0;
		this.valorTotalFrete = 0;
		int[] vetCond = new int[matGene[0].length];
		int loja;
		int card;
		for (int i = 0; i < this.matGene[0].length; i++) {
			loja = matGene[0][i];
			card = matGene[1][i];
			for (int j = 0; j < this.matGene[0].length; j++) {
				if (matGene[0][i] == matGene[0][j]) {
					if (vetCond[i] == 0) {
						this.fitness += vetLoja[loja].getFrete();
						this.valorTotalFrete += vetLoja[loja].getFrete();
					}
					vetCond[i] = 1;
					vetCond[j] = 1;
				}
			}
			if (vetCond[i] == 0) {
				this.fitness += vetLoja[loja].getFrete();
				this.valorTotalFrete += vetLoja[loja].getFrete();
			}
			this.fitness += vetCard[card].getPosVetPreco(loja);
			this.valorTotalCards += vetCard[card].getPosVetPreco(loja);
		}
	}

	void heranca(Cromossomo[] pais, Cromossomo piorPai) {
		if (pais[0].getGeracao() > pais[1].getGeracao()) {
			this.geracao = pais[0].getGeracao() + 1;
		} else {
			this.geracao = pais[1].getGeracao() + 1;
		}
		this.posPop = piorPai.getPosPop();
		this.label = piorPai.getLabel();
	}

	public String exibe(Card[] vetCard, Loja[] vetLoja) {
		DecimalFormat df = new DecimalFormat("0.00");
		String fitness = df.format(this.fitness);
		String saida = "G" + this.geracao + "\tFitness: " + fitness + "\tLojas: ";
		for (int i = 0; i < matGene[0].length; i++) {
			if (matGene[0][i] < 10) {
				saida += "|0" + matGene[0][i];
			} else {
				saida += "|" + matGene[0][i];
			}
		}
		saida += "|";
		return saida;
	}

	public String exibe2(Card[] vetCard, Loja[] vetLoja) {
		String saida = "\nLojas:\t";

		DecimalFormat df1 = new DecimalFormat("000000");
		for (int i = 0; i < matGene[0].length; i++) {
			saida += "|" + df1.format(matGene[0][i]);
		}
		saida += "|\nCards:\t";
		for (int i = 0; i < matGene[0].length; i++) {
			saida += "|" + df1.format(matGene[1][i]);
		}
		saida += "|\nPreco:\t";
		DecimalFormat df2 = new DecimalFormat("000.00");
		for (int i = 0; i < matGene[0].length; i++) {
			saida += "|" + df2.format(vetCard[matGene[1][i]].getPosVetPreco(matGene[0][i]));
		}
		DecimalFormat df3 = new DecimalFormat(".00");
		saida += "|\nN�mero total de cards: " + matGene[0].length;
		saida += "\nValor total em cards:  R$" + df3.format(this.valorTotalCards);
		saida += "\nValor total do frete:  R$" + df3.format(this.valorTotalFrete);
		saida += "\nValor total da compra: R$" + df3.format(this.fitness);
		return saida;
	}

	public String exibeE(Card[] vetCard, Loja[] vetLoja) {
		String saida = "\nLojas:\t";
		DecimalFormat df1 = new DecimalFormat("000000");
		for (int i = 0; i < matGene[0].length; i++) {
			saida += "\t" + df1.format(matGene[0][i]);
		}
		saida += "\nCards:\t";
		for (int i = 0; i < matGene[0].length; i++) {
			saida += "\t" + df1.format(matGene[1][i]);
		}
		saida += "\nPreco:\t";
		DecimalFormat df2 = new DecimalFormat("000.00");
		for (int i = 0; i < matGene[0].length; i++) {
			saida += "\t" + df2.format(vetCard[matGene[1][i]].getPosVetPreco(matGene[0][i]));
		}
		DecimalFormat df3 = new DecimalFormat(".00");
		saida += "|\nN�mero total de cards: " + matGene[0].length;
		saida += "\nValor total em cards:  R$" + df3.format(this.valorTotalCards);
		saida += "\nValor total do frete:  R$" + df3.format(this.valorTotalFrete);
		saida += "\nValor total da compra: R$" + df3.format(this.fitness);
		return saida;
	}

	public String exibe3() {
		String saida = "";
		DecimalFormat df = new DecimalFormat("0.00");
		// for (int i = 0; i < matGene[0].length; i++) {
		// saida += "Loja: " + vetLoja[matGene[0][i]].getNome();
		// saida += "\nCard: " + vetCard[matGene[1][i]].getNome();
		// saida += "\nPre�o: R$" +
		// df.format(vetCard[matGene[1][i]].getPosVetPreco(matGene[0][i]));
		// saida += "\nFrete: R$" + df.format(vetLoja[matGene[0][i]].getFrete());
		// saida += "\n---------------------------------------\n";
		// qtdLoja[matGene[0][i]]++;
		// }
		// saida += "\nQTD\t| LOJA\t\t| CARDS\t| FRETE";
		// saida += "\n-------------------------------------------------";
		// for (int i = 0; i < vetLoja.length; i++) {
		// if (qtdLoja[i] > 0) {
		// float valorCard = 0;
		// for (int j = 0; j < matGene[0].length; j++) {
		// if (matGene[0][j] == i) {
		// valorCard += vetCard[matGene[1][j]].getPosVetPreco(matGene[0][j]);
		// }
		// }
		// saida += "\n" + qtdLoja[i] + "\t| " + vetLoja[i].getNome() + "\t| R$" +
		// df.format(valorCard)
		// + "\t| R$" + df.format(vetLoja[i].getFrete());
		// }
		// }
		// saida += "\n\nN�mero total de cards: " + matGene[0].length;
		// saida += "\nValor total em cards: R$" + df.format(this.valorTotalCards);
		// saida += "\nValor total do frete: R$" + df.format(this.valorTotalFrete);
		saida += "Valor total da compra: R$" + df.format(this.fitness);
		return saida;
	}

	public void setFitness(float fitness) {
		this.fitness = fitness;
	}

	public float getFitness() {
		return fitness;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getGeracao() {
		return geracao;
	}

	public void setGeracao(int geracao) {
		this.geracao = geracao;
	}

	public int getPosPop() {
		return posPop;
	}

	public void setPosPop(int posVet) {
		this.posPop = posVet;
	}

	public int getLoja(int pos) {
		return this.matGene[0][pos];
	}

	public int getCard(int pos) {
		return this.matGene[1][pos];
	}

	public void setLoja(int loja, int pos) {
		this.matGene[0][pos] = loja;
	}

	public void copiaGene(int loja, int card, int pos) {
		this.matGene[0][pos] = loja;
		this.matGene[1][pos] = card;
	}

	public int getNumGenes() {
		return this.matGene[0].length;
	}

}
