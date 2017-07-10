package Default;

import Exceptions.AproximavelException;
import Exceptions.PersonagemException;
import java.util.ArrayList;
import java.util.List;

public class Local {
    private String nome;
    private List<Porta> portas;
    private List<Troll> trollsGuerreiros;
    
    public Local(String nome){
        this.nome = nome;
        this.portas = new ArrayList<>();
        this.trollsGuerreiros = new ArrayList<>();
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    //Porta.
    public void addPorta(Porta porta) throws AproximavelException{
        this.portas.add(porta);
    }
    
    public Porta getPorta(String portaStr) throws AproximavelException{
        for(Porta porta: this.portas){
            if(porta.compare(portaStr)){
                return porta;
            }
        }
        throw new AproximavelException("Porta " + portaStr + " não encontrada.");
    }
    
    public List<Porta> getPortas() {
        return this.portas;
    }
    
    
    //Troll
    public void addTrollGuerreiro(TrollGuerreiro troll){
        this.trollsGuerreiros.add(troll);
    }
    
    public void removeTroll(Troll troll){
        this.trollsGuerreiros.remove(troll);
    }
    
    public boolean temTrollGuerreiro(){
        return (this.trollsGuerreiros.size() > 0);
    }
    
    public Troll getTroll(String trollNome) throws PersonagemException {
        if(this.temTrollGuerreiro()== false){
            throw new PersonagemException("Não há trolls na sala.");
        }
        for (Troll troll : this.trollsGuerreiros) {
            if (troll.getNome().toLowerCase().equals(trollNome)) {
                return troll;
            }
        }
        throw new PersonagemException("Troll " + trollNome + " não está na sala");   
    }
	
	public List<Troll> getTrolls(){
		return this.trollsGuerreiros;
	}
    
    public List<Troll> getTrollsGuerreiros() {
        return this.trollsGuerreiros;
    }
}
