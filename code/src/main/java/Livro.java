import java.util.ArrayList;
import java.util.List;

public class Livro extends DetalhesLivro{
    private static int livros = 0;
    private int id;
    private int codigo;
    private EstadoLivro estadoLivro;

    private List<Emprestimo> emprestimos;

    public Livro(String titulo, String autor, Genero genero, SubGenero subGenero, int numearoEdicao, int isbn, int ano) {
        super(titulo, autor, genero, subGenero, numearoEdicao, isbn, ano);
        this.id = ++livros;
        this.codigo = 0;
        this.estadoLivro = EstadoLivro.Disponivel;
        emprestimos = new ArrayList<>();
    }

    public int getId() {
        return id;
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
