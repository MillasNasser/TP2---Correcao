package Default;
import java.util.ArrayList;

public class Machado extends Pegavel{
	private boolean utilizado = false;

	public boolean isUtilizado() {
		return utilizado;
	}

	public void setUtilizado(boolean utilizado) {
		this.utilizado = utilizado;
	}
	
	public Machado() {
		this.setPeso(1);
	}
	
	public void soltaMachado(Sala sala){
		if(!this.isUtilizado()){
			this.setLocalizacao(sala);
			this.setPegado(false);
		}				
	}
	
	public static ArrayList<Machado> setPegadoMachadoSala(ArrayList<Machado> machados,Sala sala){
		for(int i=0;i<machados.size();i++){
			if(machados.get(i).getLocalizacao().getNome() == sala.getNome()){
				machados.get(i).setPegado(true);
			}
		}
		return machados;
	}
	
	public static Machado getMachadoDisponivelSala(ArrayList<Machado> machados,Sala sala){
		for(Machado machado:machados){
			if(machado.isPegado() == false && machado.getLocalizacao().getNome() == sala.getNome()){								
				return machado;				
			}
		}
		return null;
	}
	
	public static int imprimeMachadoNaSala(ArrayList<Machado> machados,Sala sala){
		int quantidade = 0;
		for(Machado machado:machados){
			if(machado.isPegado() == false && machado.isUtilizado() == false && machado.getLocalizacao().getNome() == sala.getNome()){
				System.out.println("machado");
				quantidade++;
			}
		}		  
		return quantidade;
	}
	
	public ArrayList<Machado> inicializaMachados(ArrayList<Sala> salas){
		ArrayList<Machado> machados = new ArrayList<Machado>();
		int quantidade_salas = 5;//quantidade de salas que terá poção
		ArrayList<Integer> jaAdicionados = new ArrayList<Integer>();
		for(int i=0;i<quantidade_salas;i++){
			Machado machado = new Machado();			
			boolean encontrou = false;
			Integer pos = new Integer(0);
			while(!encontrou){
				pos = new Integer(random.nextInt(salas.size()-1));
				if(!jaAdicionados.contains(pos)){
					encontrou = true;
					break;
				}
			}				
			machado.setLocalizacao(salas.get(pos.intValue()));							
			machados.add(machado);
		}
		return machados;
	}
	
}
