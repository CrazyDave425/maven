package com.smbms.mapper;

import com.smbms.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {
    List<Bill> getProductNameAndProviderId(@Param("name") String productName,@Param("id") Integer id,@Param("isPayment") Integer isPayment);

    //增加
    int addBill(Bill bill);
    //修改
    int modify(Bill bill);
    Bill getByid(int id);
    //删除
    int delBill(int id);
}
