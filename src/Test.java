import java.io.InputStream;
import java.util.Scanner;

public class Test {
    /*����һ�����ﳵ���飺�����Ʒ(������ģ��)
     */
    static int count = 0;
    static Product carts[] = new Product[3];

    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo = true;
        while (bo) {
            System.out.println("�������û�����");
            Scanner sc = new Scanner(System.in);//���ռ�������
            String username = sc.next();//��������
            System.out.println("���������룺");
            String password = sc.next();
            InputStream in = Class.forName("Test").getResourceAsStream("/Users.xlsx");
            ReadUsersExcel readExcel = new ReadUsersExcel();//�������ļ��Ķ���
            User users[] = readExcel.readExcel(in);//��������in
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername().trim()) && password.equals(users[i].getPassword().trim()))
                //��trim�����ɴ���ǰ��ո�
                {
                    System.out.println("��¼�ɹ�");
                    bo = false;
                    shopping(sc);//���÷���
                    while (true) {
                        System.out.println("�鿴���ﳵ�밴1");
                        System.out.println("����������Ʒ�밴2");
                        System.out.println("�����밴3");
                        System.out.println("�˳��밴4");
                        int choose = sc.nextInt();//��������
                        if (choose == 1) {
                        /*
                        1.�鿴���ﳵ
                        ��1�����ﳵ������ģ��
                        ��2����������Ԫ������ҳ��������������
                         */
                            viewCarts();
                        } else if (choose == 2) {
                            shopping(sc);//���÷���
                        } else if (choose == 3) {
                            /*
                            1.��������(������)
                            2.��POI����Order.xlsx�ļ�
                            3.�ѹ��ﳵ�����Ʒд��Order.xlsx�ļ�
                             */
                            Order order = new Order();//�������������
                            order.setUser(users[i]);//��¼�ɹ��ĸ��û� ,���������û�
                            Product products[] = new Product[count];
                            /*
                            ʵ������2����Ʒ��������carts�е�2����Ʒ�������products
                             */
                            for(int j = 0;j < carts.length;j++){
                                if(carts[j]!= null){
                                    products[j] = carts[j];
                                }
                            }
                            order.setProducts(carts);//����������Ʒ:ʵ��Ӧ�ý��д�����������Ϊnull��ȥ��
                            //�¶���������Excel��
                            CreateOrder.createOrder(order);

                            /*
                            ͳ��ÿ����Ʒ������
                             */
                        } else if (choose == 4) {
                            System.out.println("�˳��ɹ�");
                            break;
                        }
                    }
                    break;
                } else {
                    System.out.println("��¼ʧ��");
                }
            }
        }
    }

    /*
    �鿴���ﳵ
     */
    public static void viewCarts() {
        System.out.println("��ǰ���ﳵ��Ʒ���£�");
        for (Product product : carts) {
            /*
            ��ʾ�ӵ����ﳵ����Ʒ��Ҳ���Ǳ�������
            */
            if (product != null) {
                System.out.print(product.getpId());
                System.out.print("\t" + product.getpName());
                System.out.print("\t" + product.getPrice());
                System.out.println("\t" + product.getpDesc());//�ո�+����
            }
        }
    }

    /*
    ����������Ʒ
     */
    public static void shopping(Scanner sc) throws ClassNotFoundException {
        InputStream inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
        ReadProductExcel readProductExcel = new ReadProductExcel();
        Product products[] = readProductExcel.getAllProduct(inProduct);
        for (Product product : products) {
            /*
            ��ʾ��Ʒ��Ҳ���Ǳ�������
            */
            System.out.print(product.getpId());
            System.out.print("\t" + product.getpName());
            System.out.print("\t" + product.getPrice());
            System.out.println("\t" + product.getpDesc());//�ո�+����
        }
        System.out.println("��������ƷID������Ʒ���빺�ﳵ��");
        String pId = sc.next();
        /*
        ���ݴ�IDȥexcel�в����Ƿ��ж�Ӧ����Ʒ��Ϣ�������򷵻ظ���Ʒ
        */
        ReadProductExcel readProductExcel1 = new ReadProductExcel();
        inProduct = null;
        inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
        Product product = readProductExcel1.getProductById(pId, inProduct);
        if (product != null) {
            carts[count++] = product;
        }
    }
}
