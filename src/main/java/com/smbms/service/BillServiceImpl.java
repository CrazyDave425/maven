package com.smbms.service;

import com.smbms.mapper.BillMapper;
import com.smbms.pojo.Bill;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class BillServiceImpl implements BillService{
    @Autowired
    BillMapper billMapper;
    @Override
    public List<Bill> getProductNameAndProviderId(String productName, Integer id, Integer isPayment) {
        return billMapper.getProductNameAndProviderId(productName,id,isPayment);
    }

    @Override
    public int addBill(Bill bill) {
        if (true)throw new SqlSessionException("SQL异常");
        return billMapper.addBill(bill);
    }

    @Override
    public int modify(Bill bill) {
        return billMapper.modify(bill);
    }

    @Override
    public Bill getByid(int id) {
        return billMapper.getByid(id);
    }

    @Override
    public int delBill(int id) {
        return billMapper.delBill(id);
    }
}
