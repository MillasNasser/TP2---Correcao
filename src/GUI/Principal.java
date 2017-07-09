package GUI;

import Default.Mapa;
import GUI.componentes.Box;
import GUI.componentes.GuiLocal;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Principal {
    JFrame janela;
    
    public Principal(Mapa mapa){
        janela = new JFrame("Jogo");
        janela.setSize(800, 600);
        janela.setDefaultCloseOperation(janela.EXIT_ON_CLOSE);
        
        UIManager.LookAndFeelInfo info[] = UIManager.getInstalledLookAndFeels();
        try{
            UIManager.setLookAndFeel(info[3].getClassName());
            SwingUtilities.updateComponentTreeUI(janela);
        }catch(Exception e){}
        
        Box infoBox = new Box(Box.HORIZONTAL);
        infoBox.setLocation(10, 10);
        janela.add(infoBox);
        
        GuiLocal local = new GuiLocal(mapa.getPlayer().getLocalAtual());
        infoBox.add(local);
        
        janela.setVisible(true);
    }
}
