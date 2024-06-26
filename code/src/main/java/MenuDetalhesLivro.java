import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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

    private int menuPrincipal;

    private int stock;


    public MenuDetalhesLivro(String titulo, int stock, int menuPrincipal){
        super(titulo);

        gestorBaseDados = GestorBaseDados.getGestorBaseDados();
        this.stock = stock;
        this.menuPrincipal= menuPrincipal;

        setContentPane(menuDetalhesLivro);

        labelTitulo.setText(gestorBaseDados.getStocks().get(stock).getTitulo());
        labelAutor.setText(gestorBaseDados.getStocks().get(stock).getAutor());
        labelGenero.setText(String.valueOf(gestorBaseDados.getStocks().get(stock).getGenero()));
        labelSubGenero.setText(String.valueOf(gestorBaseDados.getStocks().get(stock).getSubGenero()));
        //labelEditora.setText(stock.getEditora);
        labelEdicao.setText(String.valueOf(gestorBaseDados.getStocks().get(stock).getNumeroEdicao()));
        labelano.setText(String.valueOf(gestorBaseDados.getStocks().get(stock).getAno()));
        labelISBN.setText(String.valueOf(gestorBaseDados.getStocks().get(stock).getIsbn()));
        labelStock.setText(String.valueOf(gestorBaseDados.getStocks().get(stock).getQuantidadeLivrosDisponivel()));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        paginaInicialButton.addActionListener(this::paginaIncialButtonButtonActionPerformed);

    }

    public void paginaIncialButtonButtonActionPerformed(ActionEvent e){
        setVisible(false);
        dispose();
        if (menuPrincipal == 1){
            MenuPrincipal menuPrincipal = new MenuPrincipal("Menu Principal");
            menuPrincipal.setVisible(true);
        }else {
            MenuPaginaInicialSocio menuPaginaInicialSocio = new MenuPaginaInicialSocio("Menu Principal");
            menuPaginaInicialSocio.setVisible(true);
        }

    }
}
