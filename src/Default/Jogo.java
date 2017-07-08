package Default;

import Exceptions.PersonagemException;
import Interface.Principal;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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
	public static void main(String[] args) {
		Mapa mapa = new Mapa("mapa.json");

		mapa.espalhaItens();
		mapa.espalhaTrolls();
		mapa.inicializaSalas();
		/**/
		mapa.getPlayer().setSalaAtual(mapa.getSalas().get(0));
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Principal gui = new Principal(mapa);
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(gui);
				frame.pack();
				frame.setVisible(true);
			}
		});
		
		while (true) {
			try {
				//agora ja tenho o jogo pronto para jogar
				Console.console(mapa);
			} catch (PersonagemException ex) {

			}

			if (mapa.getPlayer().getSalaAtual().getNome().equals("Saida")) {
				mapa.verifcarFim();
			}
		}
		/**/
	}

}
