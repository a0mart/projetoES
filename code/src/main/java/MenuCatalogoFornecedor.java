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
    private int id;
    private List<Livro> catalogo;

    public MenuCatalogoFornecedor(String title, int id, List<Livro> catalogo) throws HeadlessException {
        super(title);
        this.id = id;
        this.catalogo = catalogo;
        table1.setModel(new DefaultTableModel(catalogo, new String[]{ "Titulo", "Autor", "Genero", "SubGenero", "Numero de Edicao", "ISBN", "Ano", "ID"}));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(menuCatalogoFornecedor);
        setMinimumSize(new Dimension(900, 600));
        pack();
        /** Listeners */
        voltarButton.addActionListener(this::btnvoltarActionPerformed);
    }
    public void btnvoltarActionPerformed(ActionEvent e) {
        dispose();
        MenuDetalhesFornecedor menuDetalhesFornecedor = new MenuDetalhesFornecedor("Menu Destalhes do fornecedor",id-1);
        menuDetalhesFornecedor.setVisible(true);
    }
}
