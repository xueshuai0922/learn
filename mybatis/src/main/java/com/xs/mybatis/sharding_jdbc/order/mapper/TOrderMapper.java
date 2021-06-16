package com.xs.mybatis.sharding_jdbc.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xs.mybatis.sharding_jdbc.order.entity.TOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xueshuai
 * @since 2021-06-01
 */
public interface TOrderMapper extends BaseMapper<TOrder> {


    //    测试sharding的分库关联查询
    @Select("select * from t_order a inner join order_detail b on a.order_id = b.order_id")
    List<Map> select();

    @Insert("insert into config (id,name) values(1,'name')")
    void  insertConfig();

    @Update("update t_order set order_name=12  where order_id >12 and order_id <40")
    void update();


    /**
     * 测试读写分离 表为user  插入
     */
    @Insert("insert into user (name,age) values ('12',12)")
    void insertUser();


    /**
     * 测试读写分离 表为user  插入
     */
    @Select("select * from  user ")
    List<Map> selectUser();




}
