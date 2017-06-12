package Default;
//tudo que é pegavel é localizavel
public abstract class Pegavel extends Localizavel{
	private boolean pegado = false;
	private int peso = 0;
	
	public boolean isPegado() {
		return pegado;
	}
	public void setPegado(boolean pegado) {
		this.pegado = pegado;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	 
}
