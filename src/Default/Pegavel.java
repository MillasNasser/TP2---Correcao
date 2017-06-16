package Default;

//tudo que é pegavel é localizavel
public abstract class Pegavel {
    
    public boolean compare(String [] nomes, String itemStr){
        itemStr = itemStr.toLowerCase();
        for(String nome: nomes){
            if(nome.equals(itemStr)){
                return true;
            }
        }
        return false;
    }
    
    abstract public boolean compare(String itemStr);
    
    abstract public void usar() throws ItemException;   
}
