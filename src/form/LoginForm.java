package form;

import service.LoginService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField username;
    private JPasswordField password;
    private JLabel title1;
    private JLabel title2;
    private JButton login;
    private JButton signin;
    private JPanel panel;


    public LoginForm() {
        setTitle("NIKE专卖店销售系统--登录");
        setContentPane(panel);
        setSize(300, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        SigninForm signinForm = new SigninForm(LoginForm.this);

        signin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signinForm.setVisible(true);
            }
        });


        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameText = username.getText();
                String passwordText = new String(password.getPassword());

                boolean loggedIn = LoginService.login(usernameText, passwordText);
                if (loggedIn) {
                    dispose();
                    MainForm mainForm = new MainForm(LoginForm.this,usernameText);
                    mainForm.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "用户名或密码错误！");
                }
            }
        });
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
    }
}