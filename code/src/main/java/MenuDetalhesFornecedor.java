import javax.swing.*;
import java.awt.*;

public class MenuDetalhesFornecedor extends JFrame{
    private JPanel menuDetalhesFornecedor;
    private JButton páginaInicialButton;
    private JButton gestãoDeReservasButton;
    private JButton gestãoDeFornecedoresButton;
    private JButton gestãoDeEmprestimosButton;
    private JButton gestãoDeMultasButton;
    private JButton gestãoDeSóciosButton;
    private JButton gestãoDeLivrosButton;
    private JPanel painelLivros;
    private JButton editarButton;
    private JButton históricoButton;
    private JLabel namelabel;
    private JLabel nifName;
    private JLabel tipoLabel;
    private JLabel emailLabel;
    private JLabel teleLabel;
    private JLabel estadoLabel;

    public MenuDetalhesFornecedor(String title,int id) throws HeadlessException {
        super(title);

        Fornecedor f = getDetalhesFornecedor(id);

        namelabel.setText(f.getNome());
        nifName.setText(String.valueOf(f.getNif()));
        tipoLabel.setText("");
        emailLabel.setText(f.getEmail());
        teleLabel.setText(String.valueOf(f.getTelefone()));
        estadoLabel.setText(String.valueOf(f.getEstadoContaFornecedor()));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(menuDetalhesFornecedor);
        setMinimumSize(new Dimension(900, 600));
        pack();

        /** LISTENERS */
    }

    public Fornecedor getDetalhesFornecedor(int id){
        GestorBaseDados gBDados = GestorBaseDados.getGestorBaseDados();
        return gBDados.getFornecedores().get(id);
    }
}
