package Default;

//tudo que é pegavel é localizavel
public abstract class Pegavel {
    
    abstract public boolean compare(String itemStr);
    
    abstract public void usar() throws ItemException;   
}
