package Default;

import java.util.ArrayList;
import java.util.Random;

import Default.Sala;
import java.util.List;

public class Porta extends Aproximavel{

    private String identificador;
    private Sala salaSaida;
    private boolean aberta;
    private boolean encantada = false; //se for encantada troll nao passa
    private boolean saida = false;
    Random random = new Random();

    Porta(){
        this.identificador = "entrada";
        this.salaSaida = null;
        this.aberta = false;
        this.encantada = true;
    }
    
    Porta(String indentificador, Sala nomeSalaSaida) {
        this.identificador = indentificador.toLowerCase();
        this.salaSaida = nomeSalaSaida;
		this.aberta = random.nextBoolean();
    }

    public boolean isEncantada() {
        return encantada;
    }

    public void setEncantada(boolean encantada) {
        this.encantada = encantada;
    }
    
    public boolean getEncantada() {
        return this.encantada;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Sala getSalaSaida() {
        return salaSaida;
    }

    public void setSalaSaida(Sala salaSaida) {
        this.salaSaida = salaSaida;
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
        String[] nomes = {this.identificador + " door",
                          "door " + this.identificador,
                          "porta " + this.identificador,
                          this.identificador + " porta"};
        
        return Util.compare(nomes, portaStr);
    }

}