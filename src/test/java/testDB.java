import com.help.dao.AfhinfoDao;
import com.help.dao.UserDao;
import com.help.pojo.Afhinfo;
import com.help.pojo.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class testDB {


    Boolean addUser(){
        User user1;
        user1 = new User();
        user1.setUsername("root");
        user1.setPassword("root");
        user1.setMobilephone("17854212890");
        user1.setUsermail("282507860@qq.com");
        user1.setUsersex("female");
        UserDao userDao = new UserDao();
        try {
            userDao.AddUser(user1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    Boolean addAfhinfo(){
        Afhinfo afhinfo = new Afhinfo();
        afhinfo.setUserId(333);
        afhinfo.setUsername("root");
        afhinfo.setReqInfo("哈哈哈哈哈");
        afhinfo.setTime("2017-10-18 17:38:56");
        afhinfo.setLocation("图书馆");
        return true;
    }

    public static void main(String[] args){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
//        UserDao userDao = new UserDao();
//        User user = new User();
//        user.setUserId(1000);
//        user.setMobilephone("15212928608");
//        user.setUsername("alice_root");
//        user.setUsermail("lacunak@sina.com");
//        try {
//         //   int num = userDao.changeMobilephone(user);
//         //   int num = userDao.changeUsername(user);
//            int num = userDao.changeUsermail(user);
//            System.out.println("更新成功！"+"受影响的行数为："+num);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        Afhinfo afhinfo = new Afhinfo();
//        afhinfo.setUserId(333);
//        afhinfo.setUsername("root");
//        afhinfo.setReqInfo("哈哈哈哈哈");
//        afhinfo.setTime("2017-10-18 17:38:56");
//        afhinfo.setLocation("图书馆");
//        AfhinfoDao afhinfoDao = new AfhinfoDao();
//        try {
//            //afhinfoDao.addAfhinfo(afhinfo);
//            List<Afhinfo> lrs =  afhinfoDao.Search();
//            for (int i = 0;i<lrs.size();i++){
//                System.out.println(lrs.get(i).getReqInfo());
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
