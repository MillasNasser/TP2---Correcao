package Default;

//tudo que é pegavel é localizavel
public abstract class Pegavel {
    private boolean pegado = false;
    private int peso = 0;

    public boolean isPegado() {
            return pegado;
    }
    public void setPegado(boolean pegado) {
            this.pegado = pegado;
    }
    public int getPeso() {
            return peso;
    }
    public void setPeso(int peso) {
            this.peso = peso;
    }
    
    public boolean compare(String [] nomes, String itemStr){
        itemStr = itemStr.toLowerCase();
        for(String nome: nomes){
            if(nome.equals(itemStr)){
                return true;
            }
        }
        return false;
    }
    
    public boolean compare(String itemStr){
        return false;
    }
}
