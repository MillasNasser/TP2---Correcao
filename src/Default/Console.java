package Default;

import Exceptions.ItemException;
import java.util.Scanner;

import Exceptions.AproximavelException;
import Exceptions.PersonagemException;

public class Console{

    public static void console(Mapa mapa,String comando) throws Exception {
        System.out.print("Player> ");
        Scanner scanner = new Scanner(System.in);
        //split do comando pelo espaço
        String[] comandoSplited = comando.toLowerCase().split(" ");

        String itemStr = null;
        switch (comandoSplited[0]) {
            case "view":
                //obtem descricao da sala e seu conteudo
                //nome da sala e portas				
                mapa.getPlayer().getLocalAtual().imprimeInfo();
                mapa.getPlayer().mostrarProximo();
                break;
            case "backpack":
                mapa.getPlayer().getItens().imprimeItens();
                break;
            case "pickup":
                if (comandoSplited.length < 2) {
                    System.out.println("Comando incompleto.");
                    break;
                }
                //pegar objetos para a mochila
                itemStr = comandoSplited[1] + ((comandoSplited.length == 4) ? " " + comandoSplited[3] : "");
                try {
                    mapa.getPlayer().pegar(itemStr);

                    System.out.println("Jogador pegou " + itemStr);
                } catch (ItemException ex) {
                    //System.out.println(ex.getMessage());
					throw ex;
                }
                break;
            case "drop":
                if (comandoSplited.length < 2) {
                    System.out.println("Comando incompleto.");
                    break;
                }
                //soltar objeto da mochila
                try {
                    mapa.getPlayer().largar(comandoSplited[1]);

                    System.out.println("Jogador largou " + comandoSplited[1]);
                } catch (ItemException ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            case "moveto":
                if (comandoSplited.length < 2) {
                    System.out.println("Comando incompleto.");
                    break;
                }
                //andar com o player
                if (comandoSplited.length == 3) {
                    //Vai colocar o player perto da porta
                    try {
                        String portaStr = comandoSplited[1] + " " + comandoSplited[2];
                        Aproximavel porta = mapa.getPlayer().getLocalAtual().getPorta(portaStr);
                        mapa.getPlayer().mover(porta);

                        System.out.println("Jogador de aproximou da Porta " + portaStr);
                    } catch (AproximavelException e) {
                        //System.out.println(e.getMessage());
						throw e;
                    }
                } else if(mapa.getPlayer().getLocalAtual() instanceof Sala){
                    try {
                        itemStr = comandoSplited[1] + ((comandoSplited.length == 4) ? " " + comandoSplited[3] : "");

                        Aproximavel item =((Sala) mapa.getPlayer().getLocalAtual()).getItem(itemStr);
                        mapa.getPlayer().mover(item);

                        System.out.println("Jogador de aproximou de " + item);

                    } catch (AproximavelException e) {
                        //System.out.println(e.getMessage());
						throw e;
                    }
                }
                break;
            case "throwaxe": ///Necessita deletar o troll do jogo
                if (comandoSplited.length < 2) {
                    System.out.println("Comando incompleto.");
                    break;
                }
                //Ataca um troll
                Troll troll;
                try {
                    troll = mapa.getPlayer().getLocalAtual().getTroll(comandoSplited[1]);
                    mapa.getPlayer().throwAxe(troll);
                    System.out.println("Jogador matou " + troll.getNome());
                } catch (Exception ex) {
                    //System.out.println(ex.getMessage());
					throw ex;
                }
                break;
            case "exit":
                try {
                    mapa.getPlayer().sair();
                    mapa.getPlayer().desencantar();
                    System.out.println("Jogador se moveu para " + mapa.getPlayer().getLocalAtual().getNome());
                } catch (AproximavelException ex) {
                    //System.out.println(ex.getMessage());
					throw ex;
                }
                try {
                    mapa.atacarTroll();
                    System.out.println("Jogador foi atacado.");
                } catch (PersonagemException ex) {
                    mapa.moverTroll();
                }
                break;

            case "lock":
                try {
                    mapa.getPlayer().encantar();
                    System.out.println("Jogador trancou a porta.");
                } catch (AproximavelException ex) {
                    //System.out.println(ex.getMessage());
					throw ex;
                }
                break;

            case "quit":
                System.exit(0);
            default:
                System.out.println("Comando inválido.");
                break;
        }
    }
}
