package Default;

import java.util.ArrayList;
import java.util.List;

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
        //TO-DO: mostrar itens e trolls tbm.
    }
}
