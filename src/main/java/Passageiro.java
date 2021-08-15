public class Passageiro {

    private final String nome;

    private final int idade;

    public Passageiro(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public boolean ePrioritario() {
        return idade >= 65;
    }

    String getNome() {
        return nome;
    }
}