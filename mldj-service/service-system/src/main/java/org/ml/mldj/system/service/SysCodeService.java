package org.ml.mldj.system.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.ml.mldj.common.exception.BizException;
import org.ml.mldj.common.utils.MyBeanUtils;
import org.ml.mldj.common.utils.ResultCode;
import org.ml.mldj.model.system.dto.SysCodeTypeDTO;
import org.ml.mldj.model.system.dto.SysCodeValueDTO;
import org.ml.mldj.model.system.dto.SysCodeValueUpdateDTO;
import org.ml.mldj.model.system.entity.SysCodeType;
import org.ml.mldj.model.system.entity.SysCodeValue;
import org.ml.mldj.model.system.vo.SysCodeTypeVO;
import org.ml.mldj.model.system.vo.SysCodeValueVO;
import org.ml.mldj.system.mapper.RedisRepository;
import org.ml.mldj.system.mapper.SysCodeTypeRepository;
import org.ml.mldj.system.mapper.SysCodeValueRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SysCodeService {

    @Autowired
    SysCodeValueRepository sysCodeValueRepository;
    @Autowired
    SysCodeTypeRepository sysCodeTypeRepository;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    RedisRepository redisRepository;

    @Cacheable(value = "sys_type", key = "#codeType")
    public List<SysCodeValueVO> queryCode(String codeType) {
        LambdaQueryWrapper<SysCodeType> query = new LambdaQueryWrapper<>();
        query.eq(SysCodeType::getCodeType, codeType);
        SysCodeType type = sysCodeTypeRepository.selectOne(query);
        if (type == null) {
            throw new BizException(ResultCode.SYS_ERROR);
        }
        LambdaQueryWrapper<SysCodeValue> queryVal = new LambdaQueryWrapper<>();
        queryVal.eq(SysCodeValue::getCodeType, type.getCodeType());
        List<SysCodeValue> codeValueList = sysCodeValueRepository.selectList(queryVal);

        return MyBeanUtils.copyList(codeValueList, SysCodeValueVO::new);

    }


    @Cacheable(value = "sys_code_type", key = "'list'")
    public List<SysCodeTypeVO> list() {
        List<SysCodeType> list = sysCodeTypeRepository.selectList(null);
        return MyBeanUtils.copyList(list, SysCodeTypeVO::new);
    }

    // 删除码表list的缓存
    @CacheEvict(value = "sys_code_type", key = "'list'")
    public SysCodeTypeVO insertSysCodeType(SysCodeTypeDTO form) {
        SysCodeType sysCodeType = new SysCodeType();
        BeanUtils.copyProperties(form, sysCodeType);
        LambdaQueryWrapper<SysCodeType> query = new LambdaQueryWrapper<>();
        query.eq(SysCodeType::getCodeType, form.getCodeType());
        SysCodeType byCodeType = sysCodeTypeRepository.selectOne(query);
        if (byCodeType != null) {
            throw new BizException(ResultCode.DUPLICATE_DATA);
        }
        sysCodeTypeRepository.insertOrUpdate(sysCodeType);
        SysCodeTypeVO sysCodeTypeVO = new SysCodeTypeVO();
        BeanUtils.copyProperties(sysCodeType, sysCodeTypeVO);
        return sysCodeTypeVO;
    }

    //  删除码表缓存
    @Caching(evict = {
            @CacheEvict(value = "sys_code_type", key = "'list'"),
            @CacheEvict(value = "sys_type", key = "#form.codeType")
    })
    public SysCodeTypeVO updateSysCodeType(SysCodeTypeDTO form) {
        LambdaQueryWrapper<SysCodeType> query = new LambdaQueryWrapper<>();
        query.eq(SysCodeType::getCodeType, form.getCodeType());
        SysCodeType byCodeType = sysCodeTypeRepository.selectOne(query);
        if (byCodeType == null) {
            throw new BizException(ResultCode.DATA_NO_EXISTS);
        }
        BeanUtils.copyProperties(form, byCodeType);
        sysCodeTypeRepository.insertOrUpdate(byCodeType);
        SysCodeTypeVO sysCodeTypeVO = new SysCodeTypeVO();
        BeanUtils.copyProperties(byCodeType, sysCodeTypeVO);
        return sysCodeTypeVO;
    }


    @Caching(evict = {
            @CacheEvict(value = "sys_code_type", key = "'list'"),
            @CacheEvict(value = "sys_type", key = "#codeType")
    })
    @Transactional
    public void delSysCodeType(String codeType) {
        LambdaQueryWrapper<SysCodeValue> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysCodeValue::getCodeType, codeType);
        List<SysCodeValue> byTypeId = sysCodeValueRepository.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(byTypeId)) {
            throw new BizException(ResultCode.EXISTS_SON);
        }
        LambdaQueryWrapper<SysCodeType> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(SysCodeType::getCodeType, codeType);
        sysCodeTypeRepository.delete(objectLambdaQueryWrapper);


    }

    @CacheEvict(value = "sys_type", key = "#form.codeType")
    public SysCodeValueVO insertSysCodeValue(SysCodeValueDTO form) {
        SysCodeType byCodeType = sysCodeTypeRepository.selectOne(new QueryWrapper<SysCodeType>().eq("code_type", form.getCodeType()));
        if (byCodeType == null) {
            throw new BizException(ResultCode.DATA_NO_EXISTS);
        }
        SysCodeValue byCodeValue = sysCodeValueRepository.selectOne(new QueryWrapper<SysCodeValue>().eq("code_value", form.getCodeValue()));
        if (byCodeValue != null) {
            throw new BizException(ResultCode.EXISTS_SON);
        }
        SysCodeValue sysCodeValue = new SysCodeValue();
        BeanUtils.copyProperties(form, sysCodeValue);
        sysCodeValueRepository.insert(sysCodeValue);
        SysCodeValueVO sysCodeValueVO = new SysCodeValueVO();
        BeanUtils.copyProperties(sysCodeValue, sysCodeValueVO);
        return sysCodeValueVO;
    }


    @CacheEvict(value = "sys_type", key = "#form.codeType")
    public SysCodeValueVO updateSysCodeValue(SysCodeValueUpdateDTO form) {
        SysCodeValue sysCodeValueById = sysCodeValueRepository.selectById(form.getId());
        if (sysCodeValueById == null) {
            throw new BizException(ResultCode.DATA_NO_EXISTS);
        }
        BeanUtils.copyProperties(form, sysCodeValueById);
        sysCodeValueRepository.updateById(sysCodeValueById);
        SysCodeValueVO sysCodeValueVO = new SysCodeValueVO();
        BeanUtils.copyProperties(sysCodeValueById, sysCodeValueVO);
        return sysCodeValueVO;

    }

    @Transactional
    public void delSysCodeValue(String codeValueId) {
        // 删除缓存
        SysCodeValue sysCodeValue = sysCodeValueRepository.selectById(codeValueId);
        if (sysCodeValue == null) {
            throw new BizException(ResultCode.DATA_NO_EXISTS);
        }
        redisTemplate.delete("sys_type::" + sysCodeValue.getCodeValue());
        // 写数据库
        sysCodeValueRepository.deleteById(codeValueId);
        // 延迟删除缓存
        redisRepository.asyncDelete("sys_type::" + sysCodeValue.getCodeValue());
    }

    public SysCodeValueVO getValue(String codeType, String codeValue) {
        LambdaQueryWrapper<SysCodeValue> sysCodeValueLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysCodeValueLambdaQueryWrapper.eq(SysCodeValue::getCodeType, codeType).eq(SysCodeValue::getCodeValue, codeValue);
        SysCodeValue sysCodeValue = sysCodeValueRepository.selectOne(sysCodeValueLambdaQueryWrapper);
        if (sysCodeValue != null) {
            SysCodeValueVO sysCodeValueVO = new SysCodeValueVO();
            BeanUtils.copyProperties(sysCodeValue, sysCodeValueVO);
            return sysCodeValueVO;
        }
        return null;
    }
}
