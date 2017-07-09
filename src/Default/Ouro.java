package Default;

import Exceptions.ItemException;
import java.util.ArrayList;

public class Ouro extends Tesouro {
	
	public Ouro(){
		super();
	}
	
	public Ouro(int quantidade){
		super(quantidade);
	}
	
    public boolean compare(String itemStr){
        String[] nomes = {"gold", "ouro"};
        return Util.compare(nomes, itemStr);
    }
    
    public void usar() throws ItemException{
        throw new ItemException("ouro não é usavel.");
    }
    
    public String toString(){
        return String.format("ouro <%d>", super.getQuantidade());
    }
}
