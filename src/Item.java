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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carta == null) ? 0 : carta.hashCode());
        result = prime * result + qtd;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (carta == null) {
            if (other.carta != null)
                return false;
        } else if (!carta.equals(other.carta))
            return false;
        if (qtd != other.qtd)
            return false;
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Item item = (Item) super.clone();
        item.carta = (Carta) this.carta.clone();
        return item;
    }
}
