package form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainForm extends JDialog {

    private JPanel panel1;
    private JLabel title1;
    private JLabel title2;
    private JButton cancel;
    private JLabel imageLable;

    public MainForm(JFrame frame,String username) {

        super(frame);

        // 菜单栏
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("进销存管理(F)");
        // 设置助记符为F，按下ALT + F 可以触发该菜单
        file.setMnemonic('F');

        JMenuItem open = new JMenuItem("商品入库");
        JMenuItem scan = new JMenuItem("商品浏览");
        JMenuItem money = new JMenuItem("收银台");

        file.add(open);
        // 设置菜单分隔符
        file.addSeparator();
        file.add(scan);
        file.addSeparator();
        file.add(money);


        menuBar.add(file);
        // 设置菜单栏，使用这种方式设置菜单栏可以不占用布局空间
        setJMenuBar(menuBar);
        setContentPane(panel1);
        updateTime2();
        run();
        setUsername(username);
        setTitle("NLKE专卖店销售系统--首页");
        setSize(520, 400);
        setLocationRelativeTo(null);
        setModalityType(ModalityType.DOCUMENT_MODAL);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(MainForm.this,
                        "确定要退出主界面吗？", "取消", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                }
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
            }
        });

        GoodsAddForm goodsAddForm = new GoodsAddForm(MainForm.this);
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodsAddForm.setVisible(true);
            }
        });

        GoodsViewForm goodsViewForm = new GoodsViewForm(MainForm.this);
        scan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodsViewForm.setVisible(true);
            }
        });

        CashRegisterForm cashRegisterForm=new CashRegisterForm(MainForm.this);
        money.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cashRegisterForm.setVisible(true);
            }
        });
        // 加载并显示图片
        ImageIcon imageIcon = new ImageIcon("OIP.jpg");
        Image image =   imageIcon.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon scaledImageIcon = new ImageIcon(image);
        imageLable.setIcon(scaledImageIcon);
    }

    public void updateTime() {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = dateFormat.format(new Date());
                title2.setText(currentTime);
            }
        };
        new javax.swing.Timer(1000, taskPerformer).start();
    }
    public void updateTime2() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());
        title2.setText(currentTime);
    }
    public void run() {
        updateTime();
    }

    public void setUsername(String username){
        title1.setText(username+"(店长)，欢迎您！");
    }
}