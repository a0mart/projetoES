public abstract class Contacto {
    protected String nome;
    protected int nif;
    protected String morada;
    protected int telefone;
    protected String email;

    public Contacto(String nome, int nif, String morada, int telefone, String email) {
        this.nome = nome;
        this.nif = nif;
        this.morada = morada;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public int getNif() {
        return nif;
    }

    public String getMorada() {
        return morada;
    }

    public int getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }
}
