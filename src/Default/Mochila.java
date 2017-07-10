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
	private Diamante diamante;

    public Mochila() {
        machados = new ArrayList<>();
        pocoes = new ArrayList<>();
        chaves = new ArrayList<>();
        ouro = new Ouro(0);
		diamante = new Diamante(0);
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
	
	public Diamante getDiamante() {
        return diamante;
    }

    public void setDiamante(Diamante diamante) {
        this.diamante = diamante;
    }
    
    public int getQuantidadeItem(Class tipo){
        if(tipo == MachadoOuro.class){
            int quantidade = 0;
            for(Pegavel machado: this.machados){
                if(machado instanceof MachadoOuro){
                    quantidade+=((Machado)machado).getDurabilidade();
                }
            }
            return quantidade;
        }else if(tipo == MachadoBronze.class){
            int quantidade = 0;
            for(Pegavel machado: this.machados){
                if(machado instanceof MachadoBronze){
                    quantidade+=((Machado)machado).getDurabilidade();
                }
            }
            return quantidade;
        }else if(tipo == MachadoFerro.class){
            int quantidade = 0;
            for(Pegavel machado: this.machados){
                if(machado instanceof MachadoFerro){
                    quantidade+=((Machado)machado).getDurabilidade();
                }
            }
            return quantidade;
        }else if(tipo == Pocao.class){
            return this.pocoes.size();
        }else if(tipo == Chave.class){
            return this.chaves.size();
        }else if(tipo == Ouro.class){
            return this.ouro.getQuantidade();
        }else if(tipo == Diamante.class){
            return this.diamante.getQuantidade();
        }
        return 0;
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
        }if (item instanceof Diamante) {
            int quantidade = this.diamante.getQuantidade();
			if(((Diamante)item).getQuantidade() == 0){
				throw new ItemException("Nao ha diamante na sala");
			}
            quantidade += ((Diamante) item).getQuantidade();
            this.diamante.setQuantidade(quantidade);
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
}
