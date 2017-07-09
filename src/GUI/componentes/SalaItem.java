package GUI.componentes;

import Default.Console;
import Default.Pegavel;
import Default.Util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;

public class SalaItem extends Box{
    private Pegavel item;
    private String tipo;
    private int quantidade;
    
    private Texto quantidadeTexto;
    private Botao acaoBotao;
    
    public SalaItem(String tipo, int quantidade, int separacao){
        super(HORIZONTAL);
        
        //this.item = item;
        this.tipo = tipo;
        this.quantidade = quantidade;
        
        //Tipo.
        Texto tipoTexto = new Texto(tipo + ":");
        tipoTexto.setWidth(separacao);
        this.add(tipoTexto);
        
        //Quantidade.
        this.quantidadeTexto = new Texto(String.valueOf(quantidade));
        this.quantidadeTexto.setWidth(30);
        this.add(this.quantidadeTexto);
        
        //Botão
        this.acaoBotao = new Botao("Mover");
        this.add(this.acaoBotao);
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.quantidadeTexto.setText(String.valueOf(quantidade));
    }

    public Texto getQuantidadeTexto() {
        return quantidadeTexto;
    }

    public void setQuantidadeTexto(Texto quantidadeTexto) {
        this.quantidadeTexto = quantidadeTexto;
    }

    public Botao getAcaoBotao() {
        return acaoBotao;
    }

    public void setAcaoBotao(Botao acaoBotao) {
        this.acaoBotao = acaoBotao;
    }
}
