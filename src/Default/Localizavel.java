package Default;

import java.util.Random;

abstract class Localizavel {
	private Sala localizacao;
	
	Random random = new Random();
	
	public Sala getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Sala localizacao) {
		this.localizacao = localizacao;
	}
	
	
	
}
