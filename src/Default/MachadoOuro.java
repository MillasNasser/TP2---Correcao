package Default;

public final class MachadoOuro extends Machado{
    public MachadoOuro(){
	setMaterial("ouro");
	setDurabilidade(5);
    }

    private void setMaterial(String material) {
	this.material = material;
    }
    
    public boolean compare(String itemStr, String material){
        String[] nomes = {"axe", "machado"};
        String[] tipos = {"gold","goldden","ouro"};
	if(Util.compare(nomes, itemStr)){
	    return Util.compare(tipos, material);
	}
	return false;
    }
}
