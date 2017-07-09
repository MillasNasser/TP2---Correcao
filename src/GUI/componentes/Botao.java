package GUI.componentes;

import javax.swing.JButton;

public class Botao extends JButton{

    public Botao(String text) {
        super(text);
        
        this.setSize(this.getPreferredSize());
    }
    
}
