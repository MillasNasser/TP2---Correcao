package GUI.componentes;

import Default.Chave;
import Default.Diamante;
import Default.MachadoBronze;
import Default.MachadoFerro;
import Default.MachadoOuro;
import Default.Ouro;
import Default.Pocao;
import Default.Sala;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalaItensBox extends Box{
    public SalaItensBox(Sala sala){
        super(HORIZONTAL);
        
        //Tesouro, Poções e Machados.
        Box esquerda = new Box(VERTICAL);
        this.add(esquerda);
            int separacaoEsquerda = 55;
            //Ouro
            SalaItem itemOuro = new SalaItem("Ouro", sala.getQuantidadeItem(Ouro.class), separacaoEsquerda);
            esquerda.add(itemOuro);
            //Diamante
            SalaItem itemDiamante = new SalaItem("Diamante", sala.getQuantidadeItem(Diamante.class), separacaoEsquerda);
            esquerda.add(itemDiamante);
            //Poções
            SalaItem itemPocao = new SalaItem("Poção", sala.getQuantidadeItem(Pocao.class), separacaoEsquerda);
            esquerda.add(itemPocao);
            //Chaves
            SalaItem itemChave = new SalaItem("Chave", sala.getQuantidadeItem(Chave.class), separacaoEsquerda);
            esquerda.add(itemChave);
        Box direita = new Box(VERTICAL);
        this.add(direita);
            int separacaoDireita = 90;
            //Machado de Ouro
            SalaItem itemMachadoOuro = new SalaItem("Machado Ouro", sala.getQuantidadeItem(MachadoOuro.class), separacaoDireita);
            direita.add(itemMachadoOuro);
            //Machado de Bronze
            SalaItem itemMachadoBronze = new SalaItem("Machado Bronze", sala.getQuantidadeItem(MachadoBronze.class), separacaoDireita);
            direita.add(itemMachadoBronze);
            //Machado de Ouro
            SalaItem itemMachadoFerro = new SalaItem("Machado Ferro", sala.getQuantidadeItem(MachadoFerro.class), separacaoDireita);
            direita.add(itemMachadoFerro);
        
            
        itemOuro.getAcaoBotao().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemOuro.setQuantidade(5234);
            }
        });
    }
}
