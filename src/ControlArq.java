import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlArq {
	static public Loja[] carregaLojas(String urlLojas) {
		FileReader arq;
		BufferedReader leitor;
		Loja[] vetLoja = null;

		try {
			arq = new FileReader(urlLojas);
			leitor = new BufferedReader(arq);
			String linha1 = leitor.readLine();
			String linha2 = leitor.readLine();
			String camposL1[] = linha1.split("	");
			String camposL2[] = linha2.split("	");
			vetLoja = new Loja[camposL1.length - 1];
			for (int i = 0; i < vetLoja.length; i++) {
				String nome = camposL1[i + 1];
				float frete = Float.parseFloat(camposL2[i + 1]);
				vetLoja[i] = new Loja(nome, frete);
			}
			leitor.close();
			arq.close();
			//System.out.println("vetor Carregado com sucesso!");
		} catch (IOException e) {
			System.out.println("Erro na abertura do arquivo!");
		}
		return vetLoja;
	}

	static public Pedido carregaPedido(String urlPedido) {
		FileReader arq;
		BufferedReader leitor;
		Pedido ped = null;

		try {
			File arquivoLeitura = new File(urlPedido);
			LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivoLeitura));
			linhaLeitura.skip(arquivoLeitura.length());
			int max = linhaLeitura.getLineNumber() + 1;
			int[] vetCodigo = new int[max];
			int[] vetNumCop = new int[max];

			arq = new FileReader(urlPedido);
			leitor = new BufferedReader(arq);
			for (int i = 0; i < max; i++) {
				String linha = leitor.readLine();
				String campos[] = linha.split("	");
				vetCodigo[i] = Integer.parseInt(campos[0]);
				vetNumCop[i] = Integer.parseInt(campos[1]);
			}
			linhaLeitura.close();
			leitor.close();
			arq.close();
			ped = new Pedido(vetCodigo, vetNumCop);
			//System.out.println("vetor Carregado com sucesso!");
		} catch (IOException e) {
			System.out.println("Erro na abertura do arquivo!");
		}
		return ped;
	}

	static public Card[] carregaCards(String urLQtd, String urLPreco) {
		String linhaPreco, linhaQtd;
		FileReader arqPreco, arqQtd;
		BufferedReader leitorPreco, leitorQtd;
		Card[] vetCard = null;
		float[] vetPreco = null;
		int[] vetQtd = null;

		try {
			File arquivoLeitura = new File(urLPreco);
			LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivoLeitura));
			linhaLeitura.skip(arquivoLeitura.length());
			int maxLin = linhaLeitura.getLineNumber();

			arqPreco = new FileReader(urLPreco);
			leitorPreco = new BufferedReader(arqPreco);
			linhaPreco = leitorPreco.readLine();

			arqQtd = new FileReader(urLQtd);
			leitorQtd = new BufferedReader(arqQtd);
			linhaQtd = leitorQtd.readLine();

			vetCard = new Card[maxLin];
			for (int i = 0; i < maxLin; i++) {
				linhaPreco = leitorPreco.readLine();
				linhaQtd = leitorQtd.readLine();
				String camposPreco[] = linhaPreco.split("	");
				String camposQtd[] = linhaQtd.split("	");
				int maxCol = camposPreco.length;
				vetPreco = new float[maxCol];
				vetQtd = new int[maxCol];
				String nome = camposPreco[0];
				for (int j = 1; j < maxCol; j++) {
					vetPreco[j] = Float.parseFloat(camposPreco[j]);
					vetQtd[j] = Integer.parseInt(camposQtd[j]);
				}
				vetCard[i] = new Card(nome, vetPreco, vetQtd);
			}
			leitorQtd.close();
			arqQtd.close();
			leitorPreco.close();
			arqPreco.close();
			linhaLeitura.close();
			//System.out.println("vetor Carregado com sucesso!");
		} catch (IOException e) {
			System.out.println("Erro na abertura do arquivo!");
		}
		return vetCard;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	static public Card[] carregaCardsQuantidade(String urLQtd) {
		String linhaQtd;
		FileReader arqQtd;
		BufferedReader leitorQtd;
		Card[] vetCard = null;
		Map<String, Integer> qtd = new HashMap<>();
		List<Map> c = new ArrayList<>();
		Map<String, List> cart = new HashMap<>();
		List<Map> vetQtd = new ArrayList<>();

		try {
			File arquivoLeitura = new File(urLQtd);
			LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivoLeitura));
			linhaLeitura.skip(arquivoLeitura.length());
			int maxLin = linhaLeitura.getLineNumber();

			arqQtd = new FileReader(urLQtd);
			leitorQtd = new BufferedReader(arqQtd);
			linhaQtd = leitorQtd.readLine();
			String sites[] = linhaQtd.split("	");

			vetCard = new Card[maxLin];
			for (int i = 0; i < maxLin; i++) {
				linhaQtd = leitorQtd.readLine();
				String camposQtd[] = linhaQtd.split("	");
				int maxCol = camposQtd.length;
				for (int j = 1; j < maxCol; j++) {
					qtd = new HashMap<>();
					qtd.put(sites[i], Integer.parseInt(camposQtd[j]));
					vetQtd.add(qtd);
				}
				String nome = camposQtd[0];
				cart.put(nome, vetQtd);
				System.out.println(vetQtd);
				// vetCard[i] = new Card(nome, vetPreco, vetQtd);
			}

			for (Map<String, Object> string : c) {
				System.out.println(string);
			}

			linhaLeitura.close();
			leitorQtd.close();
			arqQtd.close();
			System.out.println("vetor Carregado com sucesso!");
		} catch (IOException e) {
			System.out.println("Erro na abertura do arquivo!");
		}
		return vetCard;
	}

	public static void escrever(String path, String texto) {
		try {
			// O parametro � que indica se deve sobrescrever ou continua no
			// arquivo.
			FileWriter fw = new FileWriter(path, true);
			BufferedWriter conexao = new BufferedWriter(fw);
			conexao.write(texto);
			conexao.newLine();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
