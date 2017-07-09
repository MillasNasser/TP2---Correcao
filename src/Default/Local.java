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
    
    public void imprimeInfo(){
        System.out.printf("Nome: %s\n", this.getNome());
        System.out.println("  PORTAS");
        for(Porta porta: this.getPortas()){
            System.out.println("    Porta " + porta.getIdentificador() + ((porta.getAberta()) ? " aberta" : " fechada")+" e"+((porta.isEncantada()) ? " encantada" : " sem encanto"));
        }
        System.out.println("  TROLLS");
        System.out.println("    Trolls da Caverna");
        for (Troll troll : this.trollsGuerreiros) {
            System.out.println("    " + troll.getNome());
        }
        System.out.println("    Trolls Guerreiros");
        for (Troll troll : this.getTrollsGuerreiros()) {
            System.out.println("    " + troll.getNome());
        }
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
