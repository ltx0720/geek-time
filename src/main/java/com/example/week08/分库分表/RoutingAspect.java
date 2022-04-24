package com.example.week08.分库分表;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

import static com.example.week07.datasource.DynamicDataSource.DATABASE_1;
import static com.example.week07.datasource.DynamicDataSource.DATABASE_2;

@Aspect
@Service
public class RoutingAspect {

    @Pointcut("@annotation(com.example.week08.分库分表.SimpleRouting)")
    public void pointCut() {
    }


    @Before("pointCut()")
    public void around(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        SimpleRouting annotation = method.getAnnotation(SimpleRouting.class);
        Object[] args = joinPoint.getArgs();
        DbParam dbParam;
        if (args.length != 0 && args[0] instanceof DbParam) {
            dbParam = (DbParam) args[0];
        } else {
            throw new RuntimeException("arg error");
        }

        // 按 userId 路由
        if (StringUtils.isEmpty(annotation.dataBase())) {
            dbParam.setDatabaseName(getDataBaseName(dbParam.getUserId()));
        }

        dbParam.setTableName(getTableName(annotation.table(), dbParam.getUserId()));
    }

    private String getDataBaseName(String userId) {
        return (userId.hashCode() & 1) == 0 ? DATABASE_1 : DATABASE_2 ;
    }

    private String getTableName(String tableName, String userId) {
        int index = userId.hashCode() & 15;
        return index < 10 ? tableName + "0" + index : tableName +index;
    }

}
