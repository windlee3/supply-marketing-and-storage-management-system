package service;

import dao.GoodsDao;
import model.Goods;

import java.util.List;

public class GoodsService {
    private static GoodsDao goodsDao;

    static {
        goodsDao = new GoodsDao();
    }

    public static void addGoods(Goods goods) {
        // 调用GoodsDao添加商品
        goodsDao.addGoods(goods);
    }

    public static List<Goods> searchGoods(String barcode, String goodsName, String goodsType, String startDate, String endDate) {
        return goodsDao.searchGoods(barcode, goodsName, goodsType,startDate,endDate);
    }

   /* public void updateGoods(Goods goods) {
        // 调用GoodsDao更新商品信息
        goodsDao.updateGoods(goods);
    }

    public void deleteGoods(String barcode) {
        // 调用GoodsDao删除商品
        goodsDao.deleteGoods(barcode);
    }
*/
}

