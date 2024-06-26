import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private JTextField fieldNif;
    private JTextField fieldNome;
    private JComboBox comboBoxTipo;
    private JTextField fieldMail;
    private JComboBox comboBoxEstado;
    private JTextField fieldTele;
    private JTextField fieldMorada;
    private List<Livro> catalogo;

    private GestorBaseDados gestorBaseDados;

    public MenuAdicionarFornecedor(String title) throws HeadlessException {
        super(title);
        comboBoxTipo.addItem(TipoFornecedor.EditoraAcademica);
        comboBoxTipo.addItem(TipoFornecedor.DistribuidorInternacional);
        comboBoxTipo.addItem(TipoFornecedor.EditoraIndependente);
        comboBoxTipo.addItem(TipoFornecedor.DistribuidorNacional);
        gestorBaseDados = GestorBaseDados.getGestorBaseDados();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(menuAdicionarFornecedor);
        setMinimumSize(new Dimension(900, 600));
        pack();

        /** Listeners */
        cancelarButton.addActionListener(this::btncancelarActionPerformed);
        confirmarButton.addActionListener(this::btnconfirmarActionPerformed);
        páginaInicialButton.addActionListener(this::btnpáginaInicialActionPerformed);
        gestãoDeLivrosButton.addActionListener(this::btngestãoDeLivrosActionPerformed);
        gestãoDeEmprestimosButton.addActionListener(this::btngestãoDeEmprestimosActionPerformed);
        gestãoDeFornecedoresButton.addActionListener(this::btnFornecedoresActionPerformed);
        carregarCatalogoButton.addActionListener(this::btnCarregarCatalogoActionPerformed);
    }

    public static List<Livro> loadCatalogoFromFile(String filePath) {
        List<Livro> catalogo = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 7) {
                    Livro livro = new Livro(values[0], values[1], Genero.ficcao/*values[2]*/, SubGenero.suspense /*values[3]*/, Integer.parseInt(values[4]), Integer.parseInt(values[5]), Integer.parseInt(values[6]));
                    catalogo.add(livro);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return catalogo;
    }

    public void btncancelarActionPerformed(ActionEvent e) {
        dispose();
        MenuGestaoFornecedores menuGestaoFornecedores = new MenuGestaoFornecedores("Menu gestão de fornecedores");
        menuGestaoFornecedores.setVisible(true);
    }

    public void btnconfirmarActionPerformed(ActionEvent e) {
        // Assuming you have a Fornecedor object created with the input fields
        if (catalogo != null && fieldNome.getText().compareTo("") != 0 && fieldNif.getText().compareTo("") != 0 && fieldTele.getText().compareTo("") != 0 && fieldMail.getText().compareTo("") != 0 && fieldMorada.getText().compareTo("") != 0) {
            Fornecedor f = new Fornecedor(fieldNome.getText(), Integer.parseInt(fieldNif.getText()), fieldMorada.getText(), Integer.parseInt(fieldTele.getText()), fieldMail.getText(), (TipoFornecedor) comboBoxTipo.getSelectedItem());
            f.setLivrosDisponiveis(catalogo);
            gestorBaseDados.addFornecedor(f);
            dispose();
            MenuGestaoFornecedores menuGestaoFornecedores = new MenuGestaoFornecedores("Menu gestão de fornecedores");
            menuGestaoFornecedores.setVisible(true);
        }else
        {
            JOptionPane.showMessageDialog(this, "Campos em falta!");
        }
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

    public void btnCarregarCatalogoActionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            catalogo = loadCatalogoFromFile(filePath);
            JOptionPane.showMessageDialog(this, "Catálogo carregado com sucesso!");
        }
    }
}
