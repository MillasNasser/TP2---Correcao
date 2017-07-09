package GUI.componentes;

import Default.Local;
import Default.Sala;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiLocal extends Box implements ActionListener {
    private Local local;
    
    public GuiLocal(Local local){
        super(Box.VERTICAL, 10);
        
        this.local = local;
        
        Texto nomeLabel = new Texto("Local: " + local.getNome());
        this.add(nomeLabel);
        
        if(local instanceof Sala){
            //Mostrando os itens da sala.
            SalaItensBox itensBox = new SalaItensBox((Sala)local);
            this.add(itensBox);
        }
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
