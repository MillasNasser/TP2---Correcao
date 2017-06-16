package Default;

import java.util.ArrayList;

public class Machado extends Pegavel {
    
    public boolean compare(String itemStr){
        String[] nomes = {"axe", "machado"};
        return super.compare(nomes, itemStr);
    }

    public void usar() {
        //TO-DO:
    }

}
