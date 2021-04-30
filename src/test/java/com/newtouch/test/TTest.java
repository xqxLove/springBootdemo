package com.newtouch.test;

import com.newtouch.dto.User;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.concurrent.TimeUnit;

public class TTest {
    private static final User user1=new User("木叶","5566");
    private static final User user2=new User();
    @Test(timeout = 1000)
    public void xx(){
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test(expected = NullPointerException.class)
    public void testNullException() {
        System.out.println("NullPointerException....");
        throw new NullPointerException();
    }
    public static void main(String[] args) {
        int i = 0;
        while (true) {
            i++;
            if (i < 5) {
                System.out.println(i);
                break;
            }
            System.out.println(i);
            if(i == 10){
                System.out.println(i);
                return;
            }
        }

    }
    @Test
    public void xx1(){
        User user=new User();
        user.setPhone("0000");
        user.setName("aaa");
        BeanUtils.copyProperties(user1,user2);
        System.out.println("user1="+user1);
        System.out.println("user2="+user2);
    }
}