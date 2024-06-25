import javax.swing.*;
import java.awt.*;

public class MenuDetalhesLivro extends JFrame{
    private JPanel menuDetalhesLivro;
    private JButton paginaInicialButton;
    private JButton reservarButton;
    private JButton estatisticasButton;
    private JButton tituloButton;
    private JButton generoButton;
    private JButton subGeneroButton;
    private JButton autorButton;
    private JButton editoraButton;
    private JLabel labelTitulo;
    private JLabel labelGenero;
    private JLabel labelSubGenero;
    private JLabel labelEditora;
    private JLabel labelStock;
    private JLabel labelEdicao;
    private JLabel labelano;
    private JLabel labelAutor;
    private JLabel labelISBN;

    private GestorBaseDados gestorBaseDados;

    private int stock;


    public MenuDetalhesLivro(String titulo, int stock){
        super(titulo);

        gestorBaseDados = GestorBaseDados.getGestorBaseDados();
        this.stock = stock;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(menuDetalhesLivro);

        labelTitulo.setText(gestorBaseDados.getStocks().get(stock).getTitulo());
        labelAutor.setText(gestorBaseDados.getStocks().get(stock).getAutor());
        labelGenero.setText(String.valueOf(gestorBaseDados.getStocks().get(stock).getGenero()));
        labelSubGenero.setText(String.valueOf(gestorBaseDados.getStocks().get(stock).getSubGenero()));
        //labelEditora.setText(stock.getEditora);
        labelEdicao.setText(String.valueOf(gestorBaseDados.getStocks().get(stock).getNumeroEdicao()));
        labelEdicao.setText(String.valueOf(gestorBaseDados.getStocks().get(stock).getAno()));
        labelISBN.setText(String.valueOf(gestorBaseDados.getStocks().get(stock).getIsbn()));
        labelStock.setText(String.valueOf(gestorBaseDados.getStocks().get(stock).getQuantidadeLivrosDisponivel()));

        setMinimumSize(new Dimension(900, 600));
        pack();

    }
}
