import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;


public class ReadExcel {
    /*
    ReadExcel是成员方法（全局方法），成员变量（全局变量）也叫作属性
     */
    public User[] readExcel(InputStream in)/*用户数组*/ {
        User users[] = null;//用户数组长度初始化
        try {
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);//ctrl+alt+键对齐
            users = new User[xs.getLastRowNum()];//用户数组长度的赋值
            for (int j = 1; j <= xs.getLastRowNum(); j++) /*行*/ {
                XSSFRow row = xs.getRow(j);
                User user = new User();//创建用户数组,外循环每循环一次就是一个用户信息
                    /*
                    内循环就是给用户对象赋值
                    */
                for (int k = 0; k <= row.getLastCellNum(); k++)/*列*/ {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        user.setUsername(this.getValue(cell));
                    } else if (k == 1) {
                        user.setPassword(this.getValue(cell));
                    } else if (k == 2) {
                        user.setAddress(this.getValue(cell));
                    } else if (k == 3) {
                        user.setPhone(this.getValue(cell));
                    }
                    users[j - 1] = user;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return users;
    }

    private String getValue(XSSFCell cell) {
        String value;
        CellType type = cell.getCellTypeEnum();
        DecimalFormat df = new DecimalFormat("#");
        switch (type) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case NUMERIC:
                value = df.format(cell.getNumericCellValue());    //double和一个空字符串相连接，最终得到一个字符串
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case ERROR:
                value = "非法字符";
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}
