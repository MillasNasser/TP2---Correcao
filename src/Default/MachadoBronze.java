package Default;

public final class MachadoBronze extends Machado{
    private String material;
    
    public MachadoBronze(){
	setMaterial("bronze");
	setDurabilidade(2);
    }

    public String getMaterial() {
	return material;
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
