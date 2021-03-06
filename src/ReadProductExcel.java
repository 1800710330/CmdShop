import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;


public class ReadProductExcel {
    /*
    ReadExcel是成员方法（全局方法），成员变量（全局变量）也叫作属性
     */
    public Product[] readExcel(InputStream in)/*商品数组*/ {
        Product products[] = null;//商品数组长度初始化
        try {
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);//ctrl+alt+键对齐
            products = new Product[xs.getLastRowNum()];//商品数组长度的赋值
            for (int j = 1; j <= xs.getLastRowNum(); j++) /*行*/ {
                XSSFRow row = xs.getRow(j);
                Product product = new Product();//创建商品数组,外循环每循环一次就是一个商品信息
                    /*
                    内循环就是给商品对象赋值
                    */
                for (int k = 0; k <= row.getLastCellNum(); k++)/*列*/ {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        product.setpId(this.getValue(cell));
                    } else if (k == 1) {
                        product.setpName(this.getValue(cell));
                    } else if (k == 2) {
                        product.setPrice(this.getValue(cell));
                    } else if (k == 3) {
                        product.setpDesc(this.getValue(cell));
                    }
                    products[j - 1] = product;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return products;
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
