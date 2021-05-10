
public class Print {
	private String tempo;
	private String pedido;
	private String tamanhoPopulacao;
	private String valor;

	public Print(String tempo, String pedido, String tamanhoPopulacao, String valor) {
		this.tempo = tempo;
		this.pedido = pedido;
		this.tamanhoPopulacao = tamanhoPopulacao;
		this.valor = valor;
	}

	public Print() {
		this.pedido = this.tamanhoPopulacao = this.valor = null;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getTamanhoPopulacao() {
		return tamanhoPopulacao;
	}

	public void setTamanhoPopulacao(String tamanhoPopulacao) {
		this.tamanhoPopulacao = tamanhoPopulacao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Novo Top1 em " + this.tempo + "s. Pedido numero " + this.pedido + ":Populacao " + this.tamanhoPopulacao
				+ " de tamanho:" + this.valor + "";
	}
}
