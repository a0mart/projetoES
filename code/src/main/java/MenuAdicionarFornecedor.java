import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
    private JComboBox comboBoxTipo;
    private JTextField textField4;
    private JComboBox comboBoxEstado;
    private JTextField textField1;

    public MenuAdicionarFornecedor(String title) throws HeadlessException {
        super(title);
        comboBoxTipo.addItem(TipoFornecedor.EditoraAcademica);
        comboBoxTipo.addItem(TipoFornecedor.DistribuidorInternacional);
        comboBoxTipo.addItem(TipoFornecedor.EditoraIndependente);
        comboBoxTipo.addItem(TipoFornecedor.DistribuidorNacional);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(menuAdicionarFornecedor);
        setMinimumSize(new Dimension(900, 600));
        pack();

        /** Listeners */
        cancelarButton.addActionListener(this::btncancelarActionPerformed);
        páginaInicialButton.addActionListener(this::btnpáginaInicialActionPerformed);
        gestãoDeLivrosButton.addActionListener(this::btngestãoDeLivrosActionPerformed);
        gestãoDeEmprestimosButton.addActionListener(this::btngestãoDeEmprestimosActionPerformed);
        gestãoDeFornecedoresButton.addActionListener(this::btnFornecedoresActionPerformed);
    }

    public void btncancelarActionPerformed(ActionEvent e) {
        dispose();
        MenuGestaoFornecedores menuGestaoFornecedores = new MenuGestaoFornecedores("Menu gestão de fornecedores");
        menuGestaoFornecedores.setVisible(true);
    }

    public void btnFornecedoresActionPerformed(ActionEvent e) {
        dispose();
        MenuGestaoFornecedores menuGestaoFornecedores = new MenuGestaoFornecedores("Menu Gestão de fornecedoes");
        menuGestaoFornecedores.setVisible(true);
    }

    public void btnpáginaInicialActionPerformed(ActionEvent e) {
        dispose();
        MenuPrincipal menuPrincipal = new MenuPrincipal("Menu Principal");
        menuPrincipal.setVisible(true);
    }

    public void btngestãoDeEmprestimosActionPerformed(ActionEvent e) {
        MenuGestaoEmprestimo menuGestaoEmprestimos = new MenuGestaoEmprestimo("Menu Gestão de Emprestimos");
        menuGestaoEmprestimos.setVisible(true);
    }

    public void btngestãoDeLivrosActionPerformed(ActionEvent e) {
        dispose();
        MenuGestaoLivros menuGestaoLivros = new MenuGestaoLivros("Menu Gestão de Livros");
        menuGestaoLivros.setVisible(true);
    }
}
