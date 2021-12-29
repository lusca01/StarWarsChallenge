package Model;

public class Auxiliar {
    String nome;
    int participations;

    public Auxiliar(String nome, int participations){
        this.nome = nome;
        this.participations = participations;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getParticipations() {
        return participations;
    }

    public void setParticipations(int participations) {
        this.participations = participations;
    }
}
