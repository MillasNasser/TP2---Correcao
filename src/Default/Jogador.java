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

    public Pegavel getItem(String strItem) throws ItemException {
        try {
            return this.itens.getItem(strItem);
        } catch (ItemException e) {
            throw e;
        }
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
                }
                return;
            }catch(ItemException ex){
                
            }
        }
        throw new ItemException("Jogador não tem machado.");
        /*try {
            String[] materiais = {"ouro", "bronze", "ferro"};
            Pegavel machado;
            for(String material: materiais){
                machado = this.itens.getItem("machado de " + material);
                try {
                    ((Machado) machado).usar(troll);
                } catch (ItemException ie) {
                    System.out.println(ie.getMessage());
                    this.itens.removeItem(machado);
                }
                this.salaAtual.removeTroll(troll);
                return;
            }
        } catch (ItemException e) {
            throw e;
        }*/
    }

    public void sair() throws AproximavelException {
        if (this.perto instanceof Porta) {
            Porta porta = (Porta) this.perto;
            if (porta.getAberta() == false) {
                try {
                    Pegavel chave = this.itens.getItem("key");
                    this.itens.removeItem(chave);
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

    public void mostrarProximo() {
        if (this.perto != null) {
            System.out.println("Jogador está próximo à: " + this.perto);
        } else {
            System.out.println("Jogador não está próximo a nada.");
        }
    }
}
