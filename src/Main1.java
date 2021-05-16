import java.util.ArrayList;

public class Main1 {

    public static void main(String[] args) {
        ArrayList<String> arrayFitness = new ArrayList<>();
        arrayFitness.add("Fitness Final");
        ArrayList<String> arrayTempoTotal = new ArrayList<>();
        arrayTempoTotal.add("Tempo de Execucao");
        ArrayList<String> arrayInicioPopulacao = new ArrayList<>();
        arrayInicioPopulacao.add("Inicio Populacao");
        ArrayList<String> arraySelecao = new ArrayList<>();
        arraySelecao.add("Selecao");
        ArrayList<String> arrayCruzamtento = new ArrayList<>();
        arrayCruzamtento.add("Cruzamento");
        ArrayList<String> arrayMutacao = new ArrayList<>();
        arrayMutacao.add("Mutacao");
        ArrayList<String> arrayInsercao = new ArrayList<>();
        arrayInsercao.add("Insercao");
        ArrayList<ArrayList<String>> vetPreco = Control.lerArquivo("ligamagicPreco.txt");
        ArrayList<ArrayList<String>> vetQtd = Control.lerArquivo("ligamagicQtd.txt");
        ArrayList<ArrayList<String>> vetPedido = Control.lerArquivo("ligamagicPedido1.txt");
        ArrayList<Frete> frete = Control.geraVetorFrete(Control.lerArquivo("ligamagicFrete.txt"));
        ArrayList<Item> pedido = Control.geraPedido(vetPedido, vetPreco, vetQtd);
        vetPreco.clear();
        vetQtd.clear();
        for (int j = 0; j < 10; j++) {
            int geracoes = 0;
            int cont = 0;
            int tam = 1000;
            int falhas = 100;
            int chanceMutacao = 3;

            double ti = System.currentTimeMillis();
            double tiCriandoPopulacao = System.currentTimeMillis();
            Cromossomo top1 = new Cromossomo();
            top1.preencherComossomo(pedido, frete);
            top1.avaliacao(frete);
            Populacao populacao = new Populacao(pedido, frete, tam, top1);
            populacao.getPopulacao().add(top1);
            top1 = populacao.getTop1();
            arrayInicioPopulacao.add(String.valueOf((System.currentTimeMillis() - tiCriandoPopulacao) / 1000));
            for (int i = 0; i < falhas; i++) {
                double tiSelecao = System.currentTimeMillis();
                populacao.selecao(tam);
                arraySelecao.add(String.valueOf((System.currentTimeMillis() - tiSelecao) / 1000));
                double tiCruzamento = System.currentTimeMillis();
                populacao.cruzamento(pedido, frete, tam);
                arrayCruzamtento.add(String.valueOf((System.currentTimeMillis() - tiCruzamento) / 1000));
                double tiMutacao = System.currentTimeMillis();
                populacao.mutacao(frete, chanceMutacao);
                arrayMutacao.add(String.valueOf((System.currentTimeMillis() - tiMutacao) / 1000));
                double tiInsercao = System.currentTimeMillis();
                populacao.insercao(pedido, frete, tam);
                arrayInsercao.add(String.valueOf((System.currentTimeMillis() - tiInsercao) / 1000));
                if (top1.getFitness() > populacao.getTop1().getFitness()) {
                    cont = 0;
                    top1 = populacao.getTop1();
                }
                cont++;
                geracoes++;
                System.out.println("Top1: " + populacao.getTop1().getFitness() + " Geração: " + geracoes + " Cont: "
                        + cont + " Tempo de processamento: " + ((System.currentTimeMillis() - ti) / 1000) + "s.");
            }
            double tf = (System.currentTimeMillis() - ti) / 1000;
            arrayTempoTotal.add(String.valueOf(tf));
            arrayFitness.add(String.valueOf(populacao.getTop1().getFitness()));
            System.out.println("Top1 Global:\n" + top1.toString() + "\n");
            System.out.println("Top1 População Final:\n" + populacao.getTop1().toString() + "\n");
            System.out.println("Gerações: " + geracoes);
            System.out.println("Tamanho: " + tam);
            System.out.println("Falhas: " + falhas);
            System.out.println("Tempo de processamento: " + tf + "s.");
            System.out.println("Top1 Global:\n" + top1.toString() + "\n");
            System.out.println("Top1 População Final:\n" + populacao.getTop1().toString() + "\n");
            System.out.println("Gerações: " + geracoes);
            System.out.println("Tamanho: " + tam);
            System.out.println("Falhas: " + falhas);
            System.out.println("Tempo de processamento: " + (tf / 1000) + "s.");
        }
        try {
            Control.gravar("C:\\xampp\\htdocs\\Projetos\\LigaMagic-Java-TCC\\pedido1.json",
                    Control.converter("Pedido 1", arrayInicioPopulacao, arraySelecao, arrayCruzamtento, arrayMutacao,
                            arrayInsercao, arrayTempoTotal, arrayFitness));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
