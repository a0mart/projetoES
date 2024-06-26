import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

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
        gestaoDeSociosButton.addActionListener(this::gestaoDeSociosButtonActionPerformed);
        gestaoDeRequisitosButton.addActionListener(this::gestaoDeRequisitosButtonActionPerformed);
    }

    private void gestaoDeRequisitosButtonActionPerformed(ActionEvent actionEvent) {
        dispose();
        ReservaForm reservaForm = new ReservaForm("Menu Gestão de Reservas");
        reservaForm.setVisible(true);
    }

    private void gestaoDeSociosButtonActionPerformed(ActionEvent actionEvent) {
        dispose();
        MenuGestaoSocios menuGestaoSocios = new MenuGestaoSocios("Menu Gestão de Sócios");
        menuGestaoSocios.setVisible(true);
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


