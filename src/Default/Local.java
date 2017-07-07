package Default;

import Exceptions.AproximavelException;
import Exceptions.PersonagemException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Local {
    private Map<String, Porta> portas;
    private List<TrollGuerreiro> trollsGuerreiros;
    
    public Local(){
        this.portas = new HashMap<>();
        this.trollsGuerreiros = new ArrayList<>();
    }
    
    //Porta.
    public void addPorta(String portaStr, Porta porta) throws AproximavelException{
        this.portas.put(portaStr.toLowerCase(), porta);
    }
    
    public Porta getPorta(String portaStr) throws AproximavelException{
        Porta porta = this.portas.get(portaStr);
        if(porta == null){
            throw new AproximavelException("Porta " + portaStr + " não encontrada.");
        }
        return porta;
    }
    
    public Map<String, Porta> getPortas() {
        return this.portas;
    }
    
    
    //Troll
    public void addTrollGuerreiro(TrollGuerreiro troll){
        this.trollsGuerreiros.add(troll);
    }
    
    public void removeTrollGuerreiro(TrollGuerreiro troll){
        this.trollsGuerreiros.remove(troll);
    }
    
    public boolean temTrollGuerreiro(){
        return (this.trollsGuerreiros.size() > 0);
    }
    
    public TrollGuerreiro getTrollGuerreiro(String trollNome) throws PersonagemException {
        if(this.temTrollGuerreiro()== false){
            throw new PersonagemException("Não há trolls na sala.");
        }
        for (TrollGuerreiro troll : this.trollsGuerreiros) {
            if (troll.getNome().toLowerCase().equals(trollNome)) {
                return troll;
            }
        }
        throw new PersonagemException("Troll " + trollNome + " não está na sala");   
    }
    
    public List<TrollGuerreiro> getTrollsGuerreiros() {
        return this.trollsGuerreiros;
    }
}
