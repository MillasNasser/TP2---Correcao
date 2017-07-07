package Default;

import Default.Sala;

public class Porta extends Aproximavel{

    private boolean aberta;
    private boolean encantada = false; //se for encantada troll nao passa
    private boolean saida = false;
    private Sala sala;
    private Corredor corredor;
    

    Porta(){
        this.sala = null;
        this.corredor = null;
        this.aberta = false;
        this.encantada = true;
    }
    
    Porta(Sala sala, Corredor corredor) {
        this.sala = sala;
        this.corredor = corredor;
    }

    public boolean isEncantada() {
        return encantada;
    }

    public void setEncantada(boolean encantada) {
        this.encantada = encantada;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public boolean getAberta() {
        return aberta;
    }

    public void setAberta(boolean aberta) {
        this.aberta = aberta;
    }

    public boolean getSaida() {
        return saida;
    }

    public void setSaida(boolean saida) {
        this.saida = saida;
    }
    
    public boolean compare(String portaStr){
        String[] nomes = {"door", "porta"};
        return Util.compare(nomes, portaStr);
    }
    
    public String toString(){
        return "porta";
    }
}