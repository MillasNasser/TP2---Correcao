package GUI.componentes;

import javax.swing.JLabel;

public class Texto extends JLabel{
    public Texto(String texto){
        super(texto);
        
        this.setSize(this.getPreferredSize());
    }
    
    public void setWidth(int width){
        this.setSize(width, this.getHeight());
    }
    
    public void setHeight(int height){
        this.setSize(this.getWidth(), height);
    }
}
