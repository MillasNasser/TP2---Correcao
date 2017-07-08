package Default;

import Exceptions.ItemException;

public class Diamante extends Pegavel {
	
	private int quantidade;
	
	public Diamante(int quantidade){
        this.quantidade = quantidade;
    }

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public boolean compare(String itemStr){
        String[] nomes = {"diamond", "diamante"};
        return Util.compare(nomes, itemStr);
    }
    
    public void usar() throws ItemException{
        throw new ItemException("diamante não é usavel.");
    }
    
    public String toString(){
        return String.format("diamante <%d>", this.quantidade);
    }
}
