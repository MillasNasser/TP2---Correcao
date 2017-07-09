package GUI.componentes;

import Default.Util;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class Box extends Container {
    static public int HORIZONTAL = 0;
    static public int VERTICAL = 1;
    
    private int orientacao;
    private int espacamento = 1;
    
    public Box(int orientacao){
        super();
        
        this.setSize(this.getMaximumSize());
        this.setSize(0, 0);
        this.orientacao = orientacao;
    }
    
    public Box(int orientacao, int espacamento){
        this(orientacao);
        
        this.setEspacamento(espacamento);
    }

    public int getOrientacao() {
        return orientacao;
    }

    final public void setOrientacao(int orientacao) throws IllegalArgumentException {
        if(orientacao < 0 || orientacao > 1){
            throw new IllegalArgumentException("Orientação inválida.");
        }
        this.orientacao = orientacao;
    }
    
    public int getEspacamento() {
        return espacamento;
    }

    final public void setEspacamento(int espacamento) throws IllegalArgumentException {
        if(this.espacamento < 0){
            throw new IllegalArgumentException("Espaçamento negativo.");
        }
        this.espacamento = espacamento;
    }
    
    public void updateSize(){
        Dimension tamanho = new Dimension(0, 0);
        
        for(int i = 0; i < this.getComponentCount(); i++){
            if(this.getComponent(i) instanceof Box){
                ((Box)this.getComponent(i)).updateSize();
            }
            Point alcance = Util.getAlcance(this.getComponent(i));
            
            if(alcance.x > tamanho.width){
                tamanho.width = alcance.x;
            }
            if(alcance.y > tamanho.height){
                tamanho.height = alcance.y;
            }
        }
        
        this.setSize(tamanho);
    }
    
    @Override
    final public Component add(Component comp){
        Point posicao = comp.getLocation();
        posicao.x += this.getX();
        posicao.y += this.getY();
        
        int ultimoIndice = this.getComponentCount() - 1;
        if(ultimoIndice >= 0){
            Rectangle ultimoBounds = this.getComponent(ultimoIndice).getBounds();
            posicao.x = ultimoBounds.x;
            posicao.y = ultimoBounds.y;
            if(this.orientacao == HORIZONTAL){
                posicao.x += ultimoBounds.width + this.espacamento;
            }else{
                posicao.y += ultimoBounds.height + this.espacamento;
            }
        }else{
            this.setSize(Util.getAlcance(comp).x, Util.getAlcance(comp).y);
        }
        
        comp.setLocation(posicao);
        
        super.add(comp);
        
        this.updateSize();
        
        System.out.println("BOX ADD: " + comp.getClass() + "\n  " + this.getBounds());
        
        return comp;
    }
}
