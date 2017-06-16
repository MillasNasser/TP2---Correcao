package Default;

import java.util.ArrayList;

public class Pocao extends Pegavel {

    private boolean utilizado = false;

    public boolean isUtilizado() {
        return utilizado;
    }

    public void setUtilizado(boolean utilizado) {
        this.utilizado = utilizado;
    }
    
    public boolean compare(String itemStr){
        String[] nomes = {"poção", "pocao", "potion"};
        return super.compare(nomes, itemStr);
    }

    public void usar() {
        //TO-DO:
    }
}
