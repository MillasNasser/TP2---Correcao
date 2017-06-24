package Default;


import Exceptions.ItemException;
import java.util.ArrayList;

public class Chave extends Pegavel{
	
    private boolean usada = false;

    public boolean isUsada() {
        return usada;
    }

    public void setUsada(boolean usada) {
        this.usada = usada;
    }
    
    public boolean compare(String itemStr){
        String[] nomes = {"chave", "key"};
        return Util.compare(nomes, itemStr);
    }

    
    public void usar(Porta porta) throws ItemException {
        if(porta.getAberta() == false){
            porta.setAberta(true);
            return;
        }
        throw new ItemException("Porta não está trancada.");
    }
    
    public String toString(){
        return "chave";
    }
}
