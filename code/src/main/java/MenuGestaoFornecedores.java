import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MenuGestaoFornecedores extends JFrame{
    private JPanel menuGestaoFornecedores;
    private JButton páginaInicialButton;
    private JButton gestãoDeReservasButton;
    private JButton gestãoDeLivrosButton;
    private JButton gestãoDeFornecedoresButton;
    private JButton gestãoDeEmprestimosButton;
    private JButton gestãoDeMultasButton;
    private JButton gestãoDeSóciosButton;
    private JPanel painelLivros;
    private JButton adicionarFornecedorButton;
    private JTextField procurarPorFornecedorTextField;
    private JButton button1;
    private GestorBaseDados gestorBaseDados;

    private BotaoFornecedor[][] botoes;

    public MenuGestaoFornecedores(String title) throws HeadlessException {
        super(title);
        gestorBaseDados = GestorBaseDados.getGestorBaseDados();
        this.botoes = new BotaoFornecedor[3][3];
        painelLivros.setLayout(new GridLayout(3, 3));

        //adicionar a tabela 3 por 3 de Fornecedores á
        int id = -1;
        int new_id = -2;
        Iterator<Fornecedor> fornecedorIterator = gestorBaseDados.getFornecedores().iterator();
        for (int linha = 0; linha < 3; ++linha) {
            for (int coluna = 0; coluna < 3; ++coluna) {
                botoes[linha][coluna] = new BotaoFornecedor(linha, coluna);

                if (fornecedorIterator.hasNext()) {
                    Fornecedor f = fornecedorIterator.next();
                    new_id = f.getId();
                    if (new_id != -1 && new_id != id) {
                        botoes[linha][coluna].setFornecedor(f);
                        id = f.getId();
                    }
                }else{
                    botoes[linha][coluna].setEnabled(false);
                }

                botoes[linha][coluna].addActionListener(this::btnFornecedorActionPerformed);
                painelLivros.add(botoes[linha][coluna]);
            }
        }

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(menuGestaoFornecedores);
        setMinimumSize(new Dimension(900, 600));
        pack();

        /** Listeners */
        páginaInicialButton.addActionListener(this::btnpáginaInicialActionPerformed);
        gestãoDeLivrosButton.addActionListener(this::btngestãoDeLivrosActionPerformed);
        gestãoDeEmprestimosButton.addActionListener(this::btngestãoDeEmprestimosActionPerformed);
        adicionarFornecedorButton.addActionListener(this::btnadicionarFornecedorActionPerformed);

    }

    public void btngestãoDeEmprestimosActionPerformed(ActionEvent e) {
        MenuGestaoEmprestimo menuGestaoEmprestimos = new MenuGestaoEmprestimo("Menu Gestão de Emprestimos");
        menuGestaoEmprestimos.setVisible(true);
    }

    public void btnadicionarFornecedorActionPerformed(ActionEvent e) {
        dispose();
        MenuAdicionarFornecedor menuAdicionarFornecedor = new MenuAdicionarFornecedor("Menu Adicionar Fornecedor");
        menuAdicionarFornecedor.setVisible(true);
    }

    public void btngestãoDeLivrosActionPerformed(ActionEvent e) {
        dispose();
        MenuGestaoLivros menuGestaoLivros = new MenuGestaoLivros("Menu Gestão de Livros");
        menuGestaoLivros.setVisible(true);
    }
    public void btnpáginaInicialActionPerformed(ActionEvent e) {
        dispose();
        MenuPrincipal menuPrincipal = new MenuPrincipal("Menu Principal");
        menuPrincipal.setVisible(true);
    }

    public void btnFornecedorActionPerformed(ActionEvent e) {
        dispose();
        BotaoFornecedor botao = (BotaoFornecedor) e.getSource();
        int id = -1;
        for (Fornecedor s:gestorBaseDados.getFornecedores()) {
            if (s.getNome() == botao.getFornecedor().getNome()){
                MenuDetalhesFornecedor menuDetalhesFornecedor = new MenuDetalhesFornecedor("Menu Destalhes do fornecedor",s);
                menuDetalhesFornecedor.setVisible(true);
            }
        }
    }
}
