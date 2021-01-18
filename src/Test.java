import java.io.File;
import java.util.Scanner;

public class Test {
    //psvm+回车
    public static void main(String[] args) {
        /*
        开始读文件进行信息的比对
         */
        File file = new File("C:\\Users\\Administrator\\IdeaProjects\\CmdShop\\src\\Users.xlsx");
        ReadExcel readExcel = new ReadExcel();//创建读文件的对象
        User users[] = readExcel.readExcel(file);

        System.out.println("请输入用户名："); //"请输入用户名：".sout+回车
        Scanner sc = new Scanner(System.in); //Scanner出现红色时，将光标移到Scanner，按Alt+回车，
        //可自动打包，首行出现import java.util.Scanner(从键盘输入)
        //接收键盘输入
        String username = sc.next();//把键盘输入的用户名赋值给username
        System.out.println("请输入密码：");
        String password = sc.next();

        for(User user:users){
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                System.out.println("登录成功");
            }
        }
    }
}
