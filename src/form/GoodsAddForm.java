package form;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Goods;
import model.GoodsType;
import service.GoodsService;
import service.GoodsTypeService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoodsAddForm extends JDialog {
    private JTextField goodsname;
    private JTextField barcode;
    private JComboBox<String> classify1;
    private JComboBox<String> classify2;
    private JTextField inprice;
    private JTextField outprice;
    private JTextField discount;
    private JTextField innumber;
    private JButton confirm;
    private JButton cancel;
    private JLabel title1;
    private JLabel title2;
    private JLabel title3;
    private JLabel title4;
    private JLabel title5;
    private JLabel title6;
    private JLabel title7;
    private JLabel title8;
    private JPanel panel;

    public GoodsAddForm(MainForm frame) {
        super(frame);
        setTitle("NLKE专卖店销售系统--商品入库");
        setSize(600, 300);
        setContentPane(panel);
        setModalityType(ModalityType.DOCUMENT_MODAL);

        // 加载商品分类数据并初始化下拉框
        loadGoodsTypes();

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    // 创建商品对象
                    Goods goods = createGoodsObject();

                    // 在这里进行入库操作，可以调用 GoodsDao 的方法将商品信息保存到数据库中
                    GoodsService.addGoods(goods);

                    // 提示入库成功
                    JOptionPane.showMessageDialog(GoodsAddForm.this, "商品入库成功！");

                    // 清空输入框内容
                    clearInputFields();
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }

    private void loadGoodsTypes() {
        // 从 GoodsTypeService 中获取商品分类数据
        List<GoodsType> goodsTypes = GoodsTypeService.getFirstGoodsTypes();
        classify1.addItem("");
        // 初始化一级分类下拉框
        for (GoodsType goodsType : goodsTypes) {
            classify1.addItem(goodsType.getTypeName());
        }

        // 设置一级分类下拉框的监听器
        classify1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取选中的一级分类名称
                String selectedClassify1 = (String) classify1.getSelectedItem();

                // 根据一级分类名称加载对应的二级分类数据
                loadClassify2Data(selectedClassify1);
            }
        });
    }

    private void loadClassify2Data(String classify1Name) {
        // 清空二级分类下拉框
        classify2.removeAllItems();

        // 从 GoodsTypeService 中获取指定一级分类的二级分类数据
        List<String> classify2Data = GoodsTypeService.getSubTypesByType(classify1Name);

        // 初始化二级分类下拉框
        for (String classify2Item : classify2Data) {
            classify2.addItem(classify2Item);
        }
    }

    private boolean validateInput() {
        String inPriceText = inprice.getText();
        String outPriceText = outprice.getText();
        String discountText = discount.getText();
        String inNumberText = innumber.getText();

        if(barcode.getText().isEmpty()||goodsname.getText().isEmpty()||classify1.getSelectedItem().toString().isEmpty()||classify2.getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(GoodsAddForm.this, "请完善数据", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 校验进货价格
        if (!isNumeric(inPriceText)) {
            JOptionPane.showMessageDialog(GoodsAddForm.this, "进货价格必须是数字！", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 校验零售价格
        if (!isNumeric(outPriceText)) {
            JOptionPane.showMessageDialog(GoodsAddForm.this, "零售价格必须是数字！", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 校验折扣
        if (!isNumeric(discountText) || !isInRange(discountText, 0, 1)) {
            JOptionPane.showMessageDialog(GoodsAddForm.this, "折扣必须是0到1之间的数字！", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 校验入库数量
        if (!isNumeric(inNumberText) || Integer.parseInt(inNumberText) <= 0) {
            JOptionPane.showMessageDialog(GoodsAddForm.this, "入库数量必须是大于0的数字！", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    // 辅助方法：判断字符串是否是数字
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c) && c != '.') {
                return false;
            }
        }
        return true;
    }

    // 辅助方法：判断数字是否在指定范围内
    private boolean isInRange(String str, double min, double max) {
        if (!isNumeric(str)) {
            return false;
        }
        double value = Double.parseDouble(str);
        return value >= min && value <= max;
    }

    private Goods createGoodsObject() {
        String goodsName = goodsname.getText();
        String goodsBarcode = barcode.getText();
        String classify1Name = (String) classify1.getSelectedItem();
        String inPrice =inprice.getText();
        String outPrice = outprice.getText();
        String discountValue = discount.getText();
        String inNumber = innumber.getText();
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = formatter.format(currentDate);

        // 创建商品对象
        Goods goods = new Goods();
        goods.setGoodsname(goodsName);
        goods.setBarcode(goodsBarcode);
        goods.setGoodstype(classify1Name); // 修改为设置二级分类
        goods.setInprice(String.valueOf(inPrice)); // 修改为设置入价
        goods.setOutprice(String.valueOf(outPrice)); // 修改为设置出价
        goods.setDiscount(String.valueOf(discountValue));
        goods.setNumber(String.valueOf(inNumber)); // 修改为设置数量
        goods.setTime(createTime); // 修改为设置时间

        return goods;
    }

    private void clearInputFields() {
        goodsname.setText("");
        barcode.setText("");
        inprice.setText("");
        outprice.setText("");
        discount.setText("");
        innumber.setText("");
    }

    private void cancel() {
        int option = JOptionPane.showConfirmDialog(GoodsAddForm.this,
                "确定要取消添加商品吗？", "取消", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            clearInputFields();
            dispose();
        }
    }
}