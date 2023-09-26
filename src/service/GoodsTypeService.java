package service;

import dao.GoodsTypeDao;
import model.GoodsType;

import java.util.List;

public class GoodsTypeService {
    private static GoodsTypeDao goodsTypeDao;

    static {
        goodsTypeDao=new GoodsTypeDao();
    }

    public static List<GoodsType> getFirstGoodsTypes() {
        return goodsTypeDao.getFirstGoodsTypes();
    }

    public static List<String> getSubTypesByType(String typeName) {
        return goodsTypeDao.getSubTypesByType(typeName);
    }
}