
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuGestaoSocios extends JFrame {
    private JPanel menuGestaoSocios;
    private JButton paginaInicialButton;
    private JButton gestãoDeReservasButton;
    private JButton gestãoDeFornecedoresButton;
    private JButton gestãoDeEmprestimosButton;
    private JButton gestãoDeMultasButton;
    private JButton gestãoDeSóciosButton;
    private JButton gestãoDeLivrosButton;
    private JTextField procurarNomeTextField;
    private JButton lupe;
    private JTable tabelaSocios;

    private GestorBaseDados gestorBaseDados;
    
    // Constructor
    public MenuGestaoSocios(String titulo){
        super(titulo);

        setContentPane(menuGestaoSocios);
        setMinimumSize(new Dimension(900, 600));
        pack();

        gestorBaseDados = GestorBaseDados.getGestorBaseDados();

        String[][] infoSocio = new String[gestorBaseDados.getSocios().size()][4];
        for(int i = 0; i < gestorBaseDados.getSocios().size(); i++){

            int id = gestorBaseDados.getSocios().get(i).getId();
            String nomeSocio = gestorBaseDados.getSocios().get(i).getNome();
            String reservas = gestorBaseDados.getSocios().get(i).getEmprestimosAtivos().toString();
            String multas = gestorBaseDados.getSocios().get(i).getMultasPorPagar().toString();
            String estado = gestorBaseDados.getSocios().get(i).getEstadoContaSocio().toString();

            infoSocio[i] = new String[]{String.valueOf(id), nomeSocio, reservas, multas, estado};


        }
        // Tabela
        tabelaSocios.setModel(new DefaultTableModel(
                infoSocio,
                new String[]{"Nº Sócio", "Nome", "Reservas", "Multas", "Estado"}
        ));

        setLocationRelativeTo(null);
        pack();

        /** Listeners */
        lupe.addActionListener(this::lupeActionPerformed);
        paginaInicialButton.addActionListener(this::paginaInicialButtonActionPerformed);
    }

    private void paginaInicialButtonActionPerformed(ActionEvent actionEvent) {
        dispose();
        MenuPrincipal menuPrincipal = new MenuPrincipal("Menu Principal");
        menuPrincipal.setVisible(true);
    }

    private void lupeActionPerformed(ActionEvent actionEvent) {
        // ao pesquisar por nome de socio
        String nome = procurarNomeTextField.getText().toLowerCase();


        if(nome.equals("")){
            JOptionPane.showMessageDialog(this, "Preencha o campo de pesquisa", "Erro", JOptionPane.ERROR_MESSAGE);
        }else if(gestorBaseDados.getSocios().stream().noneMatch(s -> s.getNome().toLowerCase().equals(nome))){
            JOptionPane.showMessageDialog(this, "Sócio não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
        } else{
            // Encontrou o sócio
            Socio socio = gestorBaseDados.getSocios().stream().filter(s -> s.getNome().toLowerCase().equals(nome)).findFirst().get();
            JOptionPane.showMessageDialog(this, "Sócio encontrado: " + socio.getNome(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }

    }


}
