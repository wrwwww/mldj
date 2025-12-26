package org.ml.mldj.customer.service;

import jakarta.validation.Valid;
import org.ml.mldj.common.exception.BizException;
import org.ml.mldj.common.utils.ResultCode;
import org.ml.mldj.customer.mapper.CustomerCarMapper;
import org.ml.mldj.model.customer.dto.AddCustomerCarForm;
import org.ml.mldj.model.customer.entity.CustomerCar;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 客户车辆信息表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class CustomerCarService {
    @Autowired
    CustomerCarMapper customerCarMapper;

    public int delByIdAndCustomerId(String carId, String customerId) {
        int row = customerCarMapper.delByIdAndCustomerId(carId, customerId);
        if (row == 0) {
            throw new BizException(ResultCode.DATA_NO_EXISTS);
        }
        return row;
    }

    public List<CustomerCar> queryList(String customerId) {
        return customerCarMapper.queryByCustomerId(customerId);
    }

    public int add(@Valid AddCustomerCarForm form, String customerId) {
        CustomerCar customerCar = new CustomerCar();
        BeanUtils.copyProperties(form, customerCar);
        customerCar.setCustomerId(customerId);
        return customerCarMapper.add(customerCar);
    }
}
