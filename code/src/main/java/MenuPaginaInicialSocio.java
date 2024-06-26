import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;

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

        Iterator<Stock> stockIterator = gestorBaseDados.getStocks().iterator();

        String[] colunasL = {"ID", "Titulo", "Autor", "Genero", "Sub Genero", "NºEdicao", "ISBN", "Ano"};
        DefaultTableModel modelo = new DefaultTableModel(colunasL, 0);

        while (stockIterator.hasNext()){
            Stock stock = stockIterator.next();
                int id = stock.getId();
                String titulo = stock.getTitulo();
                String autor = stock.getAutor();
                Genero genero = stock.getGenero();
                SubGenero subGenero = stock.getSubGenero();
                int numeroEdicao = stock.getNumeroEdicao();
                int isbn = stock.getIsbn();
                int ano = stock.getAno();

                Object[] row = {id, titulo, autor, genero, subGenero, numeroEdicao, isbn, ano};
                modelo.addRow(row);

        }
        tabelaStocks.setModel(modelo);

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
