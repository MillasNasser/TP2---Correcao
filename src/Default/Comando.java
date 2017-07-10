package Default;

import Exceptions.ItemException;
import java.util.Scanner;

import Exceptions.AproximavelException;
import Exceptions.PersonagemException;
import Interface.Principal;

public class Comando {

	public static void comando(Mapa mapa, String comando) throws Exception {
		//split do comando pelo espaço
		String[] comandoSplited = comando.trim().toLowerCase().split("\\s+", 2);

		String itemStr = null;
		switch (comandoSplited[0]) {
			case "pickup":
				if (comandoSplited.length < 2) {
					throw new Exception("Comando imcompleto");
				}
				//pegar objetos para a mochila
				itemStr = comandoSplited[1];
                mapa.getPlayer().pegar(itemStr);
				break;
			case "drop":
				if (comandoSplited.length < 2) {
					throw new Exception("Comando imcompleto");
				}
				//soltar objeto da mochila
                mapa.getPlayer().largar(comandoSplited[1]);
				break;
			case "moveto":
				if (comandoSplited.length < 2) {
					throw new Exception("Comando imcompleto");
				}
				//andar com o player
				if (comandoSplited[1].contains("door") || comandoSplited[1].contains("porta")) {
					//Vai colocar o player perto da porta
                    String portaStr = comandoSplited[1];
                    Aproximavel porta = mapa.getPlayer().getLocalAtual().getPorta(portaStr);
                    mapa.getPlayer().mover(porta);
				} else if (mapa.getPlayer().getLocalAtual() instanceof Sala) {
                    itemStr = comandoSplited[1];

                    Aproximavel item = ((Sala) mapa.getPlayer().getLocalAtual()).getItem(itemStr);
                    mapa.getPlayer().mover(item);
				}
				break;
			case "throwaxe": ///Necessita deletar o troll do jogo
				if (comandoSplited.length < 2) {
					throw new Exception("Comando imcompleto");
				}
				//Ataca um troll
				Troll troll = mapa.getPlayer().getLocalAtual().getTroll(comandoSplited[1]);
                mapa.getPlayer().throwAxe(troll);
				break;
			case "exit":
				try {
					mapa.getPlayer().sair();
					mapa.getPlayer().desencantar();
				} catch (AproximavelException ex) {
					throw ex;
				}
			
				mapa.verifcarFim();
				
				try {
					mapa.atacarTroll();
					throw new Exception("Jogador foi atacado.");
				} catch (ItemException pe) {
				} catch (PersonagemException e) {
					mapa.moverTroll();
				}
				break;

			case "lock":
				mapa.getPlayer().encantar();
				break;

			case "quit":
				System.exit(0);
			default:
				throw new Exception("Comando inválido");
		}
	}
}
