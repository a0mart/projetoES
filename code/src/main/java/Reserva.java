import java.util.List;

public class Reserva {
    private int idReserva = 1;
    private Stock livroReservado;
    private Socio socio;
    private EstadoReserva estadoReserva;

    public Reserva(int id, Stock livroReservado, Socio socio) {
        this.idReserva++;
        this.livroReservado = livroReservado;
        this.socio = socio;
        this.estadoReserva = EstadoReserva.EmEspera;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public Stock getLivroReservado() {
        return livroReservado;
    }

    public Socio getSocio() {
        return socio;
    }

    public EstadoReserva getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public void setLivroReservado(Stock livroReservado) {
        this.livroReservado = livroReservado;
    }
}
