package Default;

import java.util.ArrayList;
import java.util.Random;

public class Troll {

    private String nome;
    private boolean vivo = true;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void mataTroll() {
        this.vivo = false;
    }
}
