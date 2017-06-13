package Default;

import java.util.Scanner;

/**
 *
 * @author paulo
 */
public class Console {

    public static boolean console(Mapa mapa) {
        System.out.print("Player> ");
        Scanner scanner = new Scanner(System.in);
        String comando = scanner.nextLine().replaceAll("\n", "").toLowerCase();
        //split do comando pelo espaço
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
                if (comandoSplited.length == 3) {
                    //vai colocar o player perto da porta					
                    if (comandoSplited[2].equals("door")) {
                        Porta p = Porta.getPortaByIdentificador(this.player.getLocalizacao().getPortas(), comandoSplited[1]);
                        
                        if (p != null) {
                            this.player.setPortaPerto(p);
                        } else {
                            this.player.setPortaPerto(null);
                            System.out.println("Nenhuma porta com o identificador solicitado");
                        }

                    }
                } else {
                    this.player.setPortaPerto(null);
                    this.player.setItemPerto(comandoSplited[1]);
                }
                break;

            case "throwaxe":
                //Ataca um troll
                if (comandoSplited.length == 2) {
                    //verifica se possui machado
                    Troll troll = Troll.getTrollByNome(this.trolls, comandoSplited[1]);
                    if (troll != null) {
                        for (Machado machado : this.player.getItens().getMachados()) {
                            if (machado.isPegado() && !machado.isUtilizado()) {
                                //taca o machado
                                machado.setUtilizado(true);
                                troll.mataTroll();
                                System.out.println("Troll foi morto");
                            }
                        }
                    } else {
                        System.out.println("Troll especificado não foi encontrado");
                    }
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
                            System.out.println("VOCÊ VENCEU!");
                            return false;
                        } else {
                            System.out.println("Você não coletou todo o ouro disponível.");
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
                                    //verifica se a saida do troll é a sala do cara
                                    if (troll.isVivo() && troll.getLocalizacao() == this.player.getLocalizacao()) {
                                        //verificar se tem machado solto na sala
                                        for (Machado machado : this.machados) {
                                            if (!machado.isPegado() && !machado.isUtilizado() && machado.getLocalizacao() == this.player.getLocalizacao()) {
                                                //tem machado troll vai atacar
                                                System.out.println("Troll de nome " + troll.getNome() + " atacou você com um machado");
                                                boolean temPocao = false;
                                                for (Pocao pocao : this.player.getItens().getPocoes()) {
                                                    if (pocao.isPegado() && !pocao.isUtilizado()) {
                                                        pocao.setUtilizado(true);
                                                        temPocao = true;
                                                        break;
                                                    }
                                                }
                                                if (temPocao) {
                                                    System.out.println("Você usou uma poção para sobreviver!");
                                                } else {
                                                    System.out.println("Você não possui poções.");
                                                    System.out.println("Você morreu!");
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
                                        //verifica se a saida do troll é a sala do cara
                                        if (troll.getLocalizacao() == this.player.getLocalizacao()) {
                                            //verificar se tem machado solto na sala
                                            for (Machado machado : this.machados) {
                                                if (!machado.isPegado() && !machado.isUtilizado() && machado.getLocalizacao() == this.player.getLocalizacao()) {
                                                    //tem machado troll vai atacar
                                                    System.out.println("Troll de nome " + troll.getNome() + " atacou você com um machado");
                                                    boolean temPocao = false;
                                                    for (Pocao pocao : this.player.getItens().getPocoes()) {
                                                        if (pocao.isPegado() && !pocao.isUtilizado()) {
                                                            pocao.setUtilizado(true);
                                                            temPocao = true;
                                                            break;
                                                        }
                                                    }
                                                    if (temPocao) {
                                                        System.out.println("Você usou uma poção para sobreviver!");
                                                    } else {
                                                        System.out.println("Você não possui poções.");
                                                        System.out.println("Você morreu!");
                                                        return false;
                                                    }

                                                }
                                            }

                                        }
                                    }
                                } else {
                                    System.out.println("A porta está fechada e você não possui chaves para usar");
                                }
                            }

                        } else {
                            System.out.println("Jogador está longe das portas");
                        }
                    }
                    this.player.setPortaPerto(null);
                } else {
                    System.out.println("Jogador está longe das portas");
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
