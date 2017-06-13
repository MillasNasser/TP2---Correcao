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
}
