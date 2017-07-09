package GUI.componentes;

import Default.Chave;
import Default.Diamante;
import Default.MachadoBronze;
import Default.MachadoFerro;
import Default.MachadoOuro;
import Default.Ouro;
import Default.Pocao;
import Default.Sala;

public class SalaItensBox extends Box{
    public SalaItensBox(Sala sala){
        super(HORIZONTAL);
        
        //Tesouro, Poções e Machados.
        Box esquerda = new Box(VERTICAL);
        this.add(esquerda);
            //Ouro
            SalaItem itemOuro = new SalaItem("Ouro", sala.getQuantidadeItem(Ouro.class));
            esquerda.add(itemOuro);
            //Diamante
            SalaItem itemDiamante = new SalaItem("Diamante", sala.getQuantidadeItem(Diamante.class));
            esquerda.add(itemDiamante);
            //Poções
            SalaItem itemPocao = new SalaItem("Poção", sala.getQuantidadeItem(Pocao.class));
            esquerda.add(itemPocao);
            //Chaves
            SalaItem itemChave = new SalaItem("Chave", sala.getQuantidadeItem(Chave.class));
            esquerda.add(itemChave);
        Box direita = new Box(VERTICAL);
        this.add(direita);
            //Machado de Ouro
            SalaItem itemMachadoOuro = new SalaItem("Machado Ouro", sala.getQuantidadeItem(MachadoOuro.class));
            direita.add(itemMachadoOuro);
            //Machado de Bronze
            SalaItem itemMachadoBronze = new SalaItem("Machado Bronze", sala.getQuantidadeItem(MachadoBronze.class));
            direita.add(itemMachadoBronze);
            //Machado de Ouro
            SalaItem itemMachadoFerro = new SalaItem("Machado Ferro", sala.getQuantidadeItem(MachadoFerro.class));
            direita.add(itemMachadoFerro);
    }
}
