package com.smbms.mapper;

import com.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface UserMapper {
    //查
    List<User> getAllUser(@Param("userName") String userName,
                          @Param("userRole")Integer userRole,
                          @Param("currentPageNo") Integer currentPageNo,@Param("pageSize") Integer pageSize);

    public int getUserCount(@Param("userName")String userName, @Param("userRole")Integer userRole);
    User getAllUserById(int id);
    //增
    int addUser(User user);
    //删
    int delUser(Integer id);
    //改
    int modify(User user);

    //登录
    User login(@Param("userCode") String userCode, @Param("userPassword") String userPassword);

//    //修改密码
    int pwdmodify(@Param("id") Integer id,@Param("userPassword") String userPassword);
    //校验密码
    User checkPwd(@Param("id") Integer id,@Param("oldPwd") String oldPwd);

    //校验编码是否存在
    User JYuserCode (String userCode);
}
