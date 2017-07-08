package Default;

import Exceptions.ItemException;
import Exceptions.LocalException;
import Exceptions.PersonagemException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mapa {

    private Jogador player;
    private List<Sala> salas;
    private List<Corredor> corredores;

    public Mapa(){
        this.salas = new ArrayList<>();
        this.corredores = new ArrayList<>();
        this.player = new Jogador();
    }
    
    public Mapa(String arquivo) throws Exception{
        this();
        
        //Lendo o arquivo e configurando as salas.
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        
        JsonElement jsonElement = new JsonParser().parse(br);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        
        //Salas
        JsonArray jsonSalas = jsonObject.getAsJsonArray("salas");
        for(int i=0; i<jsonSalas.size(); i++){
            JsonObject jsonSala = jsonSalas.get(i).getAsJsonObject();
            
            String nome = jsonSala.get("nome").getAsString();
            int tamanho = jsonSala.get("tamanho").getAsInt();
            int chaves = 0;
            try{
                chaves = jsonSala.get("chaves").getAsInt();
            }catch(Exception e){}
            
            Sala sala = new Sala(nome, tamanho);
            for(int j = 0; j<chaves; j++){
                sala.addItem(new Chave());
            }
            this.addSala(sala);
        }
        
        //Corredores.
        JsonArray jsonCorredores = jsonObject.getAsJsonArray("corredores");
        System.out.printf("corredores: %d\n", jsonCorredores.size());
        for(int i=0; i<jsonCorredores.size(); i++){
            JsonObject jsonCorredor = jsonCorredores.get(i).getAsJsonObject();
            Corredor corredor = new Corredor(String.format("Corredor %d", i));
            
            JsonArray jsonPortas = jsonCorredor.get("portas").getAsJsonArray();
            for(int j=0; j<jsonPortas.size(); j++){
                JsonObject jsonPorta = jsonPortas.get(j).getAsJsonObject();
                
                String identificador = jsonPorta.get("identificador").getAsString();
                boolean aberta, saida;
                
                try{
                    aberta = jsonPorta.get("aberta").getAsBoolean();
                }catch(NullPointerException npe){
                    aberta = true;
                }
                try{
                    saida = jsonPorta.get("saida").getAsBoolean();
                }catch(NullPointerException npe){
                    saida = false;
                }
                
                String salaStr = jsonPorta.get("sala").getAsString();
                Sala sala = this.getSala(salaStr);

                Porta porta = new Porta(identificador, sala, corredor);
                porta.setAberta(aberta);
                porta.setSaida(saida);

                sala.addPorta(porta);
                corredor.addPorta(porta);
            }
        }
    }
    
    public Jogador getPlayer() {
        return player;
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
    
    public void addSala(Sala novaSala) throws LocalException{
        for(Sala sala: this.salas){
            if(sala.getMetrosQuadrados() == novaSala.getMetrosQuadrados()){
                throw new LocalException("Salas não podem ter o mesmo tamanho.");
            }
        }
        this.salas.add(novaSala);
    }

    public void inicializaSalas() {
        for(Sala sala: this.salas){
            sala.addChave();
        }
    }
    
    public void espalhaItem(Pegavel item, int maxItens) throws ItemException{
        Random random = new Random();
        for(int i = 0; i < maxItens; i++){
            int sala = random.nextInt(salas.size() - 1);
            try {
                this.salas.get(sala).addItem(item);
            } catch (ItemException ex) {
                throw ex;
            }
        }
    }
    
    public void espalhaItens(){
        try {
            this.espalhaItem(new MachadoFerro(), this.salas.size());
        } catch (ItemException ex) {
            //A exceção só será lançada em Ouro.
        }
        try {
            this.espalhaItem(new MachadoBronze(), 5);
        } catch (ItemException ex) {
            //A exceção só será lançada em Ouro.
        }
        try {
            this.espalhaItem(new MachadoOuro(), 1);
        } catch (ItemException ex) {
            //A exceção só será lançada em Ouro.
        }
        try {
            this.espalhaItem(new Pocao(), this.salas.size());
        } catch (ItemException ex) {
            //A exceção só será lançada em Ouro.
        }
        
        Random random = new Random();
        int quantidade = 100;
        while(quantidade > 0){
            try {
                this.espalhaItem(new Ouro(random.nextInt(quantidade)), this.salas.size());
            } catch (ItemException ex) {
                quantidade -= 10;
            }
        }
    }

    public void espalhaTrolls() {
        int quantidade_trolls = 7;//quantidade de trolls que vou inicializar
        Random random = new Random();
        for (int i = 0; i < quantidade_trolls; i++) {
            //Escolhendo uma sala aleatória que esteja vazia.
            int pos;
            boolean conseguiu = false;
            while (!conseguiu) {
                pos = random.nextInt((this.salas.size() - 2)) + 1;//gera a sala aleatoriamente desde q a sala nao seja a primeira
                //checar se a sala ja foi escolhida anteriormente
                if( !this.salas.get(pos).temTroll() ) {
                    this.salas.get(pos).addTrollCaverna(new TrollCaverna());
					this.salas.get(pos).addTrollGuerreiro(new TrollGuerreiro());
                    
                    conseguiu = true;
                }
            }
        }
    }
    
    public void verifcarFim(){
        for(Sala sala : this.salas){
            try{
                sala.getItem("gold");
                return;
            }catch(ItemException e){
            }
        }
        System.exit(0); //Fim de jogo.
    }
    
    public void atacarTroll() throws PersonagemException{
        Local localAtual = this.player.getLocalAtual();
        if(localAtual.temTrollGuerreiro()) {
            for(int i=0; i<localAtual.getTrollsGuerreiros().size(); i++){
                try {
                    localAtual.getTrollsGuerreiros().get(i).atacar(localAtual, this.getPlayer());
                } catch (PersonagemException ex) {
                    throw ex;
                }
            }
        }else{
            throw new PersonagemException("Não há trolls na sala.");
        }
    }
    
    public void moverTroll() {
        for (Sala sala : this.salas) {
            if (sala.temTroll()) {
                for(int i=0; i<sala.getTrollsGuerreiros().size(); i++){
                    sala.getTrollsGuerreiros().get(i).mover(sala);
                }
            }
        }
    }
}
