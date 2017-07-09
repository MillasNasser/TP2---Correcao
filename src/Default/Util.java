package Default;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

public class Util {
	static public boolean compare(String [] nomes, String nomeC){
        nomeC = nomeC.toLowerCase();
        for(String nome: nomes){
            if(nome.toLowerCase().equals(nomeC)){
                return true;
            }
        }
        return false;
    }
    
    static public Point getAlcance(Component comp){
        Point posicao = comp.getLocation();
        Dimension tamanho = comp.getSize();
        
        Point alcance = new Point();
        
        alcance.x = posicao.x + tamanho.width;
        alcance.y = posicao.y + tamanho.height;
        
        return alcance;
    }
}
