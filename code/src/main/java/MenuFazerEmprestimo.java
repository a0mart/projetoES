import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

        String[][] dataSocios = new String[gestorBaseDados.getSocios().size()][5];
        for (int i = 0; i < gestorBaseDados.getSocios().size(); i++){

            String nome = gestorBaseDados.getSocios().get(i).getNome();
            int nif = gestorBaseDados.getSocios().get(i).getNif();
            String morada = gestorBaseDados.getSocios().get(i).getMorada();
            int telefone = gestorBaseDados.getSocios().get(i).getTelefone();
            String email = gestorBaseDados.getSocios().get(i).getEmail();
            int id = gestorBaseDados.getSocios().get(i).getId();


            dataSocios[i] = new String[]{nome, String.valueOf(nif), morada, String.valueOf(telefone), email, String.valueOf(id)};
        }
        tabelaSocios.setModel(new DefaultTableModel(
                dataSocios,
                new String[]{ "Nome", "NIF", "Morada", "Telefone", "Email", "ID"}
        ));

        String[][] dataLivros = new String[gestorBaseDados.getLivros().size()][7];
        for (int i = 0; i < gestorBaseDados.getLivros().size(); i++){
            if (gestorBaseDados.getLivros().get(i).getEstadoLivro() == EstadoLivro.Disponivel){
                int id = gestorBaseDados.getLivros().get(i).getId();
                String titulo = gestorBaseDados.getLivros().get(i).getTitulo();
                String autor = gestorBaseDados.getLivros().get(i).getAutor();
                Genero genero = gestorBaseDados.getLivros().get(i).getGenero();
                SubGenero subGenero = gestorBaseDados.getLivros().get(i).getSubGenero();
                int numeroEdicao = gestorBaseDados.getLivros().get(i).getNumeroEdicao();
                int isbn = gestorBaseDados.getLivros().get(i).getIsbn();
                int ano = gestorBaseDados.getLivros().get(i).getAno();
                int codigo = gestorBaseDados.getLivros().get(i).getCodigo();

                dataLivros[i] = new String[]{String.valueOf(id), titulo, autor, String.valueOf(genero), String.valueOf(subGenero), String.valueOf(numeroEdicao), String.valueOf(isbn), String.valueOf(ano), String.valueOf(codigo)};
            }
        }
        tabelaLivros.setModel(new DefaultTableModel(
                dataLivros,
                new String[]{"ID", "Titulo", "Autor", "Genero", "Sub Genero", "NºEdicao", "ISBN", "Ano", "Código"}
        ));



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

