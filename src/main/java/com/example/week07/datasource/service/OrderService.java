package com.example.week07.datasource.service;

import com.example.week07.Order;
//import com.example.week07.datasource.DynamicDataSource;
import com.example.week07.datasource.DynamicDataSource;
import com.example.week07.datasource.dao.OrderDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService {

    @Resource
    private OrderDao orderDao;

//    @Resource
//    private SqlSessionTemplate sqlSessionTemplate;



    @Transactional(transactionManager = "tx1", rollbackFor = Exception.class)
//    @Transactional
    public Integer delete1(Long id) throws Exception {
        DynamicDataSource.setDataSource("primaryDataSource");
        Integer delete = orderDao.delete(1L);
        DynamicDataSource.clearDataSource();

//        orderDao.delete(1L);
//        sqlSessionTemplate.rollback();
        throw new Exception();
//        return delete;
    }


    public List<Order> selectOrderList(Long id) {
        List<Order> orders = orderDao.selectOrderList(1L);
        return orders;
    }



//    public List<Order> selectOrderList(Long id) {
//        DynamicDataSource.setDataSource("primaryDataSource");
//        List<Order> orders = orderDao.selectOrderList(1L);
//        DynamicDataSource.clearDataSource();
//        return orders;
//    }

//
//    public List<Order> selectOrderList1(Long id) {
//        DynamicDataSource.setDataSource("secondDataSource");
//        List<Order> orders = orderDao.selectOrderList(1L);
//        DynamicDataSource.clearDataSource();
//        return orders;
//    }



}
