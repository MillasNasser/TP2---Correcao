package Default;

import Exceptions.ItemException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Mochila {

    private int capacidade = 5;

    private List<Pegavel> itens;
    private Ouro ouro;

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public Ouro getOuro() {
        return ouro;
    }

    public void setOuro(Ouro ouro) {
        this.ouro = ouro;
    }

    public void addItem(Pegavel item) {
        if (item instanceof Ouro) {
            int quantidade = this.ouro.getQuantidade();
            quantidade += ((Ouro) item).getQuantidade();
            this.ouro.setQuantidade(quantidade);
        } else {
            if (this.itens.size() + 1 < this.capacidade) {
                this.itens.add(item);
            }
        }
    }

    public void removeItem(Pegavel item) {
        this.itens.remove(item);
    }

    public void removeItem(String itemStr) {
        for (int i = 0; i < this.itens.size(); i++) {
            if (this.itens.get(i).compare(itemStr)) {
                this.itens.remove(i);
            }
        }
    }

    public Pegavel getItem(String itemStr) throws ItemException {
        itemStr = itemStr.toLowerCase();
        for (Pegavel item : this.itens) {
            if (item.compare(itemStr)) {
                return item;
            }
        }
        throw new ItemException("Não há " + itemStr + "na mochila.");
    }

    public void imprimeItens() {
        for (Pegavel item : itens) {
            System.out.println(item.getClass().toString());
        }
    }
}
