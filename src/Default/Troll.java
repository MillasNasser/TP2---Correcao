package Default;
import java.util.ArrayList;

public class Troll extends Localizavel{
	private String nome;
	private boolean vivo = true;
	
	
	
	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public boolean isVivo() {
		return vivo;
	}



	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}


	public static int imprimeTrollsNaSala(ArrayList<Troll> trolls,Sala sala){
		int quantidade = 0;
		for(Troll troll:trolls){
			if(troll.isVivo() && troll.getLocalizacao().getNome().equals(sala.getNome())){
				System.out.println("Troll de nome "+troll.getNome());
				quantidade++;
			}
		}	
		return quantidade;
	}
	
	public ArrayList<Troll> inicializaTroll(ArrayList<Sala> salas){
		ArrayList<Troll> trolls = new ArrayList<Troll>();
		int quantidade_trolls = 5;//quantidade de trolls que vou inicializar
		ArrayList<Integer> salasJaEscolhidas = new ArrayList<Integer>();
		TrollNome trollNome = new TrollNome();
		for(int i=0;i<quantidade_trolls;i++){
			Troll troll = new Troll();
			boolean conseguiu = false;
			Integer pos = 0;
			while(!conseguiu){
				pos = new Integer(random.nextInt((salas.size()-2))+1);//gera a sala aleatoriamente desde q a sala nao seja a primeira
				//checar se a sala ja foi escolhida anteriormente
				if(!salasJaEscolhidas.contains(pos)){
					conseguiu = true;
					break;
				}
				
			}						
			salasJaEscolhidas.add(new Integer(pos));
			troll.setLocalizacao(salas.get(pos));
			troll.setNome(trollNome.gerarNome());
			trolls.add(troll);
		}		
		return trolls;
		
	}
	
	
	public void mataTroll(){
		this.vivo = false;
	}
	
	public static Troll getTrollByNome(ArrayList<Troll> trolls,String nome){
		for(Troll troll:trolls){
			if(troll.getNome() == nome){
				return troll;
			}
		}
		return null;
	}
	
	public void movimentaTroll(ArrayList<Sala> salas){
		boolean movimenta = random.nextBoolean();
		if(movimenta && this.vivo){
			for(Porta porta : this.getLocalizacao().getPortas()){
				if(porta.getAberta() && !porta.isEncantada()){
					//movimenta troll
					this.setLocalizacao(Sala.getSalabyNome(salas, porta.getNomeSalaSaida()));					
				}
			}
		}
	}
	
	
	
}
