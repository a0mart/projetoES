
import java.time.LocalDate;
import java.util.Date;


public class Emprestimo {
    private static int emprestimos = 0;
    private int idEmprestimo;
    private Livro livroEmprestado;
    private Socio socio;
    private EstadoEmprestimo estadoEmprestimo;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, Socio socio) {
        this.idEmprestimo = ++emprestimos;
        this.livroEmprestado = livro;
        this.socio = socio;
        this.dataEmprestimo = setDataEmprestimo();
        this.dataDevolucao = setDataDevolucao(dataEmprestimo);
        this.estadoEmprestimo = EstadoEmprestimo.Aberto;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public Livro getLivroEmprestado() {
        return livroEmprestado;
    }

    public String getNomeLivro(){
        return livroEmprestado.getTitulo();
    }

    public Socio getSocio() {
        return socio;
    }

    public String getNomeSocio(){
        return socio.getNome();
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public EstadoEmprestimo getEstadoEmprestimo() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(dataDevolucao)){
            setEstadoEmprestimo(EstadoEmprestimo.EmAtraso);
        }
        return estadoEmprestimo;
    }

    public void setEstadoEmprestimo(EstadoEmprestimo estadoEmprestimo) {
        this.estadoEmprestimo = estadoEmprestimo;
    }

    public LocalDate setDataEmprestimo(){
        LocalDate currentDate = LocalDate.now();
        return currentDate;
    }

    public LocalDate setDataDevolucao(LocalDate dataeamprestimo){
        LocalDate futureDate = dataeamprestimo.plusDays(5);
        return futureDate;
    }

    //setDataDevolucaoTeste, implementação para fazer teste unitário de empréstimo em atraso
    public void setDataDevolucaoTeste(LocalDate dataeamprestimo){
        LocalDate futureDate = dataeamprestimo.minusDays(5);
        dataDevolucao = futureDate;
    }
}
