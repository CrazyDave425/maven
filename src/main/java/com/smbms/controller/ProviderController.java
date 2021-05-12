package com.smbms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smbms.mapper.ProviderMapper;
import com.smbms.pojo.Provider;
import com.smbms.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    ProviderService providerService;
    @RequestMapping("getAllProvider")
    public String getAllProvider(Model model){
        //分页
        PageHelper.startPage(1,8);
        List<Provider> allProvider = providerService.getAllProvider();
        //PageInfo<Provider> pageInfo = new PageInfo<>(allProvider);
        model.addAttribute("allProvider",allProvider);
        return "../jsp/providerlist";
    }
    @RequestMapping("addProvider")
    public String addProvider(Provider provider,Model model){
        int i = providerService.addProvider(provider);
        model.addAttribute("proCode",provider.getProCode());
        model.addAttribute("proName",provider.getProName());
        model.addAttribute("proContact",provider.getProContact());
        model.addAttribute("proPhone",provider.getProPhone());
        model.addAttribute("proAddress",provider.getProAddress());
        model.addAttribute("proFax",provider.getProFax());
        model.addAttribute("proDesc",provider.getProDesc());
        return getAllProvider(model);
    }
    @RequestMapping("getByid")
    public String getByid(Integer id,Model model){
        Provider provider = providerService.getByid(id);
        model.addAttribute("provider",provider);
        return "provider/getByid";
    }
}
