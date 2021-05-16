import java.util.ArrayList;
import java.util.Random;

public class Populacao {
	private ArrayList<Cromossomo> populacao = new ArrayList<>();
	private Cromossomo top1;

	Populacao(ArrayList<Item> pedido, ArrayList<Frete> frete, int tam, Cromossomo top1) {
		this.top1 = top1;
		for (int i = 0; i < tam; i++) {
			Cromossomo cromossomo = new Cromossomo();
			cromossomo.preencherComossomo(pedido, frete);
			this.populacao.add(cromossomo);
			if (cromossomo.getFitness() < this.top1.getFitness()) {
				this.top1 = cromossomo;
				//System.out.println("Novo Cromossomo:\n" + cromossomo.toString());
			}
		}
	}

	public ArrayList<Cromossomo> getPopulacao() {
		return populacao;
	}

	public void setCromossomo(ArrayList<Cromossomo> populacao) {
		this.populacao = populacao;
	}

	public Cromossomo getTop1() {
		return top1;
	}

	public void setTop1(Cromossomo top1) {
		this.top1 = top1;
	}

	public void cruzamentoMonoPonto(Cromossomo pai, Cromossomo mae, ArrayList<Item> pedido, ArrayList<Frete> frete) {
		Random aleatorio = new Random();
		int alet = (aleatorio.nextInt((pai.getCromossomo().size())));
		int ponto = pai.getCromossomo().get(alet).getCarta().getId();
		Cromossomo filho1 = new Cromossomo();
		Cromossomo filho2 = new Cromossomo();
		int i = 0;
		boolean status = true;
		for (i = 0; i < pai.getCromossomo().size(); i++) {
			if (pai.getCromossomo().get(i).getCarta().getId() == ponto && status == true) {
				status = false;
			}
			if (status) {
				filho1.getCromossomo().add(pai.getCromossomo().get(i));
				filho2.getCromossomo().add(mae.getCromossomo().get(i));
			} else {
				filho1.getCromossomo().add(mae.getCromossomo().get(i));
				filho2.getCromossomo().add(pai.getCromossomo().get(i));
			}
		}
		filho1.avaliacao(frete);
		filho2.avaliacao(frete);
		if (this.top1.getFitness() > filho1.getFitness()) {
			this.top1 = filho1;
			//System.out.println("Filho1 " + filho1.getFitness() + " é melhor que o Top1");
		}
		if (this.top1.getFitness() > filho2.getFitness()) {
			this.top1 = filho2;
			//System.out.println("Filho2 " + filho2.getFitness() + " é melhor que o Top1");
		}
		// System.out.println("Filho1:\n" + filho2.toString());
		// System.out.println("Filho2:\n" + filho2.toString());
		this.populacao.add(filho1);
		this.populacao.add(filho2);
	}

	public void cruzamento(ArrayList<Item> pedido, ArrayList<Frete> frete, int tam) {
		ArrayList<Cromossomo> copiaPopulacao = this.copiaPopulacao();
		for (int i = 0; i < tam / 2; i++) {
			this.cruzamentoMonoPonto(aleatorio(copiaPopulacao), aleatorio(copiaPopulacao), pedido, frete);

		}
	}

	public void insercao(ArrayList<Item> pedido, ArrayList<Frete> frete, int tam) {
		for (int i = 0; i < tam; i++) {
			Cromossomo cromossomo = new Cromossomo();
			cromossomo.preencherComossomo(pedido, frete);
			this.populacao.add(cromossomo);
			if (cromossomo.getFitness() < this.top1.getFitness()) {
				this.top1 = cromossomo;
				//System.out.println("Novo Cromossomo Inserção: " + cromossomo.getFitness());
			}
		}
	}

	public void mutacao(ArrayList<Frete> frete, int chaceMutacao) {
		ArrayList<Cromossomo> copiaPopulacao = this.copiaPopulacao();
		for (Cromossomo cromossomo : copiaPopulacao) {
			Random aleatorio = new Random();
			if (aleatorio.nextInt(101) < chaceMutacao) {
				Cromossomo mutante = cromossomo.copiaCromossomo();
				mutante.mutacao(frete);
				this.populacao.add(mutante);
				if (mutante.getFitness() < this.top1.getFitness()) {
					this.top1 = mutante;
					//System.out.println("Mutante: " + mutante.getFitness());
				}
			}
		}
	}

	public void selecao(int tam) {
		this.populacao.sort((d1, d2) -> d1.compareTo(d2));
		ArrayList<Cromossomo> sublista = new ArrayList<Cromossomo>(this.populacao.subList(0, tam));
		this.populacao = sublista;
	}

	public static Cromossomo aleatorio(ArrayList<Cromossomo> lista) {
		Random aleatorio = new Random();
		return lista.remove(aleatorio.nextInt(lista.size()));
	}

	public ArrayList<Cromossomo> copiaPopulacao() {
		ArrayList<Cromossomo> copiaCromossomo = new ArrayList<>();
		for (Cromossomo cromossomo : this.populacao) {
			ArrayList<Gene> cromo = new ArrayList<>();
			for (Gene gene : cromossomo.getCromossomo()) {
				ArrayList<Float> vetPrec = new ArrayList<>();
				ArrayList<Integer> vetQt = new ArrayList<>();
				for (int i = 0; i < gene.getCarta().getVetPreco().size(); i++) {
					vetPrec.add(gene.getCarta().getPrecoPos(i));
					vetQt.add(gene.getCarta().getQtdPos(i));
				}
				Carta carta = new Carta((int) gene.getCarta().getId(), (String) gene.getCarta().getNome(), vetPrec,
						vetQt);
				// Item i = new Item(carta, (int) item.getQtd());
				Gene newGene = new Gene(carta, gene.getLoja());
				cromo.add(newGene);
			}
			Cromossomo novoCromossomo = new Cromossomo(cromo, cromossomo.getFitness());
			copiaCromossomo.add(novoCromossomo);
		}
		return copiaCromossomo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((populacao == null) ? 0 : populacao.hashCode());
		result = prime * result + ((top1 == null) ? 0 : top1.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Populacao other = (Populacao) obj;
		if (populacao == null) {
			if (other.populacao != null)
				return false;
		} else if (!populacao.equals(other.populacao))
			return false;
		if (top1 == null) {
			if (other.top1 != null)
				return false;
		} else if (!top1.equals(other.top1))
			return false;
		return true;
	}

}
