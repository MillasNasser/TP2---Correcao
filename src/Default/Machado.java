package Default;

import java.util.ArrayList;

public class Machado extends Pegavel {
    
    public boolean compare(String itemStr){
        String[] nomes = {"axe", "machado"};
        return Util.compare(nomes, itemStr);
    }

    public void usar(Troll troll) throws ItemException {
        if(troll != null){
        	troll.mataTroll();
        }else{
        	throw new ItemException("Troll já está morto");
        }
    }

	@Override
	public void usar() throws ItemException {
		throw new ItemException("Não passou argumentos!");
		
	}

}
