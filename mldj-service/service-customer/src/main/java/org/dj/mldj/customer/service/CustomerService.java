package org.dj.mldj.customer.service;

import org.dj.mldj.customer.mapper.CustomerMapper;
import org.ml.mldj.model.entity.Customer;
import org.ml.mldj.model.vo.CustomerVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    public CustomerVO queryCustomer(String customerId) {
        Customer customer = customerMapper.selectById(customerId);
        CustomerVO customerVO = new CustomerVO();
        BeanUtils.copyProperties(customer, customerVO);
        return customerVO;

    }
}
