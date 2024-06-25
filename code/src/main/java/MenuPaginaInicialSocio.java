import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuPaginaInicialSocio extends JFrame{
    private JButton editoraButton;
    private JButton tituloButton;
    private JButton subGeneroButton;
    private JButton generoButton;
    private JButton autorButton;
    private JTextField textField1;
    private JTable tabelaStocks;
    private JPanel menuPaginaInicialSocio;
    private JButton verDetalhesButton;

    private GestorBaseDados gestorBaseDados;


    public MenuPaginaInicialSocio(String title){
        super(title);

        gestorBaseDados = GestorBaseDados.getGestorBaseDados();
        gestorBaseDados.criarStockeLivros("Cinderela", "Joaquim", Genero.tecnico, SubGenero.informatica, 213, 345, 1222,7);
        gestorBaseDados.criarStockeLivros("Shrek", "JJ", Genero.ficcao, SubGenero.romance, 213, 345, 1222,7);


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


            dataLivros[i] = new String[]{titulo, autor, String.valueOf(genero), String.valueOf(subGenero), String.valueOf(numeroEdicao), String.valueOf(isbn), String.valueOf(ano), String.valueOf(id)};
        }
        tabelaStocks.setModel(new DefaultTableModel(
                dataLivros,
                new String[]{ "Titulo", "Autor", "Genero", "SubGenero", "Numero de Edicao", "ISBN", "Ano", "ID"}
        ));


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(menuPaginaInicialSocio);

        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(600, 400));
        pack();

        verDetalhesButton.addActionListener(this::verDetalhesButtonActionPerformed);
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
        return Integer.parseInt(data[7]);
    }

    public void errorInvalidTableIndex() {
        JOptionPane.showMessageDialog(null, "Tem de selecionar uma opcao em cada tabela!");
    }

    public void close(int stock) {
        setVisible(false);
        dispose();

        MenuDetalhesLivro menuDetalhesLivro = new MenuDetalhesLivro("Menu Detalhes do Livro", stock-1);
        menuDetalhesLivro.setVisible(true);
    }

    public static void main(String[] args) {
        new MenuPaginaInicialSocio("Menu Principal").setVisible(true);
    }
}
