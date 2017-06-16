package Default;

public class Util {
	static public boolean compare(String [] nomes, String itemStr){
        itemStr = itemStr.toLowerCase();
        for(String nome: nomes){
            if(nome.equals(itemStr)){
                return true;
            }
        }
        return false;
    }
}
