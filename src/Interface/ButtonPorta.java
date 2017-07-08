/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import javax.swing.JButton;

/**
 *
 * @author millas
 */
public class ButtonPorta extends JButton{
	private String labelPorta;
	
	public ButtonPorta(String nome){
		super(nome);
		this.labelPorta = nome;
	}
	
	public String getLabelPorta() {
		return labelPorta;
	}

	public void setLabelPorta(String label) {
		this.labelPorta = label;
	}
	
}
