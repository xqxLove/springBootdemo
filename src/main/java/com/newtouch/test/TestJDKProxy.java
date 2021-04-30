package com.newtouch.test;

import com.newtouch.dao.IOrderDao;
import com.newtouch.daoIpml.OrderDao;
import com.newtouch.proxy.ProxyFactory;

public class TestJDKProxy {
    public static void main(String[] args) {
        // 目标对象
        OrderDao target = new OrderDao();

        // 给目标target对象，创建代理对象(这个对象对目标对象进行了增强)
        IOrderDao proxy = (IOrderDao) new ProxyFactory(target).genProxyBean();

        proxy.createOrder();

        System.out.println("===1创建订单完成===");

        proxy.queryOrder();

        System.out.println("===2查询订单完成===");

        proxy.cancelOrder();
        System.out.println("===3取消订单完成===");
    }
}
