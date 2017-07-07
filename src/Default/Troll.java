package Default;

public class Troll {
    private String nome;
    
    
    public Troll(){
        this.nome = TrollNome.gerarNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
