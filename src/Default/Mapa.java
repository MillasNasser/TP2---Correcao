package Default;

import java.util.ArrayList;
import java.util.List;

public class Mapa {

    private List<Sala> salas;
    private List<Troll> trolls;
    private Jogador player;

    public Mapa() {
        salas = new ArrayList<>();
        trolls = new ArrayList<>();
        player = new Jogador();
        
        inicializaSalas();
        inicializaTrolls();
    }

    private void inicializaSalas() {
        salas.add(new Sala("01"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "06"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "02"));
        salas.add(new Sala("02"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "01"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "03"));
        salas.add(new Sala("03"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "02"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "08"));
        salas.add(new Sala("04"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "00"));
        //setar a saida nesta porta
        salas.get((salas.size() - 1)).getPortas().get((salas.get((salas.size() - 1)).getPortas().size() - 1)).setSaida(true);
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "05"));
        salas.add(new Sala("05"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "10"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "04"));
        salas.add(new Sala("06"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "01"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "07"));
        salas.add(new Sala("07"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "12"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "06"));
        salas.add(new Sala("08"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "13"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "03"));
        salas.add(new Sala("09"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "10"));
        salas.add(new Sala("10"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "09"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "15"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("C", "05"));
        salas.add(new Sala("11"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "12"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "16"));
        salas.add(new Sala("12"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "11"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "07"));
        salas.add(new Sala("13"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "12"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "08"));
        salas.add(new Sala("14"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "15"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "19"));
        salas.add(new Sala("15"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "14"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "10"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "20"));
        salas.add(new Sala("16"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "11"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "17"));
        salas.add(new Sala("17"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "16"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "17"));
        salas.add(new Sala("18"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "19"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "17"));
        salas.add(new Sala("19"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "14"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("B", "18"));
        salas.add(new Sala("20"));
        salas.get((salas.size() - 1)).getPortas().add(new Porta("A", "15"));
    }

    public void inicializaTrolls() {
        int quantidade_trolls = 5;//quantidade de trolls que vou inicializar
        ArrayList<Integer> salasJaEscolhidas = new ArrayList<Integer>();
        TrollNome trollNome = new TrollNome();
        for (int i = 0; i < quantidade_trolls; i++) {
            Troll troll = new Troll();
            boolean conseguiu = false;
            Integer pos = 0;
            while (!conseguiu) {
                pos = new Integer(random.nextInt((salas.size() - 2)) + 1);//gera a sala aleatoriamente desde q a sala nao seja a primeira
                //checar se a sala ja foi escolhida anteriormente
                if (!salasJaEscolhidas.contains(pos)) {
                    conseguiu = true;
                    break;
                }

            }
            salasJaEscolhidas.add(new Integer(pos));
            troll.setLocalizacao(salas.get(pos));
            troll.setNome(trollNome.gerarNome());
            trolls.add(troll);
        }
    }
}
