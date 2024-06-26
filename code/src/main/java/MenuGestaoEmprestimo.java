import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;


public class MenuGestaoEmprestimo extends JFrame{
    private JPanel menuGestaoEmprestimo;
    private JButton gestaoDeMultasButton;
    private JButton gestaoDeSociosButton;
    private JButton gestaoDeRequisitosButton;
    private JButton gestaoDeLivrosButton;
    private JButton fazerDevolucaoButton;
    private JButton fazerEmprestimoButton;
    private JTable tabelaEmprestimos;
    private JButton paginaIncialButton;
    private JButton gestaoDeEmprestimosButton1;
    private JTextField searchEmprestimo;

    private GestorBaseDados gestorBaseDados;

    public MenuGestaoEmprestimo(String titulo){
        super(titulo);
        gestorBaseDados = GestorBaseDados.getGestorBaseDados();
        setContentPane(menuGestaoEmprestimo);



        String[][] dataEmprestimos = new String[gestorBaseDados.getEmprestimos().size()][5];
        for (int i = 0; i < gestorBaseDados.getEmprestimos().size(); i++){
            if (gestorBaseDados.getEmprestimos().get(i).getEstadoEmprestimo() == EstadoEmprestimo.Aberto || gestorBaseDados.getEmprestimos().get(i).getEstadoEmprestimo() == EstadoEmprestimo.EmAtraso){
                int id = gestorBaseDados.getEmprestimos().get(i).getIdEmprestimo();
                String livro = gestorBaseDados.getEmprestimos().get(i).getNomeLivro();
                String socio = gestorBaseDados.getEmprestimos().get(i).getNomeSocio();
                LocalDate dataEmprestimo = gestorBaseDados.getEmprestimos().get(i).getDataEmprestimo();
                EstadoEmprestimo estadoEmprestimo = gestorBaseDados.getEmprestimos().get(i).getEstadoEmprestimo();
                LocalDate dataEntrega = gestorBaseDados.getEmprestimos().get(i).getDataDevolucao();
                dataEmprestimos[i] = new String[]{String.valueOf(id), livro, socio, String.valueOf(dataEmprestimo), String.valueOf(estadoEmprestimo), String.valueOf(dataEntrega)};

            }
        }
        tabelaEmprestimos.setModel(new DefaultTableModel(
                dataEmprestimos,
                new String[]{ "ID", "Titulo", "Nome", "Data do Emprestimo", "Estado so Emprestimo", "Data de Entrega"}
        ));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        fazerEmprestimoButton.addActionListener(this::fazerEmprestimoButtonActionPerformed);
        fazerDevolucaoButton.addActionListener(this::fazerDevolucaoButtonActionPerformed);
        paginaIncialButton.addActionListener(this::paginaIncialButtonButtonActionPerformed);
        gestaoDeLivrosButton.addActionListener(this::gestaoDeLivrosButtonActionPerformed);
        searchEmprestimo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DefaultTableModel obj = (DefaultTableModel) tabelaEmprestimos.getModel();
                TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
                tabelaEmprestimos.setRowSorter(obj1);
                obj1.setRowFilter(RowFilter.regexFilter(searchEmprestimo.getText()));
            }
        });
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

    public void paginaIncialButtonButtonActionPerformed(ActionEvent e){
        setVisible(false);
        dispose();
        MenuPrincipal menuPrincipal = new MenuPrincipal("Menu Principal");
        menuPrincipal.setVisible(true);
    }

    private void gestaoDeLivrosButtonActionPerformed(ActionEvent actionEvent){
        dispose();
        MenuGestaoLivros menuGestaoLivros = new MenuGestaoLivros("Menu Gestão de Livros");
        menuGestaoLivros.setVisible(true);
    }
}

