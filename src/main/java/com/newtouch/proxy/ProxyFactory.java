package com.newtouch.proxy;


import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建动态代理对象的步骤
 * 注:动态代理不需要实现接口,但是需要指定接口类型
 */
public class ProxyFactory {
    //维护一个目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }
//对目标对象包装成代理对象
public Object genProxyBean() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (Object proxy, Method method, Object[] args)->{//lambda表达式 ：省去了复杂的函数表达式接口；lambada表达式结构：参数，箭头，主体
                        System.out.println("------订单操作前,记录日志------");
                        //运用反射执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("------订单操作后,记录日志------");
                        return returnValue;  //返回已经扩展后的代理对象
                }
//                new InvocationHandler() {
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        System.out.println("------订单操作前,记录日志------");
//                        //运用反射执行目标对象方法；
//                        Object returnValue = method.invoke(target, args);
//                        System.out.println("------订单操作后,记录日志------");
//                        return returnValue;  //返回已经扩展后的代理对象
//                    }
//                }
        );

 }
}
