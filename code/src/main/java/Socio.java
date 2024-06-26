import java.util.ArrayList;
import java.util.List;

public class Socio extends Contacto{
    private static int socios = 0;
    private int id;

    private EstadoContaSocio estadoContaSocio;
    private List<Emprestimo> emprestimosAtivos;

    private List<Multa> multasPorPagar;

    private String password;


    public Socio(String nome, int nif, String morada, int telefone, String email, String pass) {
        super(nome, nif, morada, telefone, email);
        this.id = ++socios;
        this.password = pass;
        this.estadoContaSocio = EstadoContaSocio.Ativo; //por omissao
        this.emprestimosAtivos = new ArrayList<>();
        this.multasPorPagar = new ArrayList<>();
    }

    public EstadoContaSocio getEstadoContaSocio() {
        if (multasPorPagar.size() > 0) {
            estadoContaSocio = EstadoContaSocio.Inativo;
        } else {
            estadoContaSocio = EstadoContaSocio.Ativo;
        }
        return estadoContaSocio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setEstadoContaSocio() {
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
