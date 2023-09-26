package dao;

import model.Goods;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import util.WorkbookConnection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsDao {
    int totalResults;
    private Sheet GoodsSheet;

    public GoodsDao() {
        GoodsSheet = WorkbookConnection.getWorkbook().getSheet("Goods");
    }

    public void addGoods(Goods goods) {
        // 在商品表中添加新的商品数据
        Row newRow = GoodsSheet.createRow(GoodsSheet.getLastRowNum() + 1);
        newRow.createCell(0).setCellValue(goods.getBarcode());
        newRow.createCell(1).setCellValue(goods.getGoodsname());
        newRow.createCell(2).setCellValue(goods.getGoodstype());
        newRow.createCell(3).setCellValue(goods.getInprice());
        newRow.createCell(4).setCellValue(goods.getOutprice());
        newRow.createCell(5).setCellValue(goods.getDiscount());
        newRow.createCell(6).setCellValue(goods.getNumber());
        newRow.createCell(7).setCellValue(goods.getTime());
        // 保存工作簿
        WorkbookConnection.saveWorkbook();
    }

    // 添加searchGoods方法
    public List<Goods> searchGoods(String barcode, String goodsName, String goodsType, String startDate, String endDate) {
        List<Goods> resultList = new ArrayList<>();

        boolean isFirstRow = true; // 标记是否为标题行

        for (Row row : GoodsSheet) {

            if (isFirstRow) {
                isFirstRow = false;
                continue; // 跳过标题行
            }

            String rowBarcode = row.getCell(0).getStringCellValue();
            String rowGoodsName = row.getCell(1).getStringCellValue();
            String rowGoodsType = row.getCell(2).getStringCellValue();
            String time = row.getCell(7).getStringCellValue();

            // 判断是否符合查询条件
            if (barcode.isEmpty() || rowBarcode.equals(barcode)) {
                if (goodsName.isEmpty() || rowGoodsName.equals(goodsName)) {
                    if (goodsType.isEmpty() || rowGoodsType.equals(goodsType)) {
                        if (time.isEmpty() || (startDate.isEmpty() && endDate.isEmpty()) || isWithinDateRange(time, startDate, endDate)) {

                            // 符合条件的货物，将其添加到结果列表中
                            String inPrice = String.valueOf(getCellIntegerValue(row.getCell(3)));
                            String outPrice = String.valueOf(getCellIntegerValue(row.getCell(4)));
                            String discount = row.getCell(5).getStringCellValue();
                            String number = String.valueOf(getCellIntegerValue(row.getCell(6)));

                            Goods goods = new Goods(rowBarcode, rowGoodsName, rowGoodsType, inPrice, outPrice, discount, number, time);
                            resultList.add(goods);
                        }
                    }
                }
            }
        }
        totalResults = resultList.size();
        return resultList;
    }


    // 判断时间是否在指定的日期范围内
    private boolean isWithinDateRange(String date, String startDate, String endDate) {
        //示例：判断 date 是否在 startDate 和 endDate 之间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date currentDate = sdf.parse(date);
            Date startDateObj = sdf.parse(startDate);
            Date endDateObj = sdf.parse(endDate);

            return currentDate.compareTo(startDateObj) >= 0 && currentDate.compareTo(endDateObj) <= 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

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
}