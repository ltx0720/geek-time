package com.example.week07.datasource.dao;

import com.example.week07.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDao {

    @Select("select * from `order` where id=#{id}")
    List<Order> selectOrderList(Long id);

    @Delete("delete from `order` where id=#{id}")
    Integer delete(Long id);

}
