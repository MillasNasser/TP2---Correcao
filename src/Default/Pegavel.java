package Default;

//tudo que é pegavel é localizavel

import Exceptions.ItemException;
import Exceptions.PersonagemException;

public abstract class Pegavel extends Aproximavel{
    
    public void usar() throws Exception{
        throw new Exception("Não passou argumentos!");
    }
}
