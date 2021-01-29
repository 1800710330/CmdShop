import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

public class CreateOrder {
    /**
     * Excel 文件要存放的位置，假定在E盘下
     */
    public static String outputFile = "E:\\orders.xls";

    public static void createOrder(Order order) throws FileNotFoundException {
        try {
            // 创建新的Excel 工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 在Excel工作簿中建一工作表，其名为缺省值
            // 如要新建一名为"订单"的工作表，其语句为：
            HSSFSheet sheet = workbook.createSheet("订单");

            CellStyle style = workbook.createCellStyle();
            HSSFRow firstRow = sheet.createRow((short) 0);
            HSSFCell cell01 = firstRow.createCell((short) 0);
            HSSFCell cell02 = firstRow.createCell((short) 1);
            HSSFCell cell03 = firstRow.createCell((short) 2);
            HSSFCell cell04 = firstRow.createCell((short) 3);
            HSSFCell cell05 = firstRow.createCell((short) 4);
            HSSFCell cell06 = firstRow.createCell((short) 5);
            //在单元格中输入一些内容
            cell01.setCellValue("用户");
            cell02.setCellValue("商品");
            cell03.setCellValue("购买数量");
            cell04.setCellValue("商品总价");
            cell05.setCellValue("实付款");
            cell06.setCellValue("下单时间");
//外循环的次数跟购物车的实际长度有关
            for (int i = 1; i < order.getProducts().length; i++) {
                // 在索引1的位置创建行（第二行）
                HSSFRow row = sheet.createRow((short) i);//创建行
                for (int j = 0; j < 6; j++) {
                    HSSFCell cell = row.createCell((short) j);
                    int pId = Integer.parseInt(order.getProducts()[i - 1].getpId());
                    // 在单元格中输入一些内容
                    if (j == 0) {
                        cell.setCellValue(order.getUser().getUsername());
                    } else if (j == 1) {
                        cell.setCellValue(pId);
                    } else if (j == 2) {
                        //目录(key) 内容(value)
                        Map<Integer, Integer> productAmmount = order.getProductAmmount();
                        int productNum = productAmmount.get(pId);
                        cell.setCellValue(productNum);
                    }
                }
            }
            // 新建一输出文件流
            FileOutputStream fOut = new FileOutputStream(outputFile);
            // 把相应的Excel 工作簿存盘
            workbook.write(fOut);
            fOut.flush();
            // 操作结束，关闭文件
            fOut.close();
            System.out.println("文件生成...");
        } catch (Exception e) {
            System.out.println("已运行 xlCreate() : " + e);
        }
    }
}
