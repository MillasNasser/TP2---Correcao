package Default;

public final class MachadoBronze extends Machado {

    public MachadoBronze() {
        setMaterial("bronze");
        setDurabilidade(2);
    }

    private void setMaterial(String material) {
        this.material = material;
    }
}
