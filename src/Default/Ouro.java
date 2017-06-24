package Default;

import Exceptions.ItemException;
import java.util.ArrayList;

public class Ouro extends Pegavel {

    private int quantidade;
    
    public Ouro(int quantidade){
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public boolean compare(String itemStr){
        String[] nomes = {"gold", "ouro"};
        return Util.compare(nomes, itemStr);
    }
    
    public void usar() throws ItemException{
        throw new ItemException("ouro não é usavel.");
    }
    
    public String toString(){
        return String.format("ouro <%d>", this.quantidade);
    }
}
