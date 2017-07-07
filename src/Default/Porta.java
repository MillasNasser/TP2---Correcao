package Default;

public class Porta extends Aproximavel{

    private boolean aberta;
    private boolean encantada; //se for encantada troll nao passa
    private boolean saida;
    private Sala sala;
    private Corredor corredor;
    

    Porta(){
        this.aberta = false;
        this.encantada = false;
        this.saida = false;
        this.sala = null;
        this.corredor = null;
    }
    
    Porta(Sala sala, Corredor corredor) {
        super();
        this.sala = sala;
        this.corredor = corredor;
    }

    public boolean isEncantada() {
        return encantada;
    }

    public void setEncantada(boolean encantada) {
        this.encantada = encantada;
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
    
    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Corredor getCorredor() {
        return corredor;
    }

    public void setCorredor(Corredor corredor) {
        this.corredor = corredor;
    }
    
    @Override
    public boolean compare(String portaStr){
        String[] nomes = {"door", "porta"};
        return Util.compare(nomes, portaStr);
    }
    
    @Override
    public String toString(){
        return "porta";
    }
}