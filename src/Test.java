import java.io.InputStream;
import java.util.Scanner;

public class Test {
    /*创建一个购物车数组：存放商品(用数组模拟)
     */
    static int count = 0;
    static Product carts[] = new Product[3];

    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo = true;
        while (bo) {
            System.out.println("请输入用户名：");
            Scanner sc = new Scanner(System.in);//接收键盘输入
            String username = sc.next();//阻塞方法
            System.out.println("请输入密码：");
            String password = sc.next();
            InputStream in = Class.forName("Test").getResourceAsStream("/Users.xlsx");
            ReadUsersExcel readExcel = new ReadUsersExcel();//创建读文件的对象
            User users[] = readExcel.readExcel(in);//参数对象in
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername().trim()) && password.equals(users[i].getPassword().trim()))
                //用trim（）可处理前后空格
                {
                    System.out.println("登录成功");
                    bo = false;
                    shopping(sc);//调用方法
                    while (true) {
                        System.out.println("查看购物车请按1");
                        System.out.println("继续购买商品请按2");
                        System.out.println("结账请按3");
                        System.out.println("退出请按4");
                        int choose = sc.nextInt();//输入整型
                        if (choose == 1) {
                        /*
                        1.查看购物车
                        （1）购物车用数组模拟
                        （2）将数组内元素逐个找出来：对数组遍历
                         */
                            viewCarts();
                        } else if (choose == 2) {
                            shopping(sc);//调用方法
                        } else if (choose == 3) {
                            /*
                            1.产生订单(订单类)
                            2.用POI创建Order.xlsx文件
                            3.把购物车里的商品写入Order.xlsx文件
                             */
                            Order order = new Order();//创建订单类对象
                            order.setUser(users[i]);//登录成功的该用户
                            order.setProducts();//如何关联订单和商品？
                            /*
                            统计每个商品的数量
                             */





                        } else if (choose == 4) {
                            System.out.println("退出成功");
                            break;
                        }
                    }
                    break;
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }

    /*
    查看购物车
     */
    public static void viewCarts() {
        System.out.println("当前购物车商品如下：");
        for (Product product : carts) {
            /*
            显示加到购物车的商品，也就是遍历数组
            */
            if (product != null) {
                System.out.print(product.getpId());
                System.out.print("\t" + product.getpName());
                System.out.print("\t" + product.getPrice());
                System.out.println("\t" + product.getpDesc());//空格+换行
            }
        }
    }

    /*
    继续购买商品
     */
    public static void shopping(Scanner sc) throws ClassNotFoundException {
        InputStream inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
        ReadProductExcel readProductExcel = new ReadProductExcel();
        Product products[] = readProductExcel.getAllProduct(inProduct);
        for (Product product : products) {
            /*
            显示商品，也就是遍历数组
            */
            System.out.print(product.getpId());
            System.out.print("\t" + product.getpName());
            System.out.print("\t" + product.getPrice());
            System.out.println("\t" + product.getpDesc());//空格+换行
        }
        System.out.println("请输入商品ID将该商品加入购物车：");
        String pId = sc.next();
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
    }
}
