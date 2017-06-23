package Default;

public final class MachadoFerro extends Machado{
    public MachadoFerro(){
	setMaterial("ferro");
	setDurabilidade(1);
    }

    private void setMaterial(String material) {
	this.material = material;
    }
    
    public boolean compare(String itemStr, String material){
        String[] nomes = {"axe", "machado"};
        String[] tipos = {"iron", "ferro"};
	if(Util.compare(nomes, itemStr)){
	    return Util.compare(tipos, material);
	}
	return false;
    }
}
