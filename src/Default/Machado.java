package Default;

import Exceptions.ItemException;
import Exceptions.PersonagemException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Machado extends Pegavel {

	protected int durabilidade;
	protected String material;

	public String getMaterial() {
		return material;
	}

	public int getDurabilidade() {
		return durabilidade;
	}

	protected void setDurabilidade(int durabilidade) {
		this.durabilidade = durabilidade;
	}

	public boolean compare(String itemStr) { ///Alterar daqui a pouco
		String[] nomes = {"axe " + this.material, "machado " + this.material,
			this.material + " axe", "machado de " + this.material};
		return Util.compare(nomes, itemStr);
	}

	///Do jogador
	public void usar(Troll troll) throws ItemException {
		try {
			diminuiDurabilidade();
		} catch (ItemException e) {
			throw e;
		}
	}

	///Do troll
	public void usar(Jogador player) throws PersonagemException {
		while (durabilidade != 0) {
			try {
				this.diminuiDurabilidade();
			} catch (ItemException ie) {
			} finally {
				Pegavel pocao;
				try {
					pocao = player.getItem("pocao");
					player.getItens().removeItem(pocao);
				} catch (ItemException ex) {
					player.zerarOuro();
					player.zerarDiamante();
				}
			}
		}
	}

	public void diminuiDurabilidade() throws ItemException {
		this.durabilidade--;
		if (this.durabilidade <= 0) {
			throw new ItemException("Quebrou o machado");
		}
	}

	@Override
	public String toString() {
		return "machado de " + this.material;
	}
}
