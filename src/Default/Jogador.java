package Default;

/**
 *
 */

/**
 * @author renan
 *
 */
public class Jogador {

    private Mochila itens;
    private Porta portaPerto = new Porta();
    private String itemPerto;

    public Porta getPortaPerto() {
        return portaPerto;
    }

    public void setPortaPerto(Porta portaPerto) {
        this.portaPerto = portaPerto;
    }

    public String getItemPerto() {
        return itemPerto;
    }

    public void setItemPerto(String itemPerto) {
        this.itemPerto = itemPerto;
    }

    public Mochila getItens() {
        return itens;
    }

    public void setItens(Mochila itens) {
        this.itens = itens;
    }
    
    //TO-DO: Criar função pra moveTo, drop, usar.
}
