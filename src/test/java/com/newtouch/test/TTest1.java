package com.newtouch.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class TTest1 {

//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }

    @Test
    public void xx() {//包装类型默认缓存max=128
        int a=128;
        int b=128;
        long g=128L;
        long h=128L;
        Integer c=128;
        Integer d=128;
        Long e=128L;
        Long f=128L;
        System.out.println(a==b);//true
        System.out.println(g==h);//true
        System.out.println(c==d);//false
        System.out.println(e==f);//false
    }

    @Test
    public void main() {
        List<String> list= Arrays.asList("11","22");
        list.forEach((temp)-> System.out.println(temp));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("tom", "jame", "jerry", "hello");
        Stream<String> stream = list.stream();
        Stream<String[]> streamString = stream.map(s->s.split(""));
       // streamString.forEach(c-> System.out.println(c.toString()));
        System.out.println("=========================");
        Stream<String> map = streamString.flatMap(Arrays::stream);
        map.forEach(x->{
            System.out.print(x);
        });

    }

}