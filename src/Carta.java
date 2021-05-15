import java.util.ArrayList;

public class Carta implements Cloneable {
	private int id;
	private String nome;
	private ArrayList<Float> vetPreco;
	private ArrayList<Integer> vetQtd;

	Carta(int id, String nome, ArrayList<Float> vetPreco, ArrayList<Integer> vetQtd) {
		this.id = id;
		this.nome = nome;
		this.vetPreco = vetPreco;
		this.vetQtd = vetQtd;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Float> getVetPreco() {
		return vetPreco;
	}

	public void setVetPreco(ArrayList<Float> vetPreco) {
		this.vetPreco = vetPreco;
	}

	public ArrayList<Integer> getVetQtd() {
		return vetQtd;
	}

	public void setVetQtd(ArrayList<Integer> vetQtd) {
		this.vetQtd = vetQtd;
	}

	public int getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void menos1(int i) {
		this.vetQtd.set(i, this.vetQtd.get(i) - 1);
	}

	public void mais1(int i) {
		this.vetQtd.set(i, this.vetQtd.get(i) + 1);
	}

	public float getPrecoPos(int i) {
		return this.vetPreco.get(i);
	}

	public int getQtdPos(int i) {
		return this.vetQtd.get(i);
	}

	@Override
	public Object clone() {
		try {
			return (Carta) super.clone();
		} catch (CloneNotSupportedException e) {
			return new Carta(this.getId(), this.getNome(), this.getVetPreco(), this.getVetQtd());
		}
	}

	public String toString() {
		return "Id Carta: " + this.getId() + "\nNome: " + this.getNome() + "\nVetor de Precos: \n" + this.getVetPreco()
				+ " \nVetor de Quantidade: \n" + this.getVetQtd() + "\n";
	}

}
