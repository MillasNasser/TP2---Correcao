package Default;
import java.util.ArrayList;
import java.util.Random;

public class TrollNome {
	private ArrayList<String> nomesPossiveis = new ArrayList<String>();
	public TrollNome() {
		nomesPossiveis.add("Alexandre");
		nomesPossiveis.add("Carolina");
		nomesPossiveis.add("Charles");
		nomesPossiveis.add("Guidoni");
		nomesPossiveis.add("Madeira");
		nomesPossiveis.add("Dárlinton");
		nomesPossiveis.add("Diego");
		nomesPossiveis.add("Edimilson");				
		nomesPossiveis.add("Elder");
		nomesPossiveis.add("Elisa");
		nomesPossiveis.add("Elverton");
		nomesPossiveis.add("Sumika");
		nomesPossiveis.add("Flávio");
		nomesPossiveis.add("Leo");
		nomesPossiveis.add("Marcos");
		nomesPossiveis.add("Matheus");
		nomesPossiveis.add("Michelli");
		nomesPossiveis.add("Milene");
		nomesPossiveis.add("Sachetto");
		nomesPossiveis.add("Sofia");
		nomesPossiveis.add("Vinícius");
	}
	
	public String gerarNome(){
		Random random = new Random();
		int valor = random.nextInt(nomesPossiveis.size());
		String nome = nomesPossiveis.get(valor);
		nomesPossiveis.remove(valor);
		return nome;
	}
	
}
