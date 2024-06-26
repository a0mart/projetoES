import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

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
    private JButton verButton;
    private JLabel moradaLabel;
    private List<Livro> catalogo;
    private Fornecedor f;

    public MenuDetalhesFornecedor(String title,Fornecedor f) throws HeadlessException {
        super(title);
        this.f = f;
        this.catalogo = f.getLivrosDisponiveis();
        namelabel.setText(f.getNome());
        nifName.setText(String.valueOf(f.getNif()));
        tipoLabel.setText(String.valueOf(f.getTipoFornecedor()));
        emailLabel.setText(f.getEmail());
        moradaLabel.setText(f.getMorada());
        teleLabel.setText(String.valueOf(f.getTelefone()));
        estadoLabel.setText(String.valueOf(f.getEstadoContaFornecedor()));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(menuDetalhesFornecedor);
        setMinimumSize(new Dimension(900, 600));
        pack();

        /** Listeners */
        páginaInicialButton.addActionListener(this::btnpáginaInicialActionPerformed);
        gestãoDeLivrosButton.addActionListener(this::btngestãoDeLivrosActionPerformed);
        gestãoDeEmprestimosButton.addActionListener(this::btngestãoDeEmprestimosActionPerformed);
        gestãoDeFornecedoresButton.addActionListener(this::btnFornecedoresActionPerformed);
        verButton.addActionListener(this::btnverActionPerformed);
        editarButton.addActionListener(this::btneditarActionPerformed);
    }

    public void btnverActionPerformed(ActionEvent e) {
        MenuCatalogoFornecedor menuCatalogoFornecedor = new MenuCatalogoFornecedor("Menu Ver Catalogo",catalogo);
        menuCatalogoFornecedor.setVisible(true);
    }

    public void btneditarActionPerformed(ActionEvent e) {
        dispose();
        MenuEditarFornecedor menuEditarFornecedor = new MenuEditarFornecedor("Menu Editar Fornecedor",f);
        menuEditarFornecedor.setVisible(true);
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
