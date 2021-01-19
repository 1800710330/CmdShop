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
            String username = sc.next();//把键盘输入的用户名赋值给username

            System.out.println("请输入密码：");
            String password = sc.next();
            InputStream in = Class.forName("Test").getResourceAsStream("/Users.xlsx");//改进后的输出流（常用）
            ReadUsersExcel readExcel = new ReadUsersExcel();//创建读文件的对象
            User users[] = readExcel.readExcel(in);//参数对象in
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername().trim()) && password.equals(users[i].getPassword().trim()))
                //用trim（）可处理前后空格
                {
                    System.out.println("登录成功");
                    bo = false;
                    break;
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }
}
