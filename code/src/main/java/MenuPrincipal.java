import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame{
    private JButton gestaoDeMultasButton;
    private JButton gestaoDeSociosButton;
    private JButton gestaoDeEmprestimosButton;
    private JButton gestaoDeLivrosButton;
    private JButton gestaoDeRequisitosButton;
    private JPanel menuPrincipal;
    private JButton paginaInicialButton1;

    private GestorBaseDados gestorBaseDados;

    public MenuPrincipal(String titulo){
        super(titulo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(menuPrincipal);
        setMinimumSize(new Dimension(900, 600));
        pack();

        /** Listeners */
        gestaoDeEmprestimosButton.addActionListener(this::gestaoDeEmprestimosButtonActionPerformed);
        gestaoDeLivrosButton.addActionListener(this::gestaoDeLivrosButtonActionPerformed);
    }

    private void gestaoDeEmprestimosButtonActionPerformed(ActionEvent actionEvent){
        dispose();
        MenuGestaoEmprestimo menuGestaoEmprestimos = new MenuGestaoEmprestimo("Menu Gestão de Emprestimos");
        menuGestaoEmprestimos.setVisible(true);
    }

    private void gestaoDeLivrosButtonActionPerformed(ActionEvent actionEvent){
        dispose();
        MenuGestaoLivros menuGestaoLivros = new MenuGestaoLivros("Menu Gestão de Livros");
        menuGestaoLivros.setVisible(true);
    }
}


