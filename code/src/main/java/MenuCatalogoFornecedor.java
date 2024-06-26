import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MenuCatalogoFornecedor extends JFrame{
    private JPanel menuCatalogoFornecedor;
    private JButton voltarButton;
    private JTable table1;
    private GestorBaseDados gestorBaseDados;
    private List<Livro> catalogo;

    public MenuCatalogoFornecedor(String title, List<Livro> catalogo) throws HeadlessException {
        super(title);
        this.catalogo = catalogo;

        String[] colunas = {"Título", "Autor"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        // Adiciona dados dos livros ao modelo da tabela
        for (Livro livro : catalogo) {
            String titulo = livro.getTitulo();
            String autor = livro.getAutor();

            Object[] row = {titulo, autor};
            model.addRow(row);
        }

        table1.setModel(model);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(menuCatalogoFornecedor);
        setMinimumSize(new Dimension(900, 600));
        pack();
        /** Listeners */
        voltarButton.addActionListener(this::btnvoltarActionPerformed);
    }
    public void btnvoltarActionPerformed(ActionEvent e) {
        dispose();
    }
}
