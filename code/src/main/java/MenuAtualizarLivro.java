import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;

public class MenuAtualizarLivro extends JFrame{
    private JButton gestaoDeSociosButton;
    private JButton gestaoDeMultasButton;
    private JButton gestaoDeEmprestimosButton;
    private JButton gestaoDeLivrosButton;
    private JButton gestaoDeRequisitosButton;
    private JButton paginaInicialButton;
    private JTable tabelaLivros;
    private JPanel menuAtualizarLivro;
    private JButton atualizarButton;
    private JButton atualizarLivroButton1;
    private JTextField searchLivro;

    private GestorBaseDados gestorBaseDados;


    public MenuAtualizarLivro(String title){
        super(title);

        setContentPane(menuAtualizarLivro);

        gestorBaseDados = GestorBaseDados.getGestorBaseDados();

        Iterator<Livro> livroIterator = gestorBaseDados.getLivros().iterator();

        String[] colunasL = {"ID", "Titulo", "Autor", "Genero", "Sub Genero", "NºEdicao", "ISBN", "Ano", "Código", "Estado do Livro"};
        DefaultTableModel modelo = new DefaultTableModel(colunasL, 0);

        while (livroIterator.hasNext()){
            Livro livro = livroIterator.next();
                int id = livro.getId();
                String titulo = livro.getTitulo();
                String autor = livro.getAutor();
                Genero genero = livro.getGenero();
                SubGenero subGenero = livro.getSubGenero();
                int numeroEdicao = livro.getNumeroEdicao();
                int isbn = livro.getIsbn();
                int ano = livro.getAno();
                int codigo = livro.getCodigo();
                EstadoLivro estadoLivro = livro.getEstadoLivro();

                Object[] row = {id, titulo, autor, genero, subGenero, numeroEdicao, isbn, ano, codigo, estadoLivro};
                modelo.addRow(row);
        }
        tabelaLivros.setModel(modelo);


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        atualizarButton.addActionListener(this::atualizarButtonActionPerformed);
        paginaInicialButton.addActionListener(this::paginaIncialButtonButtonActionPerformed);
        gestaoDeLivrosButton.addActionListener(this::gestaoDeLivrosButtonActionPerformed);
        gestaoDeEmprestimosButton.addActionListener(this::gestaoDeEmprestimosButtonActionPerformed);
        searchLivro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DefaultTableModel obj = (DefaultTableModel) tabelaLivros.getModel();
                TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
                tabelaLivros.setRowSorter(obj1);
                obj1.setRowFilter(RowFilter.regexFilter(searchLivro.getText()));
            }
        });
    }

    public void atualizarButtonActionPerformed(ActionEvent e){
        if (tabelaLivros.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Tem de selecionar uma opcao da tabela!");
        }
        int id = getLivroSelecionado();

        close(id);
    }
    public int getLivroSelecionado() {
        int row = tabelaLivros.getSelectedRow();
        String[] data = new String[9];

        for (int i = 0; i < 9; i++) {
            data[i] = tabelaLivros.getModel().getValueAt(row, i).toString();
        }
        return Integer.parseInt(data[0]);
    }

    public void close(int stock) {
        setVisible(false);
        dispose();
        SubMenuAtualizarLivro subMenu_atualizar_livro = new SubMenuAtualizarLivro("SubMenu Atualizar livro", stock-1);
        subMenu_atualizar_livro.setVisible(true);
    }

    public void paginaIncialButtonButtonActionPerformed(ActionEvent e){
        setVisible(false);
        dispose();
        MenuPrincipal menuPrincipal = new MenuPrincipal("Menu Principal");
        menuPrincipal.setVisible(true);
    }

    private void gestaoDeLivrosButtonActionPerformed(ActionEvent actionEvent){
        dispose();
        MenuGestaoLivros menuGestaoLivros = new MenuGestaoLivros("Menu Gestão de Livros");
        menuGestaoLivros.setVisible(true);
    }

    private void gestaoDeEmprestimosButtonActionPerformed(ActionEvent actionEvent){
        setVisible(false);
        dispose();
        MenuGestaoEmprestimo menuGestaoEmprestimos = new MenuGestaoEmprestimo("Menu Gestão de Emprestimos");
        menuGestaoEmprestimos.setVisible(true);
    }
}
