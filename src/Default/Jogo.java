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
        Mapa mapa = new Mapa("{\"salas\": [{\"nome\": \"Sala 01\",\"portas\": [{\"identificador\": \"A\",\"aberta\": true,\"encantada\": false,\"salaSaida\": \"Sala 02\",\"saida\": false},{\"identificador\": \"B\",\"aberta\": false,\"encantada\": true,\"salaSaida\": \"Sala 02\",\"saida\": false}]},{\"nome\": \"Sala 02\",\"portas\": [{\"identificador\": \"D\",\"aberta\": true,\"encantada\": false,\"salaSaida\": \"Sala 01\",\"saida\": false}]}]}");
        
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
                Jogo.main(args);
            }
        }
        /**/
    }

}
