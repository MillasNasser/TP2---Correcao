package Default;
import java.util.ArrayList;
import java.util.Random;

public class Porta{
	private String indentificador;
	private String nomeSalaSaida;
	private boolean aberta;
	private boolean encantada = false; //se for encantada troll nao passa
	private boolean saida = false;
	
	Random random = new Random(); 
	Porta(){}
	Porta(String indentificador,String nomeSalaSaida){
		
		this.indentificador = indentificador;
		this.nomeSalaSaida = nomeSalaSaida;
		this.aberta = random.nextBoolean();
	}
	
	public static Porta getPortaByIdentificador(ArrayList<Porta> portas,String identificador){		
		for(Porta porta:portas){			
			if(porta.getIndentificador().equals(identificador)){
				return porta;				
			}
		}
		return null;
	}
	
	

	public boolean isEncantada() {
		return encantada;
	}
	public void setEncantada(boolean encantada) {
		this.encantada = encantada;
	}
	public String getIndentificador() {
		return indentificador;
	}

	public void setIndentificador(String indentificador) {
		this.indentificador = indentificador;
	}

	public String getNomeSalaSaida() {
		return nomeSalaSaida;
	}

	public void setNomeSalaSaida(String nomeSalaSaida) {
		this.nomeSalaSaida = nomeSalaSaida;
	}

	public boolean getAberta() {
		return aberta;
	}

	public void setAberta(boolean aberta) {
		this.aberta = aberta;
	}
	
	public boolean getSaida() {
		return saida;
	}

	public void setSaida(boolean saida) {
		this.saida = saida;
	}
	
		
}
