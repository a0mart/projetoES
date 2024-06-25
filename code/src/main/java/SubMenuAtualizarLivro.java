import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SubMenuAtualizarLivro extends JFrame{
    private JPanel subMenuAtualizar;
    private JComboBox comboBox1;
    private JButton confirmarButton;

    private GestorBaseDados gestorBaseDados;

    private int idLivro;

    public SubMenuAtualizarLivro(String title, int idLivro){
        super(title);
        this.idLivro = idLivro;

        setContentPane(subMenuAtualizar);

        gestorBaseDados = GestorBaseDados.getGestorBaseDados();

        pack();

        confirmarButton.addActionListener(this::confirmarButtonActionPerformed);
    }

    public void confirmarButtonActionPerformed(ActionEvent e){
        if (comboBox1.getSelectedItem().toString() == "Disponivel"){
            gestorBaseDados.getLivros().get(idLivro).setEstadoLivro(EstadoLivro.Disponivel);
        }else{
            gestorBaseDados.getLivros().get(idLivro).setEstadoLivro(EstadoLivro.Indisponivel);
        }
        setVisible(false);
        dispose();
        MenuAtualizarLivro menuAtualizarLivro = new MenuAtualizarLivro("Menu Atualizar livro");
        menuAtualizarLivro.setVisible(true);
    }
}
