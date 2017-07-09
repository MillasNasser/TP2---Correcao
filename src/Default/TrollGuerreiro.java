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
    
    public void atacar(Local localAtual, Jogador player) throws PersonagemException, ItemException{
		if(machado == null){
			throw new ItemException("Nao ha machado");
		}
        if(this.machado != null && localAtual.equals(player.getLocalAtual())){
            try {
                this.machado.usar(player);
                this.machado = null;
            } catch (PersonagemException ex) {
                throw ex;
            }
        }
		///Talvez lançar uma exceção
    }
    
	public void mover(Local localAtual){
        Random random = new Random();
        int p = random.nextInt(localAtual.getPortas().size());
        Porta porta = localAtual.getPortas().get(p);
        
		if(this.machado == null && localAtual instanceof Sala){
            String[] materiais = {"ouro", "bronze", "ferro"};
            for(String material: materiais){
                try{
                    this.machado = (Machado) ((Sala)localAtual).getItem("machado de " + material);
                    ((Sala)localAtual).removeItem(this.machado);
                    break;
                }catch(ItemException e){
                    //throw e;
                }
            }
        }
		
        if(porta.getAberta() == true && porta.isEncantada() == false){
            Local saida = porta.getFora(localAtual);
            saida.addTrollGuerreiro(this);
            localAtual.removeTroll(this);
        }
    }
}
