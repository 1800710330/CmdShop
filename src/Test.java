import java.io.File;
import java.util.Scanner;//Scanner扫描，接受键盘输入

public class Test {
    public static void main(String[] args) {
        /*
        开始读文件
         */
        File file = new File("C:\\Users\\Administrator\\IdeaProjects\\CmdShop\\src\\users.xlsx");
        ReadExcel readExcel = new ReadExcel();//创建对象
        User users[] = readExcel.readExcel(file);

        System.out.println("请输入用户名：");

        Scanner sc = new Scanner(System.in);
        String username = sc.next();//把键盘输入的用户名赋值给username

        System.out.println("请输入密码：");
        String password = sc.next();

        /*for(int i=0;i<users.length;i++){
            if (username == users[i].getUsername() && password == users[i].getPassword()) {
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败");
            }
        }用==会运行出错*/
        for (User user : users) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                System.out.println("登录成功");
            } /*else {
                System.out.println("登录失败");
            }*/
        }
    }
}
/*
==和equals有什么区别
 */