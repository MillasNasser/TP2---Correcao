/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Exceptions.ItemException;
import Exceptions.PersonagemException;
import java.util.Random;

public class TrollGuerreiro extends Troll{
    
    private Machado machado = null;
    
    public void setMachado(Machado machado){
        this.machado = machado;
    }
    
    public void atacar(Local localAtual, Jogador player) throws PersonagemException{
        if(this.machado == null && localAtual instanceof Sala){
            String[] materiais = {"ouro", "bronze", "ferro"};
            boolean encontrou = true;

            for(String material: materiais){
                try{
                    this.machado = (Machado) ((Sala)localAtual).getItem("machado de " + material);
                    ((Sala)localAtual).removeItem(this.machado);
                    break;
                }catch(ItemException e){
                    //Não tinha machado na sala. Não há nada a fazer.
                }
            }
        }
        if(this.machado != null && localAtual.equals(player.getLocalAtual())){
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
        
        if(porta.getAberta() == true && porta.isEncantada() == false){
            Sala saida = porta.getSala();
            saida.addTrollGuerreiro(this);
            salaAtual.removeTroll(this);
        }
    }
}
