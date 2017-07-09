package Default;

import Interface.Principal;
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
        
        //((Sala)mapa.getPlayer().getLocalAtual()).addTrollGuerreiro(new TrollGuerreiro());
        //((Sala)mapa.getPlayer().getLocalAtual()).addTrollGuerreiro(new TrollGuerreiro());
        //((Sala)mapa.getPlayer().getLocalAtual()).addTrollGuerreiro(new TrollGuerreiro());
        ((Sala)mapa.getPlayer().getLocalAtual()).addTrollGuerreiro(new TrollGuerreiro());
        ((Sala)mapa.getPlayer().getLocalAtual()).addTrollCaverna(new TrollCaverna());
        //((Sala)mapa.getPlayer().getLocalAtual()).addItem(new MachadoBronze());
        ((Sala)mapa.getPlayer().getLocalAtual()).addItem(new MachadoBronze());
        //((Sala)mapa.getPlayer().getLocalAtual()).addItem(new MachadoOuro());
        //((Sala)mapa.getPlayer().getLocalAtual()).addItem(new MachadoOuro());
        /*((Sala)mapa.getPlayer().getLocalAtual()).addItem(new MachadoFerro());
        ((Sala)mapa.getPlayer().getLocalAtual()).addItem(new MachadoFerro());
		/**/
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				Principal gui = new Principal(mapa, frame);
				UIManager.LookAndFeelInfo info[] = UIManager.getInstalledLookAndFeels();
				try{
					UIManager.setLookAndFeel(info[3].getClassName());
					SwingUtilities.updateComponentTreeUI(gui);
				}catch(Exception e){}
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
