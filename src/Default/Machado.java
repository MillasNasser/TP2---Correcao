package Default;

import java.util.ArrayList;

public class Machado extends Pegavel {
    
    public boolean compare(String itemStr){
        String[] nomes = {"axe", "machado"};
        return Util.compare(nomes, itemStr);
    }

    public void usar(Troll troll) throws ItemException {
        if(troll == null){
        	throw new ItemException("Não há trolls com esse nome");
        }
    }
}
