import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

public class CreateOrder {
    /**
     * Excel �ļ�Ҫ��ŵ�λ�ã��ٶ���E����
     */
    public static String outputFile = System.getProperty("user.dir") + File.separator + "orders.xls";

    //�ļ����ɽ�����Ŀ·����
    //System.getProperty("user.dir") + File.separator + "orders.xls""E:\\orders.xls"
    public static void createOrder(Order order) throws FileNotFoundException {
        try {
            // �����µ�Excel ������
            HSSFWorkbook workbook = new HSSFWorkbook();
            // ��Excel�������н�һ����������Ϊȱʡֵ
            // ��Ҫ�½�һ��Ϊ"����"�Ĺ����������Ϊ��
            HSSFSheet sheet = workbook.createSheet("����");

            CellStyle style = workbook.createCellStyle();
            HSSFRow firstRow = sheet.createRow((short) 0);
            HSSFCell cell01 = firstRow.createCell((short) 0);
            HSSFCell cell02 = firstRow.createCell((short) 1);
            HSSFCell cell03 = firstRow.createCell((short) 2);
            HSSFCell cell04 = firstRow.createCell((short) 3);
            HSSFCell cell05 = firstRow.createCell((short) 4);
            HSSFCell cell06 = firstRow.createCell((short) 5);
            //�ڵ�Ԫ��������һЩ����
            cell01.setCellValue("�û�");
            cell02.setCellValue("��Ʒ");
            cell03.setCellValue("��������");
            cell04.setCellValue("��Ʒ�ܼ�");
            cell05.setCellValue("ʵ����");
            cell06.setCellValue("�µ�ʱ��");
//��ѭ���Ĵ��������ﳵ��ʵ�ʳ����й�
            for (int i = 0; i < order.getProducts().length; i++) {
                // ������0��λ�ô�����
                HSSFRow row = sheet.createRow((short)i+1);//������
                for (int j = 0; j < 6; j++) {
                    HSSFCell cell = row.createCell((short) j);
                    int pId = Integer.parseInt(order.getProducts()[i].getpId());
                    // �ڵ�Ԫ��������һЩ����
                    if (j == 0) {
                        cell.setCellValue(order.getUser().getUsername());
                    } else if (j == 1) {
                        cell.setCellValue(pId);
                    } else if (j == 2) {
                        //Ŀ¼(key) ����(value)
                        Map<Integer, Integer> productAmmount = order.getProductAmmount();
                        int productNum = productAmmount.get(pId);
                        cell.setCellValue(productNum);
                    }else if(j == 3){
                        Map<Integer, Float> totalAmountPerProduct = order.getTotalAmountPerProduct();
                        Float productTotalpay = totalAmountPerProduct.get(pId);
                        cell.setCellValue(productTotalpay);
                    }
                }
            }
            // �½�һ����ļ���
            FileOutputStream fOut = new FileOutputStream(outputFile);
            // ����Ӧ��Excel ����������
            workbook.write(fOut);
            fOut.flush();
            // �����������ر��ļ�
            fOut.close();
            System.out.println("�ļ�����...");
        } catch (Exception e) {
            System.out.println("������ xlCreate() : " + e);
        }
    }
}
