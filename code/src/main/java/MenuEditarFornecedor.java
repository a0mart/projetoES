import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuEditarFornecedor extends JFrame{
    private JPanel menuEditarFornecedor;
    private JButton páginaInicialButton;
    private JButton gestãoDeReservasButton;
    private JButton gestãoDeFornecedoresButton;
    private JButton gestãoDeEmprestimosButton;
    private JButton gestãoDeMultasButton;
    private JButton gestãoDeSóciosButton;
    private JButton gestãoDeLivrosButton;
    private JPanel painelLivros;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JButton carregarCatalogoButton;
    private JTextField fieldNif;
    private JTextField fieldNome;
    private JComboBox comboBoxTipo;
    private JTextField fieldTele;
    private JTextField fieldMail;
    private JTextField fieldMorada;
    private JComboBox comboBoxEstado;

    public MenuEditarFornecedor(String title) throws HeadlessException {
        super(title);
        comboBoxEstado.addItem(EstadoContaFornecedor.Ativa);
        comboBoxEstado.addItem(EstadoContaFornecedor.Inativa);
        comboBoxTipo.addItem(TipoFornecedor.EditoraAcademica);
        comboBoxTipo.addItem(TipoFornecedor.DistribuidorInternacional);
        comboBoxTipo.addItem(TipoFornecedor.EditoraIndependente);
        comboBoxTipo.addItem(TipoFornecedor.DistribuidorNacional);



        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(menuEditarFornecedor);
        setMinimumSize(new Dimension(900, 600));
        pack();

        /** Listeners */
        páginaInicialButton.addActionListener(this::btnpáginaInicialActionPerformed);
        gestãoDeLivrosButton.addActionListener(this::btngestãoDeLivrosActionPerformed);
        gestãoDeEmprestimosButton.addActionListener(this::btngestãoDeEmprestimosActionPerformed);
        gestãoDeFornecedoresButton.addActionListener(this::btnFornecedoresActionPerformed);
        cancelarButton.addActionListener(this::btncancelarActionPerformed);
    }
    public void btnpáginaInicialActionPerformed(ActionEvent e) {
        dispose();
        MenuPrincipal menuPrincipal = new MenuPrincipal("Menu Principal");
        menuPrincipal.setVisible(true);
    }

    public void btngestãoDeLivrosActionPerformed(ActionEvent e) {
        dispose();
        MenuGestaoLivros menuGestaoLivros = new MenuGestaoLivros("Menu Gestão de Livros");
        menuGestaoLivros.setVisible(true);
    }

    public void btncancelarActionPerformed(ActionEvent e) {
        dispose();
    }

    public void btngestãoDeEmprestimosActionPerformed(ActionEvent e) {
        MenuGestaoEmprestimo menuGestaoEmprestimos = new MenuGestaoEmprestimo("Menu Gestão de Emprestimos");
        menuGestaoEmprestimos.setVisible(true);
    }

    public void btnFornecedoresActionPerformed(ActionEvent e) {
        dispose();
        MenuGestaoFornecedores menuGestaoFornecedores = new MenuGestaoFornecedores("Menu Gestão de fornecedoes");
        menuGestaoFornecedores.setVisible(true);
    }
}
