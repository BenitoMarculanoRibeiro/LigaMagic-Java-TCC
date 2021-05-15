public class Frete {
    private int loja;
    private float frete;

    public Frete(int loja, float frete) {
        this.loja = loja;
        this.frete = frete;
    }

    public int getLoja() {
        return loja;
    }

    public void setLoja(int loja) {
        this.loja = loja;
    }

    public float getFrete() {
        return frete;
    }

    public void setFrete(float frete) {
        this.frete = frete;
    }

    @Override
    public String toString() {
        return "Loja " + loja + ": " + frete + "\n";
    }
}
