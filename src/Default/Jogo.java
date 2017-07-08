package Default;

import Exceptions.ItemException;
import Exceptions.PersonagemException;
import Interface.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

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
	public static void main(String[] args) throws Exception{
		Mapa mapa = new Mapa("mapa.json");

		mapa.espalhaItens();
		mapa.espalhaTrolls();
		mapa.inicializaSalas();
		/**/
		mapa.getPlayer().setLocalAtual(mapa.getSalas().get(0));
		mapa.getSalas().get(0).addItem(new MachadoOuro());
		
		try {
			mapa.getPlayer().getItens().addItem(new Pocao());
		} catch (ItemException ex) {}
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Principal gui = new Principal(mapa);
				UIManager.LookAndFeelInfo info[] = UIManager.getInstalledLookAndFeels();
				try{
					UIManager.setLookAndFeel(info[3].getClassName());
					SwingUtilities.updateComponentTreeUI(gui);
				}catch(Exception e){}
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(gui);
				frame.pack();
				frame.setVisible(true);
			}
		});
		
		/*while (true) {
			try {
				//agora ja tenho o jogo pronto para jogar
				Console.console(mapa,"");
			} catch (PersonagemException ex) {

			}

			if (mapa.getPlayer().getSalaAtual().getNome().equals("Saida")) {
				mapa.verifcarFim();
			}
		}
		/**/
	}

}
