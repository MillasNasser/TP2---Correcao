package Default;

import Exceptions.AproximavelException;
import Exceptions.ItemException;
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
    
    public Mapa(String arquivo){
        this();
        
        //Lendo o arquivo e configurando as salas.
        FileReader fr = null;
        BufferedReader br = null;
        
        String jsonString = "";
        
        try{
            fr = new FileReader(arquivo);
            br = new BufferedReader(fr);
            
            String linha;
            
            br = new BufferedReader(new FileReader(arquivo));
            
            while((linha = br.readLine()) != null){
                jsonString += linha;
            }
        }catch(Exception e){
            
        }
        
        JsonElement jsonElement = new JsonParser().parse(jsonString);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        
        //jsonObject = jsonObject.getAsJsonObject("salas");
        JsonArray jsonSalas = jsonObject.getAsJsonArray("salas");
        for(int i=0; i<jsonSalas.size(); i++){
            JsonObject jsonSala = jsonSalas.get(i).getAsJsonObject();
            
            String nome = jsonSala.get("nome").getAsString();
            int tamanho = jsonSala.get("tamanho").getAsInt();
            Sala sala = new Sala(nome, tamanho);
            
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

    public void inicializaTrolls() {
        int quantidade_trolls = 15;//quantidade de trolls que vou inicializar
        Random random = new Random();
        for (int i = 0; i < quantidade_trolls; i++) {
            //Escolhendo uma sala aleatória que esteja vazia.
            int pos;
            boolean conseguiu = false;
            while (!conseguiu) {
                pos = random.nextInt((this.salas.size() - 2)) + 1;//gera a sala aleatoriamente desde q a sala nao seja a primeira
                //checar se a sala ja foi escolhida anteriormente
                if( !this.salas.get(pos).temTroll() ) {
                    this.salas.get(pos).addTroll(new Troll());
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
    
    public boolean verifcarFim(){
    	boolean zerou = true;
        for(Sala sala : this.salas){
            try{
                sala.getItem("gold");
                zerou = false;
            }catch(ItemException e){
            }
        }
        return zerou;
    }
    
    public void atacarTroll() throws PersonagemException{
        for (Sala sala : this.salas) {
            if (sala.temTroll()) {
                for(int i=0; i<sala.getTrolls().size(); i++){
                    try {
                        sala.getTrolls().get(i).atacar(sala, this.getPlayer());
                    } catch (PersonagemException ex) {
                        throw ex;
                    }
                }
            }
        }
    }
    
    public void moverTroll() {
        for (Sala sala : this.salas) {
            if (sala.temTroll()) {
                for(int i=0; i<sala.getTrolls().size(); i++){
                    sala.getTrolls().get(i).mover(sala);
                }
            }
        }
    }
}
