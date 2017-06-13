package Default;

import java.util.ArrayList;
import java.util.Random;

public class Porta {

    private String identificador;
    private String nomeSalaSaida;
    private boolean aberta;
    private boolean encantada = false; //se for encantada troll nao passa
    private boolean saida = false;

    Porta(String indentificador, String nomeSalaSaida) {

        this.identificador = indentificador;
        this.nomeSalaSaida = nomeSalaSaida;
        this.aberta = random.nextBoolean();
    }

    public static Porta getPortaByIdentificador(ArrayList<Porta> portas, String identificador) {
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

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNomeSalaSaida() {
        return nomeSalaSaida;
    }

    public void setNomeSalaSaida(String nomeSalaSaida) {
        this.nomeSalaSaida = nomeSalaSaida;
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
