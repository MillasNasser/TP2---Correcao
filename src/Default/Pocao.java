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
        return Util.compare(nomes, itemStr);
    }
    
    public void usar(Porta porta) throws ItemException{
        if(porta.getAberta()){
            porta.setAberta(false);
        }else{
            throw new ItemException("Porta já está trancada");
        }
    }
}
