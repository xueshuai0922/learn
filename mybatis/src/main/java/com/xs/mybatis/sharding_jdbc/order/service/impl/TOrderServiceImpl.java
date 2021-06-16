package com.xs.mybatis.sharding_jdbc.order.service.impl;

import com.xs.mybatis.sharding_jdbc.order.entity.TOrder;
import com.xs.mybatis.sharding_jdbc.order.mapper.TOrderMapper;
import com.xs.mybatis.sharding_jdbc.order.service.ITOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xueshuai
 * @since 2021-06-01
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {

}
