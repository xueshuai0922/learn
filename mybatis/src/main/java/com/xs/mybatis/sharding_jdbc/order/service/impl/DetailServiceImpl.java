package com.xs.mybatis.sharding_jdbc.order.service.impl;

import com.xs.mybatis.sharding_jdbc.order.entity.Detail;
import com.xs.mybatis.sharding_jdbc.order.mapper.DetailMapper;
import com.xs.mybatis.sharding_jdbc.order.service.IDetailService;
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
public class DetailServiceImpl extends ServiceImpl<DetailMapper, Detail> implements IDetailService {

}
