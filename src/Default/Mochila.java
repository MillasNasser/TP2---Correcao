package Default;
import java.util.ArrayList;

public class Mochila {
	
	private int capacidade = 5;
	
	private ArrayList<Chave> chaves = new ArrayList<Chave>();
	private int ouros = 0;
	private ArrayList<Pocao> pocoes= new ArrayList<Pocao>();
	private ArrayList<Machado> machados= new ArrayList<Machado>();
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	public ArrayList<Chave> getChaves() {
		return chaves;
	}
	public void setChaves(ArrayList<Chave> chaves) {
		this.chaves = chaves;
	}
	public int getOuros() {
		return ouros;
	}
	public void setOuros(int ouros) {
		this.ouros = ouros;
	}
	public ArrayList<Pocao> getPocoes() {
		return pocoes;
	}
	public void setPocoes(ArrayList<Pocao> pocoes) {
		this.pocoes = pocoes;
	}
	public ArrayList<Machado> getMachados() {
		return machados;
	}
	public void setMachados(ArrayList<Machado> machados) {
		this.machados = machados;
	}	
	
	public void imprimeItens(){
		System.out.println(this.ouros+" de ouro");
		
		int quantidade = 0;
		for(Chave chave:this.chaves){
			if(chave.isPegado() && !chave.isUsada()){
				quantidade++;
			}
		}
		if(quantidade>0){
			System.out.println(quantidade+" chave"+((quantidade == 1)?"":"s"));
		}
		
		quantidade = 0;
		for(Machado machado:this.machados){
			if(machado.isPegado() && !machado.isUtilizado()){
				quantidade++;
			}
		}
		if(quantidade>0){
			System.out.println(quantidade+" machado"+((quantidade == 1)?"":"s"));
		}
		
		
		quantidade = 0;
		for(Pocao pocao:this.pocoes){
			if(pocao.isPegado() && !pocao.isUtilizado()){
				quantidade++;
			}
		}
		if(quantidade>0){
			System.out.println(quantidade+((quantidade == 1)?" poção":" poções"));
		}
		
	}
	
	public int contaPeso(){
		int peso = 0;
		for(Chave chave:this.chaves){
			if(chave.isPegado() && !chave.isUsada()){
				peso += chave.getPeso();
			}
		}
		for(Machado machado:this.machados){
			if(machado.isPegado() && !machado.isUtilizado()){
				peso += machado.getPeso();
			}
		}
		for(Pocao pocao:this.pocoes){
			if(pocao.isPegado() && !pocao.isUtilizado()){
				peso += pocao.getPeso();
			}
		}
		return peso;
		
	}
	
	public void adicionaOuro(Ouro ouro){
		if(ouro != null){
			this.setOuros(this.getOuros()+ouro.getQuantidade());
		}else{
			System.out.println("Não há ouro para ser adicionado");
		}
	}
	
	public void adicionaChave(Chave chave){
		if(chave != null){			
			this.getChaves().add(chave);
		}else{
			System.out.println("Não há chave para ser adicionada");
		}
	}
	
	public void adicionaMachado(Machado machado){
		if(machado != null){			
			this.getMachados().add(machado);
		}else{
			System.out.println("Não há machado para ser adicionado");
		}
	}
	
	public void adicionaPocao(Pocao pocao){
		if(pocao != null){			
			this.getPocoes().add(pocao);
		}else{
			System.out.println("Não há poção para ser adicionada");
		}
	}
	
	
}
