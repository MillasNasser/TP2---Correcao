package Default;

public final class MachadoBronze extends Machado{
    public MachadoBronze(){
	setMaterial("bronze");
	setDurabilidade(2);
    }

    private void setMaterial(String material) {
	this.material = material;
    }
    
    public boolean compare(String itemStr, String material){
        String[] nomes = {"axe", "machado"};
        String[] tipos = {"bronze"};
	if(Util.compare(nomes, itemStr)){
	    return Util.compare(tipos, material);
	}
	return false;
    }
}
