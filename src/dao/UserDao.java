package dao;

import model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import util.WorkbookConnection;

public class UserDao {
    private Sheet userSheet;
    public UserDao() {
        userSheet = WorkbookConnection.getWorkbook().getSheet("User");
    }
    public User getUserByName(String name) {
        for (Row row : userSheet) {
            Cell idCell = row.getCell(0);
            if (idCell != null && idCell.getStringCellValue().equals(name)) {
                User user = new User();
                user.setPhoneID(idCell.getStringCellValue());
                user.setPassword(row.getCell(1).getStringCellValue());
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        int lastRow = userSheet.getLastRowNum();
        Row newRow = userSheet.createRow(lastRow + 1);

        Cell idCell = newRow.createCell(0);
        idCell.setCellValue(user.getPhoneID());

        Cell classCell = newRow.createCell(1);
        classCell.setCellValue(user.getPassword());

        WorkbookConnection.saveWorkbook();
    }
}