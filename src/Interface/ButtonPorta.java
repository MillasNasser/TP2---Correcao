/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Default.Porta;
import javax.swing.JButton;

/**
 *
 * @author millas
 */
public class ButtonPorta extends JButton{
	private Porta porta;
	
	public ButtonPorta(){
		super();
		this.porta = null;
	}
	
	public ButtonPorta(Porta porta){
		super(porta.getIdentificador());
		this.porta = porta;
	}

	public Porta getPorta() {
		return porta;
	}

	public void setPorta(Porta porta) {
		this.porta = porta;
	}
	
	public String getLabelPorta(){
		return this.porta.getIdentificador();
	}
}
