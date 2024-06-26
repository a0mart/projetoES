import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.Iterator;


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



        Iterator<Emprestimo> emprestimoIterator = gestorBaseDados.getEmprestimos().iterator();

        String[] colunas = { "ID", "Titulo", "Nome", "Data de Emprestimo", "Data de Entrega", "Estado"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        while (emprestimoIterator.hasNext()) {
            Emprestimo emprestimo = emprestimoIterator.next();
            if (emprestimo.getEstadoEmprestimo() == EstadoEmprestimo.Aberto || emprestimo.getEstadoEmprestimo() == EstadoEmprestimo.EmAtraso) {
                int id = emprestimo.getIdEmprestimo();
                String livro = emprestimo.getNomeLivro();
                String socio = emprestimo.getNomeSocio();
                LocalDate dataEmprestimo = emprestimo.getDataEmprestimo();
                LocalDate dataEntrega = emprestimo.getDataDevolucao();
                EstadoEmprestimo estadoEmprestimo = emprestimo.getEstadoEmprestimo();

                Object[] row = {id, livro, socio, dataEmprestimo, dataEntrega, estadoEmprestimo};
                model.addRow(row);
            }
        }
        tabelaEmprestimos.setModel(model);

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

