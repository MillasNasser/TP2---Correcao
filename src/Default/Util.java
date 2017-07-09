package Default;

public class Util {
	static public boolean compare(String [] nomes, String nomeC){
        nomeC = nomeC.toLowerCase();
        for(String nome: nomes){
            if(nome.toLowerCase().equals(nomeC)){
                return true;
            }
        }
        return false;
    }
}
