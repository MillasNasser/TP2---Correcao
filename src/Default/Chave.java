package Default;


import java.util.ArrayList;

public class Chave extends Pegavel{
	
	private boolean usada = false;
	
	
	public Chave() {
		this.setPeso(1);
	}
	
	public boolean isUsada() {
		return usada;
	}


	public void setUsada(boolean usada) {
		this.usada = usada;
	}
	
	
	public void soltaChave(Sala sala){
		if(!this.isUsada()){
			this.setLocalizacao(sala);
			this.setPegado(false);
		}
		
		
	}
	
	public static ArrayList<Chave> setPegadoChaveSala(ArrayList<Chave> chaves,Sala sala){
		for(int i=0;i<chaves.size();i++){
			if(chaves.get(i).getLocalizacao().getNome() == sala.getNome()){
				chaves.get(i).setPegado(true);
			}
		}
		return chaves;
	}
	
	public static Chave getChaveDisponivelSala(ArrayList<Chave> chaves,Sala sala){
		for(Chave chave:chaves){
			if(chave.isPegado() == false && chave.getLocalizacao().getNome() == sala.getNome()){				
				
				return chave;
				
			}
		}
		return null;
	}
	

	public static int imprimeChavesNaSala(ArrayList<Chave> chaves,Sala sala){
		int quantidade = 0;
		for(Chave chave:chaves){
			if(chave.isPegado() == false && chave.isUsada() == false && chave.getLocalizacao().getNome() == sala.getNome()){
				System.out.println("chave");
				quantidade++;
			}
		}		
		return quantidade;
	}
	public ArrayList<Chave> inicializaChaves(ArrayList<Sala> salas){
		ArrayList<Chave> chaves = new ArrayList<Chave>();
		//colocar as chaves em suas devidas salas
		for (Sala sala : salas) {
			//solta uma chave para cada porta fechada
			for (Porta porta : sala.getPortas()) {
				if(!porta.getAberta()){
					Chave chave = new Chave();
					chave.setLocalizacao(sala);
					chaves.add(chave);
					break; //limite de 1 chave para ele ter q escolher uma porta para navegar
				}
			}
		}		
		return chaves;
	}
}
