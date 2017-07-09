package Default;

public abstract class Tesouro extends Pegavel{

	public Tesouro(){
		quantidade = 0;
	}
	
	public Tesouro(int quantidade){
		this.quantidade = quantidade;
	}
	
	private int quantidade;
	
	public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}
