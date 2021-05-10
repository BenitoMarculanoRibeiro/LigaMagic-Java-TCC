public class Cruzamento {
	public static Cromossomo[] S(Cromossomo[] pais, Card[] vetCard, Loja[] vetLoja, int tipo) {
		Cromossomo[] filhos = new Cromossomo[2];
		switch (tipo) {
		case 1:
			filhos = mascara(pais);
			break;
		case 2:
			filhos[0] = PRAleatorio(pais, vetCard, vetLoja);
			filhos[1] = null;
			break;
		}
		return filhos;
	}

	public static Cromossomo PRAleatorio(Cromossomo[] pais, Card[] vetCard, Loja[] vetLoja) {
		Cromossomo guia = new Cromossomo(pais[0].getNumGenes());
		Cromossomo filho = new Cromossomo(pais[0].getNumGenes());
		int[] aux = new int[pais[0].getNumGenes()];
		for (int i = 0; i < pais[0].getNumGenes(); i++) {
			guia.copiaGene(pais[0].getLoja(i), pais[0].getCard(i), i);
			aux[i] = i;
		}
		guia.avaliacao(vetCard, vetLoja);
		float fitMelhor = Float.MAX_VALUE;
		int cont = guia.getNumGenes();
		for (int i = 0; i < guia.getNumGenes() - 1; i++) {
			int rand = (int) (Math.random() * cont);
			int pos = aux[rand];
			guia.copiaGene(pais[1].getLoja(pos), pais[1].getCard(pos), pos);
			guia.avaliacao(vetCard, vetLoja);
			if (fitMelhor > guia.getFitness()) {
				fitMelhor = guia.getFitness();
				for (int j = 0; j < guia.getNumGenes(); j++) {
					filho.copiaGene(guia.getLoja(j), guia.getCard(j), j);
				}
			}
			aux[rand] = aux[cont - 1];
			cont--;
		}
		filho.avaliacao(vetCard, vetLoja);
		return filho;
	}

	public static Cromossomo[] mascara(Cromossomo[] pais) {
		Cromossomo[] filhos = new Cromossomo[2];
		filhos[0] = new Cromossomo(pais[0].getNumGenes());
		filhos[1] = new Cromossomo(pais[0].getNumGenes());
		int mask;
		for (int i = 0; i < pais[0].getNumGenes(); i++) {
			mask = (int) (Math.random() * 2);
			if (mask == 0) {
				filhos[0].copiaGene(pais[0].getLoja(i), pais[0].getCard(i), i);
				filhos[1].copiaGene(pais[1].getLoja(i), pais[1].getCard(i), i);
			} else {
				filhos[0].copiaGene(pais[1].getLoja(i), pais[1].getCard(i), i);
				filhos[1].copiaGene(pais[0].getLoja(i), pais[0].getCard(i), i);
			}
		}
		return filhos;
	}
}
