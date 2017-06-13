package Default;


import java.util.ArrayList;

public class Chave extends Pegavel{
	
    private boolean usada = false;


    public Chave() {
        this.setPeso(1);
    }

    public boolean isUsada() {
        return usada;
    }

    public void setUsada(boolean usada) {
        this.usada = usada;
    }
    
    public boolean compare(String itemStr){
        String[] nomes = {"chave", "key"};
        return super.compare(nomes, itemStr);
    }
}
