import java.util.Date;

public class Emprestimo {
    private int idEmprestimo = 1;
    private Livro livroEmprestado;
    private Socio socio;
    private EstadoEmprestimo estadoEmprestimo;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private int tempoEmprestimo;

    public Emprestimo(Livro livroEmprestado, Socio socio, Date dataEmprestimo, int tempoEmprestimo) {
        this.idEmprestimo++;
        this.livroEmprestado = livroEmprestado;
        this.socio = socio;
        this.dataEmprestimo = dataEmprestimo;
        this.tempoEmprestimo = tempoEmprestimo;
        this.estadoEmprestimo = EstadoEmprestimo.Aberto;
        //this.dataDevolucao = setDataDevolucao(new Date());
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public Livro getLivroEmprestado() {
        return livroEmprestado;
    }

    public Socio getSocio() {
        return socio;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setEstadoEmprestimo(EstadoEmprestimo estadoEmprestimo) {
        this.estadoEmprestimo = estadoEmprestimo;
    }

    /*
    public void setDataDevolucao(Date dataDevolucao) {
        return dataDevolucao;
    }*/
}
