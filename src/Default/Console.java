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

    public static boolean console(Mapa mapa) throws PersonagemException {
        System.out.print("Player> ");
        Scanner scanner = new Scanner(System.in);
        String comando = scanner.nextLine().replaceAll("\n", "").toLowerCase();
        //split do comando pelo espaço
        String[] comandoSplited = comando.split(" ");
        
        String itemStr = null;
        switch (comandoSplited[0]) {
            case "view":
                //obtem descricao da sala e seu conteudo
                //nome da sala e portas				
                mapa.getPlayer().getSalaAtual().imprimeInfoSala();
                mapa.getPlayer().mostrarProximo();
                return true;
            case "backpack":
                mapa.getPlayer().getItens().imprimeItens();
                return true;
            case "pickup":
                if(comandoSplited.length < 2){
                    System.out.println("Comando incompleto.");
                    return true;
                }
                //pegar objetos para a mochila
                itemStr = comandoSplited[1] + ((comandoSplited.length == 4)?" " + comandoSplited[3]:"");
                try {
                    mapa.getPlayer().pegar(itemStr);
                    
                    System.out.println("Jogador pegou " + itemStr);
                } catch (ItemException ex) {
                    System.out.println(ex.getMessage());
                }
                return true;
            case "drop":
                if(comandoSplited.length < 2){
                    System.out.println("Comando incompleto.");
                    return true;
                }
                //soltar objeto da mochila
                try {
                    mapa.getPlayer().largar(comandoSplited[1]);

                    System.out.println("Jogador largou "+ comandoSplited[1]);
                } catch (ItemException ex) {
                    System.out.println(ex.getMessage());
                }
                return true;
            case "moveto":
                if(comandoSplited.length < 2){
                    System.out.println("Comando incompleto.");
                    return true;
                }
                //andar com o player
                if (comandoSplited.length == 3 && comandoSplited[2].equals("door")) {
                    //Vai colocar o player perto da porta
                    try {
                        Aproximavel porta = mapa.getPlayer().getSalaAtual().getPorta(comandoSplited[1]);
                        mapa.getPlayer().mover(porta);
                        
                        System.out.println("Jogador de aproximou da Porta " + ((Porta)porta).getIdentificador());
                    } catch (AproximavelException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try{
                        itemStr = comandoSplited[1] + ((comandoSplited.length == 4)?" " + comandoSplited[3]:"");
                        
                        Aproximavel item = mapa.getPlayer().getSalaAtual().getItem(itemStr);
                        mapa.getPlayer().mover(item);
                        
                        System.out.println("Jogador de aproximou de " + item);
                        
                    }catch(AproximavelException e){
                        System.out.println(e.getMessage());
                    }
                }
                return true;
            case "throwaxe": ///Necessita deletar o troll do jogo
                if(comandoSplited.length < 2){
                    System.out.println("Comando incompleto.");
                    return true;
                }
                //Ataca um troll
                Troll troll;
                try {
                    troll = mapa.getPlayer().getSalaAtual().getTroll(comandoSplited[1]);
                    mapa.getPlayer().throwAxe(troll);
                    System.out.println("Jogador matou " + troll.getNome());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                return true;
            case "exit":
                try {
                    mapa.getPlayer().sair();
                    System.out.println("Jogador se moveu para a sala " + mapa.getPlayer().getSalaAtual().getNome());
                } catch (AproximavelException ex) {
                    System.out.println(ex.getMessage());
                }
                try{
                    mapa.atacarTroll();
                    System.out.println("Jogador foi atacado.");
                }catch(PersonagemException ex){
                    mapa.moverTroll();
                }
                return !mapa.verifcarFim();
            default:
                return true;
        }
    }
}
