package Default;

import java.util.ArrayList;

public class Pocao extends Pegavel{
	
	private boolean utilizado = false;
	
	
	public boolean isUtilizado() {
		return utilizado;
	}


	public void setUtilizado(boolean utilizado) {
		this.utilizado = utilizado;
	}


	public Pocao() {
		this.setPeso(1);
	}
	public static ArrayList<Pocao> setPegadoPocaoSala(ArrayList<Pocao> pocoes,Sala sala){
		for(int i=0;i<pocoes.size();i++){
			if(pocoes.get(i).getLocalizacao().getNome() == sala.getNome()){
				pocoes.get(i).setPegado(true);
			}
		}
		return pocoes;
	}
	
	public static Pocao getPocaoDisponivelSala(ArrayList<Pocao> pocoes,Sala sala){
		for(Pocao pocao:pocoes){
			if(pocao.isPegado() == false && pocao.getLocalizacao().getNome() == sala.getNome()){								
				return pocao;				
			}
		}
		return null;
	}
	public void soltaPocao(Sala sala){
		if(!this.isUtilizado()){
			this.setLocalizacao(sala);
			this.setPegado(false);
		}				
	}
	
	
	public ArrayList<Pocao> inicializaPocao(ArrayList<Sala> salas){
		ArrayList<Pocao> pocoes = new ArrayList<Pocao>();
		int quantidade_salas = 5;//quantidade de salas que terá poção
		ArrayList<Integer> jaAdicionados = new ArrayList<Integer>();
		for(int i=0;i<quantidade_salas;i++){
			Pocao pocao = new Pocao();			
			boolean encontrou = false;
			Integer pos = new Integer(0);
			while(!encontrou){
				pos = new Integer(random.nextInt(salas.size()-1));
				if(!jaAdicionados.contains(pos)){
					encontrou = true;
					break;
				}
			}				
			pocao.setLocalizacao(salas.get(pos.intValue()));							
			pocoes.add(pocao);
		}
		return pocoes;
	}
	
	
}
