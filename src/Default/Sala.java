package Default;

import java.util.ArrayList;
import java.util.List;

import Exceptions.AproximavelException;
import Exceptions.ItemException;
import Exceptions.PersonagemException;

/**
 * @author renan
 *
 */
public class Sala {

    private String nome;
    private int metrosQuadrados;
    private List<Pegavel> itens = null;
    private List<Troll> trolls = null;
    private List<Porta> portas = null;

    Sala(String nome, int tamanho) {
        this.nome = nome;
        this.portas = new ArrayList<>();
        this.itens = new ArrayList<>();
        this.trolls = new ArrayList<>();
        this.setMetrosQuadrados(tamanho);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pegavel> getItens() {
        return itens;
    }

    public void setItens(List<Pegavel> itens) {
        this.itens = itens;
    }

    public List<Troll> getTrolls() {
        return trolls;
    }

    public void setTrolls(List<Troll> trolls) {
        this.trolls = trolls;
    }

    public void setPortas(List<Porta> portas) {
        this.portas = portas;
    }

    public List<Porta> getPortas() {
        return portas;
    }

    public Porta getPorta(String portaStr) throws AproximavelException {
        for (Porta porta : this.portas) {
            if(porta.getIdentificador().toLowerCase().equals(portaStr.toLowerCase())){
            	return porta;
            }
        }
        throw new AproximavelException("Porta " + portaStr + " não encontrada.");
    }

    public void addItem(Pegavel item) throws ItemException {
        if(item instanceof Ouro){
            if(this.getQuantidadeOuro() + 1 > this.metrosQuadrados * 10){
                throw new ItemException("Não cabe mais ouro na sala.");
            }
        }
        this.itens.add(item);
    }

    public void removeItem(Pegavel item) {
        this.itens.remove(item);
    }
    
    public void addPorta(Porta porta){
    	this.portas.add(porta);
    }

    public void addChave() {
        for (Porta porta : this.portas) {
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

    public void addTroll(Troll troll) {
        this.trolls.add(troll);
    }

    public void removeTroll(Troll troll) {
        this.trolls.remove(troll);
    }

    public boolean temTroll() {
        return this.trolls.size() > 0;
    }

    public void imprimeInfoSala() {
        System.out.println("Sala atual: " + this.getNome());
        System.out.println("PORTAS");
        for (Porta porta : this.getPortas()) {
            System.out.println("Porta " + porta.getIdentificador() + ((porta.getAberta()) ? " aberta" : " fechada"));
        }
    }
    
    public boolean temItem(){
        return this.itens.size() > 0;
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
        throw new ItemException("Não há " + itemStr + " na sala");
    }
    
    public int getQuantidadeOuro(){
        int quantidade = 0;
        for(Pegavel pegavel: this.itens){
            if(pegavel instanceof Ouro){
                quantidade += ((Ouro)pegavel).getQuantidade();
            }
        }
        return quantidade;
    }

    public Troll getTroll(String trollName) throws PersonagemException {
        if(this.temTroll() == false){
            throw new PersonagemException("Não há trolls na sala.");
        }
        for (Troll troll : this.trolls) {
            if (troll.getNome().equals(trollName)) {
                return troll;
            }
        }
        throw new PersonagemException("Troll " + trollName + " não está na sala");
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
}
