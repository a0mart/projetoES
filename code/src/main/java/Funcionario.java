public class Funcionario extends Contacto{
    private static int funcionarios = 0;
    private int id;
    private String password;
    public Funcionario(String nome, int nif, String morada, int telefone, String email, String password) {
        super(nome, nif, morada, telefone, email);
        this.password = password;
        this.id = ++funcionarios;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
