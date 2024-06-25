import java.util.ArrayList;
import java.util.List;

public class Fornecedor extends Contacto{
    private static int fornecedores = 0;
    private int id;
    private EstadoContaFornecedor estadoContaSocio;
    private List<Livro> livrosDisponiveis;

    public Fornecedor(String nome, int nif, String morada, int telefone, String email) {
        super(nome, nif, morada, telefone, email);
        this.id = ++fornecedores;
        this.estadoContaSocio = EstadoContaFornecedor.Ativa;
        this.livrosDisponiveis = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public EstadoContaFornecedor getEstadoContaSocio() {
        return estadoContaSocio;
    }

    public void setEstadoContaSocio(EstadoContaFornecedor estadoContaSocio) {
        this.estadoContaSocio = estadoContaSocio;
    }

    public List<Livro> getLivrosDisponiveis() {
        return livrosDisponiveis;
    }

    public void setLivrosDisponiveis(List<Livro> livrosDisponiveis) {
        this.livrosDisponiveis = livrosDisponiveis;
    }
}
