package org.ml.mldj.customer.service;

import org.ml.mldj.customer.mapper.CustomerInfoMapper;
import org.ml.mldj.model.entity.Customer;
import org.ml.mldj.model.customer.vo.CustomerVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerInfoMapper customerInfoMapper;

    public CustomerVO queryCustomer(String customerId) {
        Customer customer = customerMapper.selectById(customerId);
        CustomerVO customerVO = new CustomerVO();
        BeanUtils.copyProperties(customer, customerVO);
        return customerVO;

    }
}
