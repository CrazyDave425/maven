package com.smbms.controller;

import com.alibaba.fastjson.JSON;
import com.smbms.pojo.Role;
import com.smbms.pojo.User;
import com.smbms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {
    @Autowired
    RoleService roleService;



    @RequestMapping("/RoleList")
    public String RoleList(Map map) {
        map.put("role",roleService.getAllRole());
        return  "../jsp/useradd";
    }
    public @RequestMapping("Rolelist")@ResponseBody String Rolelist(){
        //获取角色列表
        List<Role> roleList = roleService.getAllRole();
        return JSON.toJSONString(roleList);
    }

}
