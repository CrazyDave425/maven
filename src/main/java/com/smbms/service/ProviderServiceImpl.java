package com.smbms.service;

import com.smbms.mapper.ProviderMapper;
import com.smbms.pojo.Provider;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class ProviderServiceImpl implements ProviderService{
    @Autowired
    ProviderMapper providerMapper;
    @Override
    public List<Provider> getAllProvider() {
        return providerMapper.getAllProvider();
    }

    @Override
    public Provider getByid(int id) {
        return providerMapper.getByid(id);
    }

    @Override
    public List<Provider> getByIdAndProName(Integer id, String proName) {
        return providerMapper.getByIdAndProName(id,proName);
    }

    @Override
    public int addProvider(Provider provider) {

        //if (true) throw new SqlSessionException("SQl异常");
        return providerMapper.addProvider(provider);
    }

    @Override
    public int delProvider(Integer id) {
        return providerMapper.delProvider(id);
    }

    @Override
    public int modify(Provider provider) {
        return providerMapper.modify(provider);
    }

    @Override
    public List<Provider> getByCreatedBy(List<Integer> CreatedBys) {
        return providerMapper.getByCreatedBy(CreatedBys);
    }

    @Override
    public List<Provider> getPageProvider(Integer from, Integer pageSize) {
        return providerMapper.getPageProvider(from,pageSize);
    }
}
