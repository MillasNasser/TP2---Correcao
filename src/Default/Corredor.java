package Default;

import Exceptions.AproximavelException;

public class Corredor extends Local{
    final int maxPortas = 4;

    public Corredor(String nome) {
        super(nome);
    }
    
    public void addPorta(Porta porta) throws AproximavelException{
        if(this.getPortas().size() == this.maxPortas){
            throw new AproximavelException("Corredor nao pode ter mais que " + this.maxPortas + " portas.");
        }
        super.addPorta(porta);
    }
}
