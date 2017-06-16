package Default;

import Exceptions.ItemException;
import Exceptions.PersonagemException;
import java.util.ArrayList;

public class Machado extends Pegavel {
    
    public boolean compare(String itemStr){
        String[] nomes = {"axe", "machado"};
        return Util.compare(nomes, itemStr);
    }

    public void usar(Troll troll) {
        
    }
}
