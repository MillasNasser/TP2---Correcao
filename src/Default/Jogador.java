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
    
    public Pegavel getItem(String strItem) throws ItemException{
        try{
            return this.itens.getItem(strItem);
        }catch(ItemException e){
            throw e;
        }
    }
    
    public Sala getSalaAtual() {
        return salaAtual;
    }

    public void setSalaAtual(Sala salaAtual) {
        this.salaAtual = salaAtual;
    }
    
    public void zerarOuro(){
        int quantidade = this.itens.getOuro().getQuantidade();
        this.itens.getOuro().setQuantidade(0);
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
            try{
		((Machado)machado).usar(troll);
	    }catch(ItemException ie){
		System.out.println(ie.getMessage());
	    }
	    this.salaAtual.removeTroll(troll);
        }catch(ItemException e){
            throw e;
        }
    }
    
    public void sair() throws AproximavelException{
        if(this.perto instanceof Porta){
            Porta porta = (Porta)this.perto;
            if(porta.getAberta() == false){
                try{
                    Pegavel chave = this.itens.getItem("key");
                    this.itens.removeItem(chave);
                }catch(ItemException ie){
                    throw ie;
                }
            }
            this.salaAtual = porta.getSalaSaida();
        }else{
            throw new AproximavelException("Jogador está longe das portas.");
        }
    }
}
