import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestorBaseDados {
    private ArrayList<Livro> livros;
    private ArrayList<Socio> socios;
    private ArrayList<Emprestimo> emprestimos;
    private ArrayList<Stock> stocks;

    private List<Multa> multas;

    private static GestorBaseDados instancia = new GestorBaseDados();

    public static GestorBaseDados getGestorBaseDados(){
        return instancia;
    }

    private GestorBaseDados(){
        livros = new ArrayList<>();
        socios = new ArrayList<>();
        emprestimos = new ArrayList<>();
        stocks = new ArrayList<>();
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public ArrayList<Socio> getSocios() {
        return socios;
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public ArrayList<Stock> getStocks(){
        return stocks;
    }

    public List<Multa> getMultas(){
        return multas;
    }

    public void addLivro (Livro livro){
        livros.add(livro);
    }

    public void addSocio (Socio socio){
        socios.add(socio);
    }

    public void addEmprestimo (Emprestimo emprestimo){
        emprestimos.add(emprestimo);
    }

    public void criarStockeLivros(String titulo, String autor, Genero genero, SubGenero subGenero, int numeroEdicao, int isbn, int ano, int quantidade){
        Stock stock = new Stock(titulo, autor, genero, subGenero, numeroEdicao, isbn, ano);

        if (stocks.contains(stock)) {
            for (int i=0; i < quantidade; i++){
                Livro livro = new Livro(titulo, autor, genero, subGenero, numeroEdicao, isbn, ano);
                stock.addStock(livro);
                livros.add(livro);
            }
        }else{
            stocks.add(stock);
            for (int i=0; i < quantidade; i++){
                Livro livro = new Livro(titulo, autor, genero, subGenero, numeroEdicao, isbn, ano);
                stock.addStock(livro);
                livros.add(livro);
            }
        }
    }

}
