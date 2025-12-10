package org.ml.mldj.driver.service.impl;

import org.ml.mldj.model.entity.Wallet;
import org.ml.mldj.model.mapper.WalletMapper;
import org.ml.mldj.model.service.IWalletService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 钱包表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Service
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet> implements IWalletService {

}
