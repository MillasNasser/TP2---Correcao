package Default;

import Exceptions.AproximavelException;
import Exceptions.ItemException;
import Exceptions.PersonagemException;

/**
 * @author renan
 *
 */
public class Jogador {

    private Mochila itens;
    private Sala salaAtual;
    private Aproximavel perto;
    
    public Aproximavel getPerto(){
        return this.perto;
    }
    
    public void setPerto(Aproximavel perto){
        this.perto = perto;
    }

    public Mochila getItens() {
        return itens;
    }

    public void setItens(Mochila itens) {
        this.itens = itens;
    }
    
    public Sala getSalaAtual() {
        return salaAtual;
    }

    public void setSalaAtual(Sala salaAtual) {
        this.salaAtual = salaAtual;
    }
    
    public void pegar(String itemStr) throws ItemException{
        if(this.perto instanceof Pegavel){
            if(this.perto.compare(itemStr)){
                this.itens.addItem((Pegavel)this.perto);
                return;
            }
            throw new ItemException("Você não está perto de " + itemStr + ".");
        }
        throw new ItemException("Você não está perto de nenhum item.");
    }
    
    public void largar(String itemStr){
        this.itens.removeItem(itemStr);
    }
    
    public void mover(Aproximavel aproximavel) throws AproximavelException{
        this.setPerto(aproximavel);
    }
    
    public void throwAxe(Troll troll) throws ItemException{
        try{
            Pegavel machado = this.itens.getItem("axe");
            ((Machado)machado).usar(troll);
            this.salaAtual.removeTroll(troll);
        }catch(ItemException e){
            throw e;
        }
    }
}
