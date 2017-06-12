package Default;


import java.util.ArrayList;

public class Ouro extends Pegavel{
	private int quantidade;

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	

	
	public static ArrayList<Ouro> setPegadoOuroSala(ArrayList<Ouro> ouros,Sala sala){
		for(int i=0;i<ouros.size();i++){
			if(ouros.get(i).getLocalizacao().getNome() == sala.getNome()){
				ouros.get(i).setPegado(true);
			}
		}
		return ouros;
	}
	
	public static Ouro getOuroDisponivelSala(ArrayList<Ouro> ouros,Sala sala){
		for(Ouro ouro:ouros){
			if(ouro.isPegado() == false && ouro.getLocalizacao().getNome() == sala.getNome()){				
				
				return ouro;
				
			}
		}
		return null;
	}
	
	public static int imprimeOuroNaSala(ArrayList<Ouro> ouros,Sala sala){
		int quantidade = 0;
		for(Ouro ouro:ouros){
			if(ouro.isPegado() == false && ouro.getLocalizacao().getNome() == sala.getNome()){
				System.out.println(ouro.getQuantidade()+" de ouro");
				quantidade++;
			}
		}		  
		return quantidade;
	}
	
	
	public ArrayList<Ouro> inicializaOuros(ArrayList<Sala> salas){
		ArrayList<Ouro> ouros = new ArrayList<Ouro>();		
		int quantidade_salas = 5;//quantidade de salas que terá ouro
		ArrayList<Integer> jaAdicionados = new ArrayList<Integer>();
		for(int i=0;i<quantidade_salas;i++){
			Ouro ouro = new Ouro();
			boolean encontrou = false;
			Integer pos = new Integer(0);
			while(!encontrou){
				pos = new Integer(random.nextInt(salas.size()-1));
				if(!jaAdicionados.contains(pos)){
					encontrou = true;
					break;
				}
			}				
			ouro.setLocalizacao(salas.get(pos.intValue()));			
			ouro.setQuantidade(random.nextInt(100));
			ouros.add(ouro);
		}
		return ouros;
	}
	
}
