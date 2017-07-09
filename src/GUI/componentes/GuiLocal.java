package GUI.componentes;

import Default.Local;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuiLocal extends Box implements ActionListener {
    private Local local;
    
    public GuiLocal(Local local){
        super(Box.VERTICAL);
        
        this.local = local;
        
        //Mostrando o nome e os itens do local.
        Box itensPanel = new Box(VERTICAL);
        itensPanel.setBounds(0, 0, 500, 600);
        this.add(itensPanel);
        
        Texto nomeLabel = new Texto("Local: " + local.getNome());
        System.out.println(String.valueOf(nomeLabel.getLocation()) + nomeLabel.getPreferredSize());
        itensPanel.add(nomeLabel);
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
