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
    
    public void usar(Jogador player) throws PersonagemException{
        try{
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
}
