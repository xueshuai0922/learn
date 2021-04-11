package com.xs.mybatis.config;

import org.apache.ibatis.javassist.bytecode.SignatureAttribute;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author xueshuai
 * @date 2021/4/10 22:40
 * @description
 */
public class CustomTypeHandler  extends BaseTypeHandler {


    //*********************************** Java类型---> JDBC类型  ************************************************
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {

    }

    //*********************************** JDBC类型---> Java类型  ************************************************
    //根据列名进行获取结果集 （一般使用这个）
    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        System.out.println("----getNullableResult-----");
        return resultSet.getObject(s);
    }


    //根据下标获取结果集
    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
