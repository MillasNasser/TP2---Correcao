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
        mapa.espalhaTrolls();
        mapa.inicializaSalas();
        /**/
        mapa.getPlayer().setSalaAtual(mapa.getSalas().get(0));
        while (true) {
            try {
                //agora ja tenho o jogo pronto para jogar
                Console.console(mapa);
            } catch (PersonagemException ex) {
                
            }
            
            if(mapa.getPlayer().getSalaAtual().getNome().equals("Saida")){
            	mapa.verifcarFim();
            }
        }
        /**/
    }

}
