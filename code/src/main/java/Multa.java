public class Multa {
    private TipoMulta tipoMulta;
    private double valor;
    private Socio socio;

    public Multa(TipoMulta tipoMulta, double valor, Socio socio) {
        this.tipoMulta = tipoMulta;
        this.valor = valor;
        this.socio = socio;
    }

    public TipoMulta getTipoMulta() {
        return tipoMulta;
    }

    public double getValor() {
        return valor;
    }

    public Socio getSocio() {
        return socio;
    }


}
