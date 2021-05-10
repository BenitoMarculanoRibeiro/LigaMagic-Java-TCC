public class Selecao {
	public static Cromossomo[] S(Populacao pop, int tipo, int numPart) {
		Cromossomo pais[] = new Cromossomo[2];
		switch (tipo) {
		case 1:
			pais[0] = torneio(pop, numPart);
			do {
				pais[1] = torneio(pop, numPart);
			} while (pais[0].getFitness() == pais[1].getFitness()); // garante que os cromossomos sejam diferentes
			break;
		}
		return pais;
	}

	public static Cromossomo torneio(Populacao pop, int numPart) {
		int pos = (int) (Math.random() * pop.tam());
		Cromossomo vencedor = pop.getVetC(pos); // pega um cromossomo em uma posicao aleatoria
		for (int i = 0; i < numPart - 1; i++) {
			pos = (int) (Math.random() * pop.tam());
			if (vencedor.getFitness() > pop.getVetC(pos).getFitness()) {
				vencedor = pop.getVetC(pos);
			}
		}
		return vencedor;
	}

}
