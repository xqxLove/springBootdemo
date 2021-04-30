package com.newtouch.daoIpml;

import com.newtouch.dao.IOrderDao;

public class OrderDao implements IOrderDao {
    @Override
    public void createOrder() {
        System.out.println("订单创建成功.......");
    }

    @Override
    public void queryOrder() {
        System.out.println("订单查询成功.......");
    }

    @Override
    public void cancelOrder() {
        System.out.println("订单取消成功.......");
    }
}
