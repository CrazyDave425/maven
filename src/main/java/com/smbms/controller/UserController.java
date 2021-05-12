package com.smbms.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smbms.pojo.Role;
import com.smbms.pojo.User;
import com.smbms.service.RoleService;
import com.smbms.service.UserService;

import com.smbms.tools.PageSupport;
import com.smbms.util.Constains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    /**
     * 用户登录
     *
     * @param userCode
     * @param userPassword
     * @param session
     * @param model
     * @return
     */
    public @RequestMapping("/login")
    String login(String userCode, String userPassword, HttpSession session, Model model) {
        User user = userService.login(userCode, userPassword);
        if (user != null) {
            model.addAttribute("id", user.getId());
            session.setAttribute(Constains.User_SESSION, user);
            return "../jsp/frame";
        }
        return "../login";
    }
//public @RequestMapping("topwdmodify")String topwdmodify(){
//        return "jsp/pwdmodify";
//}
    public @RequestMapping("/checkPwd") @ResponseBody String checkPwd(Integer id, String oldpassword) {
        return JSON.toJSONString(userService.checkPwd(id, oldpassword));
    }

    public @RequestMapping("/updatePwd")@ResponseBody String updatePwd(Integer id, String newpassword) {
        return JSON.toJSONString(userService.pwdmodify(id, newpassword));
    }
    @ResponseBody
    public @RequestMapping(value = "/addUser" ,method = RequestMethod.POST) String addUser(User user, Model model, @RequestParam(value = "pic",required = false)MultipartFile file,
                                                                                          MultipartHttpServletRequest request) throws IOException {
        //获取项目实际路径
        String realPath = request.getServletContext().getRealPath("/images/");
        System.out.println("00000000"+file.getOriginalFilename());
        System.out.println("11111111111"+realPath);
        //保存头像
        file.transferTo(new File(realPath+file.getOriginalFilename()));
        //给user的idPicPath赋值
        user.setIdPicPath("images/"+file.getOriginalFilename());
        return JSON.toJSONString(userService.addUser(user)>0?true:false);
    }
    public @RequestMapping("/JYuserCode") @ResponseBody String JYuserCode(String userCode,Model model){
        User user = userService.JYuserCode(userCode);
        return JSON.toJSONString(userService.JYuserCode(userCode)!=null?true:false);
    }
    public @RequestMapping("/list") String list(String queryUserName,
                                                @RequestParam(defaultValue = "0") Integer queryUserRole,
                                                @RequestParam(defaultValue = "1") Integer currentPageNo,
                                                @RequestParam(defaultValue = "5") Integer pageSize
                                                ,Model model){
//        PageHelper.startPage(currentPageNo,pageSize);
        //查总记录数
        int count = userService.getUserCount(queryUserName, queryUserRole);
        //对页码和每页大小做兼容性处理
        PageSupport pageSupport=new PageSupport(currentPageNo,count,pageSize,(count/pageSize)+1);

        //查用户列表
        List<User> userList = userService.getAllUser(queryUserName, queryUserRole,currentPageNo,pageSize);
        for (User user : userList) {
            System.out.println(user);
        }
//        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        //获取角色列表
        List<Role> roleList = roleService.getAllRole();
        //回显输入信息
        model.addAttribute("queryUserName",queryUserName);
        model.addAttribute("queryUserRole",queryUserRole);
        model.addAttribute("userList",userList);
        model.addAttribute("roleList",roleList);
       model.addAttribute("pageSupport",pageSupport);
        return "../jsp/userlist";
    }
    public @RequestMapping("userview") String userview(Model model ,Integer id){
        User user = userService.getAllUserById(id);
        model.addAttribute("user",user);
        return "../jsp/userview";
    }
    public @RequestMapping("tousermodify") String tousermodify(Model model,Integer id){
        User user = userService.getAllUserById(id);
        //获取角色列表
        List<Role> roleList = roleService.getAllRole();
        model.addAttribute("user",user);
        model.addAttribute("roleList",roleList);
        return "../jsp/usermodify";
    }
    public @RequestMapping("usermodify") String usermodify(User user){
        userService.modify(user);
        user.setModifyDate(new Date());
    return "../jsp/userlist";
    }
}