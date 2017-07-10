package Default;

import Exceptions.ItemException;
import Exceptions.LocalException;
import Exceptions.PersonagemException;
import Interface.Principal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.type.TypeVariable;

public class Mapa {

	private Jogador player;
	private List<Sala> salas;
	private List<Corredor> corredores;

	public Mapa() {
		this.salas = new ArrayList<>();
		this.corredores = new ArrayList<>();
		this.player = new Jogador();
	}

	public Mapa(String arquivo) throws Exception {
		this();

		//Lendo o arquivo e configurando as salas.
		BufferedReader br = new BufferedReader(new FileReader(arquivo));

		JsonElement jsonElement = new JsonParser().parse(br);
		JsonObject jsonObject = jsonElement.getAsJsonObject();

		//Salas
		JsonArray jsonSalas = jsonObject.getAsJsonArray("salas");
		for (int i = 0; i < jsonSalas.size(); i++) {
			JsonObject jsonSala = jsonSalas.get(i).getAsJsonObject();

			String nome = jsonSala.get("nome").getAsString();
			int tamanho = jsonSala.get("tamanho").getAsInt();
			int chaves = 0;
			try {
				chaves = jsonSala.get("chaves").getAsInt();
			} catch (Exception e) {
			}

			Sala sala = new Sala(nome, tamanho);
			for (int j = 0; j < chaves; j++) {
				sala.addItem(new Chave());
			}
			this.addSala(sala);
		}

		getPlayer().setLocalAtual(getSalas().get(0));

		//Corredores.
		JsonArray jsonCorredores = jsonObject.getAsJsonArray("corredores");
		System.out.printf("corredores: %d\n", jsonCorredores.size());
		for (int i = 0; i < jsonCorredores.size(); i++) {
			JsonObject jsonCorredor = jsonCorredores.get(i).getAsJsonObject();
			Corredor corredor = new Corredor(String.format("Corredor %d", i));

			JsonArray jsonPortas = jsonCorredor.get("portas").getAsJsonArray();
			for (int j = 0; j < jsonPortas.size(); j++) {
				JsonObject jsonPorta = jsonPortas.get(j).getAsJsonObject();

				String identificador = jsonPorta.get("identificador").getAsString();
				boolean aberta, saida;

				try {
					aberta = jsonPorta.get("aberta").getAsBoolean();
				} catch (NullPointerException npe) {
					aberta = true;
				}
				try {
					saida = jsonPorta.get("saida").getAsBoolean();
				} catch (NullPointerException npe) {
					saida = false;
				}

				String salaStr = jsonPorta.get("sala").getAsString();
				Sala sala = this.getSala(salaStr);

				Porta porta = new Porta(identificador, sala, corredor);
				porta.setAberta(aberta);
				porta.setSaida(saida);

				sala.addPorta(porta);
				corredor.addPorta(porta);
			}
			this.corredores.add(corredor);
		}
	}

	public Jogador getPlayer() {
		return player;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public Sala getSala(String nomeSala) throws Exception {
		nomeSala = nomeSala.toLowerCase();
		for (Sala sala : this.salas) {
			if (nomeSala.equals(sala.getNome().toLowerCase())) {
				return sala;
			}
		}
		throw new Exception("Sala " + nomeSala + " não encontrada.");
	}

	public void addSala(Sala novaSala) throws LocalException {
		for (Sala sala : this.salas) {
			if (sala.getMetrosQuadrados() == novaSala.getMetrosQuadrados()) {
				throw new LocalException("Salas não podem ter o mesmo tamanho.");
			}
		}
		this.salas.add(novaSala);
	}

	public void espalhaItem(Pegavel item, int maxItens) throws ItemException {
		Random random = new Random();
		Constructor<Pegavel> con = null;
		try {
			con = (Constructor<Pegavel>) item.getClass().getConstructor();
		} catch (Exception ex) {
			throw new ItemException("Falha ao obter o construtor " + item);
		}

		for (int i = 0; i < maxItens; i++) {
			int sala = random.nextInt(salas.size() - 1);
			try {
				Pegavel copia;
				if (item instanceof Ouro) {
					copia = new Ouro(((Ouro) item).getQuantidade());
				} else if (item instanceof Diamante) {
					copia = new Diamante(((Diamante) item).getQuantidade());
				} else {
					copia = con.newInstance();
				}
				this.salas.get(sala).addItem(copia);
			} catch (ItemException ex) {
				throw ex;
			} catch (Exception e) {
				throw new ItemException("Falha ao criar uma copia de " + item);
			}
		}
	}

	public void espalhaItens() {
		try {
			this.espalhaItem(new MachadoFerro(), this.salas.size());
		} catch (ItemException ex) {
			//A exceção só será lançada em Ouro.
		}
		try {
			this.espalhaItem(new MachadoBronze(), 5);
		} catch (ItemException ex) {
			//A exceção só será lançada em Ouro.
		}
		try {
			this.espalhaItem(new MachadoOuro(), 1);
		} catch (ItemException ex) {
			//A exceção só será lançada em Ouro.
		}
		try {
			this.espalhaItem(new Pocao(), this.salas.size());
		} catch (ItemException ex) {
			//A exceção só será lançada em Ouro.
		}

		Random random = new Random();
		int quantidade = 100;
		while (quantidade > 0) {
			try {
				System.out.println(this.salas.size() + "");
				if (random.nextBoolean()) {
					this.espalhaItem(new Ouro(random.nextInt(quantidade)), this.salas.size());
				} else {
					System.out.println(this.salas.size() + "");
					this.espalhaItem(new Diamante(random.nextInt(quantidade)), this.salas.size());
				}
				System.out.println(this.salas.size() + "");
			} catch (ItemException ex) {
				quantidade -= 10;
			}
		}
	}

	public void espalhaTrolls() {
		int quantidade_trolls = 7;//quantidade de trolls que vou inicializar
		Random random = new Random();
		for (int i = 0; i < quantidade_trolls; i++) {
			//Escolhendo uma sala aleatória que esteja vazia.
			int pos;
			boolean conseguiu = false;
			while (!conseguiu) {
				pos = random.nextInt((this.salas.size() - 2)) + 1;//gera a sala aleatoriamente desde q a sala nao seja a primeira
				//checar se a sala ja foi escolhida anteriormente
				if (!this.salas.get(pos).temTroll()) {
					this.salas.get(pos).addTrollCaverna(new TrollCaverna());
					this.salas.get(pos).addTrollGuerreiro(new TrollGuerreiro());

					conseguiu = true;
				}
			}
		}
	}

	public void verifcarFim() throws ItemException {
		try {
			if (!getPlayer().getLocalAtual().equals(getSala("saida"))) {
				return;
			}
		} catch (Exception ex) {}
		for (Sala sala : this.salas) {
			if (sala.getQuantidadeOuro() != 0 || sala.getQuantidadeDiamante() != 0) {
				throw new ItemException("Ainda há tesouros para serem pegos!!!");
			}
		}
		Principal.infoBox("Você venceu o jogo!!!!!", "Jogo");
		System.exit(0); //Fim de jogo.
	}

	public void atacarTroll() throws PersonagemException, ItemException {
		Local localAtual = this.player.getLocalAtual();
		if (localAtual.temTrollGuerreiro()) {
			for (int i = 0; i < localAtual.getTrollsGuerreiros().size(); i++) {
				try {
					((TrollGuerreiro) localAtual.getTrollsGuerreiros().get(i)).atacar(localAtual, this.getPlayer());
				} catch (PersonagemException ex) {
					throw ex;
				} catch (ItemException e) {
					throw e;
				}
			}
		} else {
			throw new PersonagemException("Não há trolls na sala.");
		}
	}

	public void moverTroll() {
		ArrayList<Local> todosLocais = new ArrayList<>();
		todosLocais.addAll(salas);
		todosLocais.addAll(corredores);
		for (Local lugar : todosLocais) {
			if (lugar.temTrollGuerreiro()) {
				for (int i = 0; i < lugar.getTrollsGuerreiros().size(); i++) {
					((TrollGuerreiro) lugar.getTrollsGuerreiros().get(i)).mover(lugar);
				}
			}
		}
	}
}
