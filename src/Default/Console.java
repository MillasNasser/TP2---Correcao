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
                if (this.player.getPortaPerto() != null) {
                    if (this.player.getPortaPerto().getSaida()) {
                        //verificar se ele coletou todo o ouro disponivel
                        boolean zerou = true;
                        for (Ouro ouro : this.ouros) {
                            if (!ouro.isPegado()) {
                                zerou = false;
                                break;
                            }
                        }
                        if (zerou) {
                            System.out.println("VOCÃŠ VENCEU!");
                            return false;
                        } else {
                            System.out.println("VocÃª nÃ£o coletou todo o ouro disponÃ­vel.");
                        }

                        break;
                    } else {
                        Sala s = Sala.getSalabyNome(this.salas, this.player.getPortaPerto().getNomeSalaSaida());
                        if (s != null) {
                            if (this.player.getPortaPerto().getAberta()) {
                                //porta aberta pode passar o jogador
                                this.player.setLocalizacao(s);
                                //movimentar trolls
                                for (Troll troll : this.trolls) {
                                    troll.movimentaTroll(this.salas);
                                    //verifica se a saida do troll Ã© a sala do cara
                                    if (troll.isVivo() && troll.getLocalizacao() == this.player.getLocalizacao()) {
                                        //verificar se tem machado solto na sala
                                        for (Machado machado : this.machados) {
                                            if (!machado.isPegado() && !machado.isUtilizado() && machado.getLocalizacao() == this.player.getLocalizacao()) {
                                                //tem machado troll vai atacar
                                                System.out.println("Troll de nome " + troll.getNome() + " atacou vocÃª com um machado");
                                                boolean temPocao = false;
                                                for (Pocao pocao : this.player.getItens().getPocoes()) {
                                                    if (pocao.isPegado() && !pocao.isUtilizado()) {
                                                        pocao.setUtilizado(true);
                                                        temPocao = true;
                                                        break;
                                                    }
                                                }
                                                if (temPocao) {
                                                    System.out.println("VocÃª usou uma poÃ§Ã£o para sobreviver!");
                                                } else {
                                                    System.out.println("VocÃª nÃ£o possui poÃ§Ãµes.");
                                                    System.out.println("VocÃª morreu!");
                                                    return false;
                                                }

                                            }
                                        }

                                    }

                                }

                            } else {
                                //porta fechada
                                //verifica se tem uma chave
                                if (this.player.getItens().getChaves().size() > 0) {
                                    //remove uma chave e passa o jogador para a outra sala
                                    this.player.getItens().getChaves().remove(this.player.getItens().getChaves().size() - 1);
                                    this.player.setLocalizacao(s);
                                    //movimentar trolls
                                    for (Troll troll : this.trolls) {
                                        troll.movimentaTroll(this.salas);
                                        //verifica se a saida do troll Ã© a sala do cara
                                        if (troll.getLocalizacao() == this.player.getLocalizacao()) {
                                            //verificar se tem machado solto na sala
                                            for (Machado machado : this.machados) {
                                                if (!machado.isPegado() && !machado.isUtilizado() && machado.getLocalizacao() == this.player.getLocalizacao()) {
                                                    //tem machado troll vai atacar
                                                    System.out.println("Troll de nome " + troll.getNome() + " atacou vocÃª com um machado");
                                                    boolean temPocao = false;
                                                    for (Pocao pocao : this.player.getItens().getPocoes()) {
                                                        if (pocao.isPegado() && !pocao.isUtilizado()) {
                                                            pocao.setUtilizado(true);
                                                            temPocao = true;
                                                            break;
                                                        }
                                                    }
                                                    if (temPocao) {
                                                        System.out.println("VocÃª usou uma poÃ§Ã£o para sobreviver!");
                                                    } else {
                                                        System.out.println("VocÃª nÃ£o possui poÃ§Ãµes.");
                                                        System.out.println("VocÃª morreu!");
                                                        return false;
                                                    }

                                                }
                                            }

                                        }
                                    }
                                } else {
                                    System.out.println("A porta estÃ¡ fechada e vocÃª nÃ£o possui chaves para usar");
                                }
                            }

                        } else {
                            System.out.println("Jogador estÃ¡ longe das portas");
                        }
                    }
                    this.player.setPortaPerto(null);
                } else {
                    System.out.println("Jogador estÃ¡ longe das portas");
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
