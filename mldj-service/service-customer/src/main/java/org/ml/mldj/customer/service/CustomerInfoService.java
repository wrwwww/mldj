package org.ml.mldj.customer.service;

import jakarta.validation.Valid;
import org.ml.mldj.model.customer.dto.UpdateCustomerForm;
import org.ml.mldj.model.customer.entity.CustomerInfo;
import org.ml.mldj.customer.mapper.CustomerInfoMapper;
import org.ml.mldj.model.customer.vo.CustomerVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户信息表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class CustomerInfoService {
    @Autowired
    CustomerInfoMapper customerInfoMapper;

    public CustomerVO queryCustomer(String customerId) {
        CustomerInfo customer = customerInfoMapper.selectById(customerId);
        CustomerVO customerVO = new CustomerVO();
        BeanUtils.copyProperties(customer, customerVO);
        return customerVO;

    }

    public Boolean UpdateCustomerInfo(UpdateCustomerForm form) {
        customerInfoMapper.update(form);
        return null;
    }
}
