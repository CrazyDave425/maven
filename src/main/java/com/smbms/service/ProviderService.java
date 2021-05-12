package com.smbms.service;

import com.smbms.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderService {
    //查
    List<Provider> getAllProvider();
    Provider getByid(int id);
    List<Provider> getByIdAndProName(@Param("id")Integer id, @Param("proName") String proName);
    //增
    int addProvider(Provider provider);
    //删
    int delProvider(Integer id);
    //改
    int modify(Provider provider);
    //获取指定供应商列表下的订单列表
    //List<Provider> getByProviderId(@Param("ProviderIds") Integer [] ProviderIds);
    List<Provider> getByCreatedBy(@Param("CreatedBys") List<Integer> CreatedBys);
    //分页
    List<Provider> getPageProvider(@Param("from") Integer from,@Param("pageSize") Integer pageSize);
}
