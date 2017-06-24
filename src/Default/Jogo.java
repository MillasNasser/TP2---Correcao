package Default;

import Exceptions.PersonagemException;

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
        Mapa mapa = new Mapa("mapa.json");
        
        mapa.espalhaItens();
        mapa.inicializaTrolls();
        
        /**/
        mapa.getPlayer().setSalaAtual(mapa.getSalas().get(0));
        boolean jogavel = true;
        while (jogavel) {
            try {
                //agora ja tenho o jogo pronto para jogar
                jogavel = Console.console(mapa);
            } catch (PersonagemException ex) {
                
            }
        }
        /**/
    }

}
