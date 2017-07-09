package Default;

public class Util {
	static public boolean compare(String [] nomes, String nomeC){
        nomeC = nomeC.toLowerCase();
        System.out.println("%%%%%COMPARE: " + nomeC);
        for(String nome: nomes){
            System.out.println("    " + nome);
            if(nome.toLowerCase().equals(nomeC)){
                return true;
            }
        }
        return false;
    }
}
