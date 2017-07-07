/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Exceptions.AproximavelException;
import Exceptions.ItemException;
import Exceptions.PersonagemException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author millas
 */
public class TrollGuerreiro extends Troll{
    
    private Machado machado = null;
    
    public void setMachado(Machado machado){
        this.machado = machado;
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
        List<String> ids = new ArrayList<>(salaAtual.getPortas().keySet());
        String id = ids.get(random.nextInt(ids.size()));
        Porta porta = null;
        try {
            porta = salaAtual.getPorta(id);
        } catch (AproximavelException ex) {
            
        }
        if(porta.getAberta() == true && porta.getEncantada() == false){
            Sala saida = porta.getSalaSaida();
            saida.addTrollGuerreiro(this);
            salaAtual.removeTroll(this);
        }
    }
}
