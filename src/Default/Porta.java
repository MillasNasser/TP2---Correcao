package Default;

import java.util.ArrayList;
import java.util.Random;

import Default.Sala;
import java.util.List;

public class Porta {

    private String identificador;
   // private String nomeSalaSaida;
    private Sala salaSaida;
    private boolean aberta;
    private boolean encantada = false; //se for encantada troll nao passa
    private boolean saida = false;
    Random random = new Random();

    Porta(){
	this.identificador = "Entrada";
	this.salaSaida = null;
	this.aberta = false;
	this.encantada = true;
    }
    Porta(String indentificador, Sala nomeSalaSaida) {

        this.identificador = indentificador;
        this.salaSaida = nomeSalaSaida;
		this.aberta = random.nextBoolean();
    }

    public static Porta getPortaByIdentificador(List<Porta> portas, String identificador) {
        for (Porta porta : portas) {
            if (porta.getIdentificador().equals(identificador)) {
                return porta;
            }
        }
        return null;
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

}
