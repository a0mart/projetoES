import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteProjetoLuis {
    @Test
    public void testFornecedor(){
        Fornecedor fornecedor = new Fornecedor("joao",1,"rua",93,"mail",TipoFornecedor.EditoraIndependente);
        assertEquals("joao", fornecedor.getNome());
        assertEquals(1, fornecedor.getNif());
        assertEquals("rua", fornecedor.getMorada());
        assertEquals(93, fornecedor.getTelefone());
        assertEquals("mail", fornecedor.getEmail());
        assertEquals(TipoFornecedor.EditoraIndependente, fornecedor.getTipoFornecedor());
        assertEquals(EstadoContaFornecedor.Ativa,fornecedor.getEstadoContaFornecedor());
    }

    @Test
    public void testsetEstadoFornecedor (){
        Fornecedor fornecedor = new Fornecedor("joao",1,"rua",93,"mail",TipoFornecedor.EditoraIndependente);

        fornecedor.setEstadoContaFornecedor(EstadoContaFornecedor.Ativa);
        assertEquals(EstadoContaFornecedor.Ativa, fornecedor.getEstadoContaFornecedor());

        fornecedor.setEstadoContaFornecedor(EstadoContaFornecedor.Inativa);
        assertEquals(EstadoContaFornecedor.Inativa, fornecedor.getEstadoContaFornecedor());
    }

    @Test
    public void testsetTipoFornecedor (){
        Fornecedor fornecedor = new Fornecedor("joao",1,"rua",93,"mail",TipoFornecedor.EditoraIndependente);

        fornecedor.setTipoFornecedor(TipoFornecedor.DistribuidorNacional);
        assertEquals(TipoFornecedor.DistribuidorNacional, fornecedor.getTipoFornecedor());

        fornecedor.setTipoFornecedor(TipoFornecedor.EditoraIndependente);
        assertEquals(TipoFornecedor.EditoraIndependente, fornecedor.getTipoFornecedor());

        fornecedor.setTipoFornecedor(TipoFornecedor.DistribuidorInternacional);
        assertEquals(TipoFornecedor.DistribuidorInternacional, fornecedor.getTipoFornecedor());

        fornecedor.setTipoFornecedor(TipoFornecedor.EditoraAcademica);
        assertEquals(TipoFornecedor.EditoraAcademica, fornecedor.getTipoFornecedor());
    }

    @Test
    public void testSetAndGetLivrosDisponiveis() {
        Fornecedor fornecedor = new Fornecedor("joao",1,"rua",93,"mail",TipoFornecedor.EditoraIndependente);

        List<Livro> livros = new ArrayList<>();
        Livro livro1 = new Livro("Livro 1", "Autor 1",Genero.ficcao,SubGenero.terror,1,2,3);
        Livro livro2 = new Livro("Livro 2", "Autor 2",Genero.ficcao,SubGenero.suspense,4,5,6);
        livros.add(livro1);
        livros.add(livro2);

        fornecedor.setLivrosDisponiveis(livros);
        assertEquals(livros, fornecedor.getLivrosDisponiveis());
    }

    @Test
    public void testAddLivroDisponivel() {
        Fornecedor fornecedor = new Fornecedor("joao",1,"rua",93,"mail",TipoFornecedor.EditoraIndependente);

        Livro livro = new Livro("Livro 1", "Autor 1",Genero.ficcao,SubGenero.terror,1,2,3);
        fornecedor.getLivrosDisponiveis().add(livro);
        assertTrue(fornecedor.getLivrosDisponiveis().contains(livro));
    }

}
