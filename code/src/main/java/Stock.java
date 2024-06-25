import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Stock extends DetalhesLivro{

    private static int idStocks = 0;
    private int id;
    private List<Livro> livros;
    private List<Reserva> reservas;

    public Stock(String titulo, String autor, Genero genero, SubGenero subGenero, int numeroEdicao, int isbn, int ano) {
        super(titulo, autor, genero, subGenero, numeroEdicao, isbn, ano);
        this.id = ++idStocks;
        livros = new ArrayList<>();
        reservas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getQuantidadeLivrosDisponivel() {
        int stock = 0;
        for (int i =0; i < livros.size(); i++){
            Livro livro = livros.get(i);
            if(livro.getEstadoLivro() == EstadoLivro.Disponivel){
                stock++;
            }
        }
        return stock;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void addStock(Livro livro){
        if (livro.getTitulo().equals(titulo) && livro.getAutor().equals(autor) && livro.getGenero().equals(genero) && livro.getSubGenero().equals(subGenero) && livro.getNumeroEdicao() == numeroEdicao && livro.getIsbn() == isbn && livro.getAno() == ano){
            livro.setEstadoLivro(EstadoLivro.Disponivel);
            livro.setCodigo(livros.size() + 1);
            livros.add(livro);
        }
        else{
            System.out.println("Erro, livro não pertence a este stock");
        }
    }
}
