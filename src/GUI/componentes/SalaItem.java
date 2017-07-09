package GUI.componentes;

import Default.Pegavel;
import Default.Util;

public class SalaItem extends Box{
    private Pegavel item;
    private String tipo;
    private int quantidade;
    
    public SalaItem(String tipo, int quantidade){
        super(HORIZONTAL, 5);
        
        //this.item = item;
        this.tipo = tipo;
        this.quantidade = quantidade;
        
        //Tipo.
        Texto tipoTexto = new Texto(tipo + ":");
        this.add(tipoTexto);
        
        //Quantidade.
        Texto quantidadeTexto = new Texto(String.valueOf(quantidade));
        this.add(quantidadeTexto);
        
        //Botão
        Botao acaoBotao = new Botao("Mover");
        this.add(acaoBotao);
    }
}
