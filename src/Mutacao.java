import java.util.ArrayList;

public class Mutacao {
	public static boolean S(Cromossomo[] filhos, int porcentGene, int chanceMut, int variacao, int tipo, Analise A,
			Card[] vetCard, Loja[] vetLoja) {
		boolean cond = false;
		switch (tipo) {
		case 1:
			// cond = mutClassica(filhos, porcentGene, chanceMut, variacao, A, vetCard);
			break;
		case 2:
			cond = mutPorLoja(filhos, chanceMut, vetCard, vetLoja);
			break;
		}
		return cond;
	}

	public static boolean mutPorLoja(Cromossomo[] filhos, int chanceMut, Card[] cartas, Loja[] vetLoja) {
		if (chanceMut > (int) (Math.random() * 100)) {
			for (int i = 0; i < filhos.length; i++) {
				filhos[i] = mutCromossomo(filhos[i], cartas, vetLoja);
			}
			return true;
		}
		return false;
	}

	public static Cromossomo mutCromossomo(Cromossomo filho, Card[] cartas, Loja[] vetLoja) {
		int gene = (int) (Math.random() * (filho.getNumGenes()));
		int loja = filho.getLoja(gene);
		for (int i = 0; i < filho.getNumGenes(); i++) {
			if (loja == filho.getLoja(i)) {
				int novoGene = 0;
				novoGene = validaGene(cartas, i, filho, 85);
				if (novoGene != -1)
					filho.setLoja(novoGene, i);
				else
					filho.setLoja(getNovaLojaSemRepeticao(cartas, filho.getCard(i), loja), i);
			}
			filho.avaliacao(cartas, vetLoja);
		}
		return filho;
	}

	public static int getNovaLojaSemRepeticao(Card[] cartas, int carta, int loja) {
		int cont = 0;
		for (int i = 5; i < cartas[carta].getVetPreco().length; i++) {
			if ((cartas[carta].getVetQtd()[i] > 0 && i != loja)) {
				cont++;
			}
		}
		int[] aux = new int[cont];
		for (int i = 0; i < cont; i++) {
			if (cartas[carta].getVetQtd()[i] > 0 && i != loja) {
				aux[i] += i;
			}
		}
		int lojaReturn = (int) (Math.random() * cont);
		if (cont > 0) {
			return aux[lojaReturn];
		} else
			return loja;
	}

	// public static boolean mutClassica(Cromossomo[] filhos, int porcentGene, int
	// chanceMut, int variacao, Analise A,
	// Card[] vetCard) {
	// if (chanceMut > (int) (Math.random() * 100)) {
	// int numGenes = (filhos[0].getNumGenes() * porcentGene) / 100;
	// if (numGenes == 0) {
	// numGenes = 1;
	// }
	// int[] vetPos = new int[filhos[0].getNumGenes()];
	// for (int i = 0; i < vetPos.length; i++) {
	// vetPos[i] = i;
	// }
	// int aux, p1, p2;
	// for (int i = 0; i < vetPos.length; i++) {
	// p1 = (int) (Math.random() * vetPos.length);
	// p2 = (int) (Math.random() * vetPos.length);
	// aux = vetPos[p1];
	// vetPos[p1] = vetPos[p2];
	//
	// vetPos[p2] = aux;
	// }
	// int novoGene = 0;
	// for (int i = 0; i < numGenes; i++) {
	// novoGene = validaGene(vetCard, vetPos, i, filhos[0], variacao);
	// if (novoGene != -1) {
	// filhos[0].setLoja(novoGene, vetPos[i]);
	// }
	// novoGene = validaGene(vetCard, vetPos, i, filhos[1], variacao);
	// if (novoGene != -1) {
	// filhos[1].setLoja(novoGene, vetPos[i]);
	// }
	// }
	// return true;
	// }
	// return false;
	// }

	private static int validaGene(Card[] vetCard, int posVet, Cromossomo filho, int variacao) {

		int aux = 0, novoGene = 0, count = 0, qtdOrg = 0;

		ArrayList<Integer> lojas = new ArrayList<Integer>();

		for (int i = 0; i < variacao; i++) {
			lojas.add(i);
		}

		do {

			if (lojas.get(0) != null) {

				// TODO temporario, revisar
				do {
					aux = (int) (Math.random() * variacao);
				} while (aux > lojas.size() - 1);
				novoGene = lojas.get(aux);
				lojas.remove(aux);

			} else {

				return -1;
			}

			qtdOrg = vetCard[filho.getCard(posVet)].getVetQtd()[novoGene]; // vetCard na posicao onde a
																			// carta
																			// sera mutada, desta posicao
																			// pego a
																			// quantidade da nova loja

			count = 0;
			if (qtdOrg > 0) {// so verifica se existe aquela carta no cromossomo se ela existir no estoque
								// original
				for (int j = 0; j < filho.getNumGenes(); j++) {
					if (filho.getCard(j) == filho.getCard(posVet))// verifica se a carta da loja
																	// q esta
																	// sendo mutada esta presente no
																	// cromossomo
						count++;
				}
			}

		} while (qtdOrg == 0 || count > qtdOrg); // executa enquanto a quantidade original for igual a zero e existirem
													// mais cartas no cromossomo do q no estoque original(casos ruins)

		return novoGene;
	}
}
