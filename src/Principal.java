import java.util.ArrayList;

public class Principal {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ArrayList<Print> list = new ArrayList<>();
		int contador = 0;
		Print p = new Print();
		int[] x = { 1000, 4000, 8000, 14000 };
		for (int i = 1; i <= 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int ab = 0; ab < 10; ab++) {
					contador++;
					final String path = "armazenar.txt";
					final String urlPreco = "ligamagicPreco2.txt";
					final String urlQtd = "ligamagicQtd2.txt";
					final String urlPedido = "ligamagicPedido" + i + ".txt";
					final String urlLojas = "ligamagicFrete.txt";
					final int tipoCruzamento = 1;
					final int tipoInsercao = 2;
					final int tamPop = x[j];
					final int tamPopTop = 10;
					final int ciclos = 1000000;
					final int tipoCromossomo = 1;// 1 = randomico, 2 = menor pre�o carta, 3 = menor frete
					Card[] vetCard = ControlArq.carregaCards(urlQtd, urlPreco);
					Loja[] vetLoja = ControlArq.carregaLojas(urlLojas);
					Pedido ped = ControlArq.carregaPedido(urlPedido);
					Populacao pop = new Populacao(tamPop, vetCard, vetLoja, ped, tamPopTop, tipoCromossomo);
					int cont = 0;
					Cromossomo[] pais, filhos;
					boolean condMut, condInsert;
					long t = System.currentTimeMillis();
					do {
						pais = Selecao.S(pop, 1, 2);
						filhos = Cruzamento.S(pais, vetCard, vetLoja, tipoCruzamento);
						condMut = Mutacao.S(filhos, 1, 1, 85, 2, null, vetCard, vetLoja);
						condInsert = Insercao.S(pop, pais, filhos, tipoInsercao, t, vetCard, vetLoja, null);
						cont++;
					} while (cont < ciclos);
					Util.p(pop.exibe(vetCard, vetLoja));
					Util.p("top1: " + pop.getTop1().exibe3());

					// Extra
					p = new Print("" + t, "" + i, "" + tamPop, pop.getTop1().exibe3());
					list.add(p);
					ControlArq.escrever(path, p.toString());
					System.out.println(contador);
				}
			}
		}

		System.out.println("\n\n" + contador + "\n\n" + list.size() + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

		System.out.println(list.toString());

	}
}
