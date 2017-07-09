package GUI.componentes;

import Default.Pocao;
import Default.Sala;

public class SalaItensBox extends Box{
    public SalaItensBox(Sala sala){
        super(HORIZONTAL);
        
        //Tesouro, Poções e Machados.
        Box esquerda = new Box(VERTICAL);
        this.add(esquerda);
            //Ouro
            SalaItem itemOuro = new SalaItem("Ouro", sala.getQuantidadeOuro());
            esquerda.add(itemOuro);
            //Poções
            SalaItem itemPocao = new SalaItem("Poção", sala.getQuantidadeItem(Pocao.class));
            esquerda.add(itemPocao);
    }
}
