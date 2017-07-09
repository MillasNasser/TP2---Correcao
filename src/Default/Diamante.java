package Default;

import Exceptions.ItemException;

public class Diamante extends Tesouro {
	
	public Diamante(){
		super();
	}
	
	public Diamante(int quantidade){
		super(quantidade);
	}
	
	public boolean compare(String itemStr){
        String[] nomes = {"diamond", "diamante"};
        return Util.compare(nomes, itemStr);
    }
    
    public void usar() throws ItemException{
        throw new ItemException("diamante não é usavel.");
    }
    
    public String toString(){
        return String.format("diamante <%d>", super.getQuantidade());
    }
}
