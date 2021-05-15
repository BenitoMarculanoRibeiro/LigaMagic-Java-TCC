import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Iterator;

public class Cromossomo {
	private ArrayList<Gene> cromossomo; // 0 = loja / 1 = card
	private double fitness;

	public Cromossomo() {
		this.cromossomo = new ArrayList<>();
		this.fitness = 0;
	}

	public Cromossomo(ArrayList<Gene> cromossomo, double fitness) {
		this.cromossomo = cromossomo;
		this.fitness = fitness;
	}

	public ArrayList<Gene> getCromossomo() {
		return cromossomo;
	}

	public void setCromossomo(ArrayList<Gene> cromossomo) {
		this.cromossomo = cromossomo;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public void preencherComossomo(ArrayList<Item> pedido, ArrayList<Frete> frete) {
		ArrayList<Item> copiaPedido = new ArrayList<>();
		for (Item item : pedido) {
			copiaPedido.add((Item)item.clone());
		}
		System.out.println(pedido.get(0));
		System.out.println("\n\n\n\n");
		System.out.println(copiaPedido.get(0));
		System.out.println("\n\nNovo\n\n");
		copiaPedido.get(0).getCarta().menos1(0);
		System.out.println(pedido.get(0));
		System.out.println("\n\n\n\n");
		System.out.println(copiaPedido.get(0));
		for (Item item : copiaPedido) {
			ArrayList<Integer> aux = sample(range(0, item.getCarta().getVetQtd().size()),
					item.getCarta().getVetQtd().size());
			// System.out.println(aux);
			for (int j = 0; j < item.getQtd(); j++) {
				for (int i : aux) {
					try {
						if (item.getCarta().getQtdPos(i) > 0) {
							Gene gene = new Gene(item.getCarta(), i);
							item.getCarta().menos1(i);
							this.cromossomo.add(gene);
							break;
						}
					} catch (Exception e) {
						System.out.println("Erro preencherCromossomo!");
					}
					if (i == aux.get(aux.size() - 1)) {
						System.out.println("Acabou a carta: " + item.getCarta().getNome() + ". Pedido Invalido.");
						break;
					}

				}
			}
		}
		this.avaliacao(frete);

	}

	public void avaliacao(ArrayList<Frete> frete) {
		double fitness = 0;
		ArrayList<Integer> lojas = new ArrayList<Integer>();
		for (Gene gen : this.cromossomo) {
			fitness += gen.getCarta().getPrecoPos(gen.getLoja());
			if (lojas.contains(gen.getLoja())) {
				fitness += frete.get(gen.getLoja()).getFrete();
				lojas.add(gen.getLoja());
			}
		}
		this.fitness = Math.round(fitness * 100.0) / 100.0;
	}

	public void mutacao(ArrayList<Frete> frete) {
		if (this.cromossomo.size() > 0) {
			Random aleatorio = new Random();
			Gene gene = this.cromossomo.get(aleatorio.nextInt(this.cromossomo.size()));
			gene.getCarta().mais1(gene.getLoja());
			int posLoja = aleatorio.nextInt(gene.getCarta().getVetQtd().size() - 1);
			int loja = gene.getCarta().getQtdPos(posLoja);
			if (loja > 0) {
				gene.setLoja(posLoja);
				gene.getCarta().menos1(posLoja);
				this.avaliacao(frete);
			}
		}
	}

	private int[] range(int de, int ate) {
		int[] numeros = new int[ate - de + 1];
		for (int i = 0, numero = de; numero <= ate; i++, numero++) {
			numeros[i] = numero;
		}
		return numeros;
	}

	private ArrayList<Integer> sample(int[] numeros, int quantidade) {
		ArrayList<Integer> array = new ArrayList<>();
		Random random = new Random();
		List<Integer> lista = Arrays.stream(numeros).boxed().collect(Collectors.toList());
		for (int i = 0; i < quantidade; i++) {
			array.add(lista.remove(random.nextInt(lista.size())));
		}
		return array;
	}

	@Override
	public String toString() {
		String texto = "";
		for (Gene gene : cromossomo) {
			texto += gene.toString();
		}
		texto += "Fitness: " + this.fitness + "\n";
		return texto;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		double fitness = this.fitness;
		ArrayList<Gene> cromossomo = new ArrayList<>();
		for (int i = 0; i < this.cromossomo.size(); i++) {
			cromossomo.add((Gene) this.cromossomo.get(i).clone());
		}
		return new Cromossomo(cromossomo, fitness);
	}

	public int compareTo(Object o) {
		Cromossomo cromossomo = (Cromossomo) o;
		if (this.fitness < cromossomo.fitness) {
			return -1;
		}
		if (this.fitness > cromossomo.fitness) {
			return 1;
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cromossomo == null) ? 0 : cromossomo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(fitness);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Cromossomo other = (Cromossomo) obj;
		if (cromossomo == null) {
			if (other.cromossomo != null)
				return false;
		} else if (!cromossomo.equals(other.cromossomo))
			return false;
		if (Double.doubleToLongBits(fitness) != Double.doubleToLongBits(other.fitness))
			return false;
		return true;
	}
}
