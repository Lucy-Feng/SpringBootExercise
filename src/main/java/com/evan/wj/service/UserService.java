package com.evan.wj.service;
import com.evan.wj.dao.UserDAO;
import com.evan.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    //判断用户是否存在
     public boolean isExist(String username){
         User user = getByName(username);
         return null!=user;
     }

     public User getByName(String username){
         return userDAO.findByUsername(username);
     }
    //通过用户名及密码查询并获得对象
     public User get(String username,String password){
         return userDAO.getByUsernameAndPassword(username,password);
     }
     public void add(User user){
         userDAO.save(user);
     }
}
