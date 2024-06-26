import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class TesteProjetoFlavio {
    @Test
    public void testLivro(){
        Livro livro = new Livro("EI", "Manel", Genero.tecnico, SubGenero.informatica, 1, 123, 2024);
        assertEquals("EI", livro.getTitulo());
        assertEquals("Manel", livro.getAutor());
        assertEquals(Genero.tecnico, livro.getGenero());
        assertEquals(SubGenero.informatica, livro.getSubGenero());
        assertEquals(1, livro.getNumeroEdicao());
        assertEquals(123, livro.getIsbn());
        assertEquals(2024, livro.getAno());
    }

    @Test
    public void testEmprestimo(){
        Livro livro = new Livro("EI", "Manel", Genero.tecnico, SubGenero.informatica, 1, 123, 2024);
        Socio socio = new Socio("Maria", 123456789, "Casa", 987654321, "mail@","pass");

        Emprestimo emprestimo = new Emprestimo(livro, socio);

        assertEquals(livro, emprestimo.getLivroEmprestado());
        assertEquals(socio, emprestimo.getSocio());
        assertEquals("Maria", emprestimo.getNomeSocio());
    }

    @Test
    public void testsetEstadoLivro(){
        Livro livro = new Livro("EI", "Manel", Genero.tecnico, SubGenero.informatica, 1, 123, 2024);

        livro.setEstadoLivro(EstadoLivro.Indisponivel);
        assertEquals(EstadoLivro.Indisponivel, livro.getEstadoLivro());

        livro.setEstadoLivro(EstadoLivro.Reservado);
        assertEquals(EstadoLivro.Reservado, livro.getEstadoLivro());

        livro.setEstadoLivro(EstadoLivro.Devolvido);
        assertEquals(EstadoLivro.Devolvido, livro.getEstadoLivro());
    }

    @Test
    public void testQuantidadeStock(){

        Stock stock = new Stock("EI", "Manel", Genero.tecnico, SubGenero.informatica, 1, 123, 2024);
        for(int i=0; i < 8; i++){
            Livro livro = new Livro("EI", "Manel", Genero.tecnico, SubGenero.informatica, 1, 123, 2024);
            stock.addStock(livro);
        }
        stock.getLivros().get(1).setEstadoLivro(EstadoLivro.Indisponivel);
        stock.getLivros().get(6).setEstadoLivro(EstadoLivro.Devolvido);

        assertEquals(6, stock.getQuantidadeLivrosDisponivel());
        assertEquals(2, stock.getLivros().get(1).getCodigo());
    }

    @Test
    public void testEstadoEmprestimo(){
        LocalDate dataEmprestimo = LocalDate.now();

        Livro livro = new Livro("EI", "Manel", Genero.tecnico, SubGenero.informatica, 1, 123, 2024);
        Socio socio = new Socio("Maria", 123456789, "Casa", 987654321, "mail@","pass");

        Emprestimo emprestimo = new Emprestimo(livro, socio);

        emprestimo.setDataDevolucaoTeste(dataEmprestimo);

        assertEquals(EstadoEmprestimo.EmAtraso, emprestimo.getEstadoEmprestimo());
    }

}
