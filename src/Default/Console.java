package Default;

import Exceptions.ItemException;
import java.util.Scanner;

import Default.Mapa;
import Exceptions.AproximavelException;
import Exceptions.PersonagemException;
import java.time.Clock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Console {

    public static boolean console(Mapa mapa) throws ItemException {
        System.out.print("Player> ");
        Scanner scanner = new Scanner(System.in);
        String comando = scanner.nextLine().replaceAll("\n", "").toLowerCase();
        //split do comando pelo espaÃ§o
        String[] comandoSplited = comando.split(" ");
        switch (comandoSplited[0]) {
            case "view":
                //obtem descricao da sala e seu conteudo
                //nome da sala e portas				
                mapa.getPlayer().getSalaAtual().imprimeInfoSala();
                break;
            case "backpack":
                mapa.getPlayer().getItens().imprimeItens();
                break;
            case "pickup":
                //pegar objetos para a mochila
                if (comandoSplited.length == 2) {
                    mapa.getPlayer().pegar(comandoSplited[1]);
                }
                break;
            case "drop":
                //soltar objeto da mochila
                if (comandoSplited.length == 2) {
                    mapa.getPlayer().largar(comandoSplited[1]);
                }
                break;
            case "moveto":
                //andar com o player	
                if (comandoSplited.length == 3 && comandoSplited[2].equals("door")) {
                    //Vai colocar o player perto da porta
                    try {
                        Aproximavel porta = mapa.getPlayer().getSalaAtual().getPorta(comandoSplited[1]);
                        mapa.getPlayer().mover(porta);
                    } catch (AproximavelException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    //Vai colocar o player perto do item
                    try {
                        Aproximavel item = mapa.getPlayer().getSalaAtual().getItem(comandoSplited[1]);
                        mapa.getPlayer().mover(item);
                    } catch (AproximavelException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;

            case "throwaxe": ///Necessita deletar o troll do jogo
                //Ataca um troll
                Troll troll;
                try {
                    troll = mapa.getPlayer().getSalaAtual().getTroll(comandoSplited[1]);
                    mapa.getPlayer().throwAxe(troll);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            case "exit":
                try {
                    mapa.getPlayer().sair();
                } catch (AproximavelException ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            case "restart":
                return true; //reinicia o jogo
            default:
                break;
        }
        return retorno;
    }
}
