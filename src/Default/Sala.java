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
    Ouro ouro = null;
    private List<Troll> trolls = null;
    private List<Porta> portas = null;

    Sala(String nome, int tamanho) {
        this.nome = nome;
        this.portas = new ArrayList<>();
        this.itens = new ArrayList<>();
        this.ouro = new Ouro(0);
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
        return this.trolls;
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
            int quantidadeAtual = this.getQuantidadeOuro();
            int novaQuantidade = quantidadeAtual + ((Ouro)item).getQuantidade();
            ((Ouro)item).setQuantidade(novaQuantidade);
            
            this.ouro.setQuantidade(novaQuantidade);
            if(this.getQuantidadeOuro() + 1 > this.metrosQuadrados * 10){
                throw new ItemException("Não cabe mais ouro na sala.");
            }
            return;
        }
        this.itens.add(item);
    }

    public void removeItem(Pegavel item) {
        if(item instanceof Ouro){
            this.ouro.setQuantidade(0);
        }else{
            this.itens.remove(item);
        }
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
        if(this.trolls.size() > 0){
            return true;
        }else{
            return false;
        }
        //return (this.trolls.size() > 0);
    }

    public void imprimeInfoSala() {
        System.out.printf("Sala Atual: %s | Tamanho: %d metros quadrados\n", this.nome, this.metrosQuadrados);
        System.out.println("  PORTAS");
        for (Porta porta : this.getPortas()) {
            System.out.println("    Porta " + porta.getIdentificador() + ((porta.getAberta()) ? " aberta" : " fechada")+" e"+((porta.getEncantada()) ? " encantada" : " sem encanto"));
        }
        System.out.println("  ITENS");
        System.out.printf("%s", (this.ouro.getQuantidade() > 0)?String.format("    Ouro <%d>\n", this.ouro.getQuantidade()):"");
        for (Pegavel item : this.itens) {
            System.out.println("    " + item);
        }
        System.out.println("  TROLLS");
        for (Troll troll : this.trolls) {
            System.out.println("    " + troll.getNome());
        }
        System.out.println();
    }
    
    public boolean temItem(){
        return (this.itens.size() > 0 || this.ouro.getQuantidade() > 0);
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
        if(this.ouro.compare(itemStr)){
            return this.ouro;
        }
        throw new ItemException("Não há " + itemStr + " na sala");
    }
    
    public int getQuantidadeOuro(){
        return this.ouro.getQuantidade();
    }

    public Troll getTroll(String trollName) throws PersonagemException {
        if(this.temTroll() == false){
            throw new PersonagemException("Não há trolls na sala.");
        }
        for (Troll troll : this.trolls) {
            if (troll.getNome().toLowerCase().equals(trollName)) {
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
    
    public boolean equals(Sala sala){
        return this.nome.equals(sala.getNome());
    }
}
