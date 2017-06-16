package Default;

//tudo que é pegavel é localizavel
public abstract class Pegavel {
    
    abstract public boolean compare(String itemStr);
    
    public void usar() throws ItemException{
        throw new ItemException("Não passou argumentos!");
    }
}
