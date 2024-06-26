import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class LoginForm extends JFrame {
    private JLabel email;
    private JTextField emailField;
    private JTextField passField;
    private JLabel pass;
    private JButton entrarButton;
    private JButton registarButton;
    private JPanel loginForm;
    public LoginForm(String titulo){
        super(titulo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(loginForm);
        setMinimumSize(new Dimension(600, 300));
        pack();

        /** Listeners */
        entrarButton.addActionListener(this::entrarButtonActionPerformed);
        registarButton.addActionListener(this::registarButtonActionPerformed);
    }

    private void entrarButtonActionPerformed(ActionEvent actionEvent) {
        String email = emailField.getText();
        String pass = passField.getText();


        for (Funcionario f:GestorBaseDados.getGestorBaseDados().getFuncionarios()) {
            if (f.getEmail().equals(email) && f.getPassword().equals(pass)){
                dispose();
                MenuPrincipal menuPrincipal = new MenuPrincipal("Menu Principal");
                menuPrincipal.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(this, "Email ou password incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void registarButtonActionPerformed(ActionEvent actionEvent){
            dispose();
            RegistoForm registoForm = new RegistoForm("Registo");
            registoForm.setVisible(true);

    }


}
