import java.util.ArrayList;

public class Principal {

	public static void main(String[] args) {
		// System.out.println(frete.toString());
		ArrayList<ArrayList<String>> vetPreco = Control.lerArquivo("ligamagicPreco2.txt");
		ArrayList<ArrayList<String>> vetQtd = Control.lerArquivo("ligamagicQtd2.txt");
		ArrayList<ArrayList<String>> vetPedido = Control.lerArquivo("ligamagicPedido1.txt");
		// System.out.println(vetPedido.toString());
		ArrayList<Frete> frete = Control.geraVetorFrete(Control.lerArquivo("ligamagicFrete.txt"));
		ArrayList<Item> pedido = Control.geraPedido(vetPedido, vetPreco, vetQtd);
		int geracoes = 0;
		int cont = 0;
		int tam = 1000;
		int falhas = 100;
		int chaceMutacao = 3;

		double tic = System.currentTimeMillis();

		Cromossomo top1 = new Cromossomo();
		top1.preencherComossomo(pedido, frete);
		top1.avaliacao(frete);
		Populacao populacao = new Populacao(pedido, frete, tam, top1);
		populacao.getPopulacao().add(top1);
		top1 = populacao.getTop1();
		while (cont <= falhas) {
			// double t1 = System.currentTimeMillis();
			populacao.selecao(tam);
			populacao.cruzamento(pedido, frete, tam);
			populacao.mutacao(frete, chaceMutacao);
			populacao.insercao(pedido, frete, tam);
			if (top1.getFitness() > populacao.getTop1().getFitness()) {
				cont = 0;
				top1 = populacao.getTop1();
			}
			cont++;
			geracoes++;
			double t2 = System.currentTimeMillis();
			System.out.println("Top1: " + populacao.getTop1().getFitness() + " Geração: " + geracoes + " Cont: " + cont
					+ " Tempo de processamento: " + ((t2 - tic) / 1000) + "s.");
		}

		double toc = System.currentTimeMillis();
		System.out.println("Top1 Global:\n" + top1.toString() + "\n");
		System.out.println("Top1 População Final:\n" + populacao.getTop1().toString() + "\n");
		System.out.println("Gerações: " + geracoes);
		System.out.println("Tamanho: " + tam);
		System.out.println("Falhas: " + falhas);
		System.out.println("Tempo de processamento: " + (toc - tic) + "s.");
		System.out.println("Top1 Global:\n" + top1.toString() + "\n");
		System.out.println("Top1 População Final:\n" + populacao.getTop1().toString() + "\n");
		System.out.println("Gerações: " + geracoes);
		System.out.println("Tamanho: " + tam);
		System.out.println("Falhas: " + falhas);
		System.out.println("Tempo de processamento: " + ((toc - tic) / 1000) + "s.");
	}
}
