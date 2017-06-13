package Default;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author renan
 *
 */
public class Jogo {
    
    private Mapa mapa;

    private boolean jogando = false;

    public boolean isJogando() {
        return jogando;
    }

    public void setJogando(boolean jogando) {
        this.jogando = jogando;
    }

    /**
     * Quando o jogo inicializar, começar criando o cenário com os objetos
     */
    public Jogo() {
        this.mapa = new Mapa();
    }

    public boolean console() {
        boolean retorno = false;
        this.setJogando(true);
        while (this.isJogando()) {
            System.out.print("Player> ");
            Scanner scanner = new Scanner(System.in);
            String comando = scanner.nextLine().replaceAll("\n", "");
            //split do comando pelo espaço
            String[] comandoSplited = comando.split(" ");
            switch (comandoSplited[0]) {
                case "view":
                    //obtem descricao da sala e seu conteudo
                    //nome da sala e portas				
                    this.player.getLocalizacao().imprimeInfoSala();
                    System.out.println("ITENS NA SALA");
                    int quantidade = 0;
                    quantidade += Chave.imprimeChavesNaSala(this.chaves, this.player.getLocalizacao());
                    quantidade += Ouro.imprimeOuroNaSala(this.ouros, this.player.getLocalizacao());
                    quantidade += Machado.imprimeMachadoNaSala(this.machados, this.player.getLocalizacao());
                    if (quantidade == 0) {
                        System.out.println("Nenhum item encontrado");
                    }
                    System.out.println("TROLLS NA SALA");
                    if (Troll.imprimeTrollsNaSala(trolls, this.player.getLocalizacao()) == 0) {
                        System.out.println("Nenhum troll encontrado");
                    }

                    break;
                case "bagpack":
                    this.player.getItens().imprimeItens();
                    break;
                case "pickUp":
                    //pegar objetos para a mochila
                    if (comandoSplited.length == 2) {
                        switch (comandoSplited[1]) {
                            case "ouro":
                            case "gold":
                                if (this.player.getItemPerto() != null && (this.player.getItemPerto().equals("gold") || this.player.getItemPerto().equals("ouro"))) {
                                    this.player.getItens().adicionaOuro(Ouro.getOuroDisponivelSala(this.ouros, this.player.getLocalizacao()));
                                    this.ouros = Ouro.setPegadoOuroSala(this.ouros, this.player.getLocalizacao());
                                } else {
                                    System.out.println("Não há ouro perto de você");
                                }
                                break;
                            case "chave":
                            case "key":
                                if (this.player.getItemPerto() != null && (this.player.getItemPerto().equals("key") || this.player.getItemPerto().equals("chave"))) {
                                    if (this.player.getItens().contaPeso() < this.player.getItens().getCapacidade()) {
                                        this.player.getItens().adicionaChave(Chave.getChaveDisponivelSala(this.chaves, this.player.getLocalizacao()));
                                        this.chaves = Chave.setPegadoChaveSala(this.chaves, this.player.getLocalizacao());
                                    } else {
                                        System.out.println("Mochila está cheia.");
                                    }
                                } else {
                                    System.out.println("Não há chave perto de você");
                                }
                                break;
                            case "machado":
                            case "axe":
                                if (this.player.getItemPerto() != null && (this.player.getItemPerto().equals("axe") || this.player.getItemPerto().equals("machado"))) {
                                    if (this.player.getItens().contaPeso() < this.player.getItens().getCapacidade()) {
                                        this.player.getItens().adicionaMachado(Machado.getMachadoDisponivelSala(this.machados, this.player.getLocalizacao()));
                                        this.machados = Machado.setPegadoMachadoSala(this.machados, this.player.getLocalizacao());
                                    } else {
                                        System.out.println("Mochila está cheia.");
                                    }
                                } else {
                                    System.out.println("Não há machado perto de você");
                                }
                                break;
                            case "poção":
                            case "pocao":
                            case "potion":
                                if (this.player.getItemPerto() != null && (this.player.getItemPerto().equals("potion") || this.player.getItemPerto().equals("pocao") || this.player.getItemPerto().equals("poção"))) {
                                    if (this.player.getItens().contaPeso() < this.player.getItens().getCapacidade()) {
                                        this.player.getItens().adicionaPocao(Pocao.getPocaoDisponivelSala(this.pocoes, this.player.getLocalizacao()));
                                        this.pocoes = Pocao.setPegadoPocaoSala(this.pocoes, this.player.getLocalizacao());
                                    } else {
                                        System.out.println("Mochila está cheia.");
                                    }
                                } else {
                                    System.out.println("Não há poção perto de você");
                                }
                                break;
                        }
                    }
                    break;
                case "drop":
                    //soltar objeto da mochila
                    if (comandoSplited.length == 2) {
                        switch (comandoSplited[1]) {
                            case "ouro":
                            case "gold":

                                //solta o ouro na sala
                                Ouro ouro = new Ouro();
                                ouro.setLocalizacao(this.player.getLocalizacao());
                                ouro.setPegado(true);
                                ouro.setQuantidade(this.player.getItens().getOuros());
                                this.ouros.add(ouro);
                                this.player.getItens().setOuros(0);

                                break;
                            case "chave":
                            case "key":
                                for (Chave chave : this.chaves) {
                                    if (chave.isPegado() && !chave.isUsada()) {
                                        chave.soltaChave(this.player.getLocalizacao());
                                        break;
                                    }
                                }
                                break;
                            case "machado":
                            case "axe":
                                for (Machado machado : this.machados) {
                                    if (machado.isPegado() && !machado.isUtilizado()) {
                                        machado.soltaMachado(this.player.getLocalizacao());
                                        break;
                                    }
                                }
                                break;
                            case "poção":
                            case "pocao":
                            case "potion":
                                for (Pocao pocao : this.pocoes) {
                                    if (pocao.isPegado() && !pocao.isUtilizado()) {
                                        pocao.soltaPocao(this.player.getLocalizacao());
                                        break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case "moveTo":
                    //andar com o player
                    if (comandoSplited.length == 3) {
                        //vai colocar o player perto da porta					
                        if (comandoSplited[2].equals("door")) {
                            this.player.setItemPerto(null);
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

                case "throwAxe":
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
                case "Exit":
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
        }
        return retorno;

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        boolean jogavel = true;
        while (jogavel) {
            Jogo jogo = new Jogo();
            //agora ja tenho o jogo pronto para jogar
            jogavel = jogo.console();

        }

    }

}
