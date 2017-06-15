package Default;

import java.util.ArrayList;
import java.util.List;

import Default.Pegavel;
import Default.Porta;

/**
 * @author renan
 *
 */
public class Sala {

    private String nome;
    private List<Porta> portas;
    private List<Pegavel> itens;
    private List<Troll> trolls;

    Sala(String nome) {
        this.nome = nome;
        this.portas = new ArrayList<>();
        this.itens = new ArrayList<>();
        this.trolls = new ArrayList<>();
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
    
    public Porta getPorta(String portaStr){
        for(Porta porta: this.portas){
            
        }
    }

    public void setPortas(ArrayList<Porta> portas) {
        this.portas = portas;
    }
    
    public void addItem(Pegavel item){
        this.itens.add(item);
    }
    
    public void removeItem(Pegavel item){
        this.itens.remove(item);
    }
    
    public void addChave(){
        for(Porta porta: this.portas){
            if(porta.getAberta() == false){
                this.addItem(new Chave());
                return; //limite de 1 chave para ele ter q escolher uma porta para navegar
            }
        }
    }
    
    public void addTroll(Troll troll){
        this.trolls.add(troll);
    }
    
    public void removeTroll(Troll troll){
        this.trolls.remove(troll);
    }
    
    public boolean temTroll(){
        return this.trolls.size() > 0;
    }

    public void imprimeInfoSala() {
        System.out.println("Sala atual: " + this.getNome());
        System.out.println("PORTAS");
        for (Porta porta : this.getPortas()) {
            System.out.println("Porta " + porta.getIdentificador() + ((porta.getAberta()) ? " aberta" : " fechada"));
        }
    }
        
     public Pegavel getItem(String itemStr){
    	 for(Pegavel item: this.itens){
    		 if(item.compare(itemStr)){
    			 return item;
    		 }
    	 }
    	 return null;
     }
     
     
     public Troll getTroll(String trollName){
     	for(Troll troll: this.trolls){
     		if(troll.getNome().equals(trollName)){
     			return troll;
     		}
     	}
     	return null;
     }
     
     
        //TO-DO: mostrar itens e trolls tbm.
}
