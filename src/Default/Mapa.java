package Default;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mapa {

    private List<Sala> salas;
    private Jogador player;

    public Mapa() {
        salas = new ArrayList<>();
        player = new Jogador();
        
        inicializaSalas();
        espalhaItens();
        inicializaTrolls();
    }

    private void inicializaSalas() {
        //TO-DO: iniciar o arquivo do mapa.
        
        for(Sala sala: this.salas){
            sala.addChave();
        }
    }
    
    public void espalhaItem(Pegavel item){
        int max_itens = 5;
        Random random = new Random();
        for(int i = 0; i < max_itens; i++){
            int sala = random.nextInt(salas.size()-1);
            this.salas.get(sala).addItem(item);
        }
    }
    
    public void espalhaItens(){
        this.espalhaItem(new Machado());
        Random random = new Random();
        this.espalhaItem(new Ouro(random.nextInt(100)));
        this.espalhaItem(new Pocao());
    }

    public void inicializaTrolls() {
        int quantidade_trolls = 5;//quantidade de trolls que vou inicializar
        Random random = new Random();
        for (int i = 0; i < quantidade_trolls; i++) {
            Troll troll = new Troll();
            troll.setNome(TrollNome.gerarNome());
            
            //Escolhendo uma sala aleatória que esteja vazia.
            int pos;
            boolean conseguiu = false;
            while (!conseguiu) {
                pos = random.nextInt((salas.size() - 2)) + 1;//gera a sala aleatoriamente desde q a sala nao seja a primeira
                //checar se a sala ja foi escolhida anteriormente
                if( !this.salas.get(pos).temTroll() ) {
                    this.salas.get(pos).addTroll(troll);
                    conseguiu = true;
                }

            }
        }
    }

    public List<Sala> getSalas() {
        return salas;
    }
    
    public Jogador getPlayer() {
        return player;
    }
}
