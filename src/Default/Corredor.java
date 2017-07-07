package Default;

import Exceptions.AproximavelException;

public class Corredor extends Local{
    final int maxPortas = 4;
    
    public void addPorta(String portaStr, Porta porta) throws AproximavelException{
        if(this.getPortas().size() == this.maxPortas){
            throw new AproximavelException("Corredor nao pode ter mais que " + this.maxPortas + " portas.");
        }
        super.addPorta(portaStr, porta);
    }
}
