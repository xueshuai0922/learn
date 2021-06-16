package com.xs.mybatis.sharding_jdbc.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xueshuai
 * @since 2021-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("order_detail")
@ApiModel(value="Detail对象", description="")
public class Detail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer detailId;

    private Integer orderId;

    private String orderDetailName;


}
