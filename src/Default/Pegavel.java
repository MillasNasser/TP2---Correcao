package Default;

//tudo que é pegavel é aproximavel
public abstract class Pegavel extends Aproximavel{
    
    public void usar() throws Exception{
        throw new Exception("Não passou argumentos!");
    }
    
    public abstract String toString();
}
