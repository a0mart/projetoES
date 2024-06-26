import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ReservaForm extends JFrame{
    private JPanel reserva;
    private JPanel menuGestaoReservas;
    private JButton paginaInicialButton;
    private JButton gestãoDeReservasButton;
    private JButton gestãoDeFornecedoresButton;
    private JButton gestãoDeEmprestimosButton;
    private JButton gestãoDeMultasButton;
    private JButton gestãoDeSóciosButton;
    private JButton gestãoDeLivrosButton;
    private JTable tabelaSocios;
    private JTextField procurarNomeTextField;
    private JButton lupe;

    private GestorBaseDados gestorBaseDados;

    // Constructor
    public ReservaForm(String titulo) {
        super(titulo);

        setContentPane(reserva);
        setMinimumSize(new Dimension(900, 600));
        pack();

        gestorBaseDados = GestorBaseDados.getGestorBaseDados();

        List<Reserva> reservas = gestorBaseDados.getReservas();
        String[][] infoReservas = new String[reservas.size()][2];
        for (int i = 0; i < reservas.size(); i++) {
            String nomeSocio = reservas.get(i).getSocio().getNome();
            String tituloLivro = reservas.get(i).getLivroReservado().getTitulo();
            String estadoReserva = reservas.get(i).getEstadoReserva().toString();

            infoReservas[i] = new String[]{nomeSocio, tituloLivro, estadoReserva};
        }

        // Definir o modelo da tabela
        tabelaSocios.setModel(new DefaultTableModel(
                infoReservas,
                new String[]{"Nome do Sócio", "Título do Livro Reservado", "Estado da Reserva"}
        ));

        setLocationRelativeTo(null);
        pack();

        /** Listeners */
        paginaInicialButton.addActionListener(this::paginaInicialButtonActionPerformed);
    }

    private void paginaInicialButtonActionPerformed(ActionEvent actionEvent) {
        dispose();
        MenuPrincipal menuPrincipal = new MenuPrincipal("Menu Principal");
        menuPrincipal.setVisible(true);
    }


}
