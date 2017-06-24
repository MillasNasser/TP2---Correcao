package Default;

public final class MachadoFerro extends Machado {

    public MachadoFerro() {
        setMaterial("ferro");
        setDurabilidade(1);
    }

    private void setMaterial(String material) {
        this.material = material;
    }
}
