import java.io.InputStream;
import java.util.Scanner;
public class Test {
    //psvm+回车
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo = true;
        while (bo) {

        /*
        开始读文件进行信息的比对
         */
            /*File file = new File("C:\\Users\\Administrator\\IdeaProjects\\CmdShop\\src\\Users.xlsx");*/
            System.out.println("请输入用户名："); //"请输入用户名：".sout+回车
            Scanner sc = new Scanner(System.in); //Scanner出现红色时，将光标移到Scanner，按Alt+回车，
            //可自动打包，首行出现import java.util.Scanner(从键盘输入)
            //接收键盘输入
            String username = sc.next();//阻塞方法，//把键盘输入的用户名赋值给username

            System.out.println("请输入密码：");
            String password = sc.next();
            InputStream in = Class.forName("Test").getResourceAsStream("/Users.xlsx");//改进后的输出流（常用）
            InputStream inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");//改进后的输出流（常用）
            ReadUsersExcel readExcel = new ReadUsersExcel();//创建读文件的对象
            User users[] = readExcel.readExcel(in);//参数对象in
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername().trim()) && password.equals(users[i].getPassword().trim()))
                //用trim（）可处理前后空格
                {
                    System.out.println("登录成功");
                    bo = false;
                    /*
                    显示商品，也就是遍历数组
                     */
                    ReadProductExcel readProductExcel = new ReadProductExcel();
                    Product products[] = readProductExcel.getAllProduct(inProduct);
                    for (Product product : products) {
                        System.out.print(product.getpId());
                        System.out.print("\t" + product.getpName());
                        System.out.print("\t" + product.getPrice());
                        System.out.println("\t" + product.getpDesc());//空格+换行
                    }
                    System.out.println("请输入商品ID将该商品加入购物车：");
                    String pId = sc.next();
                    /*
                    创建一个购物车数组：存放商品(用数组模拟)
                     */
                    int count = 0;
                    Product carts[] = new Product[3];
                    /*
                    根据此ID去excel中查找是否有对应的商品信息，若有则返回该商品
                     */
                    ReadProductExcel readProductExcel1 = new ReadProductExcel();
                    inProduct = null;
                    inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                    Product product = readProductExcel1.getProductById(pId, inProduct);
                    if (product != null) {
                        carts[count++] = product;
                    }
                    System.out.println("查看购物车请按1");
                    System.out.println("继续购买商品请按2");
                    int choose = sc.nextInt();//输入整型
                    if (choose == 1) {
                        /*
                        1.查看购物车
                        （1）购物车用数组模拟
                        （2）将数组内元素逐个找出来：对数组遍历
                         */
                        inProduct = null;
                        inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                        products = readProductExcel.getAllProduct(inProduct);
                        for (int j = 0; j < carts.length; j++) {
                            if (carts[j] != null) {
                                System.out.print(carts[j].getpId());
                                System.out.print("\t" + carts[j].getpName());
                                System.out.print("\t" + carts[j].getPrice());
                                System.out.println("\t" + carts[j].getpDesc());//空格+换行
                            }
                        }
                        inProduct = null;
                        inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                        product = readProductExcel.getProductById(pId, inProduct);
                        if (product != null) {
                            /*
                            将商品加入购物车
                             */
                            carts[count++] = product;
                        }
                    } else if (choose == 2) {
                        /*
                        2.继续购物
                        （1）又要显示所有商品
                         */
                        readProductExcel = new ReadProductExcel();
                        inProduct = null;
                        inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                        products =readProductExcel.getAllProduct(inProduct);
                        for (Product p : products) {
                            System.out.print(p.getpId());
                            System.out.print("\t" + p.getpName());
                            System.out.print("\t" + p.getPrice());
                            System.out.println("\t" + p.getpDesc());//空格+换行
                        }
                        System.out.println("请输入商品ID将该商品加入购物车：");
                        pId = sc.next();
                        readProductExcel1 = new ReadProductExcel();
                        inProduct = null;
                        inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                        Product product1 = readProductExcel1.getProductById(pId, inProduct);
                        if (product != null) {
                            carts[count++] = product1;
                        }
                    }
                    System.out.println("查看购物车请按1");
                    System.out.println("继续购买商品请按2");
                    choose = sc.nextInt();//输入整型
                    if (choose == 1) {
                        /*
                        1.查看购物车
                        （1）购物车用数组模拟
                        （2）将数组内元素逐个找出来：对数组遍历
                         */
                        inProduct = null;
                        inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                        products = readProductExcel.getAllProduct(inProduct);
                        for (int j = 0; j < carts.length; j++) {
                            if (carts[j] != null) {
                                System.out.print(carts[j].getpId());
                                System.out.print("\t" + carts[j].getpName());
                                System.out.print("\t" + carts[j].getPrice());
                                System.out.println("\t" + carts[j].getpDesc());//空格+换行
                            }
                        }
                        inProduct = null;
                        inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                        product = readProductExcel.getProductById(pId, inProduct);
                        if (product != null) {
                            /*
                            将商品加入购物车
                             */
                            carts[count++] = product;
                        }
                    } else if (choose == 2) {
                        /*
                        2.继续购物
                        （1）又要显示所有商品
                         */
                        readProductExcel = new ReadProductExcel();
                        inProduct = null;
                        inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                        products =readProductExcel.getAllProduct(inProduct);
                        for (Product p : products) {
                            System.out.print(p.getpId());
                            System.out.print("\t" + p.getpName());
                            System.out.print("\t" + p.getPrice());
                            System.out.println("\t" + p.getpDesc());//空格+换行
                        }
                        System.out.println("请输入商品ID将该商品加入购物车：");
                        pId = sc.next();
                        readProductExcel1 = new ReadProductExcel();
                        inProduct = null;
                        inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                        Product product1 = readProductExcel1.getProductById(pId, inProduct);
                        if (product != null) {
                            carts[count++] = product1;
                        }
                    }

                    break;
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }
}
