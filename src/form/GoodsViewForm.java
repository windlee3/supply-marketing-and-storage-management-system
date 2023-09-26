package form;

import model.Goods;
import model.GoodsType;
import service.GoodsService;
import service.GoodsTypeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GoodsViewForm extends JDialog {
    private int totalResults;
    private JPanel panel;
    private JTextField goodsname;
    private JTextField Barcode;
    private JTextField time1;
    private JTextField time2;
    private JComboBox<String> classify1;
    private JComboBox<String> classify2;
    private JButton search;
    private JTable table1;
    private JLabel title1;
    private JLabel title2;
    private JLabel title3;
    private JLabel title4;
    private JLabel title5;
    private JLabel title6;
    private JLabel title7;

    private DefaultTableModel tableModel;
    public GoodsViewForm(MainForm frame) {
        super(frame);
        setTitle("NIKE专卖店销售系统--商品浏览");
        setSize(800, 500);
        setContentPane(panel);
        setModalityType(ModalityType.DOCUMENT_MODAL);

        loadGoodsTypes();

        Object[] columnNames = new Object[]{"货号", "商品名称", "商品分类", "进货价格", "零售价格", "折扣", "数量", "时间"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table1.setModel(tableModel);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String barcode = Barcode.getText();
                String goodsName = goodsname.getText();
                String goodsType = classify1.getSelectedItem().toString();
                String starttime = time1.getText();
                String endtime=time2.getText();
                // 调用GoodsDao的searchGoods方法查询货物
                List<Goods> goodsList = GoodsService.searchGoods(barcode, goodsName, goodsType,starttime,endtime);
                // 刷新JTable中的数据
                refreshTable(goodsList);
                title7.setText("当前共"+getTotalResults()+"条商品信息！");
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

    private void refreshTable(List<Goods> goodsList) {
        tableModel.setRowCount(0); // 清空JTable中的数据
        for (Goods goods : goodsList) {
            // 创建一行数据
            Object[] rowData = new Object[]{
                    goods.getBarcode(),
                    goods.getGoodsname(),
                    goods.getGoodstype(),
                    goods.getInprice(),
                    goods.getOutprice(),
                    goods.getDiscount(),
                    goods.getNumber(),
                    goods.getTime()
            };
            // 添加数据到JTable
            tableModel.addRow(rowData);
        }
        totalResults = goodsList.size();
    }
    public String getTotalResults() {
        return String.valueOf(totalResults);
    }
}