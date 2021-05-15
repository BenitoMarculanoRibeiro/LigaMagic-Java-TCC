import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		// System.out.println(frete.toString());
		ArrayList<ArrayList<String>> vetPreco = Control.lerArquivo("ligamagicPreco2.txt");
		ArrayList<ArrayList<String>> vetQtd = Control.lerArquivo("ligamagicQtd2.txt");
		ArrayList<ArrayList<String>> vetPedido = Control.lerArquivo("ligamagicPedido1.txt");
		// System.out.println(vetPedido.toString());
		ArrayList<Frete> frete = Control.geraVetorFrete(Control.lerArquivo("ligamagicFrete.txt"));
		ArrayList<Item> pedido = Control.geraPedido(vetPedido, vetPreco, vetQtd);
		ArrayList<Item> copiaPedido = new ArrayList<>();
		for (Item item : pedido) {
			ArrayList<Float> vetPrec = new ArrayList<>();
			ArrayList<Integer> vetQt = new ArrayList<>();
			for (int i = 0; i < item.getCarta().getVetPreco().size(); i++) {
				vetPrec.add(item.getCarta().getPrecoPos(i));
				vetQt.add(item.getCarta().getQtdPos(i));
			}
			Carta carta = new Carta((int) item.getCarta().getId(), (String) item.getCarta().getNome(), vetPrec, vetQt);
			Item i = new Item(carta, (int) item.getQtd());
			copiaPedido.add(i);
		}
		System.out.println(copiaPedido.get(1).getCarta().getQtdPos(0));
		System.out.println(pedido.get(1).getCarta().getQtdPos(0));
		copiaPedido.get(1).getCarta().mais1(0);
		System.out.println(copiaPedido.get(1).getCarta().getQtdPos(0));
		System.out.println(pedido.get(1).getCarta().getQtdPos(0));
		/*
		 * System.out.println("Copia");
		 * System.out.println(copiaPedido.get(0).getCarta().getVetQtd());
		 * System.out.println("Copia");
		 * System.out.println(copiaPedido2.get(0).getCarta().getVetQtd());
		 * System.out.println("Original");
		 * System.out.println(pedido.get(0).getCarta().getVetQtd());
		 * 
		 * //copiaPedido.get(0).getCarta().menos1(0); System.out.println("Alteração");
		 * System.out.println("Copia");
		 * System.out.println(copiaPedido.get(0).getCarta().getVetQtd());
		 * System.out.println("Original");
		 * System.out.println(pedido.get(0).getCarta().getVetQtd());
		 */
	}
}
