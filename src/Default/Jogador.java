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

    public Jogador() {
        itens = new Mochila();
    }

    public Aproximavel getPerto() {
        return this.perto;
    }

    public void setPerto(Aproximavel perto) {
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

    public void zerarOuro() {
        int quantidade = this.itens.getOuro().getQuantidade();
        this.itens.getOuro().setQuantidade(0);
    }

    public void pegar(String itemStr) throws ItemException {
        if (this.perto instanceof Pegavel) {
            if (this.perto.compare(itemStr)) {
                try{
                    this.itens.addItem((Pegavel) this.perto);
                    this.salaAtual.removeItem((Pegavel) this.perto);
                    this.perto = null;
                    return;
                }catch(ItemException e){
                    throw e;
                }
            }
            throw new ItemException("Você não está perto de " + itemStr + ".");
        }
        throw new ItemException("Você não está perto de nenhum item.");
    }

    public void largar(String itemStr) throws ItemException {
        this.itens.removeItem(itemStr);
    }

    public void mover(Aproximavel aproximavel) throws AproximavelException {
        if(aproximavel instanceof Ouro){
            if(this.salaAtual.temTroll()){
                throw new ItemException("Há trolls na sala.");
            }
        }
        this.setPerto(aproximavel);
    }

    public void throwAxe(Troll troll) throws ItemException {
        String[] materiais = {"ouro", "bronze", "ferro"};
        
        for(String material: materiais){
            try{
                Pegavel item = this.getItem("machado de " + material);
                try{
                    ((Machado)item).usar(troll);
                }catch(ItemException me){
                    //Machado acabou a duração.
                    this.itens.removeItem(item);
                }finally{
                    this.salaAtual.removeTroll(troll);
                    return;
                }
            }catch(ItemException ex){
                
            }
        }
        throw new ItemException("Jogador não tem machado.");
    }

    public void sair() throws AproximavelException {
        if (this.perto instanceof Porta) {
            Porta porta = (Porta) this.perto;
            if (porta.getAberta() == false) {
                try {
                    Pegavel chave = this.itens.getItem("key");
                    this.itens.removeItem(chave);
		    porta.setAberta(true);
		    for(Porta portas : porta.getSalaSaida().getPortas()){
			if(this.salaAtual.equals(portas.getSalaSaida())){
			    portas.setAberta(true);
			}
		    }
                } catch (ItemException ie) {
                    throw ie;
                }
            }
            this.salaAtual = porta.getSalaSaida();
            this.perto = null;
        } else {
            throw new AproximavelException("Jogador está longe das portas.");
        }
    }
    
    public void trancar() throws AproximavelException {
        if (this.perto instanceof Porta) {
            Porta porta = (Porta) this.perto;
            if (porta.getEncantada()== false) {
                try {
                    Pegavel potion = this.itens.getItem("potion");
                    this.itens.removeItem(potion);
		    porta.setEncantada(true);
		    for(Porta portas : porta.getSalaSaida().getPortas()){
			if(this.salaAtual.equals(portas.getSalaSaida())){
			    portas.setEncantada(true);
			}
		    }
                } catch (ItemException ie) {
                    throw ie;
                }
            }
            this.perto = null;
        } else {
            throw new AproximavelException("Jogador está longe das portas.");
        }
    }

    public void mostrarProximo() {
        if (this.perto != null) {
            System.out.println("Jogador está próximo à: " + this.perto);
        } else {
            System.out.println("Jogador não está próximo a nada.");
        }
    }
    
    public Pegavel getItem(String strItem) throws ItemException {
        try {
            return this.itens.getItem(strItem);
        } catch (ItemException e) {
            throw e;
        }
    }
}
