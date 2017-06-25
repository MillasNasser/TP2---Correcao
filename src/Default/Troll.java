package Default;

import Exceptions.ItemException;
import Exceptions.PersonagemException;
import java.util.Random;

public class Troll {
    private String nome;
    private Machado machado = null;
    
    public Troll(){
        this.nome = TrollNome.gerarNome();
    }

    public String getNome() {
        return nome;
    }
    
    public void setMachado(Machado machado){
	this.machado = machado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void atacar(Sala salaAtual, Jogador player) throws PersonagemException{
        if(this.machado == null){
            String[] materiais = {"ouro", "bronze", "ferro"};
            boolean encontrou = true;

            for(String material: materiais){
                try{
                    this.machado = (Machado)salaAtual.getItem("machado de " + material);
                    salaAtual.removeItem(this.machado);
                    break;
                }catch(ItemException e){
                    //Não tinha machado na sala. Não há nada a fazer.
                }
            }
        }
        if(this.machado != null && salaAtual.equals(player.getSalaAtual())){
            try {
                this.machado.usar(player);
                this.machado = null;
            } catch (PersonagemException ex) {
                throw ex;
            }
        }
    }
    
    public void mover(Sala salaAtual){
        Random random = new Random();
        int p = random.nextInt(salaAtual.getPortas().size());
        Porta porta = salaAtual.getPortas().get(p);
        if(porta.getAberta() == true && porta.getEncantada() == false){
            Sala saida = porta.getSalaSaida();
            saida.addTroll(this);
            salaAtual.removeTroll(this);
        }
    }
}
