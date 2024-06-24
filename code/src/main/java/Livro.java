import java.util.ArrayList;
import java.util.List;

public class Livro extends DetalhesLivro{
    private int codigo;
    private EstadoLivro estadoLivro;

    private List<Emprestimo> emprestimos;

    public Livro(String titulo, String autor, Genero genero, SubGenero subGenero, int numearoEdicao, int isbn, int ano) {
        super(titulo, autor, genero, subGenero, numearoEdicao, isbn, ano);
        this.codigo = 0;
        this.estadoLivro = null;
        emprestimos = new ArrayList<>();
    }


    public int getCodigo() {
        return codigo;
    }

    public EstadoLivro getEstadoLivro() {
        return estadoLivro;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setEstadoLivro(EstadoLivro estadoLivro) {
        this.estadoLivro = estadoLivro;
    }

    public void addEmprestimo(Emprestimo emprestimo) {
        if (emprestimo == null){
            return;
        }
        emprestimos.add(emprestimo);
    }
}
