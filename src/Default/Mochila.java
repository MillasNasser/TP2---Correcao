package Default;

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
    
    public void addItem(Pegavel item){
        if(item instanceof Ouro){
            int quantidade = this.ouro.getQuantidade();
            quantidade += ((Ouro) item).getQuantidade();
            this.ouro.setQuantidade(quantidade);
        }else{
            if(this.itens.size() + 1 < this.capacidade){
                this.itens.add(item);
            }
        }
    }
    
    public void removeItem(Pegavel item){
        this.itens.remove(item);
    }
    
    public void removeItem(String itemStr){
        for(int i = 0; i < this.itens.size(); i++){
            if(this.itens.get(i).compare(itemStr)){
                this.itens.remove(i);
            }
        }
    }
    
    public Pegavel compare(String itemStr){
    	itemStr = itemStr.toLowerCase();
    	for(Pegavel item: this.itens){
    		if(item.compare(itemStr)){
    			return item;
    		}
    	}
    	return null;
    }

    public void imprimeItens() {
        for (Pegavel item : itens) {
	    System.out.println(item.getClass().toString());
	}
    }
}
