import java.util.ArrayList;

public class Item implements Cloneable {
    private Carta carta;
    private int qtd;

    Item(Carta carta, int qtd) {
        this.carta = carta;
        this.qtd = qtd;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @Override
    public String toString() {
        return carta.toString() + "Quantidade: " + qtd + "\n";
    }

    @Override
    public Object clone() {
        try {
            return (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Item(this.carta, this.qtd);
        }
    }
}
