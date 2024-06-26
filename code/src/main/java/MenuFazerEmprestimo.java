import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;

public class MenuFazerEmprestimo extends JFrame{
    private JPanel menuFazerEmprestimo;
    private JButton paginaInicialButton;
    private JButton gestaoDeSociosButton;
    private JButton gestaoDeMultasButton;
    private JButton fazerDevolucaoButton;
    private JButton gestaoDeLivrosButton;
    private JButton gestaoDeRequisitosButton;
    private JButton gestaoDeEmprestimosButton;
    private JTable tabelaSocios;
    private JTable tabelaLivros;
    private JButton confirmarButton;
    private JButton adicionarSocioButton;
    private JButton fazerEmprestimoButton1;
    private JTextField searchSocio;
    private JTextField searchLivro;
    private JTextField textField1;

    private GestorBaseDados gestorBaseDados;


    public MenuFazerEmprestimo(String title){
        super(title);
        gestorBaseDados = GestorBaseDados.getGestorBaseDados();
        setContentPane(menuFazerEmprestimo);

        Iterator<Socio> socioIterator = gestorBaseDados.getSocios().iterator();
        Iterator<Livro> livoIterator = gestorBaseDados.getLivros().iterator();

        String[] colunas = { "Nome", "NIF", "Morada", "Telefone", "Email", "ID"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        while (socioIterator.hasNext()) {
            Socio socio = socioIterator.next();
            String nome = socio.getNome();
            int nif = socio.getNif();
            String morada = socio.getMorada();
            int telefone = socio.getTelefone();
            String email = socio.getEmail();
            int id = socio.getId();

            Object[] row = {nome, nif, morada, telefone, email, id};
            model.addRow(row);
        }
        tabelaSocios.setModel(model);

        String[] colunasL = {"ID", "Titulo", "Autor", "Genero", "Sub Genero", "NºEdicao", "ISBN", "Ano", "Código"};
        DefaultTableModel modelo = new DefaultTableModel(colunasL, 0);

        while (livoIterator.hasNext()){
            Livro livro = livoIterator.next();
            if (livro.getEstadoLivro() == EstadoLivro.Disponivel) {
                int id = livro.getId();
                String titulo = livro.getTitulo();
                String autor = livro.getAutor();
                Genero genero = livro.getGenero();
                SubGenero subGenero = livro.getSubGenero();
                int numeroEdicao = livro.getNumeroEdicao();
                int isbn = livro.getIsbn();
                int ano = livro.getAno();
                int codigo = livro.getCodigo();

                Object[] row = {id, titulo, autor, genero, subGenero, numeroEdicao, isbn, ano, codigo};
                modelo.addRow(row);
            }
        }
        tabelaLivros.setModel(modelo);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        confirmarButton.addActionListener(this::confirmarButtonActionPerformed);
        paginaInicialButton.addActionListener(this::paginaIncialButtonButtonActionPerformed);
        gestaoDeLivrosButton.addActionListener(this::gestaoDeLivrosButtonActionPerformed);
        gestaoDeEmprestimosButton.addActionListener(this::gestaoDeEmprestimosButtonActionPerformed);
        fazerDevolucaoButton.addActionListener(this::fazerDevolucaoButtonActionPerformed);

        searchLivro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DefaultTableModel obj = (DefaultTableModel) tabelaLivros.getModel();
                TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
                tabelaLivros.setRowSorter(obj1);
                obj1.setRowFilter(RowFilter.regexFilter(searchLivro.getText()));
            }
        });
        searchSocio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DefaultTableModel obj = (DefaultTableModel) tabelaSocios.getModel();
                TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
                tabelaSocios.setRowSorter(obj1);
                obj1.setRowFilter(RowFilter.regexFilter(searchSocio.getText()));
            }
        });
    }

    private void confirmarButtonActionPerformed(ActionEvent actionEvent){
        if (tabelaLivros.getSelectedRow() == -1 || tabelaSocios.getSelectedRow() == -1) {
            errorInvalidTableIndex();
        }
        int livro = getLivroSelecionado();
        int socio = getSocioSelecionado();

        if (gestorBaseDados.getSocios().get(socio-1).getEmprestimosAtivos().size() < 5){
            Emprestimo emprestimo = new Emprestimo(gestorBaseDados.getLivros().get(livro-1), gestorBaseDados.getSocios().get(socio-1));
            gestorBaseDados.addEmprestimo(emprestimo);
            gestorBaseDados.getLivros().get(livro-1).addEmprestimo(emprestimo);
            gestorBaseDados.getSocios().get(socio-1).addEmprestimoAtivo(emprestimo);
            gestorBaseDados.getLivros().get(livro-1).setEstadoLivro(EstadoLivro.Indisponivel);
            JOptionPane.showMessageDialog(null, "Emprestimo efetuado com sucesso!");
        }
        else{
            JOptionPane.showMessageDialog(null, "O socio já tem o máximo de empréstimos ativos possivel!");
        }


        close();
    }

    public int getLivroSelecionado() {
        int row = tabelaLivros.getSelectedRow();
        String[] data = new String[9];

        for (int i = 0; i < 9; i++) {
            data[i] = tabelaLivros.getModel().getValueAt(row, i).toString();
        }
        return Integer.parseInt(data[0]);
    }
    public int getSocioSelecionado() {
        int row = tabelaSocios.getSelectedRow();
        String[] data = new String[6];

        for (int i = 0; i < 6; i++) {
            data[i] = tabelaSocios.getModel().getValueAt(row, i).toString();
        }
        return Integer.parseInt(data[5]);
    }

    public void errorInvalidTableIndex() {
        JOptionPane.showMessageDialog(null, "Tem de selecionar uma opcao em cada tabela!");
    }
    public void close() {
        setVisible(false);
        dispose();

        MenuGestaoEmprestimo menuGestaoEmprestimo = new MenuGestaoEmprestimo("Menu Gestao de Emprestimos");
        menuGestaoEmprestimo.setVisible(true);
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

    private void fazerDevolucaoButtonActionPerformed(ActionEvent actionEvent){
        setVisible(false);
        dispose();
        MenuFazerDevolucao menuFazerDevolucao = new MenuFazerDevolucao("Menu Fazer Devolucao");
        menuFazerDevolucao.setVisible(true);
    }

}

