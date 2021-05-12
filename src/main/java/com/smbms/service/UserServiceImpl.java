package com.smbms.service;

import com.smbms.mapper.UserMapper;
import com.smbms.pojo.User;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getAllUser(String userName,Integer userRole,Integer currentPageNo,Integer pageSize) {
        return userMapper.getAllUser(userName,userRole,(currentPageNo-1)*pageSize,pageSize);
    }

    @Override
    public int getUserCount(String userName, Integer userRole) {
        return userMapper.getUserCount(userName,userRole);
    }

    @Override
    public User getAllUserById(int id) {
        return userMapper.getAllUserById(id);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);

    }

    @Override
    public int delUser(Integer id) {
        return userMapper.delUser(id);
    }

    @Override
    public int modify(User user) {
        return userMapper.modify(user);
    }

    @Override
    public User login(String userCode, String userPassword) {
        return userMapper.login(userCode,userPassword);
    }

    @Override
    public boolean pwdmodify(Integer id, String userPassword) {
        return userMapper.pwdmodify(id,userPassword)>0?true:false;
    }
    @Override
    public boolean checkPwd(Integer id, String oldPwd) {
        return userMapper.checkPwd(id,oldPwd)!=null?true:false;
    }

    @Override
    public User JYuserCode(String userCode) {
        return userMapper.JYuserCode(userCode);
    }
}
