import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuFazerEmprestimo extends JFrame{
    private JPanel menuFazerEmprestimo;
    private JButton paginaInicialButton;
    private JButton gestaoDeSociosButton;
    private JButton gestaoDeMultasButton;
    private JButton fazerDevolucaoButton;
    private JButton fazerEmprestimoButton;
    private JButton gestaoDeLivrosButton;
    private JButton gestaoDeRequisitosButton;
    private JButton gestaoDeEmprestimosButton;
    private JTable tabelaSocios;
    private JTable tabelaLivros;
    private JButton confirmarButton;
    private JButton adicionarSocioButton;

    private GestorBaseDados gestorBaseDados;


    public MenuFazerEmprestimo(String title){
        super(title);

        setContentPane(menuFazerEmprestimo);

        gestorBaseDados = GestorBaseDados.getGestorBaseDados();

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

                dataLivros[i] = new String[]{titulo, autor, String.valueOf(genero), String.valueOf(subGenero), String.valueOf(numeroEdicao), String.valueOf(isbn), String.valueOf(ano), String.valueOf(id)};
            }
        }
        tabelaLivros.setModel(new DefaultTableModel(
                dataLivros,
                new String[]{"Titulo", "Autor", "Genero", "Sub Genero", "NºEdicao", "ISBN", "Ano", "ID"}
        ));

        setMinimumSize(new Dimension(900, 600));
        pack();

        confirmarButton.addActionListener(this::confirmarButtonActionPerformed);
    }

    private void confirmarButtonActionPerformed(ActionEvent actionEvent){
        if (tabelaLivros.getSelectedRow() == -1 && tabelaSocios.getSelectedRow() == -1) {
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
        String[] data = new String[8];

        for (int i = 0; i < 8; i++) {
            data[i] = tabelaLivros.getModel().getValueAt(row, i).toString();
        }
        return Integer.parseInt(data[7]);
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

}

