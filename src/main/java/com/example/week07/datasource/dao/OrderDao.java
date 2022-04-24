package com.example.week07.datasource.dao;

import com.example.week07.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDao {

    @Delete("select * from `${tableName}` where id=#{id}")
    Integer delete(@Param("tableName") String tableName, @Param("id") Long id);

    @Select("select * from `${tableName}` where user_id=#{userId} limit 10")
    List<Order> selectOrderList(@Param("tableName") String tableName, @Param("userId") String userId);

}
