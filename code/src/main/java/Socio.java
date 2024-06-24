public class Socio extends Contacto{
    private EstadoContaSocio estadoContaSocio;

    public Socio(String nome, int nif, String morada, int telefone, String email) {
        super(nome, nif, morada, telefone, email);
        this.estadoContaSocio = null;
    }

    public EstadoContaSocio getEstadoContaSocio() {
        return estadoContaSocio;
    }

    public void setEstadoContaSocio(EstadoContaSocio estadoContaSocio) {
        this.estadoContaSocio = estadoContaSocio;
    }
}
