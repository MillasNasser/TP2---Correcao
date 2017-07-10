package Default;

import Exceptions.AproximavelException;
import Exceptions.ItemException;

/**
 * @author renan
 *
 */
public class Jogador {

	private Mochila itens;
	private Local localAtual;
	private Aproximavel perto;

	public Jogador() {
		itens = new Mochila();
	}

	public Aproximavel getPerto() {
		return this.perto;
	}

	public void setPerto(Aproximavel perto) {
		this.perto = perto;
	}

	public Mochila getItens() {
		return itens;
	}

	public void setItens(Mochila itens) {
		this.itens = itens;
	}

	public Local getLocalAtual() {
		return localAtual;
	}

	public void setLocalAtual(Sala localAtual) {
		this.localAtual = localAtual;
	}

	public void zerarOuro() {
		this.itens.getOuro().setQuantidade(0);
	}
	
	public void zerarDiamante() {
		this.itens.getDiamante().setQuantidade(0);
	}

	public void pegar(String itemStr) throws ItemException {
        if (this.perto instanceof Pegavel) {
            if (this.perto.compare(itemStr)) {
                try {
                    this.itens.addItem((Pegavel) this.perto);
                    ((Sala)this.localAtual).removeItem((Pegavel) this.perto);
                    this.perto = null;
                    return;
                } catch (ItemException e) {
                    throw e;
                }
            }
            throw new ItemException("Você não está perto de " + itemStr + ".");
        }
        throw new ItemException("Você não está perto de nenhum item.");
	}

	public void largar(String itemStr) throws ItemException {
		this.itens.removeItem(itemStr);
	}

	public void mover(Aproximavel aproximavel) throws AproximavelException {
        if (aproximavel instanceof Ouro || aproximavel instanceof Diamante) {
            if (((Sala)this.localAtual).temTrollCaverna()) {
                throw new ItemException("Há trolls da caverna na sala.");
            }
        }
        this.setPerto(aproximavel);
	}

	public void throwAxe(Troll troll) throws ItemException {
		String[] materiais = {"ouro", "bronze", "ferro"};

		for (String material : materiais) {
			try {
				Pegavel item = this.getItem("machado de " + material);
				try {
					((Machado) item).usar(troll);
				} catch (ItemException me) {
					//Machado acabou a duração.
					this.itens.removeItem(item);
					return;
					//throw me;
					
				}finally{
					this.localAtual.removeTroll(troll);
					return;
				}
			} catch (ItemException ex) {}
		}
		throw new ItemException("Jogador não tem machado.");
	}

	public void sair() throws AproximavelException {
		if (this.perto instanceof Porta) {
			Porta porta = (Porta) this.perto;
			if (porta.getAberta() == false) {
				try {
					Pegavel chave = this.itens.getItem("key");
					this.itens.removeItem(chave);
					porta.setAberta(true);
				} catch (ItemException ie) {
					throw ie;
				}
			}
			this.localAtual = porta.getFora(this.localAtual);
			this.perto = null;
		} else {
			throw new AproximavelException("Jogador está longe das portas.");
		}
	}

	public void encantar() throws AproximavelException {
		if (this.perto instanceof Porta) {
			Porta porta = (Porta) this.perto;
			if (porta.isEncantada() == false) {
				try {
					Pegavel potion = this.itens.getItem("potion");
					this.itens.removeItem(potion);
					porta.setEncantada(true);
				} catch (ItemException ie) {
					throw ie;
				}
			}
			this.perto = null;
		} else {
			throw new AproximavelException("Jogador está longe das portas.");
		}
	}
	
	public void desencantar(){
		if (this.perto instanceof Porta) {
			Porta porta = (Porta) this.perto;
			if (porta.isEncantada() == true) {
				porta.setEncantada(false);
			}
		}
	}

	public Pegavel getItem(String strItem) throws ItemException {
		try {
			return this.itens.getItem(strItem);
		} catch (ItemException e) {
			throw e;
		}
	}
}
