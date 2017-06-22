package Default;

import Exceptions.ItemException;
import Exceptions.PersonagemException;
import java.util.ArrayList;

public class Machado extends Pegavel {
    protected Integer durabilidade;
    
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
        this.durabilidade--;
	if(durabilidade == 0){
	    throw new ItemException("Quebrou o machado");
	}
    }
    
    ///Do troll
    public void usar(Jogador player) throws PersonagemException{
        try{
	    this.durabilidade--;
            Pegavel pocao = player.getItem("potion");
            player.getItens().removeItem(pocao);
        }catch(ItemException ie){
            try{
                Pegavel ouro = player.getItem("gold");
                player.zerarOuro();
            }catch(ItemException ie2){
                throw new PersonagemException("Morreu.");
            }
        }
    }
    
    public void diminuiDurabilidade() throws ItemException{
	
    }
}
