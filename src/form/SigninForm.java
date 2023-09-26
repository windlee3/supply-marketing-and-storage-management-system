package form;

import model.User;
import service.LoginService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SigninForm extends JDialog {
    private JPanel panel;
    private JTextField password;
    private JTextField username;
    private JLabel title1;
    private JLabel title2;
    private JButton signin;
    private JButton cancel;

    public SigninForm(JFrame frame) {
        super(frame);
        setTitle("NIKE专卖店销售系统--注册");
        setSize(400, 200);
        setContentPane(panel);
        setModalityType(ModalityType.DOCUMENT_MODAL);

        signin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneID = username.getText();
                String Password = password.getText();

                User user = new User(phoneID, Password);
                if (phoneID.isEmpty()||Password.isEmpty()) {
                    JOptionPane.showMessageDialog(SigninForm.this, "注册失败！请完善用户名或密码");
                } else {
                    LoginService.register(user);

                    JOptionPane.showMessageDialog(SigninForm.this, "注册成功！");
                    cancelRegistration();
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelRegistration();
            }
        });
    }

    private void cancelRegistration() {
        // 清空输入框内容
        username.setText("");
        password.setText("");
        // 关闭对话框
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}