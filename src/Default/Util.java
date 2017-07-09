package Default;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;

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
        Rectangle bounds = comp.getBounds();
        
        Point alcance = new Point();
        
        alcance.x = bounds.x + bounds.width;
        alcance.y = bounds.y + bounds.height;
        
        return alcance;
    }
}
