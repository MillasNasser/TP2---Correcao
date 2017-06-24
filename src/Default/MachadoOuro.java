package Default;

public final class MachadoOuro extends Machado{
    public MachadoOuro(){
        setMaterial("ouro");
        setDurabilidade(5);
    }

    private void setMaterial(String material) {
        this.material = material;
    }
}
