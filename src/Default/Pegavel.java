package Default;

//tudo que Ã© pegavel Ã© localizavel
public abstract class Pegavel {
    private boolean pegado = false;

    public boolean isPegado() {
            return pegado;
    }
    public void setPegado(boolean pegado) {
            this.pegado = pegado;
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
