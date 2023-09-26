package dao;

import model.GoodsType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import util.WorkbookConnection;

import java.util.ArrayList;
import java.util.List;

public class GoodsTypeDao {
    private Sheet GoodsTypeSheet;

    public GoodsTypeDao() {
        GoodsTypeSheet = WorkbookConnection.getWorkbook().getSheet("GoodsType");
    }

    //返回一个包含第一级类的列表
    public List<GoodsType> getFirstGoodsTypes() {
        List<GoodsType> goodsTypes = new ArrayList<>();
        for (Row row : GoodsTypeSheet) {
            Integer typeIdInteger = getCellIntegerValue(row.getCell(0));
            Integer parentId = getCellIntegerValue(row.getCell(2));// 使用 getCellIntegerValue() 方法获取类别ID
            if (typeIdInteger != null && parentId == null) {
                int typeId = typeIdInteger; // 进行判空处理
                String typeName = row.getCell(1).getStringCellValue();
                GoodsType goodsType = new GoodsType(typeId, typeName, null);
                goodsTypes.add(goodsType);
            }
        }
        return goodsTypes;
    }

    //获取单元格的整数值，可以处理单元格数据类型为数字类型或字符串类型的情况
    private Integer getCellIntegerValue(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            String cellValue = cell.getStringCellValue();
            if (cellValue.isEmpty()) {
                return null;
            }
            try {
                return Integer.valueOf(cellValue);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    //获取指定类型的自身信息
    private GoodsType getGoodsTypeByName(String typeName) {
        for (Row row : GoodsTypeSheet) {
            String currentTypeName = row.getCell(1).getStringCellValue();
            if (currentTypeName.equals(typeName)) {
                Integer typeIdInteger = getCellIntegerValue(row.getCell(0)); // 使用 getCellIntegerValue() 方法获取类别ID
                if (typeIdInteger != null) {
                    int typeId = typeIdInteger; // 进行判空处理
                    Integer parentId = getCellIntegerValue(row.getCell(2));
                    return new GoodsType(typeId, typeName, parentId);
                }
            }
        }
        return null;
    }

    //根据自身的类别名称，获取该类别的所有子类别
    public List<String> getSubTypesByType(String typeName) {
        List<String> subTypes = new ArrayList<>();
        GoodsType parentType = getGoodsTypeByName(typeName);//获取了typeName的类别ID，商品类型和父类别ID
        if (parentType != null) {
            for (Row row : GoodsTypeSheet) {
                Integer rowParentId = getCellIntegerValue(row.getCell(2));
                if (rowParentId != null && rowParentId.equals(parentType.getTypeId())) {
                    String subType = row.getCell(1).getStringCellValue();
                    subTypes.add(subType);
                }
            }
        }
        return subTypes;
    }
}