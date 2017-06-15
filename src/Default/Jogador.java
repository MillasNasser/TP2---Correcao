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
    private Pegavel itemPerto;
    private Sala salaAtual;

    public Porta getPortaPerto() {
        return portaPerto;
    }

    public void setPortaPerto(Porta portaPerto) {
        this.portaPerto = portaPerto;
    }

    public Pegavel getItemPerto() {
        return itemPerto;
    }

    public void setItemPerto(Pegavel itemPerto) {
        this.itemPerto = itemPerto;
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
    
    public void pegar(String itemStr){
        if(this.itemPerto != null){
            if(this.itemPerto.compare(itemStr)){
                this.itens.addItem(this.itemPerto);
            }
        }
    }
    
    public void largar(String itemStr){
        this.itens.removeItem(itemStr);
    }
    
    public void mover(String itemStr){
        this.itemPerto = this.salaAtual.getItem(itemStr);
        this.portaPerto = null;
    }
    
    public void mover(Porta porta){
        this.portaPerto = porta;
        this.itemPerto = null;
    }
    
    public void usar(Pegavel item, String trollName){
    	Troll troll = new Troll();
    	if(this.itens.compare("axe") != null){
    		troll = salaAtual.getTroll(trollName);
    		if(troll != null){
    			this.itens.removeItem(item);
    			salaAtual.removeTroll(troll);
    		}else{//troll não encontrado
    			//TO-DO : exceções
    		}
    	}else{//player não tem o item
    		//TO-DO : exceções
    	}
    	
    }
    //TO-DO: Criar funÃ§Ã£o pra moveTo, usar.
    

}
