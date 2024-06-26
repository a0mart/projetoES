import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuPaginaInicialSocio extends JFrame{
    private JButton editoraButton;
    private JButton tituloButton;
    private JButton subGeneroButton;
    private JButton generoButton;
    private JButton autorButton;
    private JTable tabelaStocks;
    private JPanel menuPaginaInicialSocio;
    private JButton verDetalhesButton;
    private JTextField searchLirvo;

    private GestorBaseDados gestorBaseDados;


    public MenuPaginaInicialSocio(String title){
        super(title);

        gestorBaseDados = GestorBaseDados.getGestorBaseDados();

        setContentPane(menuPaginaInicialSocio);


        String[][] dataLivros = new String[gestorBaseDados.getStocks().size()][8];
        for (int i = 0; i < gestorBaseDados.getStocks().size(); i++){
            String titulo = gestorBaseDados.getStocks().get(i).getTitulo();
            String autor = gestorBaseDados.getStocks().get(i).getAutor();
            Genero genero = gestorBaseDados.getStocks().get(i).getGenero();
            SubGenero subGenero = gestorBaseDados.getStocks().get(i).getSubGenero();
            int numeroEdicao = gestorBaseDados.getStocks().get(i).getNumeroEdicao();
            int isbn = gestorBaseDados.getStocks().get(i).getIsbn();
            int ano = gestorBaseDados.getStocks().get(i).getAno();
            int id = gestorBaseDados.getStocks().get(i).getId();


            dataLivros[i] = new String[]{String.valueOf(id), titulo, autor, String.valueOf(genero), String.valueOf(subGenero), String.valueOf(numeroEdicao), String.valueOf(isbn), String.valueOf(ano)};
        }
        tabelaStocks.setModel(new DefaultTableModel(
                dataLivros,
                new String[]{"ID", "Titulo", "Autor", "Genero", "SubGenero", "Numero de Edicao", "ISBN", "Ano"}
        ));


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        verDetalhesButton.addActionListener(this::verDetalhesButtonActionPerformed);
        searchLirvo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DefaultTableModel obj = (DefaultTableModel) tabelaStocks.getModel();
                TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
                tabelaStocks.setRowSorter(obj1);
                obj1.setRowFilter(RowFilter.regexFilter(searchLirvo.getText()));
            }
        });
    }

    private void verDetalhesButtonActionPerformed(ActionEvent actionEvent){
        if (tabelaStocks.getSelectedRow() == -1){
            errorInvalidTableIndex();
        }
        int id = getStockSelecionado();

        close(id);
    }

    public int getStockSelecionado() {
        int row = tabelaStocks.getSelectedRow();
        String[] data = new String[8];

        for (int i = 0; i < 8; i++) {
            data[i] = tabelaStocks.getModel().getValueAt(row, i).toString();
        }
        return Integer.parseInt(data[0]);
    }

    public void errorInvalidTableIndex() {
        JOptionPane.showMessageDialog(null, "Tem de selecionar uma opcao em cada tabela!");
    }

    public void close(int stock) {
        setVisible(false);
        dispose();

        MenuDetalhesLivro menuDetalhesLivro = new MenuDetalhesLivro("Menu Detalhes do Livro", stock-1, 2);
        menuDetalhesLivro.setVisible(true);
    }
}
