public class Gene {
    private Carta carta;
    private int loja;

    public Gene(Carta carta, int loja) {
        this.carta = carta;
        this.loja = loja;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public int getLoja() {
        return loja;
    }

    public void setLoja(int loja) {
        this.loja = loja;
    }

    @Override
    public String toString() {
        return "Id Loja: " + loja + " | Id: " + carta.getId() + " | Carta: " + carta.getNome() + "\n";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Carta carta = (Carta) this.carta.clone();
        int loja = this.loja;
        return new Gene(carta, loja);
    }
}
