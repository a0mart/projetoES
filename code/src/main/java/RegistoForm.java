import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegistoForm extends JFrame{
    private JPanel registo;
    private JLabel nome;
    private JLabel email;
    private JButton efetuarRegistoButton;
    private JLabel pass;
    private JLabel confpass;
    private JLabel morada;
    private JLabel nif;
    private JLabel cc;
    private JLabel contacto;
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField moradaField;
    private JTextField nifField;
    private JTextField ccField;
    private JTextField contacoField;


    public RegistoForm(String titulo){
        super(titulo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(registo);
        setMinimumSize(new Dimension(600, 300));
        pack();

        /** Listeners */
        efetuarRegistoButton.addActionListener(this::efetuarRegistoButtonActionPerformed);
    }

    private void efetuarRegistoButtonActionPerformed(ActionEvent actionEvent) {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String pass1 = passwordField1.getText();
        String pass2 = passwordField2.getText();
        String morada = moradaField.getText();
        String nif = nifField.getText();
        String cc = ccField.getText();
        String contacto = contacoField.getText();



        if(nome.equals("") || email.equals("") || pass1.equals("") || pass2.equals("") || morada.equals("") || nif.equals("") || cc.equals("") || contacto.equals("")){
            JOptionPane.showMessageDialog(this, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
        } else{
            if (nif.length() != 9 || cc.length() != 8){
                JOptionPane.showMessageDialog(this, "NIF ou CC incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
            }else if(email.contains("@") == false){
                JOptionPane.showMessageDialog(this, "Email incorreto", "Erro", JOptionPane.ERROR_MESSAGE);
            }else if(pass1.equals(pass2)){
                Socio socio = new Socio(nome, Integer.parseInt(nif), morada, Integer.parseInt(cc), email);
                GestorBaseDados gestorBaseDados = GestorBaseDados.getGestorBaseDados();
                gestorBaseDados.addSocio(socio);
                dispose();

                MenuPrincipal menuPrincipal = new MenuPrincipal("Menu Principal");
                menuPrincipal.setVisible(true);

                //enviar mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Registo efetuado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "As passwords não coincidem", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }


    }
}
