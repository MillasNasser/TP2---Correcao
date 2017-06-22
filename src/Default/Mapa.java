package Default;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mapa {

    private Jogador player;
    private List<Sala> salas;

    public Mapa(){
        salas = new ArrayList<>();
        player = new Jogador();
        
        /*inicializaSalas();
        espalhaItens();
        inicializaTrolls();*/
    }
    
    public Mapa(String jsonString){
        this();
        
        JsonElement jsonElement = new JsonParser().parse(jsonString);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        
        //jsonObject = jsonObject.getAsJsonObject("salas");
        JsonArray jsonSalas = jsonObject.getAsJsonArray("salas");
        for(int i=0; i<jsonSalas.size(); i++){
            JsonObject jsonSala = jsonSalas.get(i).getAsJsonObject();
            
            String nome = jsonSala.get("nome").getAsString();
            Sala sala = new Sala(nome);
            
            this.salas.add(sala);
        }
        
        //Conectando as portas.
        for(int i=0; i<jsonSalas.size(); i++){
            JsonArray jsonPortas = jsonSalas.get(i).getAsJsonObject().getAsJsonArray("portas");
            for(int j=0; j<jsonPortas.size(); j++){
                JsonObject jsonPorta = jsonPortas.get(j).getAsJsonObject();
                
                Porta porta = new Porta();
                porta.setIdentificador(jsonPorta.get("identificador").getAsString());
                porta.setAberta(jsonPorta.get("aberta").getAsBoolean());
                porta.setEncantada(jsonPorta.get("encantada").getAsBoolean());
                porta.setSaida(jsonPorta.get("saida").getAsBoolean());
                
                try {
                    String nomeSalaSaida = jsonPorta.get("salaSaida").getAsString();
                    
                    porta.setSalaSaida(this.getSala(nomeSalaSaida));
                    this.salas.get(i).addPorta(porta);
                } catch (Exception ex) {
                    //TO-DO (?): Sala a ser conectada não existe. O que fazer?
                }
            }
        }
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
            int sala = random.nextInt(salas.size());
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
        int quantidade_trolls = Integer.min(this.salas.size() - 1, 5);//quantidade de trolls que vou inicializar
        Random random = new Random();
        for (int i = 0; i < quantidade_trolls; i++) {
            Troll troll = new Troll();
            troll.setNome(TrollNome.gerarNome());
            
            //Escolhendo uma sala aleatória que esteja vazia.
            int pos;
            boolean conseguiu = false;
            while (!conseguiu) {
                pos = random.nextInt((salas.size() - 1)) + 1;//gera a sala aleatoriamente desde q a sala nao seja a primeira
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
    
    public Sala getSala(String nomeSala) throws Exception{
        nomeSala = nomeSala.toLowerCase();
        for(Sala sala: this.salas){
            if(nomeSala.equals(sala.getNome().toLowerCase())){
                return sala;
            }
        }
        throw new Exception("Sala " + nomeSala + " não encontrada.");
    }
    
    public Jogador getPlayer() {
        return player;
    }
}
