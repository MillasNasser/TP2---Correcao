package Default;

import java.util.ArrayList;

public class Ouro extends Pegavel {

    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public boolean compare(String itemStr){
        String[] nomes = {"gold", "ouro"};
        return super.compare(nomes, itemStr);
    }
}
