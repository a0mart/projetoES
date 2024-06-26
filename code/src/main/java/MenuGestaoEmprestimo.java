import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Date;


public class MenuGestaoEmprestimo extends JFrame{
    private JPanel menuGestaoEmprestimo;
    private JButton gestaoDeMultasButton;
    private JButton gestaoDeSociosButton;
    private JButton gestaoDeRequisitosButton;
    private JButton gestaoDeLivrosButton;
    private JButton fazerDevolucaoButton;
    private JButton gestaoDeEmprestimosButton;
    private JButton fazerEmprestimoButton;
    private JTable table1;
    private JButton paginaIncialButton;

    private GestorBaseDados gestorBaseDados;

    public MenuGestaoEmprestimo(String titulo){
        super(titulo);

        setContentPane(menuGestaoEmprestimo);

        gestorBaseDados = GestorBaseDados.getGestorBaseDados();

        String[][] dataEmprestimos = new String[gestorBaseDados.getEmprestimos().size()][5];
        for (int i = 0; i < gestorBaseDados.getEmprestimos().size(); i++){
            int id = gestorBaseDados.getEmprestimos().get(i).getIdEmprestimo();
            String livro = gestorBaseDados.getEmprestimos().get(i).getNomeLivro();
            String socio = gestorBaseDados.getEmprestimos().get(i).getNomeSocio();
            LocalDate dataEmprestimo = gestorBaseDados.getEmprestimos().get(i).getDataEmprestimo();
            EstadoEmprestimo estadoEmprestimo = gestorBaseDados.getEmprestimos().get(i).getEstadoEmprestimo();
            LocalDate dataEntrega = gestorBaseDados.getEmprestimos().get(i).getDataDevolucao();
            dataEmprestimos[i] = new String[]{String.valueOf(id), livro, socio, String.valueOf(dataEmprestimo), String.valueOf(estadoEmprestimo), String.valueOf(dataEntrega)};
        }
        table1.setModel(new DefaultTableModel(
                dataEmprestimos,
                new String[]{ "ID", "Titulo", "Nome", "Data do Emprestimo", "Estado so Emprestimo", "Data de Entrega"}
        ));


        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900, 600));
        pack();

        fazerEmprestimoButton.addActionListener(this::fazerEmprestimoButtonActionPerformed);
        fazerDevolucaoButton.addActionListener(this::fazerDevolucaoButtonActionPerformed);
    }



    private void fazerEmprestimoButtonActionPerformed(ActionEvent actionEvent){
        setVisible(false);
        dispose();
        MenuFazerEmprestimo menuFazerEmprestimo = new MenuFazerEmprestimo("Menu Fazer Emprestimo");
        menuFazerEmprestimo.setVisible(true);
    }

    private void fazerDevolucaoButtonActionPerformed(ActionEvent actionEvent){
        setVisible(false);
        dispose();
        MenuFazerDevolucao menuFazerDevolucao = new MenuFazerDevolucao("Menu Fazer Devolucao");
        menuFazerDevolucao.setVisible(true);
    }
}

