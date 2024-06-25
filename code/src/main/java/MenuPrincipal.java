import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame{
    private JButton gestaoDeMultasButton;
    private JButton gestaoDeSociosButton;
    private JButton gestaoDeEmprestimosButton;
    private JButton gestaoDeLivrosButton;
    private JButton gestaoDeRequisitosButton;
    private JButton paginaInicialButton;
    private JPanel menuPrincipal;

    private GestorBaseDados gestorBaseDados;

    public MenuPrincipal(String titulo){
        super(titulo);

        gestorBaseDados = GestorBaseDados.getGestorBaseDados();

        Socio socio3 = new Socio("Manel", 123, "Casa", 345,"Jaaa@");
        Fornecedor fornecedor1 = new Fornecedor("luis",456,"lisboa",933333333,"email@teste.pt");
        gestorBaseDados.addFornecedor(fornecedor1);
        gestorBaseDados.addSocio(socio3);
        gestorBaseDados.criarStockeLivros("Cinderela", "Joaquim", Genero.tecnico, SubGenero.informatica, 213, 345, 1222,3);
        gestorBaseDados.criarStockeLivros("O autocarro","Luis",Genero.ficcao,SubGenero.terror,333,476,2024,2);
        gestorBaseDados.criarStockeLivros("Nabos","Esmeralda",Genero.ficcao,SubGenero.suspense,546,874,1880,4);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(menuPrincipal);
        setMinimumSize(new Dimension(900, 600));
        pack();

        gestaoDeEmprestimosButton.addActionListener(this::gestaoDeEmprestimosButtonActionPerformed);
        gestaoDeLivrosButton.addActionListener(this::gestaoDeLivrosButtonActionPerformed);
    }

    private void gestaoDeEmprestimosButtonActionPerformed(ActionEvent actionEvent){
        MenuGestaoEmprestimo menuGestaoEmprestimos = new MenuGestaoEmprestimo("Menu Gestão de Emprestimos");
        menuGestaoEmprestimos.setVisible(true);
    }

    private void gestaoDeLivrosButtonActionPerformed(ActionEvent actionEvent){
        dispose();
        MenuGestaoLivros menuGestaoLivros = new MenuGestaoLivros("Menu Gestão de Livros");
        menuGestaoLivros.setVisible(true);
    }

    public static void main(String[] args) {
        new MenuPrincipal("Menu Principal").setVisible(true);
    }
}


