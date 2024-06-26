import java.util.ArrayList;
import java.util.List;

public class Fornecedor extends Contacto{
    private static int fornecedores = 0;
    private int id;
    private EstadoContaFornecedor estadoContaFornecedor;
    private TipoFornecedor tipoFornecedor;
    private List<Livro> livrosDisponiveis;

    public Fornecedor(String nome, int nif, String morada, int telefone, String email,TipoFornecedor tipoFornecedor) {
        super(nome, nif, morada, telefone, email);
        this.id = ++fornecedores;
        this.estadoContaFornecedor = EstadoContaFornecedor.Ativa;
        this.tipoFornecedor = tipoFornecedor;
        this.livrosDisponiveis = new ArrayList<>();
    }

    public void setEstadoContaFornecedor(EstadoContaFornecedor estadoContaFornecedor) {
        this.estadoContaFornecedor = estadoContaFornecedor;
    }

    public void setTipoFornecedor(TipoFornecedor tipoFornecedor) {
        this.tipoFornecedor = tipoFornecedor;
    }

    public int getId() {
        return id;
    }

    public TipoFornecedor getTipoFornecedor() {
        return tipoFornecedor;
    }

    public EstadoContaFornecedor getEstadoContaFornecedor() {
        return estadoContaFornecedor;
    }

    public void setEstadoContaSocio(EstadoContaFornecedor estadoContaFornecedor) {
        this.estadoContaFornecedor = estadoContaFornecedor;
    }

    public List<Livro> getLivrosDisponiveis() {
        return livrosDisponiveis;
    }

    public void setLivrosDisponiveis(List<Livro> livrosDisponiveis) {
        this.livrosDisponiveis = livrosDisponiveis;
    }
}
