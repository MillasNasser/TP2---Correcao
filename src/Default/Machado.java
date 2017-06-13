package Default;

import java.util.ArrayList;

public class Machado extends Pegavel {

    private boolean utilizado = false;

    public boolean isUtilizado() {
        return utilizado;
    }

    public void setUtilizado(boolean utilizado) {
        this.utilizado = utilizado;
    }

    public Machado() {
        this.setPeso(1);
    }
    
    public boolean compare(String itemStr){
        String[] nomes = {"axe", "machado"};
        return super.compare(nomes, itemStr);
    }
}
