import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;

public class MenuGestaoLivros extends JFrame{
    private JButton gestãoDeReservasButton;
    private JButton gestãoDeFornecedoresButton;
    private JButton gestãoDeEmprestimosButton;
    private JButton gestãoDeMultasButton;
    private JButton gestãoDeSóciosButton;
    private JButton páginaInicialButton;
    private JButton fazerEncomendaButton;
    private JTextField procurarPorLivroTextField;
    private JButton button1;
    private JPanel painelLivros;
    private JPanel menuGestaoLivros;
    private JButton gestãoDeLivrosButton;

    private BotaoLivro[][] botoes;

    private GestorBaseDados gestorBaseDados;

    public MenuGestaoLivros(String titulo) {
        super(titulo);
        gestorBaseDados = GestorBaseDados.getGestorBaseDados();
        this.botoes = new BotaoLivro[3][3];
        painelLivros.setLayout(new GridLayout(3, 3));

        //adicionar a tabela 3 por 3 de livros á pagina
        int id = -1;
        int new_id = -2;
        Iterator<Livro> livroIterator = gestorBaseDados.getLivros().iterator();
        for (int linha = 0; linha < 3; ++linha) {
            for (int coluna = 0; coluna < 3; ++coluna) {
                botoes[linha][coluna] = new BotaoLivro(linha, coluna);

                // Ensure there are still books available in the iterator
                if (livroIterator.hasNext()) {
                    Livro l = livroIterator.next();
                    new_id = l.getId();
                    if (new_id != -1 && new_id != id) {
                        botoes[linha][coluna].setLivro(l);
                        id = l.getId();
                    }
                }

                botoes[linha][coluna].addActionListener(this::btnLivroActionPerformed);
                painelLivros.add(botoes[linha][coluna]);
            }
        }
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(menuGestaoLivros);
        setMinimumSize(new Dimension(900, 600));
        pack();

        /** Listeners */
        páginaInicialButton.addActionListener(this::btnpáginaInicialActionPerformed);
        gestãoDeFornecedoresButton.addActionListener(this::btnFornecedoresActionPerformed);
        gestãoDeEmprestimosButton.addActionListener(this::btngestãoDeEmprestimosActionPerformed);

    }
    public void btnFornecedoresActionPerformed(ActionEvent e) {
        dispose();
        MenuGestaoFornecedores menuGestaoFornecedores = new MenuGestaoFornecedores("Menu Gestão de fornecedoes");
        menuGestaoFornecedores.setVisible(true);
    }

    public void btngestãoDeEmprestimosActionPerformed(ActionEvent e) {
        MenuGestaoEmprestimo menuGestaoEmprestimos = new MenuGestaoEmprestimo("Menu Gestão de Emprestimos");
        menuGestaoEmprestimos.setVisible(true);
    }


    public void btnpáginaInicialActionPerformed(ActionEvent e) {
        dispose();
        MenuPrincipal menuPrincipal = new MenuPrincipal("Menu Principal");
        menuPrincipal.setVisible(true);
    }

    public void btnLivroActionPerformed(ActionEvent e) {
        BotaoLivro botao = (BotaoLivro) e.getSource();
        int id = -1;
        for (Stock s:gestorBaseDados.getStocks()) {
            if (s.isbn == botao.getLivro().isbn){
                id = s.getId();
            }
        }
        MenuDetalhesLivro menuDetalhesLivro = new MenuDetalhesLivro("Menu Destalhes do livro",id-1);
        menuDetalhesLivro.setVisible(true);
    }
}
