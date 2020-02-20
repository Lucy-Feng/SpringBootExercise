package com.evan.wj.dao;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
//通过继承 JpaRepository 的方式构建 DAO
public interface UserDAO extends JpaRepository<User,Integer> {
//    使用 JPA，无需手动构建 SQL 语句，
//    而只需要按照规范提供方法的名字即可实现对数据库的增删改查。
    //通过用户名查询
    User findByUsername(String username);
    //通过用户名和密码查询
    User getByUsernameAndPassword(String username,String password);
}
