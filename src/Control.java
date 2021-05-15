import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class Control {
    public static ArrayList<ArrayList<String>> lerArquivo(String caminho) {
        ArrayList<ArrayList<String>> saida = new ArrayList<>();
        FileReader arq;
        BufferedReader leitor;
        try {
            File arquivoLeitura = new File(caminho);
            LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivoLeitura));
            linhaLeitura.skip(arquivoLeitura.length());
            int maxLin = linhaLeitura.getLineNumber();
            arq = new FileReader(caminho);
            leitor = new BufferedReader(arq);
            for (int i = 0; i < maxLin + 1; i++) {
                String linha = leitor.readLine();
                String coluna[] = linha.split("	");
                ArrayList<String> c = new ArrayList<>();
                for (String string : coluna) {
                    c.add(string);
                }
                saida.add(c);
            }
            linhaLeitura.close();
            leitor.close();
            arq.close();
        } catch (IOException e) {
            System.out.println("Erro na abertura do arquivo!");
        }
        return saida;
    }

    static public ArrayList<Frete> geraVetorFrete(ArrayList<ArrayList<String>> listaFrete) {
        ArrayList<Frete> saida = new ArrayList<>();
        listaFrete.get(1).remove(0);
        for (int i = 0; i < listaFrete.get(1).size(); i++) {
            Frete frete = new Frete(i, Float.parseFloat(listaFrete.get(1).get(i)));
            saida.add(frete);
        }
        return saida;
    }

    static public ArrayList<Item> geraPedido(ArrayList<ArrayList<String>> pedido, ArrayList<ArrayList<String>> vetPreco,
            ArrayList<ArrayList<String>> vetQtd) {
        ArrayList<Item> saida = new ArrayList<>();

        for (ArrayList<String> item : pedido) {
            String nome = vetPreco.get(Integer.parseInt(item.get(0))).remove(0);
            vetQtd.get(Integer.parseInt(item.get(0))).remove(0);
            ArrayList<Float> vPreco = new ArrayList<>();
            ArrayList<Integer> vQtd = new ArrayList<>();
            for (int i = 0; i < vetPreco.get(Integer.parseInt(item.get(0))).size(); i++) {
                vPreco.add(Float.parseFloat(vetPreco.get(Integer.parseInt(item.get(0))).get(i)));
                vQtd.add(Integer.parseInt(vetQtd.get(Integer.parseInt(item.get(0))).get(i)));
            }
            Carta carta = new Carta(Integer.parseInt(item.get(0)), nome, vPreco, vQtd);
            Item it = new Item(carta, Integer.parseInt(item.get(1)));
            saida.add(it);
        }
        return saida;
    }
}
