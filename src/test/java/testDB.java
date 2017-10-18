import com.help.dao.UserDao;
import com.help.pojo.User;

import java.sql.SQLException;

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

    public static void main(String[] args){
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUserId(1000);
        user.setMobilephone("15212928608");
        user.setUsername("alice_root");
        user.setUsermail("lacunak@sina.com");
        try {
         //   int num = userDao.changeMobilephone(user);
         //   int num = userDao.changeUsername(user);
            int num = userDao.changeUsermail(user);
            System.out.println("更新成功！"+"受影响的行数为："+num);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
