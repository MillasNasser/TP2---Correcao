package Default;

import Exceptions.ItemException;
import Exceptions.PersonagemException;
import java.util.ArrayList;

public class Machado extends Pegavel {
    protected Integer durabilidade;
    protected String material;
    
    public String getMaterial() {
	return material;
    }
    
    public Integer getDurabilidade() {
	return durabilidade;
    }

    protected void setDurabilidade(Integer durabilidade) {
	this.durabilidade = durabilidade;
    }
    
    public boolean compare(String itemStr){ ///Alterar daqui a pouco
        String[] nomes = {"axe", "machado"};
        return Util.compare(nomes, itemStr);
    }

    ///Do jogador
    public void usar(Troll troll) throws ItemException{
        try{
	    diminuiDurabilidade();
	}catch(ItemException ie){
	    throw ie;
	}
    }
    
    ///Do troll
    public void usar(Jogador player) throws PersonagemException{
        while(durabilidade != 0){
	    try{
		try{
		    diminuiDurabilidade();
		}catch(ItemException e){}
		
		Pegavel pocao = player.getItem("potion");
		player.getItens().removeItem(pocao);
	    }catch(ItemException ie){
		player.zerarOuro();
	    }
	}
    }
    
    public void diminuiDurabilidade() throws ItemException{
	if(this.durabilidade == 0){
	    throw new ItemException("Quebrou o machado");
	}
	this.durabilidade--;
    }
}
