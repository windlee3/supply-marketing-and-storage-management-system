package form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashRegisterForm extends JDialog {
    private JPanel panel1;
    private JButton increase;
    private JButton decrease;
    private JComboBox name;
    private JTable table1;
    private JLabel title1;
    private JLabel title2;
    private JLabel title3;
    private JLabel title4;
    private JTextField Receivables;
    private JTextField Paid;
    private JTextField change;
    private JButton calculate;
    private JLabel title9;
    private JLabel title7;
    private JLabel title8;
    private JLabel title5;
    private JLabel title6;
    private JLabel title10;
    private JTextField barcode;

    public CashRegisterForm(MainForm frame) {
        super(frame);
        setTitle("NIKE专卖店销售系统--收银台");
        setSize(600, 400);
        setContentPane(panel1);
        setModalityType(ModalityType.DOCUMENT_MODAL);


        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 结算功能
                float paid = Integer.parseInt(Paid.getText());
                float receivables = Integer.parseInt(Receivables.getText());
                float Change = paid - receivables;
                change.setText(String.valueOf(Change));
            }
        });

        increase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //增加库存

            }
        });

        decrease.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //减少库存
            }
        });
    }
}