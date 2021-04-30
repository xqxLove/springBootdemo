package com.newtouch.test;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class TestJDKProxyTest {

//    @Before
//    public void setUp() throws Exception {
//        //System.out.println("Before");
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        //System.out.println("After");
//    }

    @Test
    public void test1() {//按条件取值
        //throw new NullPointerException();
        //System.out.println("main");
        IntStream.of(new int[] {1, 2, 3}).forEach(System.out::println);
        System.out.println("=====================================");
        IntStream.range(1, 3).forEach(System.out::println);
        System.out.println("=====================================");
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
    }

    @Test
    public void turnUpperCase() {//map操作字符串转大写
        List<String> list = Arrays.asList(new String[] {"aaa", "bbb", "ccc"});
        List<String> result = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        result.forEach(x -> System.out.print(x + " "));
    }

    @Test
    public void distinctStream() {//去重并且根据字符串长度从小到大排序
        Stream<String> distinctStream = Stream.of("bj","shanghai","tianjin","bj","shanghai").distinct();
        Stream<String> sortedStream = distinctStream.sorted(Comparator.comparing(String::length));
        sortedStream.forEach(x -> System.out.print(x + " "));
    }


    @Test
    public void sortStream() {
        Stream<Integer> sortedStream = Stream.of(1,3,7,4,5,8,6,2).sorted();
        sortedStream.collect(Collectors.toList()).forEach(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println("========================================================================");

        Stream<Integer> sortedReverseStream = Stream.of(1,3,7,4,5,8,6,2).sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        sortedReverseStream.collect(Collectors.toList()).forEach(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println("========================================================================");
        Stream<Integer> sortedReverseStreamV2 = Stream.of(1,3,7,4,5,8,6,2).sorted((Integer o1, Integer o2) -> o2 - o1);
        sortedReverseStreamV2.collect(Collectors.toList()).forEach(x -> System.out.print(x + " "));
    }



    @Test
    public void collectTest() {
        List<String> list = Stream.of("hello", "world", "hello", "java").collect(Collectors.toList());
        list.forEach(x -> System.out.print(x + " "));
        System.out.println();
        Set<String> set = Stream.of("hello", "world", "hello", "java").collect(Collectors.toSet());
        set.forEach(x -> System.out.print(x + " "));
        System.out.println();
        Set<String> treeSet = Stream.of("hello", "world", "hello", "java").collect(Collectors.toCollection(TreeSet::new));
        treeSet.forEach(x -> System.out.print(x + " "));
        System.out.println();
        String resultStr = Stream.of("hello", "world", "hello", "java").collect(Collectors.joining(","));
        System.out.println(resultStr);
    }
    @Test
    public void reductionStream() {
        Stream<String> wordList = Stream.of("bj","tj","sh","yy","yq").distinct();
        Optional<String> firstWord = wordList.filter(word -> word.startsWith("y")).findFirst();
        System.out.println(firstWord);
        System.out.println(firstWord.orElse("unknown"));
    }

    @Test
    public void reduceTest() {
        Stream<Integer> list = Stream.of(1,2,3,4,5);
        Optional<Integer> result = list.reduce((x, y) -> x + y);
        System.out.println(result);
        System.out.println(result.orElse(0));
    }


}