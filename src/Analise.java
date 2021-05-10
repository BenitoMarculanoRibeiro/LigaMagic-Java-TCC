public class Analise {
	private int contInsercao = 0;
	private int contMutacao = 0;
	private int contTop1 = 0;
	private int contMutInsert = 0;
	private int contClone = 0;
	private long tempoTop1 = 0;
	private long tempoGeral = 0;
	Cromossomo top1;

	public int getContClone() {
		return contClone;
	}

	public void addContClone() {
		this.contClone++;
	}

	public int getContInsercao() {
		return contInsercao;
	}

	public void addInsercao() {
		this.contInsercao++;
	}

	public int getContMutacao() {
		return contMutacao;
	}

	public void addMutacao() {
		this.contMutacao++;
	}

	public int getContTop1() {
		return contTop1;
	}

	public void addContTop1(Cromossomo top1, long T0) {
		this.contTop1++;
		this.top1 = top1;
		this.tempoTop1 = (System.currentTimeMillis() - T0) / 1000;
	}

	public int getContMutInsert() {
		return contMutInsert;
	}

	public void addContMutInsert() {
		this.contMutInsert++;
	}

	public void fechaTempo(long T0) {
		this.tempoGeral = (System.currentTimeMillis() - T0) / 1000;
	}

	public void setTop1(Cromossomo top1) {
		this.top1 = top1;
	}

	public void setContInsercao(int contInsercao) {
		this.contInsercao = contInsercao;
	}

	public void setContMutacao(int contMutacao) {
		this.contMutacao = contMutacao;
	}

	public void setContTop1(int contTop1) {
		this.contTop1 = contTop1;
	}

	public void setContMutInsert(int contMutInsert) {
		this.contMutInsert = contMutInsert;
	}

	public void setContClone(int contClone) {
		this.contClone = contClone;
	}

	public void setTempoTop1(long tempoTop1) {
		this.tempoTop1 = tempoTop1;
	}

	public void setTempoGeral(long tempoGeral) {
		this.tempoGeral = tempoGeral;
	}

	public long getTempoTop1() {
		return tempoTop1;
	}

	public long getTempoGeral() {
		return tempoGeral;
	}

	public String toString() {
		String saida = "Inserções:\t" + this.contInsercao;
		saida += "\nMutações:\t" + this.contMutacao;
		saida += "\nInserções com mutação:\t" + this.contMutInsert;
		saida += "\nDetecções de Clones:\t" + this.contClone;
		// saida += "\n"+this.top1.exibe(vetCard, vetLoja);
		saida += "\nVezes que achou o Top 1:\t" + this.contTop1;
		saida += "\nTempo para achar o Top 1:\t" + this.tempoTop1;
		saida += "\nTempo que o AG rodou:\t" + this.tempoGeral;
		return saida;
	}

	public String exibeMedia(int max, Analise[] vetAnalise, int chanceMut) {
		String saida = "";
		double somatorioF = 0;
		double menor = Double.MAX_VALUE;
		for (int j = 0; j < max; j++) {
			this.setContInsercao(this.getContInsercao() + vetAnalise[j].getContInsercao());
			this.setContMutacao(this.getContMutacao() + vetAnalise[j].getContMutacao());
			this.setContTop1(this.getContTop1() + vetAnalise[j].getContTop1());
			this.setContMutInsert(this.getContMutInsert() + vetAnalise[j].getContMutInsert());
			this.setContClone(this.getContClone() + vetAnalise[j].getContClone());
			this.setTempoTop1(this.getTempoTop1() + vetAnalise[j].getTempoTop1());
			this.setTempoGeral(this.getTempoGeral() + vetAnalise[j].getTempoGeral());
			somatorioF += vetAnalise[j].top1.getFitness();
			if (vetAnalise[j].top1.getFitness() < menor) {
				menor = vetAnalise[j].top1.getFitness();
			}
			// saida += "\nFitness "+j+": "+vetAnalise[j].top1.getFitness();
		}
		saida += "\n\nChance de Mutação:\t" + chanceMut;
		saida += "\nMédia Inserções:\t" + this.getContInsercao() / (double) max;
		saida += "\nMédia Mutações:\t" + this.getContMutacao() / (double) max;
		saida += "\nMédia inserções com mutação:\t" + this.getContMutInsert() / (double) max;
		saida += "\nMédia detecções de Clones:\t" + this.getContClone() / (double) max;
		saida += "\nMédia de vezes que achou o Top 1:\t" + this.getContTop1() / (double) max;
		saida += "\nMédia do Fitness do Top 1:\t" + somatorioF / (double) max;
		saida += "\nMelhor top1 encontrado:\t" + menor;
		saida += "\nMédia tempo para achar o Top 1:\t" + this.getTempoTop1() / (double) max;
		saida += "\nMédia tempo que o AG rodou:\t" + this.getTempoGeral() / (double) max;
		return saida;
	}
}
