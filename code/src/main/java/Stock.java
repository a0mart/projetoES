import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Stock extends DetalhesLivro{
    private int quantidadeLivros;
    private List<Livro> livros;
    private List<Reserva> reservas;
    private List<Emprestimo> emprestimos;

    public Stock(String titulo, String autor, Genero genero, SubGenero subGenero, int numeroEdicao, int isbn, int ano) {
        super(titulo, autor, genero, subGenero, numeroEdicao, isbn, ano);
        this.quantidadeLivros = 0;
        livros = new ArrayList<>();
        reservas = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    public int getQuantidadeLivros() {
        return quantidadeLivros;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void addStock(Livro livro){
            if (livro.getTitulo().equals(titulo) && livro.getAutor().equals(autor) && livro.getGenero().equals(genero) && livro.getSubGenero().equals(subGenero) && livro.getNumeroEdicao() == numeroEdicao && livro.getIsbn() == isbn && livro.getAno() == ano){
                livro.setEstadoLivro(EstadoLivro.Disponivel);
                livro.setCodigo(livros.indexOf(livro) + 1);
                livros.add(livro);
                setQuantidadeLivros(livros.indexOf(livro) + 1);
            }
        else{
            System.out.println("Erro, livro não pertence a este stock");
        }
    }

    public void setQuantidadeLivros(int quantidade){
        this.quantidadeLivros = quantidade;
    }



}
