package Default;

import java.util.ArrayList;

public class Pocao extends Pegavel {

    private boolean utilizado = false;
    
    public Pocao() {
        this.setPeso(1);
    }

    public boolean isUtilizado() {
        return utilizado;
    }

    public void setUtilizado(boolean utilizado) {
        this.utilizado = utilizado;
    }
}
