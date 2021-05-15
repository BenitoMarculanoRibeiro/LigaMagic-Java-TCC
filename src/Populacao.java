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
				System.out.println("Novo Cromossomo: " + cromossomo.getFitness());
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
		// Gene gene = this.cromossomo.get(aleatorio.nextInt(this.cromossomo.size()));
		System.out.println("Cromossomo: " + pai);
		int alet = aleatorio.nextInt(pai.getCromossomo().size());
		System.out.println("Tamanho Cromossomo: " + pai.getCromossomo().size());
		System.out.println("Alet: " + alet);
		System.out.println(pai.getCromossomo().get(alet));
		int ponto = pai.getCromossomo().get(alet).getCarta().getId();
		Cromossomo filho1 = new Cromossomo();
		Cromossomo filho2 = new Cromossomo();
		int i = 0;
		boolean status = true;
		System.out.println("Pasdf");
		System.out.println(pai);
		System.out.println(mae);
		for (i = 1; i < pai.getCromossomo().size(); i++) {
			if (pai.getCromossomo().get(i).getCarta().getId() == ponto && pai.getCromossomo().get(i).getCarta()
					.getId() != pai.getCromossomo().get(i - 1).getCarta().getId()) {
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
			System.out.println("Filho1 " + filho1.getFitness() + " é melhor que o Top1");
		}
		if (this.top1.getFitness() > filho2.getFitness()) {
			this.top1 = filho2;
			System.out.println("Filho1 " + filho2.getFitness() + " é melhor que o Top1");
		}
		this.populacao.add(filho1);
		this.populacao.add(filho2);
	}

	public void cruzamento(ArrayList<Item> pedido, ArrayList<Frete> frete, int tam) {
		ArrayList<Cromossomo> copiaPopulacao = (ArrayList<Cromossomo>) this.populacao.clone();
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
				System.out.println("Novo Cromossomo Inserção: " + cromossomo.getFitness());
			}
		}
	}

	public void mutacao(ArrayList<Frete> frete, int chaceMutacao) {
		for (Cromossomo cromossomo : this.populacao) {
			Random aleatorio = new Random();
			if (aleatorio.nextInt(101) < chaceMutacao) {
				Cromossomo deepCopy = (Cromossomo) cromossomo.clone();
				Cromossomo mutante = (Cromossomo) cromossomo.clone();
				mutante.mutacao(frete);
				this.populacao.add(mutante);
				if (mutante.getFitness() < this.top1.getFitness()) {
					this.top1 = mutante;
					System.out.println("Mutante: " + mutante.getFitness());
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
