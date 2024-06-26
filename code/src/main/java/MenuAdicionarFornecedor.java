import javax.swing.*;
import java.awt.*;

public class MenuAdicionarFornecedor extends JFrame{
    private JPanel menuAdicionarFornecedor;
    private JButton páginaInicialButton;
    private JButton gestãoDeReservasButton;
    private JButton gestãoDeFornecedoresButton;
    private JButton gestãoDeEmprestimosButton;
    private JButton gestãoDeMultasButton;
    private JButton gestãoDeSóciosButton;
    private JButton gestãoDeLivrosButton;
    private JPanel painelLivros;
    private JButton carregarCatalogoButton;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JTextField textField4;
    private JComboBox comboBox2;
    private JTextField textField1;

    public MenuAdicionarFornecedor(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(menuAdicionarFornecedor);
        setMinimumSize(new Dimension(900, 600));
        pack();
    }
}
