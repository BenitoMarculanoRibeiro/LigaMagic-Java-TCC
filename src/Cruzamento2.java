public class Cruzamento2 {	
	public static Cromossomo S(Cromossomo[] pais,Card[] vetCard,Loja[] vetLoja,int tipo) {
		Cromossomo filho = null;
		switch(tipo) {
		case 1:
			filho = PRAleatorio(pais,vetCard,vetLoja);
			break;
		}
		return filho;
	}
	public static Cromossomo PRAleatorio(Cromossomo[] pais,Card[] vetCard,Loja[] vetLoja) {
		Cromossomo guia = new Cromossomo(pais[0].getNumGenes());
		Cromossomo filho = new Cromossomo(pais[0].getNumGenes());
		int[] aux = new int[pais[0].getNumGenes()];
		for(int i=0;i<pais[0].getNumGenes();i++){
	         guia.copiaGene(pais[0].getLoja(i),pais[0].getCard(i),i);
	         aux[i] = i;
	    }
		float fitMelhor = Float.MAX_VALUE;
		int cont = guia.getNumGenes();
		for(int i=0;i<guia.getNumGenes()-1;i++){
			int rand = (int)(Math.random()*cont);
			guia.copiaGene(pais[aux[rand]].getLoja(aux[rand]),pais[1].getCard(aux[rand]),aux[rand]);
			guia.avaliacao(vetCard, vetLoja);
			if(fitMelhor > guia.getFitness()){
	             fitMelhor = guia.getFitness();
	             for(int j=0;j<guia.getNumGenes();j++){
	            	 filho.copiaGene(guia.getLoja(j),guia.getCard(j),j);
	             }
	         }
			aux[rand] = aux[cont-1];
	        cont--;
	    }
		return filho;
	}
}