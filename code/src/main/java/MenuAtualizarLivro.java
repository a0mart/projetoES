import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

        String[][] dataLivros = new String[gestorBaseDados.getLivros().size()][7];
        for (int i = 0; i < gestorBaseDados.getLivros().size(); i++){
            int id = gestorBaseDados.getLivros().get(i).getId();
            String titulo = gestorBaseDados.getLivros().get(i).getTitulo();
            String autor = gestorBaseDados.getLivros().get(i).getAutor();
            Genero genero = gestorBaseDados.getLivros().get(i).getGenero();
            SubGenero subGenero = gestorBaseDados.getLivros().get(i).getSubGenero();
            int numeroEdicao = gestorBaseDados.getLivros().get(i).getNumeroEdicao();
            int isbn = gestorBaseDados.getLivros().get(i).getIsbn();
            int ano = gestorBaseDados.getLivros().get(i).getAno();
            EstadoLivro estadoLivro = gestorBaseDados.getLivros().get(i).getEstadoLivro();

            dataLivros[i] = new String[]{String.valueOf(id), titulo, autor, String.valueOf(genero), String.valueOf(subGenero), String.valueOf(numeroEdicao), String.valueOf(isbn), String.valueOf(ano), String.valueOf(estadoLivro)};

        }
        tabelaLivros.setModel(new DefaultTableModel(
                dataLivros,
                new String[]{"ID", "Titulo", "Autor", "Genero", "Sub Genero", "NºEdicao", "ISBN", "Ano", "Estado"}
        ));

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
