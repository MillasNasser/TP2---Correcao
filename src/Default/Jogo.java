package Default;

import Exceptions.ItemException;
import Exceptions.PersonagemException;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author renan
 *
 */
public class Jogo {

    private boolean jogando = false;

    public boolean isJogando() {
        return jogando;
    }

    public void setJogando(boolean jogando) {
        this.jogando = jogando;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        Gson gson = new Gson();
        //System.out.println(gson.toJson(mapa));
        
        //Lendo o arquivo e configurando as salas.
        FileReader fr = null;
        BufferedReader br = null;
        
        try{
            fr = new FileReader("mapa.json");
            br = new BufferedReader(fr);
            
            String linha;
            
            br = new BufferedReader(new FileReader("mapa.json"));
            
        }catch(Exception e){}
        
        Mapa m = new Mapa();
        Sala s = new Sala("Sala 1");
        s.addPorta(new Porta("Porta A", new Sala("Sala Saida")));
        m.getSalas().add(s);
        
        String sss = gson.toJson(m);
        
        System.out.println(sss);
        
        Mapa m2 = gson.fromJson(br, Mapa.class);
        
        System.out.println(m2.getSalas().get(0).getNome());
        
        /*
        Mapa mapa = new Mapa();
        boolean jogavel = true;
        while (jogavel) {
        Jogo jogo = new Jogo();
        try {
        //agora ja tenho o jogo pronto para jogar
        jogavel = Console.console(mapa);
        } catch (PersonagemException ex) {
        Jogo.main(args);
        }
        }
         */
    }

}
