import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.Iterator;


public class MenuFazerDevolucao extends JFrame{
    private JButton paginaInicialButton;
    private JButton gestaoDeMultasButton;
    private JButton gestaoDeSociosButton;
    private JButton gestaoDeRequisitosButton;
    private JButton gestaoDeLivrosButton;
    private JButton gestaoDeEmprestimosButton;
    private JButton fazerEmprestimoButton;
    private JTable tableEmprestimos;
    private JButton confirmarButton;
    private JPanel menuFazerDevolucao;
    private JButton fazerDevolucaoButton1;
    private JTextField searchEmprestimo;

    private GestorBaseDados gestorBaseDados;

    public MenuFazerDevolucao(String titulo){
        super(titulo);
        gestorBaseDados = GestorBaseDados.getGestorBaseDados();
        setContentPane(menuFazerDevolucao);

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
        tableEmprestimos.setModel(model);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        confirmarButton.addActionListener(this::confirmarButtonButtonActionPerformed);
        paginaInicialButton.addActionListener(this::paginaIncialButtonButtonActionPerformed);
        gestaoDeLivrosButton.addActionListener(this::gestaoDeLivrosButtonActionPerformed);
        gestaoDeEmprestimosButton.addActionListener(this::gestaoDeEmprestimosButtonActionPerformed);
        fazerEmprestimoButton.addActionListener(this::fazerEmprestimoButtonActionPerformed);
        searchEmprestimo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DefaultTableModel obj = (DefaultTableModel) tableEmprestimos.getModel();
                TableRowSorter<DefaultTableModel> obj1 = new TableRowSorter<>(obj);
                tableEmprestimos.setRowSorter(obj1);
                obj1.setRowFilter(RowFilter.regexFilter(searchEmprestimo.getText()));            }
        });
    }

    private void confirmarButtonButtonActionPerformed(ActionEvent actionEvent){
        int id = getIDEmprestimoSelecionado();

        for (int i = 0 ; i < gestorBaseDados.getEmprestimos().size(); i++){
            if (gestorBaseDados.getEmprestimos().get(i).getIdEmprestimo() == id){
                if (gestorBaseDados.getEmprestimos().get(i).getEstadoEmprestimo() == EstadoEmprestimo.EmAtraso){
                    //aplicar multa
                    JOptionPane.showMessageDialog(null, "Tem de pagar multa!");
                }
                gestorBaseDados.getEmprestimos().get(i).getSocio().fecharEmprestimo(gestorBaseDados.getEmprestimos().get(i));
                gestorBaseDados.getEmprestimos().get(i).setEstadoEmprestimo(EstadoEmprestimo.Fechado);
                gestorBaseDados.getEmprestimos().get(i).getLivroEmprestado().setEstadoLivro(EstadoLivro.Devolvido);
            }
        }
        JOptionPane.showMessageDialog(null, "Emprestimo Fechado com sucesso!");
        setVisible(false);
        dispose();
        MenuGestaoEmprestimo menuGestaoEmprestimo = new MenuGestaoEmprestimo("Menu Gestao Emprestimos");
        menuGestaoEmprestimo.setVisible(true);
    }

    public int getIDEmprestimoSelecionado() {
        int row = tableEmprestimos.getSelectedRow();
        String[] data = new String[4];

        for (int i = 0; i < 1; i++) {
            data[i] = tableEmprestimos.getModel().getValueAt(row, i).toString();
        }
        return Integer.parseInt(data[0]);
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

    private void gestaoDeEmprestimosButtonActionPerformed(ActionEvent actionEvent){
        setVisible(false);
        dispose();
        MenuGestaoEmprestimo menuGestaoEmprestimos = new MenuGestaoEmprestimo("Menu Gestão de Emprestimos");
        menuGestaoEmprestimos.setVisible(true);
    }

    private void fazerEmprestimoButtonActionPerformed(ActionEvent actionEvent){
        setVisible(false);
        dispose();
        MenuFazerEmprestimo menuFazerEmprestimo = new MenuFazerEmprestimo("Menu Fazer Emprestimo");
        menuFazerEmprestimo.setVisible(true);
    }
}
