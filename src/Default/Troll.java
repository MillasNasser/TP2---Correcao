package Default;

import Exceptions.ItemException;
import Exceptions.PersonagemException;
import java.util.Random;

public class Troll {
    private String nome;
    private Machado machado = null;

    public String getNome() {
        return nome;
    }
    
    public void setMachado(Machado machado){
	this.machado = machado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void mover(Sala salaAtual, Jogador player) throws PersonagemException{
        if(this.machado == null){
            try{
                this.machado = (Machado)salaAtual.getItem("axe");
                salaAtual.removeItem(this.machado);
            }catch(ItemException e){
                
            }
        }
        if(salaAtual.equals(player.getSalaAtual())){
            try {
                this.machado.usar(player);
                this.machado = null;
            } catch (PersonagemException ex) {
                throw ex;
            }
        }else{
            Random random = new Random();
            int p = random.nextInt(salaAtual.getPortas().size());
            Porta porta = salaAtual.getPortas().get(p);
            if(porta.getAberta() == true && porta.getEncantada() == false){
                salaAtual.removeTroll(this);
                porta.getSalaSaida().addTroll(this);
            }
        }
    }
}
