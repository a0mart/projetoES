import java.util.ArrayList;
import java.util.List;

public class Socio extends Contacto{
    private static int socios = 0;
    private int id;
    private EstadoContaSocio estadoContaSocio;
    private List<Emprestimo> emprestimosAtivos;

    private List<Multa> multasPorPagar;

    public Socio(String nome, int nif, String morada, int telefone, String email) {
        super(nome, nif, morada, telefone, email);
        this.id = ++socios;
        this.estadoContaSocio = null;
        this.emprestimosAtivos = new ArrayList<>();
        this.multasPorPagar = new ArrayList<>();
    }

    public EstadoContaSocio getEstadoContaSocio() {
        return estadoContaSocio;
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        return emprestimosAtivos;
    }

    public int getId() {
        return id;
    }

    public List<Multa> getMultasPorPagar() {
        return multasPorPagar;
    }

    public void setEstadoContaSocio(EstadoContaSocio estadoContaSocio) {
        this.estadoContaSocio = estadoContaSocio;
    }

    public void fecharEmprestimo(Emprestimo emprestimo){
        if (!emprestimosAtivos.contains(emprestimo)) {
            return;
        }
        emprestimosAtivos.remove(emprestimo);
    }

    public void addEmprestimoAtivo(Emprestimo emprestimo) {
        emprestimosAtivos.add(emprestimo);
    }
}
