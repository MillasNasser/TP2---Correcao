package Default;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author renan
 *
 */
public class Sala {
	private String nome;
	private ArrayList<Porta> portas = new ArrayList<Porta>();

	
	
	Sala(){}
	Sala(String nome){
		this.nome = nome;
	}
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<Porta> getPortas() {
		return portas;
	}
	
	public void setPortas(ArrayList<Porta> portas) {
		this.portas = portas;
	}
	
	public static Sala getSalabyNome(ArrayList<Sala> salas,String nome){		
		for(Sala s : salas){
			if(s.getNome().equals(nome)){
				return s;
			}
		}
		return null;
	}
	
	public void imprimeInfoSala(){
		System.out.println("Sala atual: "+this.getNome());
		System.out.println("PORTAS");
		for(Porta porta : this.getPortas()){			
			System.out.println("Porta "+porta.getIndentificador()+((porta.getAberta())?" aberta":" fechada"));
		}
	}
	
	public ArrayList<Sala> inicializaSalas(){
		ArrayList<Sala> salas = new ArrayList<Sala>();
		salas.add(new Sala("01"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "06"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "02"));
		salas.add(new Sala("02"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "01"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "03"));
		salas.add(new Sala("03"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "02"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "08"));
		salas.add(new Sala("04"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "00"));
		//setar a saida nesta porta
		salas.get((salas.size()-1)).getPortas().get((salas.get((salas.size()-1)).getPortas().size()-1)).setSaida(true);
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "05"));
		salas.add(new Sala("05"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "10"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "04"));
		salas.add(new Sala("06"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "01"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "07"));
		salas.add(new Sala("07"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "12"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "06"));
		salas.add(new Sala("08"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "13"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "03"));
		salas.add(new Sala("09"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "10"));		
		salas.add(new Sala("10"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "09"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "15"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("C", "05"));
		salas.add(new Sala("11"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "12"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "16"));
		salas.add(new Sala("12"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "11"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "07"));
		salas.add(new Sala("13"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "12"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "08"));
		salas.add(new Sala("14"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "15"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "19"));
		salas.add(new Sala("15"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "14"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "10"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "20"));
		salas.add(new Sala("16"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "11"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "17"));
		salas.add(new Sala("17"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "16"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "17"));
		salas.add(new Sala("18"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "19"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "17"));
		salas.add(new Sala("19"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "14"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("B", "18"));
		salas.add(new Sala("20"));
		salas.get((salas.size()-1)).getPortas().add(new Porta("A", "15"));		
		
		return salas;
	}
	
	

}
