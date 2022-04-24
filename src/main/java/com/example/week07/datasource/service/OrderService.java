package com.example.week07.datasource.service;

import com.example.week07.Order;
import com.example.week07.datasource.DynamicDataSource;
import com.example.week07.datasource.dao.OrderDao;
import com.example.week08.分库分表.DbParam;
import com.example.week08.分库分表.SimpleRouting;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService {

    @Resource
    private OrderDao orderDao;

    @Transactional(transactionManager = "tx1", rollbackFor = Exception.class)
    public Integer delete1(Long id) throws Exception {
//        DynamicDataSource.setDataSource("primaryDataSource");
//        Integer delete = orderDao.delete(1L);
//        DynamicDataSource.clearDataSource();

//        orderDao.delete(1L);
//        sqlSessionTemplate.rollback();
        throw new Exception();
//        return delete;
    }


    @SimpleRouting(table = "order")
    public List<Order> selectOrderList(DbParam dbParam, String userId) {
        DynamicDataSource.setDataSource(dbParam.getDatabaseName());
        try {
            return orderDao.selectOrderList(dbParam.getTableName(), userId);
        } finally {
            DynamicDataSource.clearDataSource();
        }
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
