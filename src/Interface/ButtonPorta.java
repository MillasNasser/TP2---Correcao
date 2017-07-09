/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Default.Local;
import Default.Porta;
import javax.swing.JButton;

/**
 *
 * @author millas
 */
public class ButtonPorta extends JButton{
	private String portaStr;
	
	public ButtonPorta(){
		super();
		this.portaStr = null;
	}
	
	public ButtonPorta(Local localAtual, Porta porta){
		super(porta.getFora(localAtual).getNome());
		this.portaStr = porta.getFora(localAtual).getNome();
	}
	
	public String getLabelPorta(){
		return this.portaStr;
	}
}
