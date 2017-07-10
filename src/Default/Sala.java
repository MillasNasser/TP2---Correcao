package Default;

import java.util.ArrayList;
import java.util.List;

import Exceptions.ItemException;
import Exceptions.PersonagemException;
import java.util.Map;

public class Sala extends Local{
    private int metrosQuadrados;
    private List<Pegavel> itens = null;
    Ouro ouro = null;
	Diamante diamante = null;
    private List<TrollCaverna> trollsCaverna = null;

    Sala(String nome, int tamanho) {
        super(nome);
        this.itens = new ArrayList<>();
        this.ouro = new Ouro(0);
		this.diamante = new Diamante(0);
        this.trollsCaverna = new ArrayList<>();
        this.setMetrosQuadrados(tamanho);
    }

    public List<Pegavel> getItens() {
        return itens;
    }

    public void setItens(List<Pegavel> itens) {
        this.itens = itens;
    }
    
    public int getMetrosQuadrados() {
        return metrosQuadrados;
    }

    public void setMetrosQuadrados(int metrosQuadrados) throws IllegalArgumentException {
        //Checando por erros.
        if(metrosQuadrados < 1){
            throw new IllegalArgumentException("Metros Quadrados não pode ser maior que 1.");
        }
        if(this.getQuantidadeOuro() > metrosQuadrados * 10){
            throw new IllegalArgumentException("Metros Quadrados menor que a quantidade atual de ouro.");
        }
        
        this.metrosQuadrados = metrosQuadrados;
    }
    
    public int getQuantidadeOuro(){
        return this.ouro.getQuantidade();
    }
	
	public int getQuantidadeDiamante(){
        return this.diamante.getQuantidade();
    }
    
    public int getQuantidadeItem(Class classe){
        int quantidade = 0;
        for(Pegavel item: this.itens){
            if(item.getClass().equals(classe)){
                quantidade++;
            }
        }
        return quantidade;
    }

    public void addItem(Pegavel item) throws ItemException {
        if(item instanceof Ouro){
            int quantidadeAtual = this.getQuantidadeOuro();
            int novaQuantidade = quantidadeAtual + ((Ouro)item).getQuantidade();
            ((Ouro)item).setQuantidade(novaQuantidade);
            
            this.ouro.setQuantidade(novaQuantidade);
            if(this.getQuantidadeOuro() + 1 > this.metrosQuadrados * 10){
                throw new ItemException("Não cabe mais ouro na sala.");
            }
            return;
        }else if(item instanceof Diamante){
			int quantidadeAtual = this.getQuantidadeDiamante();
            int novaQuantidade = quantidadeAtual + ((Diamante)item).getQuantidade();
            ((Diamante)item).setQuantidade(novaQuantidade);
            
            this.diamante.setQuantidade(novaQuantidade);
            if(this.getQuantidadeDiamante()+ 1 > this.metrosQuadrados * 10){
                throw new ItemException("Não cabe mais diamante na sala.");
            }
            return;
		}
        this.itens.add(item);
    }

    public void removeItem(Pegavel item) {
        if(item instanceof Ouro){
            this.ouro.setQuantidade(0);
        }else if(item instanceof Diamante){
            this.diamante.setQuantidade(0);
        }else{
            this.itens.remove(item);
        }
    }

    public void addChave() {
        for(Porta porta: this.getPortas()){
            if (porta.getAberta() == false) {
                try {
                    this.addItem(new Chave());
                } catch (ItemException ex) {
                    //A função addItem só lançará uma exceção caso o item seja Ouro.
                }
                return; //limite de 1 chave para ele ter q escolher uma porta para navegar
            }
        }
    }

    public void addTrollCaverna(TrollCaverna troll) {
        this.trollsCaverna.add(troll);
    }

    public void removeTrollCaverna(TrollCaverna troll) {
        this.trollsCaverna.remove(troll);
    }

    public boolean temTrollCaverna(){
        return (this.trollsCaverna.size() > 0);
    }
    
    public boolean temTroll() {
        return (this.temTrollGuerreiro() || this.temTrollCaverna());
    }
    
    public void removeTroll(Troll troll){
        if(troll instanceof TrollCaverna){
            this.removeTrollCaverna((TrollCaverna) troll);
        }else if(troll instanceof TrollGuerreiro){
            super.removeTroll((TrollGuerreiro) troll);
        }
    }
    
	public List<Troll> getTrolls(){
		List<Troll> todosTrolls = new ArrayList<>();
		todosTrolls.addAll(trollsCaverna);
		todosTrolls.addAll(super.getTrollsGuerreiros());
		return todosTrolls;
	}
	
    public boolean temItem(){
        return (this.itens.size() > 0 || 
				this.ouro.getQuantidade() > 0 || 
				this.diamante.getQuantidade() > 0);
    }

    public Pegavel getItem(String itemStr) throws ItemException {
        if(this.temItem() == false){
            throw new ItemException("Não há itens na sala.");
        }
        for (Pegavel item : this.itens) {
            if (item.compare(itemStr)) {
                return item;
            }
        }
        if(this.ouro.compare(itemStr) && this.getQuantidadeOuro() > 0){
            return this.ouro;
        }else if(this.diamante.compare(itemStr) && this.getQuantidadeDiamante() > 0){
			return this.diamante;
		}
        throw new ItemException("Não há " + itemStr + " na sala");
    }

    public Troll getTroll(String trollName) throws PersonagemException {
        //Verifica se há Trolls na sala.
        if(this.temTroll() == false){
            throw new PersonagemException("Não há trolls na sala.");
        }
        
        //Verifica se o troll é um dos trolls da caverna da sala.
        for (Troll troll : this.trollsCaverna) {
            if (troll.getNome().toLowerCase().equals(trollName)) {
                return troll;
            }
        }
        
        //Se não for, então basta chamar a função getTroll, que
        //ficará encarregada de lançar a exceção caso o troll não seja encontrado.
        return super.getTroll(trollName);
    }
    
    public boolean equals(Sala sala){
        return this.getNome().equals(sala.getNome());
    }
}
