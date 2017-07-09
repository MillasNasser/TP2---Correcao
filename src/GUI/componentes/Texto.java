package GUI.componentes;

import javax.swing.JLabel;

public class Texto extends JLabel{
    public Texto(String texto){
        super(texto);
        
        this.setSize(this.getPreferredSize());
    }
}
