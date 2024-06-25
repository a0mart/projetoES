import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Date;


public class MenuFazerDevolucao extends JFrame{
    private JButton paginaInicialButton;
    private JButton gestaoDeMultasButton;
    private JButton gestaoDeSociosButton;
    private JButton fazerDevolucaoButton;
    private JButton gestaoDeRequisitosButton;
    private JButton gestaoDeLivrosButton;
    private JButton gestaoDeEmprestimosButton;
    private JButton fazerEmprestimoButton;
    private JTable tableEmprestimos;
    private JButton confirmarButton;
    private JPanel menuFazerDevolucao;

    private GestorBaseDados gestorBaseDados;

    public MenuFazerDevolucao(String titulo){
        super(titulo);

        setContentPane(menuFazerDevolucao);

        gestorBaseDados = GestorBaseDados.getGestorBaseDados();

        String[][] dataEmprestimos = new String[gestorBaseDados.getEmprestimos().size()][6];
        for (int i = 0; i < gestorBaseDados.getEmprestimos().size(); i++){
            if (gestorBaseDados.getEmprestimos().get(i).getEstadoEmprestimo() == EstadoEmprestimo.Aberto){
                int id = gestorBaseDados.getEmprestimos().get(i).getIdEmprestimo();
                String livro = gestorBaseDados.getEmprestimos().get(i).getNomeLivro();
                String socio = gestorBaseDados.getEmprestimos().get(i).getNomeSocio();
                LocalDate dataEmprestimo = gestorBaseDados.getEmprestimos().get(i).getDataEmprestimo();
                LocalDate dataEntrega = gestorBaseDados.getEmprestimos().get(i).getDataDevolucao();
                EstadoEmprestimo estadoEmprestimo = gestorBaseDados.getEmprestimos().get(i).getEstadoEmprestimo();

                dataEmprestimos[i] = new String[]{String.valueOf(id), livro, socio, String.valueOf(dataEmprestimo), String.valueOf(dataEntrega), String.valueOf(estadoEmprestimo)};
            }
        }
        tableEmprestimos.setModel(new DefaultTableModel(
                dataEmprestimos,
                new String[]{ "ID", "Titulo", "Nome", "Data de Emprestimo", "Data de Entrega"}
        ));

        setMinimumSize(new Dimension(900, 600));
        pack();

        confirmarButton.addActionListener(this::confirmarButtonButtonActionPerformed);
        paginaInicialButton.addActionListener(this::paginaIncialButtonButtonActionPerformed);
        gestaoDeLivrosButton.addActionListener(this::gestaoDeLivrosButtonActionPerformed);
        gestaoDeEmprestimosButton.addActionListener(this::gestaoDeEmprestimosButtonActionPerformed);
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
}
