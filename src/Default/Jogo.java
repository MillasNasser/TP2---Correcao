package Default;

import Exceptions.ItemException;
import Exceptions.PersonagemException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author renan
 *
 */
public class Jogo {

    private boolean jogando = false;

    public boolean isJogando() {
        return jogando;
    }

    public void setJogando(boolean jogando) {
        this.jogando = jogando;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Mapa mapa = new Mapa();
        boolean jogavel = true;
        while (jogavel) {
            Jogo jogo = new Jogo();
            try {
                //agora ja tenho o jogo pronto para jogar
                jogavel = Console.console(mapa);
            } catch (PersonagemException ex) {
                Jogo.main(args);
            }
        }

    }

}
