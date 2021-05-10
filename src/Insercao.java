public class Insercao {
	public static boolean S(Populacao pop, Cromossomo[] pais, Cromossomo[] filhos, int tipo, long t, Card[] vetCard,
			Loja[] vetLoja, Analise A) {
		boolean cond = false;
		switch (tipo) {
		case 1:
			cond = piorPaiMask(pop, pais, filhos, t, vetCard, vetLoja, A);
		case 2:
			cond = piorPaiPRa(pop, pais, filhos[0], t, vetCard, vetLoja, A);
			break;
		}
		return cond;
	}

	public static boolean piorPaiMask(Populacao pop, Cromossomo[] pais, Cromossomo[] filhos, long t, Card[] vetCard,
			Loja[] vetLoja, Analise A) {
		filhos[0].avaliacao(vetCard, vetLoja);
		filhos[1].avaliacao(vetCard, vetLoja);
		Cromossomo melhorFilho;
		boolean cond = false;
		if (filhos[0].getFitness() < filhos[1].getFitness()) {
			melhorFilho = filhos[0];
		} else {
			melhorFilho = filhos[1];
		}
		Cromossomo piorPai;
		if (pais[0].getFitness() > pais[1].getFitness()) {
			piorPai = pais[0];
		} else {
			piorPai = pais[1];
		}
		if (melhorFilho.getFitness() < piorPai.getFitness()) {
			if (!detectaClone(pop, melhorFilho)) {
				melhorFilho.heranca(pais, piorPai);
				pop.setPosVetC(piorPai.getPosPop(), melhorFilho);
				// A.addInsercao();
				cond = true;
			} else {
				// A.addContClone();
			}
		}
		if (pop.getTop1().getFitness() > melhorFilho.getFitness()) {
			pop.setTop1(melhorFilho);
			t = System.currentTimeMillis() - t;
			//System.out.println("Novo Top1 em " + t / 1000 + "s \t" + melhorFilho.exibe(vetCard, vetLoja));
			// A.addContTop1(melhorFilho,t);
		}
		return cond;
	}

	public static boolean piorPaiPRa(Populacao pop, Cromossomo[] pais, Cromossomo filho, long t, Card[] vetCard,
			Loja[] vetLoja, Analise A) {
		boolean cond = false;
		filho.avaliacao(vetCard, vetLoja);
		Cromossomo piorPai;
		if (pais[0].getFitness() > pais[1].getFitness()) {
			piorPai = pais[0];
		} else {
			piorPai = pais[1];
		}
		if (filho.getFitness() < piorPai.getFitness()) {
			if (!detectaClone(pop, filho)) {
				filho.heranca(pais, piorPai);
				pop.setPosVetC(piorPai.getPosPop(), filho);
				// A.addInsercao();
				cond = true;
			} else {
				// A.addContClone();
			}
		}
		if (pop.getTop1().getFitness() > filho.getFitness()) {
			pop.setTop1(filho);
			t = System.currentTimeMillis() - t;
			System.out.println("Novo Top1 em " + t / 1000 + "s \t" + filho.exibe(vetCard, vetLoja));
			// A.addContTop1(melhorFilho,t);
		} else {
			// Util.p("\nFtop1: "+pop.getTop1().getFitness());
			// Util.p("\nFfilho: "+filho.getFitness());
		}
		return cond;
	}

	public static boolean detectaClone(Populacao pop, Cromossomo melhorFilho) {
		int i = 0;
		while (i < pop.tam()) {
			if (pop.getVetC(i).getFitness() == melhorFilho.getFitness()) {
				return true;
			}
			i++;
		}
		return false;
	}
}
