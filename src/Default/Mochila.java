package Default;

import Exceptions.ItemException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mochila {

    private int capacidade = 10;

    private List<Pegavel> machados;
    private List<Pegavel> pocoes;
    private List<Pegavel> chaves;

    private Ouro ouro;

    public Mochila() {
        machados = new ArrayList<>();
        pocoes = new ArrayList<>();
        chaves = new ArrayList<>();
        ouro = new Ouro(0);
    }

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

    public void addItem(Pegavel item) throws ItemException {
        if (item instanceof Ouro) {
            int quantidade = this.ouro.getQuantidade();
			if(((Ouro)item).getQuantidade() == 0){
				throw new ItemException("Nao ha ouro na sala");
			}
            quantidade += ((Ouro) item).getQuantidade();
            this.ouro.setQuantidade(quantidade);
            return;
        } else if (item instanceof Machado) {
            if (this.machados.size() + 1 <= 4) {
                this.machados.add(item);
                return;
            }
        } else if (item instanceof Pocao) {
            if (this.pocoes.size() + 1 <= 3) {
                this.pocoes.add(item);
                return;
            }
        } else if (item instanceof Chave) {
            if (this.chaves.size() + 1 <= 3) {
                this.chaves.add(item);
                return;
            }
        }
        throw new ItemException("Mochila cheia.");
    }

    public void removeItem(String itemStr) throws ItemException {
        try {
            Pegavel item = getItem(itemStr);
            removeItem(item);
        } catch (ItemException ex) {
            throw ex;
        }
    }

    public void removeItem(Pegavel item) {
        if (item instanceof Machado) {
            this.machados.remove(item);
        } else if (item instanceof Pocao) {
            this.pocoes.remove(item);
        } else if (item instanceof Chave) {
            this.chaves.remove(item);
        }
    }

    public Pegavel getItem(String itemStr) throws ItemException {
        List<Pegavel> allItens = new ArrayList<Pegavel>();
        allItens.addAll(pocoes);
        allItens.addAll(chaves);
        allItens.addAll(machados);

        for (Pegavel item : allItens) {
            if (item.compare(itemStr)) {
                return item;
            }
        }
        throw new ItemException("Não há " + itemStr + " na mochila.");
    }

    public void imprimeItens() {
        System.out.println("Ouro <" + ouro.getQuantidade() + ">");
        System.out.println("Poções: " + pocoes.size());
        System.out.println("Chaves: " + chaves.size());
        System.out.println("Machados:");
        for (Pegavel item : this.machados) {
            Machado machado = (Machado)item;
            System.out.printf("    -%s | %d\n", machado.getMaterial(), machado.getDurabilidade());
            /*System.out.println("    -machado de " + ((Machado) item).getMaterial()
                + ": " + ((Machado) item).getDurabilidade());*/
        }

    }
}
